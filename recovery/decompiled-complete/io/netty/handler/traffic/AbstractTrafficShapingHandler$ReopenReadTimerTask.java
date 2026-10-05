package io.netty.handler.traffic;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.model.ModelSign;

public class AbstractTrafficShapingHandler$ReopenReadTimerTask implements Runnable {
   public ChannelHandlerContext ctx;
   public ModelSign __junk633467256933602891;

   @Override
   public void run() {
      if (!this.ctx.channel().config().isAutoRead() && AbstractTrafficShapingHandler.isHandlerActive(this.ctx)) {
         if (AbstractTrafficShapingHandler.access$000().isDebugEnabled()) {
            AbstractTrafficShapingHandler.access$000()
               .debug(
                  "Channel:"
                     + this.ctx.channel().hashCode()
                     + " Not Unsuspend: "
                     + this.ctx.channel().config().isAutoRead()
                     + ":"
                     + AbstractTrafficShapingHandler.isHandlerActive(this.ctx)
               );
         }

         this.ctx.<Boolean>attr(AbstractTrafficShapingHandler.access$100()).set(false);
      } else {
         if (AbstractTrafficShapingHandler.access$000().isDebugEnabled()) {
            if (this.ctx.channel().config().isAutoRead() && !AbstractTrafficShapingHandler.isHandlerActive(this.ctx)) {
               AbstractTrafficShapingHandler.access$000()
                  .debug(
                     "Channel:"
                        + this.ctx.channel().hashCode()
                        + " Unsuspend: "
                        + this.ctx.channel().config().isAutoRead()
                        + ":"
                        + AbstractTrafficShapingHandler.isHandlerActive(this.ctx)
                  );
            } else {
               AbstractTrafficShapingHandler.access$000()
                  .debug(
                     "Channel:"
                        + this.ctx.channel().hashCode()
                        + " Normal Unsuspend: "
                        + this.ctx.channel().config().isAutoRead()
                        + ":"
                        + AbstractTrafficShapingHandler.isHandlerActive(this.ctx)
                  );
            }
         }

         this.ctx.<Boolean>attr(AbstractTrafficShapingHandler.access$100()).set(false);
         this.ctx.channel().config().setAutoRead(true);
         this.ctx.channel().read();
      }

      if (AbstractTrafficShapingHandler.access$000().isDebugEnabled()) {
         AbstractTrafficShapingHandler.access$000()
            .debug(
               "Channel:"
                  + this.ctx.channel().hashCode()
                  + " Unsupsend final status => "
                  + this.ctx.channel().config().isAutoRead()
                  + ":"
                  + AbstractTrafficShapingHandler.isHandlerActive(this.ctx)
            );
      }
   }

   public AbstractTrafficShapingHandler$ReopenReadTimerTask(ChannelHandlerContext var1) {
      this.ctx = var1;
   }
}
