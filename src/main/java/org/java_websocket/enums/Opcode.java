package org.java_websocket.enums;

public enum Opcode {
      CONTINUOUS,
      TEXT,
      BINARY,
      PING,
      PONG,
      CLOSING;
   public static Opcode[] $VALUES = new Opcode[]{CONTINUOUS, Opcode.TEXT, BINARY, Opcode.PING, Opcode.PONG, Opcode.CLOSING};
}
