package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CategoryNodeEditor$2 extends MouseAdapter {
   // $VF: synthetic field
   public CategoryNodeEditor this$0;

   public void mousePressed(MouseEvent var1) {
      if ((var1.getModifiers() & 4) != 0) {
         this.this$0.showPopup(this.this$0._lastEditedNode, var1.getX(), var1.getY());
      }

      this.this$0.stopCellEditing();
   }

   public CategoryNodeEditor$2(CategoryNodeEditor var1) {
      this.this$0 = var1;
   }
}
