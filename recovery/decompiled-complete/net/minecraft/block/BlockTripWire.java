package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.log4j.RollingCalendar;

public class BlockTripWire extends Block {
   public RollingCalendar field_0003;
   public static PropertyBool EAST = PropertyBool.create("east");
   public static PropertyBool WEST = PropertyBool.create("west");
   public static PropertyBool SOUTH = PropertyBool.create("south");
   public static PropertyBool DISARMED = PropertyBool.create("disarmed");
   public static PropertyBool ATTACHED = PropertyBool.create("attached");
   public static PropertyBool SUSPENDED = PropertyBool.create("suspended");
   public static PropertyBool POWERED = PropertyBool.create("powered");
   public static PropertyBool NORTH = PropertyBool.create("north");

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.string;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      byte var2 = 0;
      if (var1.getValue(POWERED)) {
         var2 |= 1;
      }

      if (var1.getValue(SUSPENDED)) {
         var2 |= 2;
      }

      if (var1.getValue(ATTACHED)) {
         var2 |= 4;
      }

      if (var1.getValue(DISARMED)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, POWERED, SUSPENDED, ATTACHED, DISARMED, NORTH, EAST, WEST, SOUTH);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      boolean var4 = var3.getValue(ATTACHED);
      boolean var5 = var3.getValue(SUSPENDED);
      if (!var5) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.09375F, 1.0F);
      } else if (!var4) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      } else {
         this.a(0.0F, 0.0625F, 0.0F, 1.0F, 0.15625F, 1.0F);
      }
   }

   public static boolean isConnectedTo(IBlockAccess var0, BlockPos var1, IBlockState var2, EnumFacing var3) {
      BlockPos var4 = var1.a(var3);
      IBlockState var5 = var0.getBlockState(var4);
      Block var6 = var5.getBlock();
      if (var6 == Blocks.tripwire_hook) {
         EnumFacing var9 = var3.getOpposite();
         return var5.getValue(BlockTripWireHook.FACING) == var9;
      } else if (var6 == Blocks.tripwire) {
         boolean var7 = var2.getValue(SUSPENDED);
         boolean var8 = var5.getValue(SUSPENDED);
         return var7 == var8;
      } else {
         return false;
      }
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return var1.withProperty(NORTH, isConnectedTo(var2, var3, var1, EnumFacing.NORTH))
         .withProperty(EAST, isConnectedTo(var2, var3, var1, EnumFacing.EAST))
         .withProperty(SOUTH, isConnectedTo(var2, var3, var1, EnumFacing.SOUTH))
         .withProperty(WEST, isConnectedTo(var2, var3, var1, EnumFacing.WEST));
   }

   @Override
   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   public void updateState(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      boolean var4 = var3.getValue(POWERED);
      boolean var5 = false;
      List var6 = var1.getEntitiesWithinAABBExcludingEntity(
         (Entity)null,
         new AxisAlignedBB(var2.getX() + this.B, var2.getY() + this.C, var2.getZ() + this.D, var2.getX() + this.E, var2.getY() + this.F, var2.getZ() + this.G)
      );
      if (!var6.isEmpty()) {
         for (Entity var8 : var6) {
            if (!var8.doesEntityNotTriggerPressurePlate()) {
               var5 = true;
               break;
            }
         }
      }

      if (var5 != var4) {
         var3 = var3.withProperty(POWERED, var5);
         var1.a(var2, var3, 3);
         this.notifyHook(var1, var2, var3);
      }

      if (var5) {
         var1.scheduleUpdate(var2, this, this.tickRate(var1));
      }
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      boolean var5 = var3.getValue(SUSPENDED);
      boolean var6 = !World.doesBlockHaveSolidTopSurface(var1, var2.down());
      if (var5 != var6) {
         this.dropBlockAsItem(var1, var2, var3, 0);
         var1.setBlockToAir(var2);
      }
   }

   public BlockTripWire() {
      super(Material.circuits);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(POWERED, false)
            .withProperty(SUSPENDED, false)
            .withProperty(ATTACHED, false)
            .withProperty(DISARMED, false)
            .withProperty(NORTH, false)
            .withProperty(EAST, false)
            .withProperty(SOUTH, false)
            .withProperty(WEST, false)
      );
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.15625F, 1.0F);
      this.setTickRandomly(true);
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   public void notifyHook(World var1, BlockPos var2, IBlockState var3) {
      for (EnumFacing var7 : new EnumFacing[]{EnumFacing.SOUTH, EnumFacing.WEST}) {
         for (int var8 = 1; var8 < 42; var8++) {
            BlockPos var9 = var2.a(var7, var8);
            IBlockState var10 = var1.getBlockState(var9);
            if (var10.getBlock() == Blocks.tripwire_hook) {
               if (var10.getValue(BlockTripWireHook.FACING) == var7.getOpposite()) {
                  Blocks.tripwire_hook.func_176260_a(var1, var9, var10, false, true, var8, var3);
               }
               break;
            }

            if (var10.getBlock() != Blocks.tripwire) {
               break;
            }
         }
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState()
         .withProperty(POWERED, (var1 & 1) > 0)
         .withProperty(SUSPENDED, (var1 & 2) > 0)
         .withProperty(ATTACHED, (var1 & 4) > 0)
         .withProperty(DISARMED, (var1 & 8) > 0);
   }

   @Override
   public void onBlockHarvested(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
      if (!var1.D && var4.getCurrentEquippedItem() != null && var4.getCurrentEquippedItem().getItem() == Items.shears) {
         var1.a(var2, var3.withProperty(DISARMED, Boolean.TRUE), 4);
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      var3 = var3.withProperty(SUSPENDED, !World.doesBlockHaveSolidTopSurface(var1, var2.down()));
      var1.a(var2, var3, 3);
      this.notifyHook(var1, var2, var3);
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      this.notifyHook(var1, var2, var3.withProperty(POWERED, true));
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!var1.D && var1.getBlockState(var2).getValue(POWERED)) {
         this.updateState(var1, var2);
      }
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
      if (!var1.D && !var3.getValue(POWERED)) {
         this.updateState(var1, var2);
      }
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.string;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
