package io.netty.channel;

import io.netty.handler.codec.http.HttpRequestDecoder;

public class DefaultChannelPipeline$1 implements Runnable {
   public HttpRequestDecoder __junk1168786442712746966;

   @Override
   public void run() {
      synchronized (this.this$0) {
         this.this$0.remove0(this.val$ctx);
      }
   }

   public DefaultChannelPipeline$1(DefaultChannelPipeline var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }
}
