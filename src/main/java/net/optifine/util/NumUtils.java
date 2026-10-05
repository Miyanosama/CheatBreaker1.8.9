package net.optifine.util;

public class NumUtils {
   public static float limit(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : (var0 > var2 ? var2 : var0);
   }

   public static int mod(int var0, int var1) {
      int var2 = var0 % var1;
      if (var2 < 0) {
         var2 += var1;
      }

      return var2;
   }
}
