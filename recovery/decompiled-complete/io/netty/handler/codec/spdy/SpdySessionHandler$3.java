package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$2;
import net.minecraft.util.WeightedRandomChestContent;
import recovered.unidentified.UnidentifiedClass5065;

public class SpdySessionHandler$3 implements ChannelFutureListener {
   public WeightedRandomChestContent __junk8172704880624124143;
   public UnidentifiedClass5065 __junk4443415216835167910;
   public EpollSocketChannel$EpollSocketUnsafe$2 __junk7642563672915528506;

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         SpdySessionHandler.access$000(this.this$0, this.val$ctx, SpdySessionStatus.INTERNAL_ERROR);
      }
   }

   public SpdySessionHandler$3(SpdySessionHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }
}
