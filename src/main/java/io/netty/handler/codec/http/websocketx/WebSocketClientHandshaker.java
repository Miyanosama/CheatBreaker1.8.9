package io.netty.handler.codec.http.websocketx;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.local.LocalServerChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import java.net.URI;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.realms.RealmsClickableScrolledSelectionList;

public abstract class WebSocketClientHandshaker {
   public volatile String actualSubprotocol;
   public URI uri;
   public volatile boolean handshakeComplete;
   public HttpHeaders customHeaders;
   public String expectedSubprotocol;
   public int maxFramePayloadLength;
   public WebSocketVersion version;

   public abstract WebSocketFrameDecoder newWebsocketDecoder();

   public WebSocketClientHandshaker(URI var1, WebSocketVersion var2, String var3, HttpHeaders var4, int var5) {
      this.uri = var1;
      this.version = var2;
      this.expectedSubprotocol = var3;
      this.customHeaders = var4;
      this.maxFramePayloadLength = var5;
   }

   public abstract void verify(FullHttpResponse var1);

   public int maxFramePayloadLength() {
      return this.maxFramePayloadLength;
   }

   public void finishHandshake(Channel var1, FullHttpResponse var2) {
      this.verify(var2);
      this.setActualSubprotocol(var2.headers().get("Sec-WebSocket-Protocol"));
      this.setHandshakeComplete();
      ChannelPipeline var3 = var1.pipeline();
      HttpContentDecompressor var4 = var3.get(HttpContentDecompressor.class);
      if (var4 != null) {
         var3.remove(var4);
      }

      ChannelHandlerContext var5 = var3.context(HttpResponseDecoder.class);
      if (var5 == null) {
         var5 = var3.context(HttpClientCodec.class);
         if (var5 == null) {
            throw new IllegalStateException("ChannelPipeline does not contain a HttpRequestEncoder or HttpClientCodec");
         }

         var3.replace(var5.name(), "ws-decoder", this.newWebsocketDecoder());
      } else {
         if (var3.get(HttpRequestEncoder.class) != null) {
            var3.remove(HttpRequestEncoder.class);
         }

         var3.replace(var5.name(), "ws-decoder", this.newWebsocketDecoder());
      }
   }

   public ChannelFuture close(Channel var1, CloseWebSocketFrame var2) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         return this.close(var1, var2, var1.newPromise());
      }
   }

   public abstract WebSocketFrameEncoder newWebSocketEncoder();

   public void setHandshakeComplete() {
      this.handshakeComplete = true;
   }

   public void setActualSubprotocol(String var1) {
      this.actualSubprotocol = var1;
   }

   public String actualSubprotocol() {
      return this.actualSubprotocol;
   }

   public abstract FullHttpRequest newHandshakeRequest();

   public ChannelFuture close(Channel var1, CloseWebSocketFrame var2, ChannelPromise var3) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         return var1.writeAndFlush(var2, var3);
      }
   }

   public boolean isHandshakeComplete() {
      return this.handshakeComplete;
   }

   public String expectedSubprotocol() {
      return this.expectedSubprotocol;
   }

   public ChannelFuture handshake(Channel var1) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         return this.handshake(var1, var1.newPromise());
      }
   }

   public WebSocketVersion version() {
      return this.version;
   }

   public URI uri() {
      return this.uri;
   }

   public ChannelFuture handshake(Channel var1, final ChannelPromise var2) {
      FullHttpRequest var3 = this.newHandshakeRequest();
      HttpResponseDecoder var4 = var1.pipeline().get(HttpResponseDecoder.class);
      if (var4 == null) {
         HttpClientCodec var5 = var1.pipeline().get(HttpClientCodec.class);
         if (var5 == null) {
            var2.setFailure(new IllegalStateException("ChannelPipeline does not contain a HttpResponseDecoder or HttpClientCodec"));
            return var2;
         }
      }

      var1.writeAndFlush(var3).addListener(new ChannelFutureListener() {

         public void operationComplete(ChannelFuture var1) {
            if (var1.isSuccess()) {
               ChannelPipeline var2x = var1.channel().pipeline();
               ChannelHandlerContext var3x = var2x.context(HttpRequestEncoder.class);
               if (var3x == null) {
                  var3x = var2x.context(HttpClientCodec.class);
               }

               if (var3x == null) {
                  var2.setFailure(new IllegalStateException("ChannelPipeline does not contain a HttpRequestEncoder or HttpClientCodec"));
                  return;
               }

               var2x.addAfter(var3x.name(), "ws-encoder", WebSocketClientHandshaker.this.newWebSocketEncoder());
               var2.setSuccess();
            } else {
               var2.setFailure(var1.cause());
            }
         }
      });
      return var2;
   }
}
