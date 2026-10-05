package org.apache.log4j.pattern;

public class BridgePatternParser extends org.apache.log4j.helpers.PatternParser {
   public org.apache.log4j.helpers.PatternConverter parse() {
      return new BridgePatternConverter(this.pattern);
   }

   public BridgePatternParser(String var1) {
      super(var1);
   }
}
