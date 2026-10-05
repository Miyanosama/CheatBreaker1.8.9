package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.sctp.oio.OioSctpServerChannel$OioSctpServerChannelConfig;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.server.management.UserList$Serializer;

public class HttpObjectAggregator$AggregatedFullHttpResponse extends HttpObjectAggregator$AggregatedFullHttpMessage implements FullHttpResponse {
   public UserList$Serializer __junk2804661445073568169;
   public OioSctpServerChannel$OioSctpServerChannelConfig __junk9042093493210542374;
   public SimpleBakedModel __junk8332436567996188786;

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

   public HttpObjectAggregator$AggregatedFullHttpResponse(HttpResponse var1, ByteBuf var2, HttpHeaders var3) {
      super(var1, var2, var3, null);
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
