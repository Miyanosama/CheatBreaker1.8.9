package recovery;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.EventBus;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Locale;
import net.minecraft.util.ChatComponentText;
import sun.misc.Unsafe;

public class ReconnectAfterDisconnectTest extends TestCase {
    private static <T> T allocate(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    public void testServerKickPreservesTargetAfterWorldUnloadClearsCurrentServer() throws Exception {
        CheatBreaker previousClient = CheatBreaker.instance;
        Locale previousLocale = I18n.i18nLocale;
        try {
            CheatBreaker.instance = allocate(CheatBreaker.class);
            EventBus bus = allocate(EventBus.class);
            bus.recoveredField2205 = new ConcurrentHashMap<>();
            CheatBreaker.instance.recoveredField1568 = bus;
            I18n.setLocale(new Locale());
            RecordingMinecraft minecraft = allocate(RecordingMinecraft.class);
            ServerData server = new ServerData("Test server", "example.invalid:25570", false);
            server.setResourceMode(ServerData.ServerResourceMode.ENABLED);
            minecraft.setServerData(server);
            NetHandlerPlayClient handler = allocate(NetHandlerPlayClient.class);
            handler.gameController = minecraft;
            handler.guiScreenServer = new GuiScreen();
            handler.onDisconnect(new ChatComponentText("Kicked"));
            assertTrue(minecraft.worldUnloaded);
            assertNull(minecraft.getCurrentServerData());
            assertTrue(minecraft.currentScreen instanceof GuiDisconnected);
            GuiDisconnected screen = (GuiDisconnected)minecraft.currentScreen;
            assertSame(server, screen.reconnectServer);
            assertEquals("example.invalid:25570", screen.reconnectServer.serverIP);
            assertEquals(ServerData.ServerResourceMode.ENABLED, screen.reconnectServer.getResourceMode());
        } finally {
            CheatBreaker.instance = previousClient;
            I18n.setLocale(previousLocale);
        }
    }

    public void testConnectionFailureAlsoRetainsTargetIndependentlyOfCurrentServer() throws Exception {
        Minecraft previousMinecraft = Minecraft.theMinecraft;
        Locale previousLocale = I18n.i18nLocale;
        try {
            I18n.setLocale(new Locale());
            Minecraft minecraft = allocate(Minecraft.class);
            Minecraft.theMinecraft = minecraft;
            ServerData server = new ServerData("Login failure", "example.invalid:25571", false);
            minecraft.setServerData(server);
            GuiDisconnected screen = new GuiDisconnected(new GuiScreen(), "connect.failed",
                    new ChatComponentText("Login rejected"));
            minecraft.setServerData(null);
            assertSame(server, screen.reconnectServer);
            assertEquals("example.invalid:25571", screen.reconnectServer.serverIP);
        } finally {
            Minecraft.theMinecraft = previousMinecraft;
            I18n.setLocale(previousLocale);
        }
    }

    private static final class RecordingMinecraft extends Minecraft {
        boolean worldUnloaded;
        RecordingMinecraft() { super(null); }
        @Override public void loadWorld(WorldClient world) {
            worldUnloaded = true;
            setServerData(null);
        }
        @Override public void displayGuiScreen(GuiScreen screen) { currentScreen = screen; }
    }
}
