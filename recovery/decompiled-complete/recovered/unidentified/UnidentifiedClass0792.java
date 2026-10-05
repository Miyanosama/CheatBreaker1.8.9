package recovered.unidentified;

import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.List;
import net.minecraft.client.stream.ChatController;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.world.chunk.Chunk$2;

public class UnidentifiedClass0792 extends WSPacket {
   public List<ClientResourceManager> field_0004;
   public PositionImpl field_0002;
   public ChatController field_0003;
   public Chunk$2 field_0000;
   public PacketThreadUtil field_0001;

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public UnidentifiedClass0792() {
   }

   public List<ClientResourceManager> method_05446() {
      return this.field_0004;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeInt(this.field_0004.size());

      for (ClientResourceManager var3 : this.field_0004) {
         var1.writeLong(var3.method_20860());
         var1.writeBoolean(var3.method_20849());
         var1.writeString(var3.method_20858());
         var1.writeString(var3.method_20848().method_00485());
         var1.writeFloat(var3.method_20846());
         var1.writeString(var3.method_20859().toString().replaceFirst("minecraft:", ""));
      }
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   public UnidentifiedClass0792(List<ClientResourceManager> var1) {
      this.field_0004 = var1;
   }
}
