package org.apache.log4j.rewrite;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class MapRewritePolicy implements RewritePolicy {
   public LoggingEvent rewrite(LoggingEvent var1) {
      Object var2 = var1.getMessage();
      if (var2 instanceof Map) {
         HashMap var3 = new HashMap(var1.getProperties());
         Map var4 = (Map)var2;
         Object var5 = var4.get("message");
         if (var5 == null) {
            var5 = var2;
         }

         for (Entry var7 : (Iterable<Entry>)(Iterable<?>)(var4.entrySet())) {
            if (!"message".equals(var7.getKey())) {
               var3.put(var7.getKey(), var7.getValue());
            }
         }

         return new LoggingEvent(
            var1.getFQNOfLoggerClass(),
            (Category)(var1.getLogger() != null ? var1.getLogger() : Logger.getLogger(var1.getLoggerName())),
            var1.getTimeStamp(),
            var1.getLevel(),
            var5,
            var1.getThreadName(),
            var1.getThrowableInformation(),
            var1.getNDC(),
            var1.getLocationInformation(),
            var3
         );
      } else {
         return var1;
      }
   }
}
