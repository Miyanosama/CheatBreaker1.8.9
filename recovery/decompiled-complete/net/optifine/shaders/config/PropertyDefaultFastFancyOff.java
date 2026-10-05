package net.optifine.shaders.config;

import io.netty.handler.codec.http.multipart.MixedAttribute;
import net.minecraft.client.Minecraft$3;
import net.minecraft.entity.monster.EntitySpider$AISpiderAttack;
import net.minecraft.entity.passive.EntityRabbit$AIRaidFarm;
import net.minecraft.src.Config;
import recovered.unidentified.UnidentifiedClass3867;

public class PropertyDefaultFastFancyOff extends Property {
   public UnidentifiedClass3867 field_0006;
   public static String[] PROPERTY_VALUES = new String[]{"default", "fast", "fancy", "off"};
   public static String[] USER_VALUES = new String[]{"Default", "Fast", "Fancy", "OFF"};
   public Minecraft$3 field_0003;
   public EntitySpider$AISpiderAttack field_0001;
   public EntityRabbit$AIRaidFarm field_0004;
   public MixedAttribute field_0005;

   public boolean isDefault() {
      return this.getValue() == 0;
   }

   public PropertyDefaultFastFancyOff(String var1, String var2, int var3) {
      super(var1, PROPERTY_VALUES, var2, USER_VALUES, var3);
   }

   public boolean isFast() {
      return this.getValue() == 1;
   }

   public boolean isFancy() {
      return this.getValue() == 2;
   }

   @Override
   public boolean setPropertyValue(String var1) {
      if (Config.equals(var1, "none")) {
         var1 = "off";
      }

      return super.setPropertyValue(var1);
   }

   public boolean isOff() {
      return this.getValue() == 3;
   }
}
