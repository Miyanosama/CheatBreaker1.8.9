package net.optifine.util;

import net.minecraft.util.MathHelper;

public class MathUtils {
   public static final float recoveredField2579 = 1.5707964F;
   public static final float recoveredField2580 = 6.2831855F;
   public static final float recoveredField2581 = 3.1415927F;
   public static float[] ASIN_TABLE = new float[65536];

   public static float acos(float var0) {
      return (float) (Math.PI / 2) - ASIN_TABLE[(int)((var0 + 1.0F) * 32767.5) & 65535];
   }

   public static int roundDownToPowerOfTwo(int var0) {
      int var1 = MathHelper.roundUpToPowerOfTwo(var0);
      return var0 == var1 ? var1 : var1 / 2;
   }

   public static int getSum(int[] var0) {
      if (var0.length <= 0) {
         return 0;
      } else {
         int var1 = 0;

         for (int var2 = 0; var2 < var0.length; var2++) {
            int var3 = var0[var2];
            var1 += var3;
         }

         return var1;
      }
   }

   public static float asin(float var0) {
      return ASIN_TABLE[(int)((var0 + 1.0F) * 32767.5) & 65535];
   }

   public static float toRad(float var0) {
      return var0 / 180.0F * MathHelper.PI;
   }

   public static float roundToFloat(double var0) {
      return (float)(Math.round(var0 * 1.0E8) / 1.0E8);
   }

   public static boolean equalsDelta(float var0, float var1, float var2) {
      return Math.abs(var0 - var1) <= var2;
   }

   static {
      for (int var0 = 0; var0 < 65536; var0++) {
         ASIN_TABLE[var0] = (float)Math.asin(var0 / 32767.5 - 1.0);
      }

      for (int var1 = -1; var1 < 2; var1++) {
         ASIN_TABLE[(int)((var1 + 1.0) * 32767.5) & 65535] = (float)Math.asin(var1);
      }
   }

   public static float toDeg(float var0) {
      return var0 * 180.0F / MathHelper.PI;
   }

   public static int getAverage(int[] var0) {
      if (var0.length <= 0) {
         return 0;
      } else {
         int var1 = getSum(var0);
         return var1 / var0.length;
      }
   }
}
