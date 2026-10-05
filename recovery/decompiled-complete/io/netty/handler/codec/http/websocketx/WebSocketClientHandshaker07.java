package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.URI;
import net.minecraft.block.BlockSkull;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.entity.EntityBodyHelper;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class WebSocketClientHandshaker07 extends WebSocketClientHandshaker {
   public EntityFlameFX __junk1677827358875152969;
   public static String MAGIC_GUID;
   public boolean allowExtensions;
   public C0BPacketEntityAction __junk2211911222893005437;
   public EntityBodyHelper __junk6660302375557551328;
   public EntityDragon __junk3315564292071840245;
   public BlockSkull __junk3339870125994990968;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(WebSocketClientHandshaker07.class);
   public String expectedChallengeResponseString;

   @Override
   public WebSocketFrameEncoder newWebSocketEncoder() {
      return new WebSocket07FrameEncoder(true);
   }

   @Override
   public FullHttpRequest newHandshakeRequest() {
      URI var1 = this.uri();
      String var2 = var1.getPath();
      if (var1.getQuery() != null && !var1.getQuery().isEmpty()) {
         var2 = var1.getPath() + '?' + var1.getQuery();
      }

      if (var2 == null || var2.isEmpty()) {
         var2 = "/";
      }

      byte[] var3 = WebSocketUtil.randomBytes(16);
      String var4 = WebSocketUtil.base64(var3);
      String var5 = var4 + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
      byte[] var6 = WebSocketUtil.sha1(var5.getBytes(CharsetUtil.US_ASCII));
      this.expectedChallengeResponseString = WebSocketUtil.base64(var6);
      if (logger.isDebugEnabled()) {
         logger.debug("WebSocket version 07 client handshake key: {}, expected response: {}", var4, this.expectedChallengeResponseString);
      }

      DefaultFullHttpRequest var7 = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, var2);
      HttpHeaders var8 = var7.headers();
      var8.add("Upgrade", "WebSocket".toLowerCase()).add("Connection", "Upgrade").add("Sec-WebSocket-Key", var4).add("Host", var1.getHost());
      int var9 = var1.getPort();
      String var10 = "http://" + var1.getHost();
      if (var9 != 80 && var9 != 443) {
         var10 = var10 + ':' + var9;
      }

      var8.add("Sec-WebSocket-Origin", var10);
      String var11 = this.expectedSubprotocol();
      if (var11 != null && !var11.isEmpty()) {
         var8.add("Sec-WebSocket-Protocol", var11);
      }

      var8.add("Sec-WebSocket-Version", "7");
      if (this.customHeaders != null) {
         var8.add(this.customHeaders);
      }

      return var7;
   }

   public WebSocketClientHandshaker07(URI var1, WebSocketVersion var2, String var3, boolean var4, HttpHeaders var5, int var6) {
      super(var1, var2, var3, var5, var6);
      this.allowExtensions = var4;
   }

   @Override
   public void verify(FullHttpResponse var1) {
      HttpResponseStatus var2 = HttpResponseStatus.SWITCHING_PROTOCOLS;
      HttpHeaders var3 = var1.headers();
      if (!var1.getStatus().equals(var2)) {
         throw new WebSocketHandshakeException("Invalid handshake response getStatus: " + var1.getStatus());
      } else {
         String var4 = var3.get("Upgrade");
         if (!"WebSocket".equalsIgnoreCase(var4)) {
            throw new WebSocketHandshakeException("Invalid handshake response upgrade: " + var4);
         } else {
            String var5 = var3.get("Connection");
            if (!"Upgrade".equalsIgnoreCase(var5)) {
               throw new WebSocketHandshakeException("Invalid handshake response connection: " + var5);
            } else {
               String var6 = var3.get("Sec-WebSocket-Accept");
               if (var6 == null || !var6.equals(this.expectedChallengeResponseString)) {
                  throw new WebSocketHandshakeException(
                     String.format("Invalid challenge. Actual: %s. Expected: %s", var6, this.expectedChallengeResponseString)
                  );
               }
            }
         }
      }
   }

   @Override
   public WebSocketFrameDecoder newWebsocketDecoder() {
      return new WebSocket07FrameDecoder(false, this.allowExtensions, this.maxFramePayloadLength());
   }
}
