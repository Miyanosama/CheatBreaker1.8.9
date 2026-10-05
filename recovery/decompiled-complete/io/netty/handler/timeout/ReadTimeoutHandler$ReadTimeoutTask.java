package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder;
import io.netty.util.concurrent.ScheduledFutureTask;
import java.util.concurrent.TimeUnit;

public class ReadTimeoutHandler$ReadTimeoutTask implements Runnable {
   public SpdyHeaderBlockDecoder __junk736153973976054976;
   public ChannelHandlerContext ctx;
   public ScheduledFutureTask __junk149839357047447641;

   @Override
   public void run() {
      if (this.ctx.channel().isOpen()) {
         long var1 = System.nanoTime();
         long var3 = ReadTimeoutHandler.access$000(this.this$0) - (var1 - ReadTimeoutHandler.access$100(this.this$0));
         if (var3 <= (66130L & -2661218148788689536L)) {
            ReadTimeoutHandler.access$202(this.this$0, this.ctx.executor().schedule(this, ReadTimeoutHandler.access$000(this.this$0), TimeUnit.NANOSECONDS));

            try {
               this.this$0.readTimedOut(this.ctx);
            } catch (Throwable var6) {
               this.ctx.fireExceptionCaught(var6);
            }
         } else {
            ReadTimeoutHandler.access$202(this.this$0, this.ctx.executor().schedule(this, var3, TimeUnit.NANOSECONDS));
         }
      }
   }

   public ReadTimeoutHandler$ReadTimeoutTask(ReadTimeoutHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      super();
      this.ctx = var2;
   }
}
