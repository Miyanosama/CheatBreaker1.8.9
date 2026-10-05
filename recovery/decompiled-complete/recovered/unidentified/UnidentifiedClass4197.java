package recovered.unidentified;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.swingui.TestRunner;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$NetherStalkRoom;

public class UnidentifiedClass4197 implements ActionListener {
   public TestRunner field_0001;
   public StructureNetherBridgePieces$NetherStalkRoom field_0002;
   public S01PacketJoinGame field_0000;

   public void actionPerformed(ActionEvent var1) {
      this.field_0001.runSuite();
   }

   public UnidentifiedClass4197(TestRunner var1) {
      this.field_0001 = var1;
   }
}
