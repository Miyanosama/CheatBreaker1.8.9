package org.apache.log4j.helpers;

import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import net.minecraft.entity.EntityMinecartCommandBlock;

public class SyslogWriter extends Writer {
   public EntityMinecartCommandBlock field_0003;
   public InetAddress address;
   public DatagramSocket ds;
   public int SYSLOG_PORT = 514;
   public int port;
   public static String syslogHost;

   public void write(char[] var1, int var2, int var3) {
      this.write(new String(var1, var2, var3));
   }

   public SyslogWriter(String var1) {
      syslogHost = var1;
      if (var1 == null) {
         throw new NullPointerException("syslogHost");
      } else {
         String var2 = var1;
         int var3 = -1;
         if (var1.indexOf("[") != -1 || var1.indexOf(58) == var1.lastIndexOf(58)) {
            try {
               URL var4 = new URL("http://" + var2);
               if (var4.getHost() != null) {
                  var2 = var4.getHost();
                  if (var2.startsWith("[") && var2.charAt(var2.length() - 1) == ']') {
                     var2 = var2.substring(1, var2.length() - 1);
                  }

                  var3 = var4.getPort();
               }
            } catch (MalformedURLException var7) {
               LogLog.error("Malformed URL: will attempt to interpret as InetAddress.", var7);
            }
         }

         if (var3 == -1) {
            var3 = 514;
         }

         this.port = var3;

         try {
            this.address = InetAddress.getByName(var2);
         } catch (UnknownHostException var6) {
            LogLog.error("Could not find " + var2 + ". All logging will FAIL.", var6);
         }

         try {
            this.ds = new DatagramSocket();
         } catch (SocketException var5) {
            var5.printStackTrace();
            LogLog.error("Could not instantiate DatagramSocket to " + var2 + ". All logging will FAIL.", var5);
         }
      }
   }

   public void close() {
      if (this.ds != null) {
         this.ds.close();
      }
   }

   public void write(String var1) {
      if (this.ds != null && this.address != null) {
         byte[] var2 = var1.getBytes();
         int var3 = var2.length;
         if (var3 >= 1024) {
            var3 = 1024;
         }

         DatagramPacket var4 = new DatagramPacket(var2, var3, this.address, this.port);
         this.ds.send(var4);
      }
   }

   public void flush() {
   }
}
