package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.channel.local.LocalServerChannel;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.client.particle.EntityFlameFX$Factory;
import net.minecraft.network.PacketBuffer;

public class UnidentifiedClass3405 extends WSPacket {
   public EntityFlameFX$Factory field_0003;
   public BlockRailPowered field_0001;
   public String field_0002;
   public LocalServerChannel field_0000;

   @Override
   public void read(PacketBuffer var1) {
      this.field_0002 = var1.readStringFromBuffer(32767);
   }

   public UnidentifiedClass3405(String var1) {
      this.field_0002 = var1;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10128(this);
   }

   public UnidentifiedClass3405() {
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.field_0002);
   }

   public String method_21111() {
      return this.field_0002;
   }
}
