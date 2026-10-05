package io.netty.handler.codec.compression;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelPromiseNotifier;
import net.minecraft.client.stream.BroadcastController$BroadcastState;
import net.minecraft.entity.player.InventoryPlayer;
import net.optifine.shaders.uniform.UniformType;

public class JdkZlibEncoder$1 implements Runnable {
   public UniformType __junk4523868113261009527;
   public InventoryPlayer __junk6786528467677094301;
   public BroadcastController$BroadcastState __junk830944435068962177;

   @Override
   public void run() {
      ChannelFuture var1 = JdkZlibEncoder.access$100(this.this$0, JdkZlibEncoder.access$000(this.this$0), this.val$p);
      var1.addListener(new ChannelPromiseNotifier(this.val$promise));
   }

   public JdkZlibEncoder$1(JdkZlibEncoder var1, ChannelPromise var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$p = var2;
      this.val$promise = var3;
      super();
   }
}
