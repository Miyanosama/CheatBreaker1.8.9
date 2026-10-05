package io.netty.util.concurrent;

public interface Promise<V> extends Future<V> {
   Promise<V> await() throws java.lang.InterruptedException ;

   boolean tryFailure(Throwable var1);

   boolean trySuccess(V var1);

   Promise<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   Promise<V> syncUninterruptibly();

   Promise<V> sync() throws java.lang.InterruptedException ;

   Promise<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1);

   Promise<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   boolean setUncancellable();

   Promise<V> setFailure(Throwable var1);

   Promise<V> addListener(GenericFutureListener<? extends Future<? super V>> var1);

   Promise<V> setSuccess(V var1);

   Promise<V> awaitUninterruptibly();
}
