package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public abstract class PatternConverter {
   public int max;
   public PatternConverter next;
   public boolean leftAlign;
   public int min = -1;
   public static String[] SPACES = new String[]{" ", "  ", "    ", "        ", "                ", "                                "};

   public void spacePad(StringBuffer var1, int var2) {
      while (var2 >= 32) {
         var1.append(SPACES[5]);
         var2 -= 32;
      }

      for (int var3 = 4; var3 >= 0; var3--) {
         if ((var2 & 1 << var3) != 0) {
            var1.append(SPACES[var3]);
         }
      }
   }

   public PatternConverter(FormattingInfo var1) {
      this.max = Integer.MAX_VALUE;
      this.leftAlign = false;
      this.min = var1.min;
      this.max = var1.max;
      this.leftAlign = var1.leftAlign;
   }

   public void format(StringBuffer var1, LoggingEvent var2) {
      String var3 = this.convert(var2);
      if (var3 == null) {
         if (0 < this.min) {
            this.spacePad(var1, this.min);
         }
      } else {
         int var4 = var3.length();
         if (var4 > this.max) {
            var1.append(var3.substring(var4 - this.max));
         } else if (var4 < this.min) {
            if (this.leftAlign) {
               var1.append(var3);
               this.spacePad(var1, this.min - var4);
            } else {
               this.spacePad(var1, this.min - var4);
               var1.append(var3);
            }
         } else {
            var1.append(var3);
         }
      }
   }

   public abstract String convert(LoggingEvent var1);

   public PatternConverter() {
      this.max = Integer.MAX_VALUE;
      this.leftAlign = false;
   }
}
