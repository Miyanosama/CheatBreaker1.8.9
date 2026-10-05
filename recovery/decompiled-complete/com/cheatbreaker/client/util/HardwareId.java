package com.cheatbreaker.client.util;

import java.security.MessageDigest;
import recovered.unidentified.UnidentifiedEnum3979;

public class HardwareId {
   public UnidentifiedEnum3979 field_0000;

   public static String method_10533() {
      try {
         String var0 = System.getenv("COMPUTERNAME")
            + System.getProperty("user.name")
            + System.getenv("PROCESSOR_IDENTIFIER")
            + System.getenv("PROCESSOR_LEVEL");
         MessageDigest var1 = MessageDigest.getInstance("MD5");
         var1.update(var0.getBytes());
         StringBuffer var2 = new StringBuffer();
         byte[] var3 = var1.digest();

         for (byte var7 : var3) {
            String var8 = Integer.toHexString(255 & var7);
            if (var8.length() == 1) {
               var2.append('0');
            }

            var2.append(var8);
         }

         return var2.toString();
      } catch (Exception var9) {
         var9.printStackTrace();
         return "[CB] Error retrieving Hardware ID";
      }
   }
}
