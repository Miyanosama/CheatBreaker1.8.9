package io.netty.util.concurrent;

import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.stats.Achievement;

public class ImmediateEventExecutor extends AbstractEventExecutor {
   public Future<?> terminationFuture = new FailedFuture(GlobalEventExecutor.INSTANCE, new UnsupportedOperationException());
   public static ImmediateEventExecutor INSTANCE = new ImmediateEventExecutor();
   public Achievement __junk5605491369004870096;
   public LayerSheepWool __junk3613414283837356050;

   @Override
   public Future<?> terminationFuture() {
      return this.terminationFuture;
   }

   @Override
   public void shutdown() {
   }

   @Override
   public Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5) {
      return this.terminationFuture();
   }

   @Override
   public boolean isShutdown() {
      return false;
   }

   @Override
   public boolean inEventLoop(Thread var1) {
      return true;
   }

   @Override
   public EventExecutorGroup parent() {
      return null;
   }

   @Override
   public <V> ProgressivePromise<V> newProgressivePromise() {
      return new ImmediateEventExecutor$ImmediateProgressivePromise<>(this);
   }

   @Override
   public <V> Promise<V> newPromise() {
      return new ImmediateEventExecutor$ImmediatePromise<>(this);
   }

   @Override
   public boolean isShuttingDown() {
      return false;
   }

   @Override
   public boolean inEventLoop() {
      return true;
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) {
      return false;
   }

   @Override
   public boolean isTerminated() {
      return false;
   }

   @Override
   public void execute(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("command");
      } else {
         var1.run();
      }
   }
}
