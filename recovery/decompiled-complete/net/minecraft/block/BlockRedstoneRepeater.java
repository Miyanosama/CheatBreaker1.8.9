package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBook;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPiston;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRedstoneRepeater extends BlockRedstoneDiode {
   public MobSpawnerBaseLogic field_0002;
   public static PropertyBool LOCKED = PropertyBool.create("locked");
   public ItemPiston field_0000;
   public static PropertyInteger DELAY = PropertyInteger.create("delay", 1, 4);
   public EntityAIWander field_0003;
   public ModelBook field_0005;

   public BlockRedstoneRepeater(boolean var1) {
      super(var1);
      this.setDefaultState(this.M.getBaseState().withProperty(O, EnumFacing.NORTH).withProperty(DELAY, 1).withProperty(LOCKED, false));
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      super.breakBlock(var1, var2, var3);
      this.notifyNeighbors(var1, var2, var3);
   }

   @Override
   public boolean canPowerSide(Block var1) {
      return isRedstoneRepeaterBlockID(var1);
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (!var4.bA.allowEdit) {
         return false;
      } else {
         var1.a(var2, var3.cycleProperty(DELAY), 3);
         return true;
      }
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.repeater;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(O, EnumFacing.getHorizontal(var1)).withProperty(LOCKED, false).withProperty(DELAY, 1 + (var1 >> 2));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(O).getHorizontalIndex();
      return var2 | var1.getValue(DELAY) - 1 << 2;
   }

   @Override
   public boolean isLocked(IBlockAccess var1, BlockPos var2, IBlockState var3) {
      return this.getPowerOnSides(var1, var2, var3) > 0;
   }

   @Override
   public int getDelay(IBlockState var1) {
      return var1.getValue(DELAY) * 2;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, O, DELAY, LOCKED);
   }

   @Override
   public IBlockState getUnpoweredState(IBlockState var1) {
      Integer var2 = var1.getValue(DELAY);
      Boolean var3 = var1.getValue(LOCKED);
      EnumFacing var4 = var1.getValue(O);
      return Blocks.unpowered_repeater.getDefaultState().withProperty(O, var4).withProperty(DELAY, var2).withProperty(LOCKED, var3);
   }

   @Override
   public IBlockState getPoweredState(IBlockState var1) {
      Integer var2 = var1.getValue(DELAY);
      Boolean var3 = var1.getValue(LOCKED);
      EnumFacing var4 = var1.getValue(O);
      return Blocks.powered_repeater.getDefaultState().withProperty(O, var4).withProperty(DELAY, var2).withProperty(LOCKED, var3);
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return var1.withProperty(LOCKED, this.isLocked(var2, var3, var1));
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (this.isRepeaterPowered) {
         EnumFacing var5 = var3.getValue(O);
         double var6 = var2.getX() + 0.5F + (var4.nextFloat() - 0.5F) * 0.2;
         double var8 = var2.getY() + 0.4F + (var4.nextFloat() - 0.5F) * 0.2;
         double var10 = var2.getZ() + 0.5F + (var4.nextFloat() - 0.5F) * 0.2;
         float var12 = -5.0F;
         if (var4.nextBoolean()) {
            var12 = var3.getValue(DELAY) * 2 - 1;
         }

         var12 /= 16.0F;
         double var13 = var12 * var5.getFrontOffsetX();
         double var15 = var12 * var5.getFrontOffsetZ();
         var1.spawnParticle(EnumParticleTypes.REDSTONE, var6 + var13, var8, var10 + var15, 0.0, 0.0, 0.0);
      }
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal("item.diode.name");
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Items.repeater;
   }
}
