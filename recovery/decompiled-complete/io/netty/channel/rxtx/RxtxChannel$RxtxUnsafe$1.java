package io.netty.channel.rxtx;

import io.netty.bootstrap.ServerBootstrap$1;
import io.netty.channel.ChannelPromise;
import recovered.unidentified.UnidentifiedClass1318;

public class RxtxChannel$RxtxUnsafe$1 implements Runnable {
   public UnidentifiedClass1318 __junk4783768078559008352;
   public ServerBootstrap$1 __junk2370687790288256624;

   @Override
   public void run() {
      try {
         this.this$1.this$0.doInit();
         RxtxChannel$RxtxUnsafe.access$100(this.this$1, this.val$promise);
         if (!this.val$wasActive && this.this$1.this$0.isActive()) {
            this.this$1.this$0.pipeline().fireChannelActive();
         }
      } catch (Throwable var2) {
         RxtxChannel$RxtxUnsafe.access$200(this.this$1, this.val$promise, var2);
         RxtxChannel$RxtxUnsafe.access$300(this.this$1);
      }
   }

   public RxtxChannel$RxtxUnsafe$1(RxtxChannel$RxtxUnsafe var1, ChannelPromise var2, boolean var3) {
      this.this$1 = var1;
      this.val$promise = var2;
      this.val$wasActive = var3;
      super();
   }
}
