package org.apache.log4j.varia;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class DenyAllFilter extends Filter {
   public void setOption(String var1, String var2) {
   }

   public int decide(LoggingEvent var1) {
      return -1;
   }

   public String[] getOptionStrings() {
      return null;
   }
}
