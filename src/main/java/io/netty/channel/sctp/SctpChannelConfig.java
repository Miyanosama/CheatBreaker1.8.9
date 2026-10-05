package io.netty.channel.sctp;

import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface SctpChannelConfig extends ChannelConfig {
   int getSendBufferSize();

   SctpChannelConfig setInitMaxStreams(InitMaxStreams var1);

   boolean isSctpNoDelay();

   SctpChannelConfig setWriteBufferHighWaterMark(int var1);

   int getReceiveBufferSize();

   InitMaxStreams getInitMaxStreams();

   SctpChannelConfig setSctpNoDelay(boolean var1);

   SctpChannelConfig setAutoRead(boolean var1);

   SctpChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   SctpChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);

   SctpChannelConfig setSendBufferSize(int var1);

   SctpChannelConfig setWriteBufferLowWaterMark(int var1);

   SctpChannelConfig setReceiveBufferSize(int var1);

   SctpChannelConfig setConnectTimeoutMillis(int var1);

   SctpChannelConfig setAllocator(ByteBufAllocator var1);

   SctpChannelConfig setWriteSpinCount(int var1);

   SctpChannelConfig setAutoClose(boolean var1);

   SctpChannelConfig setMaxMessagesPerRead(int var1);
}
