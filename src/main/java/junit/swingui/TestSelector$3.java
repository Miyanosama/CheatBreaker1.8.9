package junit.swingui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestSelector$3 implements ListSelectionListener {
   public TestSelector recoveredField2503;

   public void valueChanged(ListSelectionEvent var1) {
      this.recoveredField2503.checkEnableOK(var1);
   }

   public TestSelector$3(TestSelector var1) {
      this.recoveredField2503 = var1;
   }
}
