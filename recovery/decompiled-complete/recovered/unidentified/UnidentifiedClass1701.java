package recovered.unidentified;

import io.netty.channel.ChannelOutboundBuffer;
import java.util.Enumeration;
import junit.framework.Test;
import junit.swingui.TestRunView;
import junit.swingui.TestRunner;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleZRoom;

public class UnidentifiedClass1701 implements Runnable {
   public ChannelOutboundBuffer field_0001;
   public StructureOceanMonumentPieces$DoubleZRoom field_0003;
   public Test field_0000;
   public TestRunner field_0002;

   public void run() {
      Enumeration var1 = TestRunner.access$4(this.field_0002).elements();

      while (var1.hasMoreElements()) {
         TestRunView var2 = (TestRunView)var1.nextElement();
         var2.runFinished(this.field_0000, TestRunner.access$1(this.field_0002));
      }
   }

   public UnidentifiedClass1701(TestRunner var1, Test var2) {
      this.field_0002 = var1;
      this.field_0000 = var2;
   }
}
