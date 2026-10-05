package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.buffer.AbstractByteBufAllocator$1;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysToDoubleTask;
import java.util.List;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRailDetector extends BlockRailBase {
   public AbstractByteBufAllocator$1 field_0000;
   public static PropertyEnum<BlockRailBase$EnumRailDirection> SHAPE = PropertyEnum.create(
      "shape", BlockRailBase$EnumRailDirection.class, new BlockRailDetector$1()
   );
   public ConcurrentHashMapV8$MapReduceKeysToDoubleTask field_0003;
   public static PropertyBool POWERED = PropertyBool.create("powered");

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      super.onBlockAdded(var1, var2, var3);
      this.updatePoweredState(var1, var2, var3);
   }

   public <T extends EntityMinecart> List<T> findMinecarts(World var1, BlockPos var2, Class<T> var3, Predicate<Entity>... var4) {
      AxisAlignedBB var5 = this.getDectectionBox(var2);
      return var4.length != 1 ? var1.getEntitiesWithinAABB(var3, var5) : var1.getEntitiesWithinAABB(var3, var5, var4[0]);
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return var3.getValue(POWERED) ? 15 : 0;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!var1.D && var3.getValue(POWERED)) {
         this.updatePoweredState(var1, var2, var3);
      }
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return !var3.getValue(POWERED) ? 0 : (var4 == EnumFacing.UP ? 15 : 0);
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
      if (!var1.D && !var3.getValue(POWERED)) {
         this.updatePoweredState(var1, var2, var3);
      }
   }

   @Override
   public int tickRate(World var1) {
      return 20;
   }

   @Override
   public IProperty<BlockRailBase$EnumRailDirection> getShapeProperty() {
      return SHAPE;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, SHAPE, POWERED);
   }

   @Override
   public int getComparatorInputOverride(World var1, BlockPos var2) {
      if (var1.getBlockState(var2).getValue(POWERED)) {
         List var3 = this.findMinecarts(var1, var2, EntityMinecartCommandBlock.class);
         if (!var3.isEmpty()) {
            return ((EntityMinecartCommandBlock)var3.get(0)).getCommandBlockLogic().getSuccessCount();
         }

         List var4 = this.findMinecarts(var1, var2, EntityMinecart.class, EntitySelectors.selectInventories);
         if (!var4.isEmpty()) {
            return Container.calcRedstoneFromInventory((IInventory)var4.get(0));
         }
      }

      return 0;
   }

   public void updatePoweredState(World var1, BlockPos var2, IBlockState var3) {
      boolean var4 = var3.getValue(POWERED);
      boolean var5 = false;
      List var6 = this.findMinecarts(var1, var2, EntityMinecart.class);
      if (!var6.isEmpty()) {
         var5 = true;
      }

      if (var5 && !var4) {
         var1.a(var2, var3.withProperty(POWERED, true), 3);
         var1.notifyNeighborsOfStateChange(var2, this);
         var1.notifyNeighborsOfStateChange(var2.down(), this);
         var1.markBlockRangeForRenderUpdate(var2, var2);
      }

      if (!var5 && var4) {
         var1.a(var2, var3.withProperty(POWERED, false), 3);
         var1.notifyNeighborsOfStateChange(var2, this);
         var1.notifyNeighborsOfStateChange(var2.down(), this);
         var1.markBlockRangeForRenderUpdate(var2, var2);
      }

      if (var5) {
         var1.scheduleUpdate(var2, this, this.tickRate(var1));
      }

      var1.updateComparatorOutputLevel(var2, this);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(SHAPE, BlockRailBase$EnumRailDirection.byMetadata(var1 & 7)).withProperty(POWERED, (var1 & 8) > 0);
   }

   public AxisAlignedBB getDectectionBox(BlockPos var1) {
      float var2 = 0.2F;
      return new AxisAlignedBB(var1.getX() + 0.2F, var1.getY(), var1.getZ() + 0.2F, var1.getX() + 1 - 0.2F, var1.getY() + 1 - 0.2F, var1.getZ() + 1 - 0.2F);
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   public BlockRailDetector() {
      super(true);
      this.setDefaultState(this.M.getBaseState().withProperty(POWERED, false).withProperty(SHAPE, BlockRailBase$EnumRailDirection.NORTH_SOUTH));
      this.setTickRandomly(true);
   }

   @Override
   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(SHAPE).getMetadata();
      if (var1.getValue(POWERED)) {
         var2 |= 8;
      }

      return var2;
   }
}
