package io.netty.channel;

import java.net.SocketAddress;
import net.minecraft.realms.RealmsScreen;

public abstract class AbstractServerChannel extends AbstractChannel implements ServerChannel {
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public RealmsScreen __junk19033504589424423;

   @Override
   public Object filterOutboundMessage(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public SocketAddress remoteAddress() {
      return null;
   }

   @Override
   public AbstractChannel$AbstractUnsafe newUnsafe() {
      return new AbstractServerChannel$DefaultServerUnsafe(this, null);
   }

   public AbstractServerChannel() {
      super(null);
   }

   @Override
   public void doDisconnect() {
      throw new UnsupportedOperationException();
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }
}
