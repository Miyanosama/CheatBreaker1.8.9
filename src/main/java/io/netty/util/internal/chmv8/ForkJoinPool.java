package io.netty.util.internal.chmv8;

import com.cheatbreaker.client.event.type.RenderWorldEvent;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.ssl.JdkSslContext;
import io.netty.util.internal.ThreadLocalRandom;
import java.lang.Thread.State;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import javax.vecmath.Vector3d;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.chunk.storage.ChunkLoader;
import net.optifine.entity.model.ModelAdapterSpider;
import net.optifine.entity.model.ModelAdapterVillager;
import com.cheatbreaker.client.ui.mainmenu.SmallAnimatedLogoElement;
import com.cheatbreaker.client.ui.module.ModuleResizeSnapshot;
import sun.misc.Unsafe;

public class ForkJoinPool extends AbstractExecutorService {
   public static final int AC_SHIFT = 48;
   public static final int SQMASK = 126;
   public static long STEALCOUNT;
   public static final long TC_MASK = 281470681743360L;
   public static long CTL;
   public static final long IDLE_TIMEOUT = 2000000000L;
   public static final long TC_UNIT = 4294967296L;
   public UncaughtExceptionHandler ueh;
   public static final int MAX_CAP = 32767;
   public static long PARKBLOCKER;
   public short mode;
   public volatile Object pad11;
   public static final int PL_SPINS = 256;
   public volatile Object pad14;
   public volatile Object pad19;
   public static final long AC_MASK = -281474976710656L;
   public volatile long pad01;
   public static long PLOCK;
   public static final int UAC_UNIT = 65536;
   public volatile long pad04;
   public static final int SHARED_QUEUE = -1;
   public static final int UAC_MASK = -65536;
   public static final int UTC_SHIFT = 0;
   public static final long STOP_BIT = 2147483648L;
   public volatile Object pad10;
   public volatile long stealCount;
   public static long QLOCK;
   public static Unsafe U;
   public static ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory;
   public ForkJoinPool.WorkQueue[] workQueues;
   public volatile Object pad16;
   public volatile Object pad12;
   public volatile long pad06;
   public volatile int plock;
   public static int ASHIFT;
   public static final int FIFO_QUEUE = 1;
   public static long QBASE;
   public static final long AC_UNIT = 281474976710656L;
   public static final int EVENMASK = 65534;
   public volatile Object pad15;
   public static final int MAX_HELP = 64;
   public volatile long pad05;
   public volatile long ctl;
   public static long INDEXSEED;
   public static final int SHORT_SIGN = 32768;
   public static final int UAC_SHIFT = 16;
   public volatile Object pad1a;
   public static ForkJoinPool common;
   public static final int INT_SIGN = -2147483648;
   public volatile Object pad13;
   public ForkJoinPool.ForkJoinWorkerThreadFactory factory;
   public volatile Object pad17;
   public volatile long pad02;
   public static final int EC_SHIFT = 16;
   public short parallelism;
   public static final int ST_SHIFT = 31;
   public static RuntimePermission modifyThreadPermission;
   public static final int SHUTDOWN = -2147483648;
   public static final int SEED_INCREMENT = 1640531527;
   public volatile long pad03;
   public static final int LIFO_QUEUE = 0;
   public static final int UTC_UNIT = 1;
   public String workerNamePrefix;
   public static final int E_MASK = 2147483647;
   public volatile long pad00;
   public volatile int indexSeed;
   public volatile Object pad1b;
   public static final int PL_LOCK = 2;
   public static final int PL_SIGNAL = 1;
   public volatile Object pad18;
   public static int commonParallelism;
   public static final int UTC_MASK = 65535;
   public static int ABASE;
   public static final long FAST_IDLE_TIMEOUT = 200000000L;
   public static int poolNumberSequence;
   public static final int SMASK = 65535;
   public static final long TIMEOUT_SLOP = 2000000L;
   public static final int TC_SHIFT = 32;
   public static ThreadLocal<ForkJoinPool.Submitter> submitters;
   public static final int E_SEQ = 65536;

   public <T> ForkJoinTask<T> submit(Callable<T> var1) {
      ForkJoinTask.AdaptedCallable var2 = new ForkJoinTask.AdaptedCallable(var1);
      this.externalPush(var2);
      return var2;
   }

   public static int checkParallelism(int var0) {
      if (var0 > 0 && var0 <= 32767) {
         return var0;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public ForkJoinTask<?> submit(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         Object var2;
         if (var1 instanceof ForkJoinTask) {
            var2 = (ForkJoinTask)var1;
         } else {
            var2 = new ForkJoinTask.AdaptedRunnableAction(var1);
         }

         this.externalPush((ForkJoinTask<?>)var2);
         return (ForkJoinTask<?>)var2;
      }
   }

   static {
      try {
         U = getUnsafe();
         Class<ForkJoinPool> var0 = ForkJoinPool.class;
         CTL = U.objectFieldOffset(var0.getDeclaredField("ctl"));
         STEALCOUNT = U.objectFieldOffset(var0.getDeclaredField("stealCount"));
         PLOCK = U.objectFieldOffset(var0.getDeclaredField("plock"));
         INDEXSEED = U.objectFieldOffset(var0.getDeclaredField("indexSeed"));
         Class<Thread> var1 = Thread.class;
         PARKBLOCKER = U.objectFieldOffset(var1.getDeclaredField("parkBlocker"));
         Class<ForkJoinPool.WorkQueue> var2 = ForkJoinPool.WorkQueue.class;
         QBASE = U.objectFieldOffset(var2.getDeclaredField("base"));
         QLOCK = U.objectFieldOffset(var2.getDeclaredField("qlock"));
         Class<ForkJoinTask[]> var3 = ForkJoinTask[].class;
         ABASE = U.arrayBaseOffset(var3);
         int var4 = U.arrayIndexScale(var3);
         if ((var4 & var4 - 1) != 0) {
            throw new Error("data type scale not a power of two");
         }

         ASHIFT = 31 - Integer.numberOfLeadingZeros(var4);
      } catch (Exception var5) {
         throw new Error(var5);
      }

      submitters = new ThreadLocal<>();
      defaultForkJoinWorkerThreadFactory = new ForkJoinPool.DefaultForkJoinWorkerThreadFactory();
      modifyThreadPermission = new RuntimePermission("modifyThread");
      common = AccessController.doPrivileged(new PrivilegedAction<ForkJoinPool>() {
         public ForkJoinPool run() {
            return ForkJoinPool.makeCommonPool();
         }
      });
      short var6 = common.parallelism;
      commonParallelism = var6 > 0 ? var6 : 1;
   }

   public boolean awaitQuiescence(long var1, TimeUnit var3) {
      long var4 = var3.toNanos(var1);
      Thread var7 = Thread.currentThread();
      ForkJoinWorkerThread var6;
      if (var7 instanceof ForkJoinWorkerThread && (var6 = (ForkJoinWorkerThread)var7).pool == this) {
         this.helpQuiescePool(var6.workQueue);
         return true;
      } else {
         long var8 = System.nanoTime();
         int var11 = 0;
         boolean var13 = true;

         while (!this.isQuiescent()) {
            ForkJoinPool.WorkQueue[] var10 = this.workQueues;
            int var12;
            if (this.workQueues == null || (var12 = var10.length - 1) < 0) {
               break;
            }

            if (!var13) {
               if (System.nanoTime() - var8 > var4) {
                  return false;
               }

               Thread.yield();
            }

            var13 = false;

            for (int var14 = var12 + 1 << 2; var14 >= 0; var14--) {
               ForkJoinPool.WorkQueue var16;
               if ((var16 = var10[var11++ & var12]) != null) {
                  int var17 = var16.base;
                  if (var16.base - var16.top < 0) {
                     var13 = true;
                     ForkJoinTask var15;
                     if ((var15 = var16.pollAt(var17)) != null) {
                        var15.doExec();
                     }
                     break;
                  }
               }
            }
         }

         return true;
      }
   }

   public boolean tryTerminate(boolean var1, boolean var2) {
      if (this == common) {
         return false;
      } else {
         int var3 = this.plock;
         if (this.plock >= 0) {
            if (!var2) {
               return false;
            }

            label131: {
               if ((var3 & 2) == 0) {
                  var3 += 2;
                  if (U.compareAndSwapInt(this, PLOCK, var3, var3)) {
                     break label131;
                  }
               }

               var3 = this.acquirePlock();
            }

            int var4 = var3 + 2 & 2147483647 | -2147483648;
            if (!U.compareAndSwapInt(this, PLOCK, var3, var4)) {
               this.releasePlock(var4);
            }
         }

         while (true) {
            long var21 = this.ctl;
            if ((this.ctl & 2147483648L) != 0L) {
               if ((short)(var21 >>> 32) + this.parallelism <= 0) {
                  synchronized (this) {
                     this.notifyAll();
                  }
               }

               return true;
            }

            if (!var1) {
               if ((int)(var21 >> 48) + this.parallelism > 0) {
                  return false;
               }

               ForkJoinPool.WorkQueue[] var6 = this.workQueues;
               if (this.workQueues != null) {
                  for (int var8 = 0; var8 < var6.length; var8++) {
                     ForkJoinPool.WorkQueue var7;
                     if ((var7 = var6[var8]) != null && (!var7.isEmpty() || (var8 & 1) != 0 && var7.eventCount >= 0)) {
                        this.signalWork(var6, var7);
                        return false;
                     }
                  }
               }
            }

            if (U.compareAndSwapLong(this, CTL, var21, var21 | 2147483648L)) {
               for (int var22 = 0; var22 < 3; var22++) {
                  ForkJoinPool.WorkQueue[] var23 = this.workQueues;
                  if (this.workQueues != null) {
                     int var10 = var23.length;

                     for (int var11 = 0; var11 < var10; var11++) {
                        ForkJoinPool.WorkQueue var24;
                        if ((var24 = var23[var11]) != null) {
                           var24.qlock = -1;
                           if (var22 > 0) {
                              var24.cancelAll();
                              if (var22 > 1) {
                                 ForkJoinWorkerThread var9 = var24.owner;
                                 if (var24.owner != null) {
                                    if (!var9.isInterrupted()) {
                                       try {
                                          var9.interrupt();
                                       } catch (Throwable var19) {
                                       }
                                    }

                                    U.unpark(var9);
                                 }
                              }
                           }
                        }
                     }

                     while (true) {
                        long var13 = this.ctl;
                        int var12;
                        ForkJoinPool.WorkQueue var25;
                        int var26;
                        if ((var12 = (int)this.ctl & 2147483647) == 0 || (var26 = var12 & 65535) >= var10 || var26 < 0 || (var25 = var23[var26]) == null) {
                           break;
                        }

                        long var16 = var25.nextWait & 2147483647 | var13 + 281474976710656L & -281474976710656L | var13 & 281472829227008L;
                        if (var25.eventCount == (var12 | -2147483648) && U.compareAndSwapLong(this, CTL, var13, var16)) {
                           var25.eventCount = var12 + 65536 & 2147483647;
                           var25.qlock = -1;
                           Thread var15 = var25.parker;
                           if (var25.parker != null) {
                              U.unpark(var15);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public <T> RunnableFuture<T> newTaskFor(Callable<T> var1) {
      return new ForkJoinTask.AdaptedCallable<>(var1);
   }

   public int scan(ForkJoinPool.WorkQueue var1, int var2) {
      long var5 = this.ctl;
      ForkJoinPool.WorkQueue[] var3 = this.workQueues;
      int var4;
      if (this.workQueues != null && (var4 = var3.length - 1) >= 0 && var1 != null) {
         int var7 = var4 + var4 + 1;
         int var8 = var1.eventCount;

         while (true) {
            ForkJoinPool.WorkQueue var9;
            if ((var9 = var3[var2 - var7 & var4]) != null) {
               int var10 = var9.base;
               if (var9.base - var9.top < 0) {
                  ForkJoinTask[] var12 = var9.array;
                  if (var9.array != null) {
                     long var16 = ((var12.length - 1 & var10) << ASHIFT) + ABASE;
                     ForkJoinTask var13;
                     if ((var13 = (ForkJoinTask)U.getObjectVolatile(var12, var16)) != null) {
                        if (var8 < 0) {
                           this.helpRelease(var5, var3, var1, var9, var10);
                        } else if (var9.base == var10 && U.compareAndSwapObject(var12, var16, var13, null)) {
                           U.putOrderedInt(var9, QBASE, var10 + 1);
                           if (var10 + 1 - var9.top < 0) {
                              this.signalWork(var3, var9);
                           }

                           var1.runTask(var13);
                        }
                     }
                     break;
                  }
               }
            }

            if (--var7 < 0) {
               int var11;
               if ((var8 | (var11 = (int)var5)) < 0) {
                  return this.awaitWork(var1, var5, var8);
               }

               if (this.ctl == var5) {
                  long var14 = var8 | var5 - 281474976710656L & -4294967296L;
                  var1.nextWait = var11;
                  var1.eventCount = var8 | -2147483648;
                  if (!U.compareAndSwapLong(this, CTL, var5, var14)) {
                     var1.eventCount = var8;
                  }
               }
               break;
            }
         }
      }

      return 0;
   }

   public int getParallelism() {
      short var1 = this.parallelism;
      return this.parallelism > 0 ? var1 : 1;
   }

   public static ForkJoinPool.ForkJoinWorkerThreadFactory checkFactory(ForkJoinPool.ForkJoinWorkerThreadFactory var0) {
      if (var0 == null) {
         throw new NullPointerException();
      } else {
         return var0;
      }
   }

   @Override
   public boolean isShutdown() {
      return this.plock < 0;
   }

   public ForkJoinTask<?> pollSubmission() {
      ForkJoinPool.WorkQueue[] var1 = this.workQueues;
      if (this.workQueues != null) {
         for (int var4 = 0; var4 < var1.length; var4 += 2) {
            ForkJoinPool.WorkQueue var2;
            ForkJoinTask var3;
            if ((var2 = var1[var4]) != null && (var3 = var2.poll()) != null) {
               return var3;
            }
         }
      }

      return null;
   }

   public UncaughtExceptionHandler getUncaughtExceptionHandler() {
      return this.ueh;
   }

   public <T> ForkJoinTask<T> submit(ForkJoinTask<T> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.externalPush(var1);
         return var1;
      }
   }

   public boolean tryCompensate(long var1) {
      ForkJoinPool.WorkQueue[] var3 = this.workQueues;
      short var4 = this.parallelism;
      int var5 = (int)var1;
      int var6;
      if (var3 != null && (var6 = var3.length - 1) >= 0 && var5 >= 0 && this.ctl == var1) {
         ForkJoinPool.WorkQueue var8 = var3[var5 & var6];
         if (var5 != 0 && var8 != null) {
            long var10 = var8.nextWait & 2147483647 | var1 & -4294967296L;
            int var18 = var5 + 65536 & 2147483647;
            if (var8.eventCount == (var5 | -2147483648) && U.compareAndSwapLong(this, CTL, var1, var10)) {
               var8.eventCount = var18;
               Thread var17 = var8.parker;
               if (var8.parker != null) {
                  U.unpark(var17);
               }

               return true;
            }
         } else {
            short var7;
            if ((var7 = (short)(var1 >>> 32)) >= 0 && (int)(var1 >> 48) + var4 > 1) {
               long var16 = var1 - 281474976710656L & -281474976710656L | var1 & 281474976710655L;
               if (U.compareAndSwapLong(this, CTL, var1, var16)) {
                  return true;
               }
            } else if (var7 + var4 < 32767) {
               long var9 = var1 + 4294967296L & 281470681743360L | var1 & -281470681743361L;
               if (U.compareAndSwapLong(this, CTL, var1, var9)) {
                  Throwable var12 = null;
                  ForkJoinWorkerThread var13 = null;

                  try {
                     ForkJoinPool.ForkJoinWorkerThreadFactory var11 = this.factory;
                     if (this.factory != null && (var13 = var11.newThread(this)) != null) {
                        var13.start();
                        return true;
                     }
                  } catch (Throwable var15) {
                     var12 = var15;
                  }

                  this.deregisterWorker(var13, var12);
               }
            }
         }
      }

      return false;
   }

   public static Unsafe getUnsafe() {
      try {
         return Unsafe.getUnsafe();
      } catch (SecurityException var2) {
         try {
            return AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {

               public Unsafe run() throws java.lang.IllegalAccessException {
                  Class<Unsafe> var1x = Unsafe.class;

                  for (Field var5 : var1x.getDeclaredFields()) {
                     var5.setAccessible(true);
                     Object var6 = var5.get(null);
                     if (var1x.isInstance(var6)) {
                        return var1x.cast(var6);
                     }
                  }

                  throw new NoSuchFieldError("the Unsafe");
               }
            });
         } catch (PrivilegedActionException var1) {
            throw new RuntimeException("Could not initialize intrinsics", var1.getCause());
         }
      }
   }

   public void externalPush(ForkJoinTask<?> var1) {
      ForkJoinPool.Submitter var2 = submitters.get();
      int var10 = this.plock;
      ForkJoinPool.WorkQueue[] var11 = this.workQueues;
      int var5;
      if (var2 != null && var10 > 0 && var11 != null && (var5 = var11.length - 1) >= 0) {
         int var4 = var2.seed;
         ForkJoinPool.WorkQueue var3;
         if ((var3 = var11[var5 & var2.seed & 126]) != null && var4 != 0 && U.compareAndSwapInt(var3, QLOCK, 0, 1)) {
            ForkJoinTask[] var9 = var3.array;
            if (var3.array != null) {
               int var8;
               int var10000 = var8 = var9.length - 1;
               int var6 = var3.top;
               int var7;
               if (var10000 > (var7 = var3.top - var3.base)) {
                  int var12 = ((var8 & var6) << ASHIFT) + ABASE;
                  U.putOrderedObject(var9, var12, var1);
                  var3.top = var6 + 1;
                  var3.qlock = 0;
                  if (var7 <= 1) {
                     this.signalWork(var11, var3);
                  }

                  return;
               }
            }

            var3.qlock = 0;
         }
      }

      this.fullExternalPush(var1);
   }

   public ForkJoinPool() {
      this(Math.min(32767, Runtime.getRuntime().availableProcessors()), defaultForkJoinWorkerThreadFactory, null, false);
   }

   public void helpJoinOnce(ForkJoinPool.WorkQueue var1, ForkJoinTask<?> var2) {
      if (var1 != null && var2 != null) {
         int var3 = var2.status;
         if (var2.status >= 0) {
            ForkJoinTask var4 = var1.currentJoin;
            var1.currentJoin = var2;

            while (var1.tryRemoveAndExec(var2)) {
               var3 = var2.status;
               if (var2.status < 0) {
                  break;
               }
            }

            if (var3 >= 0) {
               if (var2 instanceof CountedCompleter) {
                  this.helpComplete(var1, (CountedCompleter<?>)var2);
               }

               while (var2.status >= 0 && this.tryHelpStealer(var1, var2) > 0) {
               }
            }

            var1.currentJoin = var4;
         }
      }
   }

   public ForkJoinPool.ForkJoinWorkerThreadFactory getFactory() {
      return this.factory;
   }

   public void execute(ForkJoinTask<?> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.externalPush(var1);
      }
   }

   public boolean isTerminating() {
      long var1 = this.ctl;
      return (var1 & 2147483648L) != 0L && (short)(var1 >>> 32) + this.parallelism > 0;
   }

   public ForkJoinPool(int var1, ForkJoinPool.ForkJoinWorkerThreadFactory var2, UncaughtExceptionHandler var3, boolean var4) {
      this(checkParallelism(var1), checkFactory(var2), var3, var4 ? 1 : 0, "ForkJoinPool-" + nextPoolId() + "-worker-");
      checkPermission();
   }

   public ForkJoinTask<?> nextTaskFor(ForkJoinPool.WorkQueue var1) {
      ForkJoinTask var2;
      while ((var2 = var1.nextLocalTask()) == null) {
         ForkJoinPool.WorkQueue var3;
         if ((var3 = this.findNonEmptyStealQueue()) == null) {
            return null;
         }

         int var4 = var3.base;
         if (var3.base - var3.top < 0 && (var2 = var3.pollAt(var4)) != null) {
            return var2;
         }
      }

      return var2;
   }

   public int getRunningThreadCount() {
      int var1 = 0;
      ForkJoinPool.WorkQueue[] var2 = this.workQueues;
      if (this.workQueues != null) {
         for (int var4 = 1; var4 < var2.length; var4 += 2) {
            ForkJoinPool.WorkQueue var3;
            if ((var3 = var2[var4]) != null && var3.isApparentlyUnblocked()) {
               var1++;
            }
         }
      }

      return var1;
   }

   public int tryHelpStealer(ForkJoinPool.WorkQueue var1, ForkJoinTask<?> var2) {
      byte var3 = 0;
      int var4 = 0;
      if (var2 != null && var1 != null && var1.base - var1.top >= 0) {
         label119:
         while (true) {
            ForkJoinTask var5 = var2;
            ForkJoinPool.WorkQueue var6 = var1;

            while (true) {
               int var10 = var2.status;
               if (var2.status < 0) {
                  return var10;
               }

               ForkJoinPool.WorkQueue[] var8 = this.workQueues;
               int var9;
               if (this.workQueues == null || (var9 = var8.length - 1) <= 0) {
                  return var3;
               }

               ForkJoinPool.WorkQueue var7;
               int var11;
               if ((var7 = var8[var11 = (var6.hint | 1) & var9]) == null || var7.currentSteal != var5) {
                  int var12 = var11;

                  while (true) {
                     if (((var11 = var11 + 2 & var9) & 15) == 1 && (var5.status < 0 || var6.currentJoin != var5)) {
                        continue label119;
                     }

                     if ((var7 = var8[var11]) != null && var7.currentSteal == var5) {
                        var6.hint = var11;
                        break;
                     }

                     if (var11 == var12) {
                        return var3;
                     }
                  }
               }

               while (true) {
                  if (var5.status < 0) {
                     continue label119;
                  }

                  int var13 = var7.base;
                  if (var7.base - var7.top >= 0) {
                     break;
                  }

                  ForkJoinTask[] var18 = var7.array;
                  if (var7.array == null) {
                     break;
                  }

                  int var14 = ((var18.length - 1 & var13) << ASHIFT) + ABASE;
                  ForkJoinTask var15 = (ForkJoinTask)U.getObjectVolatile(var18, var14);
                  if (var5.status < 0 || var6.currentJoin != var5 || var7.currentSteal != var5) {
                     continue label119;
                  }

                  var3 = 1;
                  if (var7.base == var13) {
                     if (var15 == null) {
                        return var3;
                     }

                     if (U.compareAndSwapObject(var18, var14, var15, null)) {
                        U.putOrderedInt(var7, QBASE, var13 + 1);
                        ForkJoinTask var16 = var1.currentSteal;
                        int var17 = var1.top;

                        do {
                           var1.currentSteal = var15;
                           var15.doExec();
                        } while (var2.status >= 0 && var1.top != var17 && (var15 = var1.pop()) != null);

                        var1.currentSteal = var16;
                        return var3;
                     }
                  }
               }

               ForkJoinTask var19 = var7.currentJoin;
               if (var5.status < 0 || var6.currentJoin != var5 || var7.currentSteal != var5) {
                  break;
               }

               if (var19 == null || ++var4 == 64) {
                  return var3;
               }

               var5 = var19;
               var6 = var7;
            }
         }
      } else {
         return var3;
      }
   }

   public void runWorker(ForkJoinPool.WorkQueue var1) {
      var1.growArray();
      int var2 = var1.hint;

      while (this.scan(var1, var2) == 0) {
         var2 ^= var2 << 13;
         var2 ^= var2 >>> 17;
         var2 ^= var2 << 5;
      }
   }

   public static void checkPermission() {
      SecurityManager var0 = System.getSecurityManager();
      if (var0 != null) {
         var0.checkPermission(modifyThreadPermission);
      }
   }

   public void fullExternalPush(ForkJoinTask<?> var1) {
      int var2 = 0;
      ForkJoinPool.Submitter var3 = submitters.get();

      while (true) {
         if (var3 == null) {
            var2 = this.indexSeed;
            var2 += 1640531527;
            if (U.compareAndSwapInt(this, INDEXSEED, this.indexSeed, var2) && var2 != 0) {
               submitters.set(var3 = new ForkJoinPool.Submitter(var2));
            }
         } else if (var2 == 0) {
            int var17 = var3.seed;
            int var18 = var17 ^ var17 << 13;
            int var19 = var18 ^ var18 >>> 17;
            var3.seed = var2 = var19 ^ var19 << 5;
         }

         int var6 = this.plock;
         if (this.plock < 0) {
            throw new RejectedExecutionException();
         }

         if (var6 != 0) {
            ForkJoinPool.WorkQueue[] var4 = this.workQueues;
            int var7;
            if (this.workQueues != null && (var7 = var4.length - 1) >= 0) {
               ForkJoinPool.WorkQueue var5;
               int var8;
               if ((var5 = var4[var8 = var2 & var7 & 126]) != null) {
                  if (var5.qlock == 0 && U.compareAndSwapInt(var5, QLOCK, 0, 1)) {
                     ForkJoinTask[] var30 = var5.array;
                     int var37 = var5.top;
                     boolean var38 = false;

                     try {
                        if (var30 != null && var30.length > var37 + 1 - var5.base || (var30 = var5.growArray()) != null) {
                           int var39 = ((var30.length - 1 & var37) << ASHIFT) + ABASE;
                           U.putOrderedObject(var30, var39, var1);
                           var5.top = var37 + 1;
                           var38 = true;
                        }
                     } finally {
                        var5.qlock = 0;
                     }

                     if (var38) {
                        this.signalWork(var4, var5);
                        return;
                     }
                  }

                  var2 = 0;
               } else {
                  var6 = this.plock;
                  if ((this.plock & 2) != 0) {
                     var2 = 0;
                  } else {
                     label165: {
                        var5 = new ForkJoinPool.WorkQueue(this, null, -1, var2);
                        var5.poolIndex = (short)var8;
                        var6 = this.plock;
                        if ((this.plock & 2) == 0) {
                           var6 += 2;
                           if (U.compareAndSwapInt(this, PLOCK, var6, var6)) {
                              break label165;
                           }
                        }

                        var6 = this.acquirePlock();
                     }

                     var4 = this.workQueues;
                     if (this.workQueues != null && var8 < var4.length && var4[var8] == null) {
                        var4[var8] = var5;
                     }

                     int var29 = var6 & -2147483648 | var6 + 2 & 2147483647;
                     if (!U.compareAndSwapInt(this, PLOCK, var6, var29)) {
                        this.releasePlock(var29);
                     }
                  }
               }
               continue;
            }
         }

         ForkJoinPool.WorkQueue[] var11;
         label181: {
            short var9 = this.parallelism;
            int var10 = var9 > 1 ? var9 - 1 : 1;
            var10 |= var10 >>> 1;
            var10 |= var10 >>> 2;
            var10 |= var10 >>> 4;
            var10 |= var10 >>> 8;
            var10 |= var10 >>> 16;
            var10 = var10 + 1 << 1;
            ForkJoinPool.WorkQueue[] var20 = this.workQueues;
            var11 = this.workQueues != null && var20.length != 0 ? null : new ForkJoinPool.WorkQueue[var10];
            var6 = this.plock;
            if ((this.plock & 2) == 0) {
               var6 += 2;
               if (U.compareAndSwapInt(this, PLOCK, var6, var6)) {
                  break label181;
               }
            }

            var6 = this.acquirePlock();
         }

         ForkJoinPool.WorkQueue[] var21 = this.workQueues;
         if ((this.workQueues == null || var21.length == 0) && var11 != null) {
            this.workQueues = var11;
         }

         int var12 = var6 & -2147483648 | var6 + 2 & 2147483647;
         if (!U.compareAndSwapInt(this, PLOCK, var6, var12)) {
            this.releasePlock(var12);
         }
      }
   }

   public int externalHelpComplete(CountedCompleter<?> var1) {
      ForkJoinPool.Submitter var5 = submitters.get();
      ForkJoinPool.WorkQueue[] var6 = this.workQueues;
      int var7 = 0;
      int var3;
      if (var5 != null && var6 != null && (var3 = var6.length - 1) >= 0) {
         int var4 = var5.seed;
         ForkJoinPool.WorkQueue var2;
         if ((var2 = var6[var5.seed & var3 & 126]) != null && var1 != null) {
            int var8 = var3 + var3 + 1;
            long var9 = 0L;
            var4 |= 1;
            int var11 = var8;

            while (true) {
               var7 = var1.status;
               if (var1.status < 0) {
                  break;
               }

               if (var2.externalPopAndExecCC(var1)) {
                  var11 = var8;
               } else {
                  var7 = var1.status;
                  if (var1.status < 0) {
                     break;
                  }

                  ForkJoinPool.WorkQueue var12;
                  if ((var12 = var6[var4 & var3]) != null && var12.pollAndExecCC(var1)) {
                     var11 = var8;
                  } else if (--var11 < 0) {
                     long var10000 = var9;
                     var9 = this.ctl;
                     if (var10000 == this.ctl) {
                        break;
                     }

                     var11 = var8;
                  }
               }

               var4 += 2;
            }
         }
      }

      return var7;
   }

   public static ForkJoinPool.WorkQueue commonSubmitterQueue() {
      ForkJoinPool.Submitter var0;
      if ((var0 = submitters.get()) != null) {
         ForkJoinPool var1 = common;
         if (common != null) {
            ForkJoinPool.WorkQueue[] var2 = var1.workQueues;
            int var3;
            if (var1.workQueues != null && (var3 = var2.length - 1) >= 0) {
               return var2[var3 & var0.seed & 126];
            }
         }
      }

      return null;
   }

   public <T> ForkJoinTask<T> submit(Runnable var1, T var2) {
      ForkJoinTask.AdaptedRunnable var3 = new ForkJoinTask.AdaptedRunnable<>(var1, var2);
      this.externalPush(var3);
      return var3;
   }

   public void deregisterWorker(ForkJoinWorkerThread var1, Throwable var2) {
      ForkJoinPool.WorkQueue var3 = null;
      if (var1 != null) {
         var3 = var1.workQueue;
         if (var1.workQueue != null) {
            var3.qlock = -1;

            long var5;
            do {
               var5 = this.stealCount;
            } while (!U.compareAndSwapLong(this, STEALCOUNT, this.stealCount, var5 + var3.nsteals));

            int var16;
            label155: {
               var16 = this.plock;
               if ((this.plock & 2) == 0) {
                  var16 += 2;
                  if (U.compareAndSwapInt(this, PLOCK, var16, var16)) {
                     break label155;
                  }
               }

               var16 = this.acquirePlock();
            }

            int var7 = var16 & -2147483648 | var16 + 2 & 2147483647;

            try {
               short var8 = var3.poolIndex;
               ForkJoinPool.WorkQueue[] var9 = this.workQueues;
               if (var9 != null && var8 >= 0 && var8 < var9.length && var9[var8] == var3) {
                  var9[var8] = null;
               }
            } finally {
               if (!U.compareAndSwapInt(this, PLOCK, var16, var7)) {
                  this.releasePlock(var7);
               }
            }
         }
      }

      long var17;
      do {
         var17 = this.ctl;
      } while (
         !U.compareAndSwapLong(this, CTL, this.ctl, var17 - 281474976710656L & -281474976710656L | var17 - 4294967296L & 281470681743360L | var17 & 4294967295L)
      );

      if (!this.tryTerminate(false, false) && var3 != null && var3.array != null) {
         var3.cancelAll();

         while (true) {
            var17 = this.ctl;
            int var11;
            int var21;
            if ((var21 = (int)(this.ctl >>> 32)) >= 0 || (var11 = (int)var17) < 0) {
               break;
            }

            if (var11 <= 0) {
               if ((short)var21 < 0) {
                  this.tryAddWorker();
               }
               break;
            }

            ForkJoinPool.WorkQueue[] var6 = this.workQueues;
            int var10;
            ForkJoinPool.WorkQueue var19;
            if (this.workQueues == null || (var10 = var11 & 65535) >= var6.length || (var19 = var6[var10]) == null) {
               break;
            }

            long var12 = var19.nextWait & 2147483647 | (long)(var21 + 65536) << 32;
            if (var19.eventCount != (var11 | -2147483648)) {
               break;
            }

            if (U.compareAndSwapLong(this, CTL, var17, var12)) {
               var19.eventCount = var11 + 65536 & 2147483647;
               Thread var20 = var19.parker;
               if (var19.parker != null) {
                  U.unpark(var20);
               }
               break;
            }
         }
      }

      if (var2 == null) {
         ForkJoinTask.helpExpungeStaleExceptions();
      } else {
         ForkJoinTask.rethrow(var2);
      }
   }

   public void tryAddWorker() {
      while (true) {
         long var1 = this.ctl;
         int var3;
         int var4;
         if ((var3 = (int)(this.ctl >>> 32)) < 0 && (var3 & 32768) != 0 && (var4 = (int)var1) >= 0) {
            long var5 = (long)(var3 + 1 & 65535 | var3 + 65536 & -65536) << 32 | var4;
            if (!U.compareAndSwapLong(this, CTL, var1, var5)) {
               continue;
            }

            Throwable var8 = null;
            ForkJoinWorkerThread var9 = null;

            try {
               ForkJoinPool.ForkJoinWorkerThreadFactory var7 = this.factory;
               if (this.factory != null && (var9 = var7.newThread(this)) != null) {
                  var9.start();
                  return;
               }
            } catch (Throwable var11) {
               var8 = var11;
            }

            this.deregisterWorker(var9, var8);
         }

         return;
      }
   }

   public int drainTasksTo(Collection<? super ForkJoinTask<?>> var1) {
      int var2 = 0;
      ForkJoinPool.WorkQueue[] var3 = this.workQueues;
      if (this.workQueues != null) {
         for (int var6 = 0; var6 < var3.length; var6++) {
            ForkJoinPool.WorkQueue var4;
            ForkJoinTask var5;
            if ((var4 = var3[var6]) != null) {
               while ((var5 = var4.poll()) != null) {
                  ((Collection)var1).add(var5);
                  var2++;
               }
            }
         }
      }

      return var2;
   }

   @Override
   public void execute(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         Object var2;
         if (var1 instanceof ForkJoinTask) {
            var2 = (ForkJoinTask)var1;
         } else {
            var2 = new ForkJoinTask.RunnableExecuteAction(var1);
         }

         this.externalPush((ForkJoinTask<?>)var2);
      }
   }

   @Override
   public List<Runnable> shutdownNow() {
      checkPermission();
      this.tryTerminate(true, true);
      return Collections.emptyList();
   }

   public int acquirePlock() {
      int var1 = 256;

      while (true) {
         int var2 = this.plock;
         int var3;
         if ((this.plock & 2) == 0 && U.compareAndSwapInt(this, PLOCK, var2, var3 = var2 + 2)) {
            return var3;
         }

         if (var1 >= 0) {
            if (ThreadLocalRandom.current().nextInt() >= 0) {
               var1--;
            }
         } else if (U.compareAndSwapInt(this, PLOCK, var2, var2 | 1)) {
            synchronized (this) {
               if ((this.plock & 1) != 0) {
                  try {
                     this.wait();
                  } catch (InterruptedException var9) {
                     try {
                        Thread.currentThread().interrupt();
                     } catch (SecurityException var8) {
                     }
                  }
               } else {
                  this.notifyAll();
               }
            }
         }
      }
   }

   public int helpComplete(ForkJoinPool.WorkQueue var1, CountedCompleter<?> var2) {
      int var5 = 0;
      ForkJoinPool.WorkQueue[] var3 = this.workQueues;
      int var4;
      if (this.workQueues != null && (var4 = var3.length - 1) >= 0 && var1 != null && var2 != null) {
         int var6 = var1.poolIndex;
         int var7 = var4 + var4 + 1;
         long var8 = 0L;
         int var10 = var7;

         while (true) {
            var5 = var2.status;
            if (var2.status < 0) {
               break;
            }

            if (var1.internalPopAndExecCC(var2)) {
               var10 = var7;
            } else {
               var5 = var2.status;
               if (var2.status < 0) {
                  break;
               }

               ForkJoinPool.WorkQueue var11;
               if ((var11 = var3[var6 & var4]) != null && var11.pollAndExecCC(var2)) {
                  var10 = var7;
               } else if (--var10 < 0) {
                  long var10000 = var8;
                  var8 = this.ctl;
                  if (var10000 == this.ctl) {
                     break;
                  }

                  var10 = var7;
               }
            }

            var6 += 2;
         }
      }

      return var5;
   }

   @Override
   public <T> RunnableFuture<T> newTaskFor(Runnable var1, T var2) {
      return new ForkJoinTask.AdaptedRunnable<>(var1, (T)var2);
   }

   public ForkJoinPool.WorkQueue findNonEmptyStealQueue() {
      int var1 = ThreadLocalRandom.current().nextInt();

      int var2;
      do {
         var2 = this.plock;
         ForkJoinPool.WorkQueue[] var4 = this.workQueues;
         int var3;
         if (this.workQueues != null && (var3 = var4.length - 1) >= 0) {
            for (int var6 = var3 + 1 << 2; var6 >= 0; var6--) {
               ForkJoinPool.WorkQueue var5;
               if ((var5 = var4[(var1 - var6 << 1 | 1) & var3]) != null && var5.base - var5.top < 0) {
                  return var5;
               }
            }
         }
      } while (this.plock != var2);

      return null;
   }

   public int getActiveThreadCount() {
      int var1 = this.parallelism + (int)(this.ctl >> 48);
      return var1 <= 0 ? 0 : var1;
   }

   public void signalWork(ForkJoinPool.WorkQueue[] var1, ForkJoinPool.WorkQueue var2) {
      while (true) {
         long var3 = this.ctl;
         int var6;
         if ((var6 = (int)(this.ctl >>> 32)) < 0) {
            int var5;
            if ((var5 = (int)var3) <= 0) {
               if ((short)var6 < 0) {
                  this.tryAddWorker();
               }
            } else {
               int var7;
               ForkJoinPool.WorkQueue var8;
               if (var1 != null && var1.length > (var7 = var5 & 65535) && (var8 = var1[var7]) != null) {
                  long var10 = var8.nextWait & 2147483647 | (long)(var6 + 65536) << 32;
                  int var12 = var5 + 65536 & 2147483647;
                  if (var8.eventCount == (var5 | -2147483648) && U.compareAndSwapLong(this, CTL, var3, var10)) {
                     var8.eventCount = var12;
                     Thread var9 = var8.parker;
                     if (var8.parker != null) {
                        U.unpark(var9);
                     }
                  } else if (var2 == null || var2.base < var2.top) {
                     continue;
                  }
               }
            }
         }

         return;
      }
   }

   public int getPoolSize() {
      return this.parallelism + (short)(this.ctl >>> 32);
   }

   public void incrementActiveCount() {
      long var1;
      do {
         var1 = this.ctl;
      } while (!U.compareAndSwapLong(this, CTL, this.ctl, var1 & 281474976710655L | (var1 & -281474976710656L) + 281474976710656L));
   }

   @Override
   public boolean isTerminated() {
      long var1 = this.ctl;
      return (var1 & 2147483648L) != 0L && (short)(var1 >>> 32) + this.parallelism <= 0;
   }

   public ForkJoinPool(int var1, ForkJoinPool.ForkJoinWorkerThreadFactory var2, UncaughtExceptionHandler var3, int var4, String var5) {
      this.workerNamePrefix = var5;
      this.factory = var2;
      this.ueh = var3;
      this.mode = (short)var4;
      this.parallelism = (short)var1;
      long var6 = -var1;
      this.ctl = var6 << 48 & -281474976710656L | var6 << 32 & 281470681743360L;
   }

   @Override
   public void shutdown() {
      checkPermission();
      this.tryTerminate(false, true);
   }

   public void helpRelease(long var1, ForkJoinPool.WorkQueue[] var3, ForkJoinPool.WorkQueue var4, ForkJoinPool.WorkQueue var5, int var6) {
      ForkJoinPool.WorkQueue var7;
      int var8;
      int var9;
      if (var4 != null
         && var4.eventCount < 0
         && (var8 = (int)var1) > 0
         && var3 != null
         && var3.length > (var9 = var8 & 65535)
         && (var7 = var3[var9]) != null
         && this.ctl == var1) {
         long var11 = var7.nextWait & 2147483647 | (long)((int)(var1 >>> 32) + 65536) << 32;
         int var13 = var8 + 65536 & 2147483647;
         if (var5 != null
            && var5.base == var6
            && var4.eventCount < 0
            && var7.eventCount == (var8 | -2147483648)
            && U.compareAndSwapLong(this, CTL, var1, var11)) {
            var7.eventCount = var13;
            Thread var10 = var7.parker;
            if (var7.parker != null) {
               U.unpark(var10);
            }
         }
      }
   }

   public static int getSurplusQueuedTaskCount() {
      Thread var0;
      if ((var0 = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
         ForkJoinWorkerThread var1;
         ForkJoinPool var2;
         short var4 = (var2 = (var1 = (ForkJoinWorkerThread)var0).pool).parallelism;
         ForkJoinPool.WorkQueue var3 = var1.workQueue;
         int var5 = var1.workQueue.top - var3.base;
         int var6 = (int)(var2.ctl >> 48) + var4;
         int var7;
         int var8;
         int var9;
         int var10;
         return var5
            - (var6 > (var7 = var4 >>> 1) ? 0 : (var6 > (var8 = var7 >>> 1) ? 1 : (var6 > (var9 = var8 >>> 1) ? 2 : (var6 > (var10 = var9 >>> 1) ? 4 : 8))));
      } else {
         return 0;
      }
   }

   public boolean tryExternalUnpush(ForkJoinTask<?> var1) {
      ForkJoinPool.Submitter var6 = submitters.get();
      ForkJoinPool.WorkQueue[] var7 = this.workQueues;
      boolean var8 = false;
      ForkJoinPool.WorkQueue var2;
      int var4;
      if (var6 != null && var7 != null && (var4 = var7.length - 1) >= 0 && (var2 = var7[var6.seed & var4 & 126]) != null) {
         int var5 = var2.top;
         if (var2.base != var2.top) {
            ForkJoinTask[] var3 = var2.array;
            if (var2.array != null) {
               long var9 = ((var3.length - 1 & var5 - 1) << ASHIFT) + ABASE;
               if (U.getObject(var3, var9) == var1 && U.compareAndSwapInt(var2, QLOCK, 0, 1)) {
                  if (var2.top == var5 && var2.array == var3 && U.compareAndSwapObject(var3, var9, var1, null)) {
                     var2.top = var5 - 1;
                     var8 = true;
                  }

                  var2.qlock = 0;
               }
            }
         }
      }

      return var8;
   }

   public ForkJoinPool(int var1) {
      this(var1, defaultForkJoinWorkerThreadFactory, null, false);
   }

   public boolean hasQueuedSubmissions() {
      ForkJoinPool.WorkQueue[] var1 = this.workQueues;
      if (this.workQueues != null) {
         for (int var3 = 0; var3 < var1.length; var3 += 2) {
            ForkJoinPool.WorkQueue var2;
            if ((var2 = var1[var3]) != null && !var2.isEmpty()) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public String toString() {
      long var1 = 0L;
      long var3 = 0L;
      int var5 = 0;
      long var6 = this.stealCount;
      long var8 = this.ctl;
      ForkJoinPool.WorkQueue[] var10 = this.workQueues;
      if (this.workQueues != null) {
         for (int var12 = 0; var12 < var10.length; var12++) {
            ForkJoinPool.WorkQueue var11;
            if ((var11 = var10[var12]) != null) {
               int var13 = var11.queueSize();
               if ((var12 & 1) == 0) {
                  var3 += var13;
               } else {
                  var1 += var13;
                  var6 += var11.nsteals;
                  if (var11.isApparentlyUnblocked()) {
                     var5++;
                  }
               }
            }
         }
      }

      short var16 = this.parallelism;
      int var17 = var16 + (short)(var8 >>> 32);
      int var14 = var16 + (int)(var8 >> 48);
      if (var14 < 0) {
         var14 = 0;
      }

      String var15;
      if ((var8 & 2147483648L) != 0L) {
         var15 = var17 == 0 ? "Terminated" : "Terminating";
      } else {
         var15 = this.plock < 0 ? "Shutting down" : "Running";
      }

      return super.toString()
         + "["
         + var15
         + ", parallelism = "
         + var16
         + ", size = "
         + var17
         + ", active = "
         + var14
         + ", running = "
         + var5
         + ", steals = "
         + var6
         + ", tasks = "
         + var1
         + ", submissions = "
         + var3
         + "]";
   }

   public static void managedBlock(ForkJoinPool.ManagedBlocker var0) throws java.lang.InterruptedException {
      Thread var1 = Thread.currentThread();
      if (var1 instanceof ForkJoinWorkerThread) {
         ForkJoinPool var2 = ((ForkJoinWorkerThread)var1).pool;

         while (!var0.isReleasable()) {
            if (var2.tryCompensate(var2.ctl)) {
               try {
                  while (!var0.isReleasable() && !var0.block()) {
                  }
                  break;
               } finally {
                  var2.incrementActiveCount();
               }
            }
         }
      } else {
         while (!var0.isReleasable() && !var0.block()) {
         }
      }
   }

   public static ForkJoinPool makeCommonPool() {
      int var0 = -1;
      ForkJoinPool.ForkJoinWorkerThreadFactory var1 = defaultForkJoinWorkerThreadFactory;
      UncaughtExceptionHandler var2 = null;

      try {
         String var3 = System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism");
         String var4 = System.getProperty("java.util.concurrent.ForkJoinPool.common.threadFactory");
         String var5 = System.getProperty("java.util.concurrent.ForkJoinPool.common.exceptionHandler");
         if (var3 != null) {
            var0 = Integer.parseInt(var3);
         }

         if (var4 != null) {
            var1 = (ForkJoinPool.ForkJoinWorkerThreadFactory)ClassLoader.getSystemClassLoader().loadClass(var4).newInstance();
         }

         if (var5 != null) {
            var2 = (UncaughtExceptionHandler)ClassLoader.getSystemClassLoader().loadClass(var5).newInstance();
         }
      } catch (Exception var6) {
      }

      if (var0 < 0 && (var0 = Runtime.getRuntime().availableProcessors() - 1) < 0) {
         var0 = 0;
      }

      if (var0 > 32767) {
         var0 = 32767;
      }

      return new ForkJoinPool(var0, var1, var2, 0, "ForkJoinPool.commonPool-worker-");
   }

   public long getQueuedTaskCount() {
      long var1 = 0L;
      ForkJoinPool.WorkQueue[] var3 = this.workQueues;
      if (this.workQueues != null) {
         for (int var5 = 1; var5 < var3.length; var5 += 2) {
            ForkJoinPool.WorkQueue var4;
            if ((var4 = var3[var5]) != null) {
               var1 += var4.queueSize();
            }
         }
      }

      return var1;
   }

   public boolean isQuiescent() {
      return this.parallelism + (int)(this.ctl >> 48) <= 0;
   }

   public ForkJoinPool.WorkQueue registerWorker(ForkJoinWorkerThread var1) {
      var1.setDaemon(true);
      UncaughtExceptionHandler var2 = this.ueh;
      if (this.ueh != null) {
         var1.setUncaughtExceptionHandler(var2);
      }

      int var16;
      do {
         var16 = this.indexSeed;
         var16 += 1640531527;
      } while (!U.compareAndSwapInt(this, INDEXSEED, this.indexSeed, var16) || var16 == 0);

      ForkJoinPool.WorkQueue var6;
      int var17;
      label98: {
         var6 = new ForkJoinPool.WorkQueue(this, var1, this.mode, var16);
         var17 = this.plock;
         if ((this.plock & 2) == 0) {
            var17 += 2;
            if (U.compareAndSwapInt(this, PLOCK, var17, var17)) {
               break label98;
            }
         }

         var17 = this.acquirePlock();
      }

      int var7 = var17 & -2147483648 | var17 + 2 & 2147483647;

      try {
         ForkJoinPool.WorkQueue[] var3 = this.workQueues;
         if (this.workQueues != null) {
            int var8 = var3.length;
            int var9 = var8 - 1;
            int var10 = var16 << 1 | 1;
            if (var3[var10 = var10 & var9] != null) {
               int var11 = 0;
               int var12 = var8 <= 4 ? 2 : (var8 >>> 1 & 65534) + 2;

               while (var3[var10 = var10 + var12 & var9] != null) {
                  if (++var11 >= var8) {
                     this.workQueues = var3 = Arrays.copyOf(var3, var8 <<= 1);
                     var9 = var8 - 1;
                     var11 = 0;
                  }
               }
            }

            var6.poolIndex = (short)var10;
            var6.eventCount = var10;
            var3[var10] = var6;
         }
      } finally {
         if (!U.compareAndSwapInt(this, PLOCK, var17, var7)) {
            this.releasePlock(var7);
         }
      }

      var1.setName(this.workerNamePrefix.concat(Integer.toString(var6.poolIndex >>> 1)));
      return var6;
   }

   public int awaitWork(ForkJoinPool.WorkQueue var1, long var2, int var4) {
      int var5 = var1.qlock;
      if (var1.qlock >= 0 && var1.eventCount == var4 && this.ctl == var2 && !Thread.interrupted()) {
         int var11 = (int)var2;
         int var12 = (int)(var2 >>> 32);
         int var13 = (var12 >> 16) + this.parallelism;
         if (var11 >= 0 && (var13 > 0 || !this.tryTerminate(false, false))) {
            int var6 = var1.nsteals;
            if (var1.nsteals != 0) {
               var1.nsteals = 0;

               long var14;
               do {
                  var14 = this.stealCount;
               } while (!U.compareAndSwapLong(this, STEALCOUNT, this.stealCount, var14 + var6));
            } else {
               long var17 = var13 <= 0 && var4 == (var11 | -2147483648) ? var1.nextWait & 2147483647 | (long)(var12 + 65536) << 32 : 0L;
               long var7;
               long var9;
               if (var17 != 0L) {
                  int var16 = -((short)(var2 >>> 32));
                  var7 = var16 < 0 ? 200000000L : (var16 + 1) * 2000000000L;
                  var9 = System.nanoTime() + var7 - 2000000L;
               } else {
                  var9 = 0L;
                  var7 = 0L;
               }

               if (var1.eventCount == var4 && this.ctl == var2) {
                  Thread var18 = Thread.currentThread();
                  U.putObject(var18, PARKBLOCKER, this);
                  var1.parker = var18;
                  if (var1.eventCount == var4 && this.ctl == var2) {
                     U.park(false, var7);
                  }

                  var1.parker = null;
                  U.putObject(var18, PARKBLOCKER, null);
                  if (var7 != 0L && this.ctl == var2 && var9 - System.nanoTime() <= 0L && U.compareAndSwapLong(this, CTL, var2, var17)) {
                     var5 = var1.qlock = -1;
                  }
               }
            }
         } else {
            var5 = var1.qlock = -1;
         }
      }

      return var5;
   }

   public <T> T invoke(ForkJoinTask<T> var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.externalPush(var1);
         return (T)var1.join();
      }
   }

   public static ForkJoinPool commonPool() {
      return common;
   }

   public long getStealCount() {
      long var1 = this.stealCount;
      ForkJoinPool.WorkQueue[] var3 = this.workQueues;
      if (this.workQueues != null) {
         for (int var5 = 1; var5 < var3.length; var5 += 2) {
            ForkJoinPool.WorkQueue var4;
            if ((var4 = var3[var5]) != null) {
               var1 += var4.nsteals;
            }
         }
      }

      return var1;
   }

   public void helpQuiescePool(ForkJoinPool.WorkQueue var1) {
      ForkJoinTask var2 = var1.currentSteal;
      boolean var3 = true;

      while (true) {
         ForkJoinTask var7;
         while ((var7 = var1.nextLocalTask()) != null) {
            var7.doExec();
         }

         ForkJoinPool.WorkQueue var6;
         if ((var6 = this.findNonEmptyStealQueue()) == null) {
            if (var3) {
               long var12 = this.ctl;
               long var9 = this.ctl & 281474976710655L | (var12 & -281474976710656L) - 281474976710656L;
               if ((int)(var9 >> 48) + this.parallelism == 0) {
                  break;
               }

               if (U.compareAndSwapLong(this, CTL, var12, var9)) {
                  var3 = false;
               }
            } else {
               long var11 = this.ctl;
               if ((int)(this.ctl >> 48) + this.parallelism <= 0
                  && U.compareAndSwapLong(this, CTL, var11, var11 & 281474976710655L | (var11 & -281474976710656L) + 281474976710656L)) {
                  break;
               }
            }
         } else {
            if (!var3) {
               var3 = true;

               long var4;
               do {
                  var4 = this.ctl;
               } while (!U.compareAndSwapLong(this, CTL, this.ctl, var4 & 281474976710655L | (var4 & -281474976710656L) + 281474976710656L));
            }

            int var8 = var6.base;
            if (var6.base - var6.top < 0 && (var7 = var6.pollAt(var8)) != null) {
               (var1.currentSteal = var7).doExec();
               var1.currentSteal = var2;
            }
         }
      }
   }

   public static synchronized int nextPoolId() {
      return ++poolNumberSequence;
   }

   public int getQueuedSubmissionCount() {
      int var1 = 0;
      ForkJoinPool.WorkQueue[] var2 = this.workQueues;
      if (this.workQueues != null) {
         for (int var4 = 0; var4 < var2.length; var4 += 2) {
            ForkJoinPool.WorkQueue var3;
            if ((var3 = var2[var4]) != null) {
               var1 += var3.queueSize();
            }
         }
      }

      return var1;
   }

   public static void quiesceCommonPool() {
      common.awaitQuiescence(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) throws java.lang.InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else if (this == common) {
         this.awaitQuiescence(var1, var3);
         return false;
      } else {
         long var4 = var3.toNanos(var1);
         if (this.isTerminated()) {
            return true;
         } else if (var4 <= 0L) {
            return false;
         } else {
            long var6 = System.nanoTime() + var4;
            synchronized (this) {
               while (!this.isTerminated()) {
                  if (var4 <= 0L) {
                     return false;
                  }

                  long var9 = TimeUnit.NANOSECONDS.toMillis(var4);
                  this.wait(var9 > 0L ? var9 : 1L);
                  var4 = var6 - System.nanoTime();
               }

               return true;
            }
         }
      }
   }

   public static int getCommonPoolParallelism() {
      return commonParallelism;
   }

   public boolean getAsyncMode() {
      return this.mode == 1;
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> var1) {
      ArrayList var2 = new ArrayList(var1.size());
      boolean var3 = false;
      boolean var11 = false /* VF: Semaphore variable */;

      ArrayList var14;
      try {
         var11 = true;

         for (Callable var5 : var1) {
            ForkJoinTask.AdaptedCallable var6 = new ForkJoinTask.AdaptedCallable(var5);
            var2.add(var6);
            this.externalPush(var6);
         }

         int var13 = 0;

         for (int var15 = var2.size(); var13 < var15; var13++) {
            ((ForkJoinTask)var2.get(var13)).quietlyJoin();
         }

         var3 = true;
         var14 = var2;
         var11 = false;
      } finally {
         if (var11) {
            if (!var3) {
               int var8 = 0;

               for (int var9 = var2.size(); var8 < var9; var8++) {
                  ((Future)var2.get(var8)).cancel(false);
               }
            }
         }
      }

      if (!var3) {
         int var16 = 0;

         for (int var17 = var2.size(); var16 < var17; var16++) {
            ((Future)var2.get(var16)).cancel(false);
         }
      }

      return var14;
   }

   public int awaitJoin(ForkJoinPool.WorkQueue var1, ForkJoinTask<?> var2) {
      int var3 = 0;
      if (var2 != null) {
         var3 = var2.status;
         if (var2.status >= 0 && var1 != null) {
            ForkJoinTask var4 = var1.currentJoin;
            var1.currentJoin = var2;

            while (var1.tryRemoveAndExec(var2)) {
               var3 = var2.status;
               if (var2.status < 0) {
                  break;
               }
            }

            if (var3 >= 0 && var2 instanceof CountedCompleter) {
               var3 = this.helpComplete(var1, (CountedCompleter<?>)var2);
            }

            long var5 = 0L;

            while (var3 >= 0) {
               var3 = var2.status;
               if (var2.status < 0) {
                  break;
               }

               if ((var3 = this.tryHelpStealer(var1, var2)) == 0) {
                  var3 = var2.status;
                  if (var2.status >= 0) {
                     if (!this.tryCompensate(var5)) {
                        var5 = this.ctl;
                     } else {
                        if (var2.trySetSignal()) {
                           var3 = var2.status;
                           if (var2.status >= 0) {
                              synchronized (var2) {
                                 if (var2.status >= 0) {
                                    try {
                                       var2.wait();
                                    } catch (InterruptedException var10) {
                                    }
                                 } else {
                                    var2.notifyAll();
                                 }
                              }
                           }
                        }

                        while (true) {
                           long var7 = this.ctl;
                           if (U.compareAndSwapLong(this, CTL, this.ctl, var7 & 281474976710655L | (var7 & -281474976710656L) + 281474976710656L)) {
                              break;
                           }
                        }
                     }
                  }
               }
            }

            var1.currentJoin = var4;
         }
      }

      return var3;
   }

   public void releasePlock(int var1) {
      this.plock = var1;
      synchronized (this) {
         this.notifyAll();
      }
   }

   public static final class DefaultForkJoinWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {

      @Override
      public ForkJoinWorkerThread newThread(ForkJoinPool var1) {
         return new ForkJoinWorkerThread(var1);
      }
   }

   public static final class EmptyTask extends ForkJoinTask<Void> {
      public static final long serialVersionUID = -7721805057305804111L;

      public void setRawResult(Void var1) {
      }

      @Override
      public boolean exec() {
         return true;
      }

      public EmptyTask() {
         this.status = -268435456;
      }

      public Void getRawResult() {
         return null;
      }
   }

   public interface ForkJoinWorkerThreadFactory {
      ForkJoinWorkerThread newThread(ForkJoinPool var1);
   }

   public interface ManagedBlocker {
      boolean isReleasable();

      boolean block() throws java.lang.InterruptedException ;
   }

   public static final class Submitter {
      public int seed;

      public Submitter(int var1) {
         this.seed = var1;
      }
   }

   public static final class WorkQueue {
      public static long QBASE;
      public static Unsafe U;
      public volatile Object pad11;
      public volatile Object pad17;
      public short poolIndex;
      public volatile Object pad10;
      public volatile Object pad19;
      public int top;
      public volatile ForkJoinTask<?> currentJoin;
      public static final int MAXIMUM_QUEUE_CAPACITY = 67108864;
      public volatile Object pad12;
      public ForkJoinTask<?> currentSteal;
      public volatile long pad01;
      public volatile int eventCount;
      public volatile long pad04;
      public volatile long pad03;
      public ForkJoinTask<?>[] array;
      public volatile int base;
      public volatile int qlock;
      public static long QLOCK;
      public static int ABASE;
      public volatile long pad05;
      public volatile Object pad16;
      public static int ASHIFT;
      public static final int INITIAL_QUEUE_CAPACITY = 8192;
      public int hint;
      public int nsteals;
      public volatile Object pad1a;
      public volatile long pad02;
      public volatile long pad06;
      public ForkJoinPool pool;
      public volatile Object pad1c;
      public volatile long pad00;
      public ForkJoinWorkerThread owner;
      public volatile Object pad15;
      public volatile Thread parker;
      public short mode;
      public volatile Object pad1d;
      public int nextWait;
      public volatile Object pad14;
      public volatile Object pad18;
      public volatile Object pad13;
      public volatile Object pad1b;

      public boolean isEmpty() {
         int var3 = this.top;
         int var4 = this.base - this.top;
         if (var4 < 0) {
            if (var4 == -1) {
               ForkJoinTask[] var1 = this.array;
               int var2;
               if (this.array == null || (var2 = var1.length - 1) < 0 || U.getObject(var1, (long)((var2 & var3 - 1) << ASHIFT) + ABASE) == null) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }

      public void push(ForkJoinTask<?> var1) {
         int var4 = this.top;
         ForkJoinTask[] var2 = this.array;
         if (this.array != null) {
            int var6 = var2.length - 1;
            U.putOrderedObject(var2, ((var6 & var4) << ASHIFT) + ABASE, var1);
            int var5;
            if ((var5 = (this.top = var4 + 1) - this.base) <= 2) {
               ForkJoinPool var3 = this.pool;
               this.pool.signalWork(var3.workQueues, this);
            } else if (var5 >= var6) {
               this.growArray();
            }
         }
      }

      public void runTask(ForkJoinTask<?> var1) {
         if ((this.currentSteal = var1) != null) {
            var1.doExec();
            ForkJoinTask[] var2 = this.array;
            short var3 = this.mode;
            this.nsteals++;
            this.currentSteal = null;
            if (var3 != 0) {
               this.pollAndExecAll();
            } else if (var2 != null) {
               int var5 = var2.length - 1;

               int var4;
               while ((var4 = this.top - 1) - this.base >= 0) {
                  long var6 = ((var5 & var4) << ASHIFT) + ABASE;
                  ForkJoinTask var8 = (ForkJoinTask)U.getObject(var2, var6);
                  if (var8 == null) {
                     break;
                  }

                  if (U.compareAndSwapObject(var2, var6, var8, null)) {
                     this.top = var4;
                     var8.doExec();
                  }
               }
            }
         }
      }

      public void pollAndExecAll() {
         ForkJoinTask var1;
         while ((var1 = this.poll()) != null) {
            var1.doExec();
         }
      }

      public ForkJoinTask<?> pollAt(int var1) {
         ForkJoinTask[] var3 = this.array;
         if (this.array != null) {
            int var4 = ((var3.length - 1 & var1) << ASHIFT) + ABASE;
            ForkJoinTask var2;
            if ((var2 = (ForkJoinTask)U.getObjectVolatile(var3, var4)) != null && this.base == var1 && U.compareAndSwapObject(var3, var4, var2, null)) {
               U.putOrderedInt(this, QBASE, var1 + 1);
               return var2;
            }
         }

         return null;
      }

      static {
         try {
            U = ForkJoinPool.getUnsafe();
            Class<ForkJoinPool.WorkQueue> var0 = ForkJoinPool.WorkQueue.class;
            Class<ForkJoinTask[]> var1 = ForkJoinTask[].class;
            QBASE = U.objectFieldOffset(var0.getDeclaredField("base"));
            QLOCK = U.objectFieldOffset(var0.getDeclaredField("qlock"));
            ABASE = U.arrayBaseOffset(var1);
            int var2 = U.arrayIndexScale(var1);
            if ((var2 & var2 - 1) != 0) {
               throw new Error("data type scale not a power of two");
            } else {
               ASHIFT = 31 - Integer.numberOfLeadingZeros(var2);
            }
         } catch (Exception var3) {
            throw new Error(var3);
         }
      }

      public void cancelAll() {
         ForkJoinTask.cancelIgnoringExceptions(this.currentJoin);
         ForkJoinTask.cancelIgnoringExceptions(this.currentSteal);

         ForkJoinTask var1;
         while ((var1 = this.poll()) != null) {
            ForkJoinTask.cancelIgnoringExceptions(var1);
         }
      }

      public boolean internalPopAndExecCC(CountedCompleter<?> var1) {
         int var3 = this.top;
         if (this.base - this.top < 0) {
            ForkJoinTask[] var2 = this.array;
            if (this.array != null) {
               long var7 = ((var2.length - 1 & var3 - 1) << ASHIFT) + ABASE;
               Object var4;
               if ((var4 = U.getObject(var2, var7)) instanceof CountedCompleter) {
                  CountedCompleter var5 = (CountedCompleter)var4;
                  CountedCompleter var6 = var5;

                  do {
                     if (var6 == var1) {
                        if (U.compareAndSwapObject(var2, var7, var5, null)) {
                           this.top = var3 - 1;
                           var5.doExec();
                        }

                        return true;
                     }
                  } while ((var6 = var6.completer) != null);
               }
            }
         }

         return false;
      }

      public ForkJoinTask<?>[] growArray() {
         ForkJoinTask[] var1 = this.array;
         int var2 = var1 != null ? var1.length << 1 : 8192;
         if (var2 > 67108864) {
            throw new RejectedExecutionException("Queue capacity exceeded");
         } else {
            ForkJoinTask[] var6 = this.array = new ForkJoinTask[var2];
            int var3;
            if (var1 != null && (var3 = var1.length - 1) >= 0) {
               int var4 = this.top;
               int var5 = this.base;
               if (this.top - this.base > 0) {
                  int var7 = var2 - 1;

                  do {
                     int var9 = ((var5 & var3) << ASHIFT) + ABASE;
                     int var10 = ((var5 & var7) << ASHIFT) + ABASE;
                     ForkJoinTask var8 = (ForkJoinTask)U.getObjectVolatile(var1, var9);
                     if (var8 != null && U.compareAndSwapObject(var1, var9, var8, null)) {
                        U.putObjectVolatile(var6, var10, var8);
                     }
                  } while (++var5 != var4);
               }
            }

            return var6;
         }
      }

      public boolean isApparentlyUnblocked() {
         if (this.eventCount >= 0) {
            ForkJoinWorkerThread var1 = this.owner;
            State var2;
            if (this.owner != null && (var2 = var1.getState()) != State.BLOCKED && var2 != State.WAITING && var2 != State.TIMED_WAITING) {
               return true;
            }
         }

         return false;
      }

      public boolean pollAndExecCC(CountedCompleter<?> var1) {
         int var3 = this.base;
         if (this.base - this.top < 0) {
            ForkJoinTask[] var2 = this.array;
            if (this.array != null) {
               long var7 = ((var2.length - 1 & var3) << ASHIFT) + ABASE;
               Object var4;
               if ((var4 = U.getObjectVolatile(var2, var7)) == null) {
                  return true;
               }

               if (var4 instanceof CountedCompleter) {
                  CountedCompleter var5 = (CountedCompleter)var4;
                  CountedCompleter var6 = var5;

                  do {
                     if (var6 == var1) {
                        if (this.base == var3 && U.compareAndSwapObject(var2, var7, var5, null)) {
                           U.putOrderedInt(this, QBASE, var3 + 1);
                           var5.doExec();
                        }

                        return true;
                     }
                  } while ((var6 = var6.completer) != null);
               }
            }
         }

         return false;
      }

      public int queueSize() {
         int var1 = this.base - this.top;
         return var1 >= 0 ? 0 : -var1;
      }

      public boolean tryUnpush(ForkJoinTask<?> var1) {
         ForkJoinTask[] var2 = this.array;
         if (this.array != null) {
            int var3 = this.top;
            if (this.top != this.base) {
               if (U.compareAndSwapObject(var2, ((var2.length - 1 & --var3) << ASHIFT) + ABASE, var1, null)) {
                  this.top = var3;
                  return true;
               }
            }
         }

         return false;
      }

      public ForkJoinTask<?> poll() {
         while (true) {
            int var2 = this.base;
            if (this.base - this.top < 0) {
               ForkJoinTask[] var1 = this.array;
               if (this.array != null) {
                  int var4 = ((var1.length - 1 & var2) << ASHIFT) + ABASE;
                  ForkJoinTask var3 = (ForkJoinTask)U.getObjectVolatile(var1, var4);
                  if (var3 != null) {
                     if (!U.compareAndSwapObject(var1, var4, var3, null)) {
                        continue;
                     }

                     U.putOrderedInt(this, QBASE, var2 + 1);
                     return var3;
                  }

                  if (this.base != var2) {
                     continue;
                  }

                  if (var2 + 1 != this.top) {
                     Thread.yield();
                     continue;
                  }
               }
            }

            return null;
         }
      }

      public ForkJoinTask<?> peek() {
         ForkJoinTask[] var1 = this.array;
         int var2;
         if (var1 != null && (var2 = var1.length - 1) >= 0) {
            int var3 = this.mode == 0 ? this.top - 1 : this.base;
            int var4 = ((var3 & var2) << ASHIFT) + ABASE;
            return (ForkJoinTask<?>)U.getObjectVolatile(var1, var4);
         } else {
            return null;
         }
      }

      public boolean externalPopAndExecCC(CountedCompleter<?> var1) {
         int var3 = this.top;
         if (this.base - this.top < 0) {
            ForkJoinTask[] var2 = this.array;
            if (this.array != null) {
               long var7 = ((var2.length - 1 & var3 - 1) << ASHIFT) + ABASE;
               Object var4;
               if ((var4 = U.getObject(var2, var7)) instanceof CountedCompleter) {
                  CountedCompleter var5 = (CountedCompleter)var4;
                  CountedCompleter var6 = var5;

                  do {
                     if (var6 == var1) {
                        if (U.compareAndSwapInt(this, QLOCK, 0, 1)) {
                           if (this.top == var3 && this.array == var2 && U.compareAndSwapObject(var2, var7, var5, null)) {
                              this.top = var3 - 1;
                              this.qlock = 0;
                              var5.doExec();
                           } else {
                              this.qlock = 0;
                           }
                        }

                        return true;
                     }
                  } while ((var6 = var6.completer) != null);
               }
            }
         }

         return false;
      }

      public boolean tryRemoveAndExec(ForkJoinTask<?> var1) {
         if (var1 != null) {
            ForkJoinTask[] var3 = this.array;
            int var4;
            if (this.array != null && (var4 = var3.length - 1) >= 0) {
               int var5 = this.top;
               int var6 = this.base;
               int var7;
               if ((var7 = this.top - this.base) > 0) {
                  boolean var8 = false;
                  boolean var9 = true;
                  boolean var2 = true;

                  while (true) {
                     long var11 = ((--var5 & var4) << ASHIFT) + ABASE;
                     ForkJoinTask var10 = (ForkJoinTask)U.getObject(var3, var11);
                     if (var10 == null) {
                        break;
                     }

                     if (var10 == var1) {
                        if (var5 + 1 == this.top) {
                           if (U.compareAndSwapObject(var3, var11, var1, null)) {
                              this.top = var5;
                              var8 = true;
                           }
                        } else if (this.base == var6) {
                           var8 = U.compareAndSwapObject(var3, var11, var1, new ForkJoinPool.EmptyTask());
                        }
                        break;
                     }

                     if (var10.status >= 0) {
                        var9 = false;
                     } else if (var5 + 1 == this.top) {
                        if (U.compareAndSwapObject(var3, var11, var10, null)) {
                           this.top = var5;
                        }
                        break;
                     }

                     if (--var7 == 0) {
                        if (!var9 && this.base == var6) {
                           var2 = false;
                        }
                        break;
                     }
                  }

                  if (var8) {
                     var1.doExec();
                  }

                  return var2;
               }
            }
         }

         return false;
      }

      public ForkJoinTask<?> pop() {
         ForkJoinTask[] var1 = this.array;
         int var3;
         int var4;
         if (this.array != null && (var3 = var1.length - 1) >= 0) {
            while ((var4 = this.top - 1) - this.base >= 0) {
               long var5 = ((var3 & var4) << ASHIFT) + ABASE;
               ForkJoinTask var2;
               if ((var2 = (ForkJoinTask)U.getObject(var1, var5)) == null) {
                  break;
               }

               if (U.compareAndSwapObject(var1, var5, var2, null)) {
                  this.top = var4;
                  return var2;
               }
            }
         }

         return null;
      }

      public ForkJoinTask<?> nextLocalTask() {
         return this.mode == 0 ? this.pop() : this.poll();
      }

      public WorkQueue(ForkJoinPool var1, ForkJoinWorkerThread var2, int var3, int var4) {
         this.pool = var1;
         this.owner = var2;
         this.mode = (short)var3;
         this.hint = var4;
         this.base = this.top = 4096;
      }
   }
}
