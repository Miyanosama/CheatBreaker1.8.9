package io.netty.channel;

import java.net.SocketAddress;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.optifine.config.MatchBlock;

public class AbstractServerChannel$DefaultServerUnsafe extends AbstractChannel$AbstractUnsafe {
   public MatchBlock __junk3822918908851330339;
   public S04PacketEntityEquipment __junk839409502354011895;
   public EntityFishHook __junk2113569040453873537;

   public AbstractServerChannel$DefaultServerUnsafe(AbstractServerChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      this.safeSetFailure(var3, new UnsupportedOperationException());
   }
}
