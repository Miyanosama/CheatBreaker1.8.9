package javax.vecmath;

import com.cheatbreaker.client.module.type.CPSModule;
import java.io.Serializable;
import net.minecraft.client.gui.GuiListExtended;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$LogBrokerMonitorWindowAdaptor;

public class TexCoord2f extends Tuple2f implements Serializable {
   public GuiListExtended field_0001;
   public CPSModule field_0003;
   public static long field_0000;
   public LogBrokerMonitor$LogBrokerMonitorWindowAdaptor field_0002;

   public TexCoord2f(TexCoord2f var1) {
      super(var1);
   }

   public TexCoord2f(float[] var1) {
      super(var1);
   }

   public TexCoord2f() {
   }

   public TexCoord2f(float var1, float var2) {
      super(var1, var2);
   }

   public TexCoord2f(Tuple2f var1) {
      super(var1);
   }
}
