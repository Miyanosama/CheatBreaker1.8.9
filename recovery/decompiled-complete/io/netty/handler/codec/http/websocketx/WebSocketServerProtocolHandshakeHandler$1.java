package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.optifine.shaders.config.ShaderOptionVariable;

public class WebSocketServerProtocolHandshakeHandler$1 implements ChannelFutureListener {
   public ShaderOptionVariable __junk2535042544084684094;

   public WebSocketServerProtocolHandshakeHandler$1(WebSocketServerProtocolHandshakeHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         this.val$ctx.fireExceptionCaught(var1.cause());
      } else {
         this.val$ctx.fireUserEventTriggered(WebSocketServerProtocolHandler$ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
      }
   }
}
