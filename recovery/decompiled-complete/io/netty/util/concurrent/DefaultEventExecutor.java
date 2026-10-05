package io.netty.util.concurrent;

import java.util.concurrent.ThreadFactory;
import net.minecraft.util.IChatComponent$Serializer;
import net.optifine.override.PlayerControllerOF;

public class DefaultEventExecutor extends SingleThreadEventExecutor {
   public IChatComponent$Serializer __junk3834265482794705884;
   public PlayerControllerOF __junk7341367999388368011;

   public DefaultEventExecutor(DefaultEventExecutorGroup var1, ThreadFactory var2) {
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
