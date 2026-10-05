package net.minecraft.client.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import net.minecraft.block.BlockSeaLantern;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.chunk.VboChunkFactory;
import net.minecraft.network.play.client.C11PacketEnchantItem;
import recovered.unidentified.UnidentifiedClass3953;

public class OldServerPinger$2 extends ChannelInitializer<Channel> {
   public BlockSeaLantern field_0002;
   public VboChunkFactory field_0004;
   public C11PacketEnchantItem field_0001;

   public OldServerPinger$2(OldServerPinger var1, ServerAddress var2, ServerData var3) {
      this.field_0005 = var1;
      this.field_0000 = var2;
      this.field_0003 = var3;
      super();
   }

   @Override
   public void initChannel(Channel var1) {
      try {
         var1.config().setOption(ChannelOption.TCP_NODELAY, true);
      } catch (ChannelException var3) {
      }

      var1.pipeline().addLast(new UnidentifiedClass3953(this));
   }
}
