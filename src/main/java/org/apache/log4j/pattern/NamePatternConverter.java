package org.apache.log4j.pattern;

public abstract class NamePatternConverter extends LoggingEventPatternConverter {
   public NameAbbreviator abbreviator;

   public NamePatternConverter(String var1, String var2, String[] var3) {
      super(var1, var2);
      if (var3 != null && var3.length > 0) {
         this.abbreviator = NameAbbreviator.getAbbreviator(var3[0]);
      } else {
         this.abbreviator = NameAbbreviator.getDefaultAbbreviator();
      }
   }

   public void abbreviate(int var1, StringBuffer var2) {
      this.abbreviator.abbreviate(var1, var2);
   }
}
