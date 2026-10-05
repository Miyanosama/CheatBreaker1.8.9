package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.util.internal.StringUtil;
import org.apache.log4j.helpers.PatternConverter;

public class DefaultHttpContent extends DefaultHttpObject implements HttpContent {
   public ByteBuf content;

   @Override
   public HttpContent retain(int var1) {
      this.content.retain(var1);
      return this;
   }

   @Override
   public HttpContent duplicate() {
      return new DefaultHttpContent(this.content.duplicate());
   }

   @Override
   public ByteBuf content() {
      return this.content;
   }

   @Override
   public boolean release() {
      return this.content.release();
   }

   @Override
   public HttpContent copy() {
      return new DefaultHttpContent(this.content.copy());
   }

   @Override
   public HttpContent retain() {
      this.content.retain();
      return this;
   }

   public DefaultHttpContent(ByteBuf var1) {
      if (var1 == null) {
         throw new NullPointerException("content");
      } else {
         this.content = var1;
      }
   }

   @Override
   public boolean release(int var1) {
      return this.content.release(var1);
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + "(data: " + this.content() + ", decoderResult: " + this.getDecoderResult() + ')';
   }

   @Override
   public int refCnt() {
      return this.content.refCnt();
   }
}
