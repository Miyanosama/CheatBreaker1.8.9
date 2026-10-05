package io.netty.channel.sctp;

import com.sun.nio.sctp.SctpStandardSocketOptions;
import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.util.Map;
import net.minecraft.inventory.ContainerBeacon$BeaconSlot;

public class DefaultSctpChannelConfig extends DefaultChannelConfig implements SctpChannelConfig {
   public ContainerBeacon$BeaconSlot __junk5235053345211905179;
   public com.sun.nio.sctp.SctpChannel javaChannel;

   @Override
   public SctpChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public int getSendBufferSize() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SO_SNDBUF);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public SctpChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   public DefaultSctpChannelConfig(SctpChannel var1, com.sun.nio.sctp.SctpChannel var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("javaChannel");
      } else {
         this.javaChannel = var2;
         if (PlatformDependent.canEnableTcpNoDelayByDefault()) {
            try {
               this.setSctpNoDelay(true);
            } catch (Exception var4) {
            }
         }
      }
   }

   @Override
   public SctpChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setSendBufferSize(int var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SO_SNDBUF, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SctpChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public SctpChannelConfig setInitMaxStreams(InitMaxStreams var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SCTP_INIT_MAXSTREAMS, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SctpChannelConfig setSctpNoDelay(boolean var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SCTP_NODELAY, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SctpChannelConfig setReceiveBufferSize(int var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SO_RCVBUF, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(
         super.getOptions(), SctpChannelOption.SO_RCVBUF, SctpChannelOption.SO_SNDBUF, SctpChannelOption.SCTP_NODELAY, SctpChannelOption.SCTP_INIT_MAXSTREAMS
      );
   }

   @Override
   public InitMaxStreams getInitMaxStreams() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SCTP_INIT_MAXSTREAMS);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == SctpChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == SctpChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)var2);
      } else if (var1 == SctpChannelOption.SCTP_NODELAY) {
         this.setSctpNoDelay((Boolean)var2);
      } else {
         if (var1 != SctpChannelOption.SCTP_INIT_MAXSTREAMS) {
            return super.setOption(var1, var2);
         }

         this.setInitMaxStreams((InitMaxStreams)var2);
      }

      return true;
   }

   @Override
   public int getReceiveBufferSize() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SO_RCVBUF);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == SctpChannelOption.SO_RCVBUF) {
         return (T)this.getReceiveBufferSize();
      } else if (var1 == SctpChannelOption.SO_SNDBUF) {
         return (T)this.getSendBufferSize();
      } else {
         return (T)(var1 == SctpChannelOption.SCTP_NODELAY ? this.isSctpNoDelay() : super.getOption(var1));
      }
   }

   @Override
   public boolean isSctpNoDelay() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SCTP_NODELAY);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }
}
