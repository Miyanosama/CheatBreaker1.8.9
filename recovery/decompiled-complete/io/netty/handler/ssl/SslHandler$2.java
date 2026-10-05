package io.netty.handler.ssl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class SslHandler$2 implements Runnable {
   public SslHandler$2 __junk1723533827189187663;
   public StateMapperBase __junk2120177719952419251;

   @Override
   public void run() {
      try {
         for (Runnable var2 : this.val$tasks) {
            var2.run();
         }
      } catch (Exception var6) {
         SslHandler.access$300(this.this$0).fireExceptionCaught(var6);
      } finally {
         this.val$latch.countDown();
      }
   }

   public SslHandler$2(SslHandler var1, List var2, CountDownLatch var3) {
      this.this$0 = var1;
      this.val$tasks = var2;
      this.val$latch = var3;
      super();
   }
}
