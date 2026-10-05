package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class WebSocketServerHandshaker13 extends WebSocketServerHandshaker {
   public boolean allowExtensions;
   public static String WEBSOCKET_13_ACCEPT_GUID;

   public WebSocketServerHandshaker13(String var1, String var2, boolean var3, int var4) {
      super(WebSocketVersion.V13, var1, var2, var4);
      this.allowExtensions = var3;
   }

   @Override
   public WebSocketFrameDecoder newWebsocketDecoder() {
      return new WebSocket13FrameDecoder(true, this.allowExtensions, this.maxFramePayloadLength());
   }

   @Override
   public WebSocketFrameEncoder newWebSocketEncoder() {
      return new WebSocket13FrameEncoder(false);
   }

   @Override
   public FullHttpResponse newHandshakeResponse(FullHttpRequest var1, HttpHeaders var2) {
      DefaultFullHttpResponse var3 = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SWITCHING_PROTOCOLS);
      if (var2 != null) {
         var3.headers().add(var2);
      }

      String var4 = var1.headers().get("Sec-WebSocket-Key");
      if (var4 == null) {
         throw new WebSocketHandshakeException("not a WebSocket request: missing key");
      } else {
         String var5 = var4 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
         byte[] var6 = WebSocketUtil.sha1(var5.getBytes(CharsetUtil.US_ASCII));
         String var7 = WebSocketUtil.base64(var6);
         if (logger.isDebugEnabled()) {
            logger.debug("WebSocket version 13 server handshake key: {}, response: {}", var4, var7);
         }

         var3.headers().add("Upgrade", "WebSocket".toLowerCase());
         var3.headers().add("Connection", "Upgrade");
         var3.headers().add("Sec-WebSocket-Accept", var7);
         String var8 = var1.headers().get("Sec-WebSocket-Protocol");
         if (var8 != null) {
            String var9 = this.selectSubprotocol(var8);
            if (var9 == null) {
               if (logger.isDebugEnabled()) {
                  logger.debug("Requested subprotocol(s) not supported: {}", var8);
               }
            } else {
               var3.headers().add("Sec-WebSocket-Protocol", var9);
            }
         }

         return var3;
      }
   }
}
