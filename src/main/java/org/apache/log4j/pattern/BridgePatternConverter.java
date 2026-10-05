package org.apache.log4j.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.apache.log4j.spi.LoggingEvent;

public class BridgePatternConverter extends org.apache.log4j.helpers.PatternConverter {
   public boolean handlesExceptions;
   public FormattingInfo[] patternFields;
   public LoggingEventPatternConverter[] patternConverters;

   public String convert(LoggingEvent var1) {
      StringBuffer var2 = new StringBuffer();
      this.format(var2, var1);
      return var2.toString();
   }

   public boolean ignoresThrowable() {
      return !this.handlesExceptions;
   }

   public void format(StringBuffer var1, LoggingEvent var2) {
      for (int var3 = 0; var3 < this.patternConverters.length; var3++) {
         int var4 = var1.length();
         this.patternConverters[var3].format(var2, var1);
         this.patternFields[var3].format(var4, var1);
      }
   }

   public BridgePatternConverter(String var1) {
      this.next = null;
      this.handlesExceptions = false;
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      Object var4 = null;
      PatternParser.parse(var1, var2, var3, (Map)var4, PatternParser.getPatternLayoutRules());
      this.patternConverters = new LoggingEventPatternConverter[var2.size()];
      this.patternFields = new FormattingInfo[var2.size()];
      int var5 = 0;
      Iterator var6 = var2.iterator();

      for (Iterator var7 = var3.iterator(); var6.hasNext(); var5++) {
         Object var8 = var6.next();
         if (var8 instanceof LoggingEventPatternConverter) {
            this.patternConverters[var5] = (LoggingEventPatternConverter)var8;
            this.handlesExceptions = this.handlesExceptions | this.patternConverters[var5].handlesThrowable();
         } else {
            this.patternConverters[var5] = new LiteralPatternConverter("");
         }

         if (var7.hasNext()) {
            this.patternFields[var5] = (FormattingInfo)var7.next();
         } else {
            this.patternFields[var5] = FormattingInfo.getDefault();
         }
      }
   }
}
