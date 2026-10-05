package io.netty.channel;

import org.apache.log4j.chainsaw.ControlPanel$1;

public class AbstractChannelHandlerContext$9 implements Runnable {
   public ControlPanel$1 __junk5526245273880921564;

   public AbstractChannelHandlerContext$9(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$800(this.val$next);
   }
}
