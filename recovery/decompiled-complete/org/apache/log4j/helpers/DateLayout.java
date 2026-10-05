package org.apache.log4j.helpers;

import com.cheatbreaker.client.nethandler.server.PacketNotification;
import io.netty.channel.local.LocalChannel$4;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import junit.awtui.TestRunner$1;
import junit.framework.TestResult;
import net.minecraft.client.stream.ChatController$2;
import net.minecraft.command.CommandHandler;
import net.minecraft.item.ItemArmor$1;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public abstract class DateLayout extends Layout {
   public PacketNotification field_0006;
   public CommandHandler field_0015;
   public ItemArmor$1 field_0005;
   public static String field_0008;
   public static String field_0011;
   public static String field_0003;
   public static String field_0007;
   public TestRunner$1 field_0009;
   public FieldPosition pos = new FieldPosition(0);
   public ChatController$2 field_0002;
   public TestResult field_0001;
   public LocalChannel$4 field_0004;
   public String timeZoneID;
   public DateFormat dateFormat;
   public String dateFormatOption;
   public Date date = new Date();

   public void setDateFormat(String var1) {
      if (var1 != null) {
         this.dateFormatOption = var1;
      }

      this.setDateFormat(this.dateFormatOption, TimeZone.getDefault());
   }

   public String getTimeZone() {
      return this.timeZoneID;
   }

   public String getDateFormat() {
      return this.dateFormatOption;
   }

   public void dateFormat(StringBuffer var1, LoggingEvent var2) {
      if (this.dateFormat != null) {
         this.date.setTime(var2.timeStamp);
         this.dateFormat.format(this.date, var1, this.pos);
         var1.append(' ');
      }
   }

   public void setTimeZone(String var1) {
      this.timeZoneID = var1;
   }

   public void activateOptions() {
      this.setDateFormat(this.dateFormatOption);
      if (this.timeZoneID != null && this.dateFormat != null) {
         this.dateFormat.setTimeZone(TimeZone.getTimeZone(this.timeZoneID));
      }
   }

   public void setDateFormat(DateFormat var1, TimeZone var2) {
      this.dateFormat = var1;
      this.dateFormat.setTimeZone(var2);
   }

   public void setDateFormat(String var1, TimeZone var2) {
      if (var1 == null) {
         this.dateFormat = null;
      } else {
         if (var1.equalsIgnoreCase("NULL")) {
            this.dateFormat = null;
         } else if (var1.equalsIgnoreCase("RELATIVE")) {
            this.dateFormat = new RelativeTimeDateFormat();
         } else if (var1.equalsIgnoreCase("ABSOLUTE")) {
            this.dateFormat = new AbsoluteTimeDateFormat(var2);
         } else if (var1.equalsIgnoreCase("DATE")) {
            this.dateFormat = new DateTimeDateFormat(var2);
         } else if (var1.equalsIgnoreCase("ISO8601")) {
            this.dateFormat = new ISO8601DateFormat(var2);
         } else {
            this.dateFormat = new SimpleDateFormat(var1);
            this.dateFormat.setTimeZone(var2);
         }
      }
   }

   public void setOption(String var1, String var2) {
      if (var1.equalsIgnoreCase("DateFormat")) {
         this.dateFormatOption = var2.toUpperCase();
      } else if (var1.equalsIgnoreCase("TimeZone")) {
         this.timeZoneID = var2;
      }
   }

   public String[] getOptionStrings() {
      return new String[]{"DateFormat", "TimeZone"};
   }
}
