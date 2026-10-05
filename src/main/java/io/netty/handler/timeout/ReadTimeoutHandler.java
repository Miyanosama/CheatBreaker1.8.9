package io.netty.handler.timeout;

import com.cheatbreaker.client.module.type.TabListModule;
import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder;
import io.netty.util.concurrent.ScheduledFutureTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.command.CommandSetPlayerTimeout;
import net.optifine.expr.TokenType$Const;
import com.cheatbreaker.client.util.ConsoleChatPrinter;

public class ReadTimeoutHandler extends ChannelInboundHandlerAdapter {
   public volatile int state;
   public volatile ScheduledFuture<?> timeout;
   public volatile long lastReadTime;
   public boolean closed;
   public long timeoutNanos;
   public static long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(1L);

   @Override
   public void channelActive(ChannelHandlerContext var1) throws java.lang.Exception {
      this.initialize(var1);
      super.channelActive(var1);
   }

   public ReadTimeoutHandler(int var1) {
      this(var1, TimeUnit.SECONDS);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      if (var1.channel().isActive() && var1.channel().isRegistered()) {
         this.initialize(var1);
      }
   }

   public void destroy() {
      this.state = 2;
      if (this.timeout != null) {
         this.timeout.cancel(false);
         this.timeout = null;
      }
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) throws java.lang.Exception {
      if (var1.channel().isActive()) {
         this.initialize(var1);
      }

      super.channelRegistered(var1);
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      this.destroy();
      super.channelInactive(var1);
   }

   public void initialize(ChannelHandlerContext var1) {
      switch (this.state) {
         case 1:
         case 2:
            return;
         default:
            this.state = 1;
            this.lastReadTime = System.nanoTime();
            if (this.timeoutNanos > 0L) {
               this.timeout = var1.executor().schedule(new ReadTimeoutHandler.ReadTimeoutTask(var1), this.timeoutNanos, TimeUnit.NANOSECONDS);
            }
      }
   }

   public void readTimedOut(ChannelHandlerContext var1) throws java.lang.Exception {
      if (!this.closed) {
         var1.fireExceptionCaught(ReadTimeoutException.INSTANCE);
         var1.close();
         this.closed = true;
      }
   }

   public ReadTimeoutHandler(long var1, TimeUnit var3) {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else {
         if (var1 <= 0L) {
            this.timeoutNanos = 0L;
         } else {
            this.timeoutNanos = Math.max(var3.toNanos(var1), MIN_TIMEOUT_NANOS);
         }
      }
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      this.lastReadTime = System.nanoTime();
      var1.fireChannelRead(var2);
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
      this.destroy();
   }

   public final class ReadTimeoutTask implements Runnable {
      public ChannelHandlerContext ctx;

      @Override
      public void run() {
         if (this.ctx.channel().isOpen()) {
            long var1 = System.nanoTime();
            long var3 = ReadTimeoutHandler.this.timeoutNanos - (var1 - ReadTimeoutHandler.this.lastReadTime);
            if (var3 <= 0L) {
               ReadTimeoutHandler.this.timeout = this.ctx.executor().schedule(this, ReadTimeoutHandler.this.timeoutNanos, TimeUnit.NANOSECONDS);

               try {
                  ReadTimeoutHandler.this.readTimedOut(this.ctx);
               } catch (Throwable var6) {
                  this.ctx.fireExceptionCaught(var6);
               }
            } else {
               ReadTimeoutHandler.this.timeout = this.ctx.executor().schedule(this, var3, TimeUnit.NANOSECONDS);
            }
         }
      }

      public ReadTimeoutTask(ChannelHandlerContext var2) {
         this.ctx = var2;
      }
   }
}
