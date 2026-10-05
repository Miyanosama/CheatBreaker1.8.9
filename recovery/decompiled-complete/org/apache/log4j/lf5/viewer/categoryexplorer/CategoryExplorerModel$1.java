package org.apache.log4j.lf5.viewer.categoryexplorer;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchEntriesTask;
import net.minecraft.util.WeightedRandom;

public class CategoryExplorerModel$1 implements Runnable {
   public WeightedRandom field_0001;
   public CategoryNode val$node;
   public ConcurrentHashMapV8$SearchEntriesTask field_0000;
   public CategoryExplorerModel this$0;

   public void run() {
      this.this$0.nodeChanged(this.val$node);
   }

   public CategoryExplorerModel$1(CategoryExplorerModel var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
      super();
   }
}
