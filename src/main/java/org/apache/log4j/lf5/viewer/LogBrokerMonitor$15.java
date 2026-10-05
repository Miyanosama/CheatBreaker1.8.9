package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogBrokerMonitor$15 implements ActionListener {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.selectAllLogTableColumns(false);
      List var2 = this.this$0.updateView();
      this.this$0._table.setView(var2);
   }

   public LogBrokerMonitor$15(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }
}
