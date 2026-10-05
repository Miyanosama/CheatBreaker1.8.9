package recovered.unidentified;

import com.cheatbreaker.client.event.EventBus$Event;
import io.netty.channel.FailedChannelFuture;
import net.minecraft.block.BlockReed;

public class UnidentifiedClass1472 extends EventBus$Event {
   public FailedChannelFuture field_0002;
   public boolean field_0001 = false;
   public BlockReed field_0000;

   public boolean method_10232() {
      return this.field_0001;
   }

   public void method_10233(boolean var1) {
      this.field_0001 = var1;
   }
}
