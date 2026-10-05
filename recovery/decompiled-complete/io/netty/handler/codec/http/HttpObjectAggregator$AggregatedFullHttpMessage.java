package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.channel.sctp.oio.OioSctpChannel;
import io.netty.handler.codec.DecoderResult;
import net.optifine.CustomSkyLayer;

public abstract class HttpObjectAggregator$AggregatedFullHttpMessage extends DefaultByteBufHolder implements FullHttpMessage {
   public CustomSkyLayer __junk8975294947239497547;
   public HttpHeaders trailingHeaders;
   public HttpMessage message;
   public OioSctpChannel __junk7209818899655245632;

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

   public HttpObjectAggregator$AggregatedFullHttpMessage(HttpMessage var1, ByteBuf var2, HttpHeaders var3) {
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
