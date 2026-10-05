package net.minecraft.block;

import io.netty.util.Recycler$WeakOrderQueue;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWallSign extends BlockSign {
   public Recycler$WeakOrderQueue field_0000;
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing$Plane.HORIZONTAL);
   public PacketThreadUtil field_0002;
   public BlockDoubleStoneSlabNew field_0001;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      EnumFacing var3 = var1.getBlockState(var2).getValue(FACING);
      float var4 = 0.28125F;
      float var5 = 0.78125F;
      float var6 = 0.0F;
      float var7 = 1.0F;
      float var8 = 0.125F;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      switch (BlockWallSign$1.field_177331_a[var3.ordinal()]) {
         case 1:
            this.a(var6, var4, 1.0F - var8, var7, var5, 1.0F);
            break;
         case 2:
            this.a(var6, var4, 0.0F, var7, var5, var8);
            break;
         case 3:
            this.a(1.0F - var8, var4, var6, 1.0F, var5, var7);
            break;
         case 4:
            this.a(0.0F, var4, var6, var8, var5, var7);
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      EnumFacing var2 = EnumFacing.getFront(var1);
      if (var2.getAxis() == EnumFacing$Axis.Y) {
         var2 = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, var2);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      EnumFacing var5 = var3.getValue(FACING);
      if (!var1.getBlockState(var2.a(var5.getOpposite())).getBlock().getMaterial().isSolid()) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }

      super.onNeighborBlockChange(var1, var2, var3, var4);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(FACING).getIndex();
   }

   public BlockWallSign() {
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH));
   }
}
