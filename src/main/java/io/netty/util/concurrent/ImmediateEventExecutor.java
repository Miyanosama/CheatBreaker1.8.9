package io.netty.util.concurrent;

import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.stats.Achievement;

public class ImmediateEventExecutor extends AbstractEventExecutor {
   public Future<?> terminationFuture = new FailedFuture(GlobalEventExecutor.INSTANCE, new UnsupportedOperationException());
   public static ImmediateEventExecutor INSTANCE = new ImmediateEventExecutor();

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
      return new ImmediateEventExecutor.ImmediateProgressivePromise<>(this);
   }

   @Override
   public <V> Promise<V> newPromise() {
      return new ImmediateEventExecutor.ImmediatePromise<>(this);
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

   public static class ImmediateProgressivePromise<V> extends DefaultProgressivePromise<V> {

      @Override
      public void checkDeadLock() {
      }

      public ImmediateProgressivePromise(EventExecutor var1) {
         super(var1);
      }
   }

   public static class ImmediatePromise<V> extends DefaultPromise<V> {
      public ImmediatePromise(EventExecutor var1) {
         super(var1);
      }

      @Override
      public void checkDeadLock() {
      }
   }
}
