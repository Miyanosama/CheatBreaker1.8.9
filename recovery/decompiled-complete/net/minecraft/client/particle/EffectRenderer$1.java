package net.minecraft.client.particle;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachMappingTask;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport$7;

public class EffectRenderer$1 implements Callable<String> {
   public CrashReport$7 field_0001;
   public ConcurrentHashMapV8$ForEachMappingTask field_0002;

   public String call() {
      return this.val$particle.toString();
   }

   public EffectRenderer$1(EffectRenderer var1, EntityFX var2) {
      this.this$0 = var1;
      this.val$particle = var2;
      super();
   }
}
