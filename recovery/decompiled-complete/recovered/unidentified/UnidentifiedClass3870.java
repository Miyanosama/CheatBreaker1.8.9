package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.handler.codec.serialization.CachingClassResolver;
import net.minecraft.network.PacketBuffer;
import org.apache.log4j.LogManager;

public class UnidentifiedClass3870 extends WSPacket {
   public double field_0003;
   public LogManager field_0001;
   public CachingClassResolver field_0002;
   public int field_0000;

   @Override
   public void read(PacketBuffer var1) {
      this.field_0000 = var1.readInt();
      this.field_0003 = var1.readDouble();
   }

   public UnidentifiedClass3870(int var1, double var2) {
      this.field_0000 = var1;
      this.field_0003 = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeInt(this.field_0000);
      var1.writeDouble(this.field_0003);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public UnidentifiedClass3870() {
   }

   public int method_23438() {
      return this.field_0000;
   }

   public double method_23439() {
      return this.field_0003;
   }
}
