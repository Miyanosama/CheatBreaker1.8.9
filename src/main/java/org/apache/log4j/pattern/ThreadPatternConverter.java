package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class ThreadPatternConverter extends LoggingEventPatternConverter {
   public static ThreadPatternConverter recoveredField2471 = new ThreadPatternConverter();

   public ThreadPatternConverter() {
      super("Thread", "thread");
   }

   public static ThreadPatternConverter method_02815(String[] var0) {
      return recoveredField2471;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getThreadName());
   }
}
