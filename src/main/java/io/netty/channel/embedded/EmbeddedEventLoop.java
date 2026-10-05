package io.netty.channel.embedded;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.util.concurrent.AbstractEventExecutor;
import io.netty.util.concurrent.Future;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.RegionRenderCache;
import net.minecraft.item.ItemSpade;

public class EmbeddedEventLoop extends AbstractEventExecutor implements EventLoop {
   public Queue<Runnable> tasks = new ArrayDeque<>(2);

   @Override
   public ChannelFuture register(Channel var1) {
      return this.register(var1, new DefaultChannelPromise(var1, this));
   }

   @Override
   public EventLoopGroup parent() {
      return this;
   }

   @Override
   public Future<?> terminationFuture() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isTerminated() {
      return false;
   }

   @Override
   public boolean inEventLoop(Thread var1) {
      return true;
   }

   @Override
   public boolean inEventLoop() {
      return true;
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) throws java.lang.InterruptedException {
      Thread.sleep(var3.toMillis(var1));
      return false;
   }

   @Override
   public ChannelFuture register(Channel var1, ChannelPromise var2) {
      var1.unsafe().register(this, var2);
      return var2;
   }

   @Override
   public boolean isShutdown() {
      return false;
   }

   @Override
   public Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isShuttingDown() {
      return false;
   }

   @Override
   public void execute(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException("command");
      } else {
         this.tasks.add(var1);
      }
   }

   @Override
   public EventLoop next() {
      return this;
   }

   @Override
   public void shutdown() {
      throw new UnsupportedOperationException();
   }

   public void runTasks() {
      while (true) {
         Runnable var1 = this.tasks.poll();
         if (var1 == null) {
            return;
         }

         var1.run();
      }
   }
}
