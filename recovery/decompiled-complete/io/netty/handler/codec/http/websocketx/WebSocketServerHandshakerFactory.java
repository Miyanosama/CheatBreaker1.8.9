package io.netty.handler.codec.http.websocketx;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import junit.swingui.DefaultFailureDetailView$StackTraceListModel;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.optifine.shaders.BlockAlias;

public class WebSocketServerHandshakerFactory {
   public String subprotocols;
   public String webSocketURL;
   public int maxFramePayloadLength;
   public C00PacketLoginStart __junk4652290398943145200;
   public BlockAlias __junk7554857140184433748;
   public boolean allowExtensions;
   public DefaultFailureDetailView$StackTraceListModel __junk1410269972743085869;

   public static void sendUnsupportedWebSocketVersionResponse(Channel var0) {
      sendUnsupportedVersionResponse(var0);
   }

   public static ChannelFuture sendUnsupportedVersionResponse(Channel var0, ChannelPromise var1) {
      DefaultHttpResponse var2 = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.UPGRADE_REQUIRED);
      var2.headers().set("Sec-WebSocket-Version", WebSocketVersion.V13.toHttpHeaderValue());
      return var0.write(var2, var1);
   }

   public static ChannelFuture sendUnsupportedVersionResponse(Channel var0) {
      return sendUnsupportedVersionResponse(var0, var0.newPromise());
   }

   public WebSocketServerHandshaker newHandshaker(HttpRequest var1) {
      String var2 = var1.headers().get("Sec-WebSocket-Version");
      if (var2 != null) {
         if (var2.equals(WebSocketVersion.V13.toHttpHeaderValue())) {
            return new WebSocketServerHandshaker13(this.webSocketURL, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength);
         } else if (var2.equals(WebSocketVersion.V08.toHttpHeaderValue())) {
            return new WebSocketServerHandshaker08(this.webSocketURL, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength);
         } else {
            return var2.equals(WebSocketVersion.V07.toHttpHeaderValue())
               ? new WebSocketServerHandshaker07(this.webSocketURL, this.subprotocols, this.allowExtensions, this.maxFramePayloadLength)
               : null;
         }
      } else {
         return new WebSocketServerHandshaker00(this.webSocketURL, this.subprotocols, this.maxFramePayloadLength);
      }
   }

   public WebSocketServerHandshakerFactory(String var1, String var2, boolean var3) {
      this(var1, var2, var3, 65536);
   }

   public WebSocketServerHandshakerFactory(String var1, String var2, boolean var3, int var4) {
      this.webSocketURL = var1;
      this.subprotocols = var2;
      this.allowExtensions = var3;
      this.maxFramePayloadLength = var4;
   }
}
