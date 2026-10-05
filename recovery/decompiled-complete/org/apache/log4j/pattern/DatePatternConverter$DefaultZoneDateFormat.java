package org.apache.log4j.pattern;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchMappingsTask;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;

public class DatePatternConverter$DefaultZoneDateFormat extends DateFormat {
   public DateFormat dateFormat;
   public ConcurrentHashMapV8$SearchMappingsTask field_0002;
   public static long field_0000;

   public DatePatternConverter$DefaultZoneDateFormat(DateFormat var1) {
      this.dateFormat = var1;
   }

   public Date parse(String var1, ParsePosition var2) {
      this.dateFormat.setTimeZone(TimeZone.getDefault());
      return this.dateFormat.parse(var1, var2);
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      this.dateFormat.setTimeZone(TimeZone.getDefault());
      return this.dateFormat.format(var1, var2, var3);
   }
}
