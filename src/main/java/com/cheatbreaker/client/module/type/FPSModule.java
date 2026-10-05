package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Config;
import com.cheatbreaker.client.config.IntegerRangeDefaults;

public class FPSModule extends NumberHudModule {
   public Setting recoveredField1505;

   @Override
   public String method_00167() {
      return this.method_09815(this.recoveredField2752, Minecraft.debugFPS, this.recoveredField2753.method_08912()) ? null : this.method_00164();
   }

   @Override
   public IntegerRangeDefaults method_00168() {
      return new IntegerRangeDefaults(0, 1000, 3000);
   }

   @Override
   public String method_00164() {
      return Minecraft.debugFPS + (this.recoveredField1505.method_08908() ? "/" + Config.getFpsMin() : "");
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
      this.recoveredField1505 = new Setting(this, "Show Minimum FPS").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      super.method_00165();
   }
}
