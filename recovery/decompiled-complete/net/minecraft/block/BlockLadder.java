package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.server.CommandSetBlock;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass4541;

public class BlockLadder extends Block {
   public CommandSetBlock field_0000;
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing$Plane.HORIZONTAL);

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getSelectedBoundingBox(var1, var2);
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return var1.getBlockState(var2.west()).getBlock().isNormalCube()
         ? true
         : (
            var1.getBlockState(var2.east()).getBlock().isNormalCube()
               ? true
               : (var1.getBlockState(var2.north()).getBlock().isNormalCube() ? true : var1.getBlockState(var2.south()).getBlock().isNormalCube())
         );
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
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      if (var3.getBlock() == this) {
         float var4 = 0.125F;
         switch (UnidentifiedClass4541.field_0003[var3.getValue(FACING).ordinal()]) {
            case 1:
               this.a(0.0F, 0.0F, 1.0F - var4, 1.0F, 1.0F, 1.0F);
               break;
            case 2:
               this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var4);
               break;
            case 3:
               this.a(1.0F - var4, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case 4:
            default:
               this.a(0.0F, 0.0F, 0.0F, var4, 1.0F, 1.0F);
         }
      }
   }

   public BlockLadder() {
      super(Material.circuits);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public boolean canBlockStay(World var1, BlockPos var2, EnumFacing var3) {
      return var1.getBlockState(var2.a(var3.getOpposite())).getBlock().isNormalCube();
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      EnumFacing var5 = var3.getValue(FACING);
      if (!this.canBlockStay(var1, var2, var5)) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }

      super.onNeighborBlockChange(var1, var2, var3, var4);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(FACING).getIndex();
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      if (var3.getAxis().isHorizontal() && this.canBlockStay(var1, var2, var3)) {
         return this.getDefaultState().withProperty(FACING, var3);
      } else {
         for (EnumFacing var10 : EnumFacing$Plane.HORIZONTAL) {
            if (this.canBlockStay(var1, var2, var10)) {
               return this.getDefaultState().withProperty(FACING, var10);
            }
         }

         return this.getDefaultState();
      }
   }
}
