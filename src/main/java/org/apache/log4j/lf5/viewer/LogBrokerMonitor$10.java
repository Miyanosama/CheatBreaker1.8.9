package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.lf5.LogLevel;

public class LogBrokerMonitor$10 implements ActionListener {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;

   public LogBrokerMonitor$10(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      LogLevel.resetLogLevelColorMap();
      this.this$0._table.getFilteredLogTableModel().refresh();
   }
}
