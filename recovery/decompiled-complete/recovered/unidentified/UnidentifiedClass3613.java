package recovered.unidentified;

import com.cheatbreaker.client.util.hologram.Hologram;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.swingui.TestSelector;
import net.minecraft.server.management.PreYggdrasilConverter$5;

public class UnidentifiedClass3613 implements ActionListener {
   public TestSelector field_0001;
   public UnidentifiedClass1388 field_0003;
   public PreYggdrasilConverter$5 field_0000;
   public Hologram field_0002;

   public UnidentifiedClass3613(TestSelector var1) {
      this.field_0001 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.field_0001.dispose();
   }
}
