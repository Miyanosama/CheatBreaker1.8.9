package net.minecraft.world.storage;

import java.util.concurrent.Callable;

public class WorldInfo$1 implements Callable<String> {
   public String call() {
      return String.valueOf(this.field_85143_a.getSeed());
   }

   public WorldInfo$1(WorldInfo var1) {
      this.field_85143_a = var1;
      super();
   }
}
