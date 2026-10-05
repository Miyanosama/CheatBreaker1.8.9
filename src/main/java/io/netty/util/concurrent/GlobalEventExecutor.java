package io.netty.util.concurrent;

import io.netty.buffer.EmptyByteBuf;
import io.netty.util.internal.MpscLinkedQueueTailRef;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.log4j.pattern.FormattingInfo;
import org.apache.log4j.varia.ReloadingPropertyConfigurator;
import org.java_websocket.exceptions.WrappedIOException;
import junit.swingui.AboutDialog$1;
import com.cheatbreaker.client.event.type.WorldChangeEvent;

public class GlobalEventExecutor extends AbstractEventExecutor {
   public static InternalLogger logger = InternalLoggerFactory.getInstance(GlobalEventExecutor.class);
   public Future<?> terminationFuture;
   public GlobalEventExecutor.TaskRunner taskRunner;
   public BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
   public static long SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1L);
   public Queue<ScheduledFutureTask<?>> delayedTaskQueue = new PriorityQueue<>();
   public ScheduledFutureTask<Void> purgeTask = new ScheduledFutureTask<>(
      this,
      this.delayedTaskQueue,
      Executors.callable(new GlobalEventExecutor.PurgeTask(), null),
      ScheduledFutureTask.deadlineNanos(SCHEDULE_PURGE_INTERVAL),
      -SCHEDULE_PURGE_INTERVAL
   );
   public static GlobalEventExecutor INSTANCE = new GlobalEventExecutor();
   public AtomicBoolean started;
   public volatile Thread thread;
   public ThreadFactory threadFactory = new DefaultThreadFactory(this.getClass());

   public void fetchFromDelayedQueue() {
      long var1 = 0L;

      while (true) {
         ScheduledFutureTask var3 = this.delayedTaskQueue.peek();
         if (var3 == null) {
            break;
         }

         if (var1 == 0L) {
            var1 = ScheduledFutureTask.nanoTime();
         }

         if (var3.deadlineNanos() > var1) {
            break;
         }

         this.delayedTaskQueue.remove();
         this.taskQueue.add(var3);
      }
   }

   public GlobalEventExecutor() {
      this.taskRunner = new GlobalEventExecutor.TaskRunner();
      this.started = new AtomicBoolean();
      this.terminationFuture = new FailedFuture(this, new UnsupportedOperationException());
      this.delayedTaskQueue.add(this.purgeTask);
   }

   @Override
   public boolean isShuttingDown() {
      return false;
   }

   public void startThread() {
      if (this.started.compareAndSet(false, true)) {
         Thread var1 = this.threadFactory.newThread(this.taskRunner);
         var1.start();
         this.thread = var1;
      }
   }

   public boolean awaitInactivity(long var1, TimeUnit var3) throws java.lang.InterruptedException {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else {
         Thread var4 = this.thread;
         if (var4 == null) {
            throw new IllegalStateException("thread was not started");
         } else {
            var4.join(var3.toMillis(var1));
            return !var4.isAlive();
         }
      }
   }

   @Override
   public EventExecutorGroup parent() {
      return null;
   }

   @Override
   public boolean isShutdown() {
      return false;
   }

   @Override
   public <V> ScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4) {
      if (var1 == null) {
         throw new NullPointerException("callable");
      } else if (var4 == null) {
         throw new NullPointerException("unit");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException(String.format("delay: %d (expected: >= 0)", var2));
      } else {
         return this.schedule(new ScheduledFutureTask<>(this, this.delayedTaskQueue, var1, ScheduledFutureTask.deadlineNanos(var4.toNanos(var2))));
      }
   }

   @Override
   public void shutdown() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isTerminated() {
      return false;
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) {
      return false;
   }

   @Override
   public ScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4) {
      if (var1 == null) {
         throw new NullPointerException("command");
      } else if (var4 == null) {
         throw new NullPointerException("unit");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException(String.format("delay: %d (expected: >= 0)", var2));
      } else {
         return this.schedule(new ScheduledFutureTask(this, this.delayedTaskQueue, var1, null, ScheduledFutureTask.deadlineNanos(var4.toNanos(var2))));
      }
   }

   @Override
   public ScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6) {
      if (var1 == null) {
         throw new NullPointerException("command");
      } else if (var6 == null) {
         throw new NullPointerException("unit");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", var2));
      } else if (var4 <= 0L) {
         throw new IllegalArgumentException(String.format("delay: %d (expected: > 0)", var4));
      } else {
         return this.schedule(
            new ScheduledFutureTask(
               this, this.delayedTaskQueue, Executors.callable(var1, null), ScheduledFutureTask.deadlineNanos(var6.toNanos(var2)), -var6.toNanos(var4)
            )
         );
      }
   }

   public int pendingTasks() {
      return this.taskQueue.size();
   }

   public <V> ScheduledFuture<V> schedule(final ScheduledFutureTask<V> var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         if (this.inEventLoop()) {
            this.delayedTaskQueue.add(var1);
         } else {
            this.execute(new Runnable() {

               @Override
               public void run() {
                  GlobalEventExecutor.this.delayedTaskQueue.add(var1);
               }
            });
         }

         return var1;
      }
   }

   @Override
   public Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5) {
      return this.terminationFuture();
   }

   public void addTask(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         this.taskQueue.add(var1);
      }
   }

   public Runnable takeTask() {
      BlockingQueue var1 = this.taskQueue;

      Runnable var5;
      do {
         ScheduledFutureTask var2 = this.delayedTaskQueue.peek();
         if (var2 == null) {
            Runnable var9 = null;

            try {
               var9 = (Runnable)var1.take();
            } catch (InterruptedException var7) {
            }

            return var9;
         }

         long var3 = var2.delayNanos();
         if (var3 > 0L) {
            try {
               var5 = (Runnable)var1.poll(var3, TimeUnit.NANOSECONDS);
            } catch (InterruptedException var8) {
               return null;
            }
         } else {
            var5 = (Runnable)var1.poll();
         }

         if (var5 == null) {
            this.fetchFromDelayedQueue();
            var5 = (Runnable)var1.poll();
         }
      } while (var5 == null);

      return var5;
   }

   @Override
   public void execute(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         this.addTask(var1);
         if (!this.inEventLoop()) {
            this.startThread();
         }
      }
   }

   @Override
   public boolean inEventLoop(Thread var1) {
      return var1 == this.thread;
   }

   @Override
   public ScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6) {
      if (var1 == null) {
         throw new NullPointerException("command");
      } else if (var6 == null) {
         throw new NullPointerException("unit");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", var2));
      } else if (var4 <= 0L) {
         throw new IllegalArgumentException(String.format("period: %d (expected: > 0)", var4));
      } else {
         return this.schedule(
            new ScheduledFutureTask(
               this, this.delayedTaskQueue, Executors.callable(var1, null), ScheduledFutureTask.deadlineNanos(var6.toNanos(var2)), var6.toNanos(var4)
            )
         );
      }
   }

   @Override
   public Future<?> terminationFuture() {
      return this.terminationFuture;
   }

   public final class PurgeTask implements Runnable {

      public PurgeTask() {
      }

      @Override
      public void run() {
         Iterator var1 = GlobalEventExecutor.this.delayedTaskQueue.iterator();

         while (var1.hasNext()) {
            ScheduledFutureTask var2 = (ScheduledFutureTask)var1.next();
            if (var2.isCancelled()) {
               var1.remove();
            }
         }
      }
   }

   public final class TaskRunner implements Runnable {
      // $VF: synthetic field
      public final boolean $assertionsDisabled = !GlobalEventExecutor.class.desiredAssertionStatus();

      @Override
      public void run() {
         while (true) {
            Runnable var1 = GlobalEventExecutor.this.takeTask();
            if (var1 != null) {
               try {
                  var1.run();
               } catch (Throwable var3) {
                  GlobalEventExecutor.logger.warn("Unexpected exception from the global event executor: ", var3);
               }

               if (var1 != GlobalEventExecutor.this.purgeTask) {
                  continue;
               }
            }

            if (GlobalEventExecutor.this.taskQueue.isEmpty() && GlobalEventExecutor.this.delayedTaskQueue.size() == 1) {
               boolean var2 = GlobalEventExecutor.this.started.compareAndSet(true, false);
               if (!$assertionsDisabled && !var2) {
                  throw new AssertionError();
               }

               if (GlobalEventExecutor.this.taskQueue.isEmpty() && GlobalEventExecutor.this.delayedTaskQueue.size() == 1
                  || !GlobalEventExecutor.this.started.compareAndSet(false, true)) {
                  return;
               }
            }
         }
      }
   }
}
