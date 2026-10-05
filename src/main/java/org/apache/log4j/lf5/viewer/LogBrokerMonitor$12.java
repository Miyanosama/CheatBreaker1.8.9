package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogBrokerMonitor$12 implements ActionListener {
   public LogBrokerMonitor recoveredField1615;

   public void actionPerformed(ActionEvent var1) {
      this.recoveredField1615._table.getFilteredLogTableModel().refresh();
      this.recoveredField1615.updateStatusLabel();
   }

   public LogBrokerMonitor$12(LogBrokerMonitor var1) {
      this.recoveredField1615 = var1;
   }
}
