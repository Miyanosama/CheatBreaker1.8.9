package io.netty.handler.codec.http;

import io.netty.handler.codec.DecoderResult;
import net.minecraft.client.renderer.texture.AbstractTexture;

public class DefaultHttpObject implements HttpObject {
   public DecoderResult decoderResult = DecoderResult.SUCCESS;
   public AbstractTexture __junk6663051689455113832;

   @Override
   public void setDecoderResult(DecoderResult var1) {
      if (var1 == null) {
         throw new NullPointerException("decoderResult");
      } else {
         this.decoderResult = var1;
      }
   }

   @Override
   public DecoderResult getDecoderResult() {
      return this.decoderResult;
   }
}
