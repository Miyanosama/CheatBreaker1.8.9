package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;

public abstract class DataFrame extends FramedataImpl1 {
   public DataFrame(Opcode var1) {
      super(var1);
   }

   @Override
   public void isValid() throws org.java_websocket.exceptions.InvalidDataException {
   }
}
