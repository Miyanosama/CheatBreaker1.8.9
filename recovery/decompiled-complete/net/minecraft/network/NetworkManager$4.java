package net.minecraft.network;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromiseAggregator;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.client.gui.GuiPlayerTabOverlay$1;

public class NetworkManager$4 implements Runnable {
   public GuiPlayerTabOverlay$1 field_0000;
   public ChannelPromiseAggregator field_0006;

   @Override
   public void run() {
      if (this.field_0002 != this.field_0005) {
         this.field_0001.setConnectionState(this.field_0002);
      }

      ChannelFuture var1 = NetworkManager.access$000(this.field_0001).writeAndFlush(this.field_0003);
      if (this.field_0004 != null) {
         var1.addListeners(this.field_0004);
      }

      var1.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
   }

   public NetworkManager$4(NetworkManager var1, EnumConnectionState var2, EnumConnectionState var3, Packet var4, GenericFutureListener[] var5) {
      this.field_0001 = var1;
      this.field_0002 = var2;
      this.field_0005 = var3;
      this.field_0003 = var4;
      this.field_0004 = var5;
      super();
   }
}
