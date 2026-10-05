package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.channel.ChannelMetadata;
import io.netty.handler.codec.spdy.SpdyHeaders$1;
import java.util.List;
import junit.swingui.TestSelector$TestCellRenderer;
import net.minecraft.block.BlockAir;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class NioUdtByteAcceptorChannel extends NioUdtAcceptorChannel {
   public WorldGenBigTree __junk6928833198566616502;
   public Frustum __junk7718127502624787134;
   public SpdyHeaders$1 __junk1770322484236994281;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public TestSelector$TestCellRenderer __junk8335960904295479033;
   public BlockAir __junk4404975989337266109;

   public NioUdtByteAcceptorChannel() {
      super(TypeUDT.STREAM);
   }

   @Override
   public int doReadMessages(List<Object> var1) {
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
