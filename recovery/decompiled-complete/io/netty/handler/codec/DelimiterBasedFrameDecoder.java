package io.netty.handler.codec;

import com.cheatbreaker.client.nethandler.server.PacketCooldown;
import com.cheatbreaker.client.util.hologram.Hologram;
import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannelHandlerContext$10;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import net.minecraft.entity.projectile.EntityFireball;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$30;

public class DelimiterBasedFrameDecoder extends ByteToMessageDecoder {
   public LineBasedFrameDecoder lineBasedDecoder;
   public AbstractChannelHandlerContext$10 __junk2239178823541467582;
   public PacketCooldown __junk693212569697812879;
   public boolean failFast;
   public int maxFrameLength;
   public LogBrokerMonitor$30 __junk4066241412088687654;
   public ByteBuf[] delimiters;
   public int tooLongFrameLength;
   public boolean stripDelimiter;
   public Hologram __junk5592148240433006532;
   public EntityFireball __junk7629556253587330571;
   public boolean discardingTooLongFrame;

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      Object var4 = this.decode(var1, var2);
      if (var4 != null) {
         var3.add(var4);
      }
   }

   public DelimiterBasedFrameDecoder(int var1, boolean var2, ByteBuf var3) {
      this(var1, var2, true, var3);
   }

   public boolean isSubclass() {
      return this.getClass() != DelimiterBasedFrameDecoder.class;
   }

   public DelimiterBasedFrameDecoder(int var1, ByteBuf... var2) {
      this(var1, true, var2);
   }

   public static boolean isLineBased(ByteBuf[] var0) {
      if (var0.length != 2) {
         return false;
      } else {
         ByteBuf var1 = var0[0];
         ByteBuf var2 = var0[1];
         if (var1.capacity() < var2.capacity()) {
            var1 = var0[1];
            var2 = var0[0];
         }

         return var1.capacity() == 2 && var2.capacity() == 1 && var1.getByte(0) == 13 && var1.getByte(1) == 10 && var2.getByte(0) == 10;
      }
   }

   public DelimiterBasedFrameDecoder(int var1, ByteBuf var2) {
      this(var1, true, var2);
   }

   public DelimiterBasedFrameDecoder(int var1, boolean var2, ByteBuf... var3) {
      this(var1, var2, true, var3);
   }

   public DelimiterBasedFrameDecoder(int var1, boolean var2, boolean var3, ByteBuf var4) {
      this(var1, var2, var3, var4.slice(var4.readerIndex(), var4.readableBytes()));
   }

   public static int indexOf(ByteBuf var0, ByteBuf var1) {
      for (int var2 = var0.readerIndex(); var2 < var0.writerIndex(); var2++) {
         int var3 = var2;

         int var4;
         for (var4 = 0; var4 < var1.capacity() && var0.getByte(var3) == var1.getByte(var4); var4++) {
            if (++var3 == var0.writerIndex() && var4 != var1.capacity() - 1) {
               return -1;
            }
         }

         if (var4 == var1.capacity()) {
            return var2 - var0.readerIndex();
         }
      }

      return -1;
   }

   public static void validateDelimiter(ByteBuf var0) {
      if (var0 == null) {
         throw new NullPointerException("delimiter");
      } else if (!var0.isReadable()) {
         throw new IllegalArgumentException("empty delimiter");
      }
   }

   public void fail(long var1) {
      if (var1 > (-6091312611219078653L & 269570100L)) {
         throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + ": " + var1 + " - discarded");
      } else {
         throw new TooLongFrameException("frame length exceeds " + this.maxFrameLength + " - discarding");
      }
   }

   public DelimiterBasedFrameDecoder(int var1, boolean var2, boolean var3, ByteBuf... var4) {
      validateMaxFrameLength(var1);
      if (var4 == null) {
         throw new NullPointerException("delimiters");
      } else if (var4.length == 0) {
         throw new IllegalArgumentException("empty delimiters");
      } else {
         if (isLineBased(var4) && !this.isSubclass()) {
            this.lineBasedDecoder = new LineBasedFrameDecoder(var1, var2, var3);
            this.delimiters = null;
         } else {
            this.delimiters = new ByteBuf[var4.length];

            for (int var5 = 0; var5 < var4.length; var5++) {
               ByteBuf var6 = var4[var5];
               validateDelimiter(var6);
               this.delimiters[var5] = var6.slice(var6.readerIndex(), var6.readableBytes());
            }

            this.lineBasedDecoder = null;
         }

         this.maxFrameLength = var1;
         this.stripDelimiter = var2;
         this.failFast = var3;
      }
   }

   public static void validateMaxFrameLength(int var0) {
      if (var0 <= 0) {
         throw new IllegalArgumentException("maxFrameLength must be a positive integer: " + var0);
      }
   }

   public Object decode(ChannelHandlerContext var1, ByteBuf var2) {
      if (this.lineBasedDecoder != null) {
         return this.lineBasedDecoder.decode(var1, var2);
      } else {
         int var3 = Integer.MAX_VALUE;
         ByteBuf var4 = null;

         for (ByteBuf var8 : this.delimiters) {
            int var9 = indexOf(var2, var8);
            if (var9 >= 0 && var9 < var3) {
               var3 = var9;
               var4 = var8;
            }
         }

         if (var4 != null) {
            int var10 = var4.capacity();
            if (this.discardingTooLongFrame) {
               this.discardingTooLongFrame = false;
               var2.skipBytes(var3 + var10);
               int var12 = this.tooLongFrameLength;
               this.tooLongFrameLength = 0;
               if (!this.failFast) {
                  this.fail(var12);
               }

               return null;
            } else if (var3 > this.maxFrameLength) {
               var2.skipBytes(var3 + var10);
               this.fail(var3);
               return null;
            } else {
               ByteBuf var11;
               if (this.stripDelimiter) {
                  var11 = var2.readSlice(var3);
                  var2.skipBytes(var10);
               } else {
                  var11 = var2.readSlice(var3 + var10);
               }

               return var11.retain();
            }
         } else {
            if (!this.discardingTooLongFrame) {
               if (var2.readableBytes() > this.maxFrameLength) {
                  this.tooLongFrameLength = var2.readableBytes();
                  var2.skipBytes(var2.readableBytes());
                  this.discardingTooLongFrame = true;
                  if (this.failFast) {
                     this.fail(this.tooLongFrameLength);
                  }
               }
            } else {
               this.tooLongFrameLength = this.tooLongFrameLength + var2.readableBytes();
               var2.skipBytes(var2.readableBytes());
            }

            return null;
         }
      }
   }
}
