package net.minecraft.enchantment;

import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.util.ResourceLocation;

public class EnchantmentArrowInfinite extends Enchantment {
   public EntityAIRestrictSun field_0000;

   @Override
   public int getMaxEnchantability(int var1) {
      return 50;
   }

   public EnchantmentArrowInfinite(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.BOW);
      this.setName("arrowInfinite");
   }

   @Override
   public int getMaxLevel() {
      return 1;
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 20;
   }
}
