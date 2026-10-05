package org.apache.log4j.helpers;

import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeDateFormat extends AbsoluteTimeDateFormat {
   public static final long recoveredField1773 = 5547637772208514971L;
   public String[] shortMonths = new DateFormatSymbols().getShortMonths();

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      this.calendar.setTime(var1);
      int var4 = this.calendar.get(5);
      if (var4 < 10) {
         var2.append('0');
      }

      var2.append(var4);
      var2.append(' ');
      var2.append(this.shortMonths[this.calendar.get(2)]);
      var2.append(' ');
      int var5 = this.calendar.get(1);
      var2.append(var5);
      var2.append(' ');
      return super.format(var1, var2, var3);
   }

   public DateTimeDateFormat() {
   }

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }

   public DateTimeDateFormat(TimeZone var1) {
      this();
      this.setCalendar(Calendar.getInstance(var1));
   }
}
