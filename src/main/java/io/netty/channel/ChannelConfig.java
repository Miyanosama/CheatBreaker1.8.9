package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import java.util.Map;

public interface ChannelConfig {
   ChannelConfig setWriteBufferHighWaterMark(int var1);

   int getConnectTimeoutMillis();

   MessageSizeEstimator getMessageSizeEstimator();

   RecvByteBufAllocator getRecvByteBufAllocator();

   ChannelConfig setMaxMessagesPerRead(int var1);

   <T> T getOption(ChannelOption<T> var1);

   ByteBufAllocator getAllocator();

   int getWriteBufferLowWaterMark();

   <T> boolean setOption(ChannelOption<T> var1, T var2);

   boolean isAutoRead();

   Map<ChannelOption<?>, Object> getOptions();

   int getWriteBufferHighWaterMark();

   ChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   ChannelConfig setAllocator(ByteBufAllocator var1);

   int getWriteSpinCount();

   ChannelConfig setWriteSpinCount(int var1);

   ChannelConfig setAutoRead(boolean var1);

   int getMaxMessagesPerRead();

   ChannelConfig setWriteBufferLowWaterMark(int var1);

   ChannelConfig setConnectTimeoutMillis(int var1);

   boolean setOptions(Map<ChannelOption<?>, ?> var1);

   ChannelConfig setAutoClose(boolean var1);

   boolean isAutoClose();

   ChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);
}
