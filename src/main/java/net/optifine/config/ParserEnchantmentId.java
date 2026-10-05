package net.optifine.config;

import net.minecraft.enchantment.Enchantment;

public class ParserEnchantmentId implements IParserInt {
   @Override
   public int parse(String var1, int var2) {
      Enchantment var3 = Enchantment.getEnchantmentByLocation(var1);
      return var3 == null ? var2 : var3.effectId;
   }
}
