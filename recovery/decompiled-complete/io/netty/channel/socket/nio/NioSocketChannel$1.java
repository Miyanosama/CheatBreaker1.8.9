package io.netty.channel.socket.nio;

import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.util.internal.OneTimeTask;

public class NioSocketChannel$1 extends OneTimeTask {
   public ZlibCodecFactory __junk489679937889278037;

   public NioSocketChannel$1(NioSocketChannel var1, ChannelPromise var2) {
      this.this$0 = var1;
      this.val$promise = var2;
      super();
   }

   @Override
   public void run() {
      this.this$0.shutdownOutput(this.val$promise);
   }
}
