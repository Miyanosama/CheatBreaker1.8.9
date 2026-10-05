package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.channel.ChannelMetadata;
import io.netty.util.internal.PendingWrite;
import java.util.List;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition;

public class NioUdtMessageAcceptorChannel extends NioUdtAcceptorChannel {
   public static ChannelMetadata METADATA = new ChannelMetadata(false);

   @Override
   public int doReadMessages(List<Object> var1) throws java.lang.Exception {
      SocketChannelUDT var2 = this.javaChannel().accept();
      if (var2 == null) {
         return 0;
      } else {
         var1.add(new NioUdtMessageConnectorChannel(this, var2));
         return 1;
      }
   }

   public NioUdtMessageAcceptorChannel() {
      super(TypeUDT.DATAGRAM);
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }
}
