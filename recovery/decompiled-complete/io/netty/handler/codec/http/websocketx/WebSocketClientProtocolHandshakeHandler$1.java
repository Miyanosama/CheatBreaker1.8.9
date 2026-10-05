package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.renderer.entity.RenderMagmaCube;
import net.minecraft.world.gen.ChunkProviderDebug;
import org.java_websocket.framing.ControlFrame;

public class WebSocketClientProtocolHandshakeHandler$1 implements ChannelFutureListener {
   public UnpooledByteBufAllocator __junk2966465707953156506;
   public RenderMagmaCube __junk6500403017656497431;
   public ControlFrame __junk6132936329562626309;
   public ChunkProviderDebug __junk8462628234679679146;

   public WebSocketClientProtocolHandshakeHandler$1(WebSocketClientProtocolHandshakeHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         this.val$ctx.fireExceptionCaught(var1.cause());
      } else {
         this.val$ctx.fireUserEventTriggered(WebSocketClientProtocolHandler$ClientHandshakeStateEvent.HANDSHAKE_ISSUED);
      }
   }
}
