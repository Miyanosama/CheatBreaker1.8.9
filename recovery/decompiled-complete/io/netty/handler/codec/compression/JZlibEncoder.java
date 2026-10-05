package io.netty.handler.codec.compression;

import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.JZlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufProcessor$7;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.EmptyArrays;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import recovered.unidentified.UnidentifiedClass0798;

public class JZlibEncoder extends ZlibEncoder {
   public UnidentifiedClass0798 __junk5407824686402362651;
   public ByteBufProcessor$7 __junk825685706702516960;
   public Deflater z = new Deflater();
   public VertexBuffer __junk2468411661009225666;
   public int wrapperOverhead;
   public volatile ChannelHandlerContext ctx;
   public volatile boolean finished;

   public ChannelFuture finishEncode(ChannelHandlerContext var1, ChannelPromise var2) {
      if (this.finished) {
         var2.setSuccess();
         return var2;
      } else {
         this.finished = true;

         ChannelPromise var6;
         try {
            this.z.next_in = EmptyArrays.EMPTY_BYTES;
            this.z.next_in_index = 0;
            this.z.avail_in = 0;
            byte[] var4 = new byte[32];
            this.z.next_out = var4;
            this.z.next_out_index = 0;
            this.z.avail_out = var4.length;
            int var5 = this.z.deflate(4);
            if (var5 == 0 || var5 == 1) {
               ByteBuf var3;
               if (this.z.next_out_index != 0) {
                  var3 = Unpooled.wrappedBuffer(var4, 0, this.z.next_out_index);
               } else {
                  var3 = Unpooled.EMPTY_BUFFER;
               }

               return var1.writeAndFlush(var3, var2);
            }

            var2.setFailure(ZlibUtil.deflaterException(this.z, "compression failure", var5));
            var6 = var2;
         } finally {
            this.z.deflateEnd();
            this.z.next_in = null;
            this.z.next_out = null;
         }

         return var6;
      }
   }

   public JZlibEncoder(ZlibWrapper var1, int var2, int var3, int var4) {
      if (var2 < 0 || var2 > 9) {
         throw new IllegalArgumentException("compressionLevel: " + var2 + " (expected: 0-9)");
      } else if (var3 < 9 || var3 > 15) {
         throw new IllegalArgumentException("windowBits: " + var3 + " (expected: 9-15)");
      } else if (var4 < 1 || var4 > 9) {
         throw new IllegalArgumentException("memLevel: " + var4 + " (expected: 1-9)");
      } else if (var1 == null) {
         throw new NullPointerException("wrapper");
      } else if (var1 == ZlibWrapper.ZLIB_OR_NONE) {
         throw new IllegalArgumentException("wrapper '" + ZlibWrapper.ZLIB_OR_NONE + "' is not " + "allowed for compression.");
      } else {
         int var5 = this.z.init(var2, var3, var4, ZlibUtil.convertWrapperType(var1));
         if (var5 != 0) {
            ZlibUtil.fail(this.z, "initialization failure", var5);
         }

         this.wrapperOverhead = ZlibUtil.wrapperOverhead(var1);
      }
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) {
      ChannelFuture var3 = this.finishEncode(var1, var1.newPromise());
      var3.addListener(new JZlibEncoder$2(this, var1, var2));
      if (!var3.isDone()) {
         var1.executor().schedule(new JZlibEncoder$3(this, var1, var2), 34160650L & -3816060420871677397L, TimeUnit.SECONDS);
      }
   }

   @Override
   public boolean isClosed() {
      return this.finished;
   }

   @Override
   public ChannelFuture close(ChannelPromise var1) {
      ChannelHandlerContext var2 = this.ctx();
      EventExecutor var3 = var2.executor();
      if (var3.inEventLoop()) {
         return this.finishEncode(var2, var1);
      } else {
         ChannelPromise var4 = var2.newPromise();
         var3.execute(new JZlibEncoder$1(this, var4, var1));
         return var4;
      }
   }

   public JZlibEncoder(int var1) {
      this(ZlibWrapper.ZLIB, var1);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      this.ctx = var1;
   }

   public JZlibEncoder(int var1, byte[] var2) {
      this(var1, 15, 8, var2);
   }

   public void encode(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) {
      if (!this.finished) {
         try {
            int var4 = var2.readableBytes();
            boolean var5 = var2.hasArray();
            this.z.avail_in = var4;
            if (var5) {
               this.z.next_in = var2.array();
               this.z.next_in_index = var2.arrayOffset() + var2.readerIndex();
            } else {
               byte[] var6 = new byte[var4];
               var2.getBytes(var2.readerIndex(), var6);
               this.z.next_in = var6;
               this.z.next_in_index = 0;
            }

            int var18 = this.z.next_in_index;
            int var7 = (int)Math.ceil(var4 * 1.001) + 12 + this.wrapperOverhead;
            var3.ensureWritable(var7);
            this.z.avail_out = var7;
            this.z.next_out = var3.array();
            this.z.next_out_index = var3.arrayOffset() + var3.writerIndex();
            int var8 = this.z.next_out_index;

            int var9;
            try {
               var9 = this.z.deflate(2);
            } finally {
               var2.skipBytes(this.z.next_in_index - var18);
            }

            if (var9 != 0) {
               ZlibUtil.fail(this.z, "compression failure", var9);
            }

            int var10 = this.z.next_out_index - var8;
            if (var10 > 0) {
               var3.writerIndex(var3.writerIndex() + var10);
            }
         } finally {
            this.z.next_in = null;
            this.z.next_out = null;
         }
      }
   }

   @Override
   public ChannelFuture close() {
      return this.close(this.ctx().channel().newPromise());
   }

   public JZlibEncoder() {
      this(6);
   }

   public JZlibEncoder(int var1, int var2, int var3, byte[] var4) {
      if (var1 < 0 || var1 > 9) {
         throw new IllegalArgumentException("compressionLevel: " + var1 + " (expected: 0-9)");
      } else if (var2 < 9 || var2 > 15) {
         throw new IllegalArgumentException("windowBits: " + var2 + " (expected: 9-15)");
      } else if (var3 < 1 || var3 > 9) {
         throw new IllegalArgumentException("memLevel: " + var3 + " (expected: 1-9)");
      } else if (var4 == null) {
         throw new NullPointerException("dictionary");
      } else {
         int var5 = this.z.deflateInit(var1, var2, var3, JZlib.W_ZLIB);
         if (var5 != 0) {
            ZlibUtil.fail(this.z, "initialization failure", var5);
         } else {
            var5 = this.z.deflateSetDictionary(var4, var4.length);
            if (var5 != 0) {
               ZlibUtil.fail(this.z, "failed to set the dictionary", var5);
            }
         }

         this.wrapperOverhead = ZlibUtil.wrapperOverhead(ZlibWrapper.ZLIB);
      }
   }

   public ChannelHandlerContext ctx() {
      ChannelHandlerContext var1 = this.ctx;
      if (var1 == null) {
         throw new IllegalStateException("not added to a pipeline");
      } else {
         return var1;
      }
   }

   public JZlibEncoder(ZlibWrapper var1) {
      this(var1, 6);
   }

   public JZlibEncoder(byte[] var1) {
      this(6, var1);
   }

   public JZlibEncoder(ZlibWrapper var1, int var2) {
      this(var1, var2, 15, 8);
   }
}
