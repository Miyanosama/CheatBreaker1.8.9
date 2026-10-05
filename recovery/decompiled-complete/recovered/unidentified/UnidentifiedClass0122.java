package recovered.unidentified;

import com.cheatbreaker.client.module.type.NickHiderModule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.awtui.TestRunner;
import net.minecraft.client.multiplayer.WorldClient;
import net.optifine.gui.GuiOptionSliderOF;

public class UnidentifiedClass0122 implements ActionListener {
   public NickHiderModule field_0002;
   public GuiOptionSliderOF field_0004;
   public WorldClient field_0001;
   public UnidentifiedClass0433 field_0003;
   public TestRunner field_0000;

   public void actionPerformed(ActionEvent var1) {
      System.exit(0);
   }

   public UnidentifiedClass0122(TestRunner var1) {
      this.field_0000 = var1;
   }
}
