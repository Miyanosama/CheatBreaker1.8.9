package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import java.util.UUID;
import net.optifine.shaders.config.ShaderMacros;

public class UnidentifiedClass1748 extends Packet {
   public UUID field_0000;
   public ShaderMacros field_0001;

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11448(this);
   }

   public UUID method_12150() {
      return this.field_0000;
   }

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0000 = var1.readUUID();
   }

   public UnidentifiedClass1748(UUID var1) {
      this.field_0000 = var1;
   }

   public UnidentifiedClass1748() {
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.field_0000);
   }
}
