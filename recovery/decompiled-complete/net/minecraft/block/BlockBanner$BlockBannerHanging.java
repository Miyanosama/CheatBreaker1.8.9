package net.minecraft.block;

import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage$Start;
import net.optifine.shaders.Programs;
import org.apache.log4j.xml.DOMConfigurator$3;

public class BlockBanner$BlockBannerHanging extends BlockBanner {
   public BinaryWebSocketFrame field_0000;
   public ClippingHelperImpl field_0001;
   public InventoryEffectRenderer field_0004;
   public MapGenVillage$Start field_0002;
   public Programs field_0005;
   public DOMConfigurator$3 field_0003;

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING);
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

   public BlockBanner$BlockBannerHanging() {
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(FACING).getIndex();
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      EnumFacing var3 = var1.getBlockState(var2).getValue(FACING);
      float var4 = 0.0F;
      float var5 = 0.78125F;
      float var6 = 0.0F;
      float var7 = 1.0F;
      float var8 = 0.125F;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      switch (BlockBanner$1.field_180370_a[var3.ordinal()]) {
         case 1:
         default:
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
}
