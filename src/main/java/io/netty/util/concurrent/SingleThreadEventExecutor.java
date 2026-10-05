package io.netty.util.concurrent;

import com.cheatbreaker.client.ui.element.module.ModulePreviewContainer;
import io.netty.channel.nio.AbstractNioByteChannel;
import io.netty.handler.codec.compression.ZlibUtil;
import io.netty.handler.codec.rtsp.RtspVersions;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.block.BlockBanner;
import net.minecraft.client.main.ProxyAuthenticator;
import net.minecraft.client.particle.EntitySnowShovelFX;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.realms.RealmsConnect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import org.apache.log4j.pattern.ThreadPatternConverter;
import org.java_websocket.framing.BinaryFrame;
import org.newsclub.net.unix.NarSystem;

public abstract class SingleThreadEventExecutor extends AbstractEventExecutor {
   public static long SCHEDULE_PURGE_INTERVAL;
   public static AtomicIntegerFieldUpdater<SingleThreadEventExecutor> STATE_UPDATER;
   public long gracefulShutdownStartTime;
   public Set<Runnable> shutdownHooks;
   public Queue<ScheduledFutureTask<?>> delayedTaskQueue = new PriorityQueue<>();
   public static final int ST_SHUTTING_DOWN = 3;
   public Semaphore threadLock = new Semaphore(0);
   public Queue<Runnable> taskQueue;
   public volatile long gracefulShutdownTimeout;
   public static final int ST_NOT_STARTED = 1;
   public volatile long gracefulShutdownQuietPeriod;
   public static final int ST_TERMINATED = 5;
   public boolean addTaskWakesUp;
   public static final int ST_SHUTDOWN = 4;
   public volatile int state;
   public static final int ST_STARTED = 2;
   public EventExecutorGroup parent;
   public static final boolean $assertionsDisabled = !SingleThreadEventExecutor.class.desiredAssertionStatus();
   public Promise<?> terminationFuture;
   public Thread thread;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(SingleThreadEventExecutor.class);
   public static Runnable WAKEUP_TASK = new Runnable() {

      @Override
      public void run() {
      }
   };
   public long lastExecutionTime;

   public boolean runAllTasks() {
      this.fetchFromDelayedQueue();
      Runnable var1 = this.pollTask();
      if (var1 == null) {
         return false;
      } else {
         do {
            try {
               var1.run();
            } catch (Throwable var3) {
               logger.warn("A task raised an exception.", var3);
            }

            var1 = this.pollTask();
         } while (var1 != null);

         this.lastExecutionTime = ScheduledFutureTask.nanoTime();
         return true;
      }
   }

   public boolean hasTasks() {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         return !this.taskQueue.isEmpty();
      }
   }

   public void addTask(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         if (this.isShutdown()) {
            reject();
         }

         this.taskQueue.add(var1);
      }
   }

   public static void reject() {
      throw new RejectedExecutionException("event executor terminated");
   }

   public Runnable takeTask() {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else if (!(this.taskQueue instanceof BlockingQueue)) {
         throw new UnsupportedOperationException();
      } else {
         BlockingQueue var1 = (BlockingQueue)this.taskQueue;

         Runnable var5;
         do {
            ScheduledFutureTask var2 = this.delayedTaskQueue.peek();
            if (var2 == null) {
               Runnable var9 = null;

               try {
                  var9 = (Runnable)var1.take();
                  if (var9 == WAKEUP_TASK) {
                     var9 = null;
                  }
               } catch (InterruptedException var7) {
               }

               return var9;
            }

            long var3 = var2.delayNanos();
            var5 = null;
            if (var3 > 0L) {
               try {
                  var5 = (Runnable)var1.poll(var3, TimeUnit.NANOSECONDS);
               } catch (InterruptedException var8) {
                  return null;
               }
            }

            if (var5 == null) {
               this.fetchFromDelayedQueue();
               var5 = (Runnable)var1.poll();
            }
         } while (var5 == null);

         return var5;
      }
   }

   public boolean runShutdownHooks() {
      boolean var1 = false;

      while (!this.shutdownHooks.isEmpty()) {
         ArrayList var2 = new ArrayList<>(this.shutdownHooks);
         this.shutdownHooks.clear();

         for (Runnable var4 : (Iterable<Runnable>)(Iterable<?>)(var2)) {
            try {
               var4.run();
            } catch (Throwable var9) {
               logger.warn("Shutdown hook raised an exception.", var9);
            } finally {
               var1 = true;
            }
         }
      }

      if (var1) {
         this.lastExecutionTime = ScheduledFutureTask.nanoTime();
      }

      return var1;
   }

   @Override
   public void execute(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         boolean var2 = this.inEventLoop();
         if (var2) {
            this.addTask(var1);
         } else {
            this.startThread();
            this.addTask(var1);
            if (this.isShutdown() && this.removeTask(var1)) {
               reject();
            }
         }

         if (!this.addTaskWakesUp && this.wakesUpForTask(var1)) {
            this.wakeup(var2);
         }
      }
   }

   @Override
   public boolean isShutdown() {
      return STATE_UPDATER.get(this) >= 4;
   }

   public void cancelDelayedTasks() {
      if (!this.delayedTaskQueue.isEmpty()) {
         ScheduledFutureTask[] var1 = (ScheduledFutureTask[])this.delayedTaskQueue.toArray(new ScheduledFutureTask[this.delayedTaskQueue.size()]);

         for (ScheduledFutureTask var5 : var1) {
            var5.cancel(false);
         }

         this.delayedTaskQueue.clear();
      }
   }

   public void interruptThread() {
      this.thread.interrupt();
   }

   @Override
   public EventExecutorGroup parent() {
      return this.parent;
   }

   public long delayNanos(long var1) {
      ScheduledFutureTask var3 = this.delayedTaskQueue.peek();
      return var3 == null ? SCHEDULE_PURGE_INTERVAL : var3.delayNanos(var1);
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) throws java.lang.InterruptedException {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else if (this.inEventLoop()) {
         throw new IllegalStateException("cannot await termination of the current thread");
      } else {
         if (this.threadLock.tryAcquire(var1, var3)) {
            this.threadLock.release();
         }

         return this.isTerminated();
      }
   }

   @Override
   public void shutdown() {
      if (!this.isShutdown()) {
         boolean var1 = this.inEventLoop();

         while (!this.isShuttingDown()) {
            boolean var2 = true;
            int var3 = STATE_UPDATER.get(this);
            int var4;
            if (var1) {
               var4 = 4;
            } else {
               switch (var3) {
                  case 1:
                  case 2:
                  case 3:
                     var4 = 4;
                     break;
                  default:
                     var4 = var3;
                     var2 = false;
               }
            }

            if (STATE_UPDATER.compareAndSet(this, var3, var4)) {
               if (var3 == 1) {
                  this.thread.start();
               }

               if (var2) {
                  this.wakeup(var1);
               }

               return;
            }
         }
      }
   }

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

   public void startThread() {
      if (STATE_UPDATER.get(this) == 1 && STATE_UPDATER.compareAndSet(this, 1, 2)) {
         this.delayedTaskQueue
            .add(
               new ScheduledFutureTask(
                  this,
                  this.delayedTaskQueue,
                  Executors.callable(new SingleThreadEventExecutor.PurgeTask(), null),
                  ScheduledFutureTask.deadlineNanos(SCHEDULE_PURGE_INTERVAL),
                  -SCHEDULE_PURGE_INTERVAL
               )
            );
         this.thread.start();
      }
   }

   @Override
   public boolean inEventLoop(Thread var1) {
      return var1 == this.thread;
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
                  SingleThreadEventExecutor.this.delayedTaskQueue.add(var1);
               }
            });
         }

         return var1;
      }
   }

   @Override
   public Future<?> terminationFuture() {
      return this.terminationFuture;
   }

   public Queue<Runnable> newTaskQueue() {
      return new LinkedBlockingQueue<>();
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

   public abstract void run();

   public SingleThreadEventExecutor(EventExecutorGroup var1, ThreadFactory var2, boolean var3) {
      this.shutdownHooks = new LinkedHashSet<>();
      this.state = 1;
      this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
      if (var2 == null) {
         throw new NullPointerException("threadFactory");
      } else {
         this.parent = var1;
         this.addTaskWakesUp = var3;
         this.thread = var2.newThread(
            new Runnable() {

               @Override
               public void run() {
                  boolean var1x = false;
                  SingleThreadEventExecutor.this.updateLastExecutionTime();

                  try {
                     SingleThreadEventExecutor.this.run();
                     var1x = true;
                  } catch (Throwable var112) {
                     SingleThreadEventExecutor.logger.warn("Unexpected exception from an event executor: ", var112);
                  } finally {
                     int var10;
                     do {
                        var10 = SingleThreadEventExecutor.STATE_UPDATER.get(SingleThreadEventExecutor.this);
                     } while (var10 < 3 && !SingleThreadEventExecutor.STATE_UPDATER.compareAndSet(SingleThreadEventExecutor.this, var10, 3));

                     if (var1x && SingleThreadEventExecutor.this.gracefulShutdownStartTime == 0L) {
                        SingleThreadEventExecutor.logger
                           .error(
                              "Buggy "
                                 + EventExecutor.class.getSimpleName()
                                 + " implementation; "
                                 + SingleThreadEventExecutor.class.getSimpleName()
                                 + ".confirmShutdown() must be called "
                                 + "before run() implementation terminates."
                           );
                     }

                     try {
                        while (!SingleThreadEventExecutor.this.confirmShutdown()) {
                        }
                     } finally {
                        try {
                           SingleThreadEventExecutor.this.cleanup();
                        } finally {
                           SingleThreadEventExecutor.STATE_UPDATER.set(SingleThreadEventExecutor.this, 5);
                           SingleThreadEventExecutor.this.threadLock.release();
                           if (!SingleThreadEventExecutor.this.taskQueue.isEmpty()) {
                              SingleThreadEventExecutor.logger
                                 .warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.this.taskQueue.size() + ')');
                           }

                           SingleThreadEventExecutor.this.terminationFuture.setSuccess(null);
                        }
                     }
                  }
               }
            }
         );
         this.taskQueue = this.newTaskQueue();
      }
   }

   public void updateLastExecutionTime() {
      this.lastExecutionTime = ScheduledFutureTask.nanoTime();
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

   public boolean hasScheduledTasks() {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         ScheduledFutureTask var1 = this.delayedTaskQueue.peek();
         return var1 != null && var1.deadlineNanos() <= ScheduledFutureTask.nanoTime();
      }
   }

   public void cleanup() {
   }

   public boolean removeTask(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         return this.taskQueue.remove(var1);
      }
   }

   public boolean wakesUpForTask(Runnable var1) {
      return true;
   }

   @Override
   public boolean isTerminated() {
      return STATE_UPDATER.get(this) == 5;
   }

   public Runnable pollTask() {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         Runnable var1;
         do {
            var1 = this.taskQueue.poll();
         } while (var1 == WAKEUP_TASK);

         return var1;
      }
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

   public boolean confirmShutdown() {
      if (!this.isShuttingDown()) {
         return false;
      } else if (!this.inEventLoop()) {
         throw new IllegalStateException("must be invoked from an event loop");
      } else {
         this.cancelDelayedTasks();
         if (this.gracefulShutdownStartTime == 0L) {
            this.gracefulShutdownStartTime = ScheduledFutureTask.nanoTime();
         }

         if (!this.runAllTasks() && !this.runShutdownHooks()) {
            long var1 = ScheduledFutureTask.nanoTime();
            if (this.isShutdown() || var1 - this.gracefulShutdownStartTime > this.gracefulShutdownTimeout) {
               return true;
            } else if (var1 - this.lastExecutionTime <= this.gracefulShutdownQuietPeriod) {
               this.wakeup(true);

               try {
                  Thread.sleep(100L);
               } catch (InterruptedException var4) {
               }

               return false;
            } else {
               return true;
            }
         } else if (this.isShutdown()) {
            return true;
         } else {
            this.wakeup(true);
            return false;
         }
      }
   }

   public void addShutdownHook(final Runnable var1) {
      if (this.inEventLoop()) {
         this.shutdownHooks.add(var1);
      } else {
         this.execute(new Runnable() {

            @Override
            public void run() {
               SingleThreadEventExecutor.this.shutdownHooks.add(var1);
            }
         });
      }
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

   static {
      AtomicIntegerFieldUpdater var0 = PlatformDependent.newAtomicIntegerFieldUpdater(SingleThreadEventExecutor.class, "state");
      if (var0 == null) {
         var0 = AtomicIntegerFieldUpdater.newUpdater(SingleThreadEventExecutor.class, "state");
      }

      STATE_UPDATER = var0;
      SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(1L);
   }

   @Override
   public Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("quietPeriod: " + var1 + " (expected >= 0)");
      } else if (var3 < var1) {
         throw new IllegalArgumentException("timeout: " + var3 + " (expected >= quietPeriod (" + var1 + "))");
      } else if (var5 == null) {
         throw new NullPointerException("unit");
      } else if (this.isShuttingDown()) {
         return this.terminationFuture();
      } else {
         boolean var6 = this.inEventLoop();

         while (!this.isShuttingDown()) {
            boolean var7 = true;
            int var8 = STATE_UPDATER.get(this);
            int var9;
            if (var6) {
               var9 = 3;
            } else {
               switch (var8) {
                  case 1:
                  case 2:
                     var9 = 3;
                     break;
                  default:
                     var9 = var8;
                     var7 = false;
               }
            }

            if (STATE_UPDATER.compareAndSet(this, var8, var9)) {
               this.gracefulShutdownQuietPeriod = var5.toNanos(var1);
               this.gracefulShutdownTimeout = var5.toNanos(var3);
               if (var8 == 1) {
                  this.thread.start();
               }

               if (var7) {
                  this.wakeup(var6);
               }

               return this.terminationFuture();
            }
         }

         return this.terminationFuture();
      }
   }

   public int pendingTasks() {
      return this.taskQueue.size();
   }

   public void removeShutdownHook(final Runnable var1) {
      if (this.inEventLoop()) {
         this.shutdownHooks.remove(var1);
      } else {
         this.execute(new Runnable() {

            @Override
            public void run() {
               SingleThreadEventExecutor.this.shutdownHooks.remove(var1);
            }
         });
      }
   }

   @Override
   public boolean isShuttingDown() {
      return STATE_UPDATER.get(this) >= 3;
   }

   public void wakeup(boolean var1) {
      if (!var1 || STATE_UPDATER.get(this) == 3) {
         this.taskQueue.add(WAKEUP_TASK);
      }
   }

   public boolean runAllTasks(long var1) {
      this.fetchFromDelayedQueue();
      Runnable var3 = this.pollTask();
      if (var3 == null) {
         return false;
      } else {
         long var4 = ScheduledFutureTask.nanoTime() + var1;
         long var6 = 0L;

         long var8;
         while (true) {
            try {
               var3.run();
            } catch (Throwable var11) {
               logger.warn("A task raised an exception.", var11);
            }

            if ((++var6 & 63L) == 0L) {
               var8 = ScheduledFutureTask.nanoTime();
               if (var8 >= var4) {
                  break;
               }
            }

            var3 = this.pollTask();
            if (var3 == null) {
               var8 = ScheduledFutureTask.nanoTime();
               break;
            }
         }

         this.lastExecutionTime = var8;
         return true;
      }
   }

   public Runnable peekTask() {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         return this.taskQueue.peek();
      }
   }

   public final class PurgeTask implements Runnable {

      @Override
      public void run() {
         Iterator var1 = SingleThreadEventExecutor.this.delayedTaskQueue.iterator();

         while (var1.hasNext()) {
            ScheduledFutureTask var2 = (ScheduledFutureTask)var1.next();
            if (var2.isCancelled()) {
               var1.remove();
            }
         }
      }

      public PurgeTask() {
      }
   }
}
