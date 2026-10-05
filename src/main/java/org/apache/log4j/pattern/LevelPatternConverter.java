package org.apache.log4j.pattern;

import org.apache.log4j.spi.LoggingEvent;

public class LevelPatternConverter extends LoggingEventPatternConverter {
   public static final int recoveredField1603 = 5000;
   public static LevelPatternConverter INSTANCE = new LevelPatternConverter();

   public LevelPatternConverter() {
      super("Level", "level");
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getLevel().toString());
   }

   public String getStyleClass(Object var1) {
      if (var1 instanceof LoggingEvent) {
         int var2 = ((LoggingEvent)var1).getLevel().toInt();
         switch (var2) {
            case 5000:
               return "level trace";
            case 10000:
               return "level debug";
            case 20000:
               return "level info";
            case 30000:
               return "level warn";
            case 40000:
               return "level error";
            case 50000:
               return "level fatal";
            default:
               return "level " + ((LoggingEvent)var1).getLevel().toString();
         }
      } else {
         return "level";
      }
   }

   public static LevelPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }
}
