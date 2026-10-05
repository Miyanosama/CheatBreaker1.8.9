package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import org.apache.log4j.lf5.LogLevel;

public class LogBrokerMonitor$11 implements ActionListener {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;
   // $VF: synthetic field
   public LogLevel val$logLevel;
   // $VF: synthetic field
   public JMenuItem val$result;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.showLogLevelColorChangeDialog(this.val$result, this.val$logLevel);
   }

   public LogBrokerMonitor$11(LogBrokerMonitor var1, JMenuItem var2, LogLevel var3) {
      this.this$0 = var1;
      this.val$result = var2;
      this.val$logLevel = var3;
   }
}
