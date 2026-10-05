package io.netty.util.internal.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class JdkLogger extends AbstractInternalLogger {
   public static final long serialVersionUID = -1767272577989225979L;
   public transient Logger logger;
   public static String SELF = JdkLogger.class.getName();
   public static String SUPER = AbstractInternalLogger.class.getName();

   @Override
   public void trace(String var1) {
      if (this.logger.isLoggable(Level.FINEST)) {
         this.log(SELF, Level.FINEST, var1, null);
      }
   }

   @Override
   public void warn(String var1, Throwable var2) {
      if (this.logger.isLoggable(Level.WARNING)) {
         this.log(SELF, Level.WARNING, var1, var2);
      }
   }

   @Override
   public boolean isWarnEnabled() {
      return this.logger.isLoggable(Level.WARNING);
   }

   @Override
   public void warn(String var1, Object var2) {
      if (this.logger.isLoggable(Level.WARNING)) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.log(SELF, Level.WARNING, var3.getMessage(), var3.getThrowable());
      }
   }

   public void log(String var1, Level var2, String var3, Throwable var4) {
      LogRecord var5 = new LogRecord(var2, var3);
      var5.setLoggerName(this.name());
      var5.setThrown(var4);
      fillCallerData(var1, var5);
      this.logger.log(var5);
   }

   @Override
   public void info(String var1, Object var2, Object var3) {
      if (this.logger.isLoggable(Level.INFO)) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.log(SELF, Level.INFO, var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void error(String var1, Object var2, Object var3) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.log(SELF, Level.SEVERE, var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public boolean isDebugEnabled() {
      return this.logger.isLoggable(Level.FINE);
   }

   @Override
   public void trace(String var1, Object var2, Object var3) {
      if (this.logger.isLoggable(Level.FINEST)) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.log(SELF, Level.FINEST, var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void warn(String var1, Object var2, Object var3) {
      if (this.logger.isLoggable(Level.WARNING)) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.log(SELF, Level.WARNING, var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void debug(String var1) {
      if (this.logger.isLoggable(Level.FINE)) {
         this.log(SELF, Level.FINE, var1, null);
      }
   }

   @Override
   public void debug(String var1, Object var2) {
      if (this.logger.isLoggable(Level.FINE)) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.log(SELF, Level.FINE, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void info(String var1, Object... var2) {
      if (this.logger.isLoggable(Level.INFO)) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.log(SELF, Level.INFO, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public boolean isErrorEnabled() {
      return this.logger.isLoggable(Level.SEVERE);
   }

   @Override
   public boolean isTraceEnabled() {
      return this.logger.isLoggable(Level.FINEST);
   }

   @Override
   public void trace(String var1, Object... var2) {
      if (this.logger.isLoggable(Level.FINEST)) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.log(SELF, Level.FINEST, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void trace(String var1, Object var2) {
      if (this.logger.isLoggable(Level.FINEST)) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.log(SELF, Level.FINEST, var3.getMessage(), var3.getThrowable());
      }
   }

   public static void fillCallerData(String var0, LogRecord var1) {
      StackTraceElement[] var2 = new Throwable().getStackTrace();
      int var3 = -1;

      for (int var4 = 0; var4 < var2.length; var4++) {
         String var5 = var2[var4].getClassName();
         if (var5.equals(var0) || var5.equals(SUPER)) {
            var3 = var4;
            break;
         }
      }

      int var7 = -1;

      for (int var8 = var3 + 1; var8 < var2.length; var8++) {
         String var6 = var2[var8].getClassName();
         if (!var6.equals(var0) && !var6.equals(SUPER)) {
            var7 = var8;
            break;
         }
      }

      if (var7 != -1) {
         StackTraceElement var9 = var2[var7];
         var1.setSourceClassName(var9.getClassName());
         var1.setSourceMethodName(var9.getMethodName());
      }
   }

   @Override
   public void error(String var1, Object... var2) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.log(SELF, Level.SEVERE, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void trace(String var1, Throwable var2) {
      if (this.logger.isLoggable(Level.FINEST)) {
         this.log(SELF, Level.FINEST, var1, var2);
      }
   }

   public JdkLogger(Logger var1) {
      super(var1.getName());
      this.logger = var1;
   }

   @Override
   public void error(String var1, Object var2) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.log(SELF, Level.SEVERE, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void info(String var1, Object var2) {
      if (this.logger.isLoggable(Level.INFO)) {
         FormattingTuple var3 = MessageFormatter.format(var1, var2);
         this.log(SELF, Level.INFO, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void info(String var1) {
      if (this.logger.isLoggable(Level.INFO)) {
         this.log(SELF, Level.INFO, var1, null);
      }
   }

   @Override
   public void info(String var1, Throwable var2) {
      if (this.logger.isLoggable(Level.INFO)) {
         this.log(SELF, Level.INFO, var1, var2);
      }
   }

   @Override
   public void warn(String var1) {
      if (this.logger.isLoggable(Level.WARNING)) {
         this.log(SELF, Level.WARNING, var1, null);
      }
   }

   @Override
   public void debug(String var1, Throwable var2) {
      if (this.logger.isLoggable(Level.FINE)) {
         this.log(SELF, Level.FINE, var1, var2);
      }
   }

   @Override
   public void debug(String var1, Object var2, Object var3) {
      if (this.logger.isLoggable(Level.FINE)) {
         FormattingTuple var4 = MessageFormatter.format(var1, var2, var3);
         this.log(SELF, Level.FINE, var4.getMessage(), var4.getThrowable());
      }
   }

   @Override
   public void debug(String var1, Object... var2) {
      if (this.logger.isLoggable(Level.FINE)) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.log(SELF, Level.FINE, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public void warn(String var1, Object... var2) {
      if (this.logger.isLoggable(Level.WARNING)) {
         FormattingTuple var3 = MessageFormatter.arrayFormat(var1, var2);
         this.log(SELF, Level.WARNING, var3.getMessage(), var3.getThrowable());
      }
   }

   @Override
   public boolean isInfoEnabled() {
      return this.logger.isLoggable(Level.INFO);
   }

   @Override
   public void error(String var1, Throwable var2) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         this.log(SELF, Level.SEVERE, var1, var2);
      }
   }

   @Override
   public void error(String var1) {
      if (this.logger.isLoggable(Level.SEVERE)) {
         this.log(SELF, Level.SEVERE, var1, null);
      }
   }
}
