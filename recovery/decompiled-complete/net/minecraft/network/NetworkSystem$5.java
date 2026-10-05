package net.minecraft.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import net.minecraft.client.network.NetHandlerHandshakeMemory;

public class NetworkSystem$5 extends ChannelInitializer<Channel> {
   public NetworkSystem$5(NetworkSystem var1) {
      this.field_0000 = var1;
      super();
   }

   @Override
   public void initChannel(Channel var1) {
      NetworkManager var2 = new NetworkManager(EnumPacketDirection.SERVERBOUND);
      var2.setNetHandler(new NetHandlerHandshakeMemory(NetworkSystem.access$100(this.field_0000), var2));
      NetworkSystem.access$000(this.field_0000).add(var2);
      var1.pipeline().addLast("packet_handler", var2);
   }
}
