package com.cheatbreaker.client.util.cosmetic;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.ClientResourceManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.imageio.ImageIO;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

/** Full-resolution previews: background decoding, page prefetch and bounded uploads. */
public final class CosmeticPreviewCache implements IResourceManagerReloadListener {
    static final int PAGE_SIZE = 5;
    static final int PIXELS_PER_FRAME = 128 * 1024;
    private static final long CACHE_BYTES = 64L * 1024 * 1024;
    private final List<ClientResourceManager> cosmetics;
    private final Map<ClientResourceManager, Integer> indices = new IdentityHashMap<>();
    private List<Integer> displayOrder = new java.util.ArrayList<>();
    private final LinkedHashMap<Integer, Entry> entries = new LinkedHashMap<>(16, 0.75F, true);
    private final LinkedHashSet<Integer> pending = new LinkedHashSet<>();
    private final ArrayDeque<Integer> retiredTextures = new ArrayDeque<>();
    private final LinkedHashSet<Integer> skippedPrefetch = new LinkedHashSet<>();
    private final ExecutorService decoder = Executors.newSingleThreadExecutor(task -> {
        Thread thread = new Thread(task, "Cosmetic Preview Decoder");
        thread.setDaemon(true);
        return thread;
    });
    private ImageReader reader;
    private boolean decoding;
    private int generation;
    private int page;

    interface ImageReader {
        BufferedImage read(ResourceLocation location) throws IOException;
    }

    interface Uploader {
        int allocate(int width, int height);
        void upload(int texture, int[] pixels, int width, int rows, int y);
        void delete(int texture);
    }

    private static final Uploader OPENGL = new Uploader() {
        public int allocate(int width, int height) {
            int texture = TextureUtil.glGenTextures();
            TextureUtil.allocateTexture(texture, width, height);
            return texture;
        }
        public void upload(int texture, int[] pixels, int width, int rows, int y) {
            TextureUtil.bindTexture(texture);
            TextureUtil.uploadTextureSub(0, pixels, width, rows, 0, y, false, false, false);
        }
        public void delete(int texture) { TextureUtil.deleteTexture(texture); }
    };

    static final class Entry {
        final CompletableFuture<Void> loaded = new CompletableFuture<>();
        int[] pixels;
        int width;
        int height;
        int texture;
        int uploadedRows;
        boolean ready;
        long bytes() { return (long)width * height * 4; }
    }

    public CosmeticPreviewCache(List<ClientResourceManager> cosmetics) {
        this.cosmetics = cosmetics;
        for (int index = 0; index < cosmetics.size(); index++) {
            indices.put(cosmetics.get(index), index);
            displayOrder.add(index);
        }
    }

    public synchronized void setDisplayOrder(List<ClientResourceManager> displayed) {
        List<Integer> next = new java.util.ArrayList<>();
        for (ClientResourceManager cosmetic : displayed) {
            Integer index = indices.get(cosmetic);
            if (index != null) next.add(index);
        }
        if (!next.equals(displayOrder)) {
            displayOrder = next;
            page = Math.min(page, Math.max(0, (displayOrder.size() - 1) / PAGE_SIZE));
            skippedPrefetch.clear();
            pending.clear();
        }
    }

    @Override
    public void onResourceManagerReload(IResourceManager resources) {
        reset(location -> {
            try (InputStream input = resources.getResource(location).getInputStream()) {
                return ImageIO.read(input);
            }
        });
    }

    synchronized void reset(ImageReader replacement) {
        generation++;
        for (Entry entry : entries.values()) retire(entry);
        entries.clear();
        pending.clear();
        skippedPrefetch.clear();
        reader = replacement;
        requestPage(page);
    }

    synchronized Entry getEntry(int index) { return entries.get(index); }

    public synchronized void requestPage(int requestedPage) {
        int nextPage = Math.max(0, Math.min(requestedPage, Math.max(0, (displayOrder.size() - 1) / PAGE_SIZE)));
        if (nextPage != page) skippedPrefetch.clear();
        page = nextPage;
        pending.clear();
        for (int candidate : new int[]{page, page + 1, page - 1}) {
            int first = candidate * PAGE_SIZE;
            if (candidate < 0 || first >= displayOrder.size()) continue;
            for (int slot = first; slot < Math.min(first + PAGE_SIZE, displayOrder.size()); slot++) {
                int index = displayOrder.get(slot);
                if (candidate != page && skippedPrefetch.contains(index)) continue;
                Entry entry = entries.get(index);
                if (entry == null) {
                    entry = new Entry();
                    entries.put(index, entry);
                }
                if (!entry.loaded.isDone()) pending.add(index);
            }
        }
        if (!decoding && reader != null && !pending.isEmpty()) {
            decoding = true;
            decoder.execute(this::decodePending);
        }
    }

    private void decodePending() {
        while (true) {
            int index;
            int requested;
            Entry entry;
            ImageReader source;
            synchronized (this) {
                if (pending.isEmpty()) {
                    decoding = false;
                    return;
                }
                Iterator<Integer> iterator = pending.iterator();
                index = iterator.next();
                iterator.remove();
                entry = entries.get(index);
                requested = generation;
                source = reader;
                if (entry == null || entry.loaded.isDone()) continue;
            }
            try {
                ClientResourceManager cosmetic = cosmetics.get(index);
                BufferedImage image = source.read(cosmetic.method_20848() == CosmeticType.CAPE
                    ? cosmetic.method_20859() : cosmetic.method_20850());
                if (image == null) throw new IOException("Invalid cosmetic preview image");
                int width = image.getWidth();
                int height = image.getHeight();
                int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);
                image.flush();
                synchronized (this) {
                    if (requested == generation && entries.get(index) == entry) {
                        entry.width = width;
                        entry.height = height;
                        entry.pixels = pixels;
                        entry.loaded.complete(null);
                        trim();
                    }
                }
            } catch (Exception error) {
                entry.loaded.completeExceptionally(error);
                LogManager.getLogger().warn("Could not decode cosmetic preview " + index, error);
            }
        }
    }

    private void retire(Entry entry) {
        if (entry.texture != 0) retiredTextures.add(entry.texture);
        entry.pixels = null;
        entry.loaded.cancel(false);
    }

    private void trim() {
        long bytes = 0;
        for (Entry entry : entries.values()) bytes += entry.bytes();
        Iterator<Map.Entry<Integer, Entry>> iterator = entries.entrySet().iterator();
        while (bytes > CACHE_BYTES && iterator.hasNext()) {
            Map.Entry<Integer, Entry> item = iterator.next();
            // Visible originals remain available even if this page alone exceeds the budget.
            if (displayOrder.subList(page * PAGE_SIZE, Math.min((page + 1) * PAGE_SIZE, displayOrder.size())).contains(item.getKey())) continue;
            bytes -= item.getValue().bytes();
            retire(item.getValue());
            pending.remove(item.getKey());
            skippedPrefetch.add(item.getKey());
            iterator.remove();
        }
    }

    public void beginFrame(int requestedPage) {
        requestPage(requestedPage);
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        advanceFrame(OPENGL);
    }

    synchronized void advanceFrame(Uploader uploader) {
        while (!retiredTextures.isEmpty()) uploader.delete(retiredTextures.remove());
        for (int candidate : new int[]{page, page + 1, page - 1}) {
            if (candidate < 0) continue;
            for (int slot = candidate * PAGE_SIZE; slot < Math.min((candidate + 1) * PAGE_SIZE, displayOrder.size()); slot++) {
                int index = displayOrder.get(slot);
                Entry entry = entries.get(index);
                if (entry == null || entry.pixels == null || entry.ready) continue;
                if (entry.texture == 0) entry.texture = uploader.allocate(entry.width, entry.height);
                int rows = Math.min(entry.height - entry.uploadedRows, Math.max(1, PIXELS_PER_FRAME / entry.width));
                int offset = entry.uploadedRows * entry.width;
                int[] stripe = Arrays.copyOfRange(entry.pixels, offset, offset + rows * entry.width);
                uploader.upload(entry.texture, stripe, entry.width, rows, entry.uploadedRows);
                entry.uploadedRows += rows;
                if (entry.uploadedRows == entry.height) {
                    entry.ready = true;
                    entry.pixels = null;
                }
                return; // At most one bounded upload per rendered frame.
            }
        }
    }

    public synchronized void draw(ClientResourceManager cosmetic, int x, int y) {
        Integer index = indices.get(cosmetic);
        Entry entry = index == null ? null : entries.get(index);
        if (entry == null || !entry.ready) {
            Gui.a(x + 2, y + 2, x + 14, y + 14, 0x40777777);
            return;
        }
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        TextureUtil.bindTexture(entry.texture);
        if (cosmetic.method_20848() == CosmeticType.CAPE) {
            GL11.glPushMatrix();
            GL11.glTranslatef(x, y, 0.0F);
            GL11.glScalef(0.25F, 0.13F, 0.25F);
            RenderUtil.method_22065(0.0F, 0.0F, 2.0F, 7.0F, 44, 120);
            GL11.glPopMatrix();
        } else {
            GL11.glEnable(GL11.GL_BLEND);
            Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, 16, 16, 16, 16);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }
}
