package org.apache.log4j.chainsaw;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControlPanel$3 implements DocumentListener {
   public JTextField val$catField;
   public MyTableModel val$aModel;
   public ControlPanel this$0;

   public void changedUpdate(DocumentEvent var1) {
      this.val$aModel.setCategoryFilter(this.val$catField.getText());
   }

   public void insertUpdate(DocumentEvent var1) {
      this.val$aModel.setCategoryFilter(this.val$catField.getText());
   }

   public void removeUpdate(DocumentEvent var1) {
      this.val$aModel.setCategoryFilter(this.val$catField.getText());
   }

   public ControlPanel$3(ControlPanel var1, MyTableModel var2, JTextField var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$catField = var3;
      super();
   }
}
