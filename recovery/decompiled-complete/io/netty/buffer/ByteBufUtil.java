package io.netty.buffer;

import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpMessage;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Locale;
import net.minecraft.stats.StatBase$2;
import net.minecraft.tileentity.TileEntityBeacon;
import org.java_websocket.drafts.Draft_6455;

public class ByteBufUtil {
   public static int THREAD_LOCAL_BUFFER_SIZE;
   public Draft_6455 __junk6556473454500869239;
   public HttpObjectAggregator$AggregatedFullHttpMessage __junk1352414260485544491;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ByteBufUtil.class);
   public StatBase$2 __junk1632357803456280476;
   public static char[] HEXDUMP_TABLE = new char[1024];
   public static ByteBufAllocator DEFAULT_ALLOCATOR;
   public TileEntityBeacon __junk2962766503982957877;

   public static int swapMedium(int var0) {
      int var1 = var0 << 16 & 0xFF0000 | var0 & 0xFF00 | var0 >>> 16 & 0xFF;
      if ((var1 & 8388608) != 0) {
         var1 |= -16777216;
      }

      return var1;
   }

   static {
      char[] var0 = "0123456789abcdef".toCharArray();

      for (int var1 = 0; var1 < 256; var1++) {
         HEXDUMP_TABLE[var1 << 1] = var0[var1 >>> 4 & 15];
         HEXDUMP_TABLE[(var1 << 1) + 1] = var0[var1 & 15];
      }

      String var3 = SystemPropertyUtil.get("io.netty.allocator.type", "unpooled").toLowerCase(Locale.US).trim();
      Object var2;
      if ("unpooled".equals(var3)) {
         var2 = UnpooledByteBufAllocator.DEFAULT;
         logger.debug("-Dio.netty.allocator.type: {}", var3);
      } else if ("pooled".equals(var3)) {
         var2 = PooledByteBufAllocator.DEFAULT;
         logger.debug("-Dio.netty.allocator.type: {}", var3);
      } else {
         var2 = UnpooledByteBufAllocator.DEFAULT;
         logger.debug("-Dio.netty.allocator.type: unpooled (unknown: {})", var3);
      }

      DEFAULT_ALLOCATOR = (ByteBufAllocator)var2;
      THREAD_LOCAL_BUFFER_SIZE = SystemPropertyUtil.getInt("io.netty.threadLocalDirectBufferSize", 65536);
      logger.debug("-Dio.netty.threadLocalDirectBufferSize: {}", THREAD_LOCAL_BUFFER_SIZE);
   }

   public static int hashCode(ByteBuf var0) {
      int var1 = var0.readableBytes();
      int var2 = var1 >>> 2;
      int var3 = var1 & 3;
      int var4 = 1;
      int var5 = var0.readerIndex();
      if (var0.order() == ByteOrder.BIG_ENDIAN) {
         for (int var6 = var2; var6 > 0; var6--) {
            var4 = 31 * var4 + var0.getInt(var5);
            var5 += 4;
         }
      } else {
         for (int var7 = var2; var7 > 0; var7--) {
            var4 = 31 * var4 + swapInt(var0.getInt(var5));
            var5 += 4;
         }
      }

      for (int var8 = var3; var8 > 0; var8--) {
         var4 = 31 * var4 + var0.getByte(var5++);
      }

      if (var4 == 0) {
         var4 = 1;
      }

      return var4;
   }

   public static String decodeString(ByteBuffer var0, Charset var1) {
      CharsetDecoder var2 = CharsetUtil.getDecoder(var1);
      CharBuffer var3 = CharBuffer.allocate((int)((double)var0.remaining() * var2.maxCharsPerByte()));

      try {
         CoderResult var4 = var2.decode(var0, var3, true);
         if (!var4.isUnderflow()) {
            var4.throwException();
         }

         var4 = var2.flush(var3);
         if (!var4.isUnderflow()) {
            var4.throwException();
         }
      } catch (CharacterCodingException var5) {
         throw new IllegalStateException(var5);
      }

      return ((Buffer)var3).flip().toString();
   }

   public static int swapInt(int var0) {
      return Integer.reverseBytes(var0);
   }

   public static long swapLong(long var0) {
      return Long.reverseBytes(var0);
   }

   public static short swapShort(short var0) {
      return Short.reverseBytes(var0);
   }

   public static int lastIndexOf(ByteBuf var0, int var1, int var2, byte var3) {
      var1 = Math.min(var1, var0.capacity());
      if (var1 >= 0 && var0.capacity() != 0) {
         for (int var4 = var1 - 1; var4 >= var2; var4--) {
            if (var0.getByte(var4) == var3) {
               return var4;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static String hexDump(ByteBuf var0, int var1, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException("length: " + var2);
      } else if (var2 == 0) {
         return "";
      } else {
         int var3 = var1 + var2;
         char[] var4 = new char[var2 << 1];
         int var5 = var1;

         for (byte var6 = 0; var5 < var3; var6 += 2) {
            System.arraycopy(HEXDUMP_TABLE, var0.getUnsignedByte(var5) << 1, var4, var6, 2);
            var5++;
         }

         return new String(var4);
      }
   }

   public static int firstIndexOf(ByteBuf var0, int var1, int var2, byte var3) {
      var1 = Math.max(var1, 0);
      if (var1 < var2 && var0.capacity() != 0) {
         for (int var4 = var1; var4 < var2; var4++) {
            if (var0.getByte(var4) == var3) {
               return var4;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   public static ByteBuf encodeString0(ByteBufAllocator var0, boolean var1, CharBuffer var2, Charset var3) {
      CharsetEncoder var4 = CharsetUtil.getEncoder(var3);
      int var5 = (int)((double)var2.remaining() * var4.maxBytesPerChar());
      boolean var6 = true;
      ByteBuf var7;
      if (var1) {
         var7 = var0.heapBuffer(var5);
      } else {
         var7 = var0.buffer(var5);
      }

      ByteBuf var11;
      try {
         ByteBuffer var8 = var7.internalNioBuffer(0, var5);
         int var9 = var8.position();
         CoderResult var10 = var4.encode(var2, var8, true);
         if (!var10.isUnderflow()) {
            var10.throwException();
         }

         var10 = var4.flush(var8);
         if (!var10.isUnderflow()) {
            var10.throwException();
         }

         var7.writerIndex(var7.writerIndex() + var8.position() - var9);
         var6 = false;
         var11 = var7;
      } catch (CharacterCodingException var15) {
         throw new IllegalStateException(var15);
      } finally {
         if (var6) {
            var7.release();
         }
      }

      return var11;
   }

   public static ByteBuf readBytes(ByteBufAllocator var0, ByteBuf var1, int var2) {
      boolean var3 = true;
      ByteBuf var4 = var0.buffer(var2);

      ByteBuf var5;
      try {
         var1.readBytes(var4);
         var3 = false;
         var5 = var4;
      } finally {
         if (var3) {
            var4.release();
         }
      }

      return var5;
   }

   public static boolean equals(ByteBuf var0, ByteBuf var1) {
      int var2 = var0.readableBytes();
      if (var2 != var1.readableBytes()) {
         return false;
      } else {
         int var3 = var2 >>> 3;
         int var4 = var2 & 7;
         int var5 = var0.readerIndex();
         int var6 = var1.readerIndex();
         if (var0.order() == var1.order()) {
            for (int var7 = var3; var7 > 0; var7--) {
               if (var0.getLong(var5) != var1.getLong(var6)) {
                  return false;
               }

               var5 += 8;
               var6 += 8;
            }
         } else {
            for (int var8 = var3; var8 > 0; var8--) {
               if (var0.getLong(var5) != swapLong(var1.getLong(var6))) {
                  return false;
               }

               var5 += 8;
               var6 += 8;
            }
         }

         for (int var9 = var4; var9 > 0; var9--) {
            if (var0.getByte(var5) != var1.getByte(var6)) {
               return false;
            }

            var5++;
            var6++;
         }

         return true;
      }
   }

   public static ByteBuf encodeString(ByteBufAllocator var0, CharBuffer var1, Charset var2) {
      return encodeString0(var0, false, var1, var2);
   }

   public static int compare(ByteBuf var0, ByteBuf var1) {
      int var2 = var0.readableBytes();
      int var3 = var1.readableBytes();
      int var4 = Math.min(var2, var3);
      int var5 = var4 >>> 2;
      int var6 = var4 & 3;
      int var7 = var0.readerIndex();
      int var8 = var1.readerIndex();
      if (var0.order() == var1.order()) {
         for (int var9 = var5; var9 > 0; var9--) {
            long var10 = var0.getUnsignedInt(var7);
            long var12 = var1.getUnsignedInt(var8);
            if (var10 > var12) {
               return 1;
            }

            if (var10 < var12) {
               return -1;
            }

            var7 += 4;
            var8 += 4;
         }
      } else {
         for (int var14 = var5; var14 > 0; var14--) {
            long var16 = var0.getUnsignedInt(var7);
            long var18 = swapInt(var1.getInt(var8)) & 4294967295L & 8285754768895246335L;
            if (var16 > var18) {
               return 1;
            }

            if (var16 < var18) {
               return -1;
            }

            var7 += 4;
            var8 += 4;
         }
      }

      for (int var15 = var6; var15 > 0; var15--) {
         short var17 = var0.getUnsignedByte(var7);
         short var11 = var1.getUnsignedByte(var8);
         if (var17 > var11) {
            return 1;
         }

         if (var17 < var11) {
            return -1;
         }

         var7++;
         var8++;
      }

      return var2 - var3;
   }

   public static int indexOf(ByteBuf var0, int var1, int var2, byte var3) {
      return var1 <= var2 ? firstIndexOf(var0, var1, var2, var3) : lastIndexOf(var0, var1, var2, var3);
   }

   public static String hexDump(ByteBuf var0) {
      return hexDump(var0, var0.readerIndex(), var0.readableBytes());
   }

   public static ByteBuf threadLocalDirectBuffer() {
      if (THREAD_LOCAL_BUFFER_SIZE <= 0) {
         return null;
      } else {
         return (ByteBuf)(PlatformDependent.hasUnsafe()
            ? ByteBufUtil$ThreadLocalUnsafeDirectByteBuf.newInstance()
            : ByteBufUtil$ThreadLocalDirectByteBuf.newInstance());
      }
   }
}
