package org.apache.log4j.pattern;

public class FileDatePatternConverter {
   public static PatternConverter newInstance(String[] var0) {
      return var0 != null && var0.length != 0 ? DatePatternConverter.newInstance(var0) : DatePatternConverter.newInstance(new String[]{"yyyy-MM-dd"});
   }
}
