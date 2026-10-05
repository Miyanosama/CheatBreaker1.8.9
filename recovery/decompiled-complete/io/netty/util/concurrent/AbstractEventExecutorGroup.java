package io.netty.util.concurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import net.minecraft.creativetab.CreativeTabs$10;

public abstract class AbstractEventExecutorGroup implements EventExecutorGroup {
   public CreativeTabs$10 __junk3645426264441071500;

   @Override
   public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> var1, long var2, TimeUnit var4) {
      return this.next().invokeAll(var1, var2, var4);
   }

   @Override
   public List<Runnable> shutdownNow() {
      this.shutdown();
      return Collections.emptyList();
   }

   @Override
   public <T> T invokeAny(Collection<? extends Callable<T>> var1) {
      return this.next().invokeAny(var1);
   }

   @Override
   public <T> T invokeAny(Collection<? extends Callable<T>> var1, long var2, TimeUnit var4) {
      return this.next().invokeAny(var1, var2, var4);
   }

   @Override
   public <V> ScheduledFuture<V> schedule(Callable<V> var1, long var2, TimeUnit var4) {
      return this.next().schedule(var1, var2, var4);
   }

   @Override
   public <T> List<java.util.concurrent.Future<T>> invokeAll(Collection<? extends Callable<T>> var1) {
      return this.next().invokeAll(var1);
   }

   @Override
   public <T> Future<T> submit(Callable<T> var1) {
      return this.next().submit(var1);
   }

   @Override
   public <T> Future<T> submit(Runnable var1, T var2) {
      return this.next().submit(var1, (T)var2);
   }

   @Override
   public Future<?> shutdownGracefully() {
      return this.shutdownGracefully(1107935651L & 3004743955432604682L, -5684672610407788401L & 5684672608937772047L, TimeUnit.SECONDS);
   }

   @Override
   public ScheduledFuture<?> scheduleWithFixedDelay(Runnable var1, long var2, long var4, TimeUnit var6) {
      return this.next().scheduleWithFixedDelay(var1, var2, var4, var6);
   }

   @Override
   public void execute(Runnable var1) {
      this.next().execute(var1);
   }

   @Override
   public ScheduledFuture<?> schedule(Runnable var1, long var2, TimeUnit var4) {
      return this.next().schedule(var1, var2, var4);
   }

   @Override
   public Future<?> submit(Runnable var1) {
      return this.next().submit(var1);
   }

   @Override
   public ScheduledFuture<?> scheduleAtFixedRate(Runnable var1, long var2, long var4, TimeUnit var6) {
      return this.next().scheduleAtFixedRate(var1, var2, var4, var6);
   }

   @Override
   public abstract void shutdown();
}
