package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.ByteToMessageCodec$1;
import io.netty.handler.codec.spdy.SpdySessionHandler$ClosingChannelFutureListener;

public class DefaultFullHttpResponse extends DefaultHttpResponse implements FullHttpResponse {
   public HttpHeaders trailingHeaders;
   public boolean validateHeaders;
   public ByteBuf content;
   public SpdySessionHandler$ClosingChannelFutureListener __junk8998511142559006104;
   public ByteToMessageCodec$1 __junk290112708061526905;

   @Override
   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }

   @Override
   public FullHttpResponse retain() {
      this.content.retain();
      return this;
   }

   @Override
   public FullHttpResponse copy() {
      DefaultFullHttpResponse var1 = new DefaultFullHttpResponse(this.getProtocolVersion(), this.getStatus(), this.content().copy(), this.validateHeaders);
      var1.headers().set(this.headers());
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   @Override
   public int refCnt() {
      return this.content.refCnt();
   }

   public DefaultFullHttpResponse(HttpVersion var1, HttpResponseStatus var2, ByteBuf var3, boolean var4) {
      super(var1, var2, var4);
      if (var3 == null) {
         throw new NullPointerException("content");
      } else {
         this.content = var3;
         this.trailingHeaders = new DefaultHttpHeaders(var4);
         this.validateHeaders = var4;
      }
   }

   @Override
   public boolean release(int var1) {
      return this.content.release(var1);
   }

   @Override
   public FullHttpResponse retain(int var1) {
      this.content.retain(var1);
      return this;
   }

   public DefaultFullHttpResponse(HttpVersion var1, HttpResponseStatus var2) {
      this(var1, var2, Unpooled.buffer(0));
   }

   @Override
   public boolean release() {
      return this.content.release();
   }

   @Override
   public FullHttpResponse setProtocolVersion(HttpVersion var1) {
      super.setProtocolVersion(var1);
      return this;
   }

   @Override
   public ByteBuf content() {
      return this.content;
   }

   @Override
   public FullHttpResponse setStatus(HttpResponseStatus var1) {
      super.setStatus(var1);
      return this;
   }

   public DefaultFullHttpResponse(HttpVersion var1, HttpResponseStatus var2, ByteBuf var3) {
      this(var1, var2, var3, true);
   }

   public FullHttpResponse duplicate() {
      DefaultFullHttpResponse var1 = new DefaultFullHttpResponse(this.getProtocolVersion(), this.getStatus(), this.content().duplicate(), this.validateHeaders);
      var1.headers().set(this.headers());
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }
}
