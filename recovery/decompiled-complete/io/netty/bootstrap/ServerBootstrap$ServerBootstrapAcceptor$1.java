package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.sctp.DefaultSctpServerChannelConfig;
import net.minecraft.client.particle.EntityCritFX$Factory;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Piece;

public class ServerBootstrap$ServerBootstrapAcceptor$1 implements ChannelFutureListener {
   public DefaultSctpServerChannelConfig __junk3037005143554302865;
   public StructureOceanMonumentPieces$Piece __junk1320056423394204903;
   public EntityCritFX$Factory __junk4015301845599749402;

   public ServerBootstrap$ServerBootstrapAcceptor$1(ServerBootstrap$ServerBootstrapAcceptor var1, Channel var2) {
      this.this$0 = var1;
      this.val$child = var2;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         ServerBootstrap$ServerBootstrapAcceptor.access$100(this.val$child, var1.cause());
      }
   }
}
