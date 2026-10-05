package io.netty.util.concurrent;

import com.cheatbreaker.client.cosmetic.model.DragonWingsModel;
import java.util.concurrent.TimeUnit;
import net.minecraft.item.ItemEditableBook;

public abstract class CompleteFuture<V> extends AbstractFuture<V> {
   public EventExecutor executor;

   @Override
   public Future<V> syncUninterruptibly() {
      return this;
   }

   @Override
   public Future<V> removeListener(GenericFutureListener<? extends Future<? super V>> var1) {
      return this;
   }

   public CompleteFuture(EventExecutor var1) {
      this.executor = var1;
   }

   @Override
   public boolean await(long var1, TimeUnit var3) throws java.lang.InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return true;
      }
   }

   @Override
   public boolean await(long var1) throws java.lang.InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return true;
      }
   }

   public EventExecutor executor() {
      return this.executor;
   }

   @Override
   public Future<V> addListener(GenericFutureListener<? extends Future<? super V>> var1) {
      if (var1 == null) {
         throw new NullPointerException("listener");
      } else {
         DefaultPromise.notifyListener(this.executor(), this, var1);
         return this;
      }
   }

   @Override
   public boolean isCancelled() {
      return false;
   }

   @Override
   public Future<V> removeListeners(GenericFutureListener<? extends Future<? super V>>... var1) {
      return this;
   }

   @Override
   public Future<V> await() throws java.lang.InterruptedException {
      if (Thread.interrupted()) {
         throw new InterruptedException();
      } else {
         return this;
      }
   }

   @Override
   public Future<V> sync() throws java.lang.InterruptedException {
      return this;
   }

   @Override
   public boolean isDone() {
      return true;
   }

   @Override
   public boolean awaitUninterruptibly(long var1, TimeUnit var3) {
      return true;
   }

   @Override
   public Future<V> awaitUninterruptibly() {
      return this;
   }

   @Override
   public boolean cancel(boolean var1) {
      return false;
   }

   @Override
   public boolean isCancellable() {
      return false;
   }

   @Override
   public Future<V> addListeners(GenericFutureListener<? extends Future<? super V>>... var1) {
      if (var1 == null) {
         throw new NullPointerException("listeners");
      } else {
         for (GenericFutureListener var5 : var1) {
            if (var5 == null) {
               break;
            }

            DefaultPromise.notifyListener(this.executor(), this, var5);
         }

         return this;
      }
   }

   @Override
   public boolean awaitUninterruptibly(long var1) {
      return true;
   }
}
