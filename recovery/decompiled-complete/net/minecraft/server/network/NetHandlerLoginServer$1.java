package net.minecraft.server.network;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.block.BlockRailBase$EnumRailDirection;
import net.minecraft.client.renderer.VertexBufferUploader;
import net.minecraft.util.FoodStats;
import org.slf4j.helpers.MessageFormatter;

public class NetHandlerLoginServer$1 implements ChannelFutureListener {
   public VertexBufferUploader field_0002;
   public BlockRailBase$EnumRailDirection field_0004;
   public FoodStats field_0001;
   public MessageFormatter field_0003;

   public void method_03520(ChannelFuture var1) {
      this.field_0000.networkManager.setCompressionTreshold(NetHandlerLoginServer.access$000(this.field_0000).getNetworkCompressionTreshold());
   }

   public NetHandlerLoginServer$1(NetHandlerLoginServer var1) {
      this.field_0000 = var1;
      super();
   }
}
