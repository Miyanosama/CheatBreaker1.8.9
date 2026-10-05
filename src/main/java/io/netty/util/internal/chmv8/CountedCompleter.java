package io.netty.util.internal.chmv8;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.item.ItemTool;
import net.optifine.expr.ConstantFloat;
import com.cheatbreaker.client.command.ModuleCommandManager;
import sun.misc.Unsafe;

public abstract class CountedCompleter<T> extends ForkJoinTask<T> {
   public CountedCompleter<?> completer;
   public static long PENDING;
   public static Unsafe U;
   public volatile int pending;
   public static final long serialVersionUID = 5232453752276485070L;

   public int decrementPendingCountUnlessZero() {
      int var1;
      do {
         var1 = this.pending;
      } while (this.pending != 0 && !U.compareAndSwapInt(this, PENDING, var1, var1 - 1));

      return var1;
   }

   @Override
   public T getRawResult() {
      return null;
   }

   public void addToPendingCount(int var1) {
      int var2;
      do {
         var2 = this.pending;
      } while (!U.compareAndSwapInt(this, PENDING, this.pending, var2 + var1));
   }

   public boolean compareAndSetPendingCount(int var1, int var2) {
      return U.compareAndSwapInt(this, PENDING, var1, var2);
   }

   public CountedCompleter() {
      this.completer = null;
   }

   @Override
   public void internalPropagateException(Throwable var1) {
      CountedCompleter var2 = this;
      CountedCompleter var3 = this;

      while (var2.onExceptionalCompletion(var1, var3)) {
         var3 = var2;
         if ((var2 = var2.completer) == null || var2.status < 0 || var2.recordExceptionalCompletion(var1) != Integer.MIN_VALUE) {
            break;
         }
      }
   }

   public int getPendingCount() {
      return this.pending;
   }

   @Override
   public boolean exec() {
      this.compute();
      return false;
   }

   public abstract void compute();

   public void propagateCompletion() {
      CountedCompleter var1 = this;

      while (true) {
         int var3 = var1.pending;
         if (var1.pending == 0) {
            CountedCompleter var2 = var1;
            if ((var1 = var1.completer) == null) {
               var2.quietlyComplete();
               return;
            }
         } else if (U.compareAndSwapInt(var1, PENDING, var3, var3 - 1)) {
            return;
         }
      }
   }

   public boolean onExceptionalCompletion(Throwable var1, CountedCompleter<?> var2) {
      return true;
   }

   public static Unsafe getUnsafe() {
      try {
         return Unsafe.getUnsafe();
      } catch (SecurityException var2) {
         try {
            return AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {

               public Unsafe run() throws java.lang.Exception {
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

   @Override
   public void setRawResult(T var1) {
   }

   public void onCompletion(CountedCompleter<?> var1) {
   }

   public void setPendingCount(int var1) {
      this.pending = var1;
   }

   public void tryComplete() {
      CountedCompleter var1 = this;
      CountedCompleter var2 = this;

      while (true) {
         int var3 = var1.pending;
         if (var1.pending == 0) {
            var1.onCompletion(var2);
            var2 = var1;
            if ((var1 = var1.completer) == null) {
               var2.quietlyComplete();
               return;
            }
         } else if (U.compareAndSwapInt(var1, PENDING, var3, var3 - 1)) {
            return;
         }
      }
   }

   public CountedCompleter(CountedCompleter<?> var1) {
      this.completer = var1;
   }

   public CountedCompleter(CountedCompleter<?> var1, int var2) {
      this.completer = var1;
      this.pending = var2;
   }

   @Override
   public void complete(T var1) {
      this.setRawResult((T)var1);
      this.onCompletion(this);
      this.quietlyComplete();
      CountedCompleter var2 = this.completer;
      if (this.completer != null) {
         var2.tryComplete();
      }
   }

   public CountedCompleter<?> firstComplete() {
      int var1;
      do {
         var1 = this.pending;
         if (this.pending == 0) {
            return this;
         }
      } while (!U.compareAndSwapInt(this, PENDING, var1, var1 - 1));

      return null;
   }

   static {
      try {
         U = getUnsafe();
         PENDING = U.objectFieldOffset(CountedCompleter.class.getDeclaredField("pending"));
      } catch (Exception var1) {
         throw new Error(var1);
      }
   }

   public CountedCompleter<?> getRoot() {
      CountedCompleter var1 = this;

      while (true) {
         CountedCompleter var2 = var1.completer;
         if (var1.completer == null) {
            return var1;
         }

         var1 = var2;
      }
   }

   public CountedCompleter<?> getCompleter() {
      return this.completer;
   }

   public void quietlyCompleteRoot() {
      CountedCompleter var1 = this;

      while (true) {
         CountedCompleter var2 = var1.completer;
         if (var1.completer == null) {
            var1.quietlyComplete();
            return;
         }

         var1 = var2;
      }
   }

   public CountedCompleter<?> nextComplete() {
      CountedCompleter var1 = this.completer;
      if (this.completer != null) {
         return var1.firstComplete();
      } else {
         this.quietlyComplete();
         return null;
      }
   }
}
