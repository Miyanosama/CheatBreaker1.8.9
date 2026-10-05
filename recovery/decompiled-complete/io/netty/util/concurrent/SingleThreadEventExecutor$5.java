package io.netty.util.concurrent;

import io.netty.handler.codec.rtsp.RtspVersions;
import net.minecraft.block.BlockBanner$BlockBannerStanding;
import net.minecraft.client.main.llIlllIIlllIIllIIlllIlIII;

public class SingleThreadEventExecutor$5 implements Runnable {
   public RtspVersions __junk7806339110806235945;
   public llIlllIIlllIIllIIlllIlIII __junk7590599102652842481;
   public BlockBanner$BlockBannerStanding __junk5580873586601220503;

   public SingleThreadEventExecutor$5(SingleThreadEventExecutor var1, ScheduledFutureTask var2) {
      this.this$0 = var1;
      this.val$task = var2;
      super();
   }

   @Override
   public void run() {
      this.this$0.delayedTaskQueue.add(this.val$task);
   }
}
