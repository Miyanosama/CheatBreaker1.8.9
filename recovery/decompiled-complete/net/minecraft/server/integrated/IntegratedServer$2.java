package net.minecraft.server.integrated;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import java.util.concurrent.Callable;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;

public class IntegratedServer$2 implements Callable<String> {
   public WebSocketServerHandshaker field_0000;

   public String call() {
      String var1 = ClientBrandRetriever.getClientModName();
      if (!var1.equals("vanilla")) {
         return "Definitely; Client brand changed to '" + var1 + "'";
      } else {
         var1 = this.this$0.getServerModName();
         return !var1.equals("vanilla")
            ? "Definitely; Server brand changed to '" + var1 + "'"
            : (
               Minecraft.class.getSigners() == null
                  ? "Very likely; Jar signature invalidated"
                  : "Probably not. Jar signature remains and both client + server brands are untouched."
            );
      }
   }

   public IntegratedServer$2(IntegratedServer var1) {
      this.this$0 = var1;
      super();
   }
}
