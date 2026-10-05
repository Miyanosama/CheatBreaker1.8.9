package org.apache.log4j.net;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.SyslogQuietWriter;
import org.apache.log4j.helpers.SyslogWriter;
import org.apache.log4j.spi.LoggingEvent;

public class SyslogAppender extends AppenderSkeleton {
   public String localHostname;
   public static final int recoveredField1931 = 136;
   public static final int recoveredField1932 = 184;
   public int syslogFacility = 8;
   public static final int recoveredField1933 = 160;
   public boolean header;
   public static final int recoveredField1934 = 168;
   public static final String recoveredField1935 = "    ";
   public static final int recoveredField1936 = 8;
   public static final int recoveredField1937 = 176;
   public static final int recoveredField1938 = 128;
   public String syslogHost;
   public static final int recoveredField1939 = 1;
   public static final int recoveredField1940 = 152;
   public static final int recoveredField1941 = 40;
   public static final int recoveredField1942 = 56;
   public static final int recoveredField1943 = 64;
   public static final int recoveredField1944 = 144;
   public boolean layoutHeaderChecked;
   public static final int recoveredField1945 = 0;
   public String facilityStr;
   public boolean facilityPrinting = false;
   public static final int recoveredField1946 = 88;
   public static final int recoveredField1947 = 16;
   public static final int recoveredField1948 = 48;
   public static final int recoveredField1949 = 72;
   public static final int recoveredField1950 = 80;
   public SimpleDateFormat dateFormat;
   public static final int recoveredField1951 = 32;
   public static final int recoveredField1952 = 24;
   public static final int recoveredField1953 = 0;
   public SyslogQuietWriter sqw;

   public void sendLayoutMessage(String var1) {
      if (this.sqw != null) {
         String var2 = var1;
         String var3 = this.getPacketHeader(new Date().getTime());
         if (this.facilityPrinting || var3.length() > 0) {
            StringBuffer var4 = new StringBuffer(var3);
            if (this.facilityPrinting) {
               var4.append(this.facilityStr);
            }

            var4.append(var1);
            var2 = var4.toString();
         }

         this.sqw.setLevel(6);
         this.sqw.write(var2);
      }
   }

   public static int getFacility(String var0) {
      if (var0 != null) {
         var0 = var0.trim();
      }

      if ("KERN".equalsIgnoreCase(var0)) {
         return 0;
      } else if ("USER".equalsIgnoreCase(var0)) {
         return 8;
      } else if ("MAIL".equalsIgnoreCase(var0)) {
         return 16;
      } else if ("DAEMON".equalsIgnoreCase(var0)) {
         return 24;
      } else if ("AUTH".equalsIgnoreCase(var0)) {
         return 32;
      } else if ("SYSLOG".equalsIgnoreCase(var0)) {
         return 40;
      } else if ("LPR".equalsIgnoreCase(var0)) {
         return 48;
      } else if ("NEWS".equalsIgnoreCase(var0)) {
         return 56;
      } else if ("UUCP".equalsIgnoreCase(var0)) {
         return 64;
      } else if ("CRON".equalsIgnoreCase(var0)) {
         return 72;
      } else if ("AUTHPRIV".equalsIgnoreCase(var0)) {
         return 80;
      } else if ("FTP".equalsIgnoreCase(var0)) {
         return 88;
      } else if ("LOCAL0".equalsIgnoreCase(var0)) {
         return 128;
      } else if ("LOCAL1".equalsIgnoreCase(var0)) {
         return 136;
      } else if ("LOCAL2".equalsIgnoreCase(var0)) {
         return 144;
      } else if ("LOCAL3".equalsIgnoreCase(var0)) {
         return 152;
      } else if ("LOCAL4".equalsIgnoreCase(var0)) {
         return 160;
      } else if ("LOCAL5".equalsIgnoreCase(var0)) {
         return 168;
      } else if ("LOCAL6".equalsIgnoreCase(var0)) {
         return 176;
      } else {
         return "LOCAL7".equalsIgnoreCase(var0) ? 184 : -1;
      }
   }

   public void setFacilityPrinting(boolean var1) {
      this.facilityPrinting = var1;
   }

   public SyslogAppender(Layout var1, String var2, int var3) {
      this(var1, var3);
      this.setSyslogHost(var2);
   }

   public boolean getFacilityPrinting() {
      return this.facilityPrinting;
   }

   public void setFacility(String var1) {
      if (var1 != null) {
         this.syslogFacility = getFacility(var1);
         if (this.syslogFacility == -1) {
            System.err.println("[" + var1 + "] is an unknown syslog facility. Defaulting to [USER].");
            this.syslogFacility = 8;
         }

         this.initSyslogFacilityStr();
         if (this.sqw != null) {
            this.sqw.setSyslogFacility(this.syslogFacility);
         }
      }
   }

   public void append(LoggingEvent var1) {
      if (this.isAsSevereAsThreshold(var1.getLevel())) {
         if (this.sqw == null) {
            this.errorHandler.error("No syslog host is set for SyslogAppedender named \"" + this.name + "\".");
         } else {
            if (!this.layoutHeaderChecked) {
               if (this.layout != null && this.layout.E_() != null) {
                  this.sendLayoutMessage(this.layout.E_());
               }

               this.layoutHeaderChecked = true;
            }

            String var2 = this.getPacketHeader(var1.timeStamp);
            String var3;
            if (this.layout == null) {
               var3 = String.valueOf(var1.getMessage());
            } else {
               var3 = this.layout.format(var1);
            }

            if (this.facilityPrinting || var2.length() > 0) {
               StringBuffer var4 = new StringBuffer(var2);
               if (this.facilityPrinting) {
                  var4.append(this.facilityStr);
               }

               var4.append(var3);
               var3 = var4.toString();
            }

            this.sqw.setLevel(var1.getLevel().getSyslogEquivalent());
            if (var3.length() > 256) {
               this.splitPacket(var2, var3);
            } else {
               this.sqw.write(var3);
            }

            if (this.layout == null || this.layout.ignoresThrowable()) {
               String[] var6 = var1.getThrowableStrRep();
               if (var6 != null) {
                  for (int var5 = 0; var5 < var6.length; var5++) {
                     if (var6[var5].startsWith("\t")) {
                        this.sqw.write(var2 + "    " + var6[var5].substring(1));
                     } else {
                        this.sqw.write(var2 + var6[var5]);
                     }
                  }
               }
            }
         }
      }
   }

   public SyslogAppender(Layout var1, int var2) {
      this.header = false;
      this.dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ", Locale.ENGLISH);
      this.layoutHeaderChecked = false;
      this.layout = var1;
      this.syslogFacility = var2;
      this.initSyslogFacilityStr();
   }

   public static String getFacilityString(int var0) {
      switch (var0) {
         case 0:
            return "kern";
         case 8:
            return "user";
         case 16:
            return "mail";
         case 24:
            return "daemon";
         case 32:
            return "auth";
         case 40:
            return "syslog";
         case 48:
            return "lpr";
         case 56:
            return "news";
         case 64:
            return "uucp";
         case 72:
            return "cron";
         case 80:
            return "authpriv";
         case 88:
            return "ftp";
         case 128:
            return "local0";
         case 136:
            return "local1";
         case 144:
            return "local2";
         case 152:
            return "local3";
         case 160:
            return "local4";
         case 168:
            return "local5";
         case 176:
            return "local6";
         case 184:
            return "local7";
         default:
            return null;
      }
   }

   public void splitPacket(String var1, String var2) {
      int var3 = var2.getBytes().length;
      if (var3 <= 1019) {
         this.sqw.write(var2);
      } else {
         int var4 = var1.length() + (var2.length() - var1.length()) / 2;
         this.splitPacket(var1, var2.substring(0, var4) + "...");
         this.splitPacket(var1, var1 + "..." + var2.substring(var4));
      }
   }

   public void initSyslogFacilityStr() {
      this.facilityStr = getFacilityString(this.syslogFacility);
      if (this.facilityStr == null) {
         System.err.println("\"" + this.syslogFacility + "\" is an unknown syslog facility. Defaulting to \"USER\".");
         this.syslogFacility = 8;
         this.facilityStr = "user:";
      } else {
         this.facilityStr = this.facilityStr + ":";
      }
   }

   public void setSyslogHost(String var1) {
      this.sqw = new SyslogQuietWriter(new SyslogWriter(var1), this.syslogFacility, this.errorHandler);
      this.syslogHost = var1;
   }

   public String getLocalHostname() {
      if (this.localHostname == null) {
         try {
            InetAddress var1 = InetAddress.getLocalHost();
            this.localHostname = var1.getHostName();
         } catch (UnknownHostException var2) {
            this.localHostname = "UNKNOWN_HOST";
         }
      }

      return this.localHostname;
   }

   public synchronized void close() {
      this.closed = true;
      if (this.sqw != null) {
         try {
            if (this.layoutHeaderChecked && this.layout != null && this.layout.getFooter() != null) {
               this.sendLayoutMessage(this.layout.getFooter());
            }

            this.sqw.close();
            this.sqw = null;
         } catch (InterruptedIOException var2) {
            Thread.currentThread().interrupt();
            this.sqw = null;
         } catch (IOException var3) {
            this.sqw = null;
         }
      }
   }

   public void setHeader(boolean var1) {
      this.header = var1;
   }

   public void activateOptions() {
      if (this.header) {
         this.getLocalHostname();
      }

      if (this.layout != null && this.layout.E_() != null) {
         this.sendLayoutMessage(this.layout.E_());
      }

      this.layoutHeaderChecked = true;
   }

   public String getSyslogHost() {
      return this.syslogHost;
   }

   public SyslogAppender() {
      this.header = false;
      this.dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss ", Locale.ENGLISH);
      this.layoutHeaderChecked = false;
      this.initSyslogFacilityStr();
   }

   public String getPacketHeader(long var1) {
      if (this.header) {
         StringBuffer var3 = new StringBuffer(this.dateFormat.format(new Date(var1)));
         if (var3.charAt(4) == '0') {
            var3.setCharAt(4, ' ');
         }

         var3.append(this.getLocalHostname());
         var3.append(' ');
         return var3.toString();
      } else {
         return "";
      }
   }

   public boolean getHeader() {
      return this.header;
   }

   public boolean requiresLayout() {
      return true;
   }

   public String getFacility() {
      return getFacilityString(this.syslogFacility);
   }
}
