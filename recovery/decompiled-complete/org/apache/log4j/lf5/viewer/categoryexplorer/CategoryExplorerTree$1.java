package org.apache.log4j.lf5.viewer.categoryexplorer;

import javax.swing.event.TreeModelEvent;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.server.integrated.IntegratedServer$1;

public class CategoryExplorerTree$1 extends TreeModelAdapter {
   public IntegratedServer$1 field_0001;
   public CategoryExplorerTree this$0;
   public EntityArrow field_0000;

   public CategoryExplorerTree$1(CategoryExplorerTree var1) {
      this.this$0 = var1;
      super();
   }

   public void treeNodesInserted(TreeModelEvent var1) {
      this.this$0.expandRootNode();
   }
}
