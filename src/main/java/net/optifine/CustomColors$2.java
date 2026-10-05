package net.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.CustomColors;

public class CustomColors$2 implements CustomColors.IColorizer {
   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      BiomeGenBase var4 = CustomColors.getColorBiome(var2, var3);
      return CustomColors.access$100() != null && var4 == BiomeGenBase.swampland
         ? CustomColors.access$100().getColor(var4, var3)
         : var4.getFoliageColorAtPos(var3);
   }

   @Override
   public boolean isColorConstant() {
      return false;
   }
}
