package io.netty.handler.ssl;

import net.minecraft.block.BlockDoubleStoneSlab;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.client.resources.SkinManager;
import net.optifine.http.HttpPipelineRequest;

public class SslHandshakeCompletionEvent {
   public static SslHandshakeCompletionEvent SUCCESS = new SslHandshakeCompletionEvent();
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
