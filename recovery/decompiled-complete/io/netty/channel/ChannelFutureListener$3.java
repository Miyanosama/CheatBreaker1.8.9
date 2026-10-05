package io.netty.channel;

import net.minecraft.world.chunk.storage.NibbleArrayReader;

public class ChannelFutureListener$3 implements ChannelFutureListener {
   public NibbleArrayReader __junk7159825675531538153;

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         var1.channel().pipeline().fireExceptionCaught(var1.cause());
      }
   }
}
