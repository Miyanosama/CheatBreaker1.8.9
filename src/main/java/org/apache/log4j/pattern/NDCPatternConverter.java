package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class NDCPatternConverter extends LoggingEventPatternConverter {
   public static NDCPatternConverter recoveredField3772 = new NDCPatternConverter();

   public static NDCPatternConverter method_05987(String[] var0) {
      return recoveredField3772;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getNDC());
   }

   public NDCPatternConverter() {
      super("NDC", "ndc");
   }
}
