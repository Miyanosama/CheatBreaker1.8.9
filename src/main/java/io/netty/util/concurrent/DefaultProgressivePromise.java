package io.netty.util.concurrent;

import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S45PacketTitle;

public class DefaultProgressivePromise<V> extends DefaultPromise<V> implements ProgressivePromise<V> {

   public DefaultProgressivePromise(EventExecutor var1) {
      super(var1);
   }

   @Override
   public ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1) {
      super.removeListener(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1) {
      super.removeListeners(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> setFailure(Throwable var1) {
      super.setFailure(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> await() throws java.lang.InterruptedException {
      super.await();
      return this;
   }

   public DefaultProgressivePromise() {
   }

   @Override
   public ProgressivePromise<V> setSuccess(V var1) {
      super.setSuccess((V)var1);
      return this;
   }

   @Override
   public boolean tryProgress(long var1, long var3) {
      if (var3 < 0L) {
         var3 = -1L;
         if (var1 < 0L || this.isDone()) {
            return false;
         }
      } else if (var1 < 0L || var1 > var3 || this.isDone()) {
         return false;
      }

      this.notifyProgressiveListeners(var1, var3);
      return true;
   }

   @Override
   public ProgressivePromise<V> syncUninterruptibly() {
      super.syncUninterruptibly();
      return this;
   }

   @Override
   public ProgressivePromise<V> setProgress(long var1, long var3) {
      if (var3 < 0L) {
         var3 = -1L;
         if (var1 < 0L) {
            throw new IllegalArgumentException("progress: " + var1 + " (expected: >= 0)");
         }
      } else if (var1 < 0L || var1 > var3) {
         throw new IllegalArgumentException("progress: " + var1 + " (expected: 0 <= progress <= total (" + var3 + "))");
      }

      if (this.isDone()) {
         throw new IllegalStateException("complete already");
      } else {
         this.notifyProgressiveListeners(var1, var3);
         return this;
      }
   }

   @Override
   public ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1) {
      super.addListeners(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> var1) {
      super.addListener(var1);
      return this;
   }

   @Override
   public ProgressivePromise<V> awaitUninterruptibly() {
      super.awaitUninterruptibly();
      return this;
   }

   @Override
   public ProgressivePromise<V> sync() throws java.lang.InterruptedException {
      super.sync();
      return this;
   }
}
