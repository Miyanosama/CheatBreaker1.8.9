package org.apache.log4j.chainsaw;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ControlPanel$4 implements DocumentListener {
   // $VF: synthetic field
   public MyTableModel val$aModel;
   // $VF: synthetic field
   public ControlPanel this$0;
   // $VF: synthetic field
   public JTextField val$ndcField;

   public void insertUpdate(DocumentEvent var1) {
      this.val$aModel.setNDCFilter(this.val$ndcField.getText());
   }

   public void removeUpdate(DocumentEvent var1) {
      this.val$aModel.setNDCFilter(this.val$ndcField.getText());
   }

   public void changedUpdate(DocumentEvent var1) {
      this.val$aModel.setNDCFilter(this.val$ndcField.getText());
   }

   public ControlPanel$4(ControlPanel var1, MyTableModel var2, JTextField var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$ndcField = var3;
   }
}
