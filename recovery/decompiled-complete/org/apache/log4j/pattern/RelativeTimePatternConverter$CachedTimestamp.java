package org.apache.log4j.pattern;

import net.optifine.GlDebugHandler;

public class RelativeTimePatternConverter$CachedTimestamp {
   public long timestamp;
   public String formatted;
   public GlDebugHandler field_0000;

   public RelativeTimePatternConverter$CachedTimestamp(long var1, String var3) {
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
