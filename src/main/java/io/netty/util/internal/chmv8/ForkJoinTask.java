package io.netty.util.internal.chmv8;

import io.netty.handler.codec.http.ComposedLastHttpContent;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.util.HashedWheelTimer;
import io.netty.util.ThreadDeathWatcher;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.util.LoggingPrintStream;
import net.minecraft.util.Vec3i;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.optifine.reflect.ReflectorField;
import net.optifine.reflect.ReflectorResolver;
import net.optifine.util.ResUtils;
import org.apache.log4j.helpers.PatternParser;
import sun.misc.Unsafe;

public abstract class ForkJoinTask<V> implements Serializable, Future<V> {
   public volatile int status;
   public static Unsafe U;
   public static long STATUS;
   public static final int EXCEPTIONAL = -2147483648;
   public static final int DONE_MASK = -268435456;
   public static final long serialVersionUID = -7721805057305804111L;
   public static final int SMASK = 65535;
   public static final int CANCELLED = -1073741824;
   public static final int SIGNAL = 65536;
   public static final int EXCEPTION_MAP_CAPACITY = 32;
   public static final int NORMAL = -268435456;
   public static ReentrantLock exceptionTableLock = new ReentrantLock();
   public static ReferenceQueue<Object> exceptionTableRefQueue = new ReferenceQueue<>();
   public static ForkJoinTask.ExceptionNode[] exceptionTable = new ForkJoinTask.ExceptionNode[32];

   public boolean compareAndSetForkJoinTaskTag(short var1, short var2) {
      int var3;
      do {
         var3 = this.status;
         if ((short)this.status != var1) {
            return false;
         }
      } while (!U.compareAndSwapInt(this, STATUS, var3, var3 & -65536 | var2 & '\uffff'));

      return true;
   }

   public static ForkJoinTask<?> adapt(Runnable var0) {
      return new ForkJoinTask.AdaptedRunnableAction(var0);
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

   public int externalInterruptibleAwaitDone() throws java.lang.InterruptedException {
      ForkJoinPool var2 = ForkJoinPool.common;
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         int var1 = this.status;
         if (this.status >= 0 && var2 != null) {
            if (this instanceof CountedCompleter) {
               var2.externalHelpComplete((CountedCompleter<?>)this);
            } else if (var2.tryExternalUnpush(this)) {
               this.doExec();
            }
         }

         while (true) {
            var1 = this.status;
            if (this.status < 0) {
               return var1;
            }

            if (U.compareAndSwapInt(this, STATUS, var1, var1 | 65536)) {
               synchronized (this) {
                  if (this.status >= 0) {
                     this.wait();
                  } else {
                     this.notifyAll();
                  }
               }
            }
         }
      }
   }

   public void internalPropagateException(Throwable var1) {
   }

   public static <T extends ForkJoinTask<?>> Collection<T> invokeAll(Collection<T> var0) {
      if (var0 instanceof RandomAccess && var0 instanceof List) {
         List var1 = (List)var0;
         Object var2 = null;
         int var3 = var1.size() - 1;

         for (int var4 = var3; var4 >= 0; var4--) {
            ForkJoinTask var5 = (ForkJoinTask)var1.get(var4);
            if (var5 == null) {
               if (var2 == null) {
                  var2 = new NullPointerException();
               }
            } else if (var4 != 0) {
               var5.fork();
            } else if (var5.doInvoke() < -268435456 && var2 == null) {
               var2 = var5.getException();
            }
         }

         for (int var6 = 1; var6 <= var3; var6++) {
            ForkJoinTask var7 = (ForkJoinTask)var1.get(var6);
            if (var7 != null) {
               if (var2 != null) {
                  var7.cancel(false);
               } else if (var7.doJoin() < -268435456) {
                  var2 = var7.getException();
               }
            }
         }

         if (var2 != null) {
            rethrow((Throwable)var2);
         }

         return var0;
      } else {
         invokeAll((ForkJoinTask[])var0.toArray(new ForkJoinTask[var0.size()]));
         return var0;
      }
   }

   public int doExec() {
      int var1 = this.status;
      if (this.status >= 0) {
         boolean var2;
         try {
            var2 = this.exec();
         } catch (Throwable var4) {
            return this.setExceptionalCompletion(var4);
         }

         if (var2) {
            var1 = this.setCompletion(-268435456);
         }
      }

      return var1;
   }

   public void completeExceptionally(Throwable var1) {
      this.setExceptionalCompletion((Throwable)(!(var1 instanceof RuntimeException) && !(var1 instanceof Error) ? new RuntimeException(var1) : var1));
   }

   public void readObject(ObjectInputStream var1) throws java.io.IOException, java.lang.ClassNotFoundException {
      var1.defaultReadObject();
      Object var2 = var1.readObject();
      if (var2 != null) {
         this.setExceptionalCompletion((Throwable)var2);
      }
   }

   public abstract V getRawResult();

   public static void rethrow(Throwable var0) {
      if (var0 != null) {
         uncheckedThrow(var0);
      }
   }

   public int recordExceptionalCompletion(Throwable var1) {
      int var2 = this.status;
      if (this.status >= 0) {
         int var3 = System.identityHashCode(this);
         ReentrantLock var4 = exceptionTableLock;
         var4.lock();

         try {
            expungeStaleExceptions();
            ForkJoinTask.ExceptionNode[] var5 = exceptionTable;
            int var6 = var3 & var5.length - 1;
            ForkJoinTask.ExceptionNode var7 = var5[var6];

            while (true) {
               if (var7 == null) {
                  var5[var6] = new ForkJoinTask.ExceptionNode(this, var1, var5[var6]);
                  break;
               }

               if (var7.get() == this) {
                  break;
               }

               var7 = var7.next;
            }
         } finally {
            var4.unlock();
         }

         var2 = this.setCompletion(Integer.MIN_VALUE);
      }

      return var2;
   }

   public void writeObject(ObjectOutputStream var1) throws java.io.IOException {
      var1.defaultWriteObject();
      var1.writeObject(this.getException());
   }

   public static int getSurplusQueuedTaskCount() {
      return ForkJoinPool.getSurplusQueuedTaskCount();
   }

   public boolean isCompletedNormally() {
      return (this.status & -268435456) == -268435456;
   }

   public void quietlyComplete() {
      this.setCompletion(-268435456);
   }

   public void reinitialize() {
      if ((this.status & -268435456) == Integer.MIN_VALUE) {
         this.clearExceptionalCompletion();
      } else {
         this.status = 0;
      }
   }

   public static ForkJoinPool getPool() {
      Thread var0 = Thread.currentThread();
      return var0 instanceof ForkJoinWorkerThread ? ((ForkJoinWorkerThread)var0).pool : null;
   }

   public static void invokeAll(ForkJoinTask<?> var0, ForkJoinTask<?> var1) {
      var1.fork();
      int var2;
      if ((var2 = var0.doInvoke() & -268435456) != -268435456) {
         var0.reportException(var2);
      }

      int var3;
      if ((var3 = var1.doJoin() & -268435456) != -268435456) {
         var1.reportException(var3);
      }
   }

   public static void helpQuiesce() {
      Thread var0;
      if ((var0 = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
         ForkJoinWorkerThread var1 = (ForkJoinWorkerThread)var0;
         var1.pool.helpQuiescePool(var1.workQueue);
      } else {
         ForkJoinPool.quiesceCommonPool();
      }
   }

   @Override
   public boolean cancel(boolean var1) {
      return (this.setCompletion(-1073741824) & -268435456) == -1073741824;
   }

   public static <T> ForkJoinTask<T> adapt(Runnable var0, T var1) {
      return new ForkJoinTask.AdaptedRunnable<>(var0, (T)var1);
   }

   public static ForkJoinTask<?> pollTask() {
      Thread var0;
      ForkJoinWorkerThread var1;
      return (var0 = Thread.currentThread()) instanceof ForkJoinWorkerThread ? (var1 = (ForkJoinWorkerThread)var0).pool.nextTaskFor(var1.workQueue) : null;
   }

   public abstract void setRawResult(V var1);

   public static ForkJoinTask<?> pollNextLocalTask() {
      Thread var0;
      return (var0 = Thread.currentThread()) instanceof ForkJoinWorkerThread ? ((ForkJoinWorkerThread)var0).workQueue.nextLocalTask() : null;
   }

   public static void cancelIgnoringExceptions(ForkJoinTask<?> var0) {
      if (var0 != null && var0.status >= 0) {
         try {
            var0.cancel(false);
         } catch (Throwable var2) {
         }
      }
   }

   @Override
   public V get() throws java.lang.InterruptedException, java.util.concurrent.ExecutionException {
      int var1 = Thread.currentThread() instanceof ForkJoinWorkerThread ? this.doJoin() : this.externalInterruptibleAwaitDone();
      if ((var1 = var1 & -268435456) == -1073741824) {
         throw new CancellationException();
      } else {
         Throwable var2;
         if (var1 == Integer.MIN_VALUE && (var2 = this.getThrowableException()) != null) {
            throw new ExecutionException(var2);
         } else {
            return this.getRawResult();
         }
      }
   }

   public void reportException(int var1) {
      if (var1 == -1073741824) {
         throw new CancellationException();
      } else {
         if (var1 == Integer.MIN_VALUE) {
            rethrow(this.getThrowableException());
         }
      }
   }

   public static void helpExpungeStaleExceptions() {
      ReentrantLock var0 = exceptionTableLock;
      if (var0.tryLock()) {
         try {
            expungeStaleExceptions();
         } finally {
            var0.unlock();
         }
      }
   }

   public boolean isCompletedAbnormally() {
      return this.status < -268435456;
   }

   public short setForkJoinTaskTag(short var1) {
      int var2;
      do {
         var2 = this.status;
      } while (!U.compareAndSwapInt(this, STATUS, this.status, var2 & -65536 | var1 & '\uffff'));

      return (short)var2;
   }

   public static boolean inForkJoinPool() {
      return Thread.currentThread() instanceof ForkJoinWorkerThread;
   }

   public void quietlyJoin() {
      this.doJoin();
   }

   @Override
   public V get(long var1, TimeUnit var3) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         long var7 = var3.toNanos(var1);
         int var4 = this.status;
         if (this.status >= 0 && var7 > 0L) {
            long var10 = System.nanoTime() + var7;
            ForkJoinPool var12 = null;
            ForkJoinPool.WorkQueue var13 = null;
            Thread var14 = Thread.currentThread();
            if (var14 instanceof ForkJoinWorkerThread) {
               ForkJoinWorkerThread var15 = (ForkJoinWorkerThread)var14;
               var12 = var15.pool;
               var13 = var15.workQueue;
               var12.helpJoinOnce(var13, this);
            } else {
               ForkJoinPool var9 = ForkJoinPool.common;
               if (ForkJoinPool.common != null) {
                  if (this instanceof CountedCompleter) {
                     var9.externalHelpComplete((CountedCompleter<?>)this);
                  } else if (var9.tryExternalUnpush(this)) {
                     this.doExec();
                  }
               }
            }

            boolean var29 = false;
            boolean var16 = false;

            try {
               while (true) {
                  var4 = this.status;
                  if (this.status < 0) {
                     break;
                  }

                  if (var13 != null && var13.qlock < 0) {
                     cancelIgnoringExceptions(this);
                  } else if (!var29) {
                     if (var12 == null || var12.tryCompensate(var12.ctl)) {
                        var29 = true;
                     }
                  } else {
                     long var5;
                     if ((var5 = TimeUnit.NANOSECONDS.toMillis(var7)) > 0L && U.compareAndSwapInt(this, STATUS, var4, var4 | 65536)) {
                        synchronized (this) {
                           if (this.status >= 0) {
                              try {
                                 this.wait(var5);
                              } catch (InterruptedException var24) {
                                 if (var12 == null) {
                                    var16 = true;
                                 }
                              }
                           } else {
                              this.notifyAll();
                           }
                        }
                     }

                     var4 = this.status;
                     if (this.status < 0 || var16 || (var7 = var10 - System.nanoTime()) <= 0L) {
                        break;
                     }
                  }
               }
            } finally {
               if (var12 != null && var29) {
                  var12.incrementActiveCount();
               }
            }

            if (var16) {
               throw new InterruptedException();
            }
         }

         if ((var4 = var4 & -268435456) != -268435456) {
            if (var4 == -1073741824) {
               throw new CancellationException();
            }

            if (var4 != Integer.MIN_VALUE) {
               throw new TimeoutException();
            }

            Throwable var28;
            if ((var28 = this.getThrowableException()) != null) {
               throw new ExecutionException(var28);
            }
         }

         return this.getRawResult();
      }
   }

   public V invoke() {
      int var1;
      if ((var1 = this.doInvoke() & -268435456) != -268435456) {
         this.reportException(var1);
      }

      return this.getRawResult();
   }

   public boolean tryUnfork() {
      Thread var1;
      return (var1 = Thread.currentThread()) instanceof ForkJoinWorkerThread
         ? ((ForkJoinWorkerThread)var1).workQueue.tryUnpush(this)
         : ForkJoinPool.common.tryExternalUnpush(this);
   }

   public static ForkJoinTask<?> peekNextLocalTask() {
      Thread var0;
      ForkJoinPool.WorkQueue var1;
      if ((var0 = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
         var1 = ((ForkJoinWorkerThread)var0).workQueue;
      } else {
         var1 = ForkJoinPool.commonSubmitterQueue();
      }

      return var1 == null ? null : var1.peek();
   }

   public short getForkJoinTaskTag() {
      return (short)this.status;
   }

   public void clearExceptionalCompletion() {
      int var1 = System.identityHashCode(this);
      ReentrantLock var2 = exceptionTableLock;
      var2.lock();

      try {
         ForkJoinTask.ExceptionNode[] var3 = exceptionTable;
         int var4 = var1 & var3.length - 1;
         ForkJoinTask.ExceptionNode var5 = var3[var4];
         ForkJoinTask.ExceptionNode var6 = null;

         while (var5 != null) {
            ForkJoinTask.ExceptionNode var7 = var5.next;
            if (var5.get() == this) {
               if (var6 == null) {
                  var3[var4] = var7;
               } else {
                  var6.next = var7;
               }
               break;
            }

            var6 = var5;
            var5 = var7;
         }

         expungeStaleExceptions();
         this.status = 0;
      } finally {
         var2.unlock();
      }
   }

   static {
      try {
         U = getUnsafe();
         Class<ForkJoinTask> var0 = ForkJoinTask.class;
         STATUS = U.objectFieldOffset(var0.getDeclaredField("status"));
      } catch (Exception var1) {
         throw new Error(var1);
      }
   }

   public static <T> ForkJoinTask<T> adapt(Callable<? extends T> var0) {
      return new ForkJoinTask.AdaptedCallable<>(var0);
   }

   public static int getQueuedTaskCount() {
      Thread var0;
      ForkJoinPool.WorkQueue var1;
      if ((var0 = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
         var1 = ((ForkJoinWorkerThread)var0).workQueue;
      } else {
         var1 = ForkJoinPool.commonSubmitterQueue();
      }

      return var1 == null ? 0 : var1.queueSize();
   }

   public Throwable getThrowableException() {
      if ((this.status & -268435456) != Integer.MIN_VALUE) {
         return null;
      } else {
         int var1 = System.identityHashCode(this);
         ReentrantLock var3 = exceptionTableLock;
         var3.lock();

         ForkJoinTask.ExceptionNode var2;
         try {
            expungeStaleExceptions();
            ForkJoinTask.ExceptionNode[] var4 = exceptionTable;
            var2 = var4[var1 & var4.length - 1];

            while (var2 != null && var2.get() != this) {
               var2 = var2.next;
            }
         } finally {
            var3.unlock();
         }

         if (var2 != null) {
            Throwable var8 = var2.ex;
            if (var2.ex != null) {
               return var8;
            }
         }

         return null;
      }
   }

   public Throwable getException() {
      int var1 = this.status & -268435456;
      return (Throwable)(var1 >= -268435456 ? null : (var1 == -1073741824 ? new CancellationException() : this.getThrowableException()));
   }

   public boolean trySetSignal() {
      int var1 = this.status;
      return var1 >= 0 && U.compareAndSwapInt(this, STATUS, var1, var1 | 65536);
   }

   public int setCompletion(int var1) {
      int var2;
      do {
         var2 = this.status;
         if (this.status < 0) {
            return var2;
         }
      } while (!U.compareAndSwapInt(this, STATUS, var2, var2 | var1));

      if (var2 >>> 16 != 0) {
         synchronized (this) {
            this.notifyAll();
         }
      }

      return var1;
   }

   public int doJoin() {
      int var1 = this.status;
      Thread var2;
      ForkJoinWorkerThread var3;
      ForkJoinPool.WorkQueue var4;
      int var5;
      return this.status < 0
         ? var1
         : (
            (var2 = Thread.currentThread()) instanceof ForkJoinWorkerThread
               ? ((var4 = (var3 = (ForkJoinWorkerThread)var2).workQueue).tryUnpush(this) && (var5 = this.doExec()) < 0 ? var5 : var3.pool.awaitJoin(var4, this))
               : this.externalAwaitDone()
         );
   }

   public static <T extends Throwable> void uncheckedThrow(Throwable var0) throws T {
      throw (T)var0;
   }

   public static void expungeStaleExceptions() {
      Reference var0;
      while ((var0 = exceptionTableRefQueue.poll()) != null) {
         if (var0 instanceof ForkJoinTask.ExceptionNode) {
            ForkJoinTask var1 = ((ForkJoinTask.ExceptionNode)var0).get();
            ForkJoinTask.ExceptionNode[] var2 = exceptionTable;
            int var3 = System.identityHashCode(var1) & var2.length - 1;
            ForkJoinTask.ExceptionNode var4 = var2[var3];
            ForkJoinTask.ExceptionNode var5 = null;

            while (var4 != null) {
               ForkJoinTask.ExceptionNode var6 = var4.next;
               if (var4 == var0) {
                  if (var5 == null) {
                     var2[var3] = var6;
                     break;
                  }

                  var5.next = var6;
                  break;
               }

               var5 = var4;
               var4 = var6;
            }
         }
      }
   }

   public int doInvoke() {
      int var1;
      Thread var2;
      ForkJoinWorkerThread var3;
      return (var1 = this.doExec()) < 0
         ? var1
         : (
            (var2 = Thread.currentThread()) instanceof ForkJoinWorkerThread
               ? (var3 = (ForkJoinWorkerThread)var2).pool.awaitJoin(var3.workQueue, this)
               : this.externalAwaitDone()
         );
   }

   public void quietlyInvoke() {
      this.doInvoke();
   }

   @Override
   public boolean isDone() {
      return this.status < 0;
   }

   public abstract boolean exec();

   public static void invokeAll(ForkJoinTask<?>... var0) {
      Object var1 = null;
      int var2 = var0.length - 1;

      for (int var3 = var2; var3 >= 0; var3--) {
         ForkJoinTask var4 = var0[var3];
         if (var4 == null) {
            if (var1 == null) {
               var1 = new NullPointerException();
            }
         } else if (var3 != 0) {
            var4.fork();
         } else if (var4.doInvoke() < -268435456 && var1 == null) {
            var1 = var4.getException();
         }
      }

      for (int var5 = 1; var5 <= var2; var5++) {
         ForkJoinTask var6 = var0[var5];
         if (var6 != null) {
            if (var1 != null) {
               var6.cancel(false);
            } else if (var6.doJoin() < -268435456) {
               var1 = var6.getException();
            }
         }
      }

      if (var1 != null) {
         rethrow((Throwable)var1);
      }
   }

   public int externalAwaitDone() {
      ForkJoinPool var2 = ForkJoinPool.common;
      int var1 = this.status;
      if (this.status >= 0) {
         if (var2 != null) {
            if (this instanceof CountedCompleter) {
               var1 = var2.externalHelpComplete((CountedCompleter<?>)this);
            } else if (var2.tryExternalUnpush(this)) {
               var1 = this.doExec();
            }
         }

         if (var1 >= 0) {
            var1 = this.status;
            if (this.status >= 0) {
               boolean var3 = false;

               do {
                  if (U.compareAndSwapInt(this, STATUS, var1, var1 | 65536)) {
                     synchronized (this) {
                        if (this.status >= 0) {
                           try {
                              this.wait();
                           } catch (InterruptedException var7) {
                              var3 = true;
                           }
                        } else {
                           this.notifyAll();
                        }
                     }
                  }

                  var1 = this.status;
               } while (this.status >= 0);

               if (var3) {
                  Thread.currentThread().interrupt();
               }
            }
         }
      }

      return var1;
   }

   public ForkJoinTask<V> fork() {
      Thread var1;
      if ((var1 = Thread.currentThread()) instanceof ForkJoinWorkerThread) {
         ((ForkJoinWorkerThread)var1).workQueue.push(this);
      } else {
         ForkJoinPool.common.externalPush(this);
      }

      return this;
   }

   public V join() {
      int var1;
      if ((var1 = this.doJoin() & -268435456) != -268435456) {
         this.reportException(var1);
      }

      return this.getRawResult();
   }

   @Override
   public boolean isCancelled() {
      return (this.status & -268435456) == -1073741824;
   }

   public void complete(V var1) {
      try {
         this.setRawResult((V)var1);
      } catch (Throwable var3) {
         this.setExceptionalCompletion(var3);
         return;
      }

      this.setCompletion(-268435456);
   }

   public int setExceptionalCompletion(Throwable var1) {
      int var2 = this.recordExceptionalCompletion(var1);
      if ((var2 & -268435456) == Integer.MIN_VALUE) {
         this.internalPropagateException(var1);
      }

      return var2;
   }

   public static final class AdaptedCallable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
      public static final long serialVersionUID = 2838392045355241008L;
      public Callable<? extends T> callable;
      public T result;

      @Override
      public boolean exec() {
         try {
            this.result = (T)this.callable.call();
            return true;
         } catch (Error var2) {
            throw var2;
         } catch (RuntimeException var3) {
            throw var3;
         } catch (Exception var4) {
            throw new RuntimeException(var4);
         }
      }

      @Override
      public T getRawResult() {
         return this.result;
      }

      public AdaptedCallable(Callable<? extends T> var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            this.callable = var1;
         }
      }

      @Override
      public void run() {
         this.invoke();
      }

      @Override
      public void setRawResult(T var1) {
         this.result = (T)var1;
      }
   }

   public static final class AdaptedRunnable<T> extends ForkJoinTask<T> implements RunnableFuture<T> {
      public T result;
      public Runnable runnable;
      public static final long serialVersionUID = 5232453952276885070L;

      @Override
      public boolean exec() {
         this.runnable.run();
         return true;
      }

      @Override
      public T getRawResult() {
         return this.result;
      }

      @Override
      public void run() {
         this.invoke();
      }

      public AdaptedRunnable(Runnable var1, T var2) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            this.runnable = var1;
            this.result = (T)var2;
         }
      }

      @Override
      public void setRawResult(T var1) {
         this.result = (T)var1;
      }
   }

   public static final class AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {
      public Runnable runnable;
      public static final long serialVersionUID = 5232453952276885070L;

      @Override
      public void run() {
         this.invoke();
      }

      public Void getRawResult() {
         return null;
      }

      public void setRawResult(Void var1) {
      }

      public AdaptedRunnableAction(Runnable var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            this.runnable = var1;
         }
      }

      @Override
      public boolean exec() {
         this.runnable.run();
         return true;
      }
   }

   public static final class ExceptionNode extends WeakReference<ForkJoinTask<?>> {
      public ForkJoinTask.ExceptionNode next;
      public Throwable ex;
      public long thrower;

      public ExceptionNode(ForkJoinTask<?> var1, Throwable var2, ForkJoinTask.ExceptionNode var3) {
         super(var1, ForkJoinTask.exceptionTableRefQueue);
         this.ex = var2;
         this.next = var3;
         this.thrower = Thread.currentThread().getId();
      }
   }

   public static final class RunnableExecuteAction extends ForkJoinTask<Void> {
      public Runnable runnable;
      public static final long serialVersionUID = 5232453952276885070L;

      public void setRawResult(Void var1) {
      }

      public Void getRawResult() {
         return null;
      }

      public RunnableExecuteAction(Runnable var1) {
         if (var1 == null) {
            throw new NullPointerException();
         } else {
            this.runnable = var1;
         }
      }

      @Override
      public void internalPropagateException(Throwable var1) {
         rethrow(var1);
      }

      @Override
      public boolean exec() {
         this.runnable.run();
         return true;
      }
   }
}
