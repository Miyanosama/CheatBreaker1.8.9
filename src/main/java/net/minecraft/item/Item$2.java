package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockFlower;
import net.minecraft.item.ItemStack;

public class Item$2 implements Function<ItemStack, String> {
   public String apply(ItemStack var1) {
      return BlockFlower.EnumFlowerType.getType(BlockFlower.EnumFlowerColor.YELLOW, var1.getMetadata()).getUnlocalizedName();
   }
}
