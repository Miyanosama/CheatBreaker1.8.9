package io.netty.handler.ssl;

import net.minecraft.block.BlockDoubleStoneSlab;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variants;
import net.minecraft.client.resources.SkinManager;
import net.optifine.http.HttpPipelineRequest;

public class SslHandshakeCompletionEvent {
   public ModelBlockDefinition$Variants __junk1781162955336952552;
   public BlockDoubleStoneSlab __junk264577321451021296;
   public ModelIronGolem __junk7378143190184728991;
   public static SslHandshakeCompletionEvent SUCCESS = new SslHandshakeCompletionEvent();
   public HttpPipelineRequest __junk924205483848417563;
   public SkinManager __junk3473970597834840195;
   public Throwable cause;

   public Throwable cause() {
      return this.cause;
   }

   public SslHandshakeCompletionEvent() {
      this.cause = null;
   }

   public boolean isSuccess() {
      return this.cause == null;
   }

   public SslHandshakeCompletionEvent(Throwable var1) {
      if (var1 == null) {
         throw new NullPointerException("cause");
      } else {
         this.cause = var1;
      }
   }
}
