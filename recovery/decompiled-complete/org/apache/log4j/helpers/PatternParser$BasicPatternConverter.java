package org.apache.log4j.helpers;

import io.netty.buffer.ByteBufProcessor$2;
import net.minecraft.util.IntHashMap;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser$BasicPatternConverter extends PatternConverter {
   public IntHashMap field_0001;
   public ByteBufProcessor$2 field_0002;
   public int type;

   public PatternParser$BasicPatternConverter(FormattingInfo var1, int var2) {
      super(var1);
      this.type = var2;
   }

   public String convert(LoggingEvent var1) {
      switch (this.type) {
         case 2000:
            return Long.toString(var1.timeStamp - LoggingEvent.getStartTime());
         case 2001:
            return var1.getThreadName();
         case 2002:
            return var1.getLevel().toString();
         case 2003:
            return var1.getNDC();
         case 2004:
            return var1.getRenderedMessage();
         default:
            return null;
      }
   }
}
