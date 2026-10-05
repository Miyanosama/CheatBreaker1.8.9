package recovered.unidentified;

import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.multipart.AbstractHttpData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.vecmath.Matrix4d;
import junit.swingui.TestSelector;
import net.minecraft.util.ClassInheritanceMultiMap;

public class UnidentifiedClass4851 implements ActionListener {
   public AbstractHttpData field_0002;
   public ClassInheritanceMultiMap field_0004;
   public Matrix4d field_0001;
   public TestSelector field_0003;
   public MessageToMessageEncoder field_0000;

   public UnidentifiedClass4851(TestSelector var1) {
      this.field_0003 = var1;
   }

   public void actionPerformed(ActionEvent var1) {
      this.field_0003.method_22000();
   }
}
