package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.PoolSubpage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class WebSocketServerProtocolHandler$1 extends ChannelInboundHandlerAdapter {
   public PoolSubpage __junk8244952994960102664;

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      if (var2 instanceof FullHttpRequest) {
         ((FullHttpRequest)var2).release();
         DefaultFullHttpResponse var3 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN);
         var1.channel().writeAndFlush(var3);
      } else {
         var1.fireChannelRead(var2);
      }
   }
}
