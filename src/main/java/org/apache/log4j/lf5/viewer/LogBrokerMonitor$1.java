package org.apache.log4j.lf5.viewer;

public class LogBrokerMonitor$1 implements Runnable {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;
   // $VF: synthetic field
   public int val$delay;

   public void run() {
      Thread.yield();
      this.this$0.pause(this.val$delay);
      this.this$0._logMonitorFrame.setVisible(true);
   }

   public LogBrokerMonitor$1(LogBrokerMonitor var1, int var2) {
      this.this$0 = var1;
      this.val$delay = var2;
   }
}
