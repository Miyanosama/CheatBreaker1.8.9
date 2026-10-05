package recovered.unidentified;

import io.netty.util.Recycler$2;
import javax.swing.JButton;
import junit.swingui.TestRunner;
import net.optifine.shaders.Programs;

public class UnidentifiedClass0631 implements Runnable {
   public TestRunner field_0002;
   public String field_0004;
   public Programs field_0001;
   public JButton field_0003;
   public Recycler$2 field_0000;

   public void run() {
      this.field_0003.setText(this.field_0004);
   }

   public UnidentifiedClass0631(TestRunner var1, JButton var2, String var3) {
      this.field_0002 = var1;
      this.field_0003 = var2;
      this.field_0004 = var3;
   }
}
