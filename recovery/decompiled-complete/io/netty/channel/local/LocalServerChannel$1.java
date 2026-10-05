package io.netty.channel.local;

import io.netty.handler.codec.socks.SocksCmdStatus;
import junit.runner.ReloadingTestSuiteLoader;

public class LocalServerChannel$1 implements Runnable {
   public SocksCmdStatus __junk5283320554704942295;
   public ReloadingTestSuiteLoader __junk529909648804695965;

   public LocalServerChannel$1(LocalServerChannel var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      this.this$0.unsafe().close(this.this$0.unsafe().voidPromise());
   }
}
