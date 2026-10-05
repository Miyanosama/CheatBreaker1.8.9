package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

public class SpdySessionHandler$2 implements ChannelFutureListener {
   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         SpdySessionHandler.access$000(this.this$0, this.val$context, SpdySessionStatus.INTERNAL_ERROR);
      }
   }

   public SpdySessionHandler$2(SpdySessionHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$context = var2;
      super();
   }
}
