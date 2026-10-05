package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockDoublePlant$EnumPlantType;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.tileentity.TileEntity$3;
import net.minecraft.world.gen.layer.GenLayer;

public class Item$7 implements Function<ItemStack, String> {
   public TileEntity$3 field_0001;
   public RenderHelper field_0002;
   public GenLayer field_0000;

   public String apply(ItemStack var1) {
      return BlockDoublePlant$EnumPlantType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
