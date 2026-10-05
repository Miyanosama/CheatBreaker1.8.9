package junit.swingui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestRunner$11 extends KeyAdapter {
   // $VF: synthetic field
   public TestRunner this$0;

   public TestRunner$11(TestRunner var1) {
      this.this$0 = var1;
   }

   public void keyTyped(KeyEvent var1) {
      this.this$0.textChanged();
      if (var1.getKeyChar() == '\n') {
         this.this$0.runSuite();
      }
   }
}
