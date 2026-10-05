package org.java_websocket.framing;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.Vec3;
import org.java_websocket.enums.Opcode;

public class PongFrame extends ControlFrame {
   public Vec3 field_0000;
   public EnumDyeColor field_0001;

   public PongFrame() {
      super(Opcode.PONG);
   }

   public PongFrame(PingFrame var1) {
      super(Opcode.PONG);
      this.setPayload(var1.getPayloadData());
   }
}
