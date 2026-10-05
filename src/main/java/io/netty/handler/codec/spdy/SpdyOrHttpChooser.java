package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.internal.StringUtil;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.apache.log4j.pattern.FormattingInfo;

public abstract class SpdyOrHttpChooser extends ByteToMessageDecoder {
   public int maxHttpContentLength;
   public int maxSpdyContentLength;

   public SpdyOrHttpChooser(int var1, int var2) {
      this.maxSpdyContentLength = var1;
      this.maxHttpContentLength = var2;
   }

   public ChannelInboundHandler createHttpRequestHandlerForSpdy() {
      return this.createHttpRequestHandlerForHttp();
   }

   public abstract ChannelInboundHandler createHttpRequestHandlerForHttp();

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      if (this.initPipeline(var1)) {
         var1.pipeline().remove(this);
      }
   }

   public boolean initPipeline(ChannelHandlerContext var1) {
      SslHandler var2 = var1.pipeline().get(SslHandler.class);
      if (var2 == null) {
         throw new IllegalStateException("SslHandler is needed for SPDY");
      } else {
         SpdyOrHttpChooser.SelectedProtocol var3 = this.getProtocol(var2.engine());
         switch (var3) {
            case UNKNOWN:
               return false;
            case SPDY_3_1:
               this.addSpdyHandlers(var1, SpdyVersion.SPDY_3_1);
               break;
            case HTTP_1_0:
            case HTTP_1_1:
               this.addHttpHandlers(var1);
               break;
            default:
               throw new IllegalStateException("Unknown SelectedProtocol");
         }

         return true;
      }
   }

   public void addSpdyHandlers(ChannelHandlerContext var1, SpdyVersion var2) {
      ChannelPipeline var3 = var1.pipeline();
      var3.addLast("spdyFrameCodec", new SpdyFrameCodec(var2));
      var3.addLast("spdySessionHandler", new SpdySessionHandler(var2, true));
      var3.addLast("spdyHttpEncoder", new SpdyHttpEncoder(var2));
      var3.addLast("spdyHttpDecoder", new SpdyHttpDecoder(var2, this.maxSpdyContentLength));
      var3.addLast("spdyStreamIdHandler", new SpdyHttpResponseStreamIdHandler());
      var3.addLast("httpRequestHandler", this.createHttpRequestHandlerForSpdy());
   }

   public void addHttpHandlers(ChannelHandlerContext var1) {
      ChannelPipeline var2 = var1.pipeline();
      var2.addLast("httpRequestDecoder", new HttpRequestDecoder());
      var2.addLast("httpResponseEncoder", new HttpResponseEncoder());
      var2.addLast("httpChunkAggregator", new HttpObjectAggregator(this.maxHttpContentLength));
      var2.addLast("httpRequestHandler", this.createHttpRequestHandlerForHttp());
   }

   public SpdyOrHttpChooser.SelectedProtocol getProtocol(SSLEngine var1) {
      String[] var2 = StringUtil.split(var1.getSession().getProtocol(), ':');
      return var2.length < 2 ? SpdyOrHttpChooser.SelectedProtocol.HTTP_1_1 : SpdyOrHttpChooser.SelectedProtocol.protocol(var2[1]);
   }

   public static enum SelectedProtocol {
      SPDY_3_1("spdy/3.1"),
      HTTP_1_1("http/1.1"),
      HTTP_1_0("http/1.0"),
      UNKNOWN("Unknown");

      // $VF: synthetic field
      public static SpdyOrHttpChooser.SelectedProtocol[] $VALUES = new SpdyOrHttpChooser.SelectedProtocol[]{
         SpdyOrHttpChooser.SelectedProtocol.SPDY_3_1, HTTP_1_1, HTTP_1_0, SpdyOrHttpChooser.SelectedProtocol.UNKNOWN
      };
      public String name;

      public String protocolName() {
         return this.name;
      }

      public static SpdyOrHttpChooser.SelectedProtocol protocol(String var0) {
         for (SpdyOrHttpChooser.SelectedProtocol var4 : values()) {
            if (var4.protocolName().equals(var0)) {
               return var4;
            }
         }

         return UNKNOWN;
      }

      SelectedProtocol(String var3) {
         this.name = var3;
      }
   }
}
