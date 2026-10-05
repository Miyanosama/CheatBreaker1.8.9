package net.minecraft.block;

import javax.vecmath.Vector4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.player.inventory.LocalBlockIntercommunication;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.stats.ObjectiveStat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSourceImpl implements IBlockSource {
   public LocalBlockIntercommunication field_0002;
   public ObjectiveStat field_0004;
   public BlockPos field_0001;
   public World field_0003;
   public Vector4f field_0000;

   @Override
   public int getBlockMetadata() {
      IBlockState var1 = this.field_0003.getBlockState(this.field_0001);
      return var1.getBlock().getMetaFromState(var1);
   }

   public BlockSourceImpl(World var1, BlockPos var2) {
      this.field_0003 = var1;
      this.field_0001 = var2;
   }

   @Override
   public BlockPos getBlockPos() {
      return this.field_0001;
   }

   @Override
   public double getX() {
      return this.field_0001.getX() + 0.5;
   }

   @Override
   public double getY() {
      return this.field_0001.getY() + 0.5;
   }

   @Override
   public World getWorld() {
      return this.field_0003;
   }

   @Override
   public <T extends TileEntity> T getBlockTileEntity() {
      return (T)this.field_0003.getTileEntity(this.field_0001);
   }

   @Override
   public double getZ() {
      return this.field_0001.getZ() + 0.5;
   }
}
