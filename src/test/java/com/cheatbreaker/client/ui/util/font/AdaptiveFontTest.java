package com.cheatbreaker.client.ui.util.font;

import java.awt.Font;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import junit.framework.TestCase;

public class AdaptiveFontTest extends TestCase {
   private Font font() throws Exception {
      try (InputStream stream = Files.newInputStream(Paths.get("src/main/resources/assets/minecraft/client/font/Roboto-Regular.ttf"))) {
         return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(13.0F);
      }
   }

   public void testAllUiDensitiesAndStylesProduceUnclippedGlyphCells() throws Exception {
      Font font = font();
      for (double density : new double[] {0.5, 1, 1.5, 2, 2.5}) {
         for (int style = 0; style < 4; style++) {
            FontAtlasImage atlas = FontAtlasImage.generate(font.deriveFont(style), density, true, true);
            assertEquals(density, atlas.density, 0.0);
            assertTrue(atlas.width <= 2048 && atlas.height <= 2048);
            for (FontAtlasImage.Glyph glyph : atlas.glyphs) {
               assertTrue(glyph.x >= 0 && glyph.y >= 0);
               assertTrue(glyph.x + glyph.width <= atlas.width);
               assertTrue(glyph.y + glyph.height <= atlas.height);
            }
            FontAtlasImage.Glyph glyph = atlas.glyphs['M'];
            boolean ink = false;
            for (int y = glyph.y; y < glyph.y + glyph.height; y++) {
               for (int x = glyph.x; x < glyph.x + glyph.width; x++) {
                  int pixel = atlas.image.getRGB(x, y);
                  if ((pixel >>> 24) != 0) {
                     ink = true;
                     assertEquals(0xFFFFFF, pixel & 0xFFFFFF);
                  }
               }
            }
            assertTrue("Missing glyph at density " + density + " and style " + style, ink);
            atlas.image.flush();
         }
      }
   }

   public void testPixelAlignmentIncludesTranslationAndOddScaleGlyphAdvances() {
      FontScreenGeometry geometry = new FontScreenGeometry(3, -3, 0.25, 1080.5);
      assertEquals(1.5, geometry.rasterScale(), 0.0);
      for (double x : new double[] {10.25, 12.5, 15, 19.5}) {
         double physical = geometry.snapX(x) * geometry.scaleX + geometry.originX;
         assertEquals(Math.rint(physical), physical, 0.000001);
         double physicalY = geometry.snapY(x) * geometry.scaleY + geometry.originY;
         assertEquals(Math.rint(physicalY), physicalY, 0.000001);
      }
      assertEquals(10.25, FontScreenGeometry.UNALIGNED.snapX(10.25), 0.0);
   }

   public void testRoundedMinecraftViewportStillUsesAdaptiveRasterizationButPerspectiveDoesNot() {
      float[] model = new float[16], projection = new float[16];
      model[0] = model[5] = model[10] = model[15] = 1;
      projection[0] = 2.0F / 854;
      projection[5] = -2.0F / 452;
      projection[10] = -1;
      projection[12] = -1; projection[13] = 1; projection[15] = 1;
      FontScreenGeometry geometry = FontScreenGeometry.fromMatrices(model, projection, new int[] {0, 0, 2560, 1356});
      assertEquals(1.5, geometry.rasterScale(), 0.0);
      projection[11] = -1;
      assertSame(FontScreenGeometry.UNALIGNED, FontScreenGeometry.fromMatrices(model, projection, new int[] {0, 0, 2560, 1356}));
   }

   public void testCacheUploadsAfterWorkerCompletesAndEvictsOldTextureWithinBudget() throws Exception {
      ExecutorService worker = Executors.newSingleThreadExecutor();
      try {
         AtomicLong clock = new AtomicLong();
         RecordingTextures textures = new RecordingTextures();
         FontAtlasCache<Integer> cache = new FontAtlasCache<>(worker, textures, clock::get, 4L * 1024 * 1024, 2);
         Font font = font();
         assertNull(cache.get(font, 0.5, true, true, texture -> true));
         worker.submit(() -> {}).get(10, TimeUnit.SECONDS);
         FontAtlasCache.Atlas<Integer> first = cache.get(font, 0.5, true, true, texture -> true);
         assertNotNull(first);
         assertSame(first, cache.get(font, 0.5, true, true, texture -> true));
         assertEquals(1, textures.uploads);
         assertNull(cache.get(font, 1.5, true, true, texture -> true));
         worker.submit(() -> {}).get(10, TimeUnit.SECONDS);
         assertNull(cache.get(font, 1.5, true, true, texture -> true));
         clock.set(20_000_000);
         assertNotNull(cache.get(font, 1.5, true, true, texture -> true));
         assertEquals(2, textures.uploads);
         clock.set(40_000_000);
         assertNull(cache.get(font, 2, true, true, texture -> true));
         worker.submit(() -> {}).get(10, TimeUnit.SECONDS);
         assertNotNull(cache.get(font, 2, true, true, texture -> true));
         assertTrue(textures.deletes >= 1);
         assertNull(cache.get(font, 1, true, true, texture -> true));
      } finally {
         worker.shutdownNow();
      }
   }

   public void testCacheProtectsTextureBeingRestoredAndUsesOriginalFontForOversizedAtlas() throws Exception {
      ExecutorService worker = Executors.newSingleThreadExecutor();
      try {
         RecordingTextures textures = new RecordingTextures();
         FontAtlasCache<Integer> cache = new FontAtlasCache<>(worker, textures, () -> 0L, 1024L * 1024, 1);
         Font font = font();
         cache.get(font, 0.5, true, true, texture -> true);
         worker.submit(() -> {}).get(10, TimeUnit.SECONDS);
         assertNotNull(cache.get(font, 0.5, true, true, texture -> true));
         assertNull(cache.get(font, 2, true, true, texture -> false));
         assertEquals(0, textures.deletes);
         FontAtlasCache<Integer> tiny = new FontAtlasCache<>(worker, textures, () -> 0L, 1, 2);
         assertNull(tiny.get(font, 2.5, true, true, texture -> true));
         worker.submit(() -> {}).get(10, TimeUnit.SECONDS);
         assertNull(tiny.get(font, 2.5, true, true, texture -> true));
         assertEquals(1, textures.uploads);
      } finally {
         worker.shutdownNow();
      }
   }

   private static final class RecordingTextures implements FontAtlasCache.Textures<Integer> {
      int uploads, deletes;
      @Override public Integer upload(FontAtlasImage image) { return ++uploads; }
      @Override public void delete(Integer texture) { deletes++; }
   }
}
