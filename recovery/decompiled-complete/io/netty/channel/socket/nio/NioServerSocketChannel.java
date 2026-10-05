package io.netty.channel.socket.nio;

import com.cheatbreaker.client.nethandler.shared.PacketAddWaypoint;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.List;

public class NioServerSocketChannel extends AbstractNioMessageChannel implements ServerSocketChannel {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(NioServerSocketChannel.class);
   public ServerSocketChannelConfig config = new NioServerSocketChannel$NioServerSocketChannelConfig(this, this, this.javaChannel().socket(), null);
   public static SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public PacketAddWaypoint __junk5321413500057306849;

   @Override
   public ServerSocketChannelConfig config() {
      return this.config;
   }

   @Override
   public boolean isActive() {
      return this.javaChannel().socket().isBound();
   }

   public java.nio.channels.ServerSocketChannel javaChannel() {
      return (java.nio.channels.ServerSocketChannel)super.javaChannel();
   }

   @Override
   public boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean doConnect(SocketAddress var1, SocketAddress var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void doFinishConnect() {
      throw new UnsupportedOperationException();
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return null;
   }

   public NioServerSocketChannel(SelectorProvider var1) {
      this(newSocket(var1));
   }

   public NioServerSocketChannel(java.nio.channels.ServerSocketChannel var1) {
      super(null, var1, 16);
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   @Override
   public void doClose() {
      this.javaChannel().close();
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.javaChannel().socket().bind(var1, this.config.getBacklog());
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }

   public NioServerSocketChannel() {
      this(newSocket(DEFAULT_SELECTOR_PROVIDER));
   }

   public static java.nio.channels.ServerSocketChannel newSocket(SelectorProvider var0) {
      try {
         return var0.openServerSocketChannel();
      } catch (IOException var2) {
         throw new ChannelException("Failed to open a server socket.", var2);
      }
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public void doDisconnect() {
      throw new UnsupportedOperationException();
   }

   @Override
   public int doReadMessages(List<Object> var1) {
      SocketChannel var2 = this.javaChannel().accept();

      try {
         if (var2 != null) {
            var1.add(new NioSocketChannel(this, var2));
            return 1;
         }
      } catch (Throwable var6) {
         logger.warn("Failed to create a new channel from an accepted socket.", var6);

         try {
            var2.close();
         } catch (Throwable var5) {
            logger.warn("Failed to close a socket.", var5);
         }
      }

      return 0;
   }
}
