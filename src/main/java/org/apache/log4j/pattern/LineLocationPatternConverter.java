package org.apache.log4j.pattern;

import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class LineLocationPatternConverter extends LoggingEventPatternConverter {
   public static LineLocationPatternConverter INSTANCE = new LineLocationPatternConverter();

   public void format(LoggingEvent var1, StringBuffer var2) {
      LocationInfo var3 = var1.getLocationInformation();
      if (var3 != null) {
         var2.append(var3.getLineNumber());
      }
   }

   public static LineLocationPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public LineLocationPatternConverter() {
      super("Line", "line");
   }
}
