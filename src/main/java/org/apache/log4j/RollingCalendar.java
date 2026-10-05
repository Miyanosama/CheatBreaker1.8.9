package org.apache.log4j;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class RollingCalendar extends GregorianCalendar {
   public static final long recoveredField2282 = -3560331770601814177L;
   public int type = -1;

   public void setType(int var1) {
      this.type = var1;
   }

   public RollingCalendar(TimeZone var1, Locale var2) {
      super(var1, var2);
   }

   public long getNextCheckMillis(Date var1) {
      return this.getNextCheckDate(var1).getTime();
   }

   public RollingCalendar() {
   }

   public Date getNextCheckDate(Date var1) {
      this.setTime(var1);
      switch (this.type) {
         case 0:
            this.set(13, 0);
            this.set(14, 0);
            this.add(12, 1);
            break;
         case 1:
            this.set(12, 0);
            this.set(13, 0);
            this.set(14, 0);
            this.add(11, 1);
            break;
         case 2:
            this.set(12, 0);
            this.set(13, 0);
            this.set(14, 0);
            int var2 = this.get(11);
            if (var2 < 12) {
               this.set(11, 12);
            } else {
               this.set(11, 0);
               this.add(5, 1);
            }
            break;
         case 3:
            this.set(11, 0);
            this.set(12, 0);
            this.set(13, 0);
            this.set(14, 0);
            this.add(5, 1);
            break;
         case 4:
            this.set(7, this.getFirstDayOfWeek());
            this.set(11, 0);
            this.set(12, 0);
            this.set(13, 0);
            this.set(14, 0);
            this.add(3, 1);
            break;
         case 5:
            this.set(5, 1);
            this.set(11, 0);
            this.set(12, 0);
            this.set(13, 0);
            this.set(14, 0);
            this.add(2, 1);
            break;
         default:
            throw new IllegalStateException("Unknown periodicity type.");
      }

      return this.getTime();
   }
}
