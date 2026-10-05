package org.apache.log4j.pattern;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;

public class CachedDateFormat extends DateFormat {
   public static final String recoveredField2882 = "654";
   public static final String recoveredField2883 = "987";
   public static final int recoveredField2884 = 654;
   public StringBuffer cache = new StringBuffer(50);
   public int recoveredField2885;
   public static final String recoveredField2886 = "000";
   public static final int recoveredField2887 = 987;
   public static final String recoveredField2888 = "0123456789";
   public DateFormat formatter;
   public long recoveredField2889;
   public Date recoveredField2890 = new Date(0L);
   public static final long recoveredField2891 = 1L;
   public static final int recoveredField2892 = -1;
   public int recoveredField2893;
   public static final int recoveredField2894 = -2;
   public long recoveredField2895;

   public StringBuffer format(Date var1, StringBuffer var2, FieldPosition var3) {
      this.format(var1.getTime(), var2);
      return var2;
   }

   public static void millisecondFormat(int var0, StringBuffer var1, int var2) {
      var1.setCharAt(var2, "0123456789".charAt(var0 / 100));
      var1.setCharAt(var2 + 1, "0123456789".charAt(var0 / 10 % 10));
      var1.setCharAt(var2 + 2, "0123456789".charAt(var0 % 10));
   }

   public StringBuffer format(long var1, StringBuffer var3) {
      if (var1 == this.recoveredField2889) {
         var3.append(this.cache);
         return var3;
      } else if (this.recoveredField2893 != -1
         && var1 < this.recoveredField2895 + this.recoveredField2885
         && var1 >= this.recoveredField2895
         && var1 < this.recoveredField2895 + 1000L) {
         if (this.recoveredField2893 >= 0) {
            millisecondFormat((int)(var1 - this.recoveredField2895), this.cache, this.recoveredField2893);
         }

         this.recoveredField2889 = var1;
         var3.append(this.cache);
         return var3;
      } else {
         this.cache.setLength(0);
         this.recoveredField2890.setTime(var1);
         this.cache.append(this.formatter.format(this.recoveredField2890));
         var3.append(this.cache);
         this.recoveredField2889 = var1;
         this.recoveredField2895 = this.recoveredField2889 / 1000L * 1000L;
         if (this.recoveredField2895 > this.recoveredField2889) {
            this.recoveredField2895 -= 1000L;
         }

         if (this.recoveredField2893 >= 0) {
            this.recoveredField2893 = findMillisecondStart(var1, this.cache.toString(), this.formatter);
         }

         return var3;
      }
   }

   public void setTimeZone(TimeZone var1) {
      this.formatter.setTimeZone(var1);
      this.recoveredField2889 = Long.MIN_VALUE;
      this.recoveredField2895 = Long.MIN_VALUE;
   }

   public Date parse(String var1, ParsePosition var2) {
      return this.formatter.parse(var1, var2);
   }

   public CachedDateFormat(DateFormat var1, int var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("dateFormat cannot be null");
      } else if (var2 < 0) {
         throw new IllegalArgumentException("expiration must be non-negative");
      } else {
         this.formatter = var1;
         this.recoveredField2885 = var2;
         this.recoveredField2893 = 0;
         this.recoveredField2889 = Long.MIN_VALUE;
         this.recoveredField2895 = Long.MIN_VALUE;
      }
   }

   public static int getMaximumCacheValidity(String var0) {
      int var1 = var0.indexOf(83);
      return var1 >= 0 && var1 != var0.lastIndexOf("SSS") ? 1 : 1000;
   }

   public static int findMillisecondStart(long var0, String var2, DateFormat var3) {
      long var4 = var0 / 1000L * 1000L;
      if (var4 > var0) {
         var4 -= 1000L;
      }

      int var6 = (int)(var0 - var4);
      short var7 = 654;
      String var8 = "654";
      if (var6 == 654) {
         var7 = 987;
         var8 = "987";
      }

      String var9 = var3.format(new Date(var4 + var7));
      if (var9.length() != var2.length()) {
         return -1;
      } else {
         for (int var10 = 0; var10 < var2.length(); var10++) {
            if (var2.charAt(var10) != var9.charAt(var10)) {
               StringBuffer var11 = new StringBuffer("ABC");
               millisecondFormat(var6, var11, 0);
               String var12 = var3.format(new Date(var4));
               if (var12.length() == var2.length()
                  && var8.regionMatches(0, var9, var10, var8.length())
                  && var11.toString().regionMatches(0, var2, var10, var8.length())
                  && "000".regionMatches(0, var12, var10, "000".length())) {
                  return var10;
               }

               return -1;
            }
         }

         return -2;
      }
   }

   public NumberFormat getNumberFormat() {
      return this.formatter.getNumberFormat();
   }
}
