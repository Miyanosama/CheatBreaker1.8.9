package io.netty.channel;

import net.minecraft.world.ChunkCache;

public class AbstractChannelHandlerContext$10 implements Runnable {
   public ChunkCache __junk3953345782218337180;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$900(this.val$next);
   }

   public AbstractChannelHandlerContext$10(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }
}
