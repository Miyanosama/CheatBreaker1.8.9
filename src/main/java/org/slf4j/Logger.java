package org.slf4j;

public interface Logger {
   String ROOT_LOGGER_NAME = "ROOT";

   void trace(String var1, Throwable var2);

   void trace(String var1, Object... var2);

   void error(String var1, Throwable var2);

   void debug(Marker var1, String var2);

   void debug(String var1, Object var2, Object var3);

   void error(Marker var1, String var2, Object var3, Object var4);

   void trace(String var1);

   void warn(String var1, Object var2, Object var3);

   void debug(String var1, Object var2);

   void trace(Marker var1, String var2);

   void warn(String var1, Object... var2);

   boolean method_02622();

   boolean method_02649();

   void warn(Marker var1, String var2);

   void error(Marker var1, String var2, Throwable var3);

   void method_02650(String var1);

   void info(Marker var1, String var2);

   void debug(String var1, Throwable var2);

   void info(String var1, Throwable var2);

   void trace(Marker var1, String var2, Throwable var3);

   void trace(Marker var1, String var2, Object... var3);

   void error(String var1);

   void warn(Marker var1, String var2, Object... var3);

   void debug(Marker var1, String var2, Object var3);

   void debug(Marker var1, String var2, Object... var3);

   void debug(Marker var1, String var2, Object var3, Object var4);

   void error(Marker var1, String var2);

   void trace(String var1, Object var2);

   void warn(Marker var1, String var2, Throwable var3);

   boolean method_02630(Marker var1);

   void warn(String var1);

   void error(String var1, Object var2);

   void trace(String var1, Object var2, Object var3);

   void info(Marker var1, String var2, Throwable var3);

   void info(String var1, Object var2, Object var3);

   void debug(Marker var1, String var2, Throwable var3);

   void info(String var1);

   void info(Marker var1, String var2, Object var3, Object var4);

   void trace(Marker var1, String var2, Object var3, Object var4);

   void info(String var1, Object... var2);

   void error(String var1, Object... var2);

   void warn(Marker var1, String var2, Object var3);

   boolean method_02655(Marker var1);

   boolean method_02614(Marker var1);

   void warn(String var1, Throwable var2);

   void error(String var1, Object var2, Object var3);

   void warn(String var1, Object var2);

   boolean method_02643(Marker var1);

   void info(Marker var1, String var2, Object var3);

   boolean method_02608();

   boolean method_02637();

   void trace(Marker var1, String var2, Object var3);

   void error(Marker var1, String var2, Object var3);

   void warn(Marker var1, String var2, Object var3, Object var4);

   void debug(String var1, Object... var2);

   void info(Marker var1, String var2, Object... var3);

   boolean isTraceEnabled();

   boolean method_02600(Marker var1);

   void error(Marker var1, String var2, Object... var3);

   String getName();

   void info(String var1, Object var2);
}
