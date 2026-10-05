package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryNodeEditor$1 implements ActionListener {
   // $VF: synthetic field
   public CategoryNodeEditor this$0;

   public CategoryNodeEditor$1(CategoryNodeEditor var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0._categoryModel.update(this.this$0._lastEditedNode, this.this$0._checkBox.isSelected());
      this.this$0.stopCellEditing();
   }
}
