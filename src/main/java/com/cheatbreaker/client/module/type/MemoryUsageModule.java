package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.IntegerRangeDefaults;

public class MemoryUsageModule extends NumberHudModule {
   @Override
   public String method_00167() {
      long var1 = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100L / Runtime.getRuntime().maxMemory();
      return this.method_09815(this.recoveredField2752, (int)var1, this.recoveredField2753.method_08912()) ? null : var1 + "%";
   }

   @Override
   public String method_21178() {
      return "%LABEL%: %VALUE%";
   }

   @Override
   public IntegerRangeDefaults method_00168() {
      return new IntegerRangeDefaults(0, 50, 100);
   }

   @Override
   public String method_00166() {
      return "Mem";
   }

   public MemoryUsageModule() {
      super("Memory Usage", "[Mem: 19%]");
      this.method_28821("Displays how much memory the game is using.");
   }

   @Override
   public String method_00164() {
      return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) * 100L / Runtime.getRuntime().maxMemory() + "%";
   }
}
