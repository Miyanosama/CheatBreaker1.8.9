package net.minecraft.world.gen.layer;

import java.util.concurrent.Callable;
import net.minecraft.world.biome.BiomeGenBase;

public class GenLayer$1 implements Callable<String> {
   public BiomeGenBase recoveredField158;

   public GenLayer$1(BiomeGenBase var1) {
      this.recoveredField158 = var1;
   }

   public String call() {
      return String.valueOf(this.recoveredField158);
   }
}
