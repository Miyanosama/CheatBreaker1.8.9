package net.minecraft.util;

public class IntegerCache {
   public static Integer[] CACHE = new Integer[65535];

   static {
      int var0 = 0;

      for (int var1 = CACHE.length; var0 < var1; var0++) {
         CACHE[var0] = var0;
      }
   }

   public static Integer getInteger(int var0) {
      return var0 >= 0 && var0 < CACHE.length ? CACHE[var0] : new Integer(var0);
   }
}
