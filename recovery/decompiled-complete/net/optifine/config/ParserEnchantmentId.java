package net.optifine.config;

import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;
import net.minecraft.enchantment.Enchantment;
import net.optifine.shaders.config.ShaderLine;

public class ParserEnchantmentId implements IParserInt {
   public TileEntityEnderChestRenderer field_0000;
   public ShaderLine field_0001;

   @Override
   public int parse(String var1, int var2) {
      Enchantment var3 = Enchantment.getEnchantmentByLocation(var1);
      return var3 == null ? var2 : var3.effectId;
   }
}
