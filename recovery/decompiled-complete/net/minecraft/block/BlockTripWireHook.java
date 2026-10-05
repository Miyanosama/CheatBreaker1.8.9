package net.minecraft.block;

import com.google.common.base.Objects;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.log4j.xml.SAXErrorHandler;

public class BlockTripWireHook extends Block {
   public static PropertyBool ATTACHED = PropertyBool.create("attached");
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public SAXErrorHandler field_0000;
   public static PropertyBool SUSPENDED = PropertyBool.create("suspended");
   public static PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing$Plane.HORIZONTAL);

   public boolean checkForDrop(World var1, BlockPos var2, IBlockState var3) {
      if (!this.canPlaceBlockAt(var1, var2)) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
         return false;
      } else {
         return true;
      }
   }

   public BlockTripWireHook() {
      super(Material.circuits);
      this.setDefaultState(
         this.M.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(POWERED, false).withProperty(ATTACHED, false).withProperty(SUSPENDED, false)
      );
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setTickRandomly(true);
   }

   @Override
   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public void func_180694_a(World var1, BlockPos var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      if (var4 && !var6) {
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.1, var2.getZ() + 0.5, "random.click", 0.4F, 0.6F);
      } else if (!var4 && var6) {
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.1, var2.getZ() + 0.5, "random.click", 0.4F, 0.5F);
      } else if (var3 && !var5) {
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.1, var2.getZ() + 0.5, "random.click", 0.4F, 0.7F);
      } else if (!var3 && var5) {
         var1.playSoundEffect(var2.getX() + 0.5, var2.getY() + 0.1, var2.getZ() + 0.5, "random.bowhit", 0.4F, 1.2F / (var1.s.nextFloat() * 0.2F + 0.9F));
      }
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return var1.withProperty(SUSPENDED, !World.doesBlockHaveSolidTopSurface(var2, var3.down()));
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      IBlockState var9 = this.getDefaultState().withProperty(POWERED, false).withProperty(ATTACHED, false).withProperty(SUSPENDED, false);
      if (var3.getAxis().isHorizontal()) {
         var9 = var9.withProperty(FACING, var3);
      }

      return var9;
   }

   @Override
   public boolean canPlaceBlockOnSide(World var1, BlockPos var2, EnumFacing var3) {
      return var3.getAxis().isHorizontal() && var1.getBlockState(var2.a(var3.getOpposite())).getBlock().isNormalCube();
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return var3.getValue(POWERED) ? 15 : 0;
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      boolean var4 = var3.getValue(ATTACHED);
      boolean var5 = var3.getValue(POWERED);
      if (var4 || var5) {
         this.func_176260_a(var1, var2, var3, true, false, -1, (IBlockState)null);
      }

      if (var5) {
         var1.notifyNeighborsOfStateChange(var2, this);
         var1.notifyNeighborsOfStateChange(var2.a(var3.getValue(FACING).getOpposite()), this);
      }

      super.breakBlock(var1, var2, var3);
   }

   public void func_176260_a(World var1, BlockPos var2, IBlockState var3, boolean var4, boolean var5, int var6, IBlockState var7) {
      EnumFacing var8 = var3.getValue(FACING);
      boolean var9 = var3.getValue(ATTACHED);
      boolean var10 = var3.getValue(POWERED);
      boolean var11 = !World.doesBlockHaveSolidTopSurface(var1, var2.down());
      boolean var12 = !var4;
      boolean var13 = false;
      int var14 = 0;
      IBlockState[] var15 = new IBlockState[42];

      for (int var16 = 1; var16 < 42; var16++) {
         BlockPos var17 = var2.a(var8, var16);
         IBlockState var18 = var1.getBlockState(var17);
         if (var18.getBlock() == Blocks.tripwire_hook) {
            if (var18.getValue(FACING) == var8.getOpposite()) {
               var14 = var16;
            }
            break;
         }

         if (var18.getBlock() != Blocks.tripwire && var16 != var6) {
            var15[var16] = null;
            var12 = false;
         } else {
            if (var16 == var6) {
               var18 = (IBlockState)Objects.firstNonNull(var7, var18);
            }

            boolean var19 = !var18.getValue(BlockTripWire.DISARMED);
            boolean var20 = var18.getValue(BlockTripWire.POWERED);
            boolean var21 = var18.getValue(BlockTripWire.SUSPENDED);
            var12 &= var21 == var11;
            var13 |= var19 && var20;
            var15[var16] = var18;
            if (var16 == var6) {
               var1.scheduleUpdate(var2, this, this.tickRate(var1));
               var12 &= var19;
            }
         }
      }

      var12 &= var14 > 1;
      var13 &= var12;
      IBlockState var24 = this.getDefaultState().withProperty(ATTACHED, var12).withProperty(POWERED, var13);
      if (var14 > 0) {
         BlockPos var25 = var2.a(var8, var14);
         EnumFacing var27 = var8.getOpposite();
         var1.a(var25, var24.withProperty(FACING, var27), 3);
         this.func_176262_b(var1, var25, var27);
         this.func_180694_a(var1, var25, var12, var13, var9, var10);
      }

      this.func_180694_a(var1, var2, var12, var13, var9, var10);
      if (!var4) {
         var1.a(var2, var24.withProperty(FACING, var8), 3);
         if (var5) {
            this.func_176262_b(var1, var2, var8);
         }
      }

      if (var9 != var12) {
         for (int var26 = 1; var26 < var14; var26++) {
            BlockPos var28 = var2.a(var8, var26);
            IBlockState var29 = var15[var26];
            if (var29 != null && var1.getBlockState(var28).getBlock() != Blocks.air) {
               var1.a(var28, var29.withProperty(ATTACHED, var12), 3);
            }
         }
      }
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return !var3.getValue(POWERED) ? 0 : (var3.getValue(FACING) == var4 ? 15 : 0);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      float var3 = 0.1875F;
      switch (BlockTripWireHook$1.field_177056_a[var1.getBlockState(var2).getValue(FACING).ordinal()]) {
         case 1:
            this.a(0.0F, 0.2F, 0.5F - var3, var3 * 2.0F, 0.8F, 0.5F + var3);
            break;
         case 2:
            this.a(1.0F - var3 * 2.0F, 0.2F, 0.5F - var3, 1.0F, 0.8F, 0.5F + var3);
            break;
         case 3:
            this.a(0.5F - var3, 0.2F, 0.0F, 0.5F + var3, 0.8F, var3 * 2.0F);
            break;
         case 4:
            this.a(0.5F - var3, 0.2F, 1.0F - var3 * 2.0F, 0.5F + var3, 0.8F, 1.0F);
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (var4 != this && this.checkForDrop(var1, var2, var3)) {
         EnumFacing var5 = var3.getValue(FACING);
         if (!var1.getBlockState(var2.a(var5.getOpposite())).getBlock().isNormalCube()) {
            this.dropBlockAsItem(var1, var2, var3, 0);
            var1.setBlockToAir(var2);
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, POWERED, ATTACHED, SUSPENDED);
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getHorizontalIndex();
      if (var1.getValue(POWERED)) {
         var2 |= 8;
      }

      if (var1.getValue(ATTACHED)) {
         var2 |= 4;
      }

      return var2;
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      this.func_176260_a(var1, var2, var3, false, false, -1, (IBlockState)null);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      this.func_176260_a(var1, var2, var3, false, true, -1, (IBlockState)null);
   }

   public void func_176262_b(World var1, BlockPos var2, EnumFacing var3) {
      var1.notifyNeighborsOfStateChange(var2, this);
      var1.notifyNeighborsOfStateChange(var2.a(var3.getOpposite()), this);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState()
         .withProperty(FACING, EnumFacing.getHorizontal(var1 & 3))
         .withProperty(POWERED, (var1 & 8) > 0)
         .withProperty(ATTACHED, (var1 & 4) > 0);
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      for (EnumFacing var4 : EnumFacing$Plane.HORIZONTAL) {
         if (var1.getBlockState(var2.a(var4)).getBlock().isNormalCube()) {
            return true;
         }
      }

      return false;
   }
}
