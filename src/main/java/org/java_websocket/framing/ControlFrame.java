package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidFrameException;

public abstract class ControlFrame extends FramedataImpl1 {
   @Override
   public void isValid() throws org.java_websocket.exceptions.InvalidDataException {
      if (!this.isFin()) {
         throw new InvalidFrameException("Control frame cant have fin==false set");
      } else if (this.isRSV1()) {
         throw new InvalidFrameException("Control frame cant have rsv1==true set");
      } else if (this.isRSV2()) {
         throw new InvalidFrameException("Control frame cant have rsv2==true set");
      } else if (this.isRSV3()) {
         throw new InvalidFrameException("Control frame cant have rsv3==true set");
      }
   }

   public ControlFrame(Opcode var1) {
      super(var1);
   }
}
