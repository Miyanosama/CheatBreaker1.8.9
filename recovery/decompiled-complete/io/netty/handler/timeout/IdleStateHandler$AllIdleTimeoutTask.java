package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import recovered.unidentified.UnidentifiedClass4788;

public class IdleStateHandler$AllIdleTimeoutTask implements Runnable {
   public UnidentifiedClass4788 __junk5232597873685944995;
   public ChannelHandlerContext ctx;
   public GuiStreamOptions __junk4483237651997047817;

   @Override
   public void run() {
      if (this.ctx.channel().isOpen()) {
         long var1 = System.nanoTime();
         long var3 = Math.max(this.this$0.lastReadTime, this.this$0.lastWriteTime);
         long var5 = IdleStateHandler.access$500(this.this$0) - (var1 - var3);
         if (var5 <= (-4756341437672969710L & 4756341437262697473L)) {
            this.this$0.allIdleTimeout = this.ctx.executor().schedule(this, IdleStateHandler.access$500(this.this$0), TimeUnit.NANOSECONDS);

            try {
               IdleStateEvent var7;
               if (IdleStateHandler.access$100(this.this$0)) {
                  IdleStateHandler.access$102(this.this$0, false);
                  var7 = IdleStateEvent.FIRST_ALL_IDLE_STATE_EVENT;
               } else {
                  var7 = IdleStateEvent.ALL_IDLE_STATE_EVENT;
               }

               this.this$0.channelIdle(this.ctx, var7);
            } catch (Throwable var8) {
               this.ctx.fireExceptionCaught(var8);
            }
         } else {
            this.this$0.allIdleTimeout = this.ctx.executor().schedule(this, var5, TimeUnit.NANOSECONDS);
         }
      }
   }

   public IdleStateHandler$AllIdleTimeoutTask(IdleStateHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      super();
      this.ctx = var2;
   }
}
