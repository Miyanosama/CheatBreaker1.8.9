package io.netty.util.internal.chmv8;

import java.lang.Thread.State;
import java.util.concurrent.RejectedExecutionException;
import javax.vecmath.Vector3d;
import net.minecraft.client.renderer.RenderGlobal$ContainerLocalRenderInformation;
import recovered.unidentified.UnidentifiedClass0557;
import sun.misc.Unsafe;

public class ForkJoinPool$WorkQueue {
   public static long QBASE;
   public static Unsafe U;
   public volatile Object pad11;
   public volatile Object pad17;
   public short poolIndex;
   public volatile Object pad10;
   public volatile Object pad19;
   public int top;
   public volatile ForkJoinTask<?> currentJoin;
   public static int MAXIMUM_QUEUE_CAPACITY;
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
   public UnidentifiedClass0557 __junk8274266706942445042;
   public volatile long pad05;
   public volatile Object pad16;
   public static int ASHIFT;
   public static int INITIAL_QUEUE_CAPACITY;
   public int hint;
   public int nsteals;
   public volatile Object pad1a;
   public volatile long pad02;
   public volatile long pad06;
   public ForkJoinPool pool;
   public volatile Object pad1c;
   public Vector3d __junk5025543750985729427;
   public volatile long pad00;
   public ForkJoinWorkerThread owner;
   public volatile Object pad15;
   public volatile Thread parker;
   public short mode;
   public volatile Object pad1d;
   public int nextWait;
   public volatile Object pad14;
   public RenderGlobal$ContainerLocalRenderInformation __junk8419348090524886571;
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
         U = ForkJoinPool.access$000();
         Class<ForkJoinPool$WorkQueue> var0 = ForkJoinPool$WorkQueue.class;
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
                        var8 = U.compareAndSwapObject(var3, var11, var1, new ForkJoinPool$EmptyTask());
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

   public ForkJoinPool$WorkQueue(ForkJoinPool var1, ForkJoinWorkerThread var2, int var3, int var4) {
      this.pool = var1;
      this.owner = var2;
      this.mode = (short)var3;
      this.hint = var4;
      this.base = this.top = 4096;
   }
}
