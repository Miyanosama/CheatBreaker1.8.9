package io.netty.handler.stream;

import com.cheatbreaker.client.module.type.NumberHudModule;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.handler.ssl.NotSslRecordException;
import io.netty.util.HashedWheelTimer;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import junit.runner.BaseTestRunner;
import net.minecraft.client.stream.ChatController;
import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.enchantment.EnchantmentOxygen;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.storage.SaveHandlerMP;
import net.optifine.expr.ParametersVariable;
import net.optifine.reflect.ReflectorConstructor;
import org.apache.log4j.SimpleLayout;
import net.minecraft.client.renderer.entity.RenderTNTPrimed;

public class ChunkedWriteHandler extends ChannelDuplexHandler {
   public Queue<ChunkedWriteHandler.PendingWrite> queue = new ArrayDeque<>();
   public ChunkedWriteHandler.PendingWrite currentWrite;
   public volatile ChannelHandlerContext ctx;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ChunkedWriteHandler.class);

   @Override
   public void channelWritabilityChanged(ChannelHandlerContext var1) throws java.lang.Exception {
      if (var1.channel().isWritable()) {
         this.doFlush(var1);
      }

      var1.fireChannelWritabilityChanged();
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      this.queue.add(new ChunkedWriteHandler.PendingWrite(var2, var3));
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      this.doFlush(var1);
      super.channelInactive(var1);
   }

   public void resumeTransfer() {
      final ChannelHandlerContext var1 = this.ctx;
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
            var1.executor().execute(new Runnable() {

               @Override
               public void run() {
                  try {
                     ChunkedWriteHandler.this.doFlush(var1);
                  } catch (Exception var2) {
                     if (ChunkedWriteHandler.logger.isWarnEnabled()) {
                        ChunkedWriteHandler.logger.warn("Unexpected exception while sending chunks.", (Throwable)var2);
                     }
                  }
               }
            });
         }
      }
   }

   public void doFlush(ChannelHandlerContext var1) throws java.lang.Exception {
      final Channel var2 = var1.channel();
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

            final ChunkedWriteHandler.PendingWrite var3 = this.currentWrite;
            final Object var4 = var3.msg;
            if (var4 instanceof ChunkedInput) {
               final ChunkedInput var5 = (ChunkedInput)var4;
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

               final int var9 = amount(var8);
               ChannelFuture var10 = var1.write(var8);
               if (var6) {
                  this.currentWrite = null;
                  var10.addListener(new ChannelFutureListener() {

                     public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
                        var3.progress(var9);
                        var3.success();
                        ChunkedWriteHandler.closeInput(var5);
                     }
                  });
               } else if (var2.isWritable()) {
                  var10.addListener(new ChannelFutureListener() {

                     public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
                        if (!var1.isSuccess()) {
                           ChunkedWriteHandler.closeInput((ChunkedInput<?>)var4);
                           var3.fail(var1.cause());
                        } else {
                           var3.progress(var9);
                        }
                     }
                  });
               } else {
                  var10.addListener(new ChannelFutureListener() {

                     public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
                        if (!var1.isSuccess()) {
                           ChunkedWriteHandler.closeInput((ChunkedInput<?>)var4);
                           var3.fail(var1.cause());
                        } else {
                           var3.progress(var9);
                           if (var2.isWritable()) {
                              ChunkedWriteHandler.this.resumeTransfer();
                           }
                        }
                     }
                  });
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
         ChunkedWriteHandler.PendingWrite var2 = this.currentWrite;
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
   public void flush(ChannelHandlerContext var1) throws java.lang.Exception {
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
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      this.ctx = var1;
   }

   public ChunkedWriteHandler(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("maxPendingWrites: " + var1 + " (expected: > 0)");
      }
   }

   public static final class PendingWrite {
      public ChannelPromise promise;
      public long progress;
      public Object msg;

      public PendingWrite(Object var1, ChannelPromise var2) {
         this.msg = var1;
         this.promise = var2;
      }

      public void progress(int var1) {
         this.progress += var1;
         if (this.promise instanceof ChannelProgressivePromise) {
            ((ChannelProgressivePromise)this.promise).tryProgress(this.progress, -1L);
         }
      }

      public void success() {
         if (!this.promise.isDone()) {
            if (this.promise instanceof ChannelProgressivePromise) {
               ((ChannelProgressivePromise)this.promise).tryProgress(this.progress, this.progress);
            }

            this.promise.trySuccess();
         }
      }

      public void fail(Throwable var1) {
         ReferenceCountUtil.release(this.msg);
         this.promise.tryFailure(var1);
      }
   }
}
