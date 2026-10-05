package io.netty.util.concurrent;

import io.netty.handler.codec.compression.ZlibUtil;
import java.util.Iterator;
import net.minecraft.block.BlockRailPowered$2;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.network.play.client.C14PacketTabComplete;

public class SingleThreadEventExecutor$PurgeTask implements Runnable {
   public C14PacketTabComplete __junk4407284468977000811;
   public ZlibUtil __junk3507475565585067284;
   public BlockRailPowered$2 __junk588873741368217418;
   public EntitySnowShovelFX __junk8605935590171633345;

   @Override
   public void run() {
      Iterator var1 = this.this$0.delayedTaskQueue.iterator();

      while (var1.hasNext()) {
         ScheduledFutureTask var2 = (ScheduledFutureTask)var1.next();
         if (var2.isCancelled()) {
            var1.remove();
         }
      }
   }

   public SingleThreadEventExecutor$PurgeTask(SingleThreadEventExecutor var1) {
      this.this$0 = var1;
      super();
   }
}
