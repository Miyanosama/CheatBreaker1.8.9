package org.apache.log4j.pattern;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class LineSeparatorPatternConverter extends LoggingEventPatternConverter {
   public static LineSeparatorPatternConverter INSTANCE = new LineSeparatorPatternConverter();
   public String lineSep = Layout.LINE_SEP;

   public static LineSeparatorPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public LineSeparatorPatternConverter() {
      super("Line Sep", "lineSep");
   }

   public void format(Object var1, StringBuffer var2) {
      var2.append(this.lineSep);
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(this.lineSep);
   }
}
