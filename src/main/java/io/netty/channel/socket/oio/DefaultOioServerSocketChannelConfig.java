package io.netty.channel.socket.oio;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.DefaultServerSocketChannelConfig;
import io.netty.channel.socket.ServerSocketChannel;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import net.minecraft.client.particle.EntityAuraFX;

public class DefaultOioServerSocketChannelConfig extends DefaultServerSocketChannelConfig implements OioServerSocketChannelConfig {

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(super.getOptions(), ChannelOption.SO_TIMEOUT);
   }

   @Override
   public OioServerSocketChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      return (T)(var1 == ChannelOption.SO_TIMEOUT ? this.getSoTimeout() : super.getOption(var1));
   }

   public DefaultOioServerSocketChannelConfig(OioServerSocketChannel var1, ServerSocket var2) {
      super(var1, var2);
   }

   @Override
   public int getSoTimeout() {
      try {
         return this.javaSocket.getSoTimeout();
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   public DefaultOioServerSocketChannelConfig(ServerSocketChannel var1, ServerSocket var2) {
      super(var1, var2);
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_TIMEOUT) {
         this.setSoTimeout((Integer)var2);
         return true;
      } else {
         return super.setOption(var1, var2);
      }
   }

   @Override
   public OioServerSocketChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setBacklog(int var1) {
      super.setBacklog(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setSoTimeout(int var1) {
      try {
         this.javaSocket.setSoTimeout(var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public void autoReadCleared() {
      if (this.channel instanceof OioServerSocketChannel) {
         ((OioServerSocketChannel)this.channel).setReadPending(false);
      }
   }

   @Override
   public OioServerSocketChannelConfig setReceiveBufferSize(int var1) {
      super.setReceiveBufferSize(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setReuseAddress(boolean var1) {
      super.setReuseAddress(var1);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setPerformancePreferences(int var1, int var2, int var3) {
      super.setPerformancePreferences(var1, var2, var3);
      return this;
   }

   @Override
   public OioServerSocketChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }
}
