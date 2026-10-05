package org.apache.log4j.pattern;

import java.util.Date;

public class IntegerPatternConverter extends PatternConverter {
   public static IntegerPatternConverter INSTANCE = new IntegerPatternConverter();

   public static IntegerPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public void format(Object var1, StringBuffer var2) {
      if (var1 instanceof Integer) {
         var2.append(var1.toString());
      }

      if (var1 instanceof Date) {
         var2.append(Long.toString(((Date)var1).getTime()));
      }
   }

   public IntegerPatternConverter() {
      super("Integer", "integer");
   }
}
