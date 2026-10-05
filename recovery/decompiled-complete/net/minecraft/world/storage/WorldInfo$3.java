package net.minecraft.world.storage;

import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder;
import java.util.concurrent.Callable;

public class WorldInfo$3 implements Callable<String> {
   public WebSocket08FrameDecoder field_0001;

   public WorldInfo$3(WorldInfo var1) {
      this.field_85141_a = var1;
      super();
   }

   public String call() {
      return WorldInfo.access$200(this.field_85141_a);
   }
}
