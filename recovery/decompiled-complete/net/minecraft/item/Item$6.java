package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockWall$EnumType;
import net.minecraft.entity.Entity$2;

public class Item$6 implements Function<ItemStack, String> {
   public ItemCloth field_0000;
   public Entity$2 field_0001;

   public String apply(ItemStack var1) {
      return BlockWall$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
