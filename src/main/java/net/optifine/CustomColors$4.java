package net.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.optifine.CustomColors;

public class CustomColors$4 implements CustomColors.IColorizer {
   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return CustomColors.access$300() != null ? CustomColors.access$300().getColor(var2, var3) : ColorizerFoliage.getFoliageColorBirch();
   }

   @Override
   public boolean isColorConstant() {
      return CustomColors.access$300() == null;
   }
}
