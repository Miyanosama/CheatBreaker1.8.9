package io.netty.channel;

import com.cheatbreaker.client.module.type.TeammatesModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import io.netty.util.concurrent.AbstractEventExecutorGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ReadOnlyIterator;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import javazoom.jl.player.FactoryRegistry;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.entity.monster.EntitySilverfish;
import net.optifine.reflect.FieldLocatorFixed;

public class ThreadPerChannelEventLoopGroup extends AbstractEventExecutorGroup implements EventLoopGroup {
   public ThreadFactory threadFactory;
   public Queue<ThreadPerChannelEventLoop> idleChildren;
   public Set<ThreadPerChannelEventLoop> activeChildren = Collections.newSetFromMap(PlatformDependent.newConcurrentHashMap());
   public int maxChannels;
   public Promise<?> terminationFuture;
   public ChannelException tooManyChannels;
   public volatile boolean shuttingDown;
   public Object[] childArgs;
   public FutureListener<Object> childTerminationListener;

   @Override
   public boolean isTerminated() {
      for (EventLoop var2 : this.activeChildren) {
         if (!var2.isTerminated()) {
            return false;
         }
      }

      for (EventLoop var4 : this.idleChildren) {
         if (!var4.isTerminated()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public ChannelFuture register(Channel var1) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         try {
            EventLoop var2 = this.nextChild();
            return var2.register(var1, new DefaultChannelPromise(var1, var2));
         } catch (Throwable var3) {
            return new FailedChannelFuture(var1, GlobalEventExecutor.INSTANCE, var3);
         }
      }
   }

   public ThreadPerChannelEventLoopGroup(int var1, ThreadFactory var2, Object... var3) {
      this.idleChildren = new ConcurrentLinkedQueue<>();
      this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
      this.childTerminationListener = new FutureListener<Object>() {

         @Override
         public void operationComplete(Future<Object> var1) throws java.lang.Exception {
            if (ThreadPerChannelEventLoopGroup.this.isTerminated()) {
               ThreadPerChannelEventLoopGroup.this.terminationFuture.trySuccess(null);
            }
         }
      };
      if (var1 < 0) {
         throw new IllegalArgumentException(String.format("maxChannels: %d (expected: >= 0)", var1));
      } else if (var2 == null) {
         throw new NullPointerException("threadFactory");
      } else {
         if (var3 == null) {
            this.childArgs = EmptyArrays.EMPTY_OBJECTS;
         } else {
            this.childArgs = (Object[])var3.clone();
         }

         this.maxChannels = var1;
         this.threadFactory = var2;
         this.tooManyChannels = new ChannelException("too many channels (max: " + var1 + ')');
         this.tooManyChannels.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      }
   }

   public ThreadPerChannelEventLoopGroup(int var1) {
      this(var1, Executors.defaultThreadFactory());
   }

   public EventLoop nextChild() throws java.lang.Exception {
      if (this.shuttingDown) {
         throw new RejectedExecutionException("shutting down");
      } else {
         ThreadPerChannelEventLoop var1 = this.idleChildren.poll();
         if (var1 == null) {
            if (this.maxChannels > 0 && this.activeChildren.size() >= this.maxChannels) {
               throw this.tooManyChannels;
            }

            var1 = this.newChild(this.childArgs);
            var1.terminationFuture().addListener(this.childTerminationListener);
         }

         this.activeChildren.add(var1);
         return var1;
      }
   }

   @Override
   public boolean isShutdown() {
      for (EventLoop var2 : this.activeChildren) {
         if (!var2.isShutdown()) {
            return false;
         }
      }

      for (EventLoop var4 : this.idleChildren) {
         if (!var4.isShutdown()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void shutdown() {
      this.shuttingDown = true;

      for (EventLoop var2 : this.activeChildren) {
         var2.shutdown();
      }

      for (EventLoop var4 : this.idleChildren) {
         var4.shutdown();
      }

      if (this.isTerminated()) {
         this.terminationFuture.trySuccess(null);
      }
   }

   @Override
   public Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5) {
      this.shuttingDown = true;

      for (EventLoop var7 : this.activeChildren) {
         var7.shutdownGracefully(var1, var3, var5);
      }

      for (EventLoop var9 : this.idleChildren) {
         var9.shutdownGracefully(var1, var3, var5);
      }

      if (this.isTerminated()) {
         this.terminationFuture.trySuccess(null);
      }

      return this.terminationFuture();
   }

   @Override
   public ChannelFuture register(Channel var1, ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         try {
            return this.nextChild().register(var1, var2);
         } catch (Throwable var4) {
            var2.setFailure(var4);
            return var2;
         }
      }
   }

   @Override
   public Future<?> terminationFuture() {
      return this.terminationFuture;
   }

   @Override
   public Iterator<EventExecutor> iterator() {
      return new ReadOnlyIterator<>(this.activeChildren.iterator());
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) throws java.lang.InterruptedException {
      long var4 = System.nanoTime() + var3.toNanos(var1);

      long var8;
      for (EventLoop var7 : this.activeChildren) {
         do {
            var8 = var4 - System.nanoTime();
            if (var8 <= 0L) {
               return this.isTerminated();
            }
         } while (!var7.awaitTermination(var8, TimeUnit.NANOSECONDS));
      }

      for (EventLoop var11 : this.idleChildren) {
         do {
            var8 = var4 - System.nanoTime();
            if (var8 <= 0L) {
               return this.isTerminated();
            }
         } while (!var11.awaitTermination(var8, TimeUnit.NANOSECONDS));
      }

      return this.isTerminated();
   }

   public ThreadPerChannelEventLoopGroup() {
      this(0);
   }

   public ThreadPerChannelEventLoop newChild(Object... var1) throws java.lang.Exception {
      return new ThreadPerChannelEventLoop(this);
   }

   @Override
   public boolean isShuttingDown() {
      for (EventLoop var2 : this.activeChildren) {
         if (!var2.isShuttingDown()) {
            return false;
         }
      }

      for (EventLoop var4 : this.idleChildren) {
         if (!var4.isShuttingDown()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public EventLoop next() {
      throw new UnsupportedOperationException();
   }
}
