package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class LoggerPatternConverter extends NamePatternConverter {
   public static LoggerPatternConverter INSTANCE = new LoggerPatternConverter(null);

   public static LoggerPatternConverter newInstance(String[] var0) {
      return var0 != null && var0.length != 0 ? new LoggerPatternConverter(var0) : INSTANCE;
   }

   public LoggerPatternConverter(String[] var1) {
      super("Logger", "logger", var1);
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      int var3 = var2.length();
      var2.append(var1.getLoggerName());
      this.abbreviate(var3, var2);
   }
}
