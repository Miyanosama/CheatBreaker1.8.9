package io.netty.channel;

import java.util.ArrayList;

public class AdaptiveRecvByteBufAllocator implements RecvByteBufAllocator {
   public static AdaptiveRecvByteBufAllocator DEFAULT;
   public int maxIndex;
   public static int DEFAULT_INITIAL;
   public int initial;
   public static int INDEX_DECREMENT;
   public static int[] SIZE_TABLE;
   public static int DEFAULT_MAXIMUM;
   public static int INDEX_INCREMENT;
   public static int DEFAULT_MINIMUM;
   public int minIndex;

   public AdaptiveRecvByteBufAllocator() {
      this(64, 1024, 65536);
   }

   public AdaptiveRecvByteBufAllocator(int var1, int var2, int var3) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("minimum: " + var1);
      } else if (var2 < var1) {
         throw new IllegalArgumentException("initial: " + var2);
      } else if (var3 < var2) {
         throw new IllegalArgumentException("maximum: " + var3);
      } else {
         int var4 = getSizeTableIndex(var1);
         if (SIZE_TABLE[var4] < var1) {
            this.minIndex = var4 + 1;
         } else {
            this.minIndex = var4;
         }

         int var5 = getSizeTableIndex(var3);
         if (SIZE_TABLE[var5] > var3) {
            this.maxIndex = var5 - 1;
         } else {
            this.maxIndex = var5;
         }

         this.initial = var2;
      }
   }

   static {
      ArrayList var0 = new ArrayList();

      for (byte var1 = 16; var1 < 512; var1 += 16) {
         var0.add(Integer.valueOf(var1));
      }

      for (short var2 = 512; var2 > 0; var2 <<= 1) {
         var0.add(Integer.valueOf(var2));
      }

      SIZE_TABLE = new int[var0.size()];

      for (int var3 = 0; var3 < SIZE_TABLE.length; var3++) {
         SIZE_TABLE[var3] = (Integer)var0.get(var3);
      }

      DEFAULT = new AdaptiveRecvByteBufAllocator();
   }

   @Override
   public RecvByteBufAllocator$Handle newHandle() {
      return new AdaptiveRecvByteBufAllocator$HandleImpl(this.minIndex, this.maxIndex, this.initial);
   }

   public static int getSizeTableIndex(int var0) {
      int var1 = 0;
      int var2 = SIZE_TABLE.length - 1;

      while (var2 >= var1) {
         if (var2 == var1) {
            return var2;
         }

         int var3 = var1 + var2 >>> 1;
         int var4 = SIZE_TABLE[var3];
         int var5 = SIZE_TABLE[var3 + 1];
         if (var0 > var5) {
            var1 = var3 + 1;
         } else {
            if (var0 >= var4) {
               if (var0 == var4) {
                  return var3;
               }

               return var3 + 1;
            }

            var2 = var3 - 1;
         }
      }

      return var1;
   }
}
