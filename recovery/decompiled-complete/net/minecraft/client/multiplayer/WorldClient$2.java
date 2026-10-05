package net.minecraft.client.multiplayer;

import io.netty.channel.nio.AbstractNioChannel;
import io.netty.util.internal.chmv8.ForkJoinWorkerThread;
import java.util.concurrent.Callable;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import recovered.unidentified.UnidentifiedClass4330;

public class WorldClient$2 implements Callable<String> {
   public ForkJoinWorkerThread field_0002;
   public UnidentifiedClass4330 field_0004;
   public AbstractNioChannel field_0001;
   public EntityAIFindEntityNearest field_0000;

   public WorldClient$2(WorldClient var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return WorldClient.access$100(this.this$0).size() + " total; " + WorldClient.access$100(this.this$0).toString();
   }
}
