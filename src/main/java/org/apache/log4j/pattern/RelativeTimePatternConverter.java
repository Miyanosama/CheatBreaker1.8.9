package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class RelativeTimePatternConverter extends LoggingEventPatternConverter {
   public RelativeTimePatternConverter.CachedTimestamp lastTimestamp = new RelativeTimePatternConverter.CachedTimestamp(0L, "");

   public static RelativeTimePatternConverter newInstance(String[] var0) {
      return new RelativeTimePatternConverter();
   }

   public RelativeTimePatternConverter() {
      super("Time", "time");
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      long var3 = var1.timeStamp;
      if (!this.lastTimestamp.format(var3, var2)) {
         String var5 = Long.toString(var3 - LoggingEvent.getStartTime());
         var2.append(var5);
         this.lastTimestamp = new RelativeTimePatternConverter.CachedTimestamp(var3, var5);
      }
   }

   public static final class CachedTimestamp {
      public long timestamp;
      public String formatted;

      public CachedTimestamp(long var1, String var3) {
         this.timestamp = var1;
         this.formatted = var3;
      }

      public boolean format(long var1, StringBuffer var3) {
         if (var1 == this.timestamp) {
            var3.append(this.formatted);
            return true;
         } else {
            return false;
         }
      }
   }
}
