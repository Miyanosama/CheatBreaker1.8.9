package io.netty.handler.codec;

import com.cheatbreaker.client.module.AbstractModule$1;
import io.netty.util.Signal;
import net.minecraft.scoreboard.Score$1;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import recovered.unidentified.UnidentifiedClass3810;

public class DecoderResult {
   public StructureVillagePieces __junk85541551583156332;
   public Score$1 __junk5780564867335743498;
   public static Signal SIGNAL_UNFINISHED = Signal.valueOf(DecoderResult.class.getName() + ".UNFINISHED");
   public static DecoderResult UNFINISHED = new DecoderResult(SIGNAL_UNFINISHED);
   public Throwable cause;
   public AbstractModule$1 __junk4090897145334938403;
   public static DecoderResult SUCCESS = new DecoderResult(DecoderResult.SIGNAL_SUCCESS);
   public UnidentifiedClass3810 __junk6097101145245777414;
   public static Signal SIGNAL_SUCCESS = Signal.valueOf(DecoderResult.class.getName() + ".SUCCESS");

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
