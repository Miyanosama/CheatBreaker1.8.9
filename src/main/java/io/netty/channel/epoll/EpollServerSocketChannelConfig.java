package io.netty.channel.epoll;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.util.NetUtil;
import io.netty.util.internal.chmv8.ForkJoinWorkerThread;
import java.util.Map;
import org.apache.log4j.NDC;

public class EpollServerSocketChannelConfig extends DefaultChannelConfig implements ServerSocketChannelConfig {
   public volatile int backlog = NetUtil.SOMAXCONN;
   public EpollServerSocketChannel channel;

   public EpollServerSocketChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public int getReceiveBufferSize() {
      return Native.getReceiveBufferSize(this.channel.fd);
   }

   public boolean isReusePort() {
      return Native.isReusePort(this.channel.fd) == 1;
   }

   @Override
   public int getBacklog() {
      return this.backlog;
   }

   public EpollServerSocketChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public boolean isReuseAddress() {
      return Native.isReuseAddress(this.channel.fd) == 1;
   }

   public EpollServerSocketChannelConfig setReceiveBufferSize(int var1) {
      Native.setReceiveBufferSize(this.channel.fd, var1);
      return this;
   }

   public EpollServerSocketChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   public EpollServerSocketChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   public EpollServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   public EpollServerSocketChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   public EpollServerSocketChannelConfig(EpollServerSocketChannel var1) {
      super(var1);
      this.channel = var1;
      this.setReuseAddress(true);
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)var2);
      } else if (var1 == ChannelOption.SO_BACKLOG) {
         this.setBacklog((Integer)var2);
      } else {
         if (var1 != EpollChannelOption.SO_REUSEPORT) {
            return super.setOption(var1, var2);
         }

         this.setReusePort((Boolean)var2);
      }

      return true;
   }

   public EpollServerSocketChannelConfig setReuseAddress(boolean var1) {
      Native.setReuseAddress(this.channel.fd, var1 ? 1 : 0);
      return this;
   }

   public EpollServerSocketChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   public EpollServerSocketChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   public EpollServerSocketChannelConfig setBacklog(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("backlog: " + var1);
      } else {
         this.backlog = var1;
         return this;
      }
   }

   @Override
   public void autoReadCleared() {
      this.channel.clearEpollIn();
   }

   public EpollServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   public EpollServerSocketChannelConfig setReusePort(boolean var1) {
      Native.setReusePort(this.channel.fd, var1 ? 1 : 0);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == ChannelOption.SO_RCVBUF) {
         return (T)(Object)this.getReceiveBufferSize();
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         return (T)(Object)this.isReuseAddress();
      } else if (var1 == ChannelOption.SO_BACKLOG) {
         return (T)(Object)this.getBacklog();
      } else {
         return (T)(var1 == EpollChannelOption.SO_REUSEPORT ? this.isReusePort() : super.getOption(var1));
      }
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(super.getOptions(), ChannelOption.SO_RCVBUF, ChannelOption.SO_REUSEADDR, ChannelOption.SO_BACKLOG, EpollChannelOption.SO_REUSEPORT);
   }

   public EpollServerSocketChannelConfig setPerformancePreferences(int var1, int var2, int var3) {
      return this;
   }
}
