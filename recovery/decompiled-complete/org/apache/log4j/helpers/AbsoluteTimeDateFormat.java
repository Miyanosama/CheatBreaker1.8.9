package org.apache.log4j.helpers;

import io.netty.util.internal.logging.InternalLoggerFactory;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.minecraft.tileentity.MobSpawnerBaseLogic;

public class AbsoluteTimeDateFormat extends DateFormat {
   public static char[] previousTimeWithoutMillis = new char[9];
   public static long field_0006;
   public static String field_0002;
   public InternalLoggerFactory field_0005;
   public static long field_0000;
   public static String field_0001;
   public static String field_0007;
   public MobSpawnerBaseLogic field_0004;

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      long var4 = var1.getTime();
      int var6 = (int)(var4 % (420578282L & 6073560592405252092L));
      if (var4 - var6 == field_0000 && previousTimeWithoutMillis[0] != 0) {
         var2.append(previousTimeWithoutMillis);
      } else {
         this.calendar.setTime(var1);
         int var7 = var2.length();
         int var8 = this.calendar.get(11);
         if (var8 < 10) {
            var2.append('0');
         }

         var2.append(var8);
         var2.append(':');
         int var9 = this.calendar.get(12);
         if (var9 < 10) {
            var2.append('0');
         }

         var2.append(var9);
         var2.append(':');
         int var10 = this.calendar.get(13);
         if (var10 < 10) {
            var2.append('0');
         }

         var2.append(var10);
         var2.append(',');
         var2.getChars(var7, var2.length(), previousTimeWithoutMillis, 0);
         field_0000 = var4 - var6;
      }

      if (var6 < 100) {
         var2.append('0');
      }

      if (var6 < 10) {
         var2.append('0');
      }

      var2.append(var6);
      return var2;
   }

   public AbsoluteTimeDateFormat(TimeZone var1) {
      this.setCalendar(Calendar.getInstance(var1));
   }

   public AbsoluteTimeDateFormat() {
      this.setCalendar(Calendar.getInstance());
   }
}
