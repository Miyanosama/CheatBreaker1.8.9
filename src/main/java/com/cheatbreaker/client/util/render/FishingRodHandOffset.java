package com.cheatbreaker.client.util.render;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Vec3;

public class FishingRodHandOffset {
   public static FishingRodHandOffset recoveredField3936 = new FishingRodHandOffset();

   public Vec3 method_21047() {
      double var1 = Minecraft.getMinecraft().gameSettings.fovSetting;
      double var3 = var1 / 110.0;
      return new Vec3(-var3 + var3 / 2.5 - var3 / 8.0 + 0.16, 0.0, 0.4);
   }
}
