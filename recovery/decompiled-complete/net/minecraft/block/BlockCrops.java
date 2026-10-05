package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCrops extends BlockBush implements IGrowable {
   public ServerStatusResponse field_0000;
   public static PropertyInteger AGE = PropertyInteger.create("age", 0, 7);

   public static float getGrowthChance(Block var0, World var1, BlockPos var2) {
      float var3 = 1.0F;
      BlockPos var4 = var2.down();

      for (int var5 = -1; var5 <= 1; var5++) {
         for (int var6 = -1; var6 <= 1; var6++) {
            float var7 = 0.0F;
            IBlockState var8 = var1.getBlockState(var4.add(var5, 0, var6));
            if (var8.getBlock() == Blocks.farmland) {
               var7 = 1.0F;
               if (var8.getValue(BlockFarmland.MOISTURE) > 0) {
                  var7 = 3.0F;
               }
            }

            if (var5 != 0 || var6 != 0) {
               var7 /= 4.0F;
            }

            var3 += var7;
         }
      }

      BlockPos var12 = var2.north();
      BlockPos var13 = var2.south();
      BlockPos var14 = var2.west();
      BlockPos var15 = var2.east();
      boolean var9 = var0 == var1.getBlockState(var14).getBlock() || var0 == var1.getBlockState(var15).getBlock();
      boolean var10 = var0 == var1.getBlockState(var12).getBlock() || var0 == var1.getBlockState(var13).getBlock();
      if (var9 && var10) {
         var3 /= 2.0F;
      } else {
         boolean var11 = var0 == var1.getBlockState(var14.north()).getBlock()
            || var0 == var1.getBlockState(var15.north()).getBlock()
            || var0 == var1.getBlockState(var15.south()).getBlock()
            || var0 == var1.getBlockState(var14.south()).getBlock();
         if (var11) {
            var3 /= 2.0F;
         }
      }

      return var3;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return this.getSeed();
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(AGE);
   }

   public void grow(World var1, BlockPos var2, IBlockState var3) {
      int var4 = var3.getValue(AGE) + MathHelper.getRandomIntegerInRange(var1.s, 2, 5);
      if (var4 > 7) {
         var4 = 7;
      }

      var1.a(var2, var3.withProperty(AGE, var4), 2);
   }

   @Override
   public boolean canPlaceBlockOn(Block var1) {
      return var1 == Blocks.farmland;
   }

   public BlockCrops() {
      this.setDefaultState(this.M.getBaseState().withProperty(AGE, 0));
      this.setTickRandomly(true);
      float var1 = 0.5F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, 0.25F, 0.5F + var1);
      this.setCreativeTab((CreativeTabs)null);
      this.setHardness(0.0F);
      this.setStepSound(h);
      this.disableStats();
   }

   @Override
   public boolean canBlockStay(World var1, BlockPos var2, IBlockState var3) {
      return (var1.getLight(var2) >= 8 || var1.canSeeSky(var2)) && this.canPlaceBlockOn(var1.getBlockState(var2.down()).getBlock());
   }

   public Item getSeed() {
      return Items.wheat_seeds;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, 0);
      if (!var1.D) {
         int var6 = var3.getValue(AGE);
         if (var6 >= 7) {
            int var7 = 3 + var5;

            for (int var8 = 0; var8 < var7; var8++) {
               if (var1.s.nextInt(15) <= var6) {
                  a(var1, var2, new ItemStack(this.getSeed(), 1, 0));
               }
            }
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, AGE);
   }

   @Override
   public boolean canUseBonemeal(World var1, Random var2, BlockPos var3, IBlockState var4) {
      return true;
   }

   @Override
   public boolean canGrow(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      return var3.getValue(AGE) < 7;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      super.updateTick(var1, var2, var3, var4);
      if (var1.getLightFromNeighbors(var2.up()) >= 9) {
         int var5 = var3.getValue(AGE);
         if (var5 < 7) {
            float var6 = getGrowthChance(this, var1, var2);
            if (var4.nextInt((int)(25.0F / var6) + 1) == 0) {
               var1.a(var2, var3.withProperty(AGE, var5 + 1), 2);
            }
         }
      }
   }

   @Override
   public void grow(World var1, Random var2, BlockPos var3, IBlockState var4) {
      this.grow(var1, var3, var4);
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return var1.getValue(AGE) == 7 ? this.getCrop() : this.getSeed();
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(AGE, var1);
   }

   public Item getCrop() {
      return Items.wheat;
   }
}
