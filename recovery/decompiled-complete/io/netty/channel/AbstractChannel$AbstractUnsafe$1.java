package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import javazoom.jl.converter.jlc;

public class AbstractChannel$AbstractUnsafe$1 extends OneTimeTask {
   public jlc __junk8972559142068983928;

   public AbstractChannel$AbstractUnsafe$1(AbstractChannel$AbstractUnsafe var1, ChannelPromise var2) {
      this.this$1 = var1;
      this.val$promise = var2;
      super();
   }

   @Override
   public void run() {
      AbstractChannel$AbstractUnsafe.access$100(this.this$1, this.val$promise);
   }
}
