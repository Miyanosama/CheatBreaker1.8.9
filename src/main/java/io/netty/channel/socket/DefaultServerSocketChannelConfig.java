package io.netty.channel.socket;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.NetUtil;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Map;
import net.minecraft.command.CommandStats;
import net.minecraft.command.server.CommandBanIp;
import net.minecraft.world.gen.structure.StructureBoundingBox$EnumSwitch;

public class DefaultServerSocketChannelConfig extends DefaultChannelConfig implements ServerSocketChannelConfig {
   public ServerSocket javaSocket;
   public volatile int backlog = NetUtil.SOMAXCONN;

   public ServerSocketChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public ServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public ServerSocketChannelConfig setReuseAddress(boolean var1) {
      try {
         this.javaSocket.setReuseAddress(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public ServerSocketChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(super.getOptions(), ChannelOption.SO_RCVBUF, ChannelOption.SO_REUSEADDR, ChannelOption.SO_BACKLOG);
   }

   @Override
   public ServerSocketChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public ServerSocketChannelConfig setPerformancePreferences(int var1, int var2, int var3) {
      this.javaSocket.setPerformancePreferences(var1, var2, var3);
      return this;
   }

   public ServerSocketChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public ServerSocketChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == ChannelOption.SO_RCVBUF) {
         return (T)(Object)this.getReceiveBufferSize();
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         return (T)(Object)this.isReuseAddress();
      } else {
         return (T)(var1 == ChannelOption.SO_BACKLOG ? this.getBacklog() : super.getOption(var1));
      }
   }

   @Override
   public ServerSocketChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public ServerSocketChannelConfig setBacklog(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("backlog: " + var1);
      } else {
         this.backlog = var1;
         return this;
      }
   }

   @Override
   public int getBacklog() {
      return this.backlog;
   }

   @Override
   public ServerSocketChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public ServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public int getReceiveBufferSize() {
      try {
         return this.javaSocket.getReceiveBufferSize();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public boolean isReuseAddress() {
      try {
         return this.javaSocket.getReuseAddress();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public ServerSocketChannelConfig setReceiveBufferSize(int var1) {
      try {
         this.javaSocket.setReceiveBufferSize(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)var2);
      } else {
         if (var1 != ChannelOption.SO_BACKLOG) {
            return super.setOption(var1, var2);
         }

         this.setBacklog((Integer)var2);
      }

      return true;
   }

   public DefaultServerSocketChannelConfig(ServerSocketChannel var1, ServerSocket var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("javaSocket");
      } else {
         this.javaSocket = var2;
      }
   }
}
