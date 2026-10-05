package io.netty.channel.oio;

import io.netty.channel.AbstractChannel;
import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.Channel;
import io.netty.channel.EventLoop;
import io.netty.channel.ThreadPerChannelEventLoop;
import java.net.SocketAddress;
import net.minecraft.command.server.CommandMessage;
import org.newsclub.net.unix.AFUNIXServerSocket;

public abstract class AbstractOioChannel extends AbstractChannel {
   public CommandMessage __junk6056213771770911059;
   public Runnable readTask = new AbstractOioChannel$1(this);
   public AFUNIXServerSocket __junk6456369138043493061;
   public static int SO_TIMEOUT;
   public volatile boolean readPending;

   public abstract void doConnect(SocketAddress var1, SocketAddress var2);

   public AbstractOioChannel(Channel var1) {
      super(var1);
   }

   public void setReadPending(boolean var1) {
      this.readPending = var1;
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof ThreadPerChannelEventLoop;
   }

   @Override
   public void doBeginRead() {
      if (!this.isReadPending()) {
         this.setReadPending(true);
         this.eventLoop().execute(this.readTask);
      }
   }

   public boolean isReadPending() {
      return this.readPending;
   }

   public abstract void doRead();

   @Override
   public AbstractChannel$AbstractUnsafe newUnsafe() {
      return new AbstractOioChannel$DefaultOioUnsafe(this, null);
   }
}
