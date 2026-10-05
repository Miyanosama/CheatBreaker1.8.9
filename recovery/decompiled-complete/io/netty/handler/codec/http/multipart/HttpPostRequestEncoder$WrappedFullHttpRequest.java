package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;
import net.minecraft.network.NetHandlerPlayServer;

public class HttpPostRequestEncoder$WrappedFullHttpRequest extends HttpPostRequestEncoder$WrappedHttpRequest implements FullHttpRequest {
   public NetHandlerPlayServer __junk8264135622473977935;
   public HttpHeaders __junk7478865758054366391;
   public HttpContent content;

   public FullHttpRequest duplicate() {
      DefaultFullHttpRequest var1 = new DefaultFullHttpRequest(this.getProtocolVersion(), this.getMethod(), this.getUri(), this.content().duplicate());
      var1.headers().set(this.headers());
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   @Override
   public FullHttpRequest setMethod(HttpMethod var1) {
      super.setMethod(var1);
      return this;
   }

   @Override
   public FullHttpRequest retain() {
      this.content.retain();
      return this;
   }

   public HttpPostRequestEncoder$WrappedFullHttpRequest(HttpRequest var1, HttpContent var2) {
      super(var1);
      this.content = var2;
   }

   @Override
   public boolean release() {
      return this.content.release();
   }

   @Override
   public boolean release(int var1) {
      return this.content.release(var1);
   }

   @Override
   public FullHttpRequest setProtocolVersion(HttpVersion var1) {
      super.setProtocolVersion(var1);
      return this;
   }

   @Override
   public FullHttpRequest copy() {
      DefaultFullHttpRequest var1 = new DefaultFullHttpRequest(this.getProtocolVersion(), this.getMethod(), this.getUri(), this.content().copy());
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
      return this.content.content();
   }

   @Override
   public FullHttpRequest retain(int var1) {
      this.content.retain(var1);
      return this;
   }

   @Override
   public FullHttpRequest setUri(String var1) {
      super.setUri(var1);
      return this;
   }

   @Override
   public HttpHeaders trailingHeaders() {
      return this.content instanceof LastHttpContent ? ((LastHttpContent)this.content).trailingHeaders() : HttpHeaders.EMPTY_HEADERS;
   }
}
