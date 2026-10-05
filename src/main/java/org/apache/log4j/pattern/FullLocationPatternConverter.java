package org.apache.log4j.pattern;

import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class FullLocationPatternConverter extends LoggingEventPatternConverter {
   public static FullLocationPatternConverter INSTANCE = new FullLocationPatternConverter();

   public static FullLocationPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      LocationInfo var3 = var1.getLocationInformation();
      if (var3 != null) {
         var2.append(var3.fullInfo);
      }
   }

   public FullLocationPatternConverter() {
      super("Full Location", "fullLocation");
   }
}
