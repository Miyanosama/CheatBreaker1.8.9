package io.netty.channel;

public class ThreadPerChannelEventLoop$1 implements ChannelFutureListener {
   public ThreadPerChannelEventLoop$1(ThreadPerChannelEventLoop var1) {
      this.this$0 = var1;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (var1.isSuccess()) {
         ThreadPerChannelEventLoop.access$002(this.this$0, var1.channel());
      } else {
         this.this$0.deregister();
      }
   }
}
