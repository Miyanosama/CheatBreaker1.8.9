package org.apache.log4j.lf5;

import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class AppenderFinalizer {
   public LogBrokerMonitor _defaultMonitor = null;

   public AppenderFinalizer(LogBrokerMonitor var1) {
      this._defaultMonitor = var1;
   }

   public void finalize() throws java.lang.Throwable {
      System.out.println("Disposing of the default LogBrokerMonitor instance");
      this._defaultMonitor.dispose();
   }
}
