package net.minecraft.network;

import com.cheatbreaker.client.ui.element.module.ModuleSettingsElement;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.util.MessageDeserializer;
import net.minecraft.util.MessageDeserializer2;
import net.minecraft.util.MessageSerializer;
import net.minecraft.util.MessageSerializer2;
import recovered.unidentified.UnidentifiedClass1464;

public class NetworkManager$5 extends ChannelInitializer<Channel> {
   public ModuleSettingsElement field_0001;
   public BlockFlower$EnumFlowerType field_0003;
   public UnidentifiedClass1464 field_0002;

   @Override
   public void initChannel(Channel var1) {
      try {
         var1.config().setOption(ChannelOption.TCP_NODELAY, true);
      } catch (ChannelException var3) {
      }

      var1.pipeline()
         .addLast("timeout", new ReadTimeoutHandler(30))
         .addLast("splitter", new MessageDeserializer2())
         .addLast("decoder", new MessageDeserializer(EnumPacketDirection.CLIENTBOUND))
         .addLast("prepender", new MessageSerializer2())
         .addLast("encoder", new MessageSerializer(EnumPacketDirection.SERVERBOUND))
         .addLast("packet_handler", this.field_0000);
   }

   public NetworkManager$5(NetworkManager var1) {
      this.field_0000 = var1;
      super();
   }
}
