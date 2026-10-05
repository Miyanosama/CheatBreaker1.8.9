package org.apache.log4j.lf5.util;

import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import net.minecraft.inventory.ContainerBrewingStand$Ingredient;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class LogMonitorAdapter {
   public static int field_0002;
   public static int field_0004;
   public LogBrokerMonitor _logMonitor;
   public ContainerBrewingStand$Ingredient field_0003;
   public LogLevel _defaultLevel = null;

   public static int getScreenHeight() {
      try {
         return Toolkit.getDefaultToolkit().getScreenSize().height;
      } catch (Throwable var1) {
         return 600;
      }
   }

   public void log(String var1, LogLevel var2, String var3, String var4) {
      this.log(var1, var2, var3, null, var4);
   }

   public static LogMonitorAdapter newInstance(LogLevel[] var0) {
      return var0 == null ? null : newInstance(Arrays.asList(var0));
   }

   public static LogMonitorAdapter newInstance(int var0) {
      LogMonitorAdapter var1;
      if (var0 == 1) {
         var1 = newInstance(LogLevel.getJdk14Levels());
         var1.setDefaultLevel(LogLevel.FINEST);
         var1.setSevereLevel(LogLevel.SEVERE);
      } else {
         var1 = newInstance(LogLevel.getLog4JLevels());
         var1.setDefaultLevel(LogLevel.DEBUG);
         var1.setSevereLevel(LogLevel.FATAL);
      }

      return var1;
   }

   public void setMaxNumberOfRecords(int var1) {
      this._logMonitor.setMaxNumberOfLogRecords(var1);
   }

   public static int getDefaultMonitorHeight() {
      return 3 * getScreenHeight() / 4;
   }

   public static int getScreenWidth() {
      try {
         return Toolkit.getDefaultToolkit().getScreenSize().width;
      } catch (Throwable var1) {
         return 800;
      }
   }

   public void setDefaultLevel(LogLevel var1) {
      this._defaultLevel = var1;
   }

   public LogLevel getDefaultLevel() {
      return this._defaultLevel;
   }

   public static int getDefaultMonitorWidth() {
      return 3 * getScreenWidth() / 4;
   }

   public void log(String var1, String var2) {
      this.log(var1, null, var2);
   }

   public void log(String var1, LogLevel var2, String var3, Throwable var4, String var5) {
      AdapterLogRecord var6 = new AdapterLogRecord();
      var6.setCategory(var1);
      var6.setMessage(var3);
      var6.setNDC(var5);
      var6.setThrown(var4);
      if (var2 == null) {
         var6.setLevel(this.getDefaultLevel());
      } else {
         var6.setLevel(var2);
      }

      this.addMessage(var6);
   }

   public LogLevel getSevereLevel() {
      return AdapterLogRecord.getSevereLevel();
   }

   public static LogMonitorAdapter newInstance(List var0) {
      return new LogMonitorAdapter(var0);
   }

   public void addMessage(LogRecord var1) {
      this._logMonitor.addMessage(var1);
   }

   public void log(String var1, LogLevel var2, String var3, Throwable var4) {
      this.log(var1, var2, var3, var4, null);
   }

   public LogMonitorAdapter(List var1) {
      this._defaultLevel = (LogLevel)var1.get(0);
      this._logMonitor = new LogBrokerMonitor(var1);
      this._logMonitor.setFrameSize(getDefaultMonitorWidth(), getDefaultMonitorHeight());
      this._logMonitor.setFontSize(12);
      this._logMonitor.show();
   }

   public void setSevereLevel(LogLevel var1) {
      AdapterLogRecord.setSevereLevel(var1);
   }

   public void log(String var1, LogLevel var2, String var3) {
      this.log(var1, var2, var3, null, null);
   }
}
