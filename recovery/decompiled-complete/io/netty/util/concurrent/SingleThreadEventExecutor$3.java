package io.netty.util.concurrent;

import io.netty.util.internal.SystemPropertyUtil;

public class SingleThreadEventExecutor$3 implements Runnable {
   public SystemPropertyUtil __junk3615667332474932156;

   @Override
   public void run() {
      SingleThreadEventExecutor.access$600(this.this$0).add(this.val$task);
   }

   public SingleThreadEventExecutor$3(SingleThreadEventExecutor var1, Runnable var2) {
      this.this$0 = var1;
      this.val$task = var2;
      super();
   }
}
