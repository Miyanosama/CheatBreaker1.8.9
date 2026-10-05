package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.channel.udt.nio.NioUdtByteConnectorChannel;
import net.minecraft.client.audio.SoundHandler$1;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class UnidentifiedClass0842 extends WSPacket {
   public String field_0004;
   public String field_0002;
   public WorldGenSavannaTree field_0003;
   public SoundHandler$1 field_0000;
   public NioUdtByteConnectorChannel field_0001;

   public UnidentifiedClass0842(String var1, String var2) {
      this.field_0004 = var1;
      this.field_0002 = var2;
   }

   public String method_05710() {
      return this.field_0004;
   }

   public String method_05711() {
      return this.field_0002;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0004 = var1.readStringFromBuffer(128);
      this.field_0002 = var1.readStringFromBuffer(512);
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.field_0004);
      var1.writeString(this.field_0002);
   }

   public UnidentifiedClass0842() {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10117(this);
   }
}
