package io.netty.handler.codec.http.websocketx;

import com.cheatbreaker.client.module.type.ToggleSprintModule;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.AttributeKey;
import java.util.List;

public class WebSocketServerProtocolHandler extends WebSocketProtocolHandler {
   public int maxFramePayloadLength;
   public String websocketPath;
   public String subprotocols;
   public ToggleSprintModule __junk4683137180817494436;
   public static AttributeKey<WebSocketServerHandshaker> HANDSHAKER_ATTR_KEY = AttributeKey.valueOf(WebSocketServerHandshaker.class.getName() + ".HANDSHAKER");
   public boolean allowExtensions;

   public WebSocketServerProtocolHandler(String var1) {
      this(var1, null, false);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      ChannelPipeline var2 = var1.pipeline();
      if (var2.get(WebSocketServerProtocolHandshakeHandler.class) == null) {
         var1.pipeline()
            .addBefore(
               var1.name(),
               WebSocketServerProtocolHandshakeHandler.class.getName(),
               new WebSocketServerProtocolHandshakeHandler(this.websocketPath, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength)
            );
      }
   }

   public static ChannelHandler forbiddenHttpRequestResponder() {
      return new WebSocketServerProtocolHandler$1();
   }

   public WebSocketServerProtocolHandler(String var1, String var2, boolean var3) {
      this(var1, var2, var3, 65536);
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      if (var2 instanceof WebSocketHandshakeException) {
         DefaultFullHttpResponse var3 = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, Unpooled.wrappedBuffer(var2.getMessage().getBytes())
         );
         var1.channel().writeAndFlush(var3).addListener(ChannelFutureListener.CLOSE);
      } else {
         var1.close();
      }
   }

   @Override
   public void decode(ChannelHandlerContext var1, WebSocketFrame var2, List<Object> var3) {
      if (var2 instanceof CloseWebSocketFrame) {
         WebSocketServerHandshaker var4 = getHandshaker(var1);
         if (var4 != null) {
            var2.retain();
            var4.close(var1.channel(), (CloseWebSocketFrame)var2);
         } else {
            var1.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
         }
      } else {
         super.decode(var1, var2, var3);
      }
   }

   public WebSocketServerProtocolHandler(String var1, String var2, boolean var3, int var4) {
      this.websocketPath = var1;
      this.subprotocols = var2;
      this.allowExtensions = var3;
      this.maxFramePayloadLength = var4;
   }

   public static WebSocketServerHandshaker getHandshaker(ChannelHandlerContext var0) {
      return var0.attr(HANDSHAKER_ATTR_KEY).get();
   }

   public static void setHandshaker(ChannelHandlerContext var0, WebSocketServerHandshaker var1) {
      var0.attr(HANDSHAKER_ATTR_KEY).set(var1);
   }

   public WebSocketServerProtocolHandler(String var1, String var2) {
      this(var1, var2, false);
   }
}
