package io.netty.channel.epoll;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.handler.codec.serialization.SoftReferenceMap;
import io.netty.util.collection.IntObjectHashMap;
import io.netty.util.collection.IntObjectMap;
import io.netty.util.collection.IntObjectMap$Entry;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.optifine.entity.model.ModelAdapterCaveSpider;
import org.apache.log4j.helpers.PatternParser;

public class EpollEventLoop extends SingleThreadEventLoop {
   public SoftReferenceMap __junk7828753389903523155;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(EpollEventLoop.class);
   public volatile int ioRatio;
   public PatternParser __junk7583253816849579285;
   public int id;
   public boolean overflown;
   public int eventFd;
   public IntObjectMap<AbstractEpollChannel> ids = new IntObjectHashMap<>();
   public static AtomicIntegerFieldUpdater<EpollEventLoop> WAKEN_UP_UPDATER;
   public long[] events;
   public ModelAdapterCaveSpider __junk8509270965188944342;
   public int epollFd;
   public volatile int wakenUp;

   public int nextId() {
      int var1 = this.id;
      if (var1 == Integer.MAX_VALUE) {
         this.overflown = true;
         var1 = 0;
      }

      if (this.overflown) {
         while (this.ids.containsKey(++var1)) {
         }

         this.id = var1;
      } else {
         this.id = ++var1;
      }

      return var1;
   }

   public int epollWait(boolean var1) {
      int var2 = 0;
      long var3 = System.nanoTime();
      long var5 = var3 + this.delayNanos(var3);

      while (true) {
         long var7 = (var5 - var3 + (-4883653769279590094L & 4883653768837571429L)) / (8514533889909146176L & -8514533891103570353L);
         if (var7 <= (-2800691570951579644L & 2800691570367742547L)) {
            if (var2 == 0) {
               int var10 = Native.epollWait(this.epollFd, this.events, 0);
               if (var10 > 0) {
                  return var10;
               }
            }

            return 0;
         }

         int var9 = Native.epollWait(this.epollFd, this.events, (int)var7);
         var2++;
         if (var9 != 0 || var1 || this.wakenUp == 1 || this.hasTasks() || this.hasScheduledTasks()) {
            return var9;
         }

         var3 = System.nanoTime();
      }
   }

   public void closeAll() {
      Native.epollWait(this.epollFd, this.events, 0);
      ArrayList var1 = new ArrayList(this.ids.size());

      for (IntObjectMap$Entry var3 : this.ids.entries()) {
         var1.add(var3.value());
      }

      for (AbstractEpollChannel var5 : var1) {
         var5.unsafe().close(var5.unsafe().voidPromise());
      }
   }

   static {
      AtomicIntegerFieldUpdater var0 = PlatformDependent.newAtomicIntegerFieldUpdater(EpollEventLoop.class, "wakenUp");
      if (var0 == null) {
         var0 = AtomicIntegerFieldUpdater.newUpdater(EpollEventLoop.class, "wakenUp");
      }

      WAKEN_UP_UPDATER = var0;
   }

   public void processReady(long[] var1, int var2) {
      for (int var3 = 0; var3 < var2; var3++) {
         long var4 = var1[var3];
         int var6 = (int)(var4 >> 32);
         if (var6 == 0) {
            Native.eventFdRead(this.eventFd);
         } else {
            boolean var7 = (var4 & 8078718894856082453L & 168329601L) != (8661765282950346890L & 411697217L);
            boolean var8 = (var4 & -4336812067683683390L & 419971075L) != (2094331233253417090L & 1107594365L);
            boolean var9 = (var4 & 4558239564047057449L & 1179648268L) != (553804804L & 5299860345425494080L);
            AbstractEpollChannel var10 = this.ids.get(var6);
            if (var10 != null) {
               AbstractEpollChannel$AbstractEpollUnsafe var11 = (AbstractEpollChannel$AbstractEpollUnsafe)var10.unsafe();
               if (var8 && var10.isOpen()) {
                  var11.epollOutReady();
               }

               if (var7 && var10.isOpen()) {
                  var11.epollInReady();
               }

               if (var9 && var10.isOpen()) {
                  var11.epollRdHupReady();
               }
            }
         }
      }
   }

   public int getIoRatio() {
      return this.ioRatio;
   }

   public void remove(AbstractEpollChannel var1) {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         if (this.ids.remove(var1.id) != null && var1.isOpen()) {
            Native.epollCtlDel(this.epollFd, var1.fd);
         }
      }
   }

   public void add(AbstractEpollChannel var1) {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         int var2 = this.nextId();
         Native.epollCtlAdd(this.epollFd, var1.fd, var1.flags, var2);
         var1.id = var2;
         this.ids.put(var2, var1);
      }
   }

   @Override
   public void cleanup() {
      try {
         Native.close(this.epollFd);
      } catch (IOException var3) {
         logger.warn("Failed to close the epoll fd.", (Throwable)var3);
      }

      try {
         Native.close(this.eventFd);
      } catch (IOException var2) {
         logger.warn("Failed to close the event fd.", (Throwable)var2);
      }
   }

   public EpollEventLoop(EventLoopGroup var1, ThreadFactory var2, int var3) {
      super(var1, var2, false);
      this.ioRatio = 50;
      this.events = new long[var3];
      boolean var4 = false;
      int var5 = -1;
      int var6 = -1;

      try {
         this.epollFd = var5 = Native.epollCreate();
         this.eventFd = var6 = Native.eventFd();
         Native.epollCtlAdd(var5, var6, 1, 0);
         var4 = true;
      } finally {
         if (!var4) {
            if (var5 != -1) {
               try {
                  Native.close(var5);
               } catch (Exception var16) {
               }
            }

            if (var6 != -1) {
               try {
                  Native.close(var6);
               } catch (Exception var15) {
               }
            }
         }
      }
   }

   @Override
   public void wakeup(boolean var1) {
      if (!var1 && WAKEN_UP_UPDATER.compareAndSet(this, 0, 1)) {
         Native.eventFdWrite(this.eventFd, 1745327169L & 37767169L);
      }
   }

   @Override
   public Queue<Runnable> newTaskQueue() {
      return PlatformDependent.newMpscQueue();
   }

   @Override
   public void run() {
      while (true) {
         boolean var1 = WAKEN_UP_UPDATER.getAndSet(this, 0) == 1;

         try {
            int var2;
            if (this.hasTasks()) {
               var2 = Native.epollWait(this.epollFd, this.events, 0);
            } else {
               var2 = this.epollWait(var1);
               if (this.wakenUp == 1) {
                  Native.eventFdWrite(this.eventFd, -4249408849147944951L & 806682625L);
               }
            }

            int var3 = this.ioRatio;
            if (var3 == 100) {
               if (var2 > 0) {
                  this.processReady(this.events, var2);
               }

               this.runAllTasks();
            } else {
               long var4 = System.nanoTime();
               if (var2 > 0) {
                  this.processReady(this.events, var2);
               }

               long var6 = System.nanoTime() - var4;
               this.runAllTasks(var6 * (100 - var3) / var3);
            }

            if (this.isShuttingDown()) {
               this.closeAll();
               if (this.confirmShutdown()) {
                  return;
               }
            }
         } catch (Throwable var9) {
            logger.warn("Unexpected exception in the selector loop.", var9);

            try {
               Thread.sleep(7070529487293254638L & 1342195704L);
            } catch (InterruptedException var8) {
            }
         }
      }
   }

   public void setIoRatio(int var1) {
      if (var1 > 0 && var1 <= 100) {
         this.ioRatio = var1;
      } else {
         throw new IllegalArgumentException("ioRatio: " + var1 + " (expected: 0 < ioRatio <= 100)");
      }
   }

   public void modify(AbstractEpollChannel var1) {
      if (!$assertionsDisabled && !this.inEventLoop()) {
         throw new AssertionError();
      } else {
         Native.epollCtlMod(this.epollFd, var1.fd, var1.flags, var1.id);
      }
   }
}
