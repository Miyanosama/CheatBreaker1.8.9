package net.optifine.config;

import net.minecraft.entity.passive.EntityRabbit$AIRaidFarm;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.optifine.expr.Parameters;
import org.apache.log4j.varia.StringMatchFilter;

public enum Weather {
   THUNDER,
   RAIN,
   CLEAR;

   public EntityRabbit$AIRaidFarm field_0003;
   public NextTickListEntry field_0001;
   public StringMatchFilter field_0007;
   public Parameters field_0004;

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
