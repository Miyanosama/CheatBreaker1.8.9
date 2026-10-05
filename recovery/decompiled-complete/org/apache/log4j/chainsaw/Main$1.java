package org.apache.log4j.chainsaw;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.apache.log4j.spi.Filter;

public class Main$1 extends WindowAdapter {
   public Filter field_0000;
   public Main this$0;

   public Main$1(Main var1) {
      this.this$0 = var1;
      super();
   }

   public void windowClosing(WindowEvent var1) {
      ExitAction.INSTANCE.actionPerformed(null);
   }
}
