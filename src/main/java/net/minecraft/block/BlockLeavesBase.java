package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class BlockLeavesBase extends Block {
   public boolean R;

   public BlockLeavesBase(Material var1, boolean var2) {
      super(var1);
      this.R = var2;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return !this.R && var1.getBlockState(var2).getBlock() == this ? false : super.shouldSideBeRendered(var1, var2, var3);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
