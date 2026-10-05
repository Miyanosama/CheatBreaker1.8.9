package io.netty.handler.codec.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageCodec;
import net.minecraft.command.CommandReplaceItem;

public class SpdySessionHandler$ClosingChannelFutureListener implements ChannelFutureListener {
   public ByteToMessageCodec __junk1353910594151322072;
   public CommandReplaceItem __junk2158696305190715432;
   public ChannelPromise promise;
   public ChannelHandlerContext ctx;

   public SpdySessionHandler$ClosingChannelFutureListener(ChannelHandlerContext var1, ChannelPromise var2) {
      this.ctx = var1;
      this.promise = var2;
   }

   public void operationComplete(ChannelFuture var1) {
      this.ctx.close(this.promise);
   }
}
