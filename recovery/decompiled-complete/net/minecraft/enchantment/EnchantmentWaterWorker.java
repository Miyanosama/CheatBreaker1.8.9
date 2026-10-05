package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;
import org.apache.log4j.AsyncAppender;

public class EnchantmentWaterWorker extends Enchantment {
   public AsyncAppender field_0000;

   @Override
   public int getMinEnchantability(int var1) {
      return 1;
   }

   public EnchantmentWaterWorker(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.ARMOR_HEAD);
      this.setName("waterWorker");
   }

   @Override
   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + 40;
   }

   @Override
   public int getMaxLevel() {
      return 1;
   }
}
