package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.util.concurrent.GlobalEventExecutor$TaskRunner;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.biome.BiomeGenForest$1;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import org.apache.log4j.spi.RootCategory;

public class UnidentifiedClass1858 extends WSPacket {
   public GlobalEventExecutor$TaskRunner field_0006;
   public BiomeGenForest$1 field_0003;
   public RootCategory field_0005;
   public UnidentifiedEnum3640 field_0000;
   public String field_0001;
   public String field_0007;
   public WorldGenTallGrass field_0004;
   public ItemSimpleFoiled field_0002;

   public String method_12690() {
      return this.field_0007;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0001 = var1.readStringFromBuffer(52);
      this.field_0007 = var1.readStringFromBuffer(1024);
   }

   public UnidentifiedClass1858() {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10123(this);
   }

   public String method_12691() {
      return this.field_0001;
   }

   public UnidentifiedClass1858(String var1, String var2) {
      this.field_0001 = var1;
      this.field_0007 = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.field_0001);
      var1.writeString(this.field_0007);
   }
}
