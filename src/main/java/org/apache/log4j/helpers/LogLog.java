package org.apache.log4j.helpers;

public class LogLog {
   public static final String recoveredField734 = "log4j.configDebug";
   public static final String recoveredField735 = "log4j:ERROR ";
   public static final String recoveredField736 = "log4j: ";
   public static final String recoveredField737 = "log4j:WARN ";
   public static final String recoveredField738 = "log4j.debug";
   public static boolean debugEnabled = false;
   public static boolean quietMode = false;

   public static void debug(String var0, Throwable var1) {
      if (debugEnabled && !quietMode) {
         System.out.println("log4j: " + var0);
         if (var1 != null) {
            var1.printStackTrace(System.out);
         }
      }
   }

   public static void error(String var0, Throwable var1) {
      if (!quietMode) {
         System.err.println("log4j:ERROR " + var0);
         if (var1 != null) {
            var1.printStackTrace();
         }
      }
   }

   static {
      String var0 = OptionConverter.getSystemProperty("log4j.debug", null);
      if (var0 == null) {
         var0 = OptionConverter.getSystemProperty("log4j.configDebug", null);
      }

      if (var0 != null) {
         debugEnabled = OptionConverter.toBoolean(var0, true);
      }
   }

   public static void error(String var0) {
      if (!quietMode) {
         System.err.println("log4j:ERROR " + var0);
      }
   }

   public static void warn(String var0) {
      if (!quietMode) {
         System.err.println("log4j:WARN " + var0);
      }
   }

   public static void setQuietMode(boolean var0) {
      quietMode = var0;
   }

   public static void warn(String var0, Throwable var1) {
      if (!quietMode) {
         System.err.println("log4j:WARN " + var0);
         if (var1 != null) {
            var1.printStackTrace();
         }
      }
   }

   public static void debug(String var0) {
      if (debugEnabled && !quietMode) {
         System.out.println("log4j: " + var0);
      }
   }

   public static void setInternalDebugging(boolean var0) {
      debugEnabled = var0;
   }
}
