package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;

public class BlockMushroom extends BlockBush implements IGrowable {
   public ModelVillager field_0001;
   public BlockTorch$1 field_0000;

   public BlockMushroom() {
      float var1 = 0.2F;
      this.a(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var1 * 2.0F, 0.5F + var1);
      this.setTickRandomly(true);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (var4.nextInt(25) == 0) {
         int var5 = 5;
         byte var6 = 4;

         for (BlockPos var8 : BlockPos.getAllInBoxMutable(var2.add(-4, -1, -4), var2.add(4, 1, 4))) {
            if (var1.getBlockState(var8).getBlock() == this) {
               if (--var5 <= 0) {
                  return;
               }
            }
         }

         BlockPos var9 = var2.add(var4.nextInt(3) - 1, var4.nextInt(2) - var4.nextInt(2), var4.nextInt(3) - 1);

         for (int var10 = 0; var10 < 4; var10++) {
            if (var1.isAirBlock(var9) && this.canBlockStay(var1, var9, this.getDefaultState())) {
               var2 = var9;
            }

            var9 = var2.add(var4.nextInt(3) - 1, var4.nextInt(2) - var4.nextInt(2), var4.nextInt(3) - 1);
         }

         if (var1.isAirBlock(var9) && this.canBlockStay(var1, var9, this.getDefaultState())) {
            var1.a(var9, this.getDefaultState(), 2);
         }
      }
   }

   @Override
   public boolean canUseBonemeal(World var1, Random var2, BlockPos var3, IBlockState var4) {
      return var2.nextFloat() < 0.4;
   }

   public boolean generateBigMushroom(World var1, BlockPos var2, IBlockState var3, Random var4) {
      var1.setBlockToAir(var2);
      WorldGenBigMushroom var5 = null;
      if (this == Blocks.brown_mushroom) {
         var5 = new WorldGenBigMushroom(Blocks.brown_mushroom_block);
      } else if (this == Blocks.red_mushroom) {
         var5 = new WorldGenBigMushroom(Blocks.red_mushroom_block);
      }

      if (var5 != null && var5.generate(var1, var4, var2)) {
         return true;
      } else {
         var1.a(var2, var3, 3);
         return false;
      }
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return super.canPlaceBlockAt(var1, var2) && this.canBlockStay(var1, var2, this.getDefaultState());
   }

   @Override
   public boolean canPlaceBlockOn(Block var1) {
      return var1.isFullBlock();
   }

   @Override
   public boolean canGrow(World var1, BlockPos var2, IBlockState var3, boolean var4) {
      return true;
   }

   @Override
   public boolean canBlockStay(World var1, BlockPos var2, IBlockState var3) {
      if (var2.getY() >= 0 && var2.getY() < 256) {
         IBlockState var4 = var1.getBlockState(var2.down());
         return var4.getBlock() == Blocks.mycelium
            ? true
            : (
               var4.getBlock() == Blocks.dirt && var4.getValue(BlockDirt.VARIANT) == BlockDirt$DirtType.PODZOL
                  ? true
                  : var1.getLight(var2) < 13 && this.canPlaceBlockOn(var4.getBlock())
            );
      } else {
         return false;
      }
   }

   @Override
   public void grow(World var1, Random var2, BlockPos var3, IBlockState var4) {
      this.generateBigMushroom(var1, var3, var4, var2);
   }
}
