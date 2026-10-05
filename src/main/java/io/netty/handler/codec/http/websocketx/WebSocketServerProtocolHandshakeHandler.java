package io.netty.handler.codec.http.websocketx;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.ssl.SslHandler;
import net.minecraft.world.biome.BiomeGenRiver;
import net.optifine.shaders.config.ShaderOptionVariable;
import org.apache.log4j.pattern.PatternConverter;

public class WebSocketServerProtocolHandshakeHandler extends ChannelInboundHandlerAdapter {
   public boolean allowExtensions;
   public String websocketPath;
   public int maxFramePayloadSize;
   public String subprotocols;

   @Override
   public void channelRead(final ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      FullHttpRequest var3 = (FullHttpRequest)var2;

      try {
         if (var3.getMethod() != HttpMethod.GET) {
            sendHttpResponse(var1, var3, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
            return;
         }

         WebSocketServerHandshakerFactory var4 = new WebSocketServerHandshakerFactory(
            getWebSocketLocation(var1.pipeline(), var3, this.websocketPath), this.subprotocols, this.allowExtensions, this.maxFramePayloadSize
         );
         WebSocketServerHandshaker var5 = var4.newHandshaker(var3);
         if (var5 == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(var1.channel());
         } else {
            ChannelFuture var6 = var5.handshake(var1.channel(), var3);
            var6.addListener(new ChannelFutureListener() {

               public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
                  if (!var1x.isSuccess()) {
                     var1.fireExceptionCaught(var1x.cause());
                  } else {
                     var1.fireUserEventTriggered(WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE);
                  }
               }
            });
            WebSocketServerProtocolHandler.setHandshaker(var1, var5);
            var1.pipeline().replace(this, "WS403Responder", WebSocketServerProtocolHandler.forbiddenHttpRequestResponder());
         }
      } finally {
         var3.release();
      }
   }

   public WebSocketServerProtocolHandshakeHandler(String var1, String var2, boolean var3, int var4) {
      this.websocketPath = var1;
      this.subprotocols = var2;
      this.allowExtensions = var3;
      this.maxFramePayloadSize = var4;
   }

   public static String getWebSocketLocation(ChannelPipeline var0, HttpRequest var1, String var2) {
      String var3 = "ws";
      if (var0.get(SslHandler.class) != null) {
         var3 = "wss";
      }

      return var3 + "://" + var1.headers().get("Host") + var2;
   }

   public static void sendHttpResponse(ChannelHandlerContext var0, HttpRequest var1, HttpResponse var2) {
      ChannelFuture var3 = var0.channel().writeAndFlush(var2);
      if (!HttpHeaders.isKeepAlive(var1) || var2.getStatus().code() != 200) {
         var3.addListener(ChannelFutureListener.CLOSE);
      }
   }
}
