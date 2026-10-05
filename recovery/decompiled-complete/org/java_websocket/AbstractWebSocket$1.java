package org.java_websocket;

import com.cheatbreaker.client.ui.overlay.element.RadioStationElement;
import io.netty.handler.codec.http.LastHttpContent$1;
import java.util.ArrayList;
import net.minecraft.client.audio.SoundCategory;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$24;

public class AbstractWebSocket$1 implements Runnable {
   public ArrayList<WebSocket> connections;
   public RadioStationElement field_0005;
   public LogBrokerMonitor$24 field_0004;
   public SoundCategory field_0000;
   public LastHttpContent$1 field_0001;

   @Override
   public void run() {
      this.connections.clear();

      try {
         this.connections.addAll(this.this$0.getConnections());
         long var1 = (long)(System.nanoTime() - AbstractWebSocket.access$100(this.this$0) * 1.5);

         for (WebSocket var4 : this.connections) {
            AbstractWebSocket.access$200(this.this$0, var4, var1);
         }
      } catch (Exception var5) {
      }

      this.connections.clear();
   }

   public AbstractWebSocket$1(AbstractWebSocket var1) {
      this.this$0 = var1;
      super();
      this.connections = new ArrayList<>();
   }
}
