package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class SequenceNumberPatternConverter extends LoggingEventPatternConverter {
   public static SequenceNumberPatternConverter INSTANCE = new SequenceNumberPatternConverter();

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append("0");
   }

   public static SequenceNumberPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public SequenceNumberPatternConverter() {
      super("Sequence Number", "sn");
   }
}
