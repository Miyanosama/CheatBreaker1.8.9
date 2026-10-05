package org.apache.log4j.chainsaw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.extensions.TestSetup;
import net.minecraft.item.Item$16;

public class ControlPanel$6 implements ActionListener {
   public Item$16 field_0001;
   public ControlPanel this$0;
   public TestSetup field_0000;
   public MyTableModel val$aModel;

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.clear();
   }

   public ControlPanel$6(ControlPanel var1, MyTableModel var2) {
      this.this$0 = var1;
      this.val$aModel = var2;
      super();
   }
}
