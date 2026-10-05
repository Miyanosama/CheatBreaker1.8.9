package com.cheatbreaker.client.util.cosmetic;

import com.cheatbreaker.client.util.ClientResourceManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;

public class CosmeticPreviewCacheTest extends TestCase {
    private static BufferedImage solid(int color) {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        int[] pixels = new int[256 * 256];
        java.util.Arrays.fill(pixels, color);
        image.setRGB(0, 0, 256, 256, pixels, 0, 256);
        return image;
    }

    public void testBlockedDecodeReturnsImmediatelyAndBuildsAllPages() throws Exception {
        List<ClientResourceManager> cosmetics = new LocalCosmetics(new File(".target/preview-test-no-config")).getCosmetics();
        CosmeticPreviewCache cache = new CosmeticPreviewCache(cosmetics);
        CountDownLatch entered = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        Thread caller = Thread.currentThread();
        CompletableFuture<Void> load = cache.reloadImages(location -> {
            assertNotSame(caller, Thread.currentThread());
            entered.countDown();
            try {
                release.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException error) {
                throw new java.io.IOException(error);
            }
            return solid(0xff3377aa);
        });
        try {
            assertTrue(entered.await(1, TimeUnit.SECONDS));
            assertFalse(load.isDone());
            assertNull(cache.getImage());
        } finally {
            release.countDown();
        }
        load.get(5, TimeUnit.SECONDS);
        BufferedImage atlas = cache.getImage();
        assertNotNull(atlas);
        assertEquals(256, atlas.getWidth());
        for (int index = 0; index < cosmetics.size(); index++) {
            int x = index % 8 * 32;
            int y = index / 8 * 32;
            assertEquals(0xff3377aa, atlas.getRGB(x + 6, y + 6));
            assertEquals(0, atlas.getRGB(x + 20, y + 20));
        }
    }

    public void testResourceReloadDiscardsOldDecodeResult() throws Exception {
        ClientResourceManager wings = new ClientResourceManager("local", "Test", CosmeticType.WINGS,
            0.125F, false, "client/wings/blue.png");
        CosmeticPreviewCache cache = new CosmeticPreviewCache(Collections.singletonList(wings));
        CountDownLatch oldEntered = new CountDownLatch(1);
        CountDownLatch oldRelease = new CountDownLatch(1);
        CountDownLatch newEntered = new CountDownLatch(1);
        CountDownLatch newRelease = new CountDownLatch(1);
        CompletableFuture<Void> old = cache.reloadImages(location -> {
            oldEntered.countDown();
            await(oldRelease);
            return solid(0xffff0000);
        });
        try {
            assertTrue(oldEntered.await(1, TimeUnit.SECONDS));
            CompletableFuture<Void> current = cache.reloadImages(location -> {
                newEntered.countDown();
                await(newRelease);
                return solid(0xff0000ff);
            });
            oldRelease.countDown();
            old.get(5, TimeUnit.SECONDS);
            assertTrue(newEntered.await(1, TimeUnit.SECONDS));
            assertNull(cache.getImage());
            newRelease.countDown();
            current.get(5, TimeUnit.SECONDS);
            assertEquals(0xff0000ff, cache.getImage().getRGB(6, 6));
        } finally {
            oldRelease.countDown();
            newRelease.countDown();
        }
    }

    private static void await(CountDownLatch latch) throws java.io.IOException {
        try {
            latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException error) {
            throw new java.io.IOException(error);
        }
    }
}
