package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDoor extends Block {
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   public static PropertyBool OPEN = PropertyBool.create("open");
   public static PropertyEnum<BlockDoor.EnumHingePosition> HINGE = PropertyEnum.create("hinge", BlockDoor.EnumHingePosition.class);
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public static PropertyEnum<BlockDoor.EnumDoorHalf> HALF = PropertyEnum.create("half", BlockDoor.EnumDoorHalf.class);

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (var3.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
         BlockPos var5 = var2.down();
         IBlockState var6 = var1.getBlockState(var5);
         if (var6.getBlock() != this) {
            var1.setBlockToAir(var2);
         } else if (var4 != this) {
            this.onNeighborBlockChange(var1, var5, var6, var4);
         }
      } else {
         boolean var9 = false;
         BlockPos var10 = var2.up();
         IBlockState var7 = var1.getBlockState(var10);
         if (var7.getBlock() != this) {
            var1.setBlockToAir(var2);
            var9 = true;
         }

         if (!World.doesBlockHaveSolidTopSurface(var1, var2.down())) {
            var1.setBlockToAir(var2);
            var9 = true;
            if (var7.getBlock() == this) {
               var1.setBlockToAir(var10);
            }
         }

         if (var9) {
            if (!var1.D) {
               this.dropBlockAsItem(var1, var2, var3, 0);
            }
         } else {
            boolean var8 = var1.isBlockPowered(var2) || var1.isBlockPowered(var10);
            if ((var8 || var4.canProvidePower()) && var4 != this && var8 != var7.getValue(POWERED)) {
               var1.a(var10, var7.withProperty(POWERED, var8), 2);
               if (var8 != var3.getValue(OPEN)) {
                  var1.a(var2, var3.withProperty(OPEN, var8), 2);
                  var1.markBlockRangeForRenderUpdate(var2, var2);
                  var1.playAuxSFXAtEntity((EntityPlayer)null, var8 ? 1003 : 1006, var2, 0);
               }
            }
         }
      }
   }

   public static boolean isHingeLeft(int var0) {
      return (var0 & 16) != 0;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return (var1 & 8) > 0
         ? this.getDefaultState()
            .withProperty(HALF, BlockDoor.EnumDoorHalf.UPPER)
            .withProperty(HINGE, (var1 & 1) > 0 ? BlockDoor.EnumHingePosition.RIGHT : BlockDoor.EnumHingePosition.LEFT)
            .withProperty(POWERED, (var1 & 2) > 0)
         : this.getDefaultState()
            .withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER)
            .withProperty(FACING, EnumFacing.getHorizontal(var1 & 3).rotateYCCW())
            .withProperty(OPEN, (var1 & 4) > 0);
   }

   public Item getItem() {
      return this == Blocks.iron_door
         ? Items.iron_door
         : (
            this == Blocks.spruce_door
               ? Items.spruce_door
               : (
                  this == Blocks.birch_door
                     ? Items.birch_door
                     : (
                        this == Blocks.jungle_door
                           ? Items.jungle_door
                           : (this == Blocks.acacia_door ? Items.acacia_door : (this == Blocks.dark_oak_door ? Items.dark_oak_door : Items.oak_door))
                     )
               )
         );
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   @Override
   public boolean isPassable(IBlockAccess var1, BlockPos var2) {
      return isOpen(combineMetadata(var1, var2));
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.setBoundBasedOnMeta(combineMetadata(var1, var2));
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal((this.getUnlocalizedName() + ".name").replaceAll("tile", "item"));
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return this.getItem();
   }

   public static EnumFacing getFacing(IBlockAccess var0, BlockPos var1) {
      return getFacing(combineMetadata(var0, var1));
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (this.J == Material.iron) {
         return true;
      } else {
         BlockPos var9 = var3.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? var2 : var2.down();
         IBlockState var10 = var2.equals(var9) ? var3 : var1.getBlockState(var9);
         if (var10.getBlock() != this) {
            return false;
         } else {
            var3 = var10.cycleProperty(OPEN);
            var1.a(var9, var3, 2);
            var1.markBlockRangeForRenderUpdate(var9, var2);
            var1.playAuxSFXAtEntity(var4, var3.getValue(OPEN) ? 1003 : 1006, var2, 0);
            return true;
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, HALF, FACING, OPEN, HINGE, POWERED);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public int getMobilityFlag() {
      return 1;
   }

   @Override
   public MovingObjectPosition collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.collisionRayTrace(var1, var2, var3, var4);
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return var1.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? null : this.getItem();
   }

   public void toggleDoor(World var1, BlockPos var2, boolean var3) {
      IBlockState var4 = var1.getBlockState(var2);
      if (var4.getBlock() == this) {
         BlockPos var5 = var4.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER ? var2 : var2.down();
         IBlockState var6 = var2 == var5 ? var4 : var1.getBlockState(var5);
         if (var6.getBlock() == this && var6.getValue(OPEN) != var3) {
            var1.a(var5, var6.withProperty(OPEN, var3), 2);
            var1.markBlockRangeForRenderUpdate(var5, var2);
            var1.playAuxSFXAtEntity((EntityPlayer)null, var3 ? 1003 : 1006, var2, 0);
         }
      }
   }

   @Override
   public void onBlockHarvested(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
      BlockPos var5 = var2.down();
      if (var4.bA.isCreativeMode && var3.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER && var1.getBlockState(var5).getBlock() == this) {
         var1.setBlockToAir(var5);
      }
   }

   public static boolean isTop(int var0) {
      return (var0 & 8) != 0;
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      if (var1.getValue(HALF) == BlockDoor.EnumDoorHalf.LOWER) {
         IBlockState var4 = var2.getBlockState(var3.up());
         if (var4.getBlock() == this) {
            var1 = var1.withProperty(HINGE, var4.getValue(HINGE)).withProperty(POWERED, var4.getValue(POWERED));
         }
      } else {
         IBlockState var5 = var2.getBlockState(var3.down());
         if (var5.getBlock() == this) {
            var1 = var1.withProperty(FACING, var5.getValue(FACING)).withProperty(OPEN, var5.getValue(OPEN));
         }
      }

      return var1;
   }

   public static boolean isOpen(int var0) {
      return (var0 & 4) != 0;
   }

   public static EnumFacing getFacing(int var0) {
      return EnumFacing.getHorizontal(var0 & 3).rotateYCCW();
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getSelectedBoundingBox(var1, var2);
   }

   public BlockDoor(Material var1) {
      super(var1);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(FACING, EnumFacing.NORTH)
            .withProperty(OPEN, false)
            .withProperty(HINGE, BlockDoor.EnumHingePosition.LEFT)
            .withProperty(POWERED, false)
            .withProperty(HALF, BlockDoor.EnumDoorHalf.LOWER)
      );
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return var2.getY() >= 255
         ? false
         : World.doesBlockHaveSolidTopSurface(var1, var2.down()) && super.canPlaceBlockAt(var1, var2) && super.canPlaceBlockAt(var1, var2.up());
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      if (var1.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER) {
         var2 |= 8;
         if (var1.getValue(HINGE) == BlockDoor.EnumHingePosition.RIGHT) {
            var2 |= 1;
         }

         if (var1.getValue(POWERED)) {
            var2 |= 2;
         }
      } else {
         var2 |= var1.getValue(FACING).rotateY().getHorizontalIndex();
         if (var1.getValue(OPEN)) {
            var2 |= 4;
         }
      }

      return var2;
   }

   public static int removeHalfBit(int var0) {
      return var0 & 7;
   }

   public static int combineMetadata(IBlockAccess var0, BlockPos var1) {
      IBlockState var2 = var0.getBlockState(var1);
      int var3 = var2.getBlock().getMetaFromState(var2);
      boolean var4 = isTop(var3);
      IBlockState var5 = var0.getBlockState(var1.down());
      int var6 = var5.getBlock().getMetaFromState(var5);
      int var7 = var4 ? var6 : var3;
      IBlockState var8 = var0.getBlockState(var1.up());
      int var9 = var8.getBlock().getMetaFromState(var8);
      int var10 = var4 ? var3 : var9;
      boolean var11 = (var10 & 1) != 0;
      boolean var12 = (var10 & 2) != 0;
      return removeHalfBit(var7) | (var4 ? 8 : 0) | (var11 ? 16 : 0) | (var12 ? 32 : 0);
   }

   public void setBoundBasedOnMeta(int var1) {
      float var2 = 0.1875F;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
      EnumFacing var3 = getFacing(var1);
      boolean var4 = isOpen(var1);
      boolean var5 = isHingeLeft(var1);
      if (var4) {
         if (var3 == EnumFacing.EAST) {
            if (!var5) {
               this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            } else {
               this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            }
         } else if (var3 == EnumFacing.SOUTH) {
            if (!var5) {
               this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
               this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            }
         } else if (var3 == EnumFacing.WEST) {
            if (!var5) {
               this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
            } else {
               this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
            }
         } else if (var3 == EnumFacing.NORTH) {
            if (!var5) {
               this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
            } else {
               this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
         }
      } else if (var3 == EnumFacing.EAST) {
         this.a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
      } else if (var3 == EnumFacing.SOUTH) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
      } else if (var3 == EnumFacing.WEST) {
         this.a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      } else if (var3 == EnumFacing.NORTH) {
         this.a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
      }
   }

   public static boolean isOpen(IBlockAccess var0, BlockPos var1) {
      return isOpen(combineMetadata(var0, var1));
   }

   public static enum EnumDoorHalf implements IStringSerializable {
      UPPER,
      LOWER;

      @Override
      public String toString() {
         return this.getName();
      }

      @Override
      public String getName() {
         return this == UPPER ? "upper" : "lower";
      }
   }

   public static enum EnumHingePosition implements IStringSerializable {
      LEFT,
      RIGHT;
      // $VF: synthetic field
      public static BlockDoor.EnumHingePosition[] $VALUES = new BlockDoor.EnumHingePosition[]{
         BlockDoor.EnumHingePosition.LEFT, BlockDoor.EnumHingePosition.RIGHT
      };

      @Override
      public String toString() {
         return this.getName();
      }

      @Override
      public String getName() {
         return this == LEFT ? "left" : "right";
      }
   }
}
