package recovered.unidentified;

import io.netty.channel.EventLoopException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.swingui.TestRunner;
import net.minecraft.item.ItemMapBase;
import org.apache.log4j.chainsaw.LoggingReceiver;

public class UnidentifiedClass1227 implements ActionListener {
   public ItemMapBase field_0001;
   public TestRunner field_0003;
   public LoggingReceiver field_0000;
   public EventLoopException field_0002;

   public void actionPerformed(ActionEvent var1) {
      this.field_0003.method_00098();
   }

   public UnidentifiedClass1227(TestRunner var1) {
      this.field_0003 = var1;
   }
}
