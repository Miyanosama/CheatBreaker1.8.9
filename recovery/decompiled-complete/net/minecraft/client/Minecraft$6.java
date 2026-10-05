package net.minecraft.client;

import java.util.concurrent.Callable;

public class Minecraft$6 implements Callable<String> {
   public String call() {
      return this.field_151425_a.mcProfiler.profilingEnabled ? this.field_151425_a.mcProfiler.getNameOfLastSection() : "N/A (disabled)";
   }

   public Minecraft$6(Minecraft var1) {
      this.field_151425_a = var1;
      super();
   }
}
