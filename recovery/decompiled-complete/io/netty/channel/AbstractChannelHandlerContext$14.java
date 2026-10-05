package io.netty.channel;

import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.netty.util.internal.OneTimeTask;
import net.minecraft.client.gui.spectator.SpectatorMenu$EndSpectatorObject;
import net.optifine.util.EntityUtils;

public class AbstractChannelHandlerContext$14 extends OneTimeTask {
   public AbstractTrafficShapingHandler __junk3273773720097105006;
   public SpectatorMenu$EndSpectatorObject __junk3343693568309449376;
   public EntityUtils __junk5283553150398213822;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$1200(this.val$next, this.val$promise);
   }

   public AbstractChannelHandlerContext$14(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$promise = var3;
      super();
   }
}
