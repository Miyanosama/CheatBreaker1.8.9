package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;

public class DoublePlantItem extends ItemMultiTexture {
   public DoublePlantItem(Block var1, Block var2, Function<ItemStack, String> var3) {
      super(var1, var2, var3);
   }

   @Override
   public int getColorFromItemStack(ItemStack var1, int var2) {
      BlockDoublePlant.EnumPlantType var3 = BlockDoublePlant.EnumPlantType.byMetadata(var1.getMetadata());
      return var3 != BlockDoublePlant.EnumPlantType.GRASS && var3 != BlockDoublePlant.EnumPlantType.FERN
         ? super.getColorFromItemStack(var1, var2)
         : ColorizerGrass.getGrassColor(0.5, 1.0);
   }
}
