package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.handler.codec.MessageToMessageEncoder;
import net.minecraft.block.BlockGlowstone;

public class UnidentifiedClass0877 extends EventBus$Event {
   public BlockGlowstone field_0002;
   public MessageToMessageEncoder field_0001;
   public int field_0000;

   public int method_05882() {
      return this.field_0000;
   }

   public UnidentifiedClass0877(int var1) {
      this.field_0000 = var1;
   }
}
