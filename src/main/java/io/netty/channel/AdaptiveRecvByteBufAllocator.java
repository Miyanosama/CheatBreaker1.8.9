package io.netty.channel;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import java.util.ArrayList;
import net.minecraft.util.ObjectIntIdentityMap;
import org.java_websocket.framing.CloseFrame;
import org.slf4j.helpers.BasicMarker;

public class AdaptiveRecvByteBufAllocator implements RecvByteBufAllocator {
   public static AdaptiveRecvByteBufAllocator DEFAULT;
   public int maxIndex;
   public static final int DEFAULT_INITIAL = 1024;
   public int initial;
   public static final int INDEX_DECREMENT = 1;
   public static int[] SIZE_TABLE;
   public static final int DEFAULT_MAXIMUM = 65536;
   public static final int INDEX_INCREMENT = 4;
   public static final int DEFAULT_MINIMUM = 64;
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

      for (int var1 = 16; var1 < 512; var1 += 16) {
         var0.add(Integer.valueOf(var1));
      }

      for (int var2 = 512; var2 > 0; var2 <<= 1) {
         var0.add(Integer.valueOf(var2));
      }

      SIZE_TABLE = new int[var0.size()];

      for (int var3 = 0; var3 < SIZE_TABLE.length; var3++) {
         SIZE_TABLE[var3] = (Integer)var0.get(var3);
      }

      DEFAULT = new AdaptiveRecvByteBufAllocator();
   }

   @Override
   public RecvByteBufAllocator.Handle newHandle() {
      return new AdaptiveRecvByteBufAllocator.HandleImpl(this.minIndex, this.maxIndex, this.initial);
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

   public static final class HandleImpl implements RecvByteBufAllocator.Handle {
      public int maxIndex;
      public boolean decreaseNow;
      public int nextReceiveBufferSize;
      public int index;
      public int minIndex;

      @Override
      public int guess() {
         return this.nextReceiveBufferSize;
      }

      @Override
      public void record(int var1) {
         if (var1 <= AdaptiveRecvByteBufAllocator.SIZE_TABLE[Math.max(0, this.index - 1 - 1)]) {
            if (this.decreaseNow) {
               this.index = Math.max(this.index - 1, this.minIndex);
               this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index];
               this.decreaseNow = false;
            } else {
               this.decreaseNow = true;
            }
         } else if (var1 >= this.nextReceiveBufferSize) {
            this.index = Math.min(this.index + 4, this.maxIndex);
            this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index];
            this.decreaseNow = false;
         }
      }

      @Override
      public ByteBuf allocate(ByteBufAllocator var1) {
         return var1.ioBuffer(this.nextReceiveBufferSize);
      }

      public HandleImpl(int var1, int var2, int var3) {
         this.minIndex = var1;
         this.maxIndex = var2;
         this.index = AdaptiveRecvByteBufAllocator.getSizeTableIndex(var3);
         this.nextReceiveBufferSize = AdaptiveRecvByteBufAllocator.SIZE_TABLE[this.index];
      }
   }
}
