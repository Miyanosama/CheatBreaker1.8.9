package recovered.unidentified;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.swingui.TestRunner;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.item.Item$ToolMaterial;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$32;

public class UnidentifiedClass1945 implements ActionListener {
   public TestRunner field_0003;
   public Item$ToolMaterial field_0005;
   public S38PacketPlayerListItem field_0002;
   public BlockMushroom field_0004;
   public LogBrokerMonitor$32 field_0000;
   public BlockStoneBrick field_0001;

   public void actionPerformed(ActionEvent var1) {
      this.field_0003.method_00098();
   }

   public UnidentifiedClass1945(TestRunner var1) {
      this.field_0003 = var1;
   }
}
