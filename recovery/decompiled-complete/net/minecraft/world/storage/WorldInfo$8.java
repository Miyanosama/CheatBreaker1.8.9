package net.minecraft.world.storage;

import java.util.concurrent.Callable;

public class WorldInfo$8 implements Callable<String> {
   public WorldInfo$8(WorldInfo var1) {
      this.field_85111_a = var1;
      super();
   }

   public String call() {
      return String.format(
         "Rain time: %d (now: %b), thunder time: %d (now: %b)",
         WorldInfo.access$1000(this.field_85111_a),
         WorldInfo.access$1100(this.field_85111_a),
         WorldInfo.access$1200(this.field_85111_a),
         WorldInfo.access$1300(this.field_85111_a)
      );
   }
}
