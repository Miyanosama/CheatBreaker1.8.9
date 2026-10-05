package org.apache.log4j.pattern;

import net.minecraft.entity.EntityTracker$1;
import org.apache.log4j.spi.LoggingEvent;

public class NDCPatternConverter extends LoggingEventPatternConverter {
   public EntityTracker$1 field_0000;
   public static NDCPatternConverter field_0001 = new NDCPatternConverter();

   public static NDCPatternConverter method_05987(String[] var0) {
      return field_0001;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getNDC());
   }

   public NDCPatternConverter() {
      super("NDC", "ndc");
   }
}
