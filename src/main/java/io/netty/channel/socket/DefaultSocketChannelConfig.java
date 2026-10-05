package io.netty.channel.socket;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.internal.PlatformDependent;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

public class DefaultSocketChannelConfig extends DefaultChannelConfig implements SocketChannelConfig {
   public volatile boolean allowHalfClosure;
   public Socket javaSocket;

   @Override
   public int getReceiveBufferSize() {
      try {
         return this.javaSocket.getReceiveBufferSize();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public SocketChannelConfig setSoLinger(int var1) {
      try {
         if (var1 < 0) {
            this.javaSocket.setSoLinger(false, 0);
         } else {
            this.javaSocket.setSoLinger(true, var1);
         }

         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
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
      } else {
         return (T)(var1 == ChannelOption.ALLOW_HALF_CLOSURE ? this.isAllowHalfClosure() : super.getOption(var1));
      }
   }

   @Override
   public boolean isAllowHalfClosure() {
      return this.allowHalfClosure;
   }

   @Override
   public SocketChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
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
   public SocketChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public int getTrafficClass() {
      try {
         return this.javaSocket.getTrafficClass();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   public SocketChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public SocketChannelConfig setTrafficClass(int var1) {
      try {
         this.javaSocket.setTrafficClass(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SocketChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   @Override
   public boolean isKeepAlive() {
      try {
         return this.javaSocket.getKeepAlive();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public SocketChannelConfig setKeepAlive(boolean var1) {
      try {
         this.javaSocket.setKeepAlive(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SocketChannelConfig setPerformancePreferences(int var1, int var2, int var3) {
      this.javaSocket.setPerformancePreferences(var1, var2, var3);
      return this;
   }

   @Override
   public SocketChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public SocketChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
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
         ChannelOption.ALLOW_HALF_CLOSURE
      );
   }

   @Override
   public int getSoLinger() {
      try {
         return this.javaSocket.getSoLinger();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public SocketChannelConfig setReceiveBufferSize(int var1) {
      try {
         this.javaSocket.setReceiveBufferSize(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   public DefaultSocketChannelConfig(SocketChannel var1, Socket var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("javaSocket");
      } else {
         this.javaSocket = var2;
         if (PlatformDependent.canEnableTcpNoDelayByDefault()) {
            try {
               this.setTcpNoDelay(true);
            } catch (Exception var4) {
            }
         }
      }
   }

   @Override
   public SocketChannelConfig setAllowHalfClosure(boolean var1) {
      this.allowHalfClosure = var1;
      return this;
   }

   @Override
   public SocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public SocketChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public SocketChannelConfig setTcpNoDelay(boolean var1) {
      try {
         this.javaSocket.setTcpNoDelay(var1);
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
      } else {
         if (var1 != ChannelOption.ALLOW_HALF_CLOSURE) {
            return super.setOption(var1, var2);
         }

         this.setAllowHalfClosure((Boolean)var2);
      }

      return true;
   }

   @Override
   public int getSendBufferSize() {
      try {
         return this.javaSocket.getSendBufferSize();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public boolean isTcpNoDelay() {
      try {
         return this.javaSocket.getTcpNoDelay();
      } catch (SocketException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public SocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public SocketChannelConfig setSendBufferSize(int var1) {
      try {
         this.javaSocket.setSendBufferSize(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SocketChannelConfig setReuseAddress(boolean var1) {
      try {
         this.javaSocket.setReuseAddress(var1);
         return this;
      } catch (SocketException var3) {
         throw new ChannelException(var3);
      }
   }

   public SocketChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }
}
