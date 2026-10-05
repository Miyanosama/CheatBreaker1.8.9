package recovered.unidentified;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import junit.swingui.TestSelector;
import net.minecraft.block.state.BlockState$2;

public class UnidentifiedClass3786 extends WindowAdapter {
   public BlockState$2 field_0000;
   public TestSelector field_0001;

   public void windowClosing(WindowEvent var1) {
      this.field_0001.dispose();
   }

   public UnidentifiedClass3786(TestSelector var1) {
      this.field_0001 = var1;
   }
}
