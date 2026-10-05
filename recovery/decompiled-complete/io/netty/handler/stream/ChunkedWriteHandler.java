package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import net.optifine.expr.ParametersVariable;
import net.optifine.model.QuadBounds$1;

public class ChunkedWriteHandler extends ChannelDuplexHandler {
   public Queue<ChunkedWriteHandler$PendingWrite> queue = new ArrayDeque<>();
   public QuadBounds$1 __junk8873500627827239301;
   public ChunkedWriteHandler$PendingWrite currentWrite;
   public ParametersVariable __junk8081119152260077021;
   public volatile ChannelHandlerContext ctx;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ChunkedWriteHandler.class);

   @Override
   public void channelWritabilityChanged(ChannelHandlerContext var1) {
      if (var1.channel().isWritable()) {
         this.doFlush(var1);
      }

      var1.fireChannelWritabilityChanged();
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.queue.add(new ChunkedWriteHandler$PendingWrite(var2, var3));
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.doFlush(var1);
      super.channelInactive(var1);
   }

   public void resumeTransfer() {
      ChannelHandlerContext var1 = this.ctx;
      if (var1 != null) {
         if (var1.executor().inEventLoop()) {
            try {
               this.doFlush(var1);
            } catch (Exception var3) {
               if (logger.isWarnEnabled()) {
                  logger.warn("Unexpected exception while sending chunks.", (Throwable)var3);
               }
            }
         } else {
            var1.executor().execute(new ChunkedWriteHandler$1(this, var1));
         }
      }
   }

   public void doFlush(ChannelHandlerContext var1) {
      Channel var2 = var1.channel();
      if (!var2.isActive()) {
         this.discard(null);
      } else {
         while (var2.isWritable()) {
            if (this.currentWrite == null) {
               this.currentWrite = this.queue.poll();
            }

            if (this.currentWrite == null) {
               break;
            }

            ChunkedWriteHandler$PendingWrite var3 = this.currentWrite;
            Object var4 = var3.msg;
            if (var4 instanceof ChunkedInput) {
               ChunkedInput var5 = (ChunkedInput)var4;
               Object var8 = null;

               boolean var6;
               boolean var7;
               try {
                  var8 = var5.readChunk(var1);
                  var6 = var5.isEndOfInput();
                  if (var8 == null) {
                     var7 = !var6;
                  } else {
                     var7 = false;
                  }
               } catch (Throwable var11) {
                  this.currentWrite = null;
                  if (var8 != null) {
                     ReferenceCountUtil.release(var8);
                  }

                  var3.fail(var11);
                  closeInput(var5);
                  break;
               }

               if (var7) {
                  break;
               }

               if (var8 == null) {
                  var8 = Unpooled.EMPTY_BUFFER;
               }

               int var9 = amount(var8);
               ChannelFuture var10 = var1.write(var8);
               if (var6) {
                  this.currentWrite = null;
                  var10.addListener(new ChunkedWriteHandler$2(this, var3, var9, var5));
               } else if (var2.isWritable()) {
                  var10.addListener(new ChunkedWriteHandler$3(this, var4, var3, var9));
               } else {
                  var10.addListener(new ChunkedWriteHandler$4(this, var4, var3, var9, var2));
               }
            } else {
               var1.write(var4, var3.promise);
               this.currentWrite = null;
            }

            var1.flush();
            if (!var2.isActive()) {
               this.discard(new ClosedChannelException());
               return;
            }
         }
      }
   }

   public static void closeInput(ChunkedInput<?> var0) {
      try {
         var0.close();
      } catch (Throwable var2) {
         if (logger.isWarnEnabled()) {
            logger.warn("Failed to close a chunked input.", var2);
         }
      }
   }

   public void discard(Throwable var1) {
      while (true) {
         ChunkedWriteHandler$PendingWrite var2 = this.currentWrite;
         if (this.currentWrite == null) {
            var2 = this.queue.poll();
         } else {
            this.currentWrite = null;
         }

         if (var2 == null) {
            return;
         }

         Object var3 = var2.msg;
         if (var3 instanceof ChunkedInput) {
            ChunkedInput var4 = (ChunkedInput)var3;

            try {
               if (!var4.isEndOfInput()) {
                  if (var1 == null) {
                     var1 = new ClosedChannelException();
                  }

                  var2.fail((Throwable)var1);
               } else {
                  var2.success();
               }

               closeInput(var4);
            } catch (Exception var6) {
               var2.fail(var6);
               logger.warn(ChunkedInput.class.getSimpleName() + ".isEndOfInput() failed", (Throwable)var6);
               closeInput(var4);
            }
         } else {
            if (var1 == null) {
               var1 = new ClosedChannelException();
            }

            var2.fail((Throwable)var1);
         }
      }
   }

   @Override
   public void flush(ChannelHandlerContext var1) {
      Channel var2 = var1.channel();
      if (var2.isWritable() || !var2.isActive()) {
         this.doFlush(var1);
      }
   }

   public static int amount(Object var0) {
      if (var0 instanceof ByteBuf) {
         return ((ByteBuf)var0).readableBytes();
      } else {
         return var0 instanceof ByteBufHolder ? ((ByteBufHolder)var0).content().readableBytes() : 1;
      }
   }

   public ChunkedWriteHandler() {
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      this.ctx = var1;
   }

   public ChunkedWriteHandler(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("maxPendingWrites: " + var1 + " (expected: > 0)");
      }
   }
}
