package junit.swingui;

import javax.swing.JButton;
import junit.swingui.TestRunner;

public class TestRunner$17 implements Runnable {
   public TestRunner recoveredField90;
   public String recoveredField91;
   public JButton recoveredField92;

   public void run() {
      this.recoveredField92.setText(this.recoveredField91);
   }

   public TestRunner$17(TestRunner var1, JButton var2, String var3) {
      this.recoveredField90 = var1;
      this.recoveredField92 = var2;
      this.recoveredField91 = var3;
   }
}
