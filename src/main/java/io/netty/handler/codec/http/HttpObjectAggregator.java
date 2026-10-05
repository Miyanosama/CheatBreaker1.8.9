package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.module.CBModulePosition;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.oio.OioSctpChannel;
import io.netty.channel.sctp.oio.OioSctpServerChannel;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.handler.codec.compression.Crc32c;
import java.util.List;
import net.minecraft.block.BlockFence;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.server.management.UserList;
import net.optifine.CustomSkyLayer;
import com.cheatbreaker.client.nethandler.server.PacketServerRule;

public class HttpObjectAggregator extends MessageToMessageDecoder<HttpObject> {
   public int maxContentLength;
   public ChannelHandlerContext ctx;
   public static final int DEFAULT_MAX_COMPOSITEBUFFER_COMPONENTS = 1024;
   public HttpObjectAggregator.AggregatedFullHttpMessage currentMessage;
   public int maxCumulationBufferComponents = 1024;
   public boolean tooLongFrameFound;
   public static final boolean $assertionsDisabled = !HttpObjectAggregator.class.desiredAssertionStatus();
   public static FullHttpResponse CONTINUE = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);

   public void decode(final ChannelHandlerContext var1, HttpObject var2, List<Object> var3) throws java.lang.Exception {
      HttpObjectAggregator.AggregatedFullHttpMessage var4 = this.currentMessage;
      if (var2 instanceof HttpMessage) {
         this.tooLongFrameFound = false;
         if (!$assertionsDisabled && var4 != null) {
            throw new AssertionError();
         }

         HttpMessage var5 = (HttpMessage)var2;
         if (HttpHeaders.is100ContinueExpected(var5)) {
            var1.writeAndFlush(CONTINUE).addListener(new ChannelFutureListener() {

               public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
                  if (!var1x.isSuccess()) {
                     var1.fireExceptionCaught(var1x.cause());
                  }
               }
            });
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
            this.currentMessage = (HttpObjectAggregator.AggregatedFullHttpMessage)(var9 = new HttpObjectAggregator.AggregatedFullHttpRequest(
               var6, var1.alloc().compositeBuffer(this.maxCumulationBufferComponents), null
            ));
         } else {
            if (!(var2 instanceof HttpResponse)) {
               throw new Error();
            }

            HttpResponse var11 = (HttpResponse)var2;
            this.currentMessage = (HttpObjectAggregator.AggregatedFullHttpMessage)(var9 = new HttpObjectAggregator.AggregatedFullHttpResponse(
               var11, Unpooled.compositeBuffer(this.maxCumulationBufferComponents), null
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
            var1 = new HttpObjectAggregator.AggregatedFullHttpRequest((HttpRequest)var0, Unpooled.EMPTY_BUFFER, new DefaultHttpHeaders());
         } else {
            if (!(var0 instanceof HttpResponse)) {
               throw new IllegalStateException();
            }

            var1 = new HttpObjectAggregator.AggregatedFullHttpResponse((HttpResponse)var0, Unpooled.EMPTY_BUFFER, new DefaultHttpHeaders());
         }

         return (FullHttpMessage)var1;
      }
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
      super.handlerRemoved(var1);
      if (this.currentMessage != null) {
         this.currentMessage.release();
         this.currentMessage = null;
      }
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      super.channelInactive(var1);
      if (this.currentMessage != null) {
         this.currentMessage.release();
         this.currentMessage = null;
      }
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      this.ctx = var1;
   }

   public abstract static class AggregatedFullHttpMessage extends DefaultByteBufHolder implements FullHttpMessage {
      public HttpHeaders trailingHeaders;
      public HttpMessage message;

      @Override
      public HttpVersion getProtocolVersion() {
         return this.message.getProtocolVersion();
      }

      @Override
      public HttpHeaders trailingHeaders() {
         return this.trailingHeaders;
      }

      @Override
      public FullHttpMessage retain(int var1) {
         super.retain(var1);
         return this;
      }

      public abstract FullHttpMessage duplicate();

      public void setTrailingHeaders(HttpHeaders var1) {
         this.trailingHeaders = var1;
      }

      public FullHttpMessage setProtocolVersion(HttpVersion var1) {
         this.message.setProtocolVersion(var1);
         return this;
      }

      @Override
      public FullHttpMessage retain() {
         super.retain();
         return this;
      }

      @Override
      public DecoderResult getDecoderResult() {
         return this.message.getDecoderResult();
      }

      public AggregatedFullHttpMessage(HttpMessage var1, ByteBuf var2, HttpHeaders var3) {
         super(var2);
         this.message = var1;
         this.trailingHeaders = var3;
      }

      @Override
      public abstract FullHttpMessage copy();

      @Override
      public HttpHeaders headers() {
         return this.message.headers();
      }

      @Override
      public void setDecoderResult(DecoderResult var1) {
         this.message.setDecoderResult(var1);
      }
   }

   public static final class AggregatedFullHttpRequest extends HttpObjectAggregator.AggregatedFullHttpMessage implements FullHttpRequest {

      @Override
      public FullHttpRequest setUri(String var1) {
         ((HttpRequest)this.message).setUri(var1);
         return this;
      }

      public AggregatedFullHttpRequest(HttpRequest var1, ByteBuf var2, HttpHeaders var3) {
         super(var1, var2, var3);
      }

      @Override
      public FullHttpRequest setMethod(HttpMethod var1) {
         ((HttpRequest)this.message).setMethod(var1);
         return this;
      }

      @Override
      public FullHttpRequest copy() {
         DefaultFullHttpRequest var1 = new DefaultFullHttpRequest(this.getProtocolVersion(), this.getMethod(), this.getUri(), this.content().copy());
         var1.headers().set(this.headers());
         var1.trailingHeaders().set(this.trailingHeaders());
         return var1;
      }

      public FullHttpRequest duplicate() {
         DefaultFullHttpRequest var1 = new DefaultFullHttpRequest(this.getProtocolVersion(), this.getMethod(), this.getUri(), this.content().duplicate());
         var1.headers().set(this.headers());
         var1.trailingHeaders().set(this.trailingHeaders());
         return var1;
      }

      @Override
      public HttpMethod getMethod() {
         return ((HttpRequest)this.message).getMethod();
      }

      @Override
      public FullHttpRequest retain(int var1) {
         super.retain(var1);
         return this;
      }

      @Override
      public FullHttpRequest retain() {
         super.retain();
         return this;
      }

      @Override
      public String getUri() {
         return ((HttpRequest)this.message).getUri();
      }

      @Override
      public FullHttpRequest setProtocolVersion(HttpVersion var1) {
         super.setProtocolVersion(var1);
         return this;
      }
   }

   public static final class AggregatedFullHttpResponse extends HttpObjectAggregator.AggregatedFullHttpMessage implements FullHttpResponse {

      public FullHttpResponse duplicate() {
         DefaultFullHttpResponse var1 = new DefaultFullHttpResponse(this.getProtocolVersion(), this.getStatus(), this.content().duplicate());
         var1.headers().set(this.headers());
         var1.trailingHeaders().set(this.trailingHeaders());
         return var1;
      }

      @Override
      public FullHttpResponse retain() {
         super.retain();
         return this;
      }

      @Override
      public HttpResponseStatus getStatus() {
         return ((HttpResponse)this.message).getStatus();
      }

      public AggregatedFullHttpResponse(HttpResponse var1, ByteBuf var2, HttpHeaders var3) {
         super(var1, var2, var3);
      }

      @Override
      public FullHttpResponse copy() {
         DefaultFullHttpResponse var1 = new DefaultFullHttpResponse(this.getProtocolVersion(), this.getStatus(), this.content().copy());
         var1.headers().set(this.headers());
         var1.trailingHeaders().set(this.trailingHeaders());
         return var1;
      }

      @Override
      public FullHttpResponse setStatus(HttpResponseStatus var1) {
         ((HttpResponse)this.message).setStatus(var1);
         return this;
      }

      @Override
      public FullHttpResponse retain(int var1) {
         super.retain(var1);
         return this;
      }

      @Override
      public FullHttpResponse setProtocolVersion(HttpVersion var1) {
         super.setProtocolVersion(var1);
         return this;
      }
   }
}
