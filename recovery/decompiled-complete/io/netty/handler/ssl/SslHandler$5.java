package io.netty.handler.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class SslHandler$5 implements GenericFutureListener<Future<Channel>> {
   @Override
   public void operationComplete(Future<Channel> var1) {
      if (!var1.isSuccess()) {
         SslHandler.access$200().debug("Failed to complete handshake", var1.cause());
         this.val$ctx.close();
      }
   }

   public SslHandler$5(SslHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }
}
