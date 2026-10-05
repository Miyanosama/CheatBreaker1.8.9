package org.apache.log4j.helpers;

import io.netty.handler.codec.compression.Snappy$State;
import io.netty.handler.codec.rtsp.RtspResponseDecoder;
import io.netty.handler.codec.socks.SocksRequest;
import io.netty.handler.timeout.ReadTimeoutHandler$ReadTimeoutTask;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.inventory.ContainerDispenser;
import org.apache.log4j.Level;

public class UtilLoggingLevel extends Level {
   public static UtilLoggingLevel FINE = new UtilLoggingLevel(13000, "FINE", 7);
   public static int field_0011;
   public ContainerDispenser field_0009;
   public static int field_0008;
   public static UtilLoggingLevel INFO = new UtilLoggingLevel(20000, "INFO", 5);
   public static int field_0001;
   public static long field_0020;
   public RtspResponseDecoder field_0014;
   public Snappy$State field_0017;
   public static UtilLoggingLevel SEVERE = new UtilLoggingLevel(22000, "SEVERE", 0);
   public SocksRequest field_0010;
   public static int field_0012;
   public ReadTimeoutHandler$ReadTimeoutTask field_0000;
   public static UtilLoggingLevel CONFIG = new UtilLoggingLevel(14000, "CONFIG", 6);
   public static UtilLoggingLevel WARNING = new UtilLoggingLevel(21000, "WARNING", 4);
   public static int field_0015;
   public CrashReportCategory field_0016;
   public static int field_0003;
   public static UtilLoggingLevel FINEST = new UtilLoggingLevel(11000, "FINEST", 9);
   public static UtilLoggingLevel FINER = new UtilLoggingLevel(12000, "FINER", 8);
   public static int field_0018;

   public static UtilLoggingLevel toLevel(int var0, UtilLoggingLevel var1) {
      switch (var0) {
         case 11000:
            return FINEST;
         case 12000:
            return FINER;
         case 13000:
            return FINE;
         case 14000:
            return CONFIG;
         case 20000:
            return INFO;
         case 21000:
            return WARNING;
         case 22000:
            return SEVERE;
         default:
            return var1;
      }
   }

   public static List getAllPossibleLevels() {
      ArrayList var0 = new ArrayList();
      var0.add(FINE);
      var0.add(FINER);
      var0.add(FINEST);
      var0.add(INFO);
      var0.add(CONFIG);
      var0.add(WARNING);
      var0.add(SEVERE);
      return var0;
   }

   public static Level toLevel(String var0) {
      return toLevel(var0, Level.DEBUG);
   }

   public static Level toLevel(String var0, Level var1) {
      if (var0 == null) {
         return var1;
      } else {
         String var2 = var0.toUpperCase();
         if (var2.equals("SEVERE")) {
            return SEVERE;
         } else if (var2.equals("WARNING")) {
            return WARNING;
         } else if (var2.equals("INFO")) {
            return INFO;
         } else if (var2.equals("CONFI")) {
            return CONFIG;
         } else if (var2.equals("FINE")) {
            return FINE;
         } else if (var2.equals("FINER")) {
            return FINER;
         } else {
            return (Level)(var2.equals("FINEST") ? FINEST : var1);
         }
      }
   }

   public UtilLoggingLevel(int var1, String var2, int var3) {
      super(var1, var2, var3);
   }

   public static Level toLevel(int var0) {
      return toLevel(var0, FINEST);
   }
}
