package net.minecraft.block;

import com.cheatbreaker.client.ui.mainmenu.BuildRestrictionsMenu;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.SkinManager$3;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRailBase extends Block {
   public BuildRestrictionsMenu field_0000;
   public SkinManager$3 field_0001;
   public EntityAIAvoidEntity field_0003;
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

   public abstract IProperty<BlockRailBase$EnumRailDirection> getShapeProperty();

   public static boolean isRailBlock(World var0, BlockPos var1) {
      return isRailBlock(var0.getBlockState(var1));
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      BlockRailBase$EnumRailDirection var4 = var3.getBlock() == this ? var3.getValue(this.getShapeProperty()) : null;
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
         BlockRailBase$EnumRailDirection var5 = var3.getValue(this.getShapeProperty());
         boolean var6 = false;
         if (!World.doesBlockHaveSolidTopSurface(var1, var2.down())) {
            var6 = true;
         }

         if (var5 == BlockRailBase$EnumRailDirection.ASCENDING_EAST && !World.doesBlockHaveSolidTopSurface(var1, var2.east())) {
            var6 = true;
         } else if (var5 == BlockRailBase$EnumRailDirection.ASCENDING_WEST && !World.doesBlockHaveSolidTopSurface(var1, var2.west())) {
            var6 = true;
         } else if (var5 == BlockRailBase$EnumRailDirection.ASCENDING_NORTH && !World.doesBlockHaveSolidTopSurface(var1, var2.north())) {
            var6 = true;
         } else if (var5 == BlockRailBase$EnumRailDirection.ASCENDING_SOUTH && !World.doesBlockHaveSolidTopSurface(var1, var2.south())) {
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
      return var1.D ? var3 : new BlockRailBase$Rail(this, var1, var2, var3).func_180364_a(var1.isBlockPowered(var2), var4).getBlockState();
   }

   @Override
   public int getMobilityFlag() {
      return 0;
   }
}
