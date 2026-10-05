package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.network.play.server.S05PacketSpawnPosition;

public class AbstractChannelHandlerContext$5 extends OneTimeTask {
   public S05PacketSpawnPosition __junk3485519126696524064;

   public AbstractChannelHandlerContext$5(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$400(this.val$next);
   }
}
