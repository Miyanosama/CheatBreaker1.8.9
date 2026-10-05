package io.netty.channel.socket.oio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.EventLoop;
import io.netty.channel.oio.OioByteStreamChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.gui.stream.GuiIngestServers$ServerList;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$2;

public class OioSocketChannel extends OioByteStreamChannel implements SocketChannel {
   public GuiScreenRealmsProxy __junk8259993923621005023;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OioSocketChannel.class);
   public GuiIngestServers$ServerList __junk5940931635826243215;
   public OioSocketChannelConfig config;
   public Socket socket;
   public LogBrokerMonitor$2 __junk7458549244374262866;

   @Override
   public void setReadPending(boolean var1) {
      super.setReadPending(var1);
   }

   @Override
   public ChannelFuture shutdownOutput(ChannelPromise var1) {
      EventLoop var2 = this.eventLoop();
      if (var2.inEventLoop()) {
         try {
            this.socket.shutdownOutput();
            var1.setSuccess();
         } catch (Throwable var4) {
            var1.setFailure(var4);
         }
      } else {
         var2.execute(new OioSocketChannel$1(this, var1));
      }

      return var1;
   }

   public OioSocketChannel(Channel var1, Socket var2) {
      super(var1);
      this.socket = var2;
      this.config = new DefaultOioSocketChannelConfig(this, var2);
      boolean var3 = false;

      try {
         if (var2.isConnected()) {
            this.activate(var2.getInputStream(), var2.getOutputStream());
         }

         var2.setSoTimeout(1000);
         var3 = true;
      } catch (Exception var12) {
         throw new ChannelException("failed to initialize a socket", var12);
      } finally {
         if (!var3) {
            try {
               var2.close();
            } catch (IOException var11) {
               logger.warn("Failed to close a socket.", (Throwable)var11);
            }
         }
      }
   }

   @Override
   public boolean isInputShutdown() {
      return super.isInputShutdown();
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.socket.bind(var1);
   }

   @Override
   public boolean isActive() {
      return !this.socket.isClosed() && this.socket.isConnected();
   }

   public OioSocketChannel(Socket var1) {
      this(null, var1);
   }

   @Override
   public void doConnect(SocketAddress var1, SocketAddress var2) {
      if (var2 != null) {
         this.socket.bind(var2);
      }

      boolean var3 = false;

      try {
         this.socket.connect(var1, this.config().getConnectTimeoutMillis());
         this.activate(this.socket.getInputStream(), this.socket.getOutputStream());
         var3 = true;
      } catch (SocketTimeoutException var9) {
         ConnectTimeoutException var5 = new ConnectTimeoutException("connection timed out: " + var1);
         var5.setStackTrace(var9.getStackTrace());
         throw var5;
      } finally {
         if (!var3) {
            this.doClose();
         }
      }
   }

   @Override
   public void doDisconnect() {
      this.doClose();
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public int doReadBytes(ByteBuf var1) {
      if (this.socket.isClosed()) {
         return -1;
      } else {
         try {
            return super.doReadBytes(var1);
         } catch (SocketTimeoutException var3) {
            return 0;
         }
      }
   }

   @Override
   public SocketAddress localAddress0() {
      return this.socket.getLocalSocketAddress();
   }

   public OioSocketChannel() {
      this(new Socket());
   }

   public OioSocketChannelConfig config() {
      return this.config;
   }

   @Override
   public boolean isOpen() {
      return !this.socket.isClosed();
   }

   @Override
   public ChannelFuture shutdownOutput() {
      return this.shutdownOutput(this.newPromise());
   }

   @Override
   public void doClose() {
      this.socket.close();
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.socket.getRemoteSocketAddress();
   }

   @Override
   public boolean isOutputShutdown() {
      return this.socket.isOutputShutdown() || !this.isActive();
   }

   @Override
   public boolean checkInputShutdown() {
      if (this.isInputShutdown()) {
         try {
            Thread.sleep(this.config().getSoTimeout());
         } catch (Throwable var2) {
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   public ServerSocketChannel parent() {
      return (ServerSocketChannel)super.parent();
   }
}
