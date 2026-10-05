package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker07;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.CommonsLoggerFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import javazoom.jl.converter.WaveFileObuffer;
import net.minecraft.block.BlockTorch;
import net.minecraft.item.ItemExpBottle;
import com.cheatbreaker.client.util.input.ToggleSprintMovementInput;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.layer.GenLayerAddIsland;

public class EpollDatagramChannel extends AbstractEpollChannel implements DatagramChannel {
   public volatile boolean connected;
   public volatile InetSocketAddress remote;
   public volatile InetSocketAddress local;
   public static ChannelMetadata METADATA = new ChannelMetadata(true);
   public EpollDatagramChannelConfig config = new EpollDatagramChannelConfig(this);
   public static String EXPECTED_TYPES = " (expected: "
      + StringUtil.simpleClassName(DatagramPacket.class)
      + ", "
      + StringUtil.simpleClassName(AddressedEnvelope.class)
      + '<'
      + StringUtil.simpleClassName(ByteBuf.class)
      + ", "
      + StringUtil.simpleClassName(InetSocketAddress.class)
      + ">, "
      + StringUtil.simpleClassName(ByteBuf.class)
      + ')';

   public InetSocketAddress localAddress0() {
      return this.local;
   }

   @Override
   public ChannelFuture joinGroup(InetSocketAddress var1, NetworkInterface var2) {
      return this.joinGroup(var1, var2, this.newPromise());
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1, NetworkInterface var2, InetAddress var3, ChannelPromise var4) {
      if (var1 == null) {
         throw new NullPointerException("multicastAddress");
      } else if (var2 == null) {
         throw new NullPointerException("networkInterface");
      } else {
         var4.setFailure(new UnsupportedOperationException("Multicast not supported"));
         return var4;
      }
   }

   public EpollDatagramChannelConfig config() {
      return this.config;
   }

   @Override
   public ChannelFuture leaveGroup(InetSocketAddress var1, NetworkInterface var2, ChannelPromise var3) {
      return this.leaveGroup(var1.getAddress(), var2, null, var3);
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      this.connected = false;
   }

   public InetSocketAddress remoteAddress0() {
      return this.remote;
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      InetSocketAddress var2 = (InetSocketAddress)var1;
      checkResolvable(var2);
      Native.bind(this.fd, var2.getAddress(), var2.getPort());
      this.local = Native.localAddress(this.fd);
      this.active = true;
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      if (var1 instanceof DatagramPacket) {
         DatagramPacket var5 = (DatagramPacket)var1;
         ByteBuf var6 = var5.content();
         return var6.hasMemoryAddress() ? var1 : new DatagramPacket(this.newDirectBuffer(var5, var6), var5.recipient());
      } else if (var1 instanceof ByteBuf) {
         ByteBuf var4 = (ByteBuf)var1;
         return var4.hasMemoryAddress() ? var1 : this.newDirectBuffer(var4);
      } else {
         if (var1 instanceof AddressedEnvelope) {
            AddressedEnvelope var2 = (AddressedEnvelope)var1;
            if (var2.content() instanceof ByteBuf && (var2.recipient() == null || var2.recipient() instanceof InetSocketAddress)) {
               ByteBuf var3 = (ByteBuf)var2.content();
               if (var3.hasMemoryAddress()) {
                  return var2;
               }

               return new DefaultAddressedEnvelope<>(this.newDirectBuffer(var2, var3), (InetSocketAddress)var2.recipient());
            }
         }

         throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(var1) + EXPECTED_TYPES);
      }
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1, NetworkInterface var2, InetAddress var3) {
      return this.joinGroup(var1, var2, var3, this.newPromise());
   }

   @Override
   public boolean isActive() {
      return this.fd != -1 && (this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) && this.isRegistered() || this.active);
   }

   public boolean doWriteMessage(Object var1) throws java.io.IOException {
      ByteBuf var2;
      InetSocketAddress var3;
      if (var1 instanceof AddressedEnvelope) {
         AddressedEnvelope var4 = (AddressedEnvelope)var1;
         var2 = (ByteBuf)var4.content();
         var3 = (InetSocketAddress)var4.recipient();
      } else {
         var2 = (ByteBuf)var1;
         var3 = null;
      }

      int var8 = var2.readableBytes();
      if (var8 == 0) {
         return true;
      } else {
         if (var3 == null) {
            var3 = this.remote;
            if (var3 == null) {
               throw new NotYetConnectedException();
            }
         }

         int var5;
         if (var2.hasMemoryAddress()) {
            long var6 = var2.memoryAddress();
            var5 = Native.sendToAddress(this.fd, var6, var2.readerIndex(), var2.writerIndex(), var3.getAddress(), var3.getPort());
         } else {
            ByteBuffer var9 = var2.internalNioBuffer(var2.readerIndex(), var2.readableBytes());
            var5 = Native.sendTo(this.fd, var9, var9.position(), var9.limit(), var3.getAddress(), var3.getPort());
         }

         return var5 > 0;
      }
   }

   @Override
   public ChannelFuture block(InetAddress var1, NetworkInterface var2, InetAddress var3, ChannelPromise var4) {
      if (var1 == null) {
         throw new NullPointerException("multicastAddress");
      } else if (var3 == null) {
         throw new NullPointerException("sourceToBlock");
      } else if (var2 == null) {
         throw new NullPointerException("networkInterface");
      } else {
         var4.setFailure(new UnsupportedOperationException("Multicast not supported"));
         return var4;
      }
   }

   @Override
   public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
      return new EpollDatagramChannel.EpollDatagramChannelUnsafe();
   }

   @Override
   public boolean isConnected() {
      return this.connected;
   }

   @Override
   public ChannelFuture block(InetAddress var1, NetworkInterface var2, InetAddress var3) {
      return this.block(var1, var2, var3, this.newPromise());
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1, ChannelPromise var2) {
      try {
         return this.leaveGroup(var1, NetworkInterface.getByInetAddress(this.localAddress().getAddress()), null, var2);
      } catch (SocketException var4) {
         var2.setFailure(var4);
         return var2;
      }
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public ChannelFuture block(InetAddress var1, InetAddress var2, ChannelPromise var3) {
      try {
         return this.block(var1, NetworkInterface.getByInetAddress(this.localAddress().getAddress()), var2, var3);
      } catch (Throwable var5) {
         var3.setFailure(var5);
         return var3;
      }
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1, NetworkInterface var2, InetAddress var3) {
      return this.leaveGroup(var1, var2, var3, this.newPromise());
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      while (true) {
         Object var2 = var1.current();
         if (var2 == null) {
            this.clearEpollOut();
         } else {
            try {
               boolean var3 = false;

               for (int var4 = this.config().getWriteSpinCount() - 1; var4 >= 0; var4--) {
                  if (this.doWriteMessage(var2)) {
                     var3 = true;
                     break;
                  }
               }

               if (var3) {
                  var1.remove();
                  continue;
               }

               this.setEpollOut();
            } catch (IOException var5) {
               var1.remove(var5);
               continue;
            }
         }

         return;
      }
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1) {
      return this.leaveGroup(var1, this.newPromise());
   }

   @Override
   public ChannelFuture leaveGroup(InetSocketAddress var1, NetworkInterface var2) {
      return this.leaveGroup(var1, var2, this.newPromise());
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1, ChannelPromise var2) {
      try {
         return this.joinGroup(var1, NetworkInterface.getByInetAddress(this.localAddress().getAddress()), null, var2);
      } catch (SocketException var4) {
         var2.setFailure(var4);
         return var2;
      }
   }

   public EpollDatagramChannel() {
      super(Native.socketDgramFd(), 1);
   }

   @Override
   public ChannelFuture leaveGroup(InetAddress var1, NetworkInterface var2, InetAddress var3, ChannelPromise var4) {
      if (var1 == null) {
         throw new NullPointerException("multicastAddress");
      } else if (var2 == null) {
         throw new NullPointerException("networkInterface");
      } else {
         var4.setFailure(new UnsupportedOperationException("Multicast not supported"));
         return var4;
      }
   }

   @Override
   public ChannelFuture joinGroup(InetAddress var1) {
      return this.joinGroup(var1, this.newPromise());
   }

   @Override
   public ChannelFuture joinGroup(InetSocketAddress var1, NetworkInterface var2, ChannelPromise var3) {
      return this.joinGroup(var1.getAddress(), var2, null, var3);
   }

   @Override
   public ChannelFuture block(InetAddress var1, InetAddress var2) {
      return this.block(var1, var2, this.newPromise());
   }

   public static final class DatagramSocketAddress extends InetSocketAddress {
      public int receivedAmount;
      public static final long serialVersionUID = 1348596211215015739L;

      public DatagramSocketAddress(String var1, int var2, int var3) {
         super(var1, var2);
         this.receivedAmount = var3;
      }
   }

   public final class EpollDatagramChannelUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
      // $VF: synthetic field
      public final boolean $assertionsDisabled = !EpollDatagramChannel.class.desiredAssertionStatus();
      public RecvByteBufAllocator.Handle allocHandle;

      @Override
      public void epollInReady() {
         EpollDatagramChannelConfig var1 = EpollDatagramChannel.this.config();
         RecvByteBufAllocator.Handle var2 = this.allocHandle;
         if (var2 == null) {
            this.allocHandle = var2 = var1.getRecvByteBufAllocator().newHandle();
         }

         if (!$assertionsDisabled && !EpollDatagramChannel.this.eventLoop().inEventLoop()) {
            throw new AssertionError();
         } else {
            ChannelPipeline var3 = EpollDatagramChannel.this.pipeline();

            try {
               while (true) {
                  ByteBuf var4 = null;

                  try {
                     var4 = var2.allocate(var1.getAllocator());
                     int var5 = var4.writerIndex();
                     EpollDatagramChannel.DatagramSocketAddress var6;
                     if (var4.hasMemoryAddress()) {
                        var6 = Native.recvFromAddress(EpollDatagramChannel.this.fd, var4.memoryAddress(), var5, var4.capacity());
                     } else {
                        ByteBuffer var7 = var4.internalNioBuffer(var5, var4.writableBytes());
                        var6 = Native.recvFrom(EpollDatagramChannel.this.fd, var7, var7.position(), var7.limit());
                     }

                     if (var6 == null) {
                        return;
                     }

                     int var19 = var6.receivedAmount;
                     var4.writerIndex(var4.writerIndex() + var19);
                     var2.record(var19);
                     this.readPending = false;
                     var3.fireChannelRead(new DatagramPacket(var4, (InetSocketAddress)this.localAddress(), var6));
                     var4 = null;
                  } catch (Throwable var16) {
                     var3.fireChannelReadComplete();
                     var3.fireExceptionCaught(var16);
                  } finally {
                     if (var4 != null) {
                        var4.release();
                     }
                  }
               }
            } finally {
               if (!EpollDatagramChannel.this.config().isAutoRead() && !this.readPending) {
                  EpollDatagramChannel.this.clearEpollIn();
               }
            }
         }
      }

      @Override
      public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
         boolean var4 = false;

         try {
            try {
               InetSocketAddress var5 = (InetSocketAddress)var1;
               if (var2 != null) {
                  InetSocketAddress var6 = (InetSocketAddress)var2;
                  EpollDatagramChannel.this.doBind(var6);
               }

               AbstractEpollChannel.checkResolvable(var5);
               EpollDatagramChannel.this.remote = var5;
               EpollDatagramChannel.this.local = Native.localAddress(EpollDatagramChannel.this.fd);
               var4 = true;
            } finally {
               if (!var4) {
                  EpollDatagramChannel.this.doClose();
               } else {
                  var3.setSuccess();
                  EpollDatagramChannel.this.connected = true;
               }
            }
         } catch (Throwable var11) {
            var3.setFailure(var11);
         }
      }
   }
}
