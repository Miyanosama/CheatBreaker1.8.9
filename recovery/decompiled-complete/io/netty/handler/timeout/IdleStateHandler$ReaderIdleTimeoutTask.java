package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;

public class IdleStateHandler$ReaderIdleTimeoutTask implements Runnable {
   public EntityAIControlledByPlayer __junk3142704395743654551;
   public ChannelHandlerContext ctx;

   public IdleStateHandler$ReaderIdleTimeoutTask(IdleStateHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      super();
      this.ctx = var2;
   }

   @Override
   public void run() {
      if (this.ctx.channel().isOpen()) {
         long var1 = System.nanoTime();
         long var3 = this.this$0.lastReadTime;
         long var5 = IdleStateHandler.access$200(this.this$0) - (var1 - var3);
         if (var5 <= (1098916416L & -8515468914421169915L)) {
            this.this$0.readerIdleTimeout = this.ctx.executor().schedule(this, IdleStateHandler.access$200(this.this$0), TimeUnit.NANOSECONDS);

            try {
               IdleStateEvent var7;
               if (IdleStateHandler.access$300(this.this$0)) {
                  IdleStateHandler.access$302(this.this$0, false);
                  var7 = IdleStateEvent.FIRST_READER_IDLE_STATE_EVENT;
               } else {
                  var7 = IdleStateEvent.READER_IDLE_STATE_EVENT;
               }

               this.this$0.channelIdle(this.ctx, var7);
            } catch (Throwable var8) {
               this.ctx.fireExceptionCaught(var8);
            }
         } else {
            this.this$0.readerIdleTimeout = this.ctx.executor().schedule(this, var5, TimeUnit.NANOSECONDS);
         }
      }
   }
}
