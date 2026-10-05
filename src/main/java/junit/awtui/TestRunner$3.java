package junit.awtui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import junit.awtui.TestRunner;

public class TestRunner$3 extends WindowAdapter {
   public Frame recoveredField76;
   public TestRunner recoveredField77;

   public TestRunner$3(TestRunner var1, Frame var2) {
      this.recoveredField77 = var1;
      this.recoveredField76 = var2;
   }

   public void windowClosing(WindowEvent var1) {
      this.recoveredField76.dispose();
      System.exit(0);
   }
}
