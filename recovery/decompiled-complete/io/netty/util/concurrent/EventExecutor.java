package io.netty.util.concurrent;

public interface EventExecutor extends EventExecutorGroup {
   boolean inEventLoop();

   boolean inEventLoop(Thread var1);

   @Override
   EventExecutor next();

   <V> Future<V> newSucceededFuture(V var1);

   <V> Future<V> newFailedFuture(Throwable var1);

   <V> Promise<V> newPromise();

   <V> ProgressivePromise<V> newProgressivePromise();

   EventExecutorGroup parent();
}
