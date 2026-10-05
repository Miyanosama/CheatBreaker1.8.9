package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;

public class PongFrame extends ControlFrame {
   public PongFrame() {
      super(Opcode.PONG);
   }

   public PongFrame(PingFrame var1) {
      super(Opcode.PONG);
      this.setPayload(var1.getPayloadData());
   }
}
