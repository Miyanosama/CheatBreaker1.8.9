package net.minecraft.block;

import io.netty.handler.codec.spdy.DefaultSpdyHeaders;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$1$1;
import net.minecraft.util.IntHashMap;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterSkeleton;

public class BlockBanner$BlockBannerStanding extends BlockBanner {
   public DefaultSpdyHeaders field_0000;
   public IntHashMap field_0001;
   public ModelAdapterSkeleton field_0003;
   public BlockPos$1$1 field_0002;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, ROTATION);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(ROTATION);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.getBlockState(var2.down()).getBlock().getMaterial().isSolid()) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }

      super.onNeighborBlockChange(var1, var2, var3, var4);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(ROTATION, var1);
   }

   public BlockBanner$BlockBannerStanding() {
      this.setDefaultState(this.M.getBaseState().withProperty(ROTATION, 0));
   }
}
