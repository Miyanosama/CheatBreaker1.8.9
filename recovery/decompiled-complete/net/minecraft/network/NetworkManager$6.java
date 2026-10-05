package net.minecraft.network;

import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import org.apache.log4j.SortedKeyEnumeration;
import org.apache.log4j.helpers.CyclicBuffer;
import recovered.unidentified.UnidentifiedClass0798;

public class NetworkManager$6 extends ChannelInitializer<Channel> {
   public SortedKeyEnumeration field_0004;
   public UnpooledByteBufAllocator field_0001;
   public CyclicBuffer field_0003;
   public UnidentifiedClass0798 field_0000;

   public NetworkManager$6(NetworkManager var1) {
      this.field_0002 = var1;
      super();
   }

   @Override
   public void initChannel(Channel var1) {
      var1.pipeline().addLast("packet_handler", this.field_0002);
   }
}
