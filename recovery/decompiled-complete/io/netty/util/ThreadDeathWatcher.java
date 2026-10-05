package io.netty.util;

import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4JLoggerFactory;
import java.util.Queue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.creativetab.CreativeTabs$1;
import net.optifine.expr.FunctionBool;

public class ThreadDeathWatcher {
   public CreativeTabs$1 __junk2181218275412385350;
   public static ThreadFactory threadFactory = new DefaultThreadFactory(ThreadDeathWatcher.class, true, 1);
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ThreadDeathWatcher.class);
   public static volatile Thread watcherThread;
   public Log4JLoggerFactory __junk3756547461765242898;
   public BlockStoneBrick __junk7817897197201792930;
   public static AtomicBoolean started = new AtomicBoolean();
   public static ThreadDeathWatcher$Watcher watcher = new ThreadDeathWatcher$Watcher(null);
   public FunctionBool __junk2098309233866582990;
   public static Queue<ThreadDeathWatcher$Entry> pendingEntries = PlatformDependent.newMpscQueue();

   public static void watch(Thread var0, Runnable var1) {
      if (var0 == null) {
         throw new NullPointerException("thread");
      } else if (var1 == null) {
         throw new NullPointerException("task");
      } else if (!var0.isAlive()) {
         throw new IllegalArgumentException("thread must be alive.");
      } else {
         schedule(var0, var1, true);
      }
   }

   public static void unwatch(Thread var0, Runnable var1) {
      if (var0 == null) {
         throw new NullPointerException("thread");
      } else if (var1 == null) {
         throw new NullPointerException("task");
      } else {
         schedule(var0, var1, false);
      }
   }

   public static boolean awaitInactivity(long var0, TimeUnit var2) {
      if (var2 == null) {
         throw new NullPointerException("unit");
      } else {
         Thread var3 = watcherThread;
         if (var3 != null) {
            var3.join(var2.toMillis(var0));
            return !var3.isAlive();
         } else {
            return true;
         }
      }
   }

   public static void schedule(Thread var0, Runnable var1, boolean var2) {
      pendingEntries.add(new ThreadDeathWatcher$Entry(var0, var1, var2));
      if (started.compareAndSet(false, true)) {
         Thread var3 = threadFactory.newThread(watcher);
         var3.start();
         watcherThread = var3;
      }
   }
}
