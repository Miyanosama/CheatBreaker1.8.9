package junit.swingui;

import io.netty.channel.AbstractChannelHandlerContext$13;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import net.minecraft.network.play.server.S44PacketWorldBorder$1;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$XYDoubleRoomFitHelper;

public class TestRunner$12 implements ItemListener {
   public AbstractChannelHandlerContext$13 field_0001;
   public TestRunner this$0;
   public S44PacketWorldBorder$1 field_0000;
   public StructureOceanMonumentPieces$XYDoubleRoomFitHelper field_0002;

   public void itemStateChanged(ItemEvent var1) {
      if (var1.getStateChange() == 1) {
         this.this$0.textChanged();
      }
   }

   public TestRunner$12(TestRunner var1) {
      this.this$0 = var1;
   }
}
