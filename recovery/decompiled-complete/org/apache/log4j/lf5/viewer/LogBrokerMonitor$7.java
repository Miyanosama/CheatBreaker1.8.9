package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.http.HttpContentEncoder$1;
import io.netty.util.ThreadDeathWatcher$1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;

public class LogBrokerMonitor$7 implements ActionListener {
   public LogBrokerMonitor this$0;
   public ThreadDeathWatcher$1 field_0003;
   public RegionRenderCacheBuilder field_0000;
   public HttpContentEncoder$1 field_0002;

   public LogBrokerMonitor$7(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._table.getFilteredLogTableModel().refresh();
      this.this$0.updateStatusLabel();
   }
}
