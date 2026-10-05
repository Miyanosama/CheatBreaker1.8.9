package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import java.net.SocketAddress;
import net.minecraft.block.BlockGlass;
import org.apache.log4j.rewrite.PropertyRewritePolicy;

public class Bootstrap$2 implements Runnable {
   public BlockGlass __junk724852331673506681;
   public PropertyRewritePolicy __junk8079028277036163647;

   public Bootstrap$2(ChannelFuture var1, SocketAddress var2, Channel var3, SocketAddress var4, ChannelPromise var5) {
      this.val$regFuture = var1;
      this.val$localAddress = var2;
      this.val$channel = var3;
      this.val$remoteAddress = var4;
      this.val$promise = var5;
      super();
   }

   @Override
   public void run() {
      if (this.val$regFuture.isSuccess()) {
         if (this.val$localAddress == null) {
            this.val$channel.connect(this.val$remoteAddress, this.val$promise);
         } else {
            this.val$channel.connect(this.val$remoteAddress, this.val$localAddress, this.val$promise);
         }

         this.val$promise.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
      } else {
         this.val$promise.setFailure(this.val$regFuture.cause());
      }
   }
}
