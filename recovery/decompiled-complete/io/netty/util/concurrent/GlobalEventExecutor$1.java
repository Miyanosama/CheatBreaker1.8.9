package io.netty.util.concurrent;

import io.netty.util.internal.RecyclableArrayList;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import recovered.unidentified.UnidentifiedClass4396;

public class GlobalEventExecutor$1 implements Runnable {
   public UnidentifiedClass4396 __junk4389869509072802132;
   public RecyclableArrayList __junk1256565693333672941;
   public BiomeGenBase$SpawnListEntry __junk3886918400997364076;

   public GlobalEventExecutor$1(GlobalEventExecutor var1, ScheduledFutureTask var2) {
      this.this$0 = var1;
      this.val$task = var2;
      super();
   }

   @Override
   public void run() {
      this.this$0.delayedTaskQueue.add(this.val$task);
   }
}
