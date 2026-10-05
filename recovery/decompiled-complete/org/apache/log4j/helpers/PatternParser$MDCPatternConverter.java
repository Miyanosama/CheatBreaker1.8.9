package org.apache.log4j.helpers;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchValuesTask;
import java.util.Arrays;
import java.util.Map;
import net.minecraft.client.renderer.BlockModelRenderer$VertexTranslations;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass1443;

public class PatternParser$MDCPatternConverter extends PatternConverter {
   public UnidentifiedClass1443 field_0001;
   public String key;
   public BlockModelRenderer$VertexTranslations field_0000;
   public ConcurrentHashMapV8$SearchValuesTask field_0002;

   public String convert(LoggingEvent var1) {
      if (this.key != null) {
         Object var6 = var1.getMDC(this.key);
         return var6 == null ? null : var6.toString();
      } else {
         StringBuffer var2 = new StringBuffer("{");
         Map var3 = var1.getProperties();
         if (var3.size() > 0) {
            Object[] var4 = var3.keySet().toArray();
            Arrays.sort(var4);

            for (int var5 = 0; var5 < var4.length; var5++) {
               var2.append('{');
               var2.append(var4[var5]);
               var2.append(',');
               var2.append(var3.get(var4[var5]));
               var2.append('}');
            }
         }

         var2.append('}');
         return var2.toString();
      }
   }

   public PatternParser$MDCPatternConverter(FormattingInfo var1, String var2) {
      super(var1);
      this.key = var2;
   }
}
