package org.apache.log4j.lf5.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import net.minecraft.entity.NpcMerchant;
import net.minecraft.entity.monster.EntityGiantZombie;

public class DateFormatManager {
   public TimeZone _timeZone = null;
   public Locale _locale = null;
   public DateFormat _dateFormat;
   public String _pattern = null;
   public EntityGiantZombie field_0000;
   public NpcMerchant field_0001;

   public synchronized Locale getLocale() {
      return this._locale == null ? Locale.getDefault() : this._locale;
   }

   public synchronized String method_12906() {
      return this._pattern;
   }

   public synchronized DateFormat getDateFormatInstance() {
      return this._dateFormat;
   }

   public DateFormatManager(TimeZone var1, Locale var2) {
      this._dateFormat = null;
      this._timeZone = var1;
      this._locale = var2;
      this.configure();
   }

   public DateFormatManager(TimeZone var1, Locale var2, String var3) {
      this._dateFormat = null;
      this._timeZone = var1;
      this._locale = var2;
      this._pattern = var3;
      this.configure();
   }

   public String format(Date var1, String var2) {
      Object var3 = null;
      var3 = this.getDateFormatInstance();
      if (var3 instanceof SimpleDateFormat) {
         var3 = (SimpleDateFormat)var3.clone();
         ((SimpleDateFormat)var3).applyPattern(var2);
      }

      return var3.format(var1);
   }

   public DateFormatManager(TimeZone var1, String var2) {
      this._dateFormat = null;
      this._timeZone = var1;
      this._pattern = var2;
      this.configure();
   }

   public synchronized void setTimeZone(TimeZone var1) {
      this._timeZone = var1;
      this.configure();
   }

   public Date parse(String var1) {
      return this.getDateFormatInstance().parse(var1);
   }

   public synchronized TimeZone getTimeZone() {
      return this._timeZone == null ? TimeZone.getDefault() : this._timeZone;
   }

   public synchronized void setLocale(Locale var1) {
      this._locale = var1;
      this.configure();
   }

   public DateFormatManager(TimeZone var1) {
      this._dateFormat = null;
      this._timeZone = var1;
      this.configure();
   }

   public String format(Date var1) {
      return this.getDateFormatInstance().format(var1);
   }

   public synchronized void method_12907(String var1) {
      this._pattern = var1;
      this.configure();
   }

   public DateFormatManager() {
      this._dateFormat = null;
      this.configure();
   }

   public Date parse(String var1, String var2) {
      Object var3 = null;
      var3 = this.getDateFormatInstance();
      if (var3 instanceof SimpleDateFormat) {
         var3 = (SimpleDateFormat)var3.clone();
         ((SimpleDateFormat)var3).applyPattern(var2);
      }

      return var3.parse(var1);
   }

   public synchronized void method_12896(String var1) {
      this._pattern = var1;
      this.configure();
   }

   public synchronized String method_12893() {
      return this._pattern;
   }

   public DateFormatManager(Locale var1) {
      this._dateFormat = null;
      this._locale = var1;
      this.configure();
   }

   public DateFormatManager(String var1) {
      this._dateFormat = null;
      this._pattern = var1;
      this.configure();
   }

   public DateFormatManager(Locale var1, String var2) {
      this._dateFormat = null;
      this._locale = var1;
      this._pattern = var2;
      this.configure();
   }

   public synchronized void configure() {
      this._dateFormat = SimpleDateFormat.getDateTimeInstance(0, 0, this.getLocale());
      this._dateFormat.setTimeZone(this.getTimeZone());
      if (this._pattern != null) {
         ((SimpleDateFormat)this._dateFormat).applyPattern(this._pattern);
      }
   }

   public synchronized void setDateFormatInstance(DateFormat var1) {
      this._dateFormat = var1;
   }
}
