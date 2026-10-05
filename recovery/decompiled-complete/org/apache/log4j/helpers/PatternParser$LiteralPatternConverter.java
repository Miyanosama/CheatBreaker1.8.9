package org.apache.log4j.helpers;

import net.optifine.expr.ExpressionParser;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass4363;

public class PatternParser$LiteralPatternConverter extends PatternConverter {
   public String literal;
   public UnidentifiedClass4363 field_0002;
   public ExpressionParser field_0000;

   public PatternParser$LiteralPatternConverter(String var1) {
      this.literal = var1;
   }

   public String convert(LoggingEvent var1) {
      return this.literal;
   }

   public void format(StringBuffer var1, LoggingEvent var2) {
      var1.append(this.literal);
   }
}
