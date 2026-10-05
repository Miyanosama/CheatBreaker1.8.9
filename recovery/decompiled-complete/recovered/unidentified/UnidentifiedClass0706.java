package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.client.audio.SoundList;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.network.PacketBuffer;

public class UnidentifiedClass0706 extends WSPacket {
   public EntityHorse field_0002;
   public byte[] field_0000;
   public SoundList field_0001;

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10116(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0000 = this.readKey(var1);
   }

   public UnidentifiedClass0706(byte[] var1) {
      this.field_0000 = var1;
   }

   public UnidentifiedClass0706() {
   }

   public byte[] method_04977() {
      return this.field_0000;
   }

   @Override
   public void write(PacketBuffer var1) {
      this.writeKey(var1, this.field_0000);
   }
}
