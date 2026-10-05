package io.netty.util.concurrent;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public interface EventExecutorGroup extends Iterable<EventExecutor>, ScheduledExecutorService {
   <V> ScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4);

   Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5);

   @Override
   List<Runnable> shutdownNow();

   ScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6);

   boolean isShuttingDown();

   EventExecutor next();

   Future<?> terminationFuture();

   <T> Future<T> submit(Runnable var1, T var2);

   <T> Future<T> submit(Callable<T> var1);

   ScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4);

   ScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6);

   Future<?> shutdownGracefully();

   @Override
   Iterator<EventExecutor> iterator();

   @Override
   void shutdown();

   Future<?> submit(Runnable var1);
}
