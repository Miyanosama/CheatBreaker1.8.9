package net.minecraft.enchantment;

import io.netty.channel.DefaultChannelPromise;
import net.minecraft.block.BlockWallSign;
import net.minecraft.util.ResourceLocation;

public class EnchantmentKnockback extends Enchantment {
   public DefaultChannelPromise field_0000;
   public BlockWallSign field_0001;

   @Override
   public int getMaxLevel() {
      return 2;
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 5 + 20 * (var1 - 1);
   }

   @Override
   public int getMaxEnchantability(int var1) {
      return super.getMinEnchantability(var1) + 50;
   }

   public EnchantmentKnockback(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.WEAPON);
      this.setName("knockback");
   }
}
