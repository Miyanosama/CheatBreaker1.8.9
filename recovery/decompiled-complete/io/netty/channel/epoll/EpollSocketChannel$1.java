package io.netty.channel.epoll;

import io.netty.channel.ChannelPromise;
import io.netty.handler.timeout.ReadTimeoutHandler$ReadTimeoutTask;

public class EpollSocketChannel$1 implements Runnable {
   public ReadTimeoutHandler$ReadTimeoutTask __junk8997876172373321199;

   @Override
   public void run() {
      this.this$0.shutdownOutput(this.val$promise);
   }

   public EpollSocketChannel$1(EpollSocketChannel var1, ChannelPromise var2) {
      this.this$0 = var1;
      this.val$promise = var2;
      super();
   }
}
