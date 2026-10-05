package junit.swingui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestRunner$7 extends WindowAdapter {
   public TestRunner recoveredField3650;

   public TestRunner$7(TestRunner var1) {
      this.recoveredField3650 = var1;
   }

   public void windowClosing(WindowEvent var1) {
      this.recoveredField3650.terminate();
   }
}
