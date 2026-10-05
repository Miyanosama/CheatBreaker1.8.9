package io.netty.channel.socket.oio;

import io.netty.channel.ChannelPromise;
import net.minecraft.client.renderer.BlockModelShapes;

public class OioSocketChannel$1 implements Runnable {
   public BlockModelShapes __junk7979690944016174783;

   @Override
   public void run() {
      this.this$0.shutdownOutput(this.val$future);
   }

   public OioSocketChannel$1(OioSocketChannel var1, ChannelPromise var2) {
      this.this$0 = var1;
      this.val$future = var2;
      super();
   }
}
