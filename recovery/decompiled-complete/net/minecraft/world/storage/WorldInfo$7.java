package net.minecraft.world.storage;

import io.netty.handler.codec.http.HttpObjectDecoder$HeaderParser;
import java.util.concurrent.Callable;
import org.apache.log4j.helpers.FormattingInfo;

public class WorldInfo$7 implements Callable<String> {
   public FormattingInfo field_0001;
   public HttpObjectDecoder$HeaderParser field_0000;

   public String call() {
      String var1 = "Unknown?";

      try {
         switch (WorldInfo.access$900(this.field_85113_a)) {
            case 19132:
               var1 = "McRegion";
               break;
            case 19133:
               var1 = "Anvil";
         }
      } catch (Throwable var3) {
      }

      return String.format("0x%05X - %s", WorldInfo.access$900(this.field_85113_a), var1);
   }

   public WorldInfo$7(WorldInfo var1) {
      this.field_85113_a = var1;
      super();
   }
}
