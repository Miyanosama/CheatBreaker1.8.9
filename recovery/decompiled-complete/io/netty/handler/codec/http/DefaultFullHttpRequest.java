package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import javazoom.jl.decoder.Equalizer;

public class DefaultFullHttpRequest extends DefaultHttpRequest implements FullHttpRequest {
   public boolean validateHeaders;
   public Equalizer __junk2066023432855484819;
   public ByteBuf content;
   public HttpHeaders trailingHeader;

   @Override
   public FullHttpRequest copy() {
      DefaultFullHttpRequest var1 = new DefaultFullHttpRequest(
         this.getProtocolVersion(), this.getMethod(), this.getUri(), this.content().copy(), this.validateHeaders
      );
      var1.headers().set(this.headers());
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   @Override
   public int refCnt() {
      return this.content.refCnt();
   }

   @Override
   public ByteBuf content() {
      return this.content;
   }

   @Override
   public FullHttpRequest setProtocolVersion(HttpVersion var1) {
      super.setProtocolVersion(var1);
      return this;
   }

   public DefaultFullHttpRequest(HttpVersion var1, HttpMethod var2, String var3, ByteBuf var4, boolean var5) {
      super(var1, var2, var3, var5);
      if (var4 == null) {
         throw new NullPointerException("content");
      } else {
         this.content = var4;
         this.trailingHeader = new DefaultHttpHeaders(var5);
         this.validateHeaders = var5;
      }
   }

   @Override
   public FullHttpRequest retain() {
      this.content.retain();
      return this;
   }

   @Override
   public FullHttpRequest retain(int var1) {
      this.content.retain(var1);
      return this;
   }

   @Override
   public boolean release(int var1) {
      return this.content.release(var1);
   }

   @Override
   public FullHttpRequest setUri(String var1) {
      super.setUri(var1);
      return this;
   }

   @Override
   public boolean release() {
      return this.content.release();
   }

   @Override
   public HttpHeaders trailingHeaders() {
      return this.trailingHeader;
   }

   public FullHttpRequest duplicate() {
      DefaultFullHttpRequest var1 = new DefaultFullHttpRequest(
         this.getProtocolVersion(), this.getMethod(), this.getUri(), this.content().duplicate(), this.validateHeaders
      );
      var1.headers().set(this.headers());
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   public DefaultFullHttpRequest(HttpVersion var1, HttpMethod var2, String var3, ByteBuf var4) {
      this(var1, var2, var3, var4, true);
   }

   public DefaultFullHttpRequest(HttpVersion var1, HttpMethod var2, String var3) {
      this(var1, var2, var3, Unpooled.buffer(0));
   }

   @Override
   public FullHttpRequest setMethod(HttpMethod var1) {
      super.setMethod(var1);
      return this;
   }
}
