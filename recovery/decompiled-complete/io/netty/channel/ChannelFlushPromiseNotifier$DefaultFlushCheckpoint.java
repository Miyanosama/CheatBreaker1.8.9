package io.netty.channel;

public class ChannelFlushPromiseNotifier$DefaultFlushCheckpoint implements ChannelFlushPromiseNotifier$FlushCheckpoint {
   public AbstractChannelHandlerContext$15 __junk4755575861900795787;
   public ChannelPromise future;
   public long checkpoint;

   @Override
   public long flushCheckpoint() {
      return this.checkpoint;
   }

   public ChannelFlushPromiseNotifier$DefaultFlushCheckpoint(long var1, ChannelPromise var3) {
      this.checkpoint = var1;
      this.future = var3;
   }

   @Override
   public void flushCheckpoint(long var1) {
      this.checkpoint = var1;
   }

   @Override
   public ChannelPromise promise() {
      return this.future;
   }
}
