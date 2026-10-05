package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpResponse;
import junit.swingui.TestRunner$13;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.world.gen.ChunkProviderDebug;
import org.java_websocket.framing.ControlFrame;

public class WebSocketClientProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
   public WebSocketClientHandshaker handshaker;

   @Override
   public void channelActive(final ChannelHandlerContext var1) throws java.lang.Exception {
      super.channelActive(var1);
      this.handshaker.handshake(var1.channel()).addListener(new ChannelFutureListener() {

         public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
            if (!var1x.isSuccess()) {
               var1.fireExceptionCaught(var1x.cause());
            } else {
               var1.fireUserEventTriggered(WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_ISSUED);
            }
         }
      });
   }

   public WebSocketClientProtocolHandshakeHandler(WebSocketClientHandshaker var1) {
      this.handshaker = var1;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      if (!(var2 instanceof FullHttpResponse)) {
         var1.fireChannelRead(var2);
      } else if (!this.handshaker.isHandshakeComplete()) {
         this.handshaker.finishHandshake(var1.channel(), (FullHttpResponse)var2);
         var1.fireUserEventTriggered(WebSocketClientProtocolHandler.ClientHandshakeStateEvent.HANDSHAKE_COMPLETE);
         var1.pipeline().remove(this);
      } else {
         throw new IllegalStateException("WebSocketClientHandshaker should have been non finished yet");
      }
   }
}
