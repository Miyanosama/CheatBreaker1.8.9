package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.item.Item$7;
import net.minecraft.util.MathHelper;
import org.apache.log4j.helpers.Loader;

public class HashedWheelTimer$Worker implements Runnable {
   public Set<Timeout> unprocessedTimeouts;
   public Loader __junk7577937902068190434;
   public long tick;
   public Item$7 __junk7152864297389079080;
   public MathHelper __junk6385287351778621792;
   public GuiListExtended __junk6890819522686331912;

   public HashedWheelTimer$Worker(HashedWheelTimer var1) {
      this.this$0 = var1;
      super();
      this.unprocessedTimeouts = new HashSet<>();
   }

   public long waitForNextTick() {
      long var1 = HashedWheelTimer.access$900(this.this$0) * (this.tick + (77801814351160841L & -77801816443451261L));

      while (true) {
         long var3 = System.nanoTime() - HashedWheelTimer.access$200(this.this$0);
         long var5 = (var1 - var3 + (-7847386369862151297L & 1213162047L)) / (114256707L & 18825812L);
         if (var5 <= (1913694870L & -4584616518888700888L)) {
            if (var3 == (-9223372035473727486L & -5247928403142639423L)) {
               return -811701301027859961L & -9223372036850553599L;
            }

            return var3;
         }

         if (PlatformDependent.isWindows()) {
            var5 = var5 / (4204546242876227594L & 1545606302L) * (12583950L & 4370642525155250202L);
         }

         try {
            Thread.sleep(var5);
         } catch (InterruptedException var8) {
            if (HashedWheelTimer.access$600().get(this.this$0) == 2) {
               return -9223372036514937773L & -3235308332457111032L;
            }
         }
      }
   }

   public Set<Timeout> unprocessedTimeouts() {
      return Collections.unmodifiableSet(this.unprocessedTimeouts);
   }

   public void processCancelledTasks() {
      while (true) {
         Runnable var1 = (Runnable)HashedWheelTimer.access$1000(this.this$0).poll();
         if (var1 == null) {
            return;
         }

         try {
            var1.run();
         } catch (Throwable var3) {
            if (HashedWheelTimer.logger.isWarnEnabled()) {
               HashedWheelTimer.logger.warn("An exception was thrown while process a cancellation task", var3);
            }
         }
      }
   }

   public void transferTimeoutsToBuckets() {
      for (int var1 = 0; var1 < 100000; var1++) {
         HashedWheelTimer$HashedWheelTimeout var2 = (HashedWheelTimer$HashedWheelTimeout)HashedWheelTimer.access$700(this.this$0).poll();
         if (var2 == null) {
            break;
         }

         if (var2.state() != 1) {
            long var3 = HashedWheelTimer$HashedWheelTimeout.access$800(var2) / HashedWheelTimer.access$900(this.this$0);
            var2.remainingRounds = (var3 - this.tick) / HashedWheelTimer.access$500(this.this$0).length;
            long var5 = Math.max(var3, this.tick);
            int var7 = (int)(var5 & HashedWheelTimer.access$400(this.this$0));
            HashedWheelTimer$HashedWheelBucket var8 = HashedWheelTimer.access$500(this.this$0)[var7];
            var8.addTimeout(var2);
         }
      }
   }

   @Override
   public void run() {
      HashedWheelTimer.access$202(this.this$0, System.nanoTime());
      if (HashedWheelTimer.access$200(this.this$0) == (629155929L & 6920614658881650720L)) {
         HashedWheelTimer.access$202(this.this$0, 21498561L & -202208690772631499L);
      }

      HashedWheelTimer.access$300(this.this$0).countDown();

      do {
         long var1 = this.waitForNextTick();
         if (var1 > (5833152132865441824L & 444600402L)) {
            int var3 = (int)(this.tick & HashedWheelTimer.access$400(this.this$0));
            this.processCancelledTasks();
            HashedWheelTimer$HashedWheelBucket var4 = HashedWheelTimer.access$500(this.this$0)[var3];
            this.transferTimeoutsToBuckets();
            var4.expireTimeouts(var1);
            this.tick += 2540021608494989441L & -2540021609565991895L;
         }
      } while (HashedWheelTimer.access$600().get(this.this$0) == 1);

      for (HashedWheelTimer$HashedWheelBucket var8 : HashedWheelTimer.access$500(this.this$0)) {
         var8.clearTimeouts(this.unprocessedTimeouts);
      }

      while (true) {
         HashedWheelTimer$HashedWheelTimeout var6 = (HashedWheelTimer$HashedWheelTimeout)HashedWheelTimer.access$700(this.this$0).poll();
         if (var6 == null) {
            this.processCancelledTasks();
            return;
         }

         if (!var6.isCancelled()) {
            this.unprocessedTimeouts.add(var6);
         }
      }
   }
}
