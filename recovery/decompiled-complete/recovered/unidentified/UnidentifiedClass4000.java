package recovered.unidentified;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.vecmath.Vector3f;
import junit.awtui.TestRunner;
import net.minecraft.network.play.server.S12PacketEntityVelocity;

public class UnidentifiedClass4000 implements ActionListener {
   public S12PacketEntityVelocity field_0001;
   public TestRunner field_0003;
   public Vector3f field_0000;
   public UnidentifiedEnum1205 field_0002;

   public UnidentifiedClass4000(TestRunner var1) {
      this.field_0003 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      System.exit(0);
   }
}
