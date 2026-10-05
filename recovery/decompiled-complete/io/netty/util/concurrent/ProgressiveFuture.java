package io.netty.util.concurrent;

public interface ProgressiveFuture<V> extends Future<V> {
   ProgressiveFuture<V> awaitUninterruptibly();

   ProgressiveFuture<V> await();

   ProgressiveFuture<V> syncUninterruptibly();

   ProgressiveFuture<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   ProgressiveFuture<V> sync();

   ProgressiveFuture<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1);

   ProgressiveFuture<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   ProgressiveFuture<V> addListener(GenericFutureListener<? extends Future<? super V>> var1);
}
