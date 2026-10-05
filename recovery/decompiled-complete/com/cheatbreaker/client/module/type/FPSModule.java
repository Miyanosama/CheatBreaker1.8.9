package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.src.Config;
import net.minecraft.world.border.WorldBorder;
import recovered.unidentified.UnidentifiedClass4439;

public class FPSModule extends NumberHudModule {
   public ServerScoreboard field_0001;
   public Setting field_0000;
   public WorldBorder field_0002;

   @Override
   public String method_00167() {
      return this.method_09815(this.field_0003, Minecraft.debugFPS, this.field_0004.method_08912()) ? null : this.method_00164();
   }

   @Override
   public UnidentifiedClass4439 method_00168() {
      return new UnidentifiedClass4439(0, 1000, 3000);
   }

   @Override
   public String method_00164() {
      return Minecraft.debugFPS + (this.field_0000.method_08908() ? "/" + Config.getFpsMin() : "");
   }

   @Override
   public String method_00166() {
      return "FPS";
   }

   public FPSModule() {
      super("FPS", "[144 FPS]");
      this.method_28821("Displays your frames per second.");
   }

   @Override
   public void method_00165() {
      this.field_0000 = new Setting(this, "Show Minimum FPS").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      super.method_00165();
   }
}
