package io.netty.channel.udt;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface UdtChannelConfig extends ChannelConfig {
   int getProtocolSendBufferSize();

   UdtChannelConfig setReceiveBufferSize(int var1);

   UdtChannelConfig setSystemSendBufferSize(int var1);

   int getSystemSendBufferSize();

   UdtChannelConfig setConnectTimeoutMillis(int var1);

   int getSystemReceiveBufferSize();

   UdtChannelConfig setAutoClose(boolean var1);

   UdtChannelConfig setWriteBufferLowWaterMark(int var1);

   UdtChannelConfig setAutoRead(boolean var1);

   UdtChannelConfig setReuseAddress(boolean var1);

   UdtChannelConfig setProtocolReceiveBufferSize(int var1);

   UdtChannelConfig setWriteSpinCount(int var1);

   UdtChannelConfig setSendBufferSize(int var1);

   int getReceiveBufferSize();

   UdtChannelConfig setSoLinger(int var1);

   UdtChannelConfig setAllocator(ByteBufAllocator var1);

   UdtChannelConfig setProtocolSendBufferSize(int var1);

   int getSoLinger();

   UdtChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);

   boolean isReuseAddress();

   UdtChannelConfig setSystemReceiveBufferSize(int var1);

   int getProtocolReceiveBufferSize();

   UdtChannelConfig setWriteBufferHighWaterMark(int var1);

   UdtChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   int getSendBufferSize();

   UdtChannelConfig setMaxMessagesPerRead(int var1);
}
