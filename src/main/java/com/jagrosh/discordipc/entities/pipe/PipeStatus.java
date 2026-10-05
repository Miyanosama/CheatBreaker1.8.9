package com.jagrosh.discordipc.entities.pipe;

public enum PipeStatus {
      UNINITIALIZED,
      CONNECTING,
      CONNECTED,
      CLOSED,
      DISCONNECTED;
   public static PipeStatus[] recoveredField2477 = new PipeStatus[]{
      UNINITIALIZED, PipeStatus.CONNECTING, PipeStatus.CONNECTED, CLOSED, DISCONNECTED
   };

   public static PipeStatus method_13419(String var0) {
      return Enum.valueOf(PipeStatus.class, var0);
   }
}
