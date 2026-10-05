package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogBrokerMonitor$8 implements ActionListener {
   public LogBrokerMonitor this$0;
   public HttpPostBodyUtil field_0001;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.selectAllLogLevels(true);
      this.this$0._table.getFilteredLogTableModel().refresh();
      this.this$0.updateStatusLabel();
   }

   public LogBrokerMonitor$8(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
