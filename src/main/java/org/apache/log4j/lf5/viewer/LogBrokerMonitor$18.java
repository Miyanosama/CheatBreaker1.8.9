package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogBrokerMonitor$18 implements ActionListener {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;

   public LogBrokerMonitor$18(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestClose();
   }
}
