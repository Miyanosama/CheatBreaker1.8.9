package net.minecraft.world.gen.feature;

import com.google.common.base.Predicate;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenMinable extends WorldGenerator {
   public Predicate<IBlockState> predicate;
   public int numberOfBlocks;
   public IBlockState oreBlock;

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      float var4 = var2.nextFloat() * (float) Math.PI;
      double var5 = var3.getX() + 8 + MathHelper.sin(var4) * this.numberOfBlocks / 8.0F;
      double var7 = var3.getX() + 8 - MathHelper.sin(var4) * this.numberOfBlocks / 8.0F;
      double var9 = var3.getZ() + 8 + MathHelper.cos(var4) * this.numberOfBlocks / 8.0F;
      double var11 = var3.getZ() + 8 - MathHelper.cos(var4) * this.numberOfBlocks / 8.0F;
      double var13 = var3.getY() + var2.nextInt(3) - 2;
      double var15 = var3.getY() + var2.nextInt(3) - 2;

      for (int var17 = 0; var17 < this.numberOfBlocks; var17++) {
         float var18 = (float)var17 / this.numberOfBlocks;
         double var19 = var5 + (var7 - var5) * var18;
         double var21 = var13 + (var15 - var13) * var18;
         double var23 = var9 + (var11 - var9) * var18;
         double var25 = var2.nextDouble() * this.numberOfBlocks / 16.0;
         double var27 = (MathHelper.sin((float) Math.PI * var18) + 1.0F) * var25 + 1.0;
         double var29 = (MathHelper.sin((float) Math.PI * var18) + 1.0F) * var25 + 1.0;
         int var31 = MathHelper.floor_double(var19 - var27 / 2.0);
         int var32 = MathHelper.floor_double(var21 - var29 / 2.0);
         int var33 = MathHelper.floor_double(var23 - var27 / 2.0);
         int var34 = MathHelper.floor_double(var19 + var27 / 2.0);
         int var35 = MathHelper.floor_double(var21 + var29 / 2.0);
         int var36 = MathHelper.floor_double(var23 + var27 / 2.0);

         for (int var37 = var31; var37 <= var34; var37++) {
            double var38 = (var37 + 0.5 - var19) / (var27 / 2.0);
            if (var38 * var38 < 1.0) {
               for (int var40 = var32; var40 <= var35; var40++) {
                  double var41 = (var40 + 0.5 - var21) / (var29 / 2.0);
                  if (var38 * var38 + var41 * var41 < 1.0) {
                     for (int var43 = var33; var43 <= var36; var43++) {
                        double var44 = (var43 + 0.5 - var23) / (var27 / 2.0);
                        if (var38 * var38 + var41 * var41 + var44 * var44 < 1.0) {
                           BlockPos var46 = new BlockPos(var37, var40, var43);
                           if (this.predicate.apply(var1.getBlockState(var46))) {
                              var1.a(var46, this.oreBlock, 2);
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      return true;
   }

   public WorldGenMinable(IBlockState var1, int var2) {
      this(var1, var2, BlockHelper.forBlock(Blocks.stone));
   }

   public WorldGenMinable(IBlockState var1, int var2, Predicate<IBlockState> var3) {
      this.oreBlock = var1;
      this.numberOfBlocks = var2;
      this.predicate = var3;
   }
}
