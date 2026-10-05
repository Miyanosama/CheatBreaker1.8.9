package io.netty.handler.codec.spdy;

import io.netty.handler.codec.CorruptedFrameException;
import net.optifine.shaders.ProgramStage;

public class SpdySessionStatus implements Comparable<SpdySessionStatus> {
   public static SpdySessionStatus INTERNAL_ERROR = new SpdySessionStatus(2, "INTERNAL_ERROR");
   public CorruptedFrameException __junk1593786479681673890;
   public String statusPhrase;
   public int code;
   public static SpdySessionStatus OK = new SpdySessionStatus(0, "OK");
   public static SpdySessionStatus PROTOCOL_ERROR = new SpdySessionStatus(1, "PROTOCOL_ERROR");
   public ProgramStage __junk198468329066221716;

   public int compareTo(SpdySessionStatus var1) {
      return this.code() - var1.code();
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof SpdySessionStatus) ? false : this.code() == ((SpdySessionStatus)var1).code();
   }

   public String statusPhrase() {
      return this.statusPhrase;
   }

   public static SpdySessionStatus valueOf(int var0) {
      switch (var0) {
         case 0:
            return OK;
         case 1:
            return PROTOCOL_ERROR;
         case 2:
            return INTERNAL_ERROR;
         default:
            return new SpdySessionStatus(var0, "UNKNOWN (" + var0 + ')');
      }
   }

   public SpdySessionStatus(int var1, String var2) {
      if (var2 == null) {
         throw new NullPointerException("statusPhrase");
      } else {
         this.code = var1;
         this.statusPhrase = var2;
      }
   }

   @Override
   public String toString() {
      return this.statusPhrase();
   }

   @Override
   public int hashCode() {
      return this.code();
   }

   public int code() {
      return this.code;
   }
}
