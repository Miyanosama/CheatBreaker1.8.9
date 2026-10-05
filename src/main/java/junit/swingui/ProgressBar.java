package junit.swingui;

import java.awt.Color;
import javax.swing.JProgressBar;

public class ProgressBar extends JProgressBar {
   public boolean fError = false;

   public void updateBarColor() {
      this.setForeground(this.getStatusColor());
   }

   public void step(int var1, boolean var2) {
      this.setValue(var1);
      if (!this.fError && !var2) {
         this.fError = true;
         this.updateBarColor();
      }
   }

   public Color getStatusColor() {
      return this.fError ? Color.red : Color.green;
   }

   public ProgressBar() {
      this.setForeground(this.getStatusColor());
   }

   public void start(int var1) {
      this.setMaximum(var1);
      this.reset();
   }

   public void reset() {
      this.fError = false;
      this.updateBarColor();
      this.setValue(0);
   }
}
