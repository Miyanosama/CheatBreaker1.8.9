package io.netty.channel.udt;

import com.barchart.udt.OptionUDT;
import com.barchart.udt.SocketUDT;
import com.barchart.udt.nio.ChannelUDT;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.handler.codec.http.cors.CorsConfig;
import java.util.Map;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.block.model.BlockFaceUV;

public class DefaultUdtChannelConfig extends DefaultChannelConfig implements UdtChannelConfig {
   public volatile int protocolReceiveBuferSize = 10485760;
   public volatile int systemSendBuferSize;
   public static final int K = 1024;
   public volatile int allocatorSendBufferSize;
   public static final int M = 1048576;
   public volatile int protocolSendBuferSize = 10485760;
   public volatile boolean reuseAddress;
   public volatile int allocatorReceiveBufferSize;
   public volatile int soLinger;
   public volatile int systemReceiveBufferSize = 1048576;

   @Override
   public int getSoLinger() {
      return this.soLinger;
   }

   @Override
   public boolean isReuseAddress() {
      return this.reuseAddress;
   }

   @Override
   public UdtChannelConfig setReuseAddress(boolean var1) {
      this.reuseAddress = var1;
      return this;
   }

   @Override
   public int getProtocolReceiveBufferSize() {
      return this.protocolReceiveBuferSize;
   }

   @Override
   public UdtChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   @Override
   public int getProtocolSendBufferSize() {
      return this.protocolSendBuferSize;
   }

   @Override
   public UdtChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setSystemReceiveBufferSize(int var1) {
      this.systemSendBuferSize = var1;
      return this;
   }

   public void apply(ChannelUDT var1) throws java.io.IOException {
      SocketUDT var2 = var1.socketUDT();
      var2.setReuseAddress(this.isReuseAddress());
      var2.setSendBufferSize(this.getSendBufferSize());
      if (this.getSoLinger() <= 0) {
         var2.setSoLinger(false, 0);
      } else {
         var2.setSoLinger(true, this.getSoLinger());
      }

      var2.setOption(OptionUDT.Protocol_Receive_Buffer_Size, this.getProtocolReceiveBufferSize());
      var2.setOption(OptionUDT.Protocol_Send_Buffer_Size, this.getProtocolSendBufferSize());
      var2.setOption(OptionUDT.System_Receive_Buffer_Size, this.getSystemReceiveBufferSize());
      var2.setOption(OptionUDT.System_Send_Buffer_Size, this.getSystemSendBufferSize());
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == UdtChannelOption.PROTOCOL_RECEIVE_BUFFER_SIZE) {
         return (T)(Object)this.getProtocolReceiveBufferSize();
      } else if (var1 == UdtChannelOption.PROTOCOL_SEND_BUFFER_SIZE) {
         return (T)(Object)this.getProtocolSendBufferSize();
      } else if (var1 == UdtChannelOption.SYSTEM_RECEIVE_BUFFER_SIZE) {
         return (T)(Object)this.getSystemReceiveBufferSize();
      } else if (var1 == UdtChannelOption.SYSTEM_SEND_BUFFER_SIZE) {
         return (T)(Object)this.getSystemSendBufferSize();
      } else if (var1 == UdtChannelOption.SO_RCVBUF) {
         return (T)(Object)this.getReceiveBufferSize();
      } else if (var1 == UdtChannelOption.SO_SNDBUF) {
         return (T)(Object)this.getSendBufferSize();
      } else if (var1 == UdtChannelOption.SO_REUSEADDR) {
         return (T)(Object)this.isReuseAddress();
      } else {
         return (T)(var1 == UdtChannelOption.SO_LINGER ? this.getSoLinger() : super.getOption(var1));
      }
   }

   @Override
   public UdtChannelConfig setProtocolSendBufferSize(int var1) {
      this.protocolSendBuferSize = var1;
      return this;
   }

   @Override
   public int getSystemSendBufferSize() {
      return this.systemSendBuferSize;
   }

   @Override
   public UdtChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public int getReceiveBufferSize() {
      return this.allocatorReceiveBufferSize;
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == UdtChannelOption.PROTOCOL_RECEIVE_BUFFER_SIZE) {
         this.setProtocolReceiveBufferSize((Integer)var2);
      } else if (var1 == UdtChannelOption.PROTOCOL_SEND_BUFFER_SIZE) {
         this.setProtocolSendBufferSize((Integer)var2);
      } else if (var1 == UdtChannelOption.SYSTEM_RECEIVE_BUFFER_SIZE) {
         this.setSystemReceiveBufferSize((Integer)var2);
      } else if (var1 == UdtChannelOption.SYSTEM_SEND_BUFFER_SIZE) {
         this.setSystemSendBufferSize((Integer)var2);
      } else if (var1 == UdtChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == UdtChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)var2);
      } else if (var1 == UdtChannelOption.SO_REUSEADDR) {
         this.setReuseAddress((Boolean)var2);
      } else {
         if (var1 != UdtChannelOption.SO_LINGER) {
            return super.setOption(var1, var2);
         }

         this.setSoLinger((Integer)var2);
      }

      return true;
   }

   @Override
   public UdtChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setSoLinger(int var1) {
      this.soLinger = var1;
      return this;
   }

   @Override
   public UdtChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public int getSendBufferSize() {
      return this.allocatorSendBufferSize;
   }

   @Override
   public UdtChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setSendBufferSize(int var1) {
      this.allocatorSendBufferSize = var1;
      return this;
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(
         super.getOptions(),
         UdtChannelOption.PROTOCOL_RECEIVE_BUFFER_SIZE,
         UdtChannelOption.PROTOCOL_SEND_BUFFER_SIZE,
         UdtChannelOption.SYSTEM_RECEIVE_BUFFER_SIZE,
         UdtChannelOption.SYSTEM_SEND_BUFFER_SIZE,
         UdtChannelOption.SO_RCVBUF,
         UdtChannelOption.SO_SNDBUF,
         UdtChannelOption.SO_REUSEADDR,
         UdtChannelOption.SO_LINGER
      );
   }

   public DefaultUdtChannelConfig(UdtChannel var1, ChannelUDT var2, boolean var3) throws java.io.IOException {
      super(var1);
      this.systemSendBuferSize = 1048576;
      this.allocatorReceiveBufferSize = 131072;
      this.allocatorSendBufferSize = 131072;
      this.reuseAddress = true;
      if (var3) {
         this.apply(var2);
      }
   }

   @Override
   public UdtChannelConfig setSystemSendBufferSize(int var1) {
      this.systemReceiveBufferSize = var1;
      return this;
   }

   @Override
   public UdtChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setReceiveBufferSize(int var1) {
      this.allocatorReceiveBufferSize = var1;
      return this;
   }

   @Override
   public int getSystemReceiveBufferSize() {
      return this.systemReceiveBufferSize;
   }

   @Override
   public UdtChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public UdtChannelConfig setProtocolReceiveBufferSize(int var1) {
      this.protocolReceiveBuferSize = var1;
      return this;
   }
}
