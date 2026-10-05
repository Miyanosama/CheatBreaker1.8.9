package net.minecraft.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.server.network.NetHandlerHandshakeTCP;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;

public class NetworkSystem$4 extends ChannelInitializer<Channel> {
   @Override
   public void initChannel(Channel var1) {
      try {
         var1.config().setOption(ChannelOption.TCP_NODELAY, true);
      } catch (ChannelException var3) {
      }

      var1.pipeline()
         .addLast("timeout", new ReadTimeoutHandler(30))
         .addLast("legacy_query", new PingResponseHandler(this.field_0000))
         .addLast("splitter", new MessageDeserializer2())
         .addLast("decoder", new MessageDeserializer(EnumPacketDirection.SERVERBOUND))
         .addLast("prepender", new MessageSerializer2())
         .addLast("encoder", new MessageSerializer(EnumPacketDirection.CLIENTBOUND));
      NetworkManager var2 = new NetworkManager(EnumPacketDirection.SERVERBOUND);
      NetworkSystem.access$000(this.field_0000).add(var2);
      var1.pipeline().addLast("packet_handler", var2);
      var2.setNetHandler(new NetHandlerHandshakeTCP(NetworkSystem.access$100(this.field_0000), var2));
   }

   public NetworkSystem$4(NetworkSystem var1) {
      this.field_0000 = var1;
      super();
   }
}
