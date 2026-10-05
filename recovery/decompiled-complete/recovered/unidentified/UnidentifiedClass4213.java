package recovered.unidentified;

import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import junit.swingui.TestRunner;
import net.minecraft.world.storage.SaveFormatComparator;

public class UnidentifiedClass4213 implements Runnable {
   public String field_0001;
   public TestRunner field_0003;
   public GradientTextButton field_0000;
   public SaveFormatComparator field_0002;

   public void run() {
      TestRunner.method_00100(this.field_0003, this.field_0001);
   }

   public UnidentifiedClass4213(TestRunner var1, String var2) {
      this.field_0003 = var1;
      this.field_0001 = var2;
   }
}
