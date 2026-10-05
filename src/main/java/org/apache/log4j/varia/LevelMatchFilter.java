package org.apache.log4j.varia;

import org.apache.log4j.Level;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class LevelMatchFilter extends Filter {
   public Level levelToMatch;
   public boolean acceptOnMatch = true;

   public String getLevelToMatch() {
      return this.levelToMatch == null ? null : this.levelToMatch.toString();
   }

   public boolean getAcceptOnMatch() {
      return this.acceptOnMatch;
   }

   public void setLevelToMatch(String var1) {
      this.levelToMatch = OptionConverter.toLevel(var1, null);
   }

   public int decide(LoggingEvent var1) {
      if (this.levelToMatch == null) {
         return 0;
      } else {
         boolean var2 = false;
         if (this.levelToMatch.equals(var1.getLevel())) {
            var2 = true;
         }

         if (var2) {
            return this.acceptOnMatch ? 1 : -1;
         } else {
            return 0;
         }
      }
   }

   public void setAcceptOnMatch(boolean var1) {
      this.acceptOnMatch = var1;
   }
}
