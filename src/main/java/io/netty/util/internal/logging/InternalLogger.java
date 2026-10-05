package io.netty.util.internal.logging;

public interface InternalLogger {
   boolean isDebugEnabled();

   void trace(String var1);

   void warn(String var1, Object... var2);

   void debug(String var1, Object var2);

   boolean isInfoEnabled();

   void trace(String var1, Object var2, Object var3);

   void error(String var1, Throwable var2);

   void log(InternalLogLevel var1, String var2, Object... var3);

   void log(InternalLogLevel var1, String var2);

   boolean isErrorEnabled();

   void warn(String var1, Object var2, Object var3);

   boolean isWarnEnabled();

   void info(String var1, Object var2, Object var3);

   void warn(String var1, Throwable var2);

   void error(String var1, Object... var2);

   void debug(String var1);

   void trace(String var1, Object var2);

   void error(String var1, Object var2);

   void info(String var1);

   void trace(String var1, Object... var2);

   boolean isTraceEnabled();

   void trace(String var1, Throwable var2);

   void debug(String var1, Object... var2);

   void info(String var1, Object var2);

   boolean isEnabled(InternalLogLevel var1);

   void log(InternalLogLevel var1, String var2, Throwable var3);

   void error(String var1);

   void debug(String var1, Throwable var2);

   void warn(String var1, Object var2);

   void info(String var1, Object... var2);

   void info(String var1, Throwable var2);

   void warn(String var1);

   void log(InternalLogLevel var1, String var2, Object var3, Object var4);

   void log(InternalLogLevel var1, String var2, Object var3);

   String name();

   void error(String var1, Object var2, Object var3);

   void debug(String var1, Object var2, Object var3);
}
