package io.netty.handler.codec.http;

import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;
import java.util.List;
import net.minecraft.block.BlockFence;
import net.minecraft.client.gui.MapItemRenderer$Instance;
import net.minecraft.client.gui.achievement.GuiStats$Stats;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;

public class HttpObjectAggregator extends MessageToMessageDecoder<HttpObject> {
   public int maxContentLength;
   public ChannelHandlerContext ctx;
   public GuiStats$Stats __junk683060524048837779;
   public static int DEFAULT_MAX_COMPOSITEBUFFER_COMPONENTS;
   public HttpObjectAggregator$AggregatedFullHttpMessage currentMessage;
   public MapItemRenderer$Instance __junk1156724231036203627;
   public LayerHeldBlock __junk5169115466381702480;
   public int maxCumulationBufferComponents = 1024;
   public boolean tooLongFrameFound;
   public static FullHttpResponse CONTINUE = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);
   public BlockFence __junk6732343496699172802;

   public void decode(ChannelHandlerContext var1, HttpObject var2, List<Object> var3) {
      HttpObjectAggregator$AggregatedFullHttpMessage var4 = this.currentMessage;
      if (var2 instanceof HttpMessage) {
         this.tooLongFrameFound = false;
         if (!$assertionsDisabled && var4 != null) {
            throw new AssertionError();
         }

         HttpMessage var5 = (HttpMessage)var2;
         if (HttpHeaders.is100ContinueExpected(var5)) {
            var1.writeAndFlush(CONTINUE).addListener(new HttpObjectAggregator$1(this, var1));
         }

         if (!var5.getDecoderResult().isSuccess()) {
            HttpHeaders.removeTransferEncodingChunked(var5);
            var3.add(toFullMessage(var5));
            this.currentMessage = null;
            return;
         }

         Object var9;
         if (var2 instanceof HttpRequest) {
            HttpRequest var6 = (HttpRequest)var2;
            this.currentMessage = (HttpObjectAggregator$AggregatedFullHttpMessage)(var9 = new HttpObjectAggregator$AggregatedFullHttpRequest(
               var6, var1.alloc().compositeBuffer(this.maxCumulationBufferComponents), null, null
            ));
         } else {
            if (!(var2 instanceof HttpResponse)) {
               throw new Error();
            }

            HttpResponse var11 = (HttpResponse)var2;
            this.currentMessage = (HttpObjectAggregator$AggregatedFullHttpMessage)(var9 = new HttpObjectAggregator$AggregatedFullHttpResponse(
               var11, Unpooled.compositeBuffer(this.maxCumulationBufferComponents), null, null
            ));
         }

         HttpHeaders.removeTransferEncodingChunked((HttpMessage)var9);
      } else {
         if (!(var2 instanceof HttpContent)) {
            throw new Error();
         }

         if (this.tooLongFrameFound) {
            if (var2 instanceof LastHttpContent) {
               this.currentMessage = null;
            }

            return;
         }

         if (!$assertionsDisabled && var4 == null) {
            throw new AssertionError();
         }

         HttpContent var10 = (HttpContent)var2;
         CompositeByteBuf var12 = (CompositeByteBuf)var4.content();
         if (var12.readableBytes() > this.maxContentLength - var10.content().readableBytes()) {
            this.tooLongFrameFound = true;
            var4.release();
            this.currentMessage = null;
            throw new TooLongFrameException("HTTP content length exceeded " + this.maxContentLength + " bytes.");
         }

         if (var10.content().isReadable()) {
            var10.retain();
            var12.addComponent(var10.content());
            var12.writerIndex(var12.writerIndex() + var10.content().readableBytes());
         }

         boolean var7;
         if (!var10.getDecoderResult().isSuccess()) {
            var4.setDecoderResult(DecoderResult.failure(var10.getDecoderResult().cause()));
            var7 = true;
         } else {
            var7 = var10 instanceof LastHttpContent;
         }

         if (var7) {
            this.currentMessage = null;
            if (var10 instanceof LastHttpContent) {
               LastHttpContent var8 = (LastHttpContent)var10;
               var4.setTrailingHeaders(var8.trailingHeaders());
            } else {
               var4.setTrailingHeaders(new DefaultHttpHeaders());
            }

            var4.headers().set("Content-Length", String.valueOf(var12.readableBytes()));
            var3.add(var4);
         }
      }
   }

   public HttpObjectAggregator(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("maxContentLength must be a positive integer: " + var1);
      } else {
         this.maxContentLength = var1;
      }
   }

   public int getMaxCumulationBufferComponents() {
      return this.maxCumulationBufferComponents;
   }

   public void setMaxCumulationBufferComponents(int var1) {
      if (var1 < 2) {
         throw new IllegalArgumentException("maxCumulationBufferComponents: " + var1 + " (expected: >= 2)");
      } else if (this.ctx == null) {
         this.maxCumulationBufferComponents = var1;
      } else {
         throw new IllegalStateException("decoder properties cannot be changed once the decoder is added to a pipeline.");
      }
   }

   public static FullHttpMessage toFullMessage(HttpMessage var0) {
      if (var0 instanceof FullHttpMessage) {
         return ((FullHttpMessage)var0).retain();
      } else {
         Object var1;
         if (var0 instanceof HttpRequest) {
            var1 = new HttpObjectAggregator$AggregatedFullHttpRequest((HttpRequest)var0, Unpooled.EMPTY_BUFFER, new DefaultHttpHeaders(), null);
         } else {
            if (!(var0 instanceof HttpResponse)) {
               throw new IllegalStateException();
            }

            var1 = new HttpObjectAggregator$AggregatedFullHttpResponse((HttpResponse)var0, Unpooled.EMPTY_BUFFER, new DefaultHttpHeaders(), null);
         }

         return (FullHttpMessage)var1;
      }
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
      super.handlerRemoved(var1);
      if (this.currentMessage != null) {
         this.currentMessage.release();
         this.currentMessage = null;
      }
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      super.channelInactive(var1);
      if (this.currentMessage != null) {
         this.currentMessage.release();
         this.currentMessage = null;
      }
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      this.ctx = var1;
   }
}
