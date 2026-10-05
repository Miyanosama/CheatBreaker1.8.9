package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import org.apache.log4j.Priority;

public class ControlPanel$1 implements ActionListener {
   // $VF: synthetic field
   public ControlPanel this$0;
   // $VF: synthetic field
   public MyTableModel val$aModel;
   // $VF: synthetic field
   public JComboBox val$priorities;

   public ControlPanel$1(ControlPanel var1, MyTableModel var2, JComboBox var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$priorities = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.setPriorityFilter((Priority)this.val$priorities.getSelectedItem());
   }
}
