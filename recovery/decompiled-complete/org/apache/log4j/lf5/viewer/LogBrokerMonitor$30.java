package org.apache.log4j.lf5.viewer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

public class LogBrokerMonitor$30 implements ActionListener {
   public LogBrokerMonitor this$0;

   public void actionPerformed(ActionEvent var1) {
      JComboBox var2 = (JComboBox)var1.getSource();
      String var3 = (String)var2.getSelectedItem();
      this.this$0._table.setFont(new Font(var3, 0, this.this$0._fontSize));
      this.this$0._fontName = var3;
   }

   public LogBrokerMonitor$30(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
