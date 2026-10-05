package io.netty.handler.codec;

import io.netty.util.Signal;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import com.cheatbreaker.client.event.type.PlayerModelRenderEvent;

public class DecoderResult {
   public static Signal SIGNAL_UNFINISHED = Signal.valueOf(DecoderResult.class.getName() + ".UNFINISHED");
   public static Signal SIGNAL_SUCCESS = Signal.valueOf(DecoderResult.class.getName() + ".SUCCESS");
   public Throwable cause;
   public static DecoderResult UNFINISHED = new DecoderResult(SIGNAL_UNFINISHED);
   public static DecoderResult SUCCESS = new DecoderResult(DecoderResult.SIGNAL_SUCCESS);

   public boolean isFinished() {
      return this.cause != SIGNAL_UNFINISHED;
   }

   public DecoderResult(Throwable var1) {
      if (var1 == null) {
         throw new NullPointerException("cause");
      } else {
         this.cause = var1;
      }
   }

   public boolean isFailure() {
      return this.cause != SIGNAL_SUCCESS && this.cause != SIGNAL_UNFINISHED;
   }

   public Throwable cause() {
      return this.isFailure() ? this.cause : null;
   }

   public static DecoderResult failure(Throwable var0) {
      if (var0 == null) {
         throw new NullPointerException("cause");
      } else {
         return new DecoderResult(var0);
      }
   }

   @Override
   public String toString() {
      if (this.isFinished()) {
         if (this.isSuccess()) {
            return "success";
         } else {
            String var1 = this.cause().toString();
            StringBuilder var2 = new StringBuilder(var1.length() + 17);
            var2.append("failure(");
            var2.append(var1);
            var2.append(')');
            return var2.toString();
         }
      } else {
         return "unfinished";
      }
   }

   public boolean isSuccess() {
      return this.cause == SIGNAL_SUCCESS;
   }
}
