package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.world.biome.BiomeGenBase;

public class GenLayer$2 implements Callable<String> {
   public BiomeGenBase recoveredField3367;

   public String call() {
      return String.valueOf(this.recoveredField3367);
   }

   public GenLayer$2(BiomeGenBase var1) {
      this.recoveredField3367 = var1;
   }
}
