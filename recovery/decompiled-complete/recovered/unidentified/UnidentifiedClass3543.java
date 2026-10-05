package recovered.unidentified;

import io.netty.handler.ssl.util.BouncyCastleSelfSignedCertGenerator;
import io.netty.util.concurrent.ImmediateEventExecutor$ImmediatePromise;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import junit.awtui.TestRunner;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.optifine.http.FileUploadThread;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$15;

public class UnidentifiedClass3543 implements ActionListener {
   public EntityAIMoveIndoors field_0003;
   public FileUploadThread field_0006;
   public ImmediateEventExecutor$ImmediatePromise field_0002;
   public S1EPacketRemoveEntityEffect field_0005;
   public BouncyCastleSelfSignedCertGenerator field_0000;
   public TestRunner field_0001;
   public LogBrokerMonitor$15 field_0007;
   public GuiDisconnected field_0004;

   public void actionPerformed(ActionEvent var1) {
      this.field_0001.rerun();
   }

   public UnidentifiedClass3543(TestRunner var1) {
      this.field_0001 = var1;
   }
}
