package org.apache.log4j.pattern;

import net.minecraft.world.storage.WorldInfo;
import org.apache.log4j.spi.LoggingEvent;

public class ThreadPatternConverter extends LoggingEventPatternConverter {
   public WorldInfo field_0000;
   public static ThreadPatternConverter field_0001 = new ThreadPatternConverter();

   public ThreadPatternConverter() {
      super("Thread", "thread");
   }

   public static ThreadPatternConverter method_02815(String[] var0) {
      return field_0001;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getThreadName());
   }
}
