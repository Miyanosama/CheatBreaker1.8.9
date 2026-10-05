package io.netty.channel.udt;

import com.barchart.udt.nio.ChannelUDT;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import java.util.Map;
import net.minecraft.block.BlockSand;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class DefaultUdtServerChannelConfig extends DefaultUdtChannelConfig implements UdtServerChannelConfig {
   public volatile int backlog = 64;

   @Override
   public UdtServerChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setReceiveBufferSize(int var1) {
      super.setReceiveBufferSize(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setSystemReceiveBufferSize(int var1) {
      super.setSystemReceiveBufferSize(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public void apply(ChannelUDT var1) throws java.io.IOException {
   }

   @Override
   public int getBacklog() {
      return this.backlog;
   }

   @Override
   public UdtServerChannelConfig setBacklog(int var1) {
      this.backlog = var1;
      return this;
   }

   @Override
   public UdtServerChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      return (T)(var1 == ChannelOption.SO_BACKLOG ? this.getBacklog() : super.getOption(var1));
   }

   @Override
   public UdtServerChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   public DefaultUdtServerChannelConfig(UdtChannel var1, ChannelUDT var2, boolean var3) throws java.io.IOException {
      super(var1, var2, var3);
      if (var3) {
         this.apply(var2);
      }
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_BACKLOG) {
         this.setBacklog((Integer)var2);
         return true;
      } else {
         return super.setOption(var1, var2);
      }
   }

   @Override
   public UdtServerChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setProtocolSendBufferSize(int var1) {
      super.setProtocolSendBufferSize(var1);
      return this;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(super.getOptions(), ChannelOption.SO_BACKLOG);
   }

   @Override
   public UdtServerChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setSystemSendBufferSize(int var1) {
      super.setSystemSendBufferSize(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setSendBufferSize(int var1) {
      super.setSendBufferSize(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setSoLinger(int var1) {
      super.setSoLinger(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setProtocolReceiveBufferSize(int var1) {
      super.setProtocolReceiveBufferSize(var1);
      return this;
   }

   @Override
   public UdtServerChannelConfig setReuseAddress(boolean var1) {
      super.setReuseAddress(var1);
      return this;
   }
}
