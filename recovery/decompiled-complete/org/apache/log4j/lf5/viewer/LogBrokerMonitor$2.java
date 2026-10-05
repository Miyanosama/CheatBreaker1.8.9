package org.apache.log4j.lf5.viewer;

import com.cheatbreaker.client.util.teammates.Teammate;
import net.minecraft.client.gui.stream.GuiIngestServers;
import net.minecraft.item.ItemAxe;
import net.minecraft.world.LockCode;
import org.apache.log4j.lf5.LogRecord;

public class LogBrokerMonitor$2 implements Runnable {
   public Teammate field_0003;
   public GuiIngestServers field_0005;
   public ItemAxe field_0002;
   public LogBrokerMonitor this$0;
   public LockCode field_0000;
   public LogRecord val$lr;

   public void run() {
      this.this$0._categoryExplorerTree.getExplorerModel().addLogRecord(this.val$lr);
      this.this$0._table.getFilteredLogTableModel().addLogRecord(this.val$lr);
      this.this$0.updateStatusLabel();
   }

   public LogBrokerMonitor$2(LogBrokerMonitor var1, LogRecord var2) {
      this.this$0 = var1;
      this.val$lr = var2;
      super();
   }
}
