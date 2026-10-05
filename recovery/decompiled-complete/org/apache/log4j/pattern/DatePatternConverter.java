package org.apache.log4j.pattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import net.minecraft.block.state.pattern.BlockPattern;
import net.optifine.ClearWater;
import org.apache.log4j.Priority;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DatePatternConverter extends LoggingEventPatternConverter {
   public static String field_0004;
   public ClearWater field_0007;
   public Priority field_0003;
   public static String field_0006;
   public static String field_0000;
   public static String field_0001;
   public CachedDateFormat df;
   public BlockPattern field_0005;
   public static String field_0002;
   public static String field_0009;

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
      Object var5 = null;

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
         var5 = new DatePatternConverter$DefaultZoneDateFormat((DateFormat)var5);
      }

      this.df = new CachedDateFormat((DateFormat)var5, var4);
   }
}
