package net.minecraft.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import junit.swingui.TestSelector$DoubleClickListener;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterMinecartTnt;
import org.apache.log4j.pattern.RelativeTimePatternConverter$CachedTimestamp;

public class BlockRedstoneWire extends Block {
   public static PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
   public static PropertyEnum<BlockRedstoneWire$EnumAttachPosition> SOUTH = PropertyEnum.create("south", BlockRedstoneWire$EnumAttachPosition.class);
   public ChunkedWriteHandler field_0001;
   public static PropertyEnum<BlockRedstoneWire$EnumAttachPosition> EAST = PropertyEnum.create("east", BlockRedstoneWire$EnumAttachPosition.class);
   public boolean canProvidePower = true;
   public HttpResponseDecoder field_0007;
   public Set<BlockPos> blocksNeedingUpdate = Sets.newHashSet();
   public static PropertyEnum<BlockRedstoneWire$EnumAttachPosition> WEST = PropertyEnum.create("west", BlockRedstoneWire$EnumAttachPosition.class);
   public TestSelector$DoubleClickListener field_0000;
   public ModelAdapterMinecartTnt field_0003;
   public static PropertyEnum<BlockRedstoneWire$EnumAttachPosition> NORTH = PropertyEnum.create("north", BlockRedstoneWire$EnumAttachPosition.class);
   public RelativeTimePatternConverter$CachedTimestamp field_0008;

   public void notifyWireNeighborsOfStateChange(World var1, BlockPos var2) {
      if (var1.getBlockState(var2).getBlock() == this) {
         var1.notifyNeighborsOfStateChange(var2, this);

         for (EnumFacing var6 : EnumFacing.values()) {
            var1.notifyNeighborsOfStateChange(var2.a(var6), this);
         }
      }
   }

   public IBlockState updateSurroundingRedstone(World var1, BlockPos var2, IBlockState var3) {
      var3 = this.calculateCurrentChanges(var1, var2, var2, var3);
      ArrayList var4 = Lists.newArrayList(this.blocksNeedingUpdate);
      this.blocksNeedingUpdate.clear();

      for (BlockPos var6 : var4) {
         var1.notifyNeighborsOfStateChange(var6, this);
      }

      return var3;
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return !this.canProvidePower ? 0 : this.getWeakPower(var1, var2, var3, var4);
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      if (!this.canProvidePower) {
         return 0;
      } else {
         int var5 = var3.getValue(POWER);
         if (var5 == 0) {
            return 0;
         } else if (var4 == EnumFacing.UP) {
            return var5;
         } else {
            EnumSet var6 = EnumSet.noneOf(EnumFacing.class);

            for (EnumFacing var8 : EnumFacing$Plane.HORIZONTAL) {
               if (this.func_176339_d(var1, var2, var8)) {
                  var6.add(var8);
               }
            }

            if (var4.getAxis().isHorizontal() && var6.isEmpty()) {
               return var5;
            } else {
               return var6.contains(var4) && !var6.contains(var4.rotateYCCW()) && !var6.contains(var4.rotateY()) ? var5 : 0;
            }
         }
      }
   }

   public BlockRedstoneWire$EnumAttachPosition getAttachPosition(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      BlockPos var4 = var2.a(var3);
      Block var5 = var1.getBlockState(var2.a(var3)).getBlock();
      if (!canConnectTo(var1.getBlockState(var4), var3) && (var5.isBlockNormalCube() || !canConnectUpwardsTo(var1.getBlockState(var4.down())))) {
         Block var6 = var1.getBlockState(var2.up()).getBlock();
         return !var6.isBlockNormalCube() && var5.isBlockNormalCube() && canConnectUpwardsTo(var1.getBlockState(var4.up()))
            ? BlockRedstoneWire$EnumAttachPosition.UP
            : BlockRedstoneWire$EnumAttachPosition.NONE;
      } else {
         return BlockRedstoneWire$EnumAttachPosition.SIDE;
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, NORTH, EAST, SOUTH, WEST, POWER);
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.redstone;
   }

   public boolean func_176339_d(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      BlockPos var4 = var2.a(var3);
      IBlockState var5 = var1.getBlockState(var4);
      Block var6 = var5.getBlock();
      boolean var7 = var6.isNormalCube();
      boolean var8 = var1.getBlockState(var2.up()).getBlock().isNormalCube();
      return !var8 && var7 && canConnectUpwardsTo(var1, var4.up())
         ? true
         : (
            canConnectTo(var5, var3)
               ? true
               : (var6 == Blocks.powered_repeater && var5.getValue(BlockRedstoneDiode.O) == var3 ? true : !var7 && canConnectUpwardsTo(var1, var4.down()))
         );
   }

   public static boolean canConnectUpwardsTo(IBlockState var0) {
      return canConnectTo(var0, (EnumFacing)null);
   }

   public IBlockState calculateCurrentChanges(World var1, BlockPos var2, BlockPos var3, IBlockState var4) {
      IBlockState var5 = var4;
      int var6 = var4.getValue(POWER);
      int var7 = 0;
      var7 = this.getMaxCurrentStrength(var1, var3, var7);
      this.canProvidePower = false;
      int var8 = var1.isBlockIndirectlyGettingPowered(var2);
      this.canProvidePower = true;
      if (var8 > 0 && var8 > var7 - 1) {
         var7 = var8;
      }

      int var9 = 0;

      for (EnumFacing var11 : EnumFacing$Plane.HORIZONTAL) {
         BlockPos var12 = var2.a(var11);
         boolean var13 = var12.getX() != var3.getX() || var12.getZ() != var3.getZ();
         if (var13) {
            var9 = this.getMaxCurrentStrength(var1, var12, var9);
         }

         if (var1.getBlockState(var12).getBlock().isNormalCube() && !var1.getBlockState(var2.up()).getBlock().isNormalCube()) {
            if (var13 && var2.getY() >= var3.getY()) {
               var9 = this.getMaxCurrentStrength(var1, var12.up(), var9);
            }
         } else if (!var1.getBlockState(var12).getBlock().isNormalCube() && var13 && var2.getY() <= var3.getY()) {
            var9 = this.getMaxCurrentStrength(var1, var12.down(), var9);
         }
      }

      if (var9 > var7) {
         var7 = var9 - 1;
      } else if (var7 > 0) {
         var7--;
      } else {
         var7 = 0;
      }

      if (var8 > var7 - 1) {
         var7 = var8;
      }

      if (var6 != var7) {
         var4 = var4.withProperty(POWER, var7);
         if (var1.getBlockState(var2) == var5) {
            var1.a(var2, var4, 2);
         }

         this.blocksNeedingUpdate.add(var2);

         for (EnumFacing var19 : EnumFacing.values()) {
            this.blocksNeedingUpdate.add(var2.a(var19));
         }
      }

      return var4;
   }

   public static boolean canConnectTo(IBlockState var0, EnumFacing var1) {
      Block var2 = var0.getBlock();
      if (var2 == Blocks.redstone_wire) {
         return true;
      } else if (Blocks.unpowered_repeater.isAssociated(var2)) {
         EnumFacing var3 = var0.getValue(BlockRedstoneRepeater.O);
         return var3 == var1 || var3.getOpposite() == var1;
      } else {
         return var2.canProvidePower() && var1 != null;
      }
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      int var5 = var3.getValue(POWER);
      if (var5 != 0) {
         double var6 = var2.getX() + 0.5 + (var4.nextFloat() - 0.5) * 0.2;
         double var8 = var2.getY() + 0.0625F;
         double var10 = var2.getZ() + 0.5 + (var4.nextFloat() - 0.5) * 0.2;
         float var12 = var5 / 15.0F;
         float var13 = var12 * 0.6F + 0.4F;
         float var14 = Math.max(0.0F, var12 * var12 * 0.7F - 0.5F);
         float var15 = Math.max(0.0F, var12 * var12 * 0.6F - 0.7F);
         var1.spawnParticle(EnumParticleTypes.REDSTONE, var6, var8, var10, var13, var14, var15);
      }
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return World.doesBlockHaveSolidTopSurface(var1, var2.down()) || var1.getBlockState(var2.down()).getBlock() == Blocks.glowstone;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(POWER);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(POWER, var1);
   }

   public BlockRedstoneWire() {
      super(Material.circuits);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(NORTH, BlockRedstoneWire$EnumAttachPosition.NONE)
            .withProperty(EAST, BlockRedstoneWire$EnumAttachPosition.NONE)
            .withProperty(SOUTH, BlockRedstoneWire$EnumAttachPosition.NONE)
            .withProperty(WEST, BlockRedstoneWire$EnumAttachPosition.NONE)
            .withProperty(POWER, 0)
      );
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
      IBlockState var4 = var1.getBlockState(var2);
      return var4.getBlock() != this ? super.colorMultiplier(var1, var2, var3) : this.colorMultiplier(var4.getValue(POWER));
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      var1 = var1.withProperty(WEST, this.getAttachPosition(var2, var3, EnumFacing.WEST));
      var1 = var1.withProperty(EAST, this.getAttachPosition(var2, var3, EnumFacing.EAST));
      var1 = var1.withProperty(NORTH, this.getAttachPosition(var2, var3, EnumFacing.NORTH));
      return var1.withProperty(SOUTH, this.getAttachPosition(var2, var3, EnumFacing.SOUTH));
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public int colorMultiplier(int var1) {
      float var2 = var1 / 15.0F;
      float var3 = var2 * 0.6F + 0.4F;
      if (var1 == 0) {
         var3 = 0.3F;
      }

      float var4 = var2 * var2 * 0.7F - 0.5F;
      float var5 = var2 * var2 * 0.6F - 0.7F;
      if (var4 < 0.0F) {
         var4 = 0.0F;
      }

      if (var5 < 0.0F) {
         var5 = 0.0F;
      }

      int var6 = MathHelper.clamp_int((int)(var3 * 255.0F), 0, 255);
      int var7 = MathHelper.clamp_int((int)(var4 * 255.0F), 0, 255);
      int var8 = MathHelper.clamp_int((int)(var5 * 255.0F), 0, 255);
      return 0xFF000000 | var6 << 16 | var7 << 8 | var8;
   }

   public int getMaxCurrentStrength(World var1, BlockPos var2, int var3) {
      if (var1.getBlockState(var2).getBlock() != this) {
         return var3;
      } else {
         int var4 = var1.getBlockState(var2).getValue(POWER);
         return var4 > var3 ? var4 : var3;
      }
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      super.breakBlock(var1, var2, var3);
      if (!var1.D) {
         for (EnumFacing var7 : EnumFacing.values()) {
            var1.notifyNeighborsOfStateChange(var2.a(var7), this);
         }

         this.updateSurroundingRedstone(var1, var2, var3);

         for (EnumFacing var10 : EnumFacing$Plane.HORIZONTAL) {
            this.notifyWireNeighborsOfStateChange(var1, var2.a(var10));
         }

         for (EnumFacing var11 : EnumFacing$Plane.HORIZONTAL) {
            BlockPos var12 = var2.a(var11);
            if (var1.getBlockState(var12).getBlock().isNormalCube()) {
               this.notifyWireNeighborsOfStateChange(var1, var12.up());
            } else {
               this.notifyWireNeighborsOfStateChange(var1, var12.down());
            }
         }
      }
   }

   @Override
   public boolean canProvidePower() {
      return this.canProvidePower;
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D) {
         this.updateSurroundingRedstone(var1, var2, var3);

         for (EnumFacing var5 : EnumFacing$Plane.VERTICAL) {
            var1.notifyNeighborsOfStateChange(var2.a(var5), this);
         }

         for (EnumFacing var9 : EnumFacing$Plane.HORIZONTAL) {
            this.notifyWireNeighborsOfStateChange(var1, var2.a(var9));
         }

         for (EnumFacing var10 : EnumFacing$Plane.HORIZONTAL) {
            BlockPos var6 = var2.a(var10);
            if (var1.getBlockState(var6).getBlock().isNormalCube()) {
               this.notifyWireNeighborsOfStateChange(var1, var6.up());
            } else {
               this.notifyWireNeighborsOfStateChange(var1, var6.down());
            }
         }
      }
   }

   public static boolean canConnectUpwardsTo(IBlockAccess var0, BlockPos var1) {
      return canConnectUpwardsTo(var0.getBlockState(var1));
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!var1.D) {
         if (this.canPlaceBlockAt(var1, var2)) {
            this.updateSurroundingRedstone(var1, var2, var3);
         } else {
            this.dropBlockAsItem(var1, var2, var3, 0);
            var1.setBlockToAir(var2);
         }
      }
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.redstone;
   }
}
