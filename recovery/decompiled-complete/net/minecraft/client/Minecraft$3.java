package net.minecraft.client;

import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpMessage;
import java.util.concurrent.Callable;

public class Minecraft$3 implements Callable<String> {
   public HttpObjectAggregator$AggregatedFullHttpMessage field_0001;

   public Minecraft$3(Minecraft var1) {
      this.field_90046_a = var1;
      super();
   }

   public String call() {
      return "Client (map_client.txt)";
   }
}
