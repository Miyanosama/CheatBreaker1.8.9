package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.optifine.entity.model.ModelAdapterSquid;

public class LogBrokerMonitor$9 implements ActionListener {
   public ModelAdapterSquid field_0000;
   public LogBrokerMonitor this$0;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.selectAllLogLevels(false);
      this.this$0._table.getFilteredLogTableModel().refresh();
      this.this$0.updateStatusLabel();
   }

   public LogBrokerMonitor$9(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
