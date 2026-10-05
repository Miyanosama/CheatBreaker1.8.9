package org.apache.log4j.lf5.viewer.categoryexplorer;

import javax.swing.event.TreeModelEvent;

public class CategoryExplorerTree$1 extends TreeModelAdapter {
   // $VF: synthetic field
   public CategoryExplorerTree this$0;

   public CategoryExplorerTree$1(CategoryExplorerTree var1) {
      this.this$0 = var1;
   }

   public void treeNodesInserted(TreeModelEvent var1) {
      this.this$0.expandRootNode();
   }
}
