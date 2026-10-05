package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogBrokerMonitor$13 implements ActionListener {
   public LogBrokerMonitor recoveredField3647;

   public void actionPerformed(ActionEvent var1) {
      List var2 = this.recoveredField3647.updateView();
      this.recoveredField3647._table.setView(var2);
   }

   public LogBrokerMonitor$13(LogBrokerMonitor var1) {
      this.recoveredField3647 = var1;
   }
}
