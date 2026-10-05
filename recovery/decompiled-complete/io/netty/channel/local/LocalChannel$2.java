package io.netty.channel.local;

import io.netty.channel.embedded.EmbeddedChannel$DefaultUnsafe;

public class LocalChannel$2 implements Runnable {
   public EmbeddedChannel$DefaultUnsafe __junk1012245418565234250;

   @Override
   public void run() {
      this.this$0.unsafe().close(this.this$0.unsafe().voidPromise());
   }

   public LocalChannel$2(LocalChannel var1) {
      this.this$0 = var1;
      super();
   }
}
