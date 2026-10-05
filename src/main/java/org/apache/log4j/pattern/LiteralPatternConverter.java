package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class LiteralPatternConverter extends LoggingEventPatternConverter {
   public String literal;

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(this.literal);
   }

   public void format(Object var1, StringBuffer var2) {
      var2.append(this.literal);
   }

   public LiteralPatternConverter(String var1) {
      super("Literal", "literal");
      this.literal = var1;
   }
}
