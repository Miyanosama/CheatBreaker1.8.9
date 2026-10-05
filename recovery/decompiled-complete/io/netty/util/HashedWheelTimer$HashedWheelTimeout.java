package io.netty.util;

import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.util.internal.MpscLinkedQueueNode;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.command.CommandEnchant;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;

public class HashedWheelTimer$HashedWheelTimeout extends MpscLinkedQueueNode<Timeout> implements Timeout {
   public static int ST_CANCELLED;
   public HashedWheelTimer$HashedWheelTimeout prev;
   public HashedWheelTimer timer;
   public CommandEnchant __junk454680725367256816;
   public static int ST_EXPIRED;
   public HashedWheelTimer$HashedWheelTimeout next;
   public volatile int state = 0;
   public static AtomicIntegerFieldUpdater<HashedWheelTimer$HashedWheelTimeout> STATE_UPDATER;
   public TimerTask task;
   public DefaultHttpDataFactory __junk2914325205513601360;
   public EntityMinecartMobSpawner __junk2829319571433948262;
   public long deadline;
   public long remainingRounds;
   public static int ST_INIT;
   public HashedWheelTimer$HashedWheelBucket bucket;

   @Override
   public boolean isCancelled() {
      return this.state() == 1;
   }

   static {
      AtomicIntegerFieldUpdater var0 = PlatformDependent.newAtomicIntegerFieldUpdater(HashedWheelTimer$HashedWheelTimeout.class, "state");
      if (var0 == null) {
         var0 = AtomicIntegerFieldUpdater.newUpdater(HashedWheelTimer$HashedWheelTimeout.class, "state");
      }

      STATE_UPDATER = var0;
   }

   @Override
   public Timer timer() {
      return this.timer;
   }

   @Override
   public String toString() {
      long var1 = System.nanoTime();
      long var3 = this.deadline - var1 + HashedWheelTimer.access$200(this.timer);
      StringBuilder var5 = new StringBuilder(192);
      var5.append(StringUtil.simpleClassName(this));
      var5.append('(');
      var5.append("deadline: ");
      if (var3 > (7758933877972140105L & -7758933878479215744L)) {
         var5.append(var3);
         var5.append(" ns later");
      } else if (var3 < (1457592899804872741L & -1457592900659897064L)) {
         var5.append(-var3);
         var5.append(" ns ago");
      } else {
         var5.append("now");
      }

      if (this.isCancelled()) {
         var5.append(", cancelled");
      }

      var5.append(", task: ");
      var5.append(this.task());
      return var5.append(')').toString();
   }

   @Override
   public boolean cancel() {
      if (!this.compareAndSetState(0, 1)) {
         return false;
      } else {
         HashedWheelTimer.access$1000(this.timer).add(new HashedWheelTimer$HashedWheelTimeout$1(this));
         return true;
      }
   }

   public boolean compareAndSetState(int var1, int var2) {
      return STATE_UPDATER.compareAndSet(this, var1, var2);
   }

   @Override
   public TimerTask task() {
      return this.task;
   }

   public HashedWheelTimer$HashedWheelTimeout value() {
      return this;
   }

   public void expire() {
      if (this.compareAndSetState(0, 2)) {
         try {
            this.task.run(this);
         } catch (Throwable var2) {
            if (HashedWheelTimer.logger.isWarnEnabled()) {
               HashedWheelTimer.logger.warn("An exception was thrown by " + TimerTask.class.getSimpleName() + '.', var2);
            }
         }
      }
   }

   @Override
   public boolean isExpired() {
      return this.state() == 2;
   }

   public HashedWheelTimer$HashedWheelTimeout(HashedWheelTimer var1, TimerTask var2, long var3) {
      this.timer = var1;
      this.task = var2;
      this.deadline = var3;
   }

   public int state() {
      return this.state;
   }
}
