package junit.awtui;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.timeout.WriteTimeoutHandler$1;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import org.apache.log4j.chainsaw.ExitAction;

public class TestRunner$7 implements ItemListener {
   public WriteTimeoutHandler$1 field_0002;
   public WebSocketServerProtocolHandler field_0004;
   public TestRunner field_0001;
   public S26PacketMapChunkBulk field_0003;
   public ExitAction field_0000;

   public TestRunner$7(TestRunner var1) {
      this.field_0001 = var1;
   }

   public void itemStateChanged(ItemEvent var1) {
      this.field_0001.method_28042();
   }
}
