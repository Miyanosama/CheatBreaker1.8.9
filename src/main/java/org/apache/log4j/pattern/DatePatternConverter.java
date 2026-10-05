package org.apache.log4j.pattern;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DatePatternConverter extends LoggingEventPatternConverter {
   public static final String recoveredField2786 = "ISO8601";
   public static final String recoveredField2787 = "dd MMM yyyy HH:mm:ss,SSS";
   public static final String recoveredField2788 = "ABSOLUTE";
   public static final String recoveredField2789 = "HH:mm:ss,SSS";
   public CachedDateFormat df;
   public static final String recoveredField2790 = "DATE";
   public static final String recoveredField2791 = "yyyy-MM-dd HH:mm:ss,SSS";

   public void format(LoggingEvent var1, StringBuffer var2) {
      synchronized (this) {
         this.df.format(var1.timeStamp, var2);
      }
   }

   public static DatePatternConverter newInstance(String[] var0) {
      return new DatePatternConverter(var0);
   }

   public void format(Date var1, StringBuffer var2) {
      synchronized (this) {
         this.df.format(var1.getTime(), var2);
      }
   }

   public void format(Object var1, StringBuffer var2) {
      if (var1 instanceof Date) {
         this.format((Date)var1, var2);
      }

      super.format(var1, var2);
   }

   public DatePatternConverter(String[] var1) {
      super("Date", "date");
      String var2;
      if (var1 != null && var1.length != 0) {
         var2 = var1[0];
      } else {
         var2 = null;
      }

      String var3;
      if (var2 == null || var2.equalsIgnoreCase("ISO8601")) {
         var3 = "yyyy-MM-dd HH:mm:ss,SSS";
      } else if (var2.equalsIgnoreCase("ABSOLUTE")) {
         var3 = "HH:mm:ss,SSS";
      } else if (var2.equalsIgnoreCase("DATE")) {
         var3 = "dd MMM yyyy HH:mm:ss,SSS";
      } else {
         var3 = var2;
      }

      int var4 = 1000;
      java.text.DateFormat var5 = null;

      try {
         var5 = new SimpleDateFormat(var3);
         var4 = CachedDateFormat.getMaximumCacheValidity(var3);
      } catch (IllegalArgumentException var7) {
         LogLog.warn("Could not instantiate SimpleDateFormat with pattern " + var2, var7);
         var5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
      }

      if (var1 != null && var1.length > 1) {
         TimeZone var6 = TimeZone.getTimeZone(var1[1]);
         var5.setTimeZone(var6);
      } else {
         var5 = new DatePatternConverter.DefaultZoneDateFormat((DateFormat)var5);
      }

      this.df = new CachedDateFormat((DateFormat)var5, var4);
   }

   public static class DefaultZoneDateFormat extends DateFormat {
      public DateFormat dateFormat;
      public static final long recoveredField98 = 1L;

      public DefaultZoneDateFormat(DateFormat var1) {
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
}
