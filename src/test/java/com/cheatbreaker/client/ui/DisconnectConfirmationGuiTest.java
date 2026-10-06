package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import java.lang.reflect.Field;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.multiplayer.WorldClient;
import sun.misc.Unsafe;

public class DisconnectConfirmationGuiTest extends TestCase {
   private static <T> T allocate(Class<T> type) throws Exception {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
   }

   private DisconnectConfirmationGui screen(RecordingMinecraft client) throws Exception {
      DisconnectConfirmationGui screen = allocate(DisconnectConfirmationGui.class);
      screen.j = client;
      client.currentScreen = screen;
      client.sound = allocate(SilentSoundHandler.class);
      screen.recoveredField1022 = new GradientTextButton("Back");
      screen.recoveredField1025 = new GradientTextButton("Disconnect");
      screen.recoveredField1021 = new GradientTextButton("Reconnect");
      screen.recoveredField1025.setElementSize(10, 10, 100, 12);
      return screen;
   }

   private void disconnect(DisconnectConfirmationGui screen) {
      try {
         screen.onMouseClicked(20, 15, 0);
         fail("Expected the test client to stop before constructing the OpenGL main menu");
      } catch (WorldUnloaded expected) {
         // The real disconnect click reached loadWorld(null), without starting native rendering.
      }
   }

   public void testDisconnectAfterWorldAlreadyUnloaded() throws Exception {
      RecordingMinecraft client = allocate(RecordingMinecraft.class);
      DisconnectConfirmationGui screen = screen(client);
      disconnect(screen);
      assertEquals(1, client.unloads);
      assertNull(client.theWorld);
   }

   public void testDisconnectNotifiesWorldOnceAndIgnoresQueuedClickAfterScreenChange() throws Exception {
      RecordingMinecraft client = allocate(RecordingMinecraft.class);
      DisconnectConfirmationGui screen = screen(client);
      RecordingWorld world = allocate(RecordingWorld.class);
      client.theWorld = world;
      disconnect(screen);
      screen.onMouseClicked(20, 15, 0);
      assertEquals(1, world.disconnects);
      assertEquals(1, client.unloads);
   }

   public void testRightClickDoesNotDisconnect() throws Exception {
      RecordingMinecraft client = allocate(RecordingMinecraft.class);
      DisconnectConfirmationGui screen = screen(client);
      screen.onMouseClicked(20, 15, 1);
      assertEquals(0, client.unloads);
      assertSame(screen, client.currentScreen);
   }

   private static class WorldUnloaded extends RuntimeException {}

   public static class RecordingMinecraft extends Minecraft {
      int unloads;
      SoundHandler sound;
      public RecordingMinecraft() { super((GameConfiguration)null); }
      @Override public SoundHandler getSoundHandler() { return sound; }
      @Override public void loadWorld(WorldClient world) {
         if (world != null) throw new AssertionError("Expected world unload");
         unloads++;
         theWorld = null;
         currentScreen = null;
         throw new WorldUnloaded();
      }
   }

   public static class SilentSoundHandler extends SoundHandler {
      public SilentSoundHandler() { super(null, null); }
      @Override public void playSound(ISound sound) {}
   }

   public static class RecordingWorld extends WorldClient {
      int disconnects;
      public RecordingWorld() { super(null, null, 0, null, null); }
      @Override public void method_05035() { disconnects++; }
   }
}
