package io.netty.handler.codec;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.gui.GuiPageButtonList$GuiButtonEntry;
import recovered.unidentified.UnidentifiedClass4579;

public class TooLongFrameException extends DecoderException {
   public UnidentifiedClass4579 __junk6056861023353877145;
   public static long serialVersionUID;
   public GuiPageButtonList$GuiButtonEntry __junk99410379066737334;
   public MovingSound __junk1797464945915285877;

   public TooLongFrameException() {
   }

   public TooLongFrameException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public TooLongFrameException(String var1) {
      super(var1);
   }

   public TooLongFrameException(Throwable var1) {
      super(var1);
   }
}
