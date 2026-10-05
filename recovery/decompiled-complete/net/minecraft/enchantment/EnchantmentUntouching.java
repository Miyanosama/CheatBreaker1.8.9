package net.minecraft.enchantment;

import io.netty.channel.VoidChannelPromise;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler$Result;
import net.minecraft.util.ResourceLocation;

public class EnchantmentUntouching extends Enchantment {
   public VoidChannelPromise field_0000;
   public Profiler$Result field_0001;

   @Override
   public int getMaxLevel() {
      return 1;
   }

   public EnchantmentUntouching(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.DIGGER);
      this.setName("untouching");
   }

   @Override
   public boolean canApply(ItemStack var1) {
      return var1.getItem() == Items.shears ? true : super.canApply(var1);
   }

   @Override
   public int getMaxEnchantability(int var1) {
      return super.getMinEnchantability(var1) + 50;
   }

   @Override
   public boolean canApplyTogether(Enchantment var1) {
      return super.canApplyTogether(var1) && var1.effectId != u.effectId;
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 15;
   }
}
