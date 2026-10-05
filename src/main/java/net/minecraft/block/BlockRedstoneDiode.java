package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRedstoneDiode extends BlockDirectional {
   public boolean isRepeaterPowered;

   public abstract IBlockState getPoweredState(IBlockState var1);

   public boolean isFacingTowardsRepeater(World var1, BlockPos var2, IBlockState var3) {
      EnumFacing var4 = var3.getValue(O).getOpposite();
      BlockPos var5 = var2.a(var4);
      return isRedstoneRepeaterBlockID(var1.getBlockState(var5).getBlock()) ? var1.getBlockState(var5).getValue(O) != var4 : false;
   }

   public abstract IBlockState getUnpoweredState(IBlockState var1);

   public boolean isPowered(IBlockState var1) {
      return this.isRepeaterPowered;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!this.isLocked(var1, var2, var3)) {
         boolean var5 = this.shouldBePowered(var1, var2, var3);
         if (this.isRepeaterPowered && !var5) {
            var1.a(var2, this.getUnpoweredState(var3), 2);
         } else if (!this.isRepeaterPowered) {
            var1.a(var2, this.getPoweredState(var3), 2);
            if (!var5) {
               var1.updateBlockTick(var2, this.getPoweredState(var3).getBlock(), this.getTickDelay(var3), -1);
            }
         }
      }
   }

   public int getPowerOnSides(IBlockAccess var1, BlockPos var2, IBlockState var3) {
      EnumFacing var4 = var3.getValue(O);
      EnumFacing var5 = var4.rotateY();
      EnumFacing var6 = var4.rotateYCCW();
      return Math.max(this.getPowerOnSide(var1, var2.a(var5), var5), this.getPowerOnSide(var1, var2.a(var6), var6));
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (this.method_12748(var1, var2)) {
         this.updateState(var1, var2, var3);
      } else {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);

         for (EnumFacing var8 : EnumFacing.values()) {
            var1.notifyNeighborsOfStateChange(var2.a(var8), this);
         }
      }
   }

   public void notifyNeighbors(World var1, BlockPos var2, IBlockState var3) {
      EnumFacing var4 = var3.getValue(O);
      BlockPos var5 = var2.a(var4.getOpposite());
      var1.notifyBlockOfStateChange(var5, this);
      var1.notifyNeighborsOfStateExcept(var5, this, var4);
   }

   public int calculateInputStrength(World var1, BlockPos var2, IBlockState var3) {
      EnumFacing var4 = var3.getValue(O);
      BlockPos var5 = var2.a(var4);
      int var6 = var1.getRedstonePower(var5, var4);
      if (var6 >= 15) {
         return var6;
      } else {
         IBlockState var7 = var1.getBlockState(var5);
         return Math.max(var6, var7.getBlock() == Blocks.redstone_wire ? var7.getValue(BlockRedstoneWire.POWER) : 0);
      }
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      this.notifyNeighbors(var1, var2, var3);
   }

   public int getTickDelay(IBlockState var1) {
      return this.getDelay(var1);
   }

   public abstract int getDelay(IBlockState var1);

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return World.doesBlockHaveSolidTopSurface(var1, var2.down()) ? super.canPlaceBlockAt(var1, var2) : false;
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return this.getWeakPower(var1, var2, var3, var4);
   }

   public boolean method_12748(World var1, BlockPos var2) {
      return World.doesBlockHaveSolidTopSurface(var1, var2.down());
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      if (this.shouldBePowered(var1, var2, var3)) {
         var1.scheduleUpdate(var2, this, 1);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean isAssociatedBlock(Block var1) {
      return this.isAssociated(var1);
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      return this.getDefaultState().withProperty(O, var8.getHorizontalFacing().getOpposite());
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return !this.isPowered(var3) ? 0 : (var3.getValue(O) == var4 ? this.getActiveSignal(var1, var2, var3) : 0);
   }

   public int getPowerOnSide(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      IBlockState var4 = var1.getBlockState(var2);
      Block var5 = var4.getBlock();
      return this.canPowerSide(var5) ? (var5 == Blocks.redstone_wire ? var4.getValue(BlockRedstoneWire.POWER) : var1.getStrongPower(var2, var3)) : 0;
   }

   public boolean isLocked(IBlockAccess var1, BlockPos var2, IBlockState var3) {
      return false;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return var3.getAxis() != EnumFacing.Axis.Y;
   }

   public static boolean isRedstoneRepeaterBlockID(Block var0) {
      return Blocks.unpowered_repeater.isAssociated(var0) || Blocks.unpowered_comparator.isAssociated(var0);
   }

   public void updateState(World var1, BlockPos var2, IBlockState var3) {
      if (!this.isLocked(var1, var2, var3)) {
         boolean var4 = this.shouldBePowered(var1, var2, var3);
         if ((this.isRepeaterPowered && !var4 || !this.isRepeaterPowered && var4) && !var1.isBlockTickPending(var2, this)) {
            byte var5 = -1;
            if (this.isFacingTowardsRepeater(var1, var2, var3)) {
               var5 = -3;
            } else if (this.isRepeaterPowered) {
               var5 = -2;
            }

            var1.updateBlockTick(var2, this, this.getDelay(var3), var5);
         }
      }
   }

   public int getActiveSignal(IBlockAccess var1, BlockPos var2, IBlockState var3) {
      return 15;
   }

   public boolean canPowerSide(Block var1) {
      return var1.canProvidePower();
   }

   @Override
   public void onBlockDestroyedByPlayer(World var1, BlockPos var2, IBlockState var3) {
      if (this.isRepeaterPowered) {
         for (EnumFacing var7 : EnumFacing.values()) {
            var1.notifyNeighborsOfStateChange(var2.a(var7), this);
         }
      }

      super.onBlockDestroyedByPlayer(var1, var2, var3);
   }

   public BlockRedstoneDiode(boolean var1) {
      super(Material.circuits);
      this.isRepeaterPowered = var1;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
   }

   public boolean shouldBePowered(World var1, BlockPos var2, IBlockState var3) {
      return this.calculateInputStrength(var1, var2, var3) > 0;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public boolean isAssociated(Block var1) {
      return var1 == this.getPoweredState(this.getDefaultState()).getBlock() || var1 == this.getUnpoweredState(this.getDefaultState()).getBlock();
   }

   @Override
   public boolean isFullCube() {
      return false;
   }
}
