package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.UUID;
import net.minecraft.network.PacketBuffer;

public class UnidentifiedClass1954 extends WSPacket {
   public int field_0001;
   public UUID field_0000;

   public UnidentifiedClass1954() {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0000 = var1.readUuid();
      this.field_0001 = var1.readInt();
   }

   public UUID method_13269() {
      return this.field_0000;
   }

   public UnidentifiedClass1954(UUID var1, int var2) {
      this.field_0000 = var1;
      this.field_0001 = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeUuid(this.field_0000);
      var1.writeInt(this.field_0001);
   }

   public int method_13270() {
      return this.field_0001;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10124(this);
   }
}
