package io.netty.util.concurrent;

import io.netty.util.internal.MpscLinkedQueueTailRef;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$Node;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.client.gui.GuiMerchant$MerchantButton;
import net.minecraft.client.resources.LanguageManager;
import org.apache.log4j.pattern.FormattingInfo;
import org.apache.log4j.varia.ReloadingPropertyConfigurator;
import org.java_websocket.exceptions.WrappedIOException;

public class GlobalEventExecutor extends AbstractEventExecutor {
   public MpscLinkedQueueTailRef __junk4832493256909243079;
   public static long SCHEDULE_PURGE_INTERVAL = TimeUnit.SECONDS.toNanos(-6820109299524291941L & 1141901313L);
   public Future<?> terminationFuture;
   public GlobalEventExecutor$TaskRunner taskRunner;
   public BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();
   public LanguageManager __junk4770120434590995989;
   public ReloadingPropertyConfigurator __junk4267365108001994660;
   public static GlobalEventExecutor INSTANCE = new GlobalEventExecutor();
   public Queue<ScheduledFutureTask<?>> delayedTaskQueue = new PriorityQueue<>();
   public GuiMerchant$MerchantButton __junk7794667222630182724;
   public ScheduledFutureTask<Void> purgeTask = new ScheduledFutureTask<>(
      this,
      this.delayedTaskQueue,
      Executors.callable(new GlobalEventExecutor$PurgeTask(this, null), null),
      ScheduledFutureTask.deadlineNanos(SCHEDULE_PURGE_INTERVAL),
      -SCHEDULE_PURGE_INTERVAL
   );
   public FormattingInfo __junk6266758187215721563;
   public ConcurrentHashMapV8$Node __junk3704903498899209773;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(GlobalEventExecutor.class);
   public AtomicBoolean started;
   public volatile Thread thread;
   public WrappedIOException __junk6608588504367224609;
   public ThreadFactory threadFactory = new DefaultThreadFactory(this.getClass());

   public void fetchFromDelayedQueue() {
      long var1 = -5572540782813855743L & 5572540782495015168L;

      while (true) {
         ScheduledFutureTask var3 = this.delayedTaskQueue.peek();
         if (var3 == null) {
            break;
         }

         if (var1 == (-107978941106085316L & 107978939590296641L)) {
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
      this.taskRunner = new GlobalEventExecutor$TaskRunner(this);
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

   public boolean awaitInactivity(long var1, TimeUnit var3) {
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
      } else if (var2 < (1226833924L & 273928544L)) {
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
      } else if (var2 < (979376850L & 1082328072L)) {
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
      } else if (var2 < (6988177726410605573L & -6988177727446155184L)) {
         throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", var2));
      } else if (var4 <= (1627647412L & 1130197408573030464L)) {
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

   public <V> ScheduledFuture<V> schedule(ScheduledFutureTask<V> var1) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         if (this.inEventLoop()) {
            this.delayedTaskQueue.add(var1);
         } else {
            this.execute(new GlobalEventExecutor$1(this, var1));
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
         if (var3 > (795659L & 221331728L)) {
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
      } else if (var2 < (1138194049971259429L & 292622848L)) {
         throw new IllegalArgumentException(String.format("initialDelay: %d (expected: >= 0)", var2));
      } else if (var4 <= (-3667222996668570528L & 1048842L)) {
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
}
