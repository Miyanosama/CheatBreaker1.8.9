package net.minecraft.enchantment;

import net.minecraft.util.WeightedRandom$Item;
import net.optifine.shaders.uniform.ShaderParameterBool$1;

public class EnchantmentData extends WeightedRandom$Item {
   public ShaderParameterBool$1 field_0001;
   public int enchantmentLevel;
   public Enchantment enchantmentobj;

   public EnchantmentData(Enchantment var1, int var2) {
      super(var1.getWeight());
      this.enchantmentobj = var1;
      this.enchantmentLevel = var2;
   }
}
