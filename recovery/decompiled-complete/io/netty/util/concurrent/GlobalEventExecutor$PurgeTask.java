package io.netty.util.concurrent;

import io.netty.buffer.EmptyByteBuf;
import java.util.Iterator;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumType;

public class GlobalEventExecutor$PurgeTask implements Runnable {
   public EmptyByteBuf __junk975589675108541859;
   public VertexFormatElement$EnumType __junk2456931410081259273;

   public GlobalEventExecutor$PurgeTask(GlobalEventExecutor var1) {
      this.this$0 = var1;
      super();
   }

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
}
