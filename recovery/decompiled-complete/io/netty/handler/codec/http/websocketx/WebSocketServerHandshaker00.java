package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.InternalAttribute;
import java.util.regex.Pattern;
import net.minecraft.command.server.CommandBroadcast;
import net.minecraft.entity.boss.EntityWither;

public class WebSocketServerHandshaker00 extends WebSocketServerHandshaker {
   public static Pattern BEGINNING_DIGIT = Pattern.compile("[^0-9]");
   public static Pattern BEGINNING_SPACE = Pattern.compile("[^ ]");
   public InternalAttribute __junk1617894287256393792;
   public EntityWither __junk1333849805667024439;
   public CommandBroadcast __junk1027960826058526876;

   @Override
   public FullHttpResponse newHandshakeResponse(FullHttpRequest var1, HttpHeaders var2) {
      if ("Upgrade".equalsIgnoreCase(var1.headers().get("Connection")) && "WebSocket".equalsIgnoreCase(var1.headers().get("Upgrade"))) {
         boolean var3 = var1.headers().contains("Sec-WebSocket-Key1") && var1.headers().contains("Sec-WebSocket-Key2");
         DefaultFullHttpResponse var4 = new DefaultFullHttpResponse(
            HttpVersion.HTTP_1_1, new HttpResponseStatus(101, var3 ? "WebSocket Protocol Handshake" : "Web Socket Protocol Handshake")
         );
         if (var2 != null) {
            var4.headers().add(var2);
         }

         var4.headers().add("Upgrade", "WebSocket");
         var4.headers().add("Connection", "Upgrade");
         if (var3) {
            var4.headers().add("Sec-WebSocket-Origin", var1.headers().get("Origin"));
            var4.headers().add("Sec-WebSocket-Location", this.uri());
            String var5 = var1.headers().get("Sec-WebSocket-Protocol");
            if (var5 != null) {
               String var6 = this.selectSubprotocol(var5);
               if (var6 == null) {
                  if (logger.isDebugEnabled()) {
                     logger.debug("Requested subprotocol(s) not supported: {}", var5);
                  }
               } else {
                  var4.headers().add("Sec-WebSocket-Protocol", var6);
               }
            }

            String var14 = var1.headers().get("Sec-WebSocket-Key1");
            String var7 = var1.headers().get("Sec-WebSocket-Key2");
            int var8 = (int)(Long.parseLong(BEGINNING_DIGIT.matcher(var14).replaceAll("")) / BEGINNING_SPACE.matcher(var14).replaceAll("").length());
            int var9 = (int)(Long.parseLong(BEGINNING_DIGIT.matcher(var7).replaceAll("")) / BEGINNING_SPACE.matcher(var7).replaceAll("").length());
            long var10 = var1.content().readLong();
            ByteBuf var12 = Unpooled.buffer(16);
            var12.writeInt(var8);
            var12.writeInt(var9);
            var12.writeLong(var10);
            var4.content().writeBytes(WebSocketUtil.md5(var12.array()));
         } else {
            var4.headers().add("WebSocket-Origin", var1.headers().get("Origin"));
            var4.headers().add("WebSocket-Location", this.uri());
            String var13 = var1.headers().get("WebSocket-Protocol");
            if (var13 != null) {
               var4.headers().add("WebSocket-Protocol", this.selectSubprotocol(var13));
            }
         }

         return var4;
      } else {
         throw new WebSocketHandshakeException("not a WebSocket handshake request: missing upgrade");
      }
   }

   public WebSocketServerHandshaker00(String var1, String var2, int var3) {
      super(WebSocketVersion.V00, var1, var2, var3);
   }

   @Override
   public WebSocketFrameDecoder newWebsocketDecoder() {
      return new WebSocket00FrameDecoder(this.maxFramePayloadLength());
   }

   @Override
   public ChannelFuture close(Channel var1, CloseWebSocketFrame var2, ChannelPromise var3) {
      return var1.writeAndFlush(var2, var3);
   }

   @Override
   public WebSocketFrameEncoder newWebSocketEncoder() {
      return new WebSocket00FrameEncoder();
   }
}
