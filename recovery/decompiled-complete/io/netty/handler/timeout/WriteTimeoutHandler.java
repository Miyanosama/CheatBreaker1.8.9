package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import io.netty.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.block.BlockStoneSlab$EnumType;

public class WriteTimeoutHandler extends ChannelOutboundHandlerAdapter {
   public SpdyFrameDecoder __junk877546016139703783;
   public BlockStoneSlab$EnumType __junk7021692338090108653;
   public boolean closed;
   public long timeoutNanos;
   public static long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(4003487990101854337L & 21008393L);

   public WriteTimeoutHandler(int var1) {
      this(var1, TimeUnit.SECONDS);
   }

   public WriteTimeoutHandler(long var1, TimeUnit var3) {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else {
         if (var1 <= (637812800L & 5404160989229947952L)) {
            this.timeoutNanos = 1611137604L & -713300257122541567L;
         } else {
            this.timeoutNanos = Math.max(var3.toNanos(var1), MIN_TIMEOUT_NANOS);
         }
      }
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.scheduleTimeout(var1, var3);
      var1.write(var2, var3);
   }

   public void scheduleTimeout(ChannelHandlerContext var1, ChannelPromise var2) {
      if (this.timeoutNanos > (1111234822L & 159408129L)) {
         ScheduledFuture var3 = var1.executor().schedule(new WriteTimeoutHandler$1(this, var2, var1), this.timeoutNanos, TimeUnit.NANOSECONDS);
         var2.addListener(new WriteTimeoutHandler$2(this, var3));
      }
   }

   public void writeTimedOut(ChannelHandlerContext var1) {
      if (!this.closed) {
         var1.fireExceptionCaught(WriteTimeoutException.INSTANCE);
         var1.close();
         this.closed = true;
      }
   }
}
