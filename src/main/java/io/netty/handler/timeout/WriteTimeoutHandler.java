package io.netty.handler.timeout;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import io.netty.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javazoom.jl.decoder.LayerIIIDecoder;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.command.CommandEffect;
import net.optifine.entity.model.ModelAdapterVillager;

public class WriteTimeoutHandler extends ChannelOutboundHandlerAdapter {
   public boolean closed;
   public long timeoutNanos;
   public static long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(1L);

   public WriteTimeoutHandler(int var1) {
      this(var1, TimeUnit.SECONDS);
   }

   public WriteTimeoutHandler(long var1, TimeUnit var3) {
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
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      this.scheduleTimeout(var1, var3);
      var1.write(var2, var3);
   }

   public void scheduleTimeout(final ChannelHandlerContext var1, final ChannelPromise var2) {
      if (this.timeoutNanos > 0L) {
         final ScheduledFuture var3 = var1.executor().schedule(new Runnable() {

            @Override
            public void run() {
               if (!var2.isDone()) {
                  try {
                     WriteTimeoutHandler.this.writeTimedOut(var1);
                  } catch (Throwable var2x) {
                     var1.fireExceptionCaught(var2x);
                  }
               }
            }
         }, this.timeoutNanos, TimeUnit.NANOSECONDS);
         var2.addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
               var3.cancel(false);
            }
         });
      }
   }

   public void writeTimedOut(ChannelHandlerContext var1) throws java.lang.Exception {
      if (!this.closed) {
         var1.fireExceptionCaught(WriteTimeoutException.INSTANCE);
         var1.close();
         this.closed = true;
      }
   }
}
