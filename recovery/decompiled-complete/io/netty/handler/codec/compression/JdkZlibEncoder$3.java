package io.netty.handler.codec.compression;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class JdkZlibEncoder$3 implements Runnable {
   @Override
   public void run() {
      this.val$ctx.close(this.val$promise);
   }

   public JdkZlibEncoder$3(JdkZlibEncoder var1, ChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$promise = var3;
      super();
   }
}
