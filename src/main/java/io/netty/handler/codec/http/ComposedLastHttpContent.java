package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DecoderResult;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.gui.GuiSnooper;

public class ComposedLastHttpContent implements LastHttpContent {
   public DecoderResult result;
   public HttpHeaders trailingHeaders;

   @Override
   public int refCnt() {
      return 1;
   }

   @Override
   public ByteBuf content() {
      return Unpooled.EMPTY_BUFFER;
   }

   public ComposedLastHttpContent(HttpHeaders var1) {
      this.trailingHeaders = var1;
   }

   @Override
   public DecoderResult getDecoderResult() {
      return this.result;
   }

   @Override
   public LastHttpContent retain(int var1) {
      return this;
   }

   @Override
   public LastHttpContent retain() {
      return this;
   }

   @Override
   public HttpContent duplicate() {
      return this.copy();
   }

   @Override
   public void setDecoderResult(DecoderResult var1) {
      this.result = var1;
   }

   @Override
   public boolean release() {
      return false;
   }

   @Override
   public LastHttpContent copy() {
      DefaultLastHttpContent var1 = new DefaultLastHttpContent(Unpooled.EMPTY_BUFFER);
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   @Override
   public boolean release(int var1) {
      return false;
   }

   @Override
   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }
}
