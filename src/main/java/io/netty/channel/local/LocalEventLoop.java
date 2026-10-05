package io.netty.channel.local;

import com.cheatbreaker.client.module.type.PingModule;
import io.netty.channel.SingleThreadEventLoop;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.ai.EntityAIDefendVillage;

public class LocalEventLoop extends SingleThreadEventLoop {

   public LocalEventLoop(LocalEventLoopGroup var1, ThreadFactory var2) {
      super(var1, var2, true);
   }

   @Override
   public void run() {
      do {
         Runnable var1 = this.takeTask();
         if (var1 != null) {
            var1.run();
            this.updateLastExecutionTime();
         }
      } while (!this.confirmShutdown());
   }
}
