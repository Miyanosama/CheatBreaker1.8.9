package org.apache.log4j;

import org.apache.log4j.lf5.viewer.LogBrokerMonitor$32;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.OptionHandler;

public abstract class Layout implements OptionHandler {
   public static int LINE_SEP_LEN = Layout.LINE_SEP.length();
   public LogBrokerMonitor$32 field_0001;
   public static String LINE_SEP = System.getProperty("line.separator");

   public abstract String format(LoggingEvent var1);

   public String getContentType() {
      return "text/plain";
   }

   public String E_() {
      return null;
   }

   public abstract boolean ignoresThrowable();

   public String getFooter() {
      return null;
   }
}
