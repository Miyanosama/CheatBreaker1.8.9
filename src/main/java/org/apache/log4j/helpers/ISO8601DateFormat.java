package org.apache.log4j.helpers;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;

public class ISO8601DateFormat extends AbsoluteTimeDateFormat {
   public static final long recoveredField253 = -759840745298755296L;
   public static char[] lastTimeString = new char[20];
   public static long recoveredField254;

   public Date parse(String var1, ParsePosition var2) {
      return null;
   }

   public ISO8601DateFormat() {
   }

   public ISO8601DateFormat(TimeZone var1) {
      super(var1);
   }

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      long var4 = var1.getTime();
      int var6 = (int)(var4 % 1000L);
      if (var4 - var6 == recoveredField254 && lastTimeString[0] != 0) {
         var2.append(lastTimeString);
      } else {
         this.calendar.setTime(var1);
         int var7 = var2.length();
         int var8 = this.calendar.get(1);
         var2.append(var8);
         String var9;
         switch (this.calendar.get(2)) {
            case 0:
               var9 = "-01-";
               break;
            case 1:
               var9 = "-02-";
               break;
            case 2:
               var9 = "-03-";
               break;
            case 3:
               var9 = "-04-";
               break;
            case 4:
               var9 = "-05-";
               break;
            case 5:
               var9 = "-06-";
               break;
            case 6:
               var9 = "-07-";
               break;
            case 7:
               var9 = "-08-";
               break;
            case 8:
               var9 = "-09-";
               break;
            case 9:
               var9 = "-10-";
               break;
            case 10:
               var9 = "-11-";
               break;
            case 11:
               var9 = "-12-";
               break;
            default:
               var9 = "-NA-";
         }

         var2.append(var9);
         int var10 = this.calendar.get(5);
         if (var10 < 10) {
            var2.append('0');
         }

         var2.append(var10);
         var2.append(' ');
         int var11 = this.calendar.get(11);
         if (var11 < 10) {
            var2.append('0');
         }

         var2.append(var11);
         var2.append(':');
         int var12 = this.calendar.get(12);
         if (var12 < 10) {
            var2.append('0');
         }

         var2.append(var12);
         var2.append(':');
         int var13 = this.calendar.get(13);
         if (var13 < 10) {
            var2.append('0');
         }

         var2.append(var13);
         var2.append(',');
         var2.getChars(var7, var2.length(), lastTimeString, 0);
         recoveredField254 = var4 - var6;
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
}
