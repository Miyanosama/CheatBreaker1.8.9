package org.apache.log4j.pattern;

import java.util.Set;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.MDCKeySetExtractor;
import org.apache.log4j.spi.LoggingEvent;

public class PropertiesPatternConverter extends LoggingEventPatternConverter {
   public String option;

   public void format(LoggingEvent var1, StringBuffer var2) {
      if (this.option == null) {
         var2.append("{");

         try {
            Set var3 = MDCKeySetExtractor.INSTANCE.getPropertyKeySet(var1);
            if (var3 != null) {
               for (Object var5 : var3) {
                  Object var6 = var1.getMDC(var5.toString());
                  var2.append("{").append(var5).append(",").append(var6).append("}");
               }
            }
         } catch (Exception var7) {
            LogLog.error("Unexpected exception while extracting MDC keys", var7);
         }

         var2.append("}");
      } else {
         Object var8 = var1.getMDC(this.option);
         if (var8 != null) {
            var2.append(var8);
         }
      }
   }

   public static PropertiesPatternConverter newInstance(String[] var0) {
      return new PropertiesPatternConverter(var0);
   }

   public PropertiesPatternConverter(String[] var1) {
      super(var1 != null && var1.length > 0 ? "Property{" + var1[0] + "}" : "Properties", "property");
      if (var1 != null && var1.length > 0) {
         this.option = var1[0];
      } else {
         this.option = null;
      }
   }
}
