package io.netty.util.concurrent;

public interface ProgressivePromise<V> extends ProgressiveFuture<V>, Promise<V> {
   ProgressivePromise<V> setProgress(long var1, long var3);

   ProgressivePromise<V> addListener(GenericFutureListener<? extends Future<? super V>> var1);

   ProgressivePromise<V> setSuccess(V var1);

   ProgressivePromise<V> syncUninterruptibly();

   ProgressivePromise<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1);

   ProgressivePromise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   ProgressivePromise<V> awaitUninterruptibly();

   ProgressivePromise<V> setFailure(Throwable var1);

   ProgressivePromise<V> sync();

   ProgressivePromise<V> await();

   boolean tryProgress(long var1, long var3);

   ProgressivePromise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1);
}
