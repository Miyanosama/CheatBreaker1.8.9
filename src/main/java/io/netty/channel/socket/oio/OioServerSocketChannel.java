package io.netty.channel.socket.oio;

import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.oio.AbstractOioMessageChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.block.BlockClay;
import net.minecraft.potion.PotionHelper;

public class OioServerSocketChannel extends AbstractOioMessageChannel implements ServerSocketChannel {
   public Lock shutdownLock = new ReentrantLock();
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OioServerSocketChannel.class);
   public OioServerSocketChannelConfig config;
   public ServerSocket socket;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      this.socket.bind(var1, this.config.getBacklog());
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public void doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public OioServerSocketChannelConfig config() {
      return this.config;
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public SocketAddress localAddress0() {
      return this.socket.getLocalSocketAddress();
   }

   @Override
   public int doReadMessages(List<Object> var1) throws java.lang.Exception {
      if (this.socket.isClosed()) {
         return -1;
      } else {
         try {
            Socket var2 = this.socket.accept();

            try {
               var1.add(new OioSocketChannel(this, var2));
               return 1;
            } catch (Throwable var6) {
               logger.warn("Failed to create a new channel from an accepted socket.", var6);

               try {
                  var2.close();
               } catch (Throwable var5) {
                  logger.warn("Failed to close a socket.", var5);
               }
            }
         } catch (SocketTimeoutException var7) {
         }

         return 0;
      }
   }

   @Override
   public boolean isOpen() {
      return !this.socket.isClosed();
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.socket.close();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public void setReadPending(boolean var1) {
      super.setReadPending(var1);
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return null;
   }

   public OioServerSocketChannel(ServerSocket var1) {
      super(null);
      if (var1 == null) {
         throw new NullPointerException("socket");
      } else {
         boolean var2 = false;

         try {
            var1.setSoTimeout(1000);
            var2 = true;
         } catch (IOException var10) {
            throw new ChannelException("Failed to set the server socket timeout.", var10);
         } finally {
            if (!var2) {
               try {
                  var1.close();
               } catch (IOException var11) {
                  if (logger.isWarnEnabled()) {
                     logger.warn("Failed to close a partially initialized socket.", (Throwable)var11);
                  }
               }
            }
         }

         this.socket = var1;
         this.config = new DefaultOioServerSocketChannelConfig(this, var1);
      }
   }

   public OioServerSocketChannel() {
      this(newServerSocket());
   }

   public static ServerSocket newServerSocket() {
      try {
         return new ServerSocket();
      } catch (IOException var1) {
         throw new ChannelException("failed to create a server socket", var1);
      }
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isActive() {
      return this.isOpen() && this.socket.isBound();
   }
}
