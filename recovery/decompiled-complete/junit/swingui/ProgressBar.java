package junit.swingui;

import java.awt.Color;
import javax.swing.JProgressBar;
import net.minecraft.crash.CrashReport$1;
import net.minecraft.entity.monster.EntityEnderman$AITakeBlock;
import net.minecraft.util.HttpUtil;

public class ProgressBar extends JProgressBar {
   public boolean fError = false;
   public HttpUtil field_0003;
   public EntityEnderman$AITakeBlock field_0002;
   public CrashReport$1 field_0001;

   public void method_04438() {
      this.setForeground(this.getStatusColor());
   }

   public void step(int var1, boolean var2) {
      this.setValue(var1);
      if (!this.fError && !var2) {
         this.fError = true;
         this.method_04438();
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
      this.method_29010();
   }

   public void method_29010() {
      this.fError = false;
      this.method_04438();
      this.setValue(0);
   }
}
