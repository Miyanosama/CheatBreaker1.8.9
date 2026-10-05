package net.minecraft.world.gen;

import java.util.Random;
import javax.vecmath.Color3f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenRavine extends MapGenBase {
   public Color3f field_0001;
   public float[] field_75046_d = new float[1024];

   public void func_180707_a(
      long var1,
      int var3,
      int var4,
      ChunkPrimer var5,
      double var6,
      double var8,
      double var10,
      float var12,
      float var13,
      float var14,
      int var15,
      int var16,
      double var17
   ) {
      Random var19 = new Random(var1);
      double var20 = var3 * 16 + 8;
      double var22 = var4 * 16 + 8;
      float var24 = 0.0F;
      float var25 = 0.0F;
      if (var16 <= 0) {
         int var26 = this.range * 16 - 16;
         var16 = var26 - var19.nextInt(var26 / 4);
      }

      boolean var64 = false;
      if (var15 == -1) {
         var15 = var16 / 2;
         var64 = true;
      }

      float var27 = 1.0F;

      for (int var28 = 0; var28 < 256; var28++) {
         if (var28 == 0 || var19.nextInt(3) == 0) {
            var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
         }

         this.field_75046_d[var28] = var27 * var27;
      }

      for (; var15 < var16; var15++) {
         double var65 = 1.5 + MathHelper.sin(var15 * (float) Math.PI / var16) * var12 * 1.0F;
         double var30 = var65 * var17;
         var65 *= var19.nextFloat() * 0.25 + 0.75;
         var30 *= var19.nextFloat() * 0.25 + 0.75;
         float var32 = MathHelper.cos(var14);
         float var33 = MathHelper.sin(var14);
         var6 += MathHelper.cos(var13) * var32;
         var8 += var33;
         var10 += MathHelper.sin(var13) * var32;
         var14 *= 0.7F;
         var14 += var25 * 0.05F;
         var13 += var24 * 0.05F;
         var25 *= 0.8F;
         var24 *= 0.5F;
         var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
         var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;
         if (var64 || var19.nextInt(4) != 0) {
            double var34 = var6 - var20;
            double var36 = var10 - var22;
            double var38 = var16 - var15;
            double var40 = var12 + 2.0F + 16.0F;
            if (var34 * var34 + var36 * var36 - var38 * var38 > var40 * var40) {
               return;
            }

            if (var6 >= var20 - 16.0 - var65 * 2.0
               && var10 >= var22 - 16.0 - var65 * 2.0
               && var6 <= var20 + 16.0 + var65 * 2.0
               && var10 <= var22 + 16.0 + var65 * 2.0) {
               int var42 = MathHelper.floor_double(var6 - var65) - var3 * 16 - 1;
               int var43 = MathHelper.floor_double(var6 + var65) - var3 * 16 + 1;
               int var44 = MathHelper.floor_double(var8 - var30) - 1;
               int var45 = MathHelper.floor_double(var8 + var30) + 1;
               int var46 = MathHelper.floor_double(var10 - var65) - var4 * 16 - 1;
               int var47 = MathHelper.floor_double(var10 + var65) - var4 * 16 + 1;
               if (var42 < 0) {
                  var42 = 0;
               }

               if (var43 > 16) {
                  var43 = 16;
               }

               if (var44 < 1) {
                  var44 = 1;
               }

               if (var45 > 248) {
                  var45 = 248;
               }

               if (var46 < 0) {
                  var46 = 0;
               }

               if (var47 > 16) {
                  var47 = 16;
               }

               boolean var48 = false;

               for (int var49 = var42; !var48 && var49 < var43; var49++) {
                  for (int var50 = var46; !var48 && var50 < var47; var50++) {
                     for (int var51 = var45 + 1; !var48 && var51 >= var44 - 1; var51--) {
                        if (var51 >= 0 && var51 < 256) {
                           IBlockState var52 = var5.getBlockState(var49, var51, var50);
                           if (var52.getBlock() == Blocks.flowing_water || var52.getBlock() == Blocks.water) {
                              var48 = true;
                           }

                           if (var51 != var44 - 1 && var49 != var42 && var49 != var43 - 1 && var50 != var46 && var50 != var47 - 1) {
                              var51 = var44;
                           }
                        }
                     }
                  }
               }

               if (!var48) {
                  BlockPos$MutableBlockPos var68 = new BlockPos$MutableBlockPos();

                  for (int var69 = var42; var69 < var43; var69++) {
                     double var70 = (var69 + var3 * 16 + 0.5 - var6) / var65;

                     for (int var53 = var46; var53 < var47; var53++) {
                        double var54 = (var53 + var4 * 16 + 0.5 - var10) / var65;
                        boolean var56 = false;
                        if (var70 * var70 + var54 * var54 < 1.0) {
                           for (int var57 = var45; var57 > var44; var57--) {
                              double var58 = (var57 - 1 + 0.5 - var8) / var30;
                              if ((var70 * var70 + var54 * var54) * this.field_75046_d[var57 - 1] + var58 * var58 / 6.0 < 1.0) {
                                 IBlockState var60 = var5.getBlockState(var69, var57, var53);
                                 if (var60.getBlock() == Blocks.grass) {
                                    var56 = true;
                                 }

                                 if (var60.getBlock() == Blocks.stone || var60.getBlock() == Blocks.dirt || var60.getBlock() == Blocks.grass) {
                                    if (var57 - 1 < 10) {
                                       var5.setBlockState(var69, var57, var53, Blocks.flowing_lava.getDefaultState());
                                    } else {
                                       var5.setBlockState(var69, var57, var53, Blocks.air.getDefaultState());
                                       if (var56 && var5.getBlockState(var69, var57 - 1, var53).getBlock() == Blocks.dirt) {
                                          var68.set(var69 + var3 * 16, 0, var53 + var4 * 16);
                                          var5.setBlockState(var69, var57 - 1, var53, this.c.getBiomeGenForCoords(var68).ak);
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (var64) {
                     break;
                  }
               }
            }
         }
      }
   }

   @Override
   public void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, ChunkPrimer var6) {
      if (this.b.nextInt(50) == 0) {
         double var7 = var2 * 16 + this.b.nextInt(16);
         double var9 = this.b.nextInt(this.b.nextInt(40) + 8) + 20;
         double var11 = var3 * 16 + this.b.nextInt(16);
         byte var13 = 1;

         for (int var14 = 0; var14 < var13; var14++) {
            float var15 = this.b.nextFloat() * (float) Math.PI * 2.0F;
            float var16 = (this.b.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float var17 = (this.b.nextFloat() * 2.0F + this.b.nextFloat()) * 2.0F;
            this.func_180707_a(this.b.nextLong(), var4, var5, var6, var7, var9, var11, var17, var15, var16, 0, 0, 3.0);
         }
      }
   }
}
