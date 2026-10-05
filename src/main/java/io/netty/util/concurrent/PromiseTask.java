package io.netty.util.concurrent;

import io.netty.channel.AbstractChannel;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import org.java_websocket.WebSocketImpl;
import com.cheatbreaker.client.emote.type.FlossPose;

public class PromiseTask<V> extends DefaultPromise<V> implements RunnableFuture<V> {
   public Callable<V> task;

   @Override
   public boolean tryFailure(Throwable var1) {
      return false;
   }

   public boolean trySuccessInternal(V var1) {
      return super.trySuccess((V)var1);
   }

   public boolean tryFailureInternal(Throwable var1) {
      return super.tryFailure(var1);
   }

   @Override
   public boolean setUncancellable() {
      throw new IllegalStateException();
   }

   @Override
   public void run() {
      try {
         if (this.setUncancellableInternal()) {
            Object var1 = this.task.call();
            this.setSuccessInternal((V)var1);
         }
      } catch (Throwable var2) {
         this.setFailureInternal(var2);
      }
   }

   public static <T> Callable<T> toCallable(Runnable var0, T var1) {
      return new PromiseTask.RunnableAdapter<>(var0, (T)var1);
   }

   public PromiseTask(EventExecutor var1, Runnable var2, V var3) {
      this(var1, toCallable(var2, (V)var3));
   }

   public Promise<V> setSuccessInternal(V var1) {
      super.setSuccess((V)var1);
      return this;
   }

   @Override
   public boolean trySuccess(V var1) {
      return false;
   }

   public PromiseTask(EventExecutor var1, Callable<V> var2) {
      super(var1);
      this.task = var2;
   }

   public boolean setUncancellableInternal() {
      return super.setUncancellable();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public int hashCode() {
      return System.identityHashCode(this);
   }

   @Override
   public Promise<V> setFailure(Throwable var1) {
      throw new IllegalStateException();
   }

   @Override
   public StringBuilder toStringBuilder() {
      StringBuilder var1 = super.toStringBuilder();
      var1.setCharAt(var1.length() - 1, ',');
      var1.append(" task: ");
      var1.append(this.task);
      var1.append(')');
      return var1;
   }

   public Promise<V> setFailureInternal(Throwable var1) {
      super.setFailure(var1);
      return this;
   }

   @Override
   public Promise<V> setSuccess(V var1) {
      throw new IllegalStateException();
   }

   public static final class RunnableAdapter<T> implements Callable<T> {
      public T result;
      public Runnable task;

      @Override
      public T call() {
         this.task.run();
         return this.result;
      }

      @Override
      public String toString() {
         return "Callable(task: " + this.task + ", result: " + this.result + ')';
      }

      public RunnableAdapter(Runnable var1, T var2) {
         this.task = var1;
         this.result = (T)var2;
      }
   }
}
