package com.jagrosh.discordipc;

// $VF: synthetic class
public class IPCClient$1 {
   public static int[] recoveredField658 = new int[IPCClient$Event.values().length];

   static {
      try {
         recoveredField658[IPCClient$Event.NULL.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         recoveredField658[IPCClient$Event.ERROR.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         recoveredField658[IPCClient$Event.ACTIVITY_JOIN.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         recoveredField658[IPCClient$Event.ACTIVITY_SPECTATE.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         recoveredField658[IPCClient$Event.ACTIVITY_JOIN_REQUEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         recoveredField658[IPCClient$Event.UNKNOWN.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
