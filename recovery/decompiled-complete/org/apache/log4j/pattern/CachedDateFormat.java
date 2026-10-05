package org.apache.log4j.pattern;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Date;
import java.util.TimeZone;
import net.minecraft.client.model.ModelBoat;

public class CachedDateFormat extends DateFormat {
   public static String field_0007;
   public static String field_0014;
   public static int field_0006;
   public StringBuffer cache = new StringBuffer(50);
   public int field_0002;
   public static String field_0003;
   public static int field_0015;
   public static String field_0010;
   public DateFormat formatter;
   public long field_0016;
   public Date field_0001 = new Date(406994944L & 6107079436404850980L);
   public static long field_0008;
   public ModelBoat field_0009;
   public static int field_0005;
   public int field_0011;
   public static int field_0013;
   public long field_0000;

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
      if (var1 == this.field_0016) {
         var3.append(this.cache);
         return var3;
      } else if (this.field_0011 != -1
         && var1 < this.field_0000 + this.field_0002
         && var1 >= this.field_0000
         && var1 < this.field_0000 + (-1475453757309036568L & 1475453755590747112L)) {
         if (this.field_0011 >= 0) {
            millisecondFormat((int)(var1 - this.field_0000), this.cache, this.field_0011);
         }

         this.field_0016 = var1;
         var3.append(this.cache);
         return var3;
      } else {
         this.cache.setLength(0);
         this.field_0001.setTime(var1);
         this.cache.append(this.formatter.format(this.field_0001));
         var3.append(this.cache);
         this.field_0016 = var1;
         this.field_0000 = this.field_0016 / (1210175466L & 5855071129425679357L) * (-3887213401436922898L & 1001L);
         if (this.field_0000 > this.field_0016) {
            this.field_0000 -= 6758374629462901737L & -6758374631204706326L;
         }

         if (this.field_0011 >= 0) {
            this.field_0011 = findMillisecondStart(var1, this.cache.toString(), this.formatter);
         }

         return var3;
      }
   }

   public void setTimeZone(TimeZone var1) {
      this.formatter.setTimeZone(var1);
      this.field_0016 = -9223372036448911159L & -862671219227359946L;
      this.field_0000 = -3112516626136676964L & -9223372036854709760L;
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
         this.field_0002 = var2;
         this.field_0011 = 0;
         this.field_0016 = -96163745385651520L & -9223372036669105892L;
         this.field_0000 = -9223372036407017468L & -9223372035155754877L;
      }
   }

   public static int getMaximumCacheValidity(String var0) {
      int var1 = var0.indexOf(83);
      return var1 >= 0 && var1 != var0.lastIndexOf("SSS") ? 1 : 1000;
   }

   public static int findMillisecondStart(long var0, String var2, DateFormat var3) {
      long var4 = var0 / (402719736L & 1112568812L) * (288891896L & 180521966L);
      if (var4 > var0) {
         var4 -= 1836919818259929064L & -1836919818674926616L;
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
