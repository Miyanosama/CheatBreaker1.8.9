package org.apache.log4j.varia;

import org.apache.log4j.Level;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public class LevelRangeFilter extends Filter {
   public Level levelMax;
   public Level levelMin;
   public boolean acceptOnMatch = false;

   public boolean getAcceptOnMatch() {
      return this.acceptOnMatch;
   }

   public void setAcceptOnMatch(boolean var1) {
      this.acceptOnMatch = var1;
   }

   public void setLevelMin(Level var1) {
      this.levelMin = var1;
   }

   public Level getLevelMin() {
      return this.levelMin;
   }

   public Level getLevelMax() {
      return this.levelMax;
   }

   public void setLevelMax(Level var1) {
      this.levelMax = var1;
   }

   public int decide(LoggingEvent var1) {
      if (this.levelMin != null && !var1.getLevel().isGreaterOrEqual(this.levelMin)) {
         return -1;
      } else if (this.levelMax != null && var1.getLevel().toInt() > this.levelMax.toInt()) {
         return -1;
      } else {
         return this.acceptOnMatch ? 1 : 0;
      }
   }
}
