package io.netty.util.concurrent;

import java.util.concurrent.TimeUnit;

public interface Future<V> extends java.util.concurrent.Future<V> {
   boolean isCancellable();

   Future<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   Future<V> syncUninterruptibly();

   Future<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1);

   Future<V> addListener(GenericFutureListener<? extends Future<? super V>> var1);

   boolean awaitUninterruptibly(long var1, TimeUnit var3);

   boolean awaitUninterruptibly(long var1);

   boolean await(long var1, TimeUnit var3);

   boolean await(long var1);

   Throwable cause();

   Future<V> awaitUninterruptibly();

   Future<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1);

   V getNow();

   @Override
   boolean cancel(boolean var1);

   boolean isSuccess();

   Future<V> await();

   Future<V> sync();
}
