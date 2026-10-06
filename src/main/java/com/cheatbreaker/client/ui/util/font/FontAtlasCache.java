package com.cheatbreaker.client.ui.util.font;

import java.awt.Font;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.LongSupplier;
import java.util.function.Predicate;

/** Accessed by the render thread; only image generation runs on the worker. */
final class FontAtlasCache<T> {
   interface Textures<T> {
      T upload(FontAtlasImage image);
      void delete(T texture);
   }

   private final LinkedHashMap<Key, Entry<T>> entries = new LinkedHashMap<>(16, 0.75F, true);
   private final ExecutorService worker;
   private final Textures<T> textures;
   private final LongSupplier clock;
   private final long maximumBytes;
   private final int maximumEntries;
   private long bytes, lastUpload = Long.MIN_VALUE;

   FontAtlasCache(ExecutorService worker, Textures<T> textures, LongSupplier clock, long maximumBytes, int maximumEntries) {
      this.worker = worker; this.textures = textures; this.clock = clock;
      this.maximumBytes = maximumBytes; this.maximumEntries = maximumEntries;
   }

   Atlas<T> get(Font font, double density, boolean antiAlias, boolean fractionalMetrics, Predicate<T> canDelete) {
      if (density == 1) return null; // Reuse the existing native-density font textures.
      Key key = new Key(font, density, antiAlias, fractionalMetrics);
      Entry<T> entry = entries.get(key);
      if (entry == null) {
         int pending = 0;
         for (Entry<T> value : entries.values()) if (value.pending != null) pending++;
         if (pending >= 4 || !makeRoom(0, true, canDelete, null)) return null;
         entry = new Entry<>();
         entry.pending = worker.submit(() -> FontAtlasImage.generate(font, density, antiAlias, fractionalMetrics));
         entries.put(key, entry);
         return null;
      }
      if (entry.atlas != null) return entry.atlas;
      if (entry.pending == null || !entry.pending.isDone()) return null;
      long now = clock.getAsLong();
      if (lastUpload != Long.MIN_VALUE && now - lastUpload < 16_000_000L) return null;
      try {
         FontAtlasImage image = entry.pending.get();
         // Account for the DynamicTexture CPU pixel array as well as GPU storage.
         long required = (long)image.width * image.height * 8;
         if (required > maximumBytes) {
            image.image.flush(); entry.pending = null; return null;
         }
         if (!makeRoom(required, false, canDelete, entry)) return null;
         T texture = textures.upload(image);
         entry.atlas = new Atlas<>(texture, image, required);
         image.image.flush(); image.image = null;
         entry.pending = null;
         bytes += required;
         lastUpload = now;
         return entry.atlas;
      } catch (Exception exception) {
         entry.pending = null;
         return null; // Keep drawing with the existing font if rasterization fails.
      }
   }

   private boolean makeRoom(long required, boolean adding, Predicate<T> canDelete, Entry<T> keep) {
      Iterator<Map.Entry<Key, Entry<T>>> iterator = entries.entrySet().iterator();
      while ((bytes + required > maximumBytes || adding && entries.size() >= maximumEntries) && iterator.hasNext()) {
         Entry<T> entry = iterator.next().getValue();
         if (entry == keep || entry.atlas != null && !canDelete.test(entry.atlas.texture)) continue;
         if (entry.pending != null) entry.pending.cancel(true);
         if (entry.atlas != null) { textures.delete(entry.atlas.texture); bytes -= entry.atlas.bytes; }
         iterator.remove();
      }
      return bytes + required <= maximumBytes && (!adding || entries.size() < maximumEntries);
   }

   static final class Atlas<T> {
      final T texture;
      final FontAtlasImage.Glyph[] glyphs;
      final int width, height;
      final double density;
      final long bytes;
      Atlas(T texture, FontAtlasImage image, long bytes) {
         this.texture = texture; this.glyphs = image.glyphs;
         this.width = image.width; this.height = image.height; this.density = image.density; this.bytes = bytes;
      }
   }

   private static final class Entry<T> {
      Future<FontAtlasImage> pending;
      Atlas<T> atlas;
   }

   private static final class Key {
      final Font font;
      final double density;
      final boolean antiAlias, fractionalMetrics;
      Key(Font font, double density, boolean antiAlias, boolean fractionalMetrics) {
         this.font = font; this.density = density; this.antiAlias = antiAlias; this.fractionalMetrics = fractionalMetrics;
      }
      @Override public int hashCode() { return Objects.hash(font, density, antiAlias, fractionalMetrics); }
      @Override public boolean equals(Object other) {
         if (!(other instanceof Key)) return false;
         Key key = (Key)other;
         return font.equals(key.font) && density == key.density && antiAlias == key.antiAlias && fractionalMetrics == key.fractionalMetrics;
      }
   }
}
