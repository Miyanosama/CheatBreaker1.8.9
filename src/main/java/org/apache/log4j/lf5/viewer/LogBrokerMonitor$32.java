package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import org.apache.log4j.lf5.LogLevel;

public class LogBrokerMonitor$32 implements ActionListener {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;

   public void actionPerformed(ActionEvent var1) {
      JComboBox var2 = (JComboBox)var1.getSource();
      LogLevel var3 = (LogLevel)var2.getSelectedItem();
      this.this$0.setLeastSevereDisplayedLogLevel(var3);
   }

   public LogBrokerMonitor$32(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }
}
