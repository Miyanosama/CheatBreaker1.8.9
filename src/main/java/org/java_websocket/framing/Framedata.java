package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;

public interface Framedata {
   boolean isRSV2();

   boolean isRSV3();

   void append(Framedata var1);

   boolean isFin();

   boolean isRSV1();

   ByteBuffer getPayloadData();

   Opcode getOpcode();

   boolean getTransfereMasked();
}
