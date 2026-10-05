package io.netty.channel.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.internal.PlatformDependent;
import java.util.Map;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.item.EnumAction;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;

public class EpollSocketChannelConfig extends DefaultChannelConfig implements SocketChannelConfig {
   public EpollSocketChannel channel;
   public volatile boolean allowHalfClosure;

   public EpollSocketChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   public EpollSocketChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   public EpollSocketChannelConfig setTcpKeepCntl(int var1) {
      Native.setTcpKeepCnt(this.channel.fd, var1);
      return this;
   }

   public EpollSocketChannelConfig setReceiveBufferSize(int var1) {
      Native.setReceiveBufferSize(this.channel.fd, var1);
      return this;
   }

   public EpollSocketChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   public EpollSocketChannelConfig setAllowHalfClosure(boolean var1) {
      this.allowHalfClosure = var1;
      return this;
   }

   public EpollSocketChannelConfig setSendBufferSize(int var1) {
      Native.setSendBufferSize(this.channel.fd, var1);
      return this;
   }

   public boolean isTcpCork() {
      return Native.isTcpCork(this.channel.fd) == 1;
   }

   public EpollSocketChannelConfig setKeepAlive(boolean var1) {
      Native.setKeepAlive(this.channel.fd, var1 ? 1 : 0);
      return this;
   }

   @Override
   public int getSoLinger() {
      return Native.getSoLinger(this.channel.fd);
   }

   public EpollSocketChannelConfig setTcpCork(boolean var1) {
      Native.setTcpCork(this.channel.fd, var1 ? 1 : 0);
      return this;
   }

   public EpollSocketChannelConfig setTcpNoDelay(boolean var1) {
      Native.setTcpNoDelay(this.channel.fd, var1 ? 1 : 0);
      return this;
   }

   public EpollSocketChannelConfig(EpollSocketChannel var1) {
      super(var1);
      this.channel = var1;
      if (PlatformDependent.canEnableTcpNoDelayByDefault()) {
         this.setTcpNoDelay(true);
      }
   }

   public EpollSocketChannelConfig setTrafficClass(int var1) {
      Native.setTrafficClass(this.channel.fd, var1);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == ChannelOption.SO_RCVBUF) {
         return (T)(Object)this.getReceiveBufferSize();
      } else if (var1 == ChannelOption.SO_SNDBUF) {
         return (T)(Object)this.getSendBufferSize();
      } else if (var1 == ChannelOption.TCP_NODELAY) {
         return (T)(Object)this.isTcpNoDelay();
      } else if (var1 == ChannelOption.SO_KEEPALIVE) {
         return (T)(Object)this.isKeepAlive();
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         return (T)(Object)this.isReuseAddress();
      } else if (var1 == ChannelOption.SO_LINGER) {
         return (T)(Object)this.getSoLinger();
      } else if (var1 == ChannelOption.IP_TOS) {
         return (T)(Object)this.getTrafficClass();
      } else if (var1 == ChannelOption.ALLOW_HALF_CLOSURE) {
         return (T)(Object)this.isAllowHalfClosure();
      } else if (var1 == EpollChannelOption.TCP_CORK) {
         return (T)(Object)this.isTcpCork();
      } else if (var1 == EpollChannelOption.TCP_KEEPIDLE) {
         return (T)(Object)this.getTcpKeepIdle();
      } else if (var1 == EpollChannelOption.TCP_KEEPINTVL) {
         return (T)(Object)this.getTcpKeepIntvl();
      } else {
         return (T)(var1 == EpollChannelOption.TCP_KEEPCNT ? this.getTcpKeepCnt() : super.getOption(var1));
      }
   }

   public EpollSocketChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public boolean isReuseAddress() {
      return Native.isReuseAddress(this.channel.fd) == 1;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(
         super.getOptions(),
         ChannelOption.SO_RCVBUF,
         ChannelOption.SO_SNDBUF,
         ChannelOption.TCP_NODELAY,
         ChannelOption.SO_KEEPALIVE,
         ChannelOption.SO_REUSEADDR,
         ChannelOption.SO_LINGER,
         ChannelOption.IP_TOS,
         ChannelOption.ALLOW_HALF_CLOSURE,
         EpollChannelOption.TCP_CORK,
         EpollChannelOption.TCP_KEEPCNT,
         EpollChannelOption.TCP_KEEPIDLE,
         EpollChannelOption.TCP_KEEPINTVL
      );
   }

   public EpollSocketChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public boolean isKeepAlive() {
      return Native.isKeepAlive(this.channel.fd) == 1;
   }

   public EpollSocketChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public int getReceiveBufferSize() {
      return Native.getReceiveBufferSize(this.channel.fd);
   }

   public EpollSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   public EpollSocketChannelConfig setPerformancePreferences(int var1, int var2, int var3) {
      return this;
   }

   public EpollSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   public int getTcpKeepIdle() {
      return Native.getTcpKeepIdle(this.channel.fd);
   }

   public int getTcpKeepIntvl() {
      return Native.getTcpKeepIntvl(this.channel.fd);
   }

   public EpollSocketChannelConfig setSoLinger(int var1) {
      Native.setSoLinger(this.channel.fd, var1);
      return this;
   }

   public EpollSocketChannelConfig setTcpKeepIdle(int var1) {
      Native.setTcpKeepIdle(this.channel.fd, var1);
      return this;
   }

   @Override
   public boolean isAllowHalfClosure() {
      return this.allowHalfClosure;
   }

   public EpollSocketChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public void autoReadCleared() {
      this.channel.clearEpollIn();
   }

   public int getTcpKeepCnt() {
      return Native.getTcpKeepCnt(this.channel.fd);
   }

   public EpollSocketChannelConfig setTcpKeepIntvl(int var1) {
      Native.setTcpKeepIntvl(this.channel.fd, var1);
      return this;
   }

   @Override
   public int getSendBufferSize() {
      return Native.getSendBufferSize(this.channel.fd);
   }

   @Override
   public int getTrafficClass() {
      return Native.getTrafficClass(this.channel.fd);
   }

   @Override
   public boolean isTcpNoDelay() {
      return Native.isTcpNoDelay(this.channel.fd) == 1;
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.TCP_NODELAY) {
         this.setTcpNoDelay((Boolean)var2);
      } else if (var1 == ChannelOption.SO_KEEPALIVE) {
         this.setKeepAlive((Boolean)var2);
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)var2);
      } else if (var1 == ChannelOption.SO_LINGER) {
         this.setSoLinger((Integer)var2);
      } else if (var1 == ChannelOption.IP_TOS) {
         this.setTrafficClass((Integer)var2);
      } else if (var1 == ChannelOption.ALLOW_HALF_CLOSURE) {
         this.setAllowHalfClosure((Boolean)var2);
      } else if (var1 == EpollChannelOption.TCP_CORK) {
         this.setTcpCork((Boolean)var2);
      } else if (var1 == EpollChannelOption.TCP_KEEPIDLE) {
         this.setTcpKeepIdle((Integer)var2);
      } else if (var1 == EpollChannelOption.TCP_KEEPCNT) {
         this.setTcpKeepCntl((Integer)var2);
      } else {
         if (var1 != EpollChannelOption.TCP_KEEPINTVL) {
            return super.setOption(var1, var2);
         }

         this.setTcpKeepIntvl((Integer)var2);
      }

      return true;
   }

   public EpollSocketChannelConfig setReuseAddress(boolean var1) {
      Native.setReuseAddress(this.channel.fd, var1 ? 1 : 0);
      return this;
   }

   public EpollSocketChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }
}
