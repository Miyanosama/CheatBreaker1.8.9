package net.optifine.util;

import java.util.HashMap;
import java.util.Map;

public class TimedEvent {
   public static Map<String, Long> mapEventTimes = new HashMap<>();

   public static boolean isActive(String var0, long var1) {
      synchronized (mapEventTimes) {
         long var4 = System.currentTimeMillis();
         Long var6 = mapEventTimes.get(var0);
         if (var6 == null) {
            var6 = new Long(var4);
            mapEventTimes.put(var0, var6);
         }

         long var7 = var6;
         if (var4 < var7 + var1) {
            return false;
         } else {
            mapEventTimes.put(var0, new Long(var4));
            return true;
         }
      }
   }
}
