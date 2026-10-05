package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import net.minecraft.nbt.NBTException;

public class LineBasedFrameDecoder extends ByteToMessageDecoder {
   public boolean stripDelimiter;
   public boolean failFast;
   public int maxLength;
   public boolean discarding;
   public int discardedBytes;

   public static int findEndOfLine(ByteBuf var0) {
      int var1 = var0.writerIndex();

      for (int var2 = var0.readerIndex(); var2 < var1; var2++) {
         byte var3 = var0.getByte(var2);
         if (var3 == 10) {
            return var2;
         }

         if (var3 == 13 && var2 < var1 - 1 && var0.getByte(var2 + 1) == 10) {
            return var2;
         }
      }

      return -1;
   }

   public Object decode(ChannelHandlerContext var1, ByteBuf var2) throws java.lang.Exception {
      int var3 = findEndOfLine(var2);
      if (!this.discarding) {
         if (var3 >= 0) {
            int var9 = var3 - var2.readerIndex();
            int var6 = var2.getByte(var3) == 13 ? 2 : 1;
            if (var9 > this.maxLength) {
               var2.readerIndex(var3 + var6);
               this.fail(var1, var9);
               return null;
            } else {
               ByteBuf var8;
               if (this.stripDelimiter) {
                  var8 = var2.readSlice(var9);
                  var2.skipBytes(var6);
               } else {
                  var8 = var2.readSlice(var9 + var6);
               }

               return var8.retain();
            }
         } else {
            int var7 = var2.readableBytes();
            if (var7 > this.maxLength) {
               this.discardedBytes = var7;
               var2.readerIndex(var2.writerIndex());
               this.discarding = true;
               if (this.failFast) {
                  this.fail(var1, "over " + this.discardedBytes);
               }
            }

            return null;
         }
      } else {
         if (var3 >= 0) {
            int var4 = this.discardedBytes + var3 - var2.readerIndex();
            int var5 = var2.getByte(var3) == 13 ? 2 : 1;
            var2.readerIndex(var3 + var5);
            this.discardedBytes = 0;
            this.discarding = false;
            if (!this.failFast) {
               this.fail(var1, var4);
            }
         } else {
            this.discardedBytes = var2.readableBytes();
            var2.readerIndex(var2.writerIndex());
         }

         return null;
      }
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      Object var4 = this.decode(var1, var2);
      if (var4 != null) {
         var3.add(var4);
      }
   }

   public void fail(ChannelHandlerContext var1, int var2) {
      this.fail(var1, String.valueOf(var2));
   }

   public void fail(ChannelHandlerContext var1, String var2) {
      var1.fireExceptionCaught(new TooLongFrameException("frame length (" + var2 + ") exceeds the allowed maximum (" + this.maxLength + ')'));
   }

   public LineBasedFrameDecoder(int var1) {
      this(var1, true, false);
   }

   public LineBasedFrameDecoder(int var1, boolean var2, boolean var3) {
      this.maxLength = var1;
      this.failFast = var3;
      this.stripDelimiter = var2;
   }
}
