package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class LogBrokerMonitor$31 implements ActionListener {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;

   public LogBrokerMonitor$31(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      JComboBox var2 = (JComboBox)var1.getSource();
      String var3 = (String)var2.getSelectedItem();
      int var4 = Integer.valueOf(var3);
      this.this$0.setFontSizeSilently(var4);
      this.this$0.refreshDetailTextArea();
      this.this$0._fontSize = var4;
   }
}
