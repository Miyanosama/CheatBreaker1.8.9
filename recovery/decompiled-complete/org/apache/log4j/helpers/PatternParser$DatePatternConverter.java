package org.apache.log4j.helpers;

import io.netty.handler.codec.compression.ZlibUtil;
import java.text.DateFormat;
import java.util.Date;
import net.minecraft.util.LongHashMap$Entry;
import org.apache.log4j.spi.LoggingEvent;

public class PatternParser$DatePatternConverter extends PatternConverter {
   public Date date = new Date();
   public ZlibUtil field_0003;
   public DateFormat df;
   public LongHashMap$Entry field_0002;

   public String convert(LoggingEvent var1) {
      this.date.setTime(var1.timeStamp);
      String var2 = null;

      try {
         var2 = this.df.format(this.date);
      } catch (Exception var4) {
         LogLog.error("Error occured while converting date.", var4);
      }

      return var2;
   }

   public PatternParser$DatePatternConverter(FormattingInfo var1, DateFormat var2) {
      super(var1);
      this.df = var2;
   }
}
