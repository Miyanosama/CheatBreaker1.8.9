package io.netty.channel;

import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.socks.SocksProtocolVersion;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import junit.extensions.ActiveTestSuite$1;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$13;

public class DefaultChannelPromise extends DefaultPromise<Void> implements ChannelFlushPromiseNotifier.FlushCheckpoint, ChannelPromise {
   public Channel channel;
   public long checkpoint;

   @Override
   public ChannelPromise awaitUninterruptibly() {
      super.awaitUninterruptibly();
      return this;
   }

   @Override
   public ChannelPromise await() throws java.lang.InterruptedException {
      super.await();
      return this;
   }

   @Override
   public void flushCheckpoint(long var1) {
      this.checkpoint = var1;
   }

   @Override
   public void checkDeadLock() {
      if (this.channel().isRegistered()) {
         super.checkDeadLock();
      }
   }

   @Override
   public ChannelPromise setSuccess() {
      return this.setSuccess(null);
   }

   @Override
   public ChannelPromise syncUninterruptibly() {
      super.syncUninterruptibly();
      return this;
   }

   @Override
   public Channel channel() {
      return this.channel;
   }

   @Override
   public ChannelPromise removeListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.removeListener(var1);
      return this;
   }

   public DefaultChannelPromise(Channel var1) {
      this.channel = var1;
   }

   @Override
   public EventExecutor executor() {
      EventExecutor var1 = super.executor();
      return (EventExecutor)(var1 == null ? this.channel().eventLoop() : var1);
   }

   @Override
   public boolean trySuccess() {
      return this.trySuccess(null);
   }

   @Override
   public ChannelPromise setSuccess(Void var1) {
      super.setSuccess(var1);
      return this;
   }

   @Override
   public ChannelPromise promise() {
      return this;
   }

   @Override
   public ChannelPromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.removeListeners(var1);
      return this;
   }

   public DefaultChannelPromise(Channel var1, EventExecutor var2) {
      super(var2);
      this.channel = var1;
   }

   @Override
   public ChannelPromise setFailure(Throwable var1) {
      super.setFailure(var1);
      return this;
   }

   @Override
   public ChannelPromise addListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.addListener(var1);
      return this;
   }

   @Override
   public long flushCheckpoint() {
      return this.checkpoint;
   }

   @Override
   public ChannelPromise sync() throws java.lang.InterruptedException {
      super.sync();
      return this;
   }

   @Override
   public ChannelPromise addListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.addListeners(var1);
      return this;
   }
}
