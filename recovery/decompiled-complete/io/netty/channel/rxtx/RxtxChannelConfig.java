package io.netty.channel.rxtx;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;

public interface RxtxChannelConfig extends ChannelConfig {
   RxtxChannelConfig setRts(boolean var1);

   RxtxChannelConfig setDtr(boolean var1);

   RxtxChannelConfig setWriteBufferLowWaterMark(int var1);

   int getBaudrate();

   RxtxChannelConfig setWriteSpinCount(int var1);

   RxtxChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);

   RxtxChannelConfig setConnectTimeoutMillis(int var1);

   RxtxChannelConfig setMaxMessagesPerRead(int var1);

   RxtxChannelConfig setDatabits(RxtxChannelConfig$Databits var1);

   RxtxChannelConfig setParitybit(RxtxChannelConfig$Paritybit var1);

   RxtxChannelConfig$Databits getDatabits();

   RxtxChannelConfig setAllocator(ByteBufAllocator var1);

   boolean isDtr();

   RxtxChannelConfig setWriteBufferHighWaterMark(int var1);

   RxtxChannelConfig setBaudrate(int var1);

   RxtxChannelConfig$Paritybit getParitybit();

   int getWaitTimeMillis();

   RxtxChannelConfig setReadTimeout(int var1);

   RxtxChannelConfig setAutoRead(boolean var1);

   RxtxChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   int getReadTimeout();

   RxtxChannelConfig setStopbits(RxtxChannelConfig$Stopbits var1);

   RxtxChannelConfig setWaitTimeMillis(int var1);

   boolean isRts();

   RxtxChannelConfig setAutoClose(boolean var1);

   RxtxChannelConfig$Stopbits getStopbits();
}
