package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.PendingWriteQueue;
import io.netty.handler.codec.socks.SocksAuthResponse;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import junit.runner.BaseTestRunner;
import com.cheatbreaker.client.util.math.ScalarMath;

public class IovArray implements ChannelOutboundBuffer.MessageProcessor {
   public long memoryAddress;
   public static final boolean $assertionsDisabled = !IovArray.class.desiredAssertionStatus();
   public static int ADDRESS_SIZE = PlatformDependent.addressSize();
   public long size;
   public static int IOV_SIZE = 2 * ADDRESS_SIZE;
   public static int CAPACITY = Native.IOV_MAX * IovArray.IOV_SIZE;
   public static FastThreadLocal<IovArray> ARRAY = new FastThreadLocal<IovArray>() {

      public void onRemoval(IovArray var1) throws java.lang.Exception {
         PlatformDependent.freeMemory(var1.memoryAddress);
      }

      public IovArray initialValue() throws java.lang.Exception {
         return new IovArray();
      }
   };
   public int count;

   public long memoryAddress(int var1) {
      return this.memoryAddress + IOV_SIZE * var1;
   }

   public IovArray() {
      this.memoryAddress = PlatformDependent.allocateMemory(CAPACITY);
   }

   public int count() {
      return this.count;
   }

   public static IovArray get(ChannelOutboundBuffer var0) throws java.lang.Exception {
      IovArray var1 = ARRAY.get();
      var1.size = 0L;
      var1.count = 0;
      var0.forEachFlushedMessage(var1);
      return var1;
   }

   public boolean add(ByteBuf var1) {
      if (this.count == Native.IOV_MAX) {
         return false;
      } else {
         int var2 = var1.readableBytes();
         if (var2 == 0) {
            return true;
         } else {
            long var3 = var1.memoryAddress();
            int var5 = var1.readerIndex();
            long var6 = this.memoryAddress(this.count++);
            long var8 = var6 + ADDRESS_SIZE;
            if (ADDRESS_SIZE == 8) {
               PlatformDependent.putLong(var6, var3 + var5);
               PlatformDependent.putLong(var8, var2);
            } else {
               if (!$assertionsDisabled && ADDRESS_SIZE != 4) {
                  throw new AssertionError();
               }

               PlatformDependent.putInt(var6, (int)var3 + var5);
               PlatformDependent.putInt(var8, var2);
            }

            this.size += var2;
            return true;
         }
      }
   }

   public long processWritten(int var1, long var2) {
      long var4 = this.memoryAddress(var1);
      long var6 = var4 + ADDRESS_SIZE;
      if (ADDRESS_SIZE == 8) {
         long var12 = PlatformDependent.getLong(var6);
         if (var12 > var2) {
            long var13 = PlatformDependent.getLong(var4);
            PlatformDependent.putLong(var4, var13 + var2);
            PlatformDependent.putLong(var6, var12 - var2);
            return -1L;
         } else {
            return var12;
         }
      } else if (!$assertionsDisabled && ADDRESS_SIZE != 4) {
         throw new AssertionError();
      } else {
         long var8 = PlatformDependent.getInt(var6);
         if (var8 > var2) {
            int var10 = PlatformDependent.getInt(var4);
            PlatformDependent.putInt(var4, (int)(var10 + var2));
            PlatformDependent.putInt(var6, (int)(var8 - var2));
            return -1L;
         } else {
            return var8;
         }
      }
   }

   @Override
   public boolean processMessage(Object var1) throws java.lang.Exception {
      return var1 instanceof ByteBuf && this.add((ByteBuf)var1);
   }

   public long size() {
      return this.size;
   }
}
