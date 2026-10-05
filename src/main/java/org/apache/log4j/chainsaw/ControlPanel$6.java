package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel$6 implements ActionListener {
   // $VF: synthetic field
   public ControlPanel this$0;
   // $VF: synthetic field
   public MyTableModel val$aModel;

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.clear();
   }

   public ControlPanel$6(ControlPanel var1, MyTableModel var2) {
      this.this$0 = var1;
      this.val$aModel = var2;
   }
}
