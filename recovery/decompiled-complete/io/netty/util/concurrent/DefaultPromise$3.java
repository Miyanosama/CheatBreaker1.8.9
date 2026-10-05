package io.netty.util.concurrent;

import recovered.unidentified.UnidentifiedClass4855;

public class DefaultPromise$3 implements Runnable {
   public UnidentifiedClass4855 __junk8192302128996550163;

   public DefaultPromise$3(Future var1, GenericFutureListener var2) {
      this.val$future = var1;
      this.val$l = var2;
      super();
   }

   @Override
   public void run() {
      DefaultPromise.notifyListener0(this.val$future, this.val$l);
   }
}
