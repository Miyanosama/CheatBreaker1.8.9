package io.netty.channel;

import net.minecraft.client.network.LanServerDetector$ThreadLanServerFind;
import net.minecraft.entity.projectile.EntityThrowable;

public class AbstractChannelHandlerContext$17 implements Runnable {
   public LanServerDetector$ThreadLanServerFind __junk4940022941523569804;
   public EntityThrowable __junk2442881591808178114;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$1600(this.val$next);
   }

   public AbstractChannelHandlerContext$17(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }
}
