package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.ControlFrame;
import org.java_websocket.framing.DataFrame;
import org.java_websocket.framing.Framedata;

public abstract class CompressionExtension extends DefaultExtension {
   @Override
   public void isFrameValid(Framedata var1) throws org.java_websocket.exceptions.InvalidDataException {
      if (!(var1 instanceof DataFrame) || !var1.isRSV2() && !var1.isRSV3()) {
         if (var1 instanceof ControlFrame && (var1.isRSV1() || var1.isRSV2() || var1.isRSV3())) {
            throw new InvalidFrameException("bad rsv RSV1: " + var1.isRSV1() + " RSV2: " + var1.isRSV2() + " RSV3: " + var1.isRSV3());
         }
      } else {
         throw new InvalidFrameException("bad rsv RSV1: " + var1.isRSV1() + " RSV2: " + var1.isRSV2() + " RSV3: " + var1.isRSV3());
      }
   }
}
