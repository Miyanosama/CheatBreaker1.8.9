package io.netty.channel;

import io.netty.util.concurrent.DefaultProgressivePromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.projectile.EntityThrowable;

public class DefaultChannelProgressivePromise
   extends DefaultProgressivePromise<Void>
   implements ChannelFlushPromiseNotifier.FlushCheckpoint,
   ChannelProgressivePromise {
   public long checkpoint;
   public Channel channel;

   @Override
   public ChannelProgressivePromise setSuccess() {
      return this.setSuccess(null);
   }

   @Override
   public long flushCheckpoint() {
      return this.checkpoint;
   }

   @Override
   public Channel channel() {
      return this.channel;
   }

   @Override
   public void flushCheckpoint(long var1) {
      this.checkpoint = var1;
   }

   public DefaultChannelProgressivePromise(Channel var1, EventExecutor var2) {
      super(var2);
      this.channel = var1;
   }

   @Override
   public boolean trySuccess() {
      return this.trySuccess(null);
   }

   public DefaultChannelProgressivePromise(Channel var1) {
      this.channel = var1;
   }

   @Override
   public ChannelProgressivePromise awaitUninterruptibly() {
      super.awaitUninterruptibly();
      return this;
   }

   @Override
   public ChannelProgressivePromise sync() throws java.lang.InterruptedException {
      super.sync();
      return this;
   }

   @Override
   public ChannelProgressivePromise syncUninterruptibly() {
      super.syncUninterruptibly();
      return this;
   }

   @Override
   public ChannelProgressivePromise setSuccess(Void var1) {
      super.setSuccess(var1);
      return this;
   }

   @Override
   public EventExecutor executor() {
      EventExecutor var1 = super.executor();
      return (EventExecutor)(var1 == null ? this.channel().eventLoop() : var1);
   }

   @Override
   public ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.removeListener(var1);
      return this;
   }

   @Override
   public ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.addListeners(var1);
      return this;
   }

   @Override
   public ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> var1) {
      super.addListener(var1);
      return this;
   }

   @Override
   public ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... var1) {
      super.removeListeners(var1);
      return this;
   }

   @Override
   public void checkDeadLock() {
      if (this.channel().isRegistered()) {
         super.checkDeadLock();
      }
   }

   public ChannelProgressivePromise promise() {
      return this;
   }

   @Override
   public ChannelProgressivePromise setProgress(long var1, long var3) {
      super.setProgress(var1, var3);
      return this;
   }

   @Override
   public ChannelProgressivePromise setFailure(Throwable var1) {
      super.setFailure(var1);
      return this;
   }

   @Override
   public ChannelProgressivePromise await() throws java.lang.InterruptedException {
      super.await();
      return this;
   }
}
