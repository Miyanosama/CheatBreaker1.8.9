package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public class NetUtil {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(NetUtil.class);
   public static Inet6Address LOCALHOST6;
   public static InetAddress LOCALHOST;
   public static NetworkInterface LOOPBACK_IF;
   public static Inet4Address LOCALHOST4;
   public static int SOMAXCONN;

   public static boolean isValidIp4Word(String var0) {
      if (var0.length() >= 1 && var0.length() <= 3) {
         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var1 = var0.charAt(var2);
            if (var1 < '0' || var1 > '9') {
               return false;
            }
         }

         return Integer.parseInt(var0) <= 255;
      } else {
         return false;
      }
   }

   public static boolean isValidIpV6Address(String var0) {
      int var1 = var0.length();
      boolean var2 = false;
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;
      StringBuilder var6 = new StringBuilder();
      char var7 = 0;
      byte var9 = 0;
      if (var1 < 2) {
         return false;
      } else {
         for (int var10 = 0; var10 < var1; var10++) {
            char var8 = var7;
            var7 = var0.charAt(var10);
            switch (var7) {
               case '%':
                  if (var3 == 0) {
                     return false;
                  }

                  var5++;
                  if (var10 + 1 >= var1) {
                     return false;
                  }

                  try {
                     if (Integer.parseInt(var0.substring(var10 + 1)) < 0) {
                        return false;
                     }
                     break;
                  } catch (NumberFormatException var12) {
                     return false;
                  }
               case '.':
                  if (++var4 > 3) {
                     return false;
                  }

                  if (!isValidIp4Word(var6.toString())) {
                     return false;
                  }

                  if (var3 != 6 && !var2) {
                     return false;
                  }

                  if (var3 == 7 && var0.charAt(var9) != ':' && var0.charAt(1 + var9) != ':') {
                     return false;
                  }

                  var6.delete(0, var6.length());
                  break;
               case ':':
                  if (var10 == var9 && (var0.length() <= var10 || var0.charAt(var10 + 1) != ':')) {
                     return false;
                  }

                  if (++var3 > 7) {
                     return false;
                  }

                  if (var4 > 0) {
                     return false;
                  }

                  if (var8 == ':') {
                     if (var2) {
                        return false;
                     }

                     var2 = true;
                  }

                  var6.delete(0, var6.length());
                  break;
               case '[':
                  if (var10 != 0) {
                     return false;
                  }

                  if (var0.charAt(var1 - 1) != ']') {
                     return false;
                  }

                  var9 = 1;
                  if (var1 < 4) {
                     return false;
                  }
                  break;
               case ']':
                  if (var10 != var1 - 1) {
                     return false;
                  }

                  if (var0.charAt(0) != '[') {
                     return false;
                  }
                  break;
               default:
                  if (var5 == 0) {
                     if (var6 != null && var6.length() > 3) {
                        return false;
                     }

                     if (!isValidHexChar(var7)) {
                        return false;
                     }
                  }

                  var6.append(var7);
            }
         }

         if (var4 > 0) {
            if (var4 != 3 || !isValidIp4Word(var6.toString()) || var3 >= 7) {
               return false;
            }
         } else {
            if (var3 != 7 && !var2) {
               return false;
            }

            if (var5 == 0 && var6.length() == 0 && var0.charAt(var1 - 1 - var9) == ':' && var0.charAt(var1 - 2 - var9) != ':') {
               return false;
            }
         }

         return true;
      }
   }

   public static boolean isValidHexChar(char var0) {
      return var0 >= '0' && var0 <= '9' || var0 >= 'A' && var0 <= 'F' || var0 >= 'a' && var0 <= 'f';
   }

   public static boolean isValidIpV4Address(String var0) {
      int var1 = 0;
      int var3 = var0.length();
      if (var3 > 15) {
         return false;
      } else {
         StringBuilder var5 = new StringBuilder();

         for (int var2 = 0; var2 < var3; var2++) {
            char var4 = var0.charAt(var2);
            if (var4 == '.') {
               if (++var1 > 3) {
                  return false;
               }

               if (var5.length() == 0) {
                  return false;
               }

               if (Integer.parseInt(var5.toString()) > 255) {
                  return false;
               }

               var5.delete(0, var5.length());
            } else {
               if (!Character.isDigit(var4)) {
                  return false;
               }

               if (var5.length() > 2) {
                  return false;
               }

               var5.append(var4);
            }
         }

         return var5.length() != 0 && Integer.parseInt(var5.toString()) <= 255 ? var1 == 3 : false;
      }
   }

   public static byte[] createByteArrayFromIpAddressString(String var0) {
      if (isValidIpV4Address(var0)) {
         StringTokenizer var10 = new StringTokenizer(var0, ".");
         byte[] var13 = new byte[4];

         for (int var14 = 0; var14 < 4; var14++) {
            String var11 = var10.nextToken();
            int var12 = Integer.parseInt(var11);
            var13[var14] = (byte)var12;
         }

         return var13;
      } else if (!isValidIpV6Address(var0)) {
         return null;
      } else {
         if (var0.charAt(0) == '[') {
            var0 = var0.substring(1, var0.length() - 1);
         }

         StringTokenizer var1 = new StringTokenizer(var0, ":.", true);
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         String var4 = "";
         String var5 = "";
         int var6 = -1;

         while (var1.hasMoreTokens()) {
            var5 = var4;
            var4 = var1.nextToken();
            if (":".equals(var4)) {
               if (":".equals(var5)) {
                  var6 = var2.size();
               } else if (!var5.isEmpty()) {
                  var2.add(var5);
               }
            } else if (".".equals(var4)) {
               var3.add(var5);
            }
         }

         if (":".equals(var5)) {
            if (":".equals(var4)) {
               var6 = var2.size();
            } else {
               var2.add(var4);
            }
         } else if (".".equals(var5)) {
            var3.add(var4);
         }

         byte var7 = 8;
         if (!var3.isEmpty()) {
            var7 -= 2;
         }

         if (var6 != -1) {
            int var8 = var7 - var2.size();

            for (int var9 = 0; var9 < var8; var9++) {
               var2.add(var6, "0");
            }
         }

         byte[] var15 = new byte[16];

         for (int var16 = 0; var16 < var2.size(); var16++) {
            convertToBytes((String)var2.get(var16), var15, var16 * 2);
         }

         for (int var17 = 0; var17 < var3.size(); var17++) {
            var15[var17 + 12] = (byte)(Integer.parseInt((String)var3.get(var17)) & 0xFF);
         }

         return var15;
      }
   }

   static {
      byte[] var0 = new byte[]{127, 0, 0, 1};
      byte[] var1 = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
      Inet4Address var2 = null;

      try {
         var2 = (Inet4Address)InetAddress.getByAddress(var0);
      } catch (Exception var38) {
         PlatformDependent.throwException(var38);
      }

      LOCALHOST4 = var2;
      Inet6Address var3 = null;

      try {
         var3 = (Inet6Address)InetAddress.getByAddress(var1);
      } catch (Exception var37) {
         PlatformDependent.throwException(var37);
      }

      LOCALHOST6 = var3;
      ArrayList var4 = new ArrayList();

      try {
         Enumeration var5 = NetworkInterface.getNetworkInterfaces();

         while (var5.hasMoreElements()) {
            NetworkInterface var6 = (NetworkInterface)var5.nextElement();
            if (var6.getInetAddresses().hasMoreElements()) {
               var4.add(var6);
            }
         }
      } catch (SocketException var42) {
         logger.warn("Failed to retrieve the list of available network interfaces", (Throwable)var42);
      }

      NetworkInterface var43 = null;
      java.net.InetAddress var44 = null;

      label310:
      for (NetworkInterface var8 : (Iterable<NetworkInterface>)(Iterable<?>)(var4)) {
         Enumeration var9 = var8.getInetAddresses();

         while (var9.hasMoreElements()) {
            InetAddress var10 = (InetAddress)var9.nextElement();
            if (var10.isLoopbackAddress()) {
               var43 = var8;
               var44 = var10;
               break label310;
            }
         }
      }

      if (var43 == null) {
         try {
            for (NetworkInterface var47 : (Iterable<NetworkInterface>)(Iterable<?>)(var4)) {
               if (var47.isLoopback()) {
                  Enumeration var49 = var47.getInetAddresses();
                  if (var49.hasMoreElements()) {
                     var43 = var47;
                     var44 = (InetAddress)var49.nextElement();
                     break;
                  }
               }
            }

            if (var43 == null) {
               logger.warn("Failed to find the loopback interface");
            }
         } catch (SocketException var41) {
            logger.warn("Failed to find the loopback interface", (Throwable)var41);
         }
      }

      if (var43 != null) {
         logger.debug("Loopback interface: {} ({}, {})", var43.getName(), var43.getDisplayName(), var44.getHostAddress());
      } else if (var44 == null) {
         try {
            if (NetworkInterface.getByInetAddress(LOCALHOST6) != null) {
               logger.debug("Using hard-coded IPv6 localhost address: {}", var3);
               var44 = var3;
            }
         } catch (Exception var36) {
         } finally {
            if (var44 == null) {
               logger.debug("Using hard-coded IPv4 localhost address: {}", var2);
            }
         }
      }

      LOOPBACK_IF = var43;
      LOCALHOST = (InetAddress)var44;
      int var46 = PlatformDependent.isWindows() ? 200 : 128;
      File var48 = new File("/proc/sys/net/core/somaxconn");
      if (var48.exists()) {
         BufferedReader var50 = null;

         try {
            var50 = new BufferedReader(new FileReader(var48));
            var46 = Integer.parseInt(var50.readLine());
            if (logger.isDebugEnabled()) {
               logger.debug("{}: {}", var48, var46);
            }
         } catch (Exception var35) {
            logger.debug("Failed to get SOMAXCONN from: {}", var48, var35);
         } finally {
            if (var50 != null) {
               try {
                  var50.close();
               } catch (Exception var34) {
               }
            }
         }
      } else if (logger.isDebugEnabled()) {
         logger.debug("{}: {} (non-existent)", var48, var46);
      }

      SOMAXCONN = var46;
   }

   public static int getIntValue(char var0) {
      switch (var0) {
         case '0':
            return 0;
         case '1':
            return 1;
         case '2':
            return 2;
         case '3':
            return 3;
         case '4':
            return 4;
         case '5':
            return 5;
         case '6':
            return 6;
         case '7':
            return 7;
         case '8':
            return 8;
         case '9':
            return 9;
         default:
            var0 = Character.toLowerCase(var0);
            switch (var0) {
               case 'a':
                  return 10;
               case 'b':
                  return 11;
               case 'c':
                  return 12;
               case 'd':
                  return 13;
               case 'e':
                  return 14;
               case 'f':
                  return 15;
               default:
                  return 0;
            }
      }
   }

   public static void convertToBytes(String var0, byte[] var1, int var2) {
      int var3 = var0.length();
      int var4 = 0;
      var1[var2] = 0;
      var1[var2 + 1] = 0;
      if (var3 > 3) {
         int var5 = getIntValue(var0.charAt(var4++));
         var1[var2] = (byte)(var1[var2] | var5 << 4);
      }

      if (var3 > 2) {
         int var6 = getIntValue(var0.charAt(var4++));
         var1[var2] = (byte)(var1[var2] | var6);
      }

      if (var3 > 1) {
         int var7 = getIntValue(var0.charAt(var4++));
         var1[var2 + 1] = (byte)(var1[var2 + 1] | var7 << 4);
      }

      int var8 = getIntValue(var0.charAt(var4));
      var1[var2 + 1] = (byte)(var1[var2 + 1] | var8 & 15);
   }
}
