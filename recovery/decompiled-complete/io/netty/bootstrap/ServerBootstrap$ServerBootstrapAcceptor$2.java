package io.netty.bootstrap;

import io.netty.channel.ChannelConfig;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityGuardian$GuardianMoveHelper;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer;
import net.minecraft.tileentity.TileEntityBrewingStand;

public class ServerBootstrap$ServerBootstrapAcceptor$2 implements Runnable {
   public ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer __junk8863192326657636792;
   public EntityGuardian$GuardianMoveHelper __junk3441884524139891913;
   public EnumCreatureAttribute __junk41391845026240837;
   public TileEntityBrewingStand __junk2418014204327565147;

   @Override
   public void run() {
      this.val$config.setAutoRead(true);
   }

   public ServerBootstrap$ServerBootstrapAcceptor$2(ServerBootstrap$ServerBootstrapAcceptor var1, ChannelConfig var2) {
      this.this$0 = var1;
      this.val$config = var2;
      super();
   }
}
