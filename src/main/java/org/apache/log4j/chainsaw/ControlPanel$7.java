package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ControlPanel$7 implements ActionListener {
   // $VF: synthetic field
   public MyTableModel val$aModel;
   // $VF: synthetic field
   public JButton val$toggleButton;
   // $VF: synthetic field
   public ControlPanel this$0;

   public ControlPanel$7(ControlPanel var1, MyTableModel var2, JButton var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$toggleButton = var3;
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.toggle();
      this.val$toggleButton.setText(this.val$aModel.isPaused() ? "Resume" : "Pause");
   }
}
