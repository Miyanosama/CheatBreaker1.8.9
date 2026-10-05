package net.minecraft.world.storage;

import io.netty.util.concurrent.GlobalEventExecutor$1;
import java.util.concurrent.Callable;

public class WorldInfo$9 implements Callable<String> {
   public GlobalEventExecutor$1 field_0000;

   public WorldInfo$9(WorldInfo var1) {
      this.field_85109_a = var1;
      super();
   }

   public String call() {
      return String.format(
         "Game mode: %s (ID %d). Hardcore: %b. Cheats: %b",
         WorldInfo.access$1400(this.field_85109_a).getName(),
         WorldInfo.access$1400(this.field_85109_a).getID(),
         WorldInfo.access$1500(this.field_85109_a),
         WorldInfo.access$1600(this.field_85109_a)
      );
   }
}
