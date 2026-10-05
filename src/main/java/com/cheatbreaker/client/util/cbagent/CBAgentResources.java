package com.cheatbreaker.client.util.cbagent;

public class CBAgentResources {
   public static native boolean existsBytesNative(String var0);

   public static native byte[] getBytesNative(String var0);

   public static boolean existsBytes(String var0) {
      boolean var1 = false;

      try {
         var1 = existsBytesNative(var0);
      } catch (UnsatisfiedLinkError var3) {
      }

      return var1;
   }
}
