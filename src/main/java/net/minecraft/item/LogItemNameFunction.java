package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockPlanks;
import net.minecraft.item.ItemStack;

public class LogItemNameFunction implements Function<ItemStack, String> {
   public String apply(ItemStack var1) {
      return BlockPlanks.EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
