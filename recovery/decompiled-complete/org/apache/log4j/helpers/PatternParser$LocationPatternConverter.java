package org.apache.log4j.helpers;

import io.netty.util.ResourceLeakDetector;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser$LocationPatternConverter extends PatternConverter {
   public int type;
   public PatternParser this$0;
   public ResourceLeakDetector field_0000;

   public String convert(LoggingEvent var1) {
      LocationInfo var2 = var1.getLocationInformation();
      switch (this.type) {
         case 1000:
            return var2.fullInfo;
         case 1001:
            return var2.getMethodName();
         case 1002:
         default:
            return null;
         case 1003:
            return var2.getLineNumber();
         case 1004:
            return var2.getFileName();
      }
   }

   public PatternParser$LocationPatternConverter(PatternParser var1, FormattingInfo var2, int var3) {
      this.this$0 = var1;
      super(var2);
      this.type = var3;
   }
}
