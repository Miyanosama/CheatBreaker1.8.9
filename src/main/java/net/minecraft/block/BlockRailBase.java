package net.minecraft.block;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
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

public abstract class BlockRailBase extends Block {
   public boolean isPowered;

   @Override
   public MovingObjectPosition collisionRayTrace(World var1, BlockPos var2, Vec3 var3, Vec3 var4) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.collisionRayTrace(var1, var2, var3, var4);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public static boolean isRailBlock(IBlockState var0) {
      Block var1 = var0.getBlock();
      return var1 == Blocks.rail || var1 == Blocks.golden_rail || var1 == Blocks.detector_rail || var1 == Blocks.activator_rail;
   }

   public abstract IProperty<BlockRailBase.EnumRailDirection> getShapeProperty();

   public static boolean isRailBlock(World var0, BlockPos var1) {
      return isRailBlock(var0.getBlockState(var1));
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      BlockRailBase.EnumRailDirection var4 = var3.getBlock() == this ? var3.getValue(this.getShapeProperty()) : null;
      if (var4 != null && var4.isAscending()) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      } else {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      }
   }

   public void onNeighborChangedInternal(World var1, BlockPos var2, IBlockState var3, Block var4) {
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D) {
         BlockRailBase.EnumRailDirection var5 = var3.getValue(this.getShapeProperty());
         boolean var6 = false;
         if (!World.doesBlockHaveSolidTopSurface(var1, var2.down())) {
            var6 = true;
         }

         if (var5 == BlockRailBase.EnumRailDirection.ASCENDING_EAST && !World.doesBlockHaveSolidTopSurface(var1, var2.east())) {
            var6 = true;
         } else if (var5 == BlockRailBase.EnumRailDirection.ASCENDING_WEST && !World.doesBlockHaveSolidTopSurface(var1, var2.west())) {
            var6 = true;
         } else if (var5 == BlockRailBase.EnumRailDirection.ASCENDING_NORTH && !World.doesBlockHaveSolidTopSurface(var1, var2.north())) {
            var6 = true;
         } else if (var5 == BlockRailBase.EnumRailDirection.ASCENDING_SOUTH && !World.doesBlockHaveSolidTopSurface(var1, var2.south())) {
            var6 = true;
         }

         if (var6) {
            this.dropBlockAsItem(var1, var2, var3, 0);
            var1.setBlockToAir(var2);
         } else {
            this.onNeighborChangedInternal(var1, var2, var3, var4);
         }
      }
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return World.doesBlockHaveSolidTopSurface(var1, var2.down());
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      super.breakBlock(var1, var2, var3);
      if (var3.getValue(this.getShapeProperty()).isAscending()) {
         var1.notifyNeighborsOfStateChange(var2.up(), this);
      }

      if (this.isPowered) {
         var1.notifyNeighborsOfStateChange(var2, this);
         var1.notifyNeighborsOfStateChange(var2.down(), this);
      }
   }

   public BlockRailBase(boolean var1) {
      super(Material.circuits);
      this.isPowered = var1;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
      this.setCreativeTab(CreativeTabs.tabTransport);
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D) {
         var3 = this.a(var1, var2, var3, true);
         if (this.isPowered) {
            this.onNeighborBlockChange(var1, var2, var3, this);
         }
      }
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public IBlockState a(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      return var1.D ? var3 : new BlockRailBase.Rail(var1, var2, var3).func_180364_a(var1.isBlockPowered(var2), var4).getBlockState();
   }

   @Override
   public int getMobilityFlag() {
      return 0;
   }

   public static enum EnumRailDirection implements IStringSerializable {
      NORTH_SOUTH(0, "north_south"),
      EAST_WEST(1, "east_west"),
      ASCENDING_EAST(2, "ascending_east"),
      ASCENDING_WEST(3, "ascending_west"),
      ASCENDING_NORTH(4, "ascending_north"),
      ASCENDING_SOUTH(5, "ascending_south"),
      SOUTH_EAST(6, "south_east"),
      SOUTH_WEST(7, "south_west"),
      NORTH_WEST(8, "north_west"),
      NORTH_EAST(9, "north_east");
      // $VF: synthetic field
      public static BlockRailBase.EnumRailDirection[] $VALUES = new BlockRailBase.EnumRailDirection[]{
         NORTH_SOUTH,
         EAST_WEST,
         BlockRailBase.EnumRailDirection.ASCENDING_EAST,
         BlockRailBase.EnumRailDirection.ASCENDING_WEST,
         BlockRailBase.EnumRailDirection.ASCENDING_NORTH,
         BlockRailBase.EnumRailDirection.ASCENDING_SOUTH,
         BlockRailBase.EnumRailDirection.SOUTH_EAST,
         SOUTH_WEST,
         BlockRailBase.EnumRailDirection.NORTH_WEST,
         NORTH_EAST
      };
      public static BlockRailBase.EnumRailDirection[] META_LOOKUP = new BlockRailBase.EnumRailDirection[values().length];
      public String name;
      public int meta;

      EnumRailDirection(int var3, String var4) {
         this.meta = var3;
         this.name = var4;
      }

      public int getMetadata() {
         return this.meta;
      }

      public boolean isAscending() {
         return this == ASCENDING_NORTH || this == ASCENDING_EAST || this == ASCENDING_SOUTH || this == ASCENDING_WEST;
      }

      public static BlockRailBase.EnumRailDirection byMetadata(int var0) {
         if (var0 < 0 || var0 >= META_LOOKUP.length) {
            var0 = 0;
         }

         return META_LOOKUP[var0];
      }

      @Override
      public String getName() {
         return this.name;
      }

      @Override
      public String toString() {
         return this.name;
      }

      static {
         for (BlockRailBase.EnumRailDirection var3 : values()) {
            META_LOOKUP[var3.getMetadata()] = var3;
         }
      }
   }

   public class Rail {
      public IBlockState state;
      public List<BlockPos> field_150657_g = Lists.newArrayList();
      public BlockPos pos;
      public BlockRailBase block;
      public World world;
      public boolean isPowered;

      public void func_150651_b() {
         for (int var1 = 0; var1 < this.field_150657_g.size(); var1++) {
            BlockRailBase.Rail var2 = this.findRailAt(this.field_150657_g.get(var1));
            if (var2 != null && var2.func_150653_a(this)) {
               this.field_150657_g.set(var1, var2.pos);
            } else {
               this.field_150657_g.remove(var1--);
            }
         }
      }

      public boolean func_180361_d(BlockPos var1) {
         BlockRailBase.Rail var2 = this.findRailAt(var1);
         if (var2 == null) {
            return false;
         } else {
            var2.func_150651_b();
            return var2.func_150649_b(this);
         }
      }

      public boolean func_180363_c(BlockPos var1) {
         for (int var2 = 0; var2 < this.field_150657_g.size(); var2++) {
            BlockPos var3 = this.field_150657_g.get(var2);
            if (var3.getX() == var1.getX() && var3.getZ() == var1.getZ()) {
               return true;
            }
         }

         return false;
      }

      public boolean hasRailAt(BlockPos var1) {
         return BlockRailBase.isRailBlock(this.world, var1)
            || BlockRailBase.isRailBlock(this.world, var1.up())
            || BlockRailBase.isRailBlock(this.world, var1.down());
      }

      public BlockRailBase.Rail func_180364_a(boolean var1, boolean var2) {
         BlockPos var3 = this.pos.north();
         BlockPos var4 = this.pos.south();
         BlockPos var5 = this.pos.west();
         BlockPos var6 = this.pos.east();
         boolean var7 = this.func_180361_d(var3);
         boolean var8 = this.func_180361_d(var4);
         boolean var9 = this.func_180361_d(var5);
         boolean var10 = this.func_180361_d(var6);
         BlockRailBase.EnumRailDirection var11 = null;
         if ((var7 || var8) && !var9 && !var10) {
            var11 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if ((var9 || var10) && !var7 && !var8) {
            var11 = BlockRailBase.EnumRailDirection.EAST_WEST;
         }

         if (!this.isPowered) {
            if (var8 && var10 && !var7 && !var9) {
               var11 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
            }

            if (var8 && var9 && !var7 && !var10) {
               var11 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
            }

            if (var7 && var9 && !var8 && !var10) {
               var11 = BlockRailBase.EnumRailDirection.NORTH_WEST;
            }

            if (var7 && var10 && !var8 && !var9) {
               var11 = BlockRailBase.EnumRailDirection.NORTH_EAST;
            }
         }

         if (var11 == null) {
            if (var7 || var8) {
               var11 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
            }

            if (var9 || var10) {
               var11 = BlockRailBase.EnumRailDirection.EAST_WEST;
            }

            if (!this.isPowered) {
               if (var1) {
                  if (var8 && var10) {
                     var11 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
                  }

                  if (var9 && var8) {
                     var11 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
                  }

                  if (var10 && var7) {
                     var11 = BlockRailBase.EnumRailDirection.NORTH_EAST;
                  }

                  if (var7 && var9) {
                     var11 = BlockRailBase.EnumRailDirection.NORTH_WEST;
                  }
               } else {
                  if (var7 && var9) {
                     var11 = BlockRailBase.EnumRailDirection.NORTH_WEST;
                  }

                  if (var10 && var7) {
                     var11 = BlockRailBase.EnumRailDirection.NORTH_EAST;
                  }

                  if (var9 && var8) {
                     var11 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
                  }

                  if (var8 && var10) {
                     var11 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
                  }
               }
            }
         }

         if (var11 == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if (BlockRailBase.isRailBlock(this.world, var3.up())) {
               var11 = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
            }

            if (BlockRailBase.isRailBlock(this.world, var4.up())) {
               var11 = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
            }
         }

         if (var11 == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if (BlockRailBase.isRailBlock(this.world, var6.up())) {
               var11 = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
            }

            if (BlockRailBase.isRailBlock(this.world, var5.up())) {
               var11 = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
            }
         }

         if (var11 == null) {
            var11 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         this.func_180360_a(var11);
         this.state = this.state.withProperty(this.block.getShapeProperty(), var11);
         if (var2 || this.world.getBlockState(this.pos) != this.state) {
            this.world.a(this.pos, this.state, 3);

            for (int var12 = 0; var12 < this.field_150657_g.size(); var12++) {
               BlockRailBase.Rail var13 = this.findRailAt(this.field_150657_g.get(var12));
               if (var13 != null) {
                  var13.func_150651_b();
                  if (var13.func_150649_b(this)) {
                     var13.func_150645_c(this);
                  }
               }
            }
         }

         return this;
      }

      public BlockRailBase.Rail findRailAt(BlockPos var1) {
         IBlockState var2 = this.world.getBlockState(var1);
         if (BlockRailBase.isRailBlock(var2)) {
            return BlockRailBase.this.new Rail(this.world, var1, var2);
         } else {
            BlockPos var3 = var1.up();
            var2 = this.world.getBlockState(var3);
            if (BlockRailBase.isRailBlock(var2)) {
               return BlockRailBase.this.new Rail(this.world, var3, var2);
            } else {
               var3 = var1.down();
               var2 = this.world.getBlockState(var3);
               return BlockRailBase.isRailBlock(var2) ? BlockRailBase.this.new Rail(this.world, var3, var2) : null;
            }
         }
      }

      public int countAdjacentRails() {
         int var1 = 0;

         for (EnumFacing var3 : EnumFacing.Plane.HORIZONTAL) {
            if (this.hasRailAt(this.pos.a(var3))) {
               var1++;
            }
         }

         return var1;
      }

      public boolean func_150649_b(BlockRailBase.Rail var1) {
         return this.func_150653_a(var1) || this.field_150657_g.size() != 2;
      }

      public boolean func_150653_a(BlockRailBase.Rail var1) {
         return this.func_180363_c(var1.pos);
      }

      public void func_150645_c(BlockRailBase.Rail var1) {
         this.field_150657_g.add(var1.pos);
         BlockPos var2 = this.pos.north();
         BlockPos var3 = this.pos.south();
         BlockPos var4 = this.pos.west();
         BlockPos var5 = this.pos.east();
         boolean var6 = this.func_180363_c(var2);
         boolean var7 = this.func_180363_c(var3);
         boolean var8 = this.func_180363_c(var4);
         boolean var9 = this.func_180363_c(var5);
         BlockRailBase.EnumRailDirection var10 = null;
         if (var6 || var7) {
            var10 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         if (var8 || var9) {
            var10 = BlockRailBase.EnumRailDirection.EAST_WEST;
         }

         if (!this.isPowered) {
            if (var7 && var9 && !var6 && !var8) {
               var10 = BlockRailBase.EnumRailDirection.SOUTH_EAST;
            }

            if (var7 && var8 && !var6 && !var9) {
               var10 = BlockRailBase.EnumRailDirection.SOUTH_WEST;
            }

            if (var6 && var8 && !var7 && !var9) {
               var10 = BlockRailBase.EnumRailDirection.NORTH_WEST;
            }

            if (var6 && var9 && !var7 && !var8) {
               var10 = BlockRailBase.EnumRailDirection.NORTH_EAST;
            }
         }

         if (var10 == BlockRailBase.EnumRailDirection.NORTH_SOUTH) {
            if (BlockRailBase.isRailBlock(this.world, var2.up())) {
               var10 = BlockRailBase.EnumRailDirection.ASCENDING_NORTH;
            }

            if (BlockRailBase.isRailBlock(this.world, var3.up())) {
               var10 = BlockRailBase.EnumRailDirection.ASCENDING_SOUTH;
            }
         }

         if (var10 == BlockRailBase.EnumRailDirection.EAST_WEST) {
            if (BlockRailBase.isRailBlock(this.world, var5.up())) {
               var10 = BlockRailBase.EnumRailDirection.ASCENDING_EAST;
            }

            if (BlockRailBase.isRailBlock(this.world, var4.up())) {
               var10 = BlockRailBase.EnumRailDirection.ASCENDING_WEST;
            }
         }

         if (var10 == null) {
            var10 = BlockRailBase.EnumRailDirection.NORTH_SOUTH;
         }

         this.state = this.state.withProperty(this.block.getShapeProperty(), var10);
         this.world.a(this.pos, this.state, 3);
      }

      public Rail(World var2, BlockPos var3, IBlockState var4) {
         this.world = var2;
         this.pos = var3;
         this.state = var4;
         this.block = (BlockRailBase)var4.getBlock();
         BlockRailBase.EnumRailDirection var5 = var4.getValue(BlockRailBase.this.getShapeProperty());
         this.isPowered = this.block.isPowered;
         this.func_180360_a(var5);
      }

      public IBlockState getBlockState() {
         return this.state;
      }

      public void func_180360_a(BlockRailBase.EnumRailDirection var1) {
         this.field_150657_g.clear();
         switch (var1) {
            case NORTH_SOUTH:
               this.field_150657_g.add(this.pos.north());
               this.field_150657_g.add(this.pos.south());
               break;
            case EAST_WEST:
               this.field_150657_g.add(this.pos.west());
               this.field_150657_g.add(this.pos.east());
               break;
            case ASCENDING_EAST:
               this.field_150657_g.add(this.pos.west());
               this.field_150657_g.add(this.pos.east().up());
               break;
            case ASCENDING_WEST:
               this.field_150657_g.add(this.pos.west().up());
               this.field_150657_g.add(this.pos.east());
               break;
            case ASCENDING_NORTH:
               this.field_150657_g.add(this.pos.north().up());
               this.field_150657_g.add(this.pos.south());
               break;
            case ASCENDING_SOUTH:
               this.field_150657_g.add(this.pos.north());
               this.field_150657_g.add(this.pos.south().up());
               break;
            case SOUTH_EAST:
               this.field_150657_g.add(this.pos.east());
               this.field_150657_g.add(this.pos.south());
               break;
            case SOUTH_WEST:
               this.field_150657_g.add(this.pos.west());
               this.field_150657_g.add(this.pos.south());
               break;
            case NORTH_WEST:
               this.field_150657_g.add(this.pos.west());
               this.field_150657_g.add(this.pos.north());
               break;
            case NORTH_EAST:
               this.field_150657_g.add(this.pos.east());
               this.field_150657_g.add(this.pos.north());
         }
      }
   }
}
