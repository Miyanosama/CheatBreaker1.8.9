package net.optifine.config;

import net.minecraft.world.World;

public enum Weather {
      CLEAR,
      RAIN,
      THUNDER;

   public static Weather[] $VALUES = new Weather[]{CLEAR, RAIN, THUNDER};

   public static Weather getWeather(World var0, float var1) {
      float var2 = var0.h(var1);
      if (var2 > 0.5F) {
         return THUNDER;
      } else {
         float var3 = var0.j(var1);
         return var3 > 0.5F ? RAIN : CLEAR;
      }
   }
}
