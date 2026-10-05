package junit.swingui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import recovered.unidentified.UnidentifiedClass0440;
import recovered.unidentified.UnidentifiedClass4596;

public class TestRunner$7 extends WindowAdapter {
   public UnidentifiedClass0440 field_0001;
   public S3BPacketScoreboardObjective field_0003;
   public UnidentifiedClass4596 field_0000;
   public TestRunner field_0002;

   public TestRunner$7(TestRunner var1) {
      this.field_0002 = var1;
   }

   public void windowClosing(WindowEvent var1) {
      this.field_0002.method_00098();
   }
}
