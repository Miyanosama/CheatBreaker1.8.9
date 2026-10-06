package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.element.module.ModuleListElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.client.settings.GameSettings;
import sun.misc.Unsafe;

public class ClientUiScaleTest extends TestCase {
   public void testAllSixChoicesAtLargeResolution() {
      String[] choices = {"Small", "Normal", "Large", "4x", "5x", "Auto"};
      int[] expected = {1, 2, 3, 4, 5, 9};
      for (int index = 0; index < choices.length; index++) {
         assertEquals(expected[index], ClientUiScale.resolve(choices[index], 3840, 2160));
      }
   }

   public void testSmallWindowsClampScaleToKeepSettingsAccessible() {
      assertEquals(1, ClientUiScale.resolve("5x", 640, 480));
      assertEquals(3, ClientUiScale.resolve("Auto", 1280, 720));
      assertEquals(4, ClientUiScale.resolve("5x", 1920, 1080));
      assertEquals(5, ClientUiScale.resolve("5x", 2560, 1440));
      assertEquals(1, ClientUiScale.resolve("Auto", 200, 150));
   }

   public void testRenderingAndMouseCoordinatesRemainIndependentOfMinecraftGuiScale() {
      for (int minecraftScale = 1; minecraftScale <= 5; minecraftScale++) {
         float renderScale = ClientUiScale.renderScale("Large", 2560, 1440, minecraftScale);
         assertEquals(3.0F, renderScale * minecraftScale, 0.00001F);
         float componentX = 125.0F;
         float physicalMouseX = componentX * 3.0F;
         float minecraftMouseX = physicalMouseX / minecraftScale;
         assertEquals(componentX, minecraftMouseX / renderScale, 0.00001F);
      }
   }

   public void testDefaultAndUnknownValuesPreserveNormalScale() {
      assertEquals(2, ClientUiScale.resolve(null, 1920, 1080));
      assertEquals(2, ClientUiScale.resolve("Unknown", 1920, 1080));
   }

   private static <T> T allocate(Class<T> type) throws Exception {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
   }

   public void testChangingScalePreservesOpenSettingsPageAndScrollPosition() throws Exception {
      CheatBreaker previous = CheatBreaker.instance;
      boolean closing = CBModulesGui.recoveredField784;
      try {
         CheatBreaker client = allocate(CheatBreaker.class);
         client.globalSettings = allocate(GlobalSettings.class);
         client.globalSettings.clientUiScale = allocate(Setting.class);
         client.globalSettings.clientUiScale.recoveredField3086 = "Large";
         CheatBreaker.instance = client;
         RecordingGui gui = allocate(RecordingGui.class);
         gui.j = allocate(TestMinecraft.class);
         gui.j.displayWidth = 1920;
         gui.j.displayHeight = 1080;
         gui.j.gameSettings = allocate(GameSettings.class);
         gui.j.gameSettings.guiScale = 2;
         gui.l = 960;
         gui.initGui();
         ModuleListElement oldPanel = (ModuleListElement)gui.recoveredField789.get(2);
         oldPanel.recoveredField1358 = true;
         oldPanel.recoveredField3012 = -137;
         oldPanel.recoveredField3010 = -5.5;
         gui.recoveredField795 = oldPanel;
         gui.currentScrollableElement = oldPanel;
         gui.recoveredField778 = 30.0F;
         CBModulesGui.recoveredField784 = false;
         Method refresh = CBModulesGui.class.getDeclaredMethod("refreshClientUiScale");
         refresh.setAccessible(true);
         refresh.invoke(gui);
         ModuleListElement newPanel = (ModuleListElement)gui.recoveredField789.get(2);
         assertNotSame(oldPanel, newPanel);
         assertSame(newPanel, gui.recoveredField795);
         assertSame(newPanel, gui.currentScrollableElement);
         assertTrue(newPanel.recoveredField1358);
         assertEquals(-137, newPanel.recoveredField3012);
         assertEquals(-5.5, newPanel.recoveredField3010, 0.0);
         assertEquals(135, newPanel.x);
         assertEquals(30.0F, gui.recoveredField778, 0.0F);
         assertFalse(CBModulesGui.recoveredField784);
      } finally {
         CheatBreaker.instance = previous;
         CBModulesGui.recoveredField784 = closing;
      }
   }

   public static class TestMinecraft extends Minecraft {
      public TestMinecraft() { super((GameConfiguration)null); }
      @Override public boolean isUnicode() { return false; }
   }

   public static class RecordingGui extends CBModulesGui {
      @Override public void initGui() {
         recoveredField789 = new ArrayList<AbstractScrollableElement>();
         try {
            for (int index = 0; index < 4; index++) recoveredField789.add(allocate(ModuleListElement.class));
         } catch (Exception exception) {
            throw new AssertionError(exception);
         }
         currentScrollableElement = null;
         recoveredField795 = null;
         recoveredField778 = 5.0F;
      }
   }
}
