package org.apache.log4j.chainsaw;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main$1 extends WindowAdapter {
   // $VF: synthetic field
   public Main this$0;

   public Main$1(Main var1) {
      this.this$0 = var1;
   }

   public void windowClosing(WindowEvent var1) {
      ExitAction.INSTANCE.actionPerformed(null);
   }
}
