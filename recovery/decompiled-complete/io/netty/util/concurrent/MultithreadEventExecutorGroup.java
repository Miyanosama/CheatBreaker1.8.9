package io.netty.util.concurrent;

import io.netty.buffer.WrappedByteBuf;
import io.netty.handler.codec.DecoderResult;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import junit.awtui.Logo;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.village.MerchantRecipe;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$19;

public abstract class MultithreadEventExecutorGroup extends AbstractEventExecutorGroup {
   public LogBrokerMonitor$19 __junk1642659459485761293;
   public WrappedByteBuf __junk2384292489017449630;
   public MultithreadEventExecutorGroup$EventExecutorChooser chooser;
   public EventExecutor[] children;
   public MerchantRecipe __junk2139017352195367022;
   public Logo __junk3535204447301545522;
   public DecoderResult __junk2542468272697239667;
   public NBTTagIntArray __junk9054026264486896592;
   public Promise<?> terminationFuture;
   public AtomicInteger terminatedChildren;
   public AtomicInteger childIndex = new AtomicInteger();
   public Slot __junk2644238395580141698;

   @Override
   public Future<?> shutdownGracefully(long var1, long var3, TimeUnit var5) {
      for (EventExecutor var9 : this.children) {
         var9.shutdownGracefully(var1, var3, var5);
      }

      return this.terminationFuture();
   }

   @Override
   public Iterator<EventExecutor> iterator() {
      return this.children().iterator();
   }

   public static boolean isPowerOfTwo(int var0) {
      return (var0 & -var0) == var0;
   }

   public int executorCount() {
      return this.children.length;
   }

   @Override
   public boolean isShutdown() {
      for (EventExecutor var4 : this.children) {
         if (!var4.isShutdown()) {
            return false;
         }
      }

      return true;
   }

   @Override
   public EventExecutor next() {
      return this.chooser.next();
   }

   public Set<EventExecutor> children() {
      Set var1 = Collections.newSetFromMap(new LinkedHashMap());
      Collections.addAll(var1, this.children);
      return var1;
   }

   @Override
   public boolean isShuttingDown() {
      for (EventExecutor var4 : this.children) {
         if (!var4.isShuttingDown()) {
            return false;
         }
      }

      return true;
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public MultithreadEventExecutorGroup(int var1, ThreadFactory var2, Object... var3) {
      this.terminatedChildren = new AtomicInteger();
      this.terminationFuture = new DefaultPromise(GlobalEventExecutor.INSTANCE);
      if (var1 <= 0) {
         throw new IllegalArgumentException(String.format("nThreads: %d (expected: > 0)", var1));
      } else {
         if (var2 == null) {
            var2 = this.newDefaultThreadFactory();
         }

         this.children = new SingleThreadEventExecutor[var1];
         if (isPowerOfTwo(this.children.length)) {
            this.chooser = new MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser(this, null);
         } else {
            this.chooser = new MultithreadEventExecutorGroup$GenericEventExecutorChooser(this, null);
         }

         for (int var4 = 0; var4 < var1; var4++) {
            boolean var5 = false;
            boolean var17 = false /* VF: Semaphore variable */;

            try {
               var17 = true;
               this.children[var4] = this.newChild(var2, var3);
               var5 = true;
               var17 = false;
            } catch (Exception var18) {
               throw new IllegalStateException("failed to create a child event loop", var18);
            } finally {
               if (var17) {
                  if (!var5) {
                     for (int var10 = 0; var10 < var4; var10++) {
                        this.children[var10].shutdownGracefully();
                     }

                     for (int var27 = 0; var27 < var4; var27++) {
                        EventExecutor var11 = this.children[var27];

                        try {
                           while (!var11.isTerminated()) {
                              var11.awaitTermination(2147483647L & -3111577778726633473L, TimeUnit.SECONDS);
                           }
                        } catch (InterruptedException var19) {
                           Thread.currentThread().interrupt();
                           break;
                        }
                     }
                  }
               }
            }

            if (!var5) {
               for (int var6 = 0; var6 < var4; var6++) {
                  this.children[var6].shutdownGracefully();
               }

               for (int var24 = 0; var24 < var4; var24++) {
                  EventExecutor var7 = this.children[var24];

                  try {
                     while (!var7.isTerminated()) {
                        var7.awaitTermination(-1941377760274415617L & 1941377762421899263L, TimeUnit.SECONDS);
                     }
                  } catch (InterruptedException var21) {
                     Thread.currentThread().interrupt();
                     break;
                  }
               }
            }
         }

         MultithreadEventExecutorGroup$1 var22 = new MultithreadEventExecutorGroup$1(this);

         for (EventExecutor var8 : this.children) {
            var8.terminationFuture().addListener(var22);
         }
      }
   }

   @Override
   public boolean isTerminated() {
      for (EventExecutor var4 : this.children) {
         if (!var4.isTerminated()) {
            return false;
         }
      }

      return true;
   }

   public ThreadFactory newDefaultThreadFactory() {
      return new DefaultThreadFactory(this.getClass());
   }

   @Override
   public Future<?> terminationFuture() {
      return this.terminationFuture;
   }

   @Override
   public boolean awaitTermination(long var1, TimeUnit var3) {
      long var4 = System.nanoTime() + var3.toNanos(var1);

      long var10;
      for (EventExecutor var9 : this.children) {
         do {
            var10 = var4 - System.nanoTime();
            if (var10 <= (32786L & 5696570778631803428L)) {
               return this.isTerminated();
            }
         } while (!var9.awaitTermination(var10, TimeUnit.NANOSECONDS));
      }

      return this.isTerminated();
   }

   @Override
   public void shutdown() {
      for (EventExecutor var4 : this.children) {
         var4.shutdown();
      }
   }

   public abstract EventExecutor newChild(ThreadFactory var1, Object... var2);
}
