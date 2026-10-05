package net.minecraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTrapDoor extends Block {
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static PropertyBool OPEN = PropertyBool.create("open");
   public static PropertyEnum<BlockTrapDoor.DoorHalf> HALF = PropertyEnum.create("half", BlockTrapDoor.DoorHalf.class);

   @Override
   public MovingObjectPosition collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.collisionRayTrace(var1, var2, var3, var4);
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (this.J == Material.iron) {
         return true;
      } else {
         var3 = var3.cycleProperty(OPEN);
         var1.a(var2, var3, 2);
         var1.playAuxSFXAtEntity(var4, var3.getValue(OPEN) ? 1003 : 1006, var2, 0);
         return true;
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= getMetaForFacing(var1.getValue(FACING));
      if (var1.getValue(OPEN)) {
         var2 |= 4;
      }

      if (var1.getValue(HALF) == BlockTrapDoor.DoorHalf.TOP) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public static EnumFacing getFacing(int var0) {
      switch (var0 & 3) {
         case 0:
            return EnumFacing.NORTH;
         case 1:
            return EnumFacing.SOUTH;
         case 2:
            return EnumFacing.WEST;
         case 3:
         default:
            return EnumFacing.EAST;
      }
   }

   public static int getMetaForFacing(EnumFacing var0) {
      switch (var0) {
         case NORTH:
            return 0;
         case SOUTH:
            return 1;
         case WEST:
            return 2;
         case EAST:
         default:
            return 3;
      }
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      IBlockState var9 = this.getDefaultState();
      if (var3.getAxis().isHorizontal()) {
         var9 = var9.withProperty(FACING, var3).withProperty(OPEN, false);
         var9 = var9.withProperty(HALF, var5 > 0.5F ? BlockTrapDoor.DoorHalf.TOP : BlockTrapDoor.DoorHalf.BOTTOM);
      }

      return var9;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D) {
         BlockPos var5 = var2.a(var3.getValue(FACING).getOpposite());
         if (!isValidSupportBlock(var1.getBlockState(var5).getBlock())) {
            var1.setBlockToAir(var2);
            this.dropBlockAsItem(var1, var2, var3, 0);
         } else {
            boolean var6 = var1.isBlockPowered(var2);
            if (var6 || var4.canProvidePower()) {
               boolean var7 = var3.getValue(OPEN);
               if (var7 != var6) {
                  var1.a(var2, var3.withProperty(OPEN, var6), 2);
                  var1.playAuxSFXAtEntity((EntityPlayer)null, var6 ? 1003 : 1006, var2, 0);
               }
            }
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, OPEN, HALF);
   }

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      return !var3.getAxis().isVertical() && isValidSupportBlock(var1.getBlockState(var2.a(var3.getOpposite())).getBlock());
   }

   public static boolean isValidSupportBlock(Block var0) {
      return var0.J.isOpaque() && var0.isFullCube() || var0 == Blocks.glowstone || var0 instanceof BlockSlab || var0 instanceof BlockStairs;
   }

   @Override
   public void setBlockBoundsForItemRender() {
      float var1 = 0.1875F;
      this.a(0.0F, 0.40625F, 0.0F, 1.0F, 0.59375F, 1.0F);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.setBounds(var1.getBlockState(var2));
   }

   public void setBounds(IBlockState var1) {
      if (var1.getBlock() == this) {
         boolean var2 = var1.getValue(HALF) == BlockTrapDoor.DoorHalf.TOP;
         Boolean var3 = var1.getValue(OPEN);
         EnumFacing var4 = var1.getValue(FACING);
         float var5 = 0.1875F;
         if (var2) {
            this.a(0.0F, 0.8125F, 0.0F, 1.0F, 1.0F, 1.0F);
         } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.1875F, 1.0F);
         }

         if (var3) {
            if (var4 == EnumFacing.NORTH) {
               this.a(0.0F, 0.0F, 0.8125F, 1.0F, 1.0F, 1.0F);
            }

            if (var4 == EnumFacing.SOUTH) {
               this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.1875F);
            }

            if (var4 == EnumFacing.WEST) {
               this.a(0.8125F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if (var4 == EnumFacing.EAST) {
               this.a(0.0F, 0.0F, 0.0F, 0.1875F, 1.0F, 1.0F);
            }
         }
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getSelectedBoundingBox(var1, var2);
   }

   public BlockTrapDoor(Material var1) {
      super(var1);
      this.setDefaultState(
         this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false).withProperty(HALF, BlockTrapDoor.DoorHalf.BOTTOM)
      );
      float var2 = 0.5F;
      float var3 = 1.0F;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabRedstone);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState()
         .withProperty(FACING, getFacing(var1))
         .withProperty(OPEN, (var1 & 4) != 0)
         .withProperty(HALF, (var1 & 8) == 0 ? BlockTrapDoor.DoorHalf.BOTTOM : BlockTrapDoor.DoorHalf.TOP);
   }

   @Override
   public boolean isPassable(IBlockAccess var1, BlockPos var2) {
      return !var1.getBlockState(var2).getValue(OPEN);
   }

   public static enum DoorHalf implements IStringSerializable {
      TOP("top"),
      BOTTOM("bottom");
      public String name;

      DoorHalf(String var3) {
         this.name = var3;
      }

      @Override
      public String toString() {
         return this.name;
      }

      @Override
      public String getName() {
         return this.name;
      }
   }
}
