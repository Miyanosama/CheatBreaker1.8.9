package org.java_websocket.framing;

import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.renderer.GlStateManager$TextureState;
import org.java_websocket.enums.Opcode;

public class PingFrame extends ControlFrame {
   public TickEvent field_0000;
   public GlStateManager$TextureState field_0001;

   public PingFrame() {
      super(Opcode.PING);
   }
}
