package org.apache.log4j.lf5;

import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class StartLogFactor5 {
   public static void main(String[] var0) {
      LogBrokerMonitor var1 = new LogBrokerMonitor(LogLevel.getLog4JLevels());
      var1.setFrameSize(LF5Appender.getDefaultMonitorWidth(), LF5Appender.getDefaultMonitorHeight());
      var1.setFontSize(12);
      var1.show();
   }
}
