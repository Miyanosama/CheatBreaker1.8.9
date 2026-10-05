package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.channel.ChannelMetadata;
import io.netty.util.internal.PendingWrite;
import java.util.List;
import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Deserializer;

public class NioUdtMessageAcceptorChannel extends NioUdtAcceptorChannel {
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public ModelBlockDefinition$Deserializer __junk125750254712873751;
   public PendingWrite __junk8882412803295207118;

   @Override
   public int doReadMessages(List<Object> var1) {
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
