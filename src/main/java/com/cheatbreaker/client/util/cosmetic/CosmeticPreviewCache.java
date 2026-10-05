package com.cheatbreaker.client.util.cosmetic;

import com.cheatbreaker.client.util.ClientResourceManager;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL11;

/** Decode on a worker; upload only the small, shared preview atlas on the render thread. */
public final class CosmeticPreviewCache implements IResourceManagerReloadListener {
    private static final int COLUMNS = 8;
    private static final int CELL = 32;
    private static final ResourceLocation TEXTURE = new ResourceLocation("client/local_cosmetic_previews");
    private final List<ClientResourceManager> cosmetics;
    private final Map<ClientResourceManager, Integer> indices = new IdentityHashMap<>();
    private final ExecutorService decoder = Executors.newSingleThreadExecutor(task -> {
        Thread thread = new Thread(task, "Cosmetic Preview Decoder");
        thread.setDaemon(true);
        return thread;
    });
    private volatile BufferedImage image;
    private BufferedImage uploadedImage;
    private int generation;

    interface ImageReader {
        BufferedImage read(ResourceLocation location) throws IOException;
    }

    public CosmeticPreviewCache(List<ClientResourceManager> cosmetics) {
        this.cosmetics = cosmetics;
        for (int index = 0; index < cosmetics.size(); index++) indices.put(cosmetics.get(index), index);
    }

    @Override
    public void onResourceManagerReload(IResourceManager resources) {
        reloadImages(location -> {
            try (InputStream input = resources.getResource(location).getInputStream()) {
                return ImageIO.read(input);
            }
        });
    }

    synchronized CompletableFuture<Void> reloadImages(ImageReader reader) {
        final int requested = ++generation;
        image = null;
        return CompletableFuture.runAsync(() -> {
            int height = Math.max(CELL, ((cosmetics.size() + COLUMNS - 1) / COLUMNS) * CELL);
            BufferedImage atlas = new BufferedImage(COLUMNS * CELL, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = atlas.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            try {
                for (int index = 0; index < cosmetics.size(); index++) {
                    synchronized (this) {
                        if (requested != generation) return;
                    }
                    ClientResourceManager cosmetic = cosmetics.get(index);
                    boolean cape = cosmetic.method_20848() == CosmeticType.CAPE;
                    try {
                        BufferedImage source = reader.read(cape ? cosmetic.method_20859() : cosmetic.method_20850());
                        if (source == null) throw new IOException("Invalid cosmetic preview image");
                        int x = index % COLUMNS * CELL;
                        int y = index / COLUMNS * CELL;
                        if (cape) {
                            // Match the original cape preview's normalized texture coordinates.
                            graphics.drawImage(source, x + 2, y, x + 13, y + 16,
                                source.getWidth() * 2 / 256, source.getHeight() * 7 / 256,
                                source.getWidth() * 46 / 256, source.getHeight() * 127 / 256, null);
                        } else {
                            graphics.drawImage(source, x, y, 16, 16, null);
                        }
                        source.flush();
                    } catch (IOException error) {
                        LogManager.getLogger().warn("Could not decode cosmetic preview " + cosmetic.method_20858(), error);
                    }
                }
            } finally {
                graphics.dispose();
            }
            synchronized (this) {
                if (requested == generation) image = atlas;
            }
        }, decoder);
    }

    BufferedImage getImage() {
        return image;
    }

    public void draw(ClientResourceManager cosmetic, int x, int y) {
        Integer index = indices.get(cosmetic);
        BufferedImage ready = image;
        if (ready == null || index == null) {
            Gui.a(x + 2, y + 2, x + 14, y + 14, 0x40777777);
            return;
        }
        TextureManager textures = Minecraft.getMinecraft().getTextureManager();
        if (uploadedImage != ready || textures.getTexture(TEXTURE) == null) {
            textures.deleteTexture(TEXTURE);
            textures.loadTexture(TEXTURE, new DynamicTexture(ready));
            uploadedImage = ready;
        }
        textures.bindTexture(TEXTURE);
        GL11.glEnable(GL11.GL_BLEND);
        Gui.drawModalRectWithCustomSizedTexture(x, y, index % COLUMNS * CELL, index / COLUMNS * CELL,
            16, 16, ready.getWidth(), ready.getHeight());
        GL11.glDisable(GL11.GL_BLEND);
    }
}
