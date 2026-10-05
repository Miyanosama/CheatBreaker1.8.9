package io.netty.channel;

import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.block.material.MaterialLiquid;
import com.cheatbreaker.client.ui.resourcepack.DirectoryRegistrationVisitor;

public class ChannelFlushPromiseNotifier {
   public long writeCounter;
   public boolean tryNotify;
   public Queue<ChannelFlushPromiseNotifier.FlushCheckpoint> flushCheckpoints = new ArrayDeque<>();

   public long writeCounter() {
      return this.writeCounter;
   }

   public ChannelFlushPromiseNotifier(boolean var1) {
      this.tryNotify = var1;
   }

   public ChannelFlushPromiseNotifier notifyFlushFutures() {
      return this.notifyPromises();
   }

   public ChannelFlushPromiseNotifier() {
      this(false);
   }

   public ChannelFlushPromiseNotifier add(ChannelPromise var1, long var2) {
      if (var1 == null) {
         throw new NullPointerException("promise");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException("pendingDataSize must be >= 0 but was " + var2);
      } else {
         long var4 = this.writeCounter + var2;
         if (var1 instanceof ChannelFlushPromiseNotifier.FlushCheckpoint) {
            ChannelFlushPromiseNotifier.FlushCheckpoint var6 = (ChannelFlushPromiseNotifier.FlushCheckpoint)var1;
            var6.flushCheckpoint(var4);
            this.flushCheckpoints.add(var6);
         } else {
            this.flushCheckpoints.add(new ChannelFlushPromiseNotifier.DefaultFlushCheckpoint(var4, var1));
         }

         return this;
      }
   }

   public ChannelFlushPromiseNotifier notifyFlushFutures(Throwable var1) {
      return this.notifyPromises(var1);
   }

   public ChannelFlushPromiseNotifier notifyFlushFutures(Throwable var1, Throwable var2) {
      return this.notifyPromises(var1, var2);
   }

   public ChannelFlushPromiseNotifier add(ChannelPromise var1, int var2) {
      return this.add(var1, (long)var2);
   }

   public void notifyPromises0(Throwable var1) {
      if (this.flushCheckpoints.isEmpty()) {
         this.writeCounter = 0L;
      } else {
         long var2 = this.writeCounter;

         while (true) {
            ChannelFlushPromiseNotifier.FlushCheckpoint var4 = this.flushCheckpoints.peek();
            if (var4 == null) {
               this.writeCounter = 0L;
               break;
            }

            if (var4.flushCheckpoint() > var2) {
               if (var2 > 0L && this.flushCheckpoints.size() == 1) {
                  this.writeCounter = 0L;
                  var4.flushCheckpoint(var4.flushCheckpoint() - var2);
               }
               break;
            }

            this.flushCheckpoints.remove();
            ChannelPromise var5 = var4.promise();
            if (var1 == null) {
               if (this.tryNotify) {
                  var5.trySuccess();
               } else {
                  var5.setSuccess();
               }
            } else if (this.tryNotify) {
               var5.tryFailure(var1);
            } else {
               var5.setFailure(var1);
            }
         }

         long var8 = this.writeCounter;
         if (var8 >= 549755813888L) {
            this.writeCounter = 0L;

            for (ChannelFlushPromiseNotifier.FlushCheckpoint var7 : this.flushCheckpoints) {
               var7.flushCheckpoint(var7.flushCheckpoint() - var8);
            }
         }
      }
   }

   public ChannelFlushPromiseNotifier increaseWriteCounter(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("delta must be >= 0 but was " + var1);
      } else {
         this.writeCounter += var1;
         return this;
      }
   }

   public ChannelFlushPromiseNotifier notifyPromises(Throwable var1, Throwable var2) {
      this.notifyPromises0(var1);

      while (true) {
         ChannelFlushPromiseNotifier.FlushCheckpoint var3 = this.flushCheckpoints.poll();
         if (var3 == null) {
            return this;
         }

         if (this.tryNotify) {
            var3.promise().tryFailure(var2);
         } else {
            var3.promise().setFailure(var2);
         }
      }
   }

   public ChannelFlushPromiseNotifier notifyPromises() {
      this.notifyPromises0(null);
      return this;
   }

   public ChannelFlushPromiseNotifier notifyPromises(Throwable var1) {
      this.notifyPromises();

      while (true) {
         ChannelFlushPromiseNotifier.FlushCheckpoint var2 = this.flushCheckpoints.poll();
         if (var2 == null) {
            return this;
         }

         if (this.tryNotify) {
            var2.promise().tryFailure(var1);
         } else {
            var2.promise().setFailure(var1);
         }
      }
   }

   public static class DefaultFlushCheckpoint implements ChannelFlushPromiseNotifier.FlushCheckpoint {
      public ChannelPromise future;
      public long checkpoint;

      @Override
      public long flushCheckpoint() {
         return this.checkpoint;
      }

      public DefaultFlushCheckpoint(long var1, ChannelPromise var3) {
         this.checkpoint = var1;
         this.future = var3;
      }

      @Override
      public void flushCheckpoint(long var1) {
         this.checkpoint = var1;
      }

      @Override
      public ChannelPromise promise() {
         return this.future;
      }
   }

   public interface FlushCheckpoint {
      void flushCheckpoint(long var1);

      long flushCheckpoint();

      ChannelPromise promise();
   }
}
