package recovery;

import java.io.IOException;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
import net.minecraft.client.network.LanServerDetector;

public class LanDiscoveryResponsivenessTest extends TestCase {
    private static class SlowDetector extends LanServerDetector.ThreadLanServerFind {
        final CountDownLatch opening = new CountDownLatch(1);
        final CountDownLatch release = new CountDownLatch(1);
        volatile Thread socketThread;

        SlowDetector() throws IOException {
            super(new LanServerDetector.LanServerList());
        }

        @Override
        protected MulticastSocket createSocket() throws IOException {
            socketThread = Thread.currentThread();
            opening.countDown();
            try {
                release.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
            return new MulticastSocket((SocketAddress)null);
        }
    }

    public void testSocketSetupRunsInBackgroundAndCancellationClosesSocket() throws Exception {
        SlowDetector detector = new SlowDetector();
        try {
            assertNull(detector.socket);
            detector.start();
            assertTrue(detector.opening.await(1, TimeUnit.SECONDS));
            assertSame(detector, detector.socketThread);
            detector.interrupt();
            detector.release.countDown();
            detector.join(1000);
            assertFalse(detector.isAlive());
            assertNotNull(detector.socket);
            assertTrue(detector.socket.isClosed());
        } finally {
            detector.interrupt();
            detector.release.countDown();
            detector.join(1000);
        }
    }
}
