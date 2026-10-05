package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import java.net.SocketAddress;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.entity.passive.EntityVillager$ListItemForEmeralds;
import net.minecraft.inventory.SlotCrafting;
import net.optifine.RandomEntity;

public class Bootstrap$1 implements ChannelFutureListener {
   public BlockModelRenderer __junk7088285926208391014;
   public RandomEntity __junk5860227562395661625;
   public SlotCrafting __junk7755696437626616906;
   public EntityVillager$ListItemForEmeralds __junk6591071062116118884;

   public void operationComplete(ChannelFuture var1) {
      Bootstrap.access$000(this.val$regFuture, this.val$channel, this.val$remoteAddress, this.val$localAddress, this.val$promise);
   }

   public Bootstrap$1(Bootstrap var1, ChannelFuture var2, Channel var3, SocketAddress var4, SocketAddress var5, ChannelPromise var6) {
      this.this$0 = var1;
      this.val$regFuture = var2;
      this.val$channel = var3;
      this.val$remoteAddress = var4;
      this.val$localAddress = var5;
      this.val$promise = var6;
      super();
   }
}
