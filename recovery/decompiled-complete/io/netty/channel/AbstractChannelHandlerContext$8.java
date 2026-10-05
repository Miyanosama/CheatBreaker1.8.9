package io.netty.channel;

import com.cheatbreaker.client.nethandler.obj.ServerRule;
import io.netty.util.internal.OneTimeTask;

public class AbstractChannelHandlerContext$8 extends OneTimeTask {
   public ServerRule __junk7111614038868009275;

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$700(this.val$next, this.val$msg);
   }

   public AbstractChannelHandlerContext$8(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, Object var3) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$msg = var3;
      super();
   }
}
