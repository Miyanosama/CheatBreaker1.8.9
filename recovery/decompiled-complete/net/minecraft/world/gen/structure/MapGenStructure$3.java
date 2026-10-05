package net.minecraft.world.gen.structure;

import io.netty.buffer.PoolThreadCache$1;
import java.util.concurrent.Callable;

public class MapGenStructure$3 implements Callable<String> {
   public PoolThreadCache$1 field_0000;

   public MapGenStructure$3(MapGenStructure var1) {
      this.field_0001 = var1;
      super();
   }

   public String method_29685() {
      return this.field_0001.getClass().getCanonicalName();
   }
}
