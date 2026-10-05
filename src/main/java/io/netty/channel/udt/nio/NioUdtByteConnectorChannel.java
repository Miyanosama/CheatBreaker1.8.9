package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.FileRegion;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.channel.udt.DefaultUdtChannelConfig;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.UdtChannelConfig;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class NioUdtByteConnectorChannel extends AbstractNioByteChannel implements UdtChannel {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(NioUdtByteConnectorChannel.class);
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public UdtChannelConfig config;

   @Override
   public UdtChannelConfig config() {
      return this.config;
   }

   @Override
   public boolean doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception {
      this.doBind((SocketAddress)(var2 != null ? var2 : new InetSocketAddress(0)));
      boolean var3 = false;

      boolean var5;
      try {
         boolean var4 = this.javaChannel().connect(var1);
         if (!var4) {
            this.selectionKey().interestOps(this.selectionKey().interestOps() | 8);
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

   public NioUdtByteConnectorChannel(TypeUDT var1) {
      this(NioUdtProvider.newConnectorChannelUDT(var1));
   }

   @Override
   public boolean isActive() {
      SocketChannelUDT var1 = this.javaChannel();
      return var1.isOpen() && var1.isConnectFinished();
   }

   @Override
   public int doReadBytes(ByteBuf var1) throws java.lang.Exception {
      return var1.writeBytes(this.javaChannel(), var1.writableBytes());
   }

   public SocketChannelUDT javaChannel() {
      return (SocketChannelUDT)super.javaChannel();
   }

   public NioUdtByteConnectorChannel() {
      this(TypeUDT.STREAM);
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      this.doClose();
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.javaChannel().close();
   }

   public NioUdtByteConnectorChannel(SocketChannelUDT var1) {
      this(null, var1);
   }

   @Override
   public int doWriteBytes(ByteBuf var1) throws java.lang.Exception {
      int var2 = var1.readableBytes();
      return var1.readBytes(this.javaChannel(), var2);
   }

   @Override
   public long doWriteFileRegion(FileRegion var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public NioUdtByteConnectorChannel(Channel var1, SocketChannelUDT var2) {
      super(var1, var2);

      try {
         var2.configureBlocking(false);
         switch (var2.socketUDT().status()) {
            case INIT:
            case OPENED:
               this.config = new DefaultUdtChannelConfig(this, var2, true);
               break;
            default:
               this.config = new DefaultUdtChannelConfig(this, var2, false);
         }
      } catch (Exception var6) {
         try {
            var2.close();
         } catch (Exception var5) {
            if (logger.isWarnEnabled()) {
               logger.warn("Failed to close channel.", (Throwable)var5);
            }
         }

         throw new ChannelException("Failed to configure channel.", var6);
      }
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public void doFinishConnect() throws java.lang.Exception {
      if (this.javaChannel().finishConnect()) {
         this.selectionKey().interestOps(this.selectionKey().interestOps() & -9);
      } else {
         throw new Error("Provider error: failed to finish connect. Provider library should be upgraded.");
      }
   }

   @Override
   public SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      this.javaChannel().bind(var1);
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.javaChannel().socket().getRemoteSocketAddress();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }
}
