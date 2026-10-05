package org.java_websocket.framing;

import io.netty.channel.DefaultMessageSizeEstimator;
import org.java_websocket.enums.Opcode;

public class ContinuousFrame extends DataFrame {
   public DefaultMessageSizeEstimator field_0000;

   public ContinuousFrame() {
      super(Opcode.CONTINUOUS);
   }
}
