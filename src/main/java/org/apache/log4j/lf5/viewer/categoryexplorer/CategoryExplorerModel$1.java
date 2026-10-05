package org.apache.log4j.lf5.viewer.categoryexplorer;

public class CategoryExplorerModel$1 implements Runnable {
   // $VF: synthetic field
   public CategoryNode val$node;
   // $VF: synthetic field
   public CategoryExplorerModel this$0;

   public void run() {
      this.this$0.nodeChanged(this.val$node);
   }

   public CategoryExplorerModel$1(CategoryExplorerModel var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
   }
}
