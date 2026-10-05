package io.netty.channel.local;

import com.cheatbreaker.client.module.type.PingModule;
import io.netty.channel.SingleThreadEventLoop;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.entity.ai.EntityAIDefendVillage;

public class LocalEventLoop extends SingleThreadEventLoop {
   public EntityAIDefendVillage __junk9097482122124342277;
   public GameSettings$Options __junk5475340828091456838;
   public PingModule __junk3140031768954956072;

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
