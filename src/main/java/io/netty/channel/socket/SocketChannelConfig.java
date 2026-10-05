package io.netty.channel.socket;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface SocketChannelConfig extends ChannelConfig {
   SocketChannelConfig setReceiveBufferSize(int var1);

   SocketChannelConfig setAutoRead(boolean var1);

   int getTrafficClass();

   int getSoLinger();

   SocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);

   SocketChannelConfig setTrafficClass(int var1);

   SocketChannelConfig setConnectTimeoutMillis(int var1);

   SocketChannelConfig setTcpNoDelay(boolean var1);

   SocketChannelConfig setAllocator(ByteBufAllocator var1);

   SocketChannelConfig setPerformancePreferences(int var1, int var2, int var3);

   SocketChannelConfig setWriteSpinCount(int var1);

   boolean isKeepAlive();

   boolean isAllowHalfClosure();

   SocketChannelConfig setAutoClose(boolean var1);

   SocketChannelConfig setSoLinger(int var1);

   SocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   SocketChannelConfig setSendBufferSize(int var1);

   SocketChannelConfig setAllowHalfClosure(boolean var1);

   int getReceiveBufferSize();

   SocketChannelConfig setMaxMessagesPerRead(int var1);

   boolean isTcpNoDelay();

   SocketChannelConfig setReuseAddress(boolean var1);

   int getSendBufferSize();

   SocketChannelConfig setKeepAlive(boolean var1);

   boolean isReuseAddress();
}
