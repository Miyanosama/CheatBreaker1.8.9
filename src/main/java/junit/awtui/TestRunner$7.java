package junit.awtui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestRunner$7 implements ItemListener {
   public TestRunner recoveredField2284;

   public TestRunner$7(TestRunner var1) {
      this.recoveredField2284 = var1;
   }

   public void itemStateChanged(ItemEvent var1) {
      this.recoveredField2284.failureSelected();
   }
}
