package com.cheatbreaker.client.util.cosmetic;

import com.cheatbreaker.client.util.ClientResourceManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;

public class CosmeticPreviewCacheTest extends TestCase {
    private static ClientResourceManager wing(int index) {
        return new ClientResourceManager("local", "Wing " + index, CosmeticType.WINGS,
            0.125F, false, "client/wings/" + index + ".png");
    }

    private static BufferedImage image(int width, int height, int color) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        int[] pixels = new int[width * height];
        java.util.Arrays.fill(pixels, color);
        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }

    public void testOriginalPixelsUploadInBoundedFramesAndTextureIsReused() throws Exception {
        CosmeticPreviewCache cache = new CosmeticPreviewCache(Collections.singletonList(wing(0)));
        BufferedImage original = image(1024, 512, 0xff123456);
        original.setRGB(1023, 511, 0xffabcdef);
        cache.reset(location -> original);
        CosmeticPreviewCache.Entry entry = cache.getEntry(0);
        entry.loaded.get(5, TimeUnit.SECONDS);
        assertEquals(1024, entry.width);
        assertEquals(512, entry.height);
        assertEquals(0xffabcdef, entry.pixels[entry.pixels.length - 1]);
        final int[] calls = new int[2];
        final int[] uploaded = new int[1024 * 512];
        CosmeticPreviewCache.Uploader uploader = new CosmeticPreviewCache.Uploader() {
            public int allocate(int width, int height) {
                assertEquals(1024, width);
                assertEquals(512, height);
                calls[0]++;
                return 7;
            }
            public void upload(int texture, int[] pixels, int width, int rows, int y) {
                assertEquals(7, texture);
                assertTrue(pixels.length <= CosmeticPreviewCache.PIXELS_PER_FRAME);
                assertEquals(y, calls[1] * 128);
                System.arraycopy(pixels, 0, uploaded, y * width, pixels.length);
                calls[1]++;
            }
            public void delete(int texture) { fail("Cached texture must remain available"); }
        };
        for (int frame = 0; frame < 4; frame++) {
            assertFalse(entry.ready);
            cache.advanceFrame(uploader);
            assertEquals(frame + 1, calls[1]);
        }
        assertTrue(entry.ready);
        assertNull(entry.pixels);
        assertEquals(0xffabcdef, uploaded[uploaded.length - 1]);
        assertEquals(0xff123456, uploaded[0]);
        cache.requestPage(0);
        cache.advanceFrame(uploader);
        assertEquals(1, calls[0]);
        assertEquals(4, calls[1]);
    }

    public void testNewPageTakesPriorityOverQueuedPrefetchWithoutBlockingCaller() throws Exception {
        List<ClientResourceManager> cosmetics = new ArrayList<>();
        for (int index = 0; index < 15; index++) cosmetics.add(wing(index));
        CosmeticPreviewCache cache = new CosmeticPreviewCache(cosmetics);
        CountDownLatch firstEntered = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        List<String> decoded = Collections.synchronizedList(new ArrayList<>());
        Thread caller = Thread.currentThread();
        cache.reset(location -> {
            assertNotSame(caller, Thread.currentThread());
            decoded.add(location.getResourcePath());
            firstEntered.countDown();
            await(release);
            return image(64, 32, 0xff0000ff);
        });
        try {
            assertTrue(firstEntered.await(1, TimeUnit.SECONDS));
            cache.requestPage(2);
            CosmeticPreviewCache.Entry current = cache.getEntry(10);
            assertFalse(current.loaded.isDone());
            release.countDown();
            current.loaded.get(5, TimeUnit.SECONDS);
            assertEquals("client/preview/wings/0.png", decoded.get(0));
            assertEquals("client/preview/wings/10.png", decoded.get(1));
            assertEquals(64, current.width);
        } finally {
            release.countDown();
        }
    }

    public void testReloadRejectsStaleOriginalPixels() throws Exception {
        CosmeticPreviewCache cache = new CosmeticPreviewCache(Collections.singletonList(wing(0)));
        CountDownLatch entered = new CountDownLatch(1);
        CountDownLatch release = new CountDownLatch(1);
        cache.reset(location -> {
            entered.countDown();
            await(release);
            return image(256, 256, 0xffff0000);
        });
        try {
            assertTrue(entered.await(1, TimeUnit.SECONDS));
            CosmeticPreviewCache.Entry old = cache.getEntry(0);
            cache.reset(location -> image(512, 256, 0xff0000ff));
            CosmeticPreviewCache.Entry current = cache.getEntry(0);
            assertTrue(old.loaded.isCancelled());
            release.countDown();
            current.loaded.get(5, TimeUnit.SECONDS);
            assertEquals(512, current.width);
            assertEquals(0xff0000ff, current.pixels[0]);
        } finally {
            release.countDown();
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
