package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryNodeEditor$3 implements ActionListener {
   // $VF: synthetic field
   public CategoryNodeEditor this$0;
   // $VF: synthetic field
   public CategoryNode val$node;

   public CategoryNodeEditor$3(CategoryNodeEditor var1, CategoryNode var2) {
      this.this$0 = var1;
      this.val$node = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.showPropertiesDialog(this.val$node);
   }
}
