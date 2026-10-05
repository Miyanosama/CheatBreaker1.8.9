package org.apache.log4j.pattern;

import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class MethodLocationPatternConverter extends LoggingEventPatternConverter {
   public static MethodLocationPatternConverter recoveredField3 = new MethodLocationPatternConverter();

   public static MethodLocationPatternConverter method_28285(String[] var0) {
      return recoveredField3;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      LocationInfo var3 = var1.getLocationInformation();
      if (var3 != null) {
         var2.append(var3.getMethodName());
      }
   }

   public MethodLocationPatternConverter() {
      super("Method", "method");
   }
}
