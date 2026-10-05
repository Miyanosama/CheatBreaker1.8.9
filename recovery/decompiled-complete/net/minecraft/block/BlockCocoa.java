package net.minecraft.block;

import io.netty.handler.codec.sctp.SctpOutboundByteStreamHandler;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCocoa extends BlockDirectional implements IGrowable {
   public SctpOutboundByteStreamHandler field_0000;
   public static PropertyInteger AGE = PropertyInteger.create("age", 0, 2);

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   public BlockCocoa() {
      super(Material.plants);
      this.setDefaultState(this.M.getBaseState().withProperty(O, EnumFacing.NORTH).withProperty(AGE, 0));
      this.setTickRandomly(true);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void grow(World var1, Random var2, BlockPos var3, IBlockState var4) {
      var1.a(var3, var4.withProperty(AGE, var4.getValue(AGE) + 1), 2);
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      int var6 = var3.getValue(AGE);
      byte var7 = 1;
      if (var6 >= 2) {
         var7 = 3;
      }

      for (int var8 = 0; var8 < var7; var8++) {
         a(var1, var2, new ItemStack(Items.dye, 1, EnumDyeColor.BROWN.getDyeDamage()));
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(O, EnumFacing.getHorizontal(var1)).withProperty(AGE, (var1 & 15) >> 2);
   }

   public boolean canBlockStay(World var1, BlockPos var2, IBlockState var3) {
      var2 = var2.a(var3.getValue(O));
      IBlockState var4 = var1.getBlockState(var2);
      return var4.getBlock() == Blocks.log && var4.getValue(BlockPlanks.VARIANT) == BlockPlanks$EnumType.JUNGLE;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.dye;
   }

   @Override
   public int getDamageValue(World var1, BlockPos var2) {
      return EnumDyeColor.BROWN.getDyeDamage();
   }

   @Override
   public boolean canGrow(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      return var3.getValue(AGE) < 2;
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World var1, BlockPos var2) {
      this.setBlockBoundsBasedOnState(var1, var2);
      return super.getSelectedBoundingBox(var1, var2);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (!this.canBlockStay(var1, var2, var3)) {
         this.dropBlock(var1, var2, var3);
      } else if (var1.s.nextInt(5) == 0) {
         int var5 = var3.getValue(AGE);
         if (var5 < 2) {
            var1.a(var2, var3.withProperty(AGE, var5 + 1), 2);
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(O).getHorizontalIndex();
      return var2 | var1.getValue(AGE) << 2;
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      if (!var3.getAxis().isHorizontal()) {
         var3 = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(O, var3.getOpposite()).withProperty(AGE, 0);
   }

   public void dropBlock(World var1, BlockPos var2, IBlockState var3) {
      var1.a(var2, Blocks.air.getDefaultState(), 3);
      this.dropBlockAsItem(var1, var2, var3, 0);
   }

   @Override
   public boolean canUseBonemeal(World var1, Random var2, BlockPos var3, IBlockState var4) {
      return true;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      EnumFacing var4 = var3.getValue(O);
      int var5 = var3.getValue(AGE);
      int var6 = 4 + var5 * 2;
      int var7 = 5 + var5 * 2;
      float var8 = var6 / 2.0F;
      switch (BlockCocoa$1.field_180415_a[var4.ordinal()]) {
         case 1:
            this.a((8.0F - var8) / 16.0F, (12.0F - var7) / 16.0F, (15.0F - var6) / 16.0F, (8.0F + var8) / 16.0F, 0.75F, 0.9375F);
            break;
         case 2:
            this.a((8.0F - var8) / 16.0F, (12.0F - var7) / 16.0F, 0.0625F, (8.0F + var8) / 16.0F, 0.75F, (1.0F + var6) / 16.0F);
            break;
         case 3:
            this.a(0.0625F, (12.0F - var7) / 16.0F, (8.0F - var8) / 16.0F, (1.0F + var6) / 16.0F, 0.75F, (8.0F + var8) / 16.0F);
            break;
         case 4:
            this.a((15.0F - var6) / 16.0F, (12.0F - var7) / 16.0F, (8.0F - var8) / 16.0F, 0.9375F, 0.75F, (8.0F + var8) / 16.0F);
      }
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, O, AGE);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!this.canBlockStay(var1, var2, var3)) {
         this.dropBlock(var1, var2, var3);
      }
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      EnumFacing var6 = EnumFacing.fromAngle(var4.y);
      var1.a(var2, var3.withProperty(O, var6), 2);
   }
}
