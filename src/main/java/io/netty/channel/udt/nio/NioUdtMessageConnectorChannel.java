package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import com.cheatbreaker.client.util.voicechat.VoiceChannel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.udt.DefaultUdtChannelConfig;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.UdtChannelConfig;
import io.netty.channel.udt.UdtMessage;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker07;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import net.minecraft.server.management.PlayerProfileCache;

public class NioUdtMessageConnectorChannel extends AbstractNioMessageChannel implements UdtChannel {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(NioUdtMessageConnectorChannel.class);
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public static String EXPECTED_TYPE = " (expected: " + StringUtil.simpleClassName(UdtMessage.class) + ')';
   public UdtChannelConfig config;

   public NioUdtMessageConnectorChannel(Channel var1, SocketChannelUDT var2) {
      super(var1, var2, 1);

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
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public SocketAddress localAddress0() {
      return this.javaChannel().socket().getLocalSocketAddress();
   }

   public NioUdtMessageConnectorChannel(SocketChannelUDT var1) {
      this(null, var1);
   }

   public NioUdtMessageConnectorChannel(TypeUDT var1) {
      this(NioUdtProvider.newConnectorChannelUDT(var1));
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.javaChannel().close();
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

   @Override
   public int doReadMessages(List<Object> var1) throws java.lang.Exception {
      int var2 = this.config.getReceiveBufferSize();
      ByteBuf var3 = this.config.getAllocator().directBuffer(var2);
      int var4 = var3.writeBytes(this.javaChannel(), var2);
      if (var4 <= 0) {
         var3.release();
         return 0;
      } else if (var4 >= var2) {
         this.javaChannel().close();
         throw new ChannelException("Invalid config : increase receive buffer size to avoid message truncation");
      } else {
         var1.add(new UdtMessage(var3));
         return 1;
      }
   }

   @Override
   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      if (var1 instanceof UdtMessage) {
         return var1;
      } else {
         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(var1) + EXPECTED_TYPE);
      }
   }

   public SocketChannelUDT javaChannel() {
      return (SocketChannelUDT)super.javaChannel();
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      this.doClose();
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.javaChannel().socket().getRemoteSocketAddress();
   }

   @Override
   public boolean isActive() {
      SocketChannelUDT var1 = this.javaChannel();
      return var1.isOpen() && var1.isConnectFinished();
   }

   @Override
   public void doFinishConnect() throws java.lang.Exception {
      if (this.javaChannel().finishConnect()) {
         this.selectionKey().interestOps(this.selectionKey().interestOps() & -9);
      } else {
         throw new Error("Provider error: failed to finish connect. Provider library should be upgraded.");
      }
   }

   public NioUdtMessageConnectorChannel() {
      this(TypeUDT.DATAGRAM);
   }

   @Override
   public UdtChannelConfig config() {
      return this.config;
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      this.javaChannel().bind(var1);
   }

   @Override
   public boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2) throws java.lang.Exception {
      UdtMessage var3 = (UdtMessage)var1;
      ByteBuf var4 = var3.content();
      int var5 = var4.readableBytes();
      long var6;
      if (var4.nioBufferCount() == 1) {
         var6 = this.javaChannel().write(var4.nioBuffer());
      } else {
         var6 = this.javaChannel().write(var4.nioBuffers());
      }

      if (var6 <= 0L && var5 > 0) {
         return false;
      } else if (var6 != var5) {
         throw new Error("Provider error: failed to write message. Provider library should be upgraded.");
      } else {
         return true;
      }
   }
}
