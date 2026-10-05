package org.apache.log4j.pattern;

import net.minecraft.server.management.UserList$1;

public class BridgePatternParser extends org.apache.log4j.helpers.PatternParser {
   public UserList$1 field_0000;

   public org.apache.log4j.helpers.PatternConverter parse() {
      return new BridgePatternConverter(this.pattern);
   }

   public BridgePatternParser(String var1) {
      super(var1);
   }
}
