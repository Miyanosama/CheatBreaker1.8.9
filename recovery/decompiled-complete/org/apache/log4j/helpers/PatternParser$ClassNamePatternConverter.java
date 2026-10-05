package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class PatternParser$ClassNamePatternConverter extends PatternParser$NamedPatternConverter {
   public PatternParser this$0;

   public String getFullyQualifiedName(LoggingEvent var1) {
      return var1.getLocationInformation().getClassName();
   }

   public PatternParser$ClassNamePatternConverter(PatternParser var1, FormattingInfo var2, int var3) {
      this.this$0 = var1;
      super(var2, var3);
   }
}
