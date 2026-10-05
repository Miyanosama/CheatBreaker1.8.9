package org.apache.log4j.pattern;

import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class ClassNamePatternConverter extends NamePatternConverter {
   public ClassNamePatternConverter(String[] var1) {
      super("Class Name", "class name", var1);
   }

   public static ClassNamePatternConverter newInstance(String[] var0) {
      return new ClassNamePatternConverter(var0);
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      int var3 = var2.length();
      LocationInfo var4 = var1.getLocationInformation();
      if (var4 == null) {
         var2.append("?");
      } else {
         var2.append(var4.getClassName());
      }

      this.abbreviate(var3, var2);
   }
}
