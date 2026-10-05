package net.minecraft.dispenser;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;

public interface IBlockSource extends ILocatableSource {
   int getBlockMetadata();

   @Override
   double getY();

   @Override
   double getZ();

   <T extends TileEntity> T getBlockTileEntity();

   @Override
   double getX();

   BlockPos getBlockPos();
}
