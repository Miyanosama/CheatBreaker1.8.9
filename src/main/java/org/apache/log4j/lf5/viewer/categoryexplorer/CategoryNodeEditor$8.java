package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryNodeEditor$8 implements ActionListener {
   // $VF: synthetic field
   public CategoryNodeEditor this$0;

   public void actionPerformed(ActionEvent var1) {
      while (this.this$0.removeUnusedNodes() > 0) {
      }
   }

   public CategoryNodeEditor$8(CategoryNodeEditor var1) {
      this.this$0 = var1;
   }
}
