package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Collections;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage;
import net.optifine.shaders.SVertexBuilder;

public class HashedWheelTimer implements Timer {
   public static int WORKER_STATE_INIT;
   public CountDownLatch startTimeInitialized;
   public HashedWheelTimer$Worker worker = new HashedWheelTimer$Worker(this, null);
   public HashedWheelTimer$HashedWheelBucket[] wheel;
   public volatile int workerState = 0;
   public Queue<HashedWheelTimer$HashedWheelTimeout> timeouts;
   public Queue<Runnable> cancelledTimeouts;
   public static int WORKER_STATE_SHUTDOWN;
   public Thread workerThread;
   public static int WORKER_STATE_STARTED;
   public ResourceLeak leak;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(HashedWheelTimer.class);
   public long tickDuration;
   public static ResourceLeakDetector<HashedWheelTimer> leakDetector = new ResourceLeakDetector<>(
      HashedWheelTimer.class, 1, (long)(Runtime.getRuntime().availableProcessors() * 4)
   );
   public volatile long startTime;
   public VertexFormatElement$EnumUsage __junk617576501353899428;
   public static AtomicIntegerFieldUpdater<HashedWheelTimer> WORKER_STATE_UPDATER;
   public int mask;
   public SVertexBuilder __junk5627625257778639815;

   public static int normalizeTicksPerWheel(int var0) {
      byte var1 = 1;

      while (var1 < var0) {
         var1 <<= 1;
      }

      return var1;
   }

   public HashedWheelTimer(ThreadFactory var1, long var2, TimeUnit var4) {
      this(var1, var2, var4, 512);
   }

   public HashedWheelTimer() {
      this(Executors.defaultThreadFactory());
   }

   public void start() {
      switch (WORKER_STATE_UPDATER.get(this)) {
         case 0:
            if (WORKER_STATE_UPDATER.compareAndSet(this, 0, 1)) {
               this.workerThread.start();
            }
         case 1:
            break;
         case 2:
            throw new IllegalStateException("cannot be started once stopped");
         default:
            throw new Error("Invalid WorkerState");
      }

      while (this.startTime == (940873834L & -8636160692556062588L)) {
         try {
            this.startTimeInitialized.await();
         } catch (InterruptedException var2) {
         }
      }
   }

   public HashedWheelTimer(long var1, TimeUnit var3, int var4) {
      this(Executors.defaultThreadFactory(), var1, var3, var4);
   }

   @Override
   public Timeout newTimeout(TimerTask var1, long var2, TimeUnit var4) {
      if (var1 == null) {
         throw new NullPointerException("task");
      } else if (var4 == null) {
         throw new NullPointerException("unit");
      } else {
         this.start();
         long var5 = System.nanoTime() + var4.toNanos(var2) - this.startTime;
         HashedWheelTimer$HashedWheelTimeout var7 = new HashedWheelTimer$HashedWheelTimeout(this, var1, var5);
         this.timeouts.add(var7);
         return var7;
      }
   }

   public HashedWheelTimer(ThreadFactory var1) {
      this(var1, -6123887743268286746L & 1214128244L, TimeUnit.MILLISECONDS);
   }

   public HashedWheelTimer(ThreadFactory var1, long var2, TimeUnit var4, int var5) {
      this.startTimeInitialized = new CountDownLatch(1);
      this.timeouts = PlatformDependent.newMpscQueue();
      this.cancelledTimeouts = PlatformDependent.newMpscQueue();
      if (var1 == null) {
         throw new NullPointerException("threadFactory");
      } else if (var4 == null) {
         throw new NullPointerException("unit");
      } else if (var2 <= (-199928027855838904L & 2654848L)) {
         throw new IllegalArgumentException("tickDuration must be greater than 0: " + var2);
      } else if (var5 <= 0) {
         throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + var5);
      } else {
         this.wheel = createWheel(var5);
         this.mask = this.wheel.length - 1;
         this.tickDuration = var4.toNanos(var2);
         if (this.tickDuration >= (-1L & Long.MAX_VALUE) / this.wheel.length) {
            throw new IllegalArgumentException(
               String.format("tickDuration: %d (expected: 0 < tickDuration in nanos < %d", var2, (-1L & Long.MAX_VALUE) / this.wheel.length)
            );
         } else {
            this.workerThread = var1.newThread(this.worker);
            this.leak = leakDetector.open(this);
         }
      }
   }

   public static HashedWheelTimer$HashedWheelBucket[] createWheel(int var0) {
      if (var0 <= 0) {
         throw new IllegalArgumentException("ticksPerWheel must be greater than 0: " + var0);
      } else if (var0 > 1073741824) {
         throw new IllegalArgumentException("ticksPerWheel may not be greater than 2^30: " + var0);
      } else {
         var0 = normalizeTicksPerWheel(var0);
         HashedWheelTimer$HashedWheelBucket[] var1 = new HashedWheelTimer$HashedWheelBucket[var0];

         for (int var2 = 0; var2 < var1.length; var2++) {
            var1[var2] = new HashedWheelTimer$HashedWheelBucket(null);
         }

         return var1;
      }
   }

   static {
      AtomicIntegerFieldUpdater var0 = PlatformDependent.newAtomicIntegerFieldUpdater(HashedWheelTimer.class, "workerState");
      if (var0 == null) {
         var0 = AtomicIntegerFieldUpdater.newUpdater(HashedWheelTimer.class, "workerState");
      }

      WORKER_STATE_UPDATER = var0;
   }

   public HashedWheelTimer(long var1, TimeUnit var3) {
      this(Executors.defaultThreadFactory(), var1, var3);
   }

   @Override
   public Set<Timeout> stop() {
      if (Thread.currentThread() == this.workerThread) {
         throw new IllegalStateException(HashedWheelTimer.class.getSimpleName() + ".stop() cannot be called from " + TimerTask.class.getSimpleName());
      } else if (!WORKER_STATE_UPDATER.compareAndSet(this, 1, 2)) {
         WORKER_STATE_UPDATER.set(this, 2);
         if (this.leak != null) {
            this.leak.close();
         }

         return Collections.emptySet();
      } else {
         boolean var1 = false;

         while (this.workerThread.isAlive()) {
            this.workerThread.interrupt();

            try {
               this.workerThread.join(1074528628L & -492569049572532123L);
            } catch (InterruptedException var3) {
               var1 = true;
            }
         }

         if (var1) {
            Thread.currentThread().interrupt();
         }

         if (this.leak != null) {
            this.leak.close();
         }

         return this.worker.unprocessedTimeouts();
      }
   }
}
