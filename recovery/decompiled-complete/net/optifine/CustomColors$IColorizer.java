package net.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface CustomColors$IColorizer {
   int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3);

   boolean isColorConstant();
}
