package net.minecraft.world.gen;

import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.http.websocketx.WebSocket13FrameDecoder;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import org.apache.log4j.helpers.OptionConverter;

public class MapGenCavesHell extends MapGenBase {
   public WebSocket13FrameDecoder field_0003;
   public EntityTameable field_0002;
   public ZlibWrapper field_0001;
   public RenderVillager field_0004;
   public OptionConverter field_0000;

   public void func_180704_a(
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
      double var19 = var3 * 16 + 8;
      double var21 = var4 * 16 + 8;
      float var23 = 0.0F;
      float var24 = 0.0F;
      Random var25 = new Random(var1);
      if (var16 <= 0) {
         int var26 = this.range * 16 - 16;
         var16 = var26 - var25.nextInt(var26 / 4);
      }

      boolean var63 = false;
      if (var15 == -1) {
         var15 = var16 / 2;
         var63 = true;
      }

      int var27 = var25.nextInt(var16 / 2) + var16 / 4;

      for (boolean var28 = var25.nextInt(6) == 0; var15 < var16; var15++) {
         double var29 = 1.5 + MathHelper.sin(var15 * (float) Math.PI / var16) * var12 * 1.0F;
         double var31 = var29 * var17;
         float var33 = MathHelper.cos(var14);
         float var34 = MathHelper.sin(var14);
         var6 += MathHelper.cos(var13) * var33;
         var8 += var34;
         var10 += MathHelper.sin(var13) * var33;
         if (var28) {
            var14 *= 0.92F;
         } else {
            var14 *= 0.7F;
         }

         var14 += var24 * 0.1F;
         var13 += var23 * 0.1F;
         var24 *= 0.9F;
         var23 *= 0.75F;
         var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
         var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;
         if (!var63 && var15 == var27 && var12 > 1.0F) {
            this.func_180704_a(
               var25.nextLong(),
               var3,
               var4,
               var5,
               var6,
               var8,
               var10,
               var25.nextFloat() * 0.5F + 0.5F,
               var13 - (float) (Math.PI / 2),
               var14 / 3.0F,
               var15,
               var16,
               1.0
            );
            this.func_180704_a(
               var25.nextLong(),
               var3,
               var4,
               var5,
               var6,
               var8,
               var10,
               var25.nextFloat() * 0.5F + 0.5F,
               var13 + (float) (Math.PI / 2),
               var14 / 3.0F,
               var15,
               var16,
               1.0
            );
            return;
         }

         if (var63 || var25.nextInt(4) != 0) {
            double var35 = var6 - var19;
            double var37 = var10 - var21;
            double var39 = var16 - var15;
            double var41 = var12 + 2.0F + 16.0F;
            if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
               return;
            }

            if (var6 >= var19 - 16.0 - var29 * 2.0
               && var10 >= var21 - 16.0 - var29 * 2.0
               && var6 <= var19 + 16.0 + var29 * 2.0
               && var10 <= var21 + 16.0 + var29 * 2.0) {
               int var43 = MathHelper.floor_double(var6 - var29) - var3 * 16 - 1;
               int var44 = MathHelper.floor_double(var6 + var29) - var3 * 16 + 1;
               int var45 = MathHelper.floor_double(var8 - var31) - 1;
               int var46 = MathHelper.floor_double(var8 + var31) + 1;
               int var47 = MathHelper.floor_double(var10 - var29) - var4 * 16 - 1;
               int var48 = MathHelper.floor_double(var10 + var29) - var4 * 16 + 1;
               if (var43 < 0) {
                  var43 = 0;
               }

               if (var44 > 16) {
                  var44 = 16;
               }

               if (var45 < 1) {
                  var45 = 1;
               }

               if (var46 > 120) {
                  var46 = 120;
               }

               if (var47 < 0) {
                  var47 = 0;
               }

               if (var48 > 16) {
                  var48 = 16;
               }

               boolean var49 = false;

               for (int var50 = var43; !var49 && var50 < var44; var50++) {
                  for (int var51 = var47; !var49 && var51 < var48; var51++) {
                     for (int var52 = var46 + 1; !var49 && var52 >= var45 - 1; var52--) {
                        if (var52 >= 0 && var52 < 128) {
                           IBlockState var53 = var5.getBlockState(var50, var52, var51);
                           if (var53.getBlock() == Blocks.flowing_lava || var53.getBlock() == Blocks.lava) {
                              var49 = true;
                           }

                           if (var52 != var45 - 1 && var50 != var43 && var50 != var44 - 1 && var51 != var47 && var51 != var48 - 1) {
                              var52 = var45;
                           }
                        }
                     }
                  }
               }

               if (!var49) {
                  for (int var64 = var43; var64 < var44; var64++) {
                     double var65 = (var64 + var3 * 16 + 0.5 - var6) / var29;

                     for (int var66 = var47; var66 < var48; var66++) {
                        double var54 = (var66 + var4 * 16 + 0.5 - var10) / var29;

                        for (int var56 = var46; var56 > var45; var56--) {
                           double var57 = (var56 - 1 + 0.5 - var8) / var31;
                           if (var57 > -0.7 && var65 * var65 + var57 * var57 + var54 * var54 < 1.0) {
                              IBlockState var59 = var5.getBlockState(var64, var56, var66);
                              if (var59.getBlock() == Blocks.netherrack || var59.getBlock() == Blocks.dirt || var59.getBlock() == Blocks.grass) {
                                 var5.setBlockState(var64, var56, var66, Blocks.air.getDefaultState());
                              }
                           }
                        }
                     }
                  }

                  if (var63) {
                     break;
                  }
               }
            }
         }
      }
   }

   @Override
   public void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, ChunkPrimer var6) {
      int var7 = this.b.nextInt(this.b.nextInt(this.b.nextInt(10) + 1) + 1);
      if (this.b.nextInt(5) != 0) {
         var7 = 0;
      }

      for (int var8 = 0; var8 < var7; var8++) {
         double var9 = var2 * 16 + this.b.nextInt(16);
         double var11 = this.b.nextInt(128);
         double var13 = var3 * 16 + this.b.nextInt(16);
         int var15 = 1;
         if (this.b.nextInt(4) == 0) {
            this.func_180705_a(this.b.nextLong(), var4, var5, var6, var9, var11, var13);
            var15 += this.b.nextInt(4);
         }

         for (int var16 = 0; var16 < var15; var16++) {
            float var17 = this.b.nextFloat() * (float) Math.PI * 2.0F;
            float var18 = (this.b.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float var19 = this.b.nextFloat() * 2.0F + this.b.nextFloat();
            this.func_180704_a(this.b.nextLong(), var4, var5, var6, var9, var11, var13, var19 * 2.0F, var17, var18, 0, 0, 0.5);
         }
      }
   }

   public void func_180705_a(long var1, int var3, int var4, ChunkPrimer var5, double var6, double var8, double var10) {
      this.func_180704_a(var1, var3, var4, var5, var6, var8, var10, 1.0F + this.b.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5);
   }
}
