package io.netty.channel.socket.oio;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.DefaultSocketChannelConfig;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.JdkSslServerContext;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import net.minecraft.client.model.ModelHumanoidHead;
import net.minecraft.client.multiplayer.WorldClient$2;
import net.minecraft.entity.DataWatcher;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.world.biome.BiomeDecorator;

public class DefaultOioSocketChannelConfig extends DefaultSocketChannelConfig implements OioSocketChannelConfig {
   public ModelHumanoidHead __junk7218348519116870126;
   public DataWatcher __junk4500160021464894066;
   public TileEntityEnchantmentTable __junk9108433322229314643;
   public JdkSslServerContext __junk4003555666792836459;
   public BiomeDecorator __junk834566069926219096;
   public WorldClient$2 __junk7336252755120862002;

   public DefaultOioSocketChannelConfig(SocketChannel var1, Socket var2) {
      super(var1, var2);
   }

   @Override
   public OioSocketChannelConfig setPerformancePreferences(int var1, int var2, int var3) {
      super.setPerformancePreferences(var1, var2, var3);
      return this;
   }

   @Override
   public OioSocketChannelConfig setAllowHalfClosure(boolean var1) {
      super.setAllowHalfClosure(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setSoLinger(int var1) {
      super.setSoLinger(var1);
      return this;
   }

   @Override
   public int getSoTimeout() {
      try {
         return this.javaSocket.getSoTimeout();
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public OioSocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setTcpNoDelay(boolean var1) {
      super.setTcpNoDelay(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   public DefaultOioSocketChannelConfig(OioSocketChannel var1, Socket var2) {
      super(var1, var2);
   }

   @Override
   public OioSocketChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(super.getOptions(), ChannelOption.SO_TIMEOUT);
   }

   @Override
   public OioSocketChannelConfig setReuseAddress(boolean var1) {
      super.setReuseAddress(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setTrafficClass(int var1) {
      super.setTrafficClass(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setKeepAlive(boolean var1) {
      super.setKeepAlive(var1);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      return (T)(var1 == ChannelOption.SO_TIMEOUT ? this.getSoTimeout() : super.getOption(var1));
   }

   @Override
   public OioSocketChannelConfig setReceiveBufferSize(int var1) {
      super.setReceiveBufferSize(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public void autoReadCleared() {
      if (this.channel instanceof OioSocketChannel) {
         ((OioSocketChannel)this.channel).setReadPending(false);
      }
   }

   @Override
   public OioSocketChannelConfig setSendBufferSize(int var1) {
      super.setSendBufferSize(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
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
   public OioSocketChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public OioSocketChannelConfig setSoTimeout(int var1) {
      try {
         this.javaSocket.setSoTimeout(var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }
}
