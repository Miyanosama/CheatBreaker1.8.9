package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.compression.Crc32c;
import recovered.unidentified.UnidentifiedClass1927;

public class HttpObjectAggregator$AggregatedFullHttpRequest extends HttpObjectAggregator$AggregatedFullHttpMessage implements FullHttpRequest {
   public Crc32c __junk3886914978576461129;
   public UnidentifiedClass1927 __junk2824738522287376832;

   @Override
   public FullHttpRequest setUri(String var1) {
      ((HttpRequest)this.message).setUri(var1);
      return this;
   }

   public HttpObjectAggregator$AggregatedFullHttpRequest(HttpRequest var1, ByteBuf var2, HttpHeaders var3) {
      super(var1, var2, var3, null);
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
