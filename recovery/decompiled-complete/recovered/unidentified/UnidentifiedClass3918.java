package recovered.unidentified;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import javax.vecmath.Point4f;
import javazoom.jl.decoder.SynthesisFilter;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.integrated.IntegratedServerCommandManager;

public class UnidentifiedClass3918 extends WSPacket {
   public byte[] field_0003;
   public SynthesisFilter field_0001;
   public IntegratedServerCommandManager field_0002;
   public Point4f field_0000;

   public UnidentifiedClass3918() {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.field_0003 = this.readKey(var1);
   }

   public UnidentifiedClass3918(byte[] var1) {
      this.field_0003 = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      this.writeKey(var1, this.field_0003);
   }

   public byte[] method_23630() {
      return this.field_0003;
   }
}
