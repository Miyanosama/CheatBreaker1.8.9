package org.apache.log4j.lf5.viewer;

import net.minecraft.server.network.NetHandlerStatusServer;

public class LogBrokerMonitor$1 implements Runnable {
   public NetHandlerStatusServer field_0001;
   public LogBrokerMonitor this$0;
   public int val$delay;

   public void run() {
      Thread.yield();
      this.this$0.pause(this.val$delay);
      this.this$0._logMonitorFrame.setVisible(true);
   }

   public LogBrokerMonitor$1(LogBrokerMonitor var1, int var2) {
      this.this$0 = var1;
      this.val$delay = var2;
      super();
   }
}
