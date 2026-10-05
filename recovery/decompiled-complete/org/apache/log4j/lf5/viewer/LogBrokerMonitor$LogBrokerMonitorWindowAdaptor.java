package org.apache.log4j.lf5.viewer;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.minecraft.entity.item.EntityExpBottle;

public class LogBrokerMonitor$LogBrokerMonitorWindowAdaptor extends WindowAdapter {
   public EntityExpBottle field_0001;
   public LogBrokerMonitor this$0;
   public LogBrokerMonitor _monitor;

   public void windowClosing(WindowEvent var1) {
      this._monitor.requestClose();
   }

   public LogBrokerMonitor$LogBrokerMonitorWindowAdaptor(LogBrokerMonitor var1, LogBrokerMonitor var2) {
      this.this$0 = var1;
      super();
      this._monitor = var2;
   }
}
