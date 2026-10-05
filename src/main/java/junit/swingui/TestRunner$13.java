package junit.swingui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestRunner$13 implements ChangeListener {
   // $VF: synthetic field
   public TestRunner this$0;

   public TestRunner$13(TestRunner var1) {
      this.this$0 = var1;
   }

   public void stateChanged(ChangeEvent var1) {
      this.this$0.testViewChanged();
   }
}
