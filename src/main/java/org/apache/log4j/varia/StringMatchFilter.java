package org.apache.log4j.varia;

import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class StringMatchFilter extends Filter {
   public String stringToMatch;
   public static final String recoveredField901 = "AcceptOnMatch";
   public boolean acceptOnMatch = true;
   public static final String recoveredField902 = "StringToMatch";

   public String getStringToMatch() {
      return this.stringToMatch;
   }

   public int decide(LoggingEvent var1) {
      String var2 = var1.getRenderedMessage();
      if (var2 != null && this.stringToMatch != null) {
         if (var2.indexOf(this.stringToMatch) == -1) {
            return 0;
         } else {
            return this.acceptOnMatch ? 1 : -1;
         }
      } else {
         return 0;
      }
   }

   public boolean getAcceptOnMatch() {
      return this.acceptOnMatch;
   }

   public String[] getOptionStrings() {
      return new String[]{"StringToMatch", "AcceptOnMatch"};
   }

   public void setAcceptOnMatch(boolean var1) {
      this.acceptOnMatch = var1;
   }

   public void setStringToMatch(String var1) {
      this.stringToMatch = var1;
   }

   public void setOption(String var1, String var2) {
      if (var1.equalsIgnoreCase("StringToMatch")) {
         this.stringToMatch = var2;
      } else if (var1.equalsIgnoreCase("AcceptOnMatch")) {
         this.acceptOnMatch = OptionConverter.toBoolean(var2, this.acceptOnMatch);
      }
   }
}
