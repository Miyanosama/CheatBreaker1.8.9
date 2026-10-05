package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import com.cheatbreaker.client.websocket.client.WSPacketClientCrashReport;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.gen.layer.GenLayerEdge;

public class UnidentifiedClass1774 extends WSPacket {
   public WSPacketClientCrashReport field_0001;
   public GenLayerEdge field_0000;

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10122(this);
   }

   @Override
   public void write(PacketBuffer var1) {
   }
}
