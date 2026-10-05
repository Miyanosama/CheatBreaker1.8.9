package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.util.Enumeration;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;

public class CategoryExplorerLogRecordFilter implements LogRecordFilter {
   public CategoryExplorerModel _model;

   public CategoryExplorerLogRecordFilter(CategoryExplorerModel var1) {
      this._model = var1;
   }

   public void reset() {
      this.resetAllNodes();
   }

   public void resetAllNodes() {
      Enumeration var1 = this._model.getRootCategoryNode().depthFirstEnumeration();

      while (var1.hasMoreElements()) {
         CategoryNode var2 = (CategoryNode)var1.nextElement();
         var2.resetNumberOfContainedRecords();
         this._model.nodeChanged(var2);
      }
   }

   public boolean passes(LogRecord var1) {
      CategoryPath var2 = new CategoryPath(var1.getCategory());
      return this._model.isCategoryPathActive(var2);
   }
}
