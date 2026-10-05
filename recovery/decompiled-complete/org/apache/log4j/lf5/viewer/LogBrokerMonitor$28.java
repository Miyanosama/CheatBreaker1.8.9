package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.crash.CrashReport;

public class LogBrokerMonitor$28 implements ActionListener {
   public GuiCrafting field_0001;
   public LogBrokerMonitor this$0;
   public CrashReport field_0000;

   public LogBrokerMonitor$28(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._table.getFilteredLogTableModel().setLogRecordFilter(this.this$0.createLogRecordFilter());
      this.this$0.setNDCTextFilter("");
      this.this$0._table.getFilteredLogTableModel().refresh();
      this.this$0.updateStatusLabel();
   }
}
