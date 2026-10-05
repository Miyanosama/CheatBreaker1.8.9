package io.netty.channel.socket.oio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator$Handle;
import io.netty.channel.oio.AbstractOioMessageChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.socket.DefaultDatagramChannelConfig;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Locale;
import javazoom.jl.converter.WaveFileObuffer;
import javazoom.jl.player.advanced.PlaybackEvent;
import net.minecraft.realms.RealmsConnect$1;

public class OioDatagramChannel extends AbstractOioMessageChannel implements DatagramChannel {
   public DatagramChannelConfig config;
   public WaveFileObuffer __junk7194469091438141656;
   public static ChannelMetadata METADATA = new ChannelMetadata(true);
   public RecvByteBufAllocator$Handle allocHandle;
   public RealmsConnect$1 __junk8944128268273930176;
   public PlaybackEvent __junk672302833343334668;
   public MulticastSocket socket;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OioDatagramChannel.class);
   public DatagramPacket tmpPacket = new DatagramPacket(EmptyArrays.EMPTY_BYTES, 0);
   public static String EXPECTED_TYPES = " (expected: "
      + StringUtil.simpleClassName(io.netty.channel.socket.DatagramPacket.class)
      + ", "
      + StringUtil.simpleClassName(AddressedEnvelope.class)
      + '<'
      + StringUtil.simpleClassName(ByteBuf.class)
      + ", "
      + StringUtil.simpleClassName(SocketAddress.class)
      + ">, "
      + StringUtil.simpleClassName(ByteBuf.class)
      + ')';

   @Override
   public boolean isActive() {
      return this.isOpen() && (this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) && this.isRegistered() || this.socket.isBound());
   }

   @Override
   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public void doDisconnect() {
      this.socket.disconnect();
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public void doClose() {
      this.socket.close();
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.socket.getRemoteSocketAddress();
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1, NetworkInterface var2, InetAddress var3, ChannelPromise var4) {
      var4.setFailure(new UnsupportedOperationException());
      return var4;
   }

   @Override
   public DatagramChannelConfig config() {
      return this.config;
   }

   public OioDatagramChannel(MulticastSocket var1) {
      super(null);
      boolean var2 = false;

      try {
         var1.setSoTimeout(1000);
         var1.setBroadcast(false);
         var2 = true;
      } catch (SocketException var7) {
         throw new ChannelException("Failed to configure the datagram socket timeout.", var7);
      } finally {
         if (!var2) {
            var1.close();
         }
      }

      this.socket = var1;
      this.config = new DefaultDatagramChannelConfig(this, var1);
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1, NetworkInterface var2, InetAddress var3) {
      return this.newFailedFuture(new UnsupportedOperationException());
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1) {
      return this.leaveGroup(var1, this.newPromise());
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1, NetworkInterface var2, InetAddress var3, ChannelPromise var4) {
      var4.setFailure(new UnsupportedOperationException());
      return var4;
   }

   @Override
   public ChannelFuture block(InetAddress var1, NetworkInterface var2, InetAddress var3) {
      return this.newFailedFuture(new UnsupportedOperationException());
   }

   @Override
   public ChannelFuture joinGroup(InetSocketAddress var1, NetworkInterface var2, ChannelPromise var3) {
      this.ensureBound();

      try {
         this.socket.joinGroup(var1, var2);
         var3.setSuccess();
      } catch (IOException var5) {
         var3.setFailure(var5);
      }

      return var3;
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1) {
      return this.joinGroup(var1, this.newPromise());
   }

   @Override
   public boolean isConnected() {
      return this.socket.isConnected();
   }

   @Override
   public void doConnect(SocketAddress var1, SocketAddress var2) {
      if (var2 != null) {
         this.socket.bind(var2);
      }

      boolean var3 = false;

      try {
         this.socket.connect(var1);
         var3 = true;
      } finally {
         if (!var3) {
            try {
               this.socket.close();
            } catch (Throwable var10) {
               logger.warn("Failed to close a socket.", var10);
            }
         }
      }
   }

   @Override
   public ChannelFuture block(InetAddress var1, InetAddress var2) {
      return this.newFailedFuture(new UnsupportedOperationException());
   }

   @Override
   public ChannelFuture leaveGroup(InetSocketAddress var1, NetworkInterface var2) {
      return this.leaveGroup(var1, var2, this.newPromise());
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.socket.bind(var1);
   }

   @Override
   public ChannelFuture block(InetAddress var1, InetAddress var2, ChannelPromise var3) {
      var3.setFailure(new UnsupportedOperationException());
      return var3;
   }

   @Override
   public ChannelFuture leaveGroup(InetSocketAddress var1, NetworkInterface var2, ChannelPromise var3) {
      try {
         this.socket.leaveGroup(var1, var2);
         var3.setSuccess();
      } catch (IOException var5) {
         var3.setFailure(var5);
      }

      return var3;
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      while (true) {
         Object var2 = var1.current();
         if (var2 == null) {
            return;
         }

         ByteBuf var3;
         SocketAddress var4;
         if (var2 instanceof AddressedEnvelope) {
            AddressedEnvelope var5 = (AddressedEnvelope)var2;
            var4 = var5.recipient();
            var3 = (ByteBuf)var5.content();
         } else {
            var3 = (ByteBuf)var2;
            var4 = null;
         }

         int var8 = var3.readableBytes();
         if (var4 != null) {
            this.tmpPacket.setSocketAddress(var4);
         }

         if (var3.hasArray()) {
            this.tmpPacket.setData(var3.array(), var3.arrayOffset() + var3.readerIndex(), var8);
         } else {
            byte[] var6 = new byte[var8];
            var3.getBytes(var3.readerIndex(), var6);
            this.tmpPacket.setData(var6);
         }

         try {
            this.socket.send(this.tmpPacket);
            var1.remove();
         } catch (IOException var7) {
            var1.remove(var7);
         }
      }
   }

   @Override
   public boolean isOpen() {
      return !this.socket.isClosed();
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1, NetworkInterface var2, InetAddress var3) {
      return this.newFailedFuture(new UnsupportedOperationException());
   }

   public void ensureBound() {
      if (!this.isActive()) {
         throw new IllegalStateException(DatagramChannel.class.getName() + " must be bound to join a group.");
      }
   }

   @Override
   public SocketAddress localAddress0() {
      return this.socket.getLocalSocketAddress();
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      if (!(var1 instanceof io.netty.channel.socket.DatagramPacket) && !(var1 instanceof ByteBuf)) {
         if (var1 instanceof AddressedEnvelope) {
            AddressedEnvelope var2 = (AddressedEnvelope)var1;
            if (var2.content() instanceof ByteBuf) {
               return var1;
            }
         }

         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(var1) + EXPECTED_TYPES);
      } else {
         return var1;
      }
   }

   public static MulticastSocket newSocket() {
      try {
         return new MulticastSocket(null);
      } catch (Exception var1) {
         throw new ChannelException("failed to create a new socket", var1);
      }
   }

   @Override
   public ChannelFuture joinGroup(InetSocketAddress var1, NetworkInterface var2) {
      return this.joinGroup(var1, var2, this.newPromise());
   }

   @Override
   public ChannelFuture block(InetAddress var1, NetworkInterface var2, InetAddress var3, ChannelPromise var4) {
      var4.setFailure(new UnsupportedOperationException());
      return var4;
   }

   @Override
   public int doReadMessages(List<Object> var1) {
      DatagramChannelConfig var2 = this.config();
      RecvByteBufAllocator$Handle var3 = this.allocHandle;
      if (var3 == null) {
         this.allocHandle = var3 = var2.getRecvByteBufAllocator().newHandle();
      }

      ByteBuf var4 = var2.getAllocator().heapBuffer(var3.guess());
      boolean var5 = true;

      int var18;
      try {
         this.tmpPacket.setData(var4.array(), var4.arrayOffset(), var4.capacity());
         this.socket.receive(this.tmpPacket);
         InetSocketAddress var6 = (InetSocketAddress)this.tmpPacket.getSocketAddress();
         var18 = this.tmpPacket.getLength();
         var3.record(var18);
         var1.add(new io.netty.channel.socket.DatagramPacket(var4.writerIndex(var18), this.localAddress(), var6));
         var5 = false;
         return 1;
      } catch (SocketTimeoutException var14) {
         return 0;
      } catch (SocketException var15) {
         if (!var15.getMessage().toLowerCase(Locale.US).contains("socket closed")) {
            throw var15;
         }

         var18 = -1;
      } catch (Throwable var16) {
         PlatformDependent.throwException(var16);
         return -1;
      } finally {
         if (var5) {
            var4.release();
         }
      }

      return var18;
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1, ChannelPromise var2) {
      this.ensureBound();

      try {
         this.socket.joinGroup(var1);
         var2.setSuccess();
      } catch (IOException var4) {
         var2.setFailure(var4);
      }

      return var2;
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1, ChannelPromise var2) {
      try {
         this.socket.leaveGroup(var1);
         var2.setSuccess();
      } catch (IOException var4) {
         var2.setFailure(var4);
      }

      return var2;
   }

   public OioDatagramChannel() {
      this(newSocket());
   }
}
