package io.netty.handler.timeout;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import java.util.concurrent.ScheduledFuture;
import net.minecraft.command.server.CommandAchievement$1;
import net.optifine.entity.model.ModelAdapterVillager;

public class WriteTimeoutHandler$2 implements ChannelFutureListener {
   public CommandAchievement$1 __junk6572633481473603479;
   public ModelAdapterVillager __junk4645498847702493865;

   public WriteTimeoutHandler$2(WriteTimeoutHandler var1, ScheduledFuture var2) {
      this.this$0 = var1;
      this.val$sf = var2;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      this.val$sf.cancel(false);
   }
}
