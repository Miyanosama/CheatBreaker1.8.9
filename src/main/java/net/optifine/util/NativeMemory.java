package net.optifine.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.src.Config;

public class NativeMemory {
   public static LongSupplier bufferAllocatedSupplier = makeLongSupplier(
      new String[][]{
         {"sun.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"},
         {"jdk.internal.misc.SharedSecrets", "getJavaNioAccess", "getDirectBufferPool", "getMemoryUsed"}
      }
   );
   public static LongSupplier bufferMaximumSupplier = makeLongSupplier(
      new String[][]{{"sun.misc.VM", "maxDirectMemory"}, {"jdk.internal.misc.VM", "maxDirectMemory"}}
   );

   public static LongSupplier makeLongSupplier(String[][] var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < var0.length; var2++) {
         String[] var3 = var0[var2];

         try {
            return makeLongSupplier(var3);
         } catch (Throwable var5) {
            var1.add(var5);
         }
      }

      for (Throwable var7 : (Iterable<Throwable>)(Iterable<?>)(var1)) {
         Config.warn("" + var7.getClass().getName() + ": " + var7.getMessage());
      }

      return null;
   }

   public static long method_29544() {
      return bufferMaximumSupplier == null ? -1L : bufferMaximumSupplier.getAsLong();
   }

   public static long method_29547() {
      return bufferAllocatedSupplier == null ? -1L : bufferAllocatedSupplier.getAsLong();
   }

   public static LongSupplier makeLongSupplier(String[] var0) throws java.lang.Exception {
      if (var0.length < 2) {
         return null;
      } else {
         Class var1 = Class.forName(var0[0]);
         Method var2 = var1.getMethod(var0[1]);
         var2.setAccessible(true);
         Object var3 = null;

         for (int var4 = 2; var4 < var0.length; var4++) {
            String var5 = var0[var4];
            var3 = var2.invoke(var3);
            var2 = var3.getClass().getMethod(var5);
            var2.setAccessible(true);
         }

         final Method memoryMethod = var2;
         final Object memoryPool = var3;
         return new LongSupplier() {
            public boolean disabled = false;

            @Override
            public long getAsLong() {
               if (this.disabled) {
                  return -1L;
               } else {
                  try {
                     return (Long)memoryMethod.invoke(memoryPool);
                  } catch (Throwable var2x) {
                     Config.warn("" + var2x.getClass().getName() + ": " + var2x.getMessage());
                     this.disabled = true;
                     return -1L;
                  }
               }
            }
         };
      }
   }
}
