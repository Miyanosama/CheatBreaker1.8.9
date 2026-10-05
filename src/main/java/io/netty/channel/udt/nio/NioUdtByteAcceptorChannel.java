package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.channel.ChannelMetadata;
import java.util.List;
import junit.swingui.TestSelector;
import net.minecraft.block.BlockAir;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class NioUdtByteAcceptorChannel extends NioUdtAcceptorChannel {
   public static ChannelMetadata METADATA = new ChannelMetadata(false);

   public NioUdtByteAcceptorChannel() {
      super(TypeUDT.STREAM);
   }

   @Override
   public int doReadMessages(List<Object> var1) throws java.lang.Exception {
      SocketChannelUDT var2 = this.javaChannel().accept();
      if (var2 == null) {
         return 0;
      } else {
         var1.add(new NioUdtByteConnectorChannel(this, var2));
         return 1;
      }
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }
}
