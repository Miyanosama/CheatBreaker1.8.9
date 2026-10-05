package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.IntHashMap$Entry;

public class SpdySessionHandler$1 implements ChannelFutureListener {
   public IntHashMap$Entry __junk8241467360880057864;

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         SpdySessionHandler.access$000(this.this$0, this.val$context, SpdySessionStatus.INTERNAL_ERROR);
      }
   }

   public SpdySessionHandler$1(SpdySessionHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$context = var2;
      super();
   }
}
