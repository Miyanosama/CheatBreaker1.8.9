package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class MessagePatternConverter extends LoggingEventPatternConverter {
   public static MessagePatternConverter recoveredField1310 = new MessagePatternConverter();

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getRenderedMessage());
   }

   public MessagePatternConverter() {
      super("Message", "message");
   }

   public static MessagePatternConverter method_29443(String[] var0) {
      return recoveredField1310;
   }
}
