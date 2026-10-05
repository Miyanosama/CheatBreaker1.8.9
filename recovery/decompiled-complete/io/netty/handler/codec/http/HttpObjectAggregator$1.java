package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.module.CBModulePosition;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

public class HttpObjectAggregator$1 implements ChannelFutureListener {
   public HttpObjectAggregator$1 __junk6989525384385179753;
   public CBModulePosition __junk5921223565646222895;

   public HttpObjectAggregator$1(HttpObjectAggregator var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         this.val$ctx.fireExceptionCaught(var1.cause());
      }
   }
}
