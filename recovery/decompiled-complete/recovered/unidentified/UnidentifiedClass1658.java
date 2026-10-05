package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.nbt.JsonToNBT$Primitive;
import net.minecraft.network.PacketBuffer;

public class UnidentifiedClass1658 extends WSPacket {
   public JsonToNBT$Primitive field_0002;
   public boolean field_0000;
   public String field_0001;

   public UnidentifiedClass1658(boolean var1, String var2) {
      this.field_0000 = var1;
      this.field_0001 = var2;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10120(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0000 = var1.readBoolean();
      this.field_0001 = var1.readStringFromBuffer(52);
   }

   public boolean method_11226() {
      return this.field_0000;
   }

   public UnidentifiedClass1658() {
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeBoolean(this.field_0000);
      var1.writeString(this.field_0001);
   }

   public String method_11227() {
      return this.field_0001;
   }
}
