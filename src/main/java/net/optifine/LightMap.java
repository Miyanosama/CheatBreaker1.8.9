package net.optifine;

import net.minecraft.src.Config;
import net.minecraft.world.World;

public class LightMap {
   public float[][] sunRgbs;
   public CustomColormap lightMapRgb = null;
   public float[][] torchRgbs;

   public CustomColormap getColormap() {
      return this.lightMapRgb;
   }

   public LightMap(CustomColormap var1) {
      this.sunRgbs = new float[16][3];
      this.torchRgbs = new float[16][3];
      this.lightMapRgb = var1;
   }

   public boolean updateLightmap(World var1, float var2, int[] var3, boolean var4) {
      if (this.lightMapRgb == null) {
         return false;
      } else {
         int var5 = this.lightMapRgb.getHeight();
         if (var4 && var5 < 64) {
            return false;
         } else {
            int var6 = this.lightMapRgb.getWidth();
            if (var6 < 16) {
               warn("Invalid lightmap width: " + var6);
               this.lightMapRgb = null;
               return false;
            } else {
               int var7 = 0;
               if (var4) {
                  var7 = var6 * 16 * 2;
               }

               float var8 = 1.1666666F * (var1.getSunBrightness(1.0F) - 0.2F);
               if (var1.getLastLightningBolt() > 0) {
                  var8 = 1.0F;
               }

               var8 = Config.limitTo1(var8);
               float var9 = var8 * (var6 - 1);
               float var10 = Config.limitTo1(var2 + 0.5F) * (var6 - 1);
               float var11 = Config.limitTo1(Config.getGameSettings().method_01290());
               boolean var12 = var11 > 1.0E-4F;
               float[][] var13 = this.lightMapRgb.getColorsRgb();
               this.getLightMapColumn(var13, var9, var7, var6, this.sunRgbs);
               this.getLightMapColumn(var13, var10, var7 + 16 * var6, var6, this.torchRgbs);
               float[] var14 = new float[3];

               for (int var15 = 0; var15 < 16; var15++) {
                  for (int var16 = 0; var16 < 16; var16++) {
                     for (int var17 = 0; var17 < 3; var17++) {
                        float var18 = Config.limitTo1(this.sunRgbs[var15][var17] + this.torchRgbs[var16][var17]);
                        if (var12) {
                           float var19 = 1.0F - var18;
                           var19 = 1.0F - var19 * var19 * var19 * var19;
                           var18 = var11 * var19 + (1.0F - var11) * var18;
                        }

                        var14[var17] = var18;
                     }

                     int var21 = (int)(var14[0] * 255.0F);
                     int var22 = (int)(var14[1] * 255.0F);
                     int var24 = (int)(var14[2] * 255.0F);
                     var3[var15 * 16 + var16] = 0xFF000000 | var21 << 16 | var22 << 8 | var24;
                  }
               }

               return true;
            }
         }
      }
   }

   public void getLightMapColumn(float[][] var1, float var2, int var3, int var4, float[][] var5) {
      int var6 = (int)Math.floor(var2);
      int var7 = (int)Math.ceil(var2);
      if (var6 == var7) {
         for (int var8 = 0; var8 < 16; var8++) {
            float[] var9 = var1[var3 + var8 * var4 + var6];
            float[] var10 = var5[var8];

            for (int var11 = 0; var11 < 3; var11++) {
               var10[var11] = var9[var11];
            }
         }
      } else {
         float var15 = 1.0F - (var2 - var6);
         float var16 = 1.0F - (var7 - var2);

         for (int var17 = 0; var17 < 16; var17++) {
            float[] var18 = var1[var3 + var17 * var4 + var6];
            float[] var12 = var1[var3 + var17 * var4 + var7];
            float[] var13 = var5[var17];

            for (int var14 = 0; var14 < 3; var14++) {
               var13[var14] = var18[var14] * var15 + var12[var14] * var16;
            }
         }
      }
   }

   public static void warn(String var0) {
      Config.warn("CustomColors: " + var0);
   }

   public static void method_09043(String var0) {
      Config.dbg("CustomColors: " + var0);
   }
}
