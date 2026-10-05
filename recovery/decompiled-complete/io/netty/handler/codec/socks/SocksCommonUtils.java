package io.netty.handler.codec.socks;

import io.netty.util.internal.StringUtil;
import net.minecraft.client.stream.MetadataPlayerDeath;

public class SocksCommonUtils {
   public static int FIRST_ADDRESS_OCTET_SHIFT;
   public static int SECOND_ADDRESS_OCTET_SHIFT;
   public static SocksRequest UNKNOWN_SOCKS_REQUEST = new UnknownSocksRequest();
   public static int THIRD_ADDRESS_OCTET_SHIFT;
   public static char[] ipv6conseqZeroFiller = new char[]{':', ':'};
   public static char ipv6hextetSeparator;
   public static int XOR_DEFAULT_VALUE;
   public MetadataPlayerDeath __junk3700626438421132914;
   public static SocksResponse UNKNOWN_SOCKS_RESPONSE = new UnknownSocksResponse();

   public static void ipv6toStr(StringBuilder var0, byte[] var1, int var2, int var3) {
      var3--;

      int var4;
      for (var4 = var2; var4 < var3; var4++) {
         appendHextet(var0, var1, var4);
         var0.append(':');
      }

      appendHextet(var0, var1, var4);
   }

   public static void appendHextet(StringBuilder var0, byte[] var1, int var2) {
      StringUtil.toHexString(var0, var1, var2 << 1, 2);
   }

   public static String ipv6toCompressedForm(byte[] var0) {
      if (!$assertionsDisabled && var0.length != 16) {
         throw new AssertionError();
      } else {
         int var1 = -1;
         int var2 = 0;
         int var3 = 0;

         while (var3 < 8) {
            int var4 = var3 * 2;

            int var5;
            for (var5 = 0; var4 < var0.length && var0[var4] == 0 && var0[var4 + 1] == 0; var5++) {
               var4 += 2;
            }

            if (var5 > var2) {
               var1 = var3;
               var2 = var5;
            }

            var3 = var4 / 2 + 1;
         }

         if (var1 != -1 && var2 >= 2) {
            StringBuilder var6 = new StringBuilder(39);
            ipv6toStr(var6, var0, 0, var1);
            var6.append(ipv6conseqZeroFiller);
            ipv6toStr(var6, var0, var1 + var2, 8);
            return var6.toString();
         } else {
            return ipv6toStr(var0);
         }
      }
   }

   public static String ipv6toStr(byte[] var0) {
      if (!$assertionsDisabled && var0.length != 16) {
         throw new AssertionError();
      } else {
         StringBuilder var1 = new StringBuilder(39);
         ipv6toStr(var1, var0, 0, 8);
         return var1.toString();
      }
   }

   public static String intToIp(int var0) {
      return String.valueOf(var0 >> 24 & 0xFF) + '.' + (var0 >> 16 & 0xFF) + '.' + (var0 >> 8 & 0xFF) + '.' + (var0 & 0xFF);
   }
}
