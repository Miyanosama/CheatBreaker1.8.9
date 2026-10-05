package recovered.unidentified;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.minecraft.block.BlockStoneBrick;

public class UnidentifiedClass5128 extends WindowAdapter {
   public BlockStoneBrick field_0000;
   public UnidentifiedClass3416 field_0001;

   public void windowClosing(WindowEvent var1) {
      this.field_0001.dispose();
   }

   public UnidentifiedClass5128(UnidentifiedClass3416 var1) {
      this.field_0001 = var1;
   }
}
