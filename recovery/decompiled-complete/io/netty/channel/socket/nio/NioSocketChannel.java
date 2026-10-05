package io.netty.channel.socket.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.FileRegion;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.AttributeKey;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.spi.SelectorProvider;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog;
import org.java_websocket.exceptions.InvalidEncodingException;

public class NioSocketChannel extends AbstractNioByteChannel implements SocketChannel {
   public static SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
   public InvalidEncodingException __junk4021168405161149399;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public LogFactor5ErrorDialog __junk4739042900930831271;
   public SocketChannelConfig config;
   public AttributeKey __junk5433118864957970959;

   @Override
   public SocketChannelConfig config() {
      return this.config;
   }

   @Override
   public int doReadBytes(ByteBuf var1) {
      return var1.writeBytes(this.javaChannel(), var1.writableBytes());
   }

   @Override
   public void doFinishConnect() {
      if (!this.javaChannel().finishConnect()) {
         throw new Error();
      }
   }

   @Override
   public ChannelFuture shutdownOutput() {
      return this.shutdownOutput(this.newPromise());
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      while (true) {
         int var2 = var1.size();
         if (var2 == 0) {
            this.clearOpWrite();
            break;
         }

         long var3;
         boolean var5;
         boolean var6;
         var3 = 5166408605926162944L & -5166408607068221416L;
         var5 = false;
         var6 = false;
         ByteBuffer[] var7 = var1.nioBuffers();
         int var8 = var1.nioBufferCount();
         long var9 = var1.nioBufferSize();
         java.nio.channels.SocketChannel var11 = this.javaChannel();
         label51:
         switch (var8) {
            case 0:
               super.doWrite(var1);
               return;
            case 1:
               ByteBuffer var12 = var7[0];

               for (int var16 = this.config().getWriteSpinCount() - 1; var16 >= 0; var16--) {
                  int var17 = var11.write(var12);
                  if (var17 == 0) {
                     var6 = true;
                     break label51;
                  }

                  var9 -= var17;
                  var3 += var17;
                  if (var9 == (5091500921373065226L & -5091500921487885851L)) {
                     var5 = true;
                     break label51;
                  }
               }
               break;
            default:
               for (int var13 = this.config().getWriteSpinCount() - 1; var13 >= 0; var13--) {
                  long var14 = var11.write(var7, 0, var8);
                  if (var14 == (-2598236602374776829L & 1612709896L)) {
                     var6 = true;
                     break;
                  }

                  var9 -= var14;
                  var3 += var14;
                  if (var9 == (-8034738915273175862L & 545259812L)) {
                     var5 = true;
                     break;
                  }
               }
         }

         var1.removeBytes(var3);
         if (!var5) {
            this.incompleteWrite(var6);
            break;
         }
      }
   }

   @Override
   public void doClose() {
      this.javaChannel().close();
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.javaChannel().socket().bind(var1);
   }

   public NioSocketChannel() {
      this(newSocket(DEFAULT_SELECTOR_PROVIDER));
   }

   @Override
   public boolean isOutputShutdown() {
      return this.javaChannel().socket().isOutputShutdown() || !this.isActive();
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   @Override
   public boolean isActive() {
      java.nio.channels.SocketChannel var1 = this.javaChannel();
      return var1.isOpen() && var1.isConnected();
   }

   @Override
   public boolean doConnect(SocketAddress var1, SocketAddress var2) {
      if (var2 != null) {
         this.javaChannel().socket().bind(var2);
      }

      boolean var3 = false;

      boolean var5;
      try {
         boolean var4 = this.javaChannel().connect(var1);
         if (!var4) {
            this.selectionKey().interestOps(8);
         }

         var3 = true;
         var5 = var4;
      } finally {
         if (!var3) {
            this.doClose();
         }
      }

      return var5;
   }

   public static java.nio.channels.SocketChannel newSocket(SelectorProvider var0) {
      try {
         return var0.openSocketChannel();
      } catch (IOException var2) {
         throw new ChannelException("Failed to open a socket.", var2);
      }
   }

   public NioSocketChannel(SelectorProvider var1) {
      this(newSocket(var1));
   }

   @Override
   public ChannelFuture shutdownOutput(ChannelPromise var1) {
      NioEventLoop var2 = this.eventLoop();
      if (var2.inEventLoop()) {
         try {
            this.javaChannel().socket().shutdownOutput();
            var1.setSuccess();
         } catch (Throwable var4) {
            var1.setFailure(var4);
         }
      } else {
         var2.execute(new NioSocketChannel$1(this, var1));
      }

      return var1;
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   public java.nio.channels.SocketChannel javaChannel() {
      return (java.nio.channels.SocketChannel)super.javaChannel();
   }

   public NioSocketChannel(java.nio.channels.SocketChannel var1) {
      this(null, var1);
   }

   @Override
   public void doDisconnect() {
      this.doClose();
   }

   @Override
   public int doWriteBytes(ByteBuf var1) {
      int var2 = var1.readableBytes();
      return var1.readBytes(this.javaChannel(), var2);
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public boolean isInputShutdown() {
      return super.isInputShutdown();
   }

   public NioSocketChannel(Channel var1, java.nio.channels.SocketChannel var2) {
      super(var1, var2);
      this.config = new NioSocketChannel$NioSocketChannelConfig(this, this, var2.socket(), null);
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.javaChannel().socket().getRemoteSocketAddress();
   }

   @Override
   public long doWriteFileRegion(FileRegion var1) {
      long var2 = var1.transfered();
      return var1.transferTo(this.javaChannel(), var2);
   }

   @Override
   public ServerSocketChannel parent() {
      return (ServerSocketChannel)super.parent();
   }
}
