package net.minecraft.client.particle;

import io.netty.util.HashedWheelTimer;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ValuesView;
import java.util.concurrent.Callable;
import net.minecraft.block.BlockSnow;

public class EffectRenderer$3 implements Callable<String> {
   public BlockSnow field_0001;
   public HashedWheelTimer field_0003;
   public ConcurrentHashMapV8$ValuesView field_0000;

   public EffectRenderer$3(EffectRenderer var1, EntityFX var2) {
      this.field_0002 = var1;
      this.field_0004 = var2;
      super();
   }

   public String method_12611() {
      return this.field_0004.toString();
   }
}
