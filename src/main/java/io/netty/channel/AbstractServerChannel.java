package io.netty.channel;

import java.net.SocketAddress;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.realms.RealmsScreen;
import net.optifine.config.MatchBlock;

public abstract class AbstractServerChannel extends AbstractChannel implements ServerChannel {
   public static ChannelMetadata METADATA = new ChannelMetadata(false);

   @Override
   public Object filterOutboundMessage(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public SocketAddress remoteAddress() {
      return null;
   }

   @Override
   public AbstractChannel.AbstractUnsafe newUnsafe() {
      return new AbstractServerChannel.DefaultServerUnsafe();
   }

   public AbstractServerChannel() {
      super(null);
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   public final class DefaultServerUnsafe extends AbstractChannel.AbstractUnsafe {

      public DefaultServerUnsafe() {
      }

      @Override
      public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
         this.safeSetFailure(var3, new UnsupportedOperationException());
      }
   }
}
