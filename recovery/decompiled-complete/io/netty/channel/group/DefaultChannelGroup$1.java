package io.netty.channel.group;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.network.NetworkManager$3;

public class DefaultChannelGroup$1 implements ChannelFutureListener {
   public NetworkManager$3 __junk2515553458328770909;
   public ModelRotation __junk6964456728856974856;

   public void operationComplete(ChannelFuture var1) {
      this.this$0.remove(var1.channel());
   }

   public DefaultChannelGroup$1(DefaultChannelGroup var1) {
      this.this$0 = var1;
      super();
   }
}
