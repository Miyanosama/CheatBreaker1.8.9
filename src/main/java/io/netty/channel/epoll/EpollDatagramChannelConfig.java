package io.netty.channel.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.DatagramChannelConfig;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;
import javazoom.jl.decoder.LayerIIIDecoder;
import junit.swingui.TestSelector$3;
import net.minecraft.realms.RealmsServerPing;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$19;

public class EpollDatagramChannelConfig extends DefaultChannelConfig implements DatagramChannelConfig {
   public EpollDatagramChannel datagramChannel;
   public static RecvByteBufAllocator DEFAULT_RCVBUF_ALLOCATOR = new FixedRecvByteBufAllocator(2048);
   public boolean activeOnOpen;

   @Override
   public DatagramChannelConfig setLoopbackModeDisabled(boolean var1) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public boolean isReusePort() {
      return Native.isReusePort(this.datagramChannel.fd) == 1;
   }

   public EpollDatagramChannelConfig setSendBufferSize(int var1) {
      Native.setSendBufferSize(this.datagramChannel.fd, var1);
      return this;
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_BROADCAST) {
         this.setBroadcast((Boolean)var2);
      } else if (var1 == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)var2);
      } else if (var1 == ChannelOption.IP_MULTICAST_LOOP_DISABLED) {
         this.setLoopbackModeDisabled((Boolean)var2);
      } else if (var1 == ChannelOption.IP_MULTICAST_ADDR) {
         this.setInterface((InetAddress)var2);
      } else if (var1 == ChannelOption.IP_MULTICAST_IF) {
         this.setNetworkInterface((NetworkInterface)var2);
      } else if (var1 == ChannelOption.IP_MULTICAST_TTL) {
         this.setTimeToLive((Integer)var2);
      } else if (var1 == ChannelOption.IP_TOS) {
         this.setTrafficClass((Integer)var2);
      } else if (var1 == ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) {
         this.setActiveOnOpen((Boolean)var2);
      } else {
         if (var1 != EpollChannelOption.SO_REUSEPORT) {
            return super.setOption(var1, var2);
         }

         this.setReusePort((Boolean)var2);
      }

      return true;
   }

   public EpollDatagramChannelConfig setReceiveBufferSize(int var1) {
      Native.setReceiveBufferSize(this.datagramChannel.fd, var1);
      return this;
   }

   public EpollDatagramChannelConfig setReusePort(boolean var1) {
      Native.setReusePort(this.datagramChannel.fd, var1 ? 1 : 0);
      return this;
   }

   @Override
   public int getTimeToLive() {
      return -1;
   }

   public EpollDatagramChannelConfig setBroadcast(boolean var1) {
      Native.setBroadcast(this.datagramChannel.fd, var1 ? 1 : 0);
      return this;
   }

   @Override
   public int getReceiveBufferSize() {
      return Native.getReceiveBufferSize(this.datagramChannel.fd);
   }

   public EpollDatagramChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   public EpollDatagramChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   public EpollDatagramChannelConfig(EpollDatagramChannel var1) {
      super(var1);
      this.datagramChannel = var1;
      this.setRecvByteBufAllocator(DEFAULT_RCVBUF_ALLOCATOR);
   }

   public EpollDatagramChannelConfig setNetworkInterface(NetworkInterface var1) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public EpollDatagramChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == ChannelOption.SO_BROADCAST) {
         return (T)(Object)this.isBroadcast();
      } else if (var1 == ChannelOption.SO_RCVBUF) {
         return (T)(Object)this.getReceiveBufferSize();
      } else if (var1 == ChannelOption.SO_SNDBUF) {
         return (T)(Object)this.getSendBufferSize();
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         return (T)(Object)this.isReuseAddress();
      } else if (var1 == ChannelOption.IP_MULTICAST_LOOP_DISABLED) {
         return (T)(Object)this.isLoopbackModeDisabled();
      } else if (var1 == ChannelOption.IP_MULTICAST_ADDR) {
         return (T)this.getInterface();
      } else if (var1 == ChannelOption.IP_MULTICAST_IF) {
         return (T)this.getNetworkInterface();
      } else if (var1 == ChannelOption.IP_MULTICAST_TTL) {
         return (T)(Object)this.getTimeToLive();
      } else if (var1 == ChannelOption.IP_TOS) {
         return (T)(Object)this.getTrafficClass();
      } else if (var1 == ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION) {
         return (T)(Object)this.activeOnOpen;
      } else {
         return (T)(var1 == EpollChannelOption.SO_REUSEPORT ? this.isReusePort() : super.getOption(var1));
      }
   }

   public EpollDatagramChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   public void setActiveOnOpen(boolean var1) {
      if (this.channel.isRegistered()) {
         throw new IllegalStateException("Can only changed before channel was registered");
      } else {
         this.activeOnOpen = var1;
      }
   }

   @Override
   public NetworkInterface getNetworkInterface() {
      return null;
   }

   public EpollDatagramChannelConfig setTrafficClass(int var1) {
      Native.setTrafficClass(this.datagramChannel.fd, var1);
      return this;
   }

   public EpollDatagramChannelConfig setTimeToLive(int var1) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public EpollDatagramChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public void autoReadCleared() {
      this.datagramChannel.clearEpollIn();
   }

   @Override
   public boolean isLoopbackModeDisabled() {
      return false;
   }

   @Override
   public boolean isBroadcast() {
      return Native.isBroadcast(this.datagramChannel.fd) == 1;
   }

   @Override
   public int getSendBufferSize() {
      return Native.getSendBufferSize(this.datagramChannel.fd);
   }

   @Override
   public int getTrafficClass() {
      return Native.getTrafficClass(this.datagramChannel.fd);
   }

   public EpollDatagramChannelConfig setInterface(InetAddress var1) {
      throw new UnsupportedOperationException("Multicast not supported");
   }

   public EpollDatagramChannelConfig setReuseAddress(boolean var1) {
      Native.setReuseAddress(this.datagramChannel.fd, var1 ? 1 : 0);
      return this;
   }

   public EpollDatagramChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   public EpollDatagramChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(
         super.getOptions(),
         ChannelOption.SO_BROADCAST,
         ChannelOption.SO_RCVBUF,
         ChannelOption.SO_SNDBUF,
         ChannelOption.SO_REUSEADDR,
         ChannelOption.IP_MULTICAST_LOOP_DISABLED,
         ChannelOption.IP_MULTICAST_ADDR,
         ChannelOption.IP_MULTICAST_IF,
         ChannelOption.IP_MULTICAST_TTL,
         ChannelOption.IP_TOS,
         ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION,
         EpollChannelOption.SO_REUSEPORT
      );
   }

   @Override
   public boolean isReuseAddress() {
      return Native.isReuseAddress(this.datagramChannel.fd) == 1;
   }

   public EpollDatagramChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public InetAddress getInterface() {
      return null;
   }

   public EpollDatagramChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   public EpollDatagramChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }
}
