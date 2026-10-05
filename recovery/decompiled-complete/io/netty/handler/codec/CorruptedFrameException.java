package io.netty.handler.codec;

import io.netty.handler.codec.http.DefaultHttpMessage;
import net.minecraft.client.renderer.entity.RenderWitch;
import recovered.unidentified.UnidentifiedEnum3640;

public class CorruptedFrameException extends DecoderException {
   public static long serialVersionUID;
   public DefaultHttpMessage __junk770903565617842910;
   public RenderWitch __junk8646882849712015863;
   public UnidentifiedEnum3640 __junk1082055317369234792;

   public CorruptedFrameException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public CorruptedFrameException(Throwable var1) {
      super(var1);
   }

   public CorruptedFrameException(String var1) {
      super(var1);
   }

   public CorruptedFrameException() {
   }
}
