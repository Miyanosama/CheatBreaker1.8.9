package org.apache.log4j.lf5.util;

import io.netty.handler.ssl.SslHandler$8;
import java.io.PrintWriter;
import java.io.StringWriter;
import net.minecraft.block.BlockHugeMushroom$EnumType;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;

public class AdapterLogRecord extends LogRecord {
   public static LogLevel severeLevel = null;
   public SslHandler$8 field_0000;
   public BlockHugeMushroom$EnumType field_0001;
   public static PrintWriter pw = new PrintWriter(AdapterLogRecord.sw);
   public static StringWriter sw = new StringWriter();

   public String getLocationInfo(String var1) {
      String var2 = this.stackTraceToString(new Throwable());
      return this.parseLine(var2, var1);
   }

   public String stackTraceToString(Throwable var1) {
      String var2 = null;
      synchronized (sw) {
         var1.printStackTrace(pw);
         var2 = sw.toString();
         sw.getBuffer().setLength(0);
         return var2;
      }
   }

   public static void setSevereLevel(LogLevel var0) {
      severeLevel = var0;
   }

   public boolean isSevereLevel() {
      return severeLevel == null ? false : severeLevel.equals(this.getLevel());
   }

   public String parseLine(String var1, String var2) {
      int var3 = var1.indexOf(var2);
      if (var3 == -1) {
         return null;
      } else {
         var1 = var1.substring(var3);
         return var1.substring(0, var1.indexOf(")") + 1);
      }
   }

   public static LogLevel getSevereLevel() {
      return severeLevel;
   }

   public void setCategory(String var1) {
      super.setCategory(var1);
      super.setLocation(this.getLocationInfo(var1));
   }
}
