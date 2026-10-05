package io.netty.handler.codec;

import io.netty.handler.codec.http.DefaultHttpMessage;
import net.minecraft.client.renderer.entity.RenderWitch;
import com.cheatbreaker.client.ui.mainmenu.MainMenuMode;

public class CorruptedFrameException extends DecoderException {
   public static final long serialVersionUID = 3918052232492988408L;

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
