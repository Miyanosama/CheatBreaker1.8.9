package io.netty.util.concurrent;

public interface ProgressiveFuture<V> extends Future<V> {
   ProgressiveFuture<V> awaitUninterruptibly();

   ProgressiveFuture<V> await() throws java.lang.InterruptedException ;

   ProgressiveFuture<V> syncUninterruptibly();

   ProgressiveFuture<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   ProgressiveFuture<V> sync() throws java.lang.InterruptedException ;

   ProgressiveFuture<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1);

   ProgressiveFuture<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   ProgressiveFuture<V> addListener(GenericFutureListener<? extends Future<? super V>> var1);
}
