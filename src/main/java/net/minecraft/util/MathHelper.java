package net.minecraft.util;

import java.util.Random;
import java.util.UUID;
import net.optifine.util.MathUtils;

public class MathHelper {
   public static final int recoveredField1909 = 12;
   public static final int recoveredField1911 = 4096;
   public static double field_181163_d;
   public static final int recoveredField1912 = 4095;
   public static final int recoveredField1913 = 1024;
   public static float recoveredField1910 = sqrt_float(2.0F);
   public static float PI = MathUtils.roundToFloat(Math.PI);
   public static float recoveredField1908 = MathUtils.roundToFloat(Math.PI * 2);
   public static float recoveredField1915 = MathUtils.roundToFloat(Math.PI / 2);
   public static double[] field_181164_e;
   public static float recoveredField1906 = MathUtils.roundToFloat(651.8986469044033);
   public static float recoveredField1904 = MathUtils.roundToFloat(Math.PI / 180.0);
   public static double[] field_181165_f;
   public static float[] recoveredField1905 = new float[4096];
   public static boolean fastMath = false;
   public static int[] recoveredField1914;
   public static float[] recoveredField1907 = new float[65536];

   public static int func_180181_b(int var0, int var1, int var2) {
      int var3 = (var0 << 8) + var1;
      return (var3 << 8) + var2;
   }

   public static float sin(float var0) {
      return fastMath ? recoveredField1905[(int)(var0 * recoveredField1906) & 4095] : recoveredField1907[(int)(var0 * 10430.378F) & 65535];
   }

   public static double denormalizeClamp(double var0, double var2, double var4) {
      return var4 < 0.0 ? var0 : (var4 > 1.0 ? var2 : var0 + (var2 - var0) * var4);
   }

   public static float abs(float var0) {
      return var0 >= 0.0F ? var0 : -var0;
   }

   public static float cos(float var0) {
      return fastMath ? recoveredField1905[(int)(var0 * recoveredField1906 + 1024.0F) & 4095] : recoveredField1907[(int)(var0 * 10430.378F + 16384.0F) & 65535];
   }

   public static float clamp_float(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : (var0 > var2 ? var2 : var0);
   }

   public static boolean epsilonEquals(float var0, float var1) {
      return abs(var1 - var0) < 1.0E-5F;
   }

   public static int roundUpToPowerOfTwo(int var0) {
      int var1 = var0 - 1;
      var1 |= var1 >> 1;
      var1 |= var1 >> 2;
      var1 |= var1 >> 4;
      var1 |= var1 >> 8;
      var1 |= var1 >> 16;
      return var1 + 1;
   }

   public static double getRandomDoubleInRange(Random var0, double var1, double var3) {
      return var1 >= var3 ? var1 : var0.nextDouble() * (var3 - var1) + var1;
   }

   public static int parseIntWithDefault(String var0, int var1) {
      try {
         return Integer.parseInt(var0);
      } catch (Throwable var3) {
         return var1;
      }
   }

   public static double atan2(double var0, double var2) {
      double var4 = var2 * var2 + var0 * var0;
      if (Double.isNaN(var4)) {
         return Double.NaN;
      } else {
         boolean var6 = var0 < 0.0;
         if (var6) {
            var0 = -var0;
         }

         boolean var7 = var2 < 0.0;
         if (var7) {
            var2 = -var2;
         }

         boolean var8 = var0 > var2;
         if (var8) {
            double var9 = var2;
            var2 = var0;
            var0 = var9;
         }

         double var28 = func_181161_i(var4);
         var2 *= var28;
         var0 *= var28;
         double var11 = field_181163_d + var0;
         int var13 = (int)Double.doubleToRawLongBits(var11);
         double var14 = field_181164_e[var13];
         double var16 = field_181165_f[var13];
         double var18 = var11 - field_181163_d;
         double var20 = var0 * var16 - var2 * var18;
         double var22 = (6.0 + var20 * var20) * var20 * 0.16666666666666666;
         double var24 = var14 + var22;
         if (var8) {
            var24 = (Math.PI / 2) - var24;
         }

         if (var7) {
            var24 = Math.PI - var24;
         }

         if (var6) {
            var24 = -var24;
         }

         return var24;
      }
   }

   public static int normalizeAngle(int var0, int var1) {
      return (var0 % var1 + var1) % var1;
   }

   public static int abs_int(int var0) {
      return var0 >= 0 ? var0 : -var0;
   }

   public static long floor_double_long(double var0) {
      long var2 = (long)var0;
      return var0 < var2 ? var2 - 1L : var2;
   }

   public static int truncateDoubleToInt(double var0) {
      return (int)(var0 + 1024.0) - 1024;
   }

   public static int hsvToRGB(float var0, float var1, float var2) {
      int var3 = (int)(var0 * 6.0F) % 6;
      float var4 = var0 * 6.0F - var3;
      float var5 = var2 * (1.0F - var1);
      float var6 = var2 * (1.0F - var4 * var1);
      float var7 = var2 * (1.0F - (1.0F - var4) * var1);
      float var8;
      float var9;
      float var10;
      switch (var3) {
         case 0:
            var8 = var2;
            var9 = var7;
            var10 = var5;
            break;
         case 1:
            var8 = var6;
            var9 = var2;
            var10 = var5;
            break;
         case 2:
            var8 = var5;
            var9 = var2;
            var10 = var7;
            break;
         case 3:
            var8 = var5;
            var9 = var6;
            var10 = var2;
            break;
         case 4:
            var8 = var7;
            var9 = var5;
            var10 = var2;
            break;
         case 5:
            var8 = var2;
            var9 = var5;
            var10 = var6;
            break;
         default:
            throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + var0 + ", " + var1 + ", " + var2);
      }

      int var11 = clamp_int((int)(var8 * 255.0F), 0, 255);
      int var12 = clamp_int((int)(var9 * 255.0F), 0, 255);
      int var13 = clamp_int((int)(var10 * 255.0F), 0, 255);
      return var11 << 16 | var12 << 8 | var13;
   }

   public static float sqrt_double(double var0) {
      return (float)Math.sqrt(var0);
   }

   public static long getCoordinateRandom(int var0, int var1, int var2) {
      long var3 = var0 * 3129871 ^ var2 * 116129781L ^ var1;
      return var3 * var3 * 42317861L + var3 * 11L;
   }

   public static double clamp_double(double var0, double var2, double var4) {
      return var0 < var2 ? var2 : (var0 > var4 ? var4 : var0);
   }

   public static double parseDoubleWithDefault(String var0, double var1) {
      try {
         return Double.parseDouble(var0);
      } catch (Throwable var4) {
         return var1;
      }
   }

   public static int calculateLogBaseTwo(int var0) {
      return calculateLogBaseTwoDeBruijn(var0) - (isPowerOfTwo(var0) ? 0 : 1);
   }

   public static float randomFloatClamp(Random var0, float var1, float var2) {
      return var1 >= var2 ? var1 : var0.nextFloat() * (var2 - var1) + var1;
   }

   public static double parseDoubleWithDefaultAndMax(String var0, double var1, double var3) {
      return Math.max(var3, parseDoubleWithDefault(var0, var1));
   }

   static {
      for (int var0 = 0; var0 < 65536; var0++) {
         recoveredField1907[var0] = (float)Math.sin(var0 * Math.PI * 2.0 / 65536.0);
      }

      for (int var5 = 0; var5 < recoveredField1905.length; var5++) {
         recoveredField1905[var5] = MathUtils.roundToFloat(Math.sin(var5 * Math.PI * 2.0 / 4096.0));
      }

      recoveredField1914 = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
      field_181163_d = Double.longBitsToDouble(4805340802404319232L);
      field_181164_e = new double[257];
      field_181165_f = new double[257];

      for (int var6 = 0; var6 < 257; var6++) {
         double var1 = var6 / 256.0;
         double var3 = Math.asin(var1);
         field_181165_f[var6] = Math.cos(var3);
         field_181164_e[var6] = var3;
      }
   }

   public static boolean isPowerOfTwo(int var0) {
      return var0 != 0 && (var0 & var0 - 1) == 0;
   }

   public static int func_180188_d(int var0, int var1) {
      int var2 = (var0 & 0xFF0000) >> 16;
      int var3 = (var1 & 0xFF0000) >> 16;
      int var4 = (var0 & 0xFF00) >> 8;
      int var5 = (var1 & 0xFF00) >> 8;
      int var6 = (var0 & 0xFF) >> 0;
      int var7 = (var1 & 0xFF) >> 0;
      int var8 = (int)((float)var2 * var3 / 255.0F);
      int var9 = (int)((float)var4 * var5 / 255.0F);
      int var10 = (int)((float)var6 * var7 / 255.0F);
      return var0 & 0xFF000000 | var8 << 16 | var9 << 8 | var10;
   }

   public static double func_181161_i(double var0) {
      double var2 = 0.5 * var0;
      long var4 = Double.doubleToRawLongBits(var0);
      var4 = 6910469410427058090L - (var4 >> 1);
      var0 = Double.longBitsToDouble(var4);
      return var0 * (1.5 - var2 * var0 * var0);
   }

   public static int ceiling_double_int(double var0) {
      int var2 = (int)var0;
      return var0 > var2 ? var2 + 1 : var2;
   }

   public static int func_154353_e(double var0) {
      return (int)(var0 >= 0.0 ? var0 : -var0 + 1.0);
   }

   public static long getPositionRandom(Vec3i var0) {
      return getCoordinateRandom(var0.getX(), var0.getY(), var0.getZ());
   }

   public static int ceiling_float_int(float var0) {
      int var1 = (int)var0;
      return var0 > var1 ? var1 + 1 : var1;
   }

   public static int bucketInt(int var0, int var1) {
      return var0 < 0 ? -((-var0 - 1) / var1) - 1 : var0 / var1;
   }

   public static double average(long[] var0) {
      long var1 = 0L;

      for (long var6 : var0) {
         var1 += var6;
      }

      return (double)var1 / var0.length;
   }

   public static int getRandomIntegerInRange(Random var0, int var1, int var2) {
      return var1 >= var2 ? var1 : var0.nextInt(var2 - var1 + 1) + var1;
   }

   public static int floor_float(float var0) {
      int var1 = (int)var0;
      return var0 < var1 ? var1 - 1 : var1;
   }

   public static double func_181160_c(double var0, double var2, double var4) {
      return (var0 - var2) / (var4 - var2);
   }

   public static double func_181162_h(double var0) {
      return var0 - Math.floor(var0);
   }

   public static float sqrt_float(float var0) {
      return (float)Math.sqrt(var0);
   }

   public static int calculateLogBaseTwoDeBruijn(int var0) {
      var0 = isPowerOfTwo(var0) ? var0 : roundUpToPowerOfTwo(var0);
      return recoveredField1914[(int)(var0 * 125613361L >> 27) & 31];
   }

   public static int parseIntWithDefaultAndMax(String var0, int var1, int var2) {
      return Math.max(var2, parseIntWithDefault(var0, var1));
   }

   public static double wrapAngleTo180_double(double var0) {
      var0 %= 360.0;
      if (var0 >= 180.0) {
         var0 -= 360.0;
      }

      if (var0 < -180.0) {
         var0 += 360.0;
      }

      return var0;
   }

   public static int clamp_int(int var0, int var1, int var2) {
      return var0 < var1 ? var1 : (var0 > var2 ? var2 : var0);
   }

   public static int func_180183_b(float var0, float var1, float var2) {
      return func_180181_b(floor_float(var0 * 255.0F), floor_float(var1 * 255.0F), floor_float(var2 * 255.0F));
   }

   public static float wrapAngleTo180_float(float var0) {
      var0 %= 360.0F;
      if (var0 >= 180.0F) {
         var0 -= 360.0F;
      }

      if (var0 < -180.0F) {
         var0 += 360.0F;
      }

      return var0;
   }

   public static int floor_double(double var0) {
      int var2 = (int)var0;
      return var0 < var2 ? var2 - 1 : var2;
   }

   public static double abs_max(double var0, double var2) {
      if (var0 < 0.0) {
         var0 = -var0;
      }

      if (var2 < 0.0) {
         var2 = -var2;
      }

      return var0 > var2 ? var0 : var2;
   }

   public static UUID getRandomUuid(Random var0) {
      long var1 = var0.nextLong() & -61441L | 16384L;
      long var3 = var0.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
      return new UUID(var1, var3);
   }

   public static int roundUp(int var0, int var1) {
      if (var1 == 0) {
         return 0;
      } else if (var0 == 0) {
         return var1;
      } else {
         if (var0 < 0) {
            var1 *= -1;
         }

         int var2 = var0 % var1;
         return var2 == 0 ? var0 : var0 + var1 - var2;
      }
   }
}
