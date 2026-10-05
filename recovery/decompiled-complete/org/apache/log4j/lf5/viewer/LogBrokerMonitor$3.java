package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.compression.CompressionException;
import net.minecraft.client.renderer.entity.RenderHorse;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

public class LogBrokerMonitor$3 implements LogRecordFilter {
   public LogBrokerMonitor this$0;
   public RenderHorse field_0002;
   public CompressionException field_0000;

   public boolean passes(LogRecord var1) {
      CategoryPath var2 = new CategoryPath(var1.getCategory());
      return this.this$0.getMenuItem(var1.getLevel()).isSelected() && this.this$0._categoryExplorerTree.getExplorerModel().isCategoryPathActive(var2);
   }

   public LogBrokerMonitor$3(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
