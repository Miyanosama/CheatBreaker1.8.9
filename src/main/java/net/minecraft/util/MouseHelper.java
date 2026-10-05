package net.minecraft.util;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class MouseHelper {
   public int recoveredField1619;
   public int recoveredField1620;

   public void mouseXYChange() {
      int lwjglX = Mouse.getDX();
      int lwjglY = Mouse.getDY();
      CheatBreaker client = CheatBreaker.getInstance();
      GlobalSettings settings = client == null ? null : client.getGlobalSettings();
      if (settings != null && settings.rawMouseInput != null && settings.rawMouseInput.method_08908()
         && Mouse.isGrabbed() && Display.isActive() && WindowsRawMouseInput.poll(this)) {
         return;
      }
      WindowsRawMouseInput.reset();
      this.recoveredField1619 = lwjglX;
      this.recoveredField1620 = lwjglY;
   }

   public void ungrabMouseCursor() {
      Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
      Mouse.setGrabbed(false);
      WindowsRawMouseInput.reset();
   }

   public void grabMouseCursor() {
      Mouse.setGrabbed(true);
      WindowsRawMouseInput.reset();
      this.recoveredField1619 = 0;
      this.recoveredField1620 = 0;
   }
}
