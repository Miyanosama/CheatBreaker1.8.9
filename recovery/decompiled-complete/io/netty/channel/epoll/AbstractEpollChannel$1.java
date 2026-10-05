package io.netty.channel.epoll;

import io.netty.util.internal.OneTimeTask;
import recovered.unidentified.UnidentifiedClass0373;

public class AbstractEpollChannel$1 extends OneTimeTask {
   public UnidentifiedClass0373 __junk8315135275072359182;

   @Override
   public void run() {
      if (!this.this$0.config().isAutoRead() && !this.val$unsafe.readPending) {
         this.val$unsafe.clearEpollIn0();
      }
   }

   public AbstractEpollChannel$1(AbstractEpollChannel var1, AbstractEpollChannel$AbstractEpollUnsafe var2) {
      this.this$0 = var1;
      this.val$unsafe = var2;
      super();
   }
}
