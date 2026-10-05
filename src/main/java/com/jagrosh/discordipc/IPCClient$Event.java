package com.jagrosh.discordipc;

public enum IPCClient$Event {
      NULL(false),
      READY(false),
      ERROR(false),
      ACTIVITY_JOIN(true),
      ACTIVITY_SPECTATE(true),
      ACTIVITY_JOIN_REQUEST(true),
      UNKNOWN(false);
   public boolean recoveredField3712;
   public static IPCClient$Event[] recoveredField3713 = new IPCClient$Event[]{
      IPCClient$Event.NULL,
      IPCClient$Event.READY,
      IPCClient$Event.ERROR,
      IPCClient$Event.ACTIVITY_JOIN,
      IPCClient$Event.ACTIVITY_SPECTATE,
      ACTIVITY_JOIN_REQUEST,
      UNKNOWN
   };

   public boolean method_13329() {
      return this.recoveredField3712;
   }

   public static IPCClient$Event method_13330(String var0) {
      if (var0 == null) {
         return NULL;
      } else {
         for (IPCClient$Event var4 : values()) {
            if (var4 != UNKNOWN && var4.name().equalsIgnoreCase(var0)) {
               return var4;
            }
         }

         return UNKNOWN;
      }
   }

   IPCClient$Event(boolean var3) {
      this.recoveredField3712 = var3;
   }

   public static IPCClient$Event method_13331(String var0) {
      return Enum.valueOf(IPCClient$Event.class, var0);
   }
}
