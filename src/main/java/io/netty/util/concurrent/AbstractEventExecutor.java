package io.netty.util.concurrent;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.audio.SoundListSerializer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.realms.RealmsVertexFormat;
import com.cheatbreaker.client.module.type.keystrokes.KeystrokeKey;

public abstract class AbstractEventExecutor extends AbstractExecutorService implements EventExecutor {

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
      return this.shutdownGracefully(2L, 15L, TimeUnit.SECONDS);
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
      return new AbstractEventExecutor.EventExecutorIterator();
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

   public final class EventExecutorIterator implements Iterator<EventExecutor> {
      public boolean nextCalled;

      public EventExecutorIterator() {
      }

      @Override
      public boolean hasNext() {
         return !this.nextCalled;
      }

      public EventExecutor next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            this.nextCalled = true;
            return AbstractEventExecutor.this;
         }
      }

      @Override
      public void remove() {
         throw new UnsupportedOperationException("read-only");
      }
   }
}
