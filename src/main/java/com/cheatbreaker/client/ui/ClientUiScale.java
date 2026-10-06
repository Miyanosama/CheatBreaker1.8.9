package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public final class ClientUiScale {
   private ClientUiScale() {}

   public static int resolve(String choice, int displayWidth, int displayHeight) {
      // The module settings panel is 370 units wide; leave space around it.
      int maximum = Math.max(1, Math.min(displayWidth / 400, displayHeight / 240));
      int requested;
      switch (choice == null ? "Normal" : choice) {
         case "Small": requested = 1; break;
         case "Large": requested = 3; break;
         case "4x": requested = 4; break;
         case "5x": requested = 5; break;
         case "Auto": requested = maximum; break;
         default: requested = 2;
      }
      return Math.min(requested, maximum);
   }

   public static float renderScale(String choice, int displayWidth, int displayHeight, int minecraftScale) {
      return (float)resolve(choice, displayWidth, displayHeight) / minecraftScale;
   }

   public static float getRenderScale(Minecraft minecraft, ScaledResolution resolution) {
      GlobalSettings settings = CheatBreaker.getInstance().getGlobalSettings();
      String choice = settings == null || settings.clientUiScale == null ? "Normal" : (String)settings.clientUiScale.getValue();
      return renderScale(choice, minecraft.displayWidth, minecraft.displayHeight, resolution.getScaleFactor());
   }
}
