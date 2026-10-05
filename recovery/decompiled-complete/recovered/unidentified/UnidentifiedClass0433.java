package recovered.unidentified;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import com.cheatbreaker.client.nethandler.ICBNetHandler;
import com.cheatbreaker.client.nethandler.Packet;
import com.cheatbreaker.client.nethandler.client.ICBNetHandlerClient;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;
import java.util.UUID;
import net.minecraft.util.EntitySelectors$2;
import org.java_websocket.framing.ContinuousFrame;

public class UnidentifiedClass0433 extends Packet {
   public EntitySelectors$2 field_0000;
   public ChannelTrafficShapingHandler field_0001;
   public UUID field_0003;
   public ContinuousFrame field_0002;

   @Override
   public void read(ByteBufWrapper var1) {
      this.field_0003 = var1.readUUID();
   }

   public UnidentifiedClass0433(UUID var1) {
      this.field_0003 = var1;
   }

   public UUID method_03211() {
      return this.field_0003;
   }

   @Override
   public void process(ICBNetHandler var1) {
      ((ICBNetHandlerClient)var1).method_11438(this);
   }

   @Override
   public void write(ByteBufWrapper var1) {
      var1.writeUUID(this.field_0003);
   }

   public UnidentifiedClass0433() {
   }
}
