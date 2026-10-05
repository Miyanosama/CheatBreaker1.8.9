package net.minecraft.world.storage;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker$1;
import java.util.concurrent.Callable;

public class WorldInfo$5 implements Callable<String> {
   public WebSocketClientHandshaker$1 field_0001;

   public String call() {
      return String.format("%d game time, %d day time", WorldInfo.access$600(this.field_85137_a), WorldInfo.access$700(this.field_85137_a));
   }

   public WorldInfo$5(WorldInfo var1) {
      this.field_85137_a = var1;
      super();
   }
}
