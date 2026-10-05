package org.apache.log4j;

import java.util.ResourceBundle;
import org.apache.log4j.spi.LoggingEvent;

public class LogSF extends LogXF {
   public static String FQCN = (LogSF.class$org$apache$log4j$LogSF == null
         ? (LogSF.class$org$apache$log4j$LogSF = class$("org.apache.log4j.LogSF"))
         : LogSF.class$org$apache$log4j$LogSF)
      .getName();
   public static Class class$org$apache$log4j$LogSF;

   public static void logrb(Logger var0, Level var1, String var2, String var3, boolean var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void info(Logger var0, String var1, boolean var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void trace(Logger var0, Throwable var1, String var2, Object[] var3) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var2, var3), var1);
      }
   }

   public static void debug(Logger var0, String var1, int var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void forcedLog(Logger var0, Level var1, String var2, Throwable var3) {
      var0.callAppenders(new LoggingEvent(FQCN, var0, var1, var2, var3));
   }

   public static void log(Logger var0, Level var1, String var2, Object var3, Object var4, Object var5, Object var6) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(var3, var4, var5, var6)));
      }
   }

   public static void info(Logger var0, String var1, Object[] var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, var2));
      }
   }

   public static void debug(Logger var0, String var1, short var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void debug(Logger var0, String var1, Object var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, var2));
      }
   }

   public static void trace(Logger var0, String var1, short var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void info(Logger var0, String var1, byte var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void info(Logger var0, String var1, Object var2, Object var3) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, toArray(var2, var3)));
      }
   }

   public static void forcedLog(Logger var0, Level var1, String var2) {
      var0.callAppenders(new LoggingEvent(FQCN, var0, var1, var2, null));
   }

   public static void error(Logger var0, Throwable var1, String var2, Object[] var3) {
      if (var0.isEnabledFor(Level.ERROR)) {
         forcedLog(var0, Level.ERROR, format(var2, var3), var1);
      }
   }

   public static void debug(Logger var0, String var1, long var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void trace(Logger var0, String var1, Object var2, Object var3) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, toArray(var2, var3)));
      }
   }

   public static void warn(Logger var0, String var1, byte var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void debug(Logger var0, String var1, byte var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void info(Logger var0, String var1, int var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static String format(String var0, Object var1) {
      if (var0 != null) {
         if (var0.indexOf("\\{") >= 0) {
            return format(var0, new Object[]{var1});
         }

         int var2 = var0.indexOf("{}");
         if (var2 >= 0) {
            return var0.substring(0, var2) + var1 + var0.substring(var2 + 2);
         }
      }

      return var0;
   }

   public static void warn(Logger var0, Throwable var1, String var2, Object[] var3) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var2, var3), var1);
      }
   }

   public static String format(String var0, Object[] var1) {
      if (var0 == null) {
         return null;
      } else {
         String var2 = "";
         int var3 = 0;
         int var4 = 0;

         for (int var5 = var0.indexOf("{"); var5 >= 0; var5 = var0.indexOf("{", var4)) {
            if (var5 != 0 && var0.charAt(var5 - 1) == '\\') {
               var2 = var2 + var0.substring(var4, var5 - 1) + "{";
               var4 = var5 + 1;
            } else {
               var2 = var2 + var0.substring(var4, var5);
               if (var5 + 1 < var0.length() && var0.charAt(var5 + 1) == '}') {
                  if (var1 != null && var3 < var1.length) {
                     var2 = var2 + var1[var3++];
                  } else {
                     var2 = var2 + "{}";
                  }

                  var4 = var5 + 2;
               } else {
                  var2 = var2 + "{";
                  var4 = var5 + 1;
               }
            }
         }

         return var2 + var0.substring(var4);
      }
   }

   public static void trace(Logger var0, String var1, Object var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, var2));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, Object var4, Object var5, Object var6) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(var4, var5, var6)));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, Object var4, Object var5) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(var4, var5)));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, long var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void warn(Logger var0, String var1, Object var2, Object var3) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, toArray(var2, var3)));
      }
   }

   public static void warn(Logger var0, String var1, double var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void trace(Logger var0, String var1, int var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void debug(Logger var0, String var1, boolean var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, char var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void trace(Logger var0, String var1, float var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void fatal(Logger var0, Throwable var1, String var2, Object[] var3) {
      if (var0.isEnabledFor(Level.FATAL)) {
         forcedLog(var0, Level.FATAL, format(var2, var3), var1);
      }
   }

   public static void info(Logger var0, String var1, short var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, Object var3, Object var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(var3, var4)));
      }
   }

   public static void info(Logger var0, String var1, Object var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, var2));
      }
   }

   public static void debug(Logger var0, Throwable var1, String var2, Object[] var3) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var2, var3), var1);
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, int var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void trace(Logger var0, String var1, char var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void debug(Logger var0, String var1, double var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void warn(Logger var0, String var1, int var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void logrb(Logger var0, Level var1, Throwable var2, String var3, String var4, Object[] var5) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var3, var4, var5), var2);
      }
   }

   public static void fatal(Logger var0, String var1, Object[] var2) {
      if (var0.isEnabledFor(Level.FATAL)) {
         forcedLog(var0, Level.FATAL, format(var1, var2));
      }
   }

   public static void warn(Logger var0, String var1, long var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, float var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void debug(Logger var0, String var1, Object[] var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, var2));
      }
   }

   public static void log(Logger var0, Level var1, String var2, Object var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(var3)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, byte var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void warn(Logger var0, String var1, Object var2, Object var3, Object var4) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, toArray(var2, var3, var4)));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, float var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static String format(String var0, String var1, Object[] var2) {
      String var3;
      if (var0 != null) {
         try {
            ResourceBundle var4 = ResourceBundle.getBundle(var0);
            var3 = var4.getString(var1);
         } catch (Exception var5) {
            var3 = var1;
         }
      } else {
         var3 = var1;
      }

      return format(var3, var2);
   }

   public static void info(Logger var0, String var1, double var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, boolean var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void trace(Logger var0, String var1, Object var2, Object var3, Object var4, Object var5) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, toArray(var2, var3, var4, var5)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, Object var3, Object var4, Object var5) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(var3, var4, var5)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, int var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void trace(Logger var0, String var1, Object[] var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, var2));
      }
   }

   public static void debug(Logger var0, String var1, Object var2, Object var3, Object var4, Object var5) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, toArray(var2, var3, var4, var5)));
      }
   }

   public static void trace(Logger var0, String var1, byte var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void trace(Logger var0, String var1, Object var2, Object var3, Object var4) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, toArray(var2, var3, var4)));
      }
   }

   public static void info(Logger var0, String var1, Object var2, Object var3, Object var4) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, toArray(var2, var3, var4)));
      }
   }

   public static void debug(Logger var0, String var1, float var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, long var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, byte var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, Object var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(var4)));
      }
   }

   public static void debug(Logger var0, String var1, Object var2, Object var3, Object var4) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, toArray(var2, var3, var4)));
      }
   }

   public static void warn(Logger var0, String var1, Object var2, Object var3, Object var4, Object var5) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, toArray(var2, var3, var4, var5)));
      }
   }

   public static void trace(Logger var0, String var1, long var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void warn(Logger var0, String var1, char var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void warn(Logger var0, String var1, float var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void info(Logger var0, String var1, float var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void trace(Logger var0, String var1, double var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, Object var4, Object var5, Object var6, Object var7) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(var4, var5, var6, var7)));
      }
   }

   public static void warn(Logger var0, String var1, Object var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, var2));
      }
   }

   public static void info(Logger var0, String var1, long var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, Object[] var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3));
      }
   }

   public static void log(Logger var0, Level var1, String var2, char var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, short var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void error(Logger var0, String var1, Object[] var2) {
      if (var0.isEnabledFor(Level.ERROR)) {
         forcedLog(var0, Level.ERROR, format(var1, var2));
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, Object[] var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, var4));
      }
   }

   public static void warn(Logger var0, String var1, short var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void info(Logger var0, String var1, char var2) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, valueOf(var2)));
      }
   }

   public static void info(Logger var0, Throwable var1, String var2, Object[] var3) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var2, var3), var1);
      }
   }

   public static void logrb(Logger var0, Level var1, String var2, String var3, double var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, var3, toArray(valueOf(var4))));
      }
   }

   public static void warn(Logger var0, String var1, boolean var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, valueOf(var2)));
      }
   }

   public static void trace(Logger var0, String var1, boolean var2) {
      if (var0.isEnabledFor(TRACE)) {
         forcedLog(var0, TRACE, format(var1, valueOf(var2)));
      }
   }

   public static void log(Logger var0, Level var1, String var2, short var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }

   public static void debug(Logger var0, String var1, char var2) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, valueOf(var2)));
      }
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void warn(Logger var0, String var1, Object[] var2) {
      if (var0.isEnabledFor(Level.WARN)) {
         forcedLog(var0, Level.WARN, format(var1, var2));
      }
   }

   public static void debug(Logger var0, String var1, Object var2, Object var3) {
      if (var0.isDebugEnabled()) {
         forcedLog(var0, Level.DEBUG, format(var1, toArray(var2, var3)));
      }
   }

   public static void info(Logger var0, String var1, Object var2, Object var3, Object var4, Object var5) {
      if (var0.isInfoEnabled()) {
         forcedLog(var0, Level.INFO, format(var1, toArray(var2, var3, var4, var5)));
      }
   }

   public static void log(Logger var0, Level var1, Throwable var2, String var3, Object[] var4) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var3, var4), var2);
      }
   }

   public static void log(Logger var0, Level var1, String var2, double var3) {
      if (var0.isEnabledFor(var1)) {
         forcedLog(var0, var1, format(var2, toArray(valueOf(var3))));
      }
   }
}
