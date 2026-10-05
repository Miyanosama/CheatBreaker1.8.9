package recovery;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import sun.misc.Unsafe;

public class ServerConnectionResponsivenessTest extends TestCase {
    private static final CountDownLatch RESOLVING = new CountDownLatch(1);
    private static final CountDownLatch RELEASE = new CountDownLatch(1);
    private static volatile Thread resolverThread;
    private static volatile boolean srvRequested;

    private static class SlowConnecting extends GuiConnecting {
        SlowConnecting(Minecraft minecraft, ServerData server) {
            super(null, minecraft, server);
        }

        @Override
        protected ServerAddress resolveAddress(String host, int port, boolean lookupSrv) {
            resolverThread = Thread.currentThread();
            srvRequested = lookupSrv;
            RESOLVING.countDown();
            try {
                RELEASE.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException error) {
                Thread.currentThread().interrupt();
            }
            return new ServerAddress("127.0.0.1", 25565);
        }
    }

    public void testScreenReturnsWhileDnsIsBlockedAndCancelStopsConnection() throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Minecraft minecraft = (Minecraft)((Unsafe)field.get(null)).allocateInstance(Minecraft.class);
        ServerData server = new ServerData("Slow DNS", "example.invalid", false);
        ExecutorService caller = Executors.newSingleThreadExecutor();
        try {
            Future<SlowConnecting> future = caller.submit(() -> new SlowConnecting(minecraft, server));
            SlowConnecting screen = future.get(1, TimeUnit.SECONDS);
            assertTrue(RESOLVING.await(1, TimeUnit.SECONDS));
            assertTrue(resolverThread.getName().startsWith("Server Connector #"));
            assertTrue(srvRequested);
            assertSame(server, minecraft.currentServerData);
            assertNull(screen.networkManager);
            screen.cancel = true;
            RELEASE.countDown();
            resolverThread.join(1000);
            assertFalse(resolverThread.isAlive());
            assertNull(screen.networkManager);
        } finally {
            RELEASE.countDown();
            caller.shutdownNow();
        }
    }
}
