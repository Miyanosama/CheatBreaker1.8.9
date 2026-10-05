package org.apache.log4j.rewrite;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class PropertyRewritePolicy implements RewritePolicy {
   public Map properties = Collections.EMPTY_MAP;

   public void setProperties(String var1) {
      HashMap var2 = new HashMap();
      StringTokenizer var3 = new StringTokenizer(var1, ",");

      while (var3.hasMoreTokens()) {
         StringTokenizer var4 = new StringTokenizer(var3.nextToken(), "=");
         var2.put(var4.nextElement().toString().trim(), var4.nextElement().toString().trim());
      }

      synchronized (this) {
         this.properties = var2;
      }
   }

   public LoggingEvent rewrite(LoggingEvent var1) {
      if (!this.properties.isEmpty()) {
         HashMap var2 = new HashMap(var1.getProperties());

         for (Entry var4 : (Iterable<Entry>)(Iterable<?>)(this.properties.entrySet())) {
            if (!var2.containsKey(var4.getKey())) {
               var2.put(var4.getKey(), var4.getValue());
            }
         }

         return new LoggingEvent(
            var1.getFQNOfLoggerClass(),
            (Category)(var1.getLogger() != null ? var1.getLogger() : Logger.getLogger(var1.getLoggerName())),
            var1.getTimeStamp(),
            var1.getLevel(),
            var1.getMessage(),
            var1.getThreadName(),
            var1.getThrowableInformation(),
            var1.getNDC(),
            var1.getLocationInformation(),
            var2
         );
      } else {
         return var1;
      }
   }
}
