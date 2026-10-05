package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSourceImpl implements IBlockSource {
   public BlockPos recoveredField2472;
   public World recoveredField2473;

   @Override
   public int getBlockMetadata() {
      IBlockState var1 = this.recoveredField2473.getBlockState(this.recoveredField2472);
      return var1.getBlock().getMetaFromState(var1);
   }

   public BlockSourceImpl(World var1, BlockPos var2) {
      this.recoveredField2473 = var1;
      this.recoveredField2472 = var2;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.recoveredField2472;
   }

   @Override
   public double getX() {
      return this.recoveredField2472.getX() + 0.5;
   }

   @Override
   public double getY() {
      return this.recoveredField2472.getY() + 0.5;
   }

   @Override
   public World getWorld() {
      return this.recoveredField2473;
   }

   @Override
   public <T extends TileEntity> T getBlockTileEntity() {
      return (T)this.recoveredField2473.getTileEntity(this.recoveredField2472);
   }

   @Override
   public double getZ() {
      return this.recoveredField2472.getZ() + 0.5;
   }
}
