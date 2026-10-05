package org.apache.log4j.lf5;

import java.awt.Toolkit;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class LF5Appender extends AppenderSkeleton {
   public static AppenderFinalizer _finalizer;
   public LogBrokerMonitor _logMonitor;
   public static LogBrokerMonitor _defaultLogMonitor;

   public boolean equals(LF5Appender var1) {
      return this._logMonitor == var1.getLogBrokerMonitor();
   }

   public void append(LoggingEvent var1) {
      String var2 = var1.getLoggerName();
      String var3 = var1.getRenderedMessage();
      String var4 = var1.getNDC();
      String var5 = var1.getThreadName();
      String var6 = var1.getLevel().toString();
      long var7 = var1.timeStamp;
      LocationInfo var9 = var1.getLocationInformation();
      Log4JLogRecord var10 = new Log4JLogRecord();
      var10.setCategory(var2);
      var10.setMessage(var3);
      var10.setLocation(var9.fullInfo);
      var10.setMillis(var7);
      var10.setThreadDescription(var5);
      if (var4 != null) {
         var10.setNDC(var4);
      } else {
         var10.setNDC("");
      }

      if (var1.getThrowableInformation() != null) {
         var10.setThrownStackTrace(var1.getThrowableInformation());
      }

      try {
         var10.setLevel(LogLevel.valueOf(var6));
      } catch (LogLevelFormatException var12) {
         var10.setLevel(LogLevel.WARN);
      }

      if (this._logMonitor != null) {
         this._logMonitor.addMessage(var10);
      }
   }

   public void setMaxNumberOfRecords(int var1) {
      _defaultLogMonitor.setMaxNumberOfLogRecords(var1);
   }

   public static synchronized LogBrokerMonitor getDefaultInstance() {
      if (_defaultLogMonitor == null) {
         try {
            _defaultLogMonitor = new LogBrokerMonitor(LogLevel.getLog4JLevels());
            _finalizer = new AppenderFinalizer(_defaultLogMonitor);
            _defaultLogMonitor.setFrameSize(getDefaultMonitorWidth(), getDefaultMonitorHeight());
            _defaultLogMonitor.setFontSize(12);
            _defaultLogMonitor.show();
         } catch (SecurityException var1) {
            _defaultLogMonitor = null;
         }
      }

      return _defaultLogMonitor;
   }

   public static void main(String[] var0) {
      new LF5Appender();
   }

   public void close() {
   }

   public static int getDefaultMonitorHeight() {
      return 3 * getScreenHeight() / 4;
   }

   public void setCallSystemExitOnClose(boolean var1) {
      this._logMonitor.setCallSystemExitOnClose(var1);
   }

   public static int getScreenHeight() {
      try {
         return Toolkit.getDefaultToolkit().getScreenSize().height;
      } catch (Throwable var1) {
         return 600;
      }
   }

   public boolean requiresLayout() {
      return false;
   }

   public static int getScreenWidth() {
      try {
         return Toolkit.getDefaultToolkit().getScreenSize().width;
      } catch (Throwable var1) {
         return 800;
      }
   }

   public LogBrokerMonitor getLogBrokerMonitor() {
      return this._logMonitor;
   }

   public static int getDefaultMonitorWidth() {
      return 3 * getScreenWidth() / 4;
   }

   public LF5Appender() {
      this(getDefaultInstance());
   }

   public LF5Appender(LogBrokerMonitor var1) {
      if (var1 != null) {
         this._logMonitor = var1;
      }
   }
}
