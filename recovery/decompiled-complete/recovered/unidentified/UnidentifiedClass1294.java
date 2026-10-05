package recovered.unidentified;

import io.netty.channel.AbstractChannelHandlerContext$5;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.swingui.TestRunner;
import net.minecraft.block.BlockPressurePlateWeighted;
import org.apache.log4j.helpers.AppenderAttachableImpl;

public class UnidentifiedClass1294 implements ActionListener {
   public AppenderAttachableImpl field_0001;
   public AbstractChannelHandlerContext$5 field_0003;
   public TestRunner field_0000;
   public BlockPressurePlateWeighted field_0002;

   public UnidentifiedClass1294(TestRunner var1) {
      this.field_0000 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      TestRunner.method_00064(this.field_0000);
   }
}
