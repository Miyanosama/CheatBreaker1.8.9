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
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      if (this.initPipeline(var1)) {
         var1.pipeline().remove(this);
      }
   }

   public boolean initPipeline(ChannelHandlerContext var1) {
      SslHandler var2 = var1.pipeline().get(SslHandler.class);
      if (var2 == null) {
         throw new IllegalStateException("SslHandler is needed for SPDY");
      } else {
         SpdyOrHttpChooser$SelectedProtocol var3 = this.getProtocol(var2.engine());
         switch (SpdyOrHttpChooser$1.$SwitchMap$io$netty$handler$codec$spdy$SpdyOrHttpChooser$SelectedProtocol[var3.ordinal()]) {
            case 1:
               return false;
            case 2:
               this.addSpdyHandlers(var1, SpdyVersion.SPDY_3_1);
               break;
            case 3:
            case 4:
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

   public SpdyOrHttpChooser$SelectedProtocol getProtocol(SSLEngine var1) {
      String[] var2 = StringUtil.split(var1.getSession().getProtocol(), ':');
      return var2.length < 2 ? SpdyOrHttpChooser$SelectedProtocol.HTTP_1_1 : SpdyOrHttpChooser$SelectedProtocol.protocol(var2[1]);
   }
}
