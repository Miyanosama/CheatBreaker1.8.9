package net.minecraft.client.renderer.texture;

import java.util.concurrent.Callable;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

public class TextureAtlasSprite$1 implements Callable<String> {
   public DefaultSSLWebSocketServerFactory field_0002;

   public TextureAtlasSprite$1(TextureAtlasSprite var1, int[][] var2) {
      this.this$0 = var1;
      this.val$aint = var2;
      super();
   }

   public String call() {
      StringBuilder var1 = new StringBuilder();

      for (int[] var5 : this.val$aint) {
         if (var1.length() > 0) {
            var1.append(", ");
         }

         var1.append(var5 == null ? "null" : var5.length);
      }

      return var1.toString();
   }
}
