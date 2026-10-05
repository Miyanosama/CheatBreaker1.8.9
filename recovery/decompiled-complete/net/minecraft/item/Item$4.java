package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockSilverfish$EnumType;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.world.gen.layer.GenLayerHills;

public class Item$4 implements Function<ItemStack, String> {
   public GenLayerHills field_0000;
   public PropertyDirection field_0001;

   public String apply(ItemStack var1) {
      return BlockSilverfish$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
