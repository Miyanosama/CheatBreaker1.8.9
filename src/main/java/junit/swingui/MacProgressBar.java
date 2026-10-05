package junit.swingui;

import javax.swing.JTextField;
import junit.swingui.ProgressBar;

public class MacProgressBar extends ProgressBar {
   public JTextField recoveredField3943;

   public void updateBarColor() {
      this.recoveredField3943.setBackground(this.getStatusColor());
   }

   public MacProgressBar(JTextField var1) {
      this.recoveredField3943 = var1;
   }
}
