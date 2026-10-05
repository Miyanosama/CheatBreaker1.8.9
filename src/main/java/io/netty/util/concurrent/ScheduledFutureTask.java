package io.netty.util.concurrent;

import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.optifine.texture.TextureType;

public class ScheduledFutureTask<V> extends PromiseTask<V> implements ScheduledFuture<V> {
   public long periodNanos;
   public static final boolean $assertionsDisabled = !ScheduledFutureTask.class.desiredAssertionStatus();
   public long deadlineNanos;
   public static AtomicLong nextTaskId = new AtomicLong();
   public long id = nextTaskId.getAndIncrement();
   public Queue<ScheduledFutureTask<?>> delayedTaskQueue;
   public static long START_TIME = System.nanoTime();

   @Override
   public EventExecutor executor() {
      return super.executor();
   }

   public long delayNanos(long var1) {
      return Math.max(0L, this.deadlineNanos() - (var1 - START_TIME));
   }

   public static long nanoTime() {
      return System.nanoTime() - START_TIME;
   }

   public long delayNanos() {
      return Math.max(0L, this.deadlineNanos() - nanoTime());
   }

   public ScheduledFutureTask(EventExecutor var1, Queue<ScheduledFutureTask<?>> var2, Runnable var3, V var4, long var5) {
      this(var1, var2, toCallable(var3, (V)var4), var5);
   }

   public static long deadlineNanos(long var0) {
      return nanoTime() + var0;
   }

   @Override
   public StringBuilder toStringBuilder() {
      StringBuilder var1 = super.toStringBuilder();
      var1.setCharAt(var1.length() - 1, ',');
      var1.append(" id: ");
      var1.append(this.id);
      var1.append(", deadline: ");
      var1.append(this.deadlineNanos);
      var1.append(", period: ");
      var1.append(this.periodNanos);
      var1.append(')');
      return var1;
   }

   public ScheduledFutureTask(EventExecutor var1, Queue<ScheduledFutureTask<?>> var2, Callable<V> var3, long var4, long var6) {
      super(var1, var3);
      if (var6 == 0L) {
         throw new IllegalArgumentException("period: 0 (expected: != 0)");
      } else {
         this.delayedTaskQueue = var2;
         this.deadlineNanos = var4;
         this.periodNanos = var6;
      }
   }

   @Override
   public long getDelay(TimeUnit var1) {
      return var1.convert(this.delayNanos(), TimeUnit.NANOSECONDS);
   }

   public int compareTo(Delayed var1) {
      if (this == var1) {
         return 0;
      } else {
         ScheduledFutureTask var2 = (ScheduledFutureTask)var1;
         long var3 = this.deadlineNanos() - var2.deadlineNanos();
         if (var3 < 0L) {
            return -1;
         } else if (var3 > 0L) {
            return 1;
         } else if (this.id < var2.id) {
            return -1;
         } else if (this.id == var2.id) {
            throw new Error();
         } else {
            return 1;
         }
      }
   }

   @Override
   public void run() {
      if (!$assertionsDisabled && !this.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         try {
            if (this.periodNanos == 0L) {
               if (this.setUncancellableInternal()) {
                  Object var1 = this.task.call();
                  this.setSuccessInternal((V)var1);
               }
            } else if (!this.isCancelled()) {
               this.task.call();
               if (!this.executor().isShutdown()) {
                  long var4 = this.periodNanos;
                  if (var4 > 0L) {
                     this.deadlineNanos += var4;
                  } else {
                     this.deadlineNanos = nanoTime() - var4;
                  }

                  if (!this.isCancelled()) {
                     this.delayedTaskQueue.add(this);
                  }
               }
            }
         } catch (Throwable var3) {
            this.setFailureInternal(var3);
         }
      }
   }

   public long deadlineNanos() {
      return this.deadlineNanos;
   }

   public ScheduledFutureTask(EventExecutor var1, Queue<ScheduledFutureTask<?>> var2, Callable<V> var3, long var4) {
      super(var1, var3);
      this.delayedTaskQueue = var2;
      this.deadlineNanos = var4;
      this.periodNanos = 0L;
   }
}
