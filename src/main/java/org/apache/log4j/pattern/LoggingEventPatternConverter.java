package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public abstract class LoggingEventPatternConverter extends PatternConverter {
   public boolean handlesThrowable() {
      return false;
   }

   public abstract void format(LoggingEvent var1, StringBuffer var2);

   public void format(Object var1, StringBuffer var2) {
      if (var1 instanceof LoggingEvent) {
         this.format((LoggingEvent)var1, var2);
      }
   }

   public LoggingEventPatternConverter(String var1, String var2) {
      super(var1, var2);
   }
}
