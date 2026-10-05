package io.netty.util.concurrent;

import java.util.concurrent.Executor;
import net.minecraft.client.model.ModelDragon;

public class ImmediateExecutor implements Executor {
   public static ImmediateExecutor INSTANCE = new ImmediateExecutor();
   public ModelDragon __junk527951779294578683;

   @Override
   public void execute(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("command");
      } else {
         var1.run();
      }
   }
}
