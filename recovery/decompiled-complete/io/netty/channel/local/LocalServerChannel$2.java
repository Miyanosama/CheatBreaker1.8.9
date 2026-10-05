package io.netty.channel.local;

import io.netty.channel.AbstractChannelHandlerContext$13;

public class LocalServerChannel$2 implements Runnable {
   public AbstractChannelHandlerContext$13 __junk6557085144105965154;

   @Override
   public void run() {
      LocalServerChannel.access$000(this.this$0, this.val$child);
   }

   public LocalServerChannel$2(LocalServerChannel var1, LocalChannel var2) {
      this.this$0 = var1;
      this.val$child = var2;
      super();
   }
}
