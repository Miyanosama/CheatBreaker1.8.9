package net.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.CustomColors;

public class CustomColors$1 implements CustomColors.IColorizer {
   @Override
   public boolean isColorConstant() {
      return false;
   }

   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      BiomeGenBase var4 = CustomColors.getColorBiome(var2, var3);
      return CustomColors.access$000() != null && var4 == BiomeGenBase.swampland
         ? CustomColors.access$000().getColor(var4, var3)
         : var4.getGrassColorAtPos(var3);
   }
}
