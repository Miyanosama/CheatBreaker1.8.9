package io.netty.handler.codec.http.websocketx;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.network.play.server.S2EPacketCloseWindow;

public abstract class WebSocketServerHandshaker {
   public String selectedSubprotocol;
   public String[] subprotocols;
   public String uri;
   public static String SUB_PROTOCOL_WILDCARD;
   public S2EPacketCloseWindow __junk2141141672554189899;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(WebSocketServerHandshaker.class);
   public int maxFramePayloadLength;
   public WebSocketVersion version;
   public NBTTagEnd __junk6429331804510157384;

   public String selectSubprotocol(String var1) {
      if (var1 != null && this.subprotocols.length != 0) {
         String[] var2 = StringUtil.split(var1, ',');

         for (String var6 : var2) {
            String var7 = var6.trim();

            for (String var11 : this.subprotocols) {
               if ("*".equals(var11) || var7.equals(var11)) {
                  this.selectedSubprotocol = var7;
                  return var7;
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public WebSocketServerHandshaker(WebSocketVersion var1, String var2, String var3, int var4) {
      this.version = var1;
      this.uri = var2;
      if (var3 != null) {
         String[] var5 = StringUtil.split(var3, ',');

         for (int var6 = 0; var6 < var5.length; var6++) {
            var5[var6] = var5[var6].trim();
         }

         this.subprotocols = var5;
      } else {
         this.subprotocols = EmptyArrays.EMPTY_STRINGS;
      }

      this.maxFramePayloadLength = var4;
   }

   public ChannelFuture handshake(Channel var1, FullHttpRequest var2, HttpHeaders var3, ChannelPromise var4) {
      if (logger.isDebugEnabled()) {
         logger.debug("{} WebSocket version {} server handshake", var1, this.version());
      }

      FullHttpResponse var5 = this.newHandshakeResponse(var2, var3);
      ChannelPipeline var6 = var1.pipeline();
      if (var6.get(HttpObjectAggregator.class) != null) {
         var6.remove(HttpObjectAggregator.class);
      }

      if (var6.get(HttpContentCompressor.class) != null) {
         var6.remove(HttpContentCompressor.class);
      }

      ChannelHandlerContext var7 = var6.context(HttpRequestDecoder.class);
      String var8;
      if (var7 == null) {
         var7 = var6.context(HttpServerCodec.class);
         if (var7 == null) {
            var4.setFailure(new IllegalStateException("No HttpDecoder and no HttpServerCodec in the pipeline"));
            return var4;
         }

         var6.addBefore(var7.name(), "wsdecoder", this.newWebsocketDecoder());
         var6.addBefore(var7.name(), "wsencoder", this.newWebSocketEncoder());
         var8 = var7.name();
      } else {
         var6.replace(var7.name(), "wsdecoder", this.newWebsocketDecoder());
         var8 = var6.context(HttpResponseEncoder.class).name();
         var6.addBefore(var8, "wsencoder", this.newWebSocketEncoder());
      }

      var1.writeAndFlush(var5).addListener(new WebSocketServerHandshaker$1(this, var8, var4));
      return var4;
   }

   public abstract WebSocketFrameEncoder newWebSocketEncoder();

   public int maxFramePayloadLength() {
      return this.maxFramePayloadLength;
   }

   public abstract WebSocketFrameDecoder newWebsocketDecoder();

   public String selectedSubprotocol() {
      return this.selectedSubprotocol;
   }

   public ChannelFuture handshake(Channel var1, FullHttpRequest var2) {
      return this.handshake(var1, var2, null, var1.newPromise());
   }

   public ChannelFuture close(Channel var1, CloseWebSocketFrame var2) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         return this.close(var1, var2, var1.newPromise());
      }
   }

   public Set<String> subprotocols() {
      LinkedHashSet var1 = new LinkedHashSet();
      Collections.addAll(var1, this.subprotocols);
      return var1;
   }

   public WebSocketVersion version() {
      return this.version;
   }

   public ChannelFuture close(Channel var1, CloseWebSocketFrame var2, ChannelPromise var3) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         return var1.writeAndFlush(var2, var3).addListener(ChannelFutureListener.CLOSE);
      }
   }

   public String uri() {
      return this.uri;
   }

   public abstract FullHttpResponse newHandshakeResponse(FullHttpRequest var1, HttpHeaders var2);
}
