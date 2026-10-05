package net.minecraft.world.storage;

import java.util.concurrent.Callable;
import net.minecraft.client.stream.IngestServerTester$2;
import net.minecraft.command.CommandExecuteAt$1;

public class WorldInfo$2 implements Callable<String> {
   public IngestServerTester$2 field_0001;
   public CommandExecuteAt$1 field_0000;

   public String call() {
      return String.format(
         "ID %02d - %s, ver %d. Features enabled: %b",
         WorldInfo.access$000(this.field_85139_a).getWorldTypeID(),
         WorldInfo.access$000(this.field_85139_a).getWorldTypeName(),
         WorldInfo.access$000(this.field_85139_a).getGeneratorVersion(),
         WorldInfo.access$100(this.field_85139_a)
      );
   }

   public WorldInfo$2(WorldInfo var1) {
      this.field_85139_a = var1;
      super();
   }
}
