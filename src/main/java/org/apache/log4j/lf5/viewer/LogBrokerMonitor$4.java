package org.apache.log4j.lf5.viewer;

import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

public class LogBrokerMonitor$4 implements LogRecordFilter {
   // $VF: synthetic field
   public LogBrokerMonitor this$0;

   public LogBrokerMonitor$4(LogBrokerMonitor var1) {
      this.this$0 = var1;
   }

   public boolean passes(LogRecord var1) {
      String var2 = var1.getNDC();
      CategoryPath var3 = new CategoryPath(var1.getCategory());
      if (var2 != null && this.this$0._NDCTextFilter != null) {
         return var2.toLowerCase().indexOf(this.this$0._NDCTextFilter.toLowerCase()) == -1
            ? false
            : this.this$0.getMenuItem(var1.getLevel()).isSelected() && this.this$0._categoryExplorerTree.getExplorerModel().isCategoryPathActive(var3);
      } else {
         return false;
      }
   }
}
