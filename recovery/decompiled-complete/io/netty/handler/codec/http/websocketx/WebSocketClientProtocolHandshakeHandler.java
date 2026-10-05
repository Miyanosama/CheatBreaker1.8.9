package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import junit.swingui.TestRunner$13;
import net.minecraft.entity.ai.EntityAIArrowAttack;

public class WebSocketClientProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
   public TestRunner$13 __junk9104557623909891400;
   public WebSocketClientHandshaker handshaker;
   public EntityAIArrowAttack __junk4256471145904689083;

   @Override
   public void channelActive(ChannelHandlerContext var1) {
      super.channelActive(var1);
      this.handshaker.handshake(var1.channel()).addListener(new WebSocketClientProtocolHandshakeHandler$1(this, var1));
   }

   public WebSocketClientProtocolHandshakeHandler(WebSocketClientHandshaker var1) {
      this.handshaker = var1;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      if (!(var2 instanceof FullHttpResponse)) {
         var1.fireChannelRead(var2);
      } else if (!this.handshaker.isHandshakeComplete()) {
         this.handshaker.finishHandshake(var1.channel(), (FullHttpResponse)var2);
         var1.fireUserEventTriggered(WebSocketClientProtocolHandler$ClientHandshakeStateEvent.HANDSHAKE_COMPLETE);
         var1.pipeline().remove(this);
      } else {
         throw new IllegalStateException("WebSocketClientHandshaker should have been non finished yet");
      }
   }
}
