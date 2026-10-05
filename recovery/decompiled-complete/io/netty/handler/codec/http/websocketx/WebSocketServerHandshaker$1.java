package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.client.renderer.GlStateManager$ColorLogicState;
import net.optifine.CustomBlockLayers;

public class WebSocketServerHandshaker$1 implements ChannelFutureListener {
   public GlStateManager$ColorLogicState __junk8597470512225806217;
   public EntitySnowShovelFX __junk9145500680530171729;
   public CustomBlockLayers __junk8355079119378300487;

   public WebSocketServerHandshaker$1(WebSocketServerHandshaker var1, String var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$encoderName = var2;
      this.val$promise = var3;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (var1.isSuccess()) {
         ChannelPipeline var2 = var1.channel().pipeline();
         var2.remove(this.val$encoderName);
         this.val$promise.setSuccess();
      } else {
         this.val$promise.setFailure(var1.cause());
      }
   }
}
