package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.handler.traffic.TrafficCounter$TrafficMonitoringTask;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.network.play.server.S02PacketChat;

public class UnidentifiedClass0890 extends EventBus$Event {
   public ItemMultiTexture field_0002;
   public S02PacketChat field_0001;
   public TrafficCounter$TrafficMonitoringTask field_0000;

   public UnidentifiedClass0890(S02PacketChat var1) {
      this.field_0001 = var1;
   }

   public S02PacketChat method_05975() {
      return this.field_0001;
   }
}
