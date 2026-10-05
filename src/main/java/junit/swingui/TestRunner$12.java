package junit.swingui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestRunner$12 implements ItemListener {
   // $VF: synthetic field
   public TestRunner this$0;

   public void itemStateChanged(ItemEvent var1) {
      if (var1.getStateChange() == 1) {
         this.this$0.textChanged();
      }
   }

   public TestRunner$12(TestRunner var1) {
      this.this$0 = var1;
   }
}
