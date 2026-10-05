package io.netty.channel;

public interface ChannelFlushPromiseNotifier$FlushCheckpoint {
   void flushCheckpoint(long var1);

   long flushCheckpoint();

   ChannelPromise promise();
}
