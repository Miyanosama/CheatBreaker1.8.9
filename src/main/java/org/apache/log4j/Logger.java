package org.apache.log4j;

import org.apache.log4j.spi.LoggerFactory;

public class Logger extends Category {
   public static Class class$org$apache$log4j$Logger;
   public static String FQCN = (class$org$apache$log4j$Logger == null
         ? (class$org$apache$log4j$Logger = class$("org.apache.log4j.Logger"))
         : class$org$apache$log4j$Logger)
      .getName();

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public void trace(Object var1) {
      if (!this.repository.isDisabled(5000)) {
         if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.TRACE, var1, null);
         }
      }
   }

   public void trace(Object var1, Throwable var2) {
      if (!this.repository.isDisabled(5000)) {
         if (Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel())) {
            this.forcedLog(FQCN, Level.TRACE, var1, var2);
         }
      }
   }

   public static Logger getLogger(Class var0) {
      return LogManager.getLogger(var0.getName());
   }

   public Logger(String var1) {
      super(var1);
   }

   public static Logger getLogger(String var0, LoggerFactory var1) {
      return LogManager.getLogger(var0, var1);
   }

   public static Logger getLogger(String var0) {
      return LogManager.getLogger(var0);
   }

   public static Logger getRootLogger() {
      return LogManager.getRootLogger();
   }

   public boolean isTraceEnabled() {
      return this.repository.isDisabled(5000) ? false : Level.TRACE.isGreaterOrEqual(this.getEffectiveLevel());
   }
}
