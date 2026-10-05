package com.jagrosh.discordipc.entities;

public enum Packet$OpCode {
      HANDSHAKE,
      FRAME,
      CLOSE,
      PING,
      PONG;
   public static Packet$OpCode[] recoveredField3397 = new Packet$OpCode[]{
      Packet$OpCode.HANDSHAKE, FRAME, CLOSE, Packet$OpCode.PING, Packet$OpCode.PONG
   };

   public static Packet$OpCode method_13364(String var0) {
      return Enum.valueOf(Packet$OpCode.class, var0);
   }
}
