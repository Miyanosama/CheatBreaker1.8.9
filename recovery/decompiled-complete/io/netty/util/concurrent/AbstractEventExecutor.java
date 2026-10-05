package io.netty.util.concurrent;

import io.netty.handler.codec.http.DefaultHttpHeaders$1;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import recovered.unidentified.UnidentifiedClass0315;

public abstract class AbstractEventExecutor extends AbstractExecutorService implements EventExecutor {
   public UnidentifiedClass0315 __junk5130203639337343022;
   public DefaultHttpHeaders$1 __junk6679442857272133396;

   @Override
   public <T> Future<T> submit(Callable<T> var1) {
      return (Future<T>)super.<T>submit(var1);
   }

   @Override
   public <V> Promise<V> newPromise() {
      return new DefaultPromise<>(this);
   }

   @Override
   public boolean inEventLoop() {
      return this.inEventLoop(Thread.currentThread());
   }

   @Override
   public <V> Future<V> newFailedFuture(Throwable var1) {
      return new FailedFuture<>(this, var1);
   }

   @Override
   public <T> RunnableFuture<T> newTaskFor(Runnable var1, T var2) {
      return new PromiseTask<>(this, var1, (T)var2);
   }

   @Override
   public ScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4) {
      throw new UnsupportedOperationException();
   }

   @Override
   public <T> RunnableFuture<T> newTaskFor(Callable<T> var1) {
      return new PromiseTask<>(this, var1);
   }

   @Override
   public Future<?> shutdownGracefully() {
      return this.shutdownGracefully(8402459839475036418L & 17435L, -491645224199486865L & 491645222355998751L, TimeUnit.SECONDS);
   }

   @Override
   public EventExecutor next() {
      return this;
   }

   @Override
   public <V> ScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6) {
      throw new UnsupportedOperationException();
   }

   @Override
   public abstract void shutdown();

   @Override
   public <V> ProgressivePromise<V> newProgressivePromise() {
      return new DefaultProgressivePromise<>(this);
   }

   @Override
   public List<Runnable> shutdownNow() {
      this.shutdown();
      return Collections.emptyList();
   }

   @Override
   public Iterator<EventExecutor> iterator() {
      return new AbstractEventExecutor$EventExecutorIterator(this, null);
   }

   @Override
   public Future<?> submit(Runnable var1) {
      return (Future<?>)super.submit(var1);
   }

   @Override
   public <V> Future<V> newSucceededFuture(V var1) {
      return new SucceededFuture<>(this, (V)var1);
   }

   @Override
   public <T> Future<T> submit(Runnable var1, T var2) {
      return (Future<T>)super.<T>submit(var1, (T)var2);
   }

   @Override
   public ScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6) {
      throw new UnsupportedOperationException();
   }
}
