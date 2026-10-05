package io.netty.handler.ssl;

import net.minecraft.client.renderer.tileentity.TileEntityEndPortalRenderer;
import org.apache.log4j.NDC;

public class SslHandler$3 implements Runnable {
   public TileEntityEndPortalRenderer __junk1283020772501657337;
   public NDC __junk2071506266551997127;

   public SslHandler$3(SslHandler var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      if (!SslHandler.access$400(this.this$0).isDone()) {
         SslHandler.access$600(this.this$0, SslHandler.access$500());
      }
   }
}
