package io.netty.channel;

import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.block.material.MaterialLiquid;
import recovered.unidentified.UnidentifiedClass5059;

public class ChannelFlushPromiseNotifier {
   public long writeCounter;
   public boolean tryNotify;
   public Queue<ChannelFlushPromiseNotifier$FlushCheckpoint> flushCheckpoints = new ArrayDeque<>();
   public MaterialLiquid __junk5603887559256627103;
   public UnidentifiedClass5059 __junk2194862390112716621;

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
      } else if (var2 < (135267616L & 7001835312303999108L)) {
         throw new IllegalArgumentException("pendingDataSize must be >= 0 but was " + var2);
      } else {
         long var4 = this.writeCounter + var2;
         if (var1 instanceof ChannelFlushPromiseNotifier$FlushCheckpoint) {
            ChannelFlushPromiseNotifier$FlushCheckpoint var6 = (ChannelFlushPromiseNotifier$FlushCheckpoint)var1;
            var6.flushCheckpoint(var4);
            this.flushCheckpoints.add(var6);
         } else {
            this.flushCheckpoints.add(new ChannelFlushPromiseNotifier$DefaultFlushCheckpoint(var4, var1));
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
         this.writeCounter = -1319029826460708864L & 1319029825150656519L;
      } else {
         long var2 = this.writeCounter;

         while (true) {
            ChannelFlushPromiseNotifier$FlushCheckpoint var4 = this.flushCheckpoints.peek();
            if (var4 == null) {
               this.writeCounter = 75579392L & 273420304L;
               break;
            }

            if (var4.flushCheckpoint() > var2) {
               if (var2 > (1074537792L & 549470850L) && this.flushCheckpoints.size() == 1) {
                  this.writeCounter = 8333L & 8815879481573196880L;
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
         if (var8 >= (5210407333125427492L & 549768396811L)) {
            this.writeCounter = -714346068840527296L & 714346067298489357L;

            for (ChannelFlushPromiseNotifier$FlushCheckpoint var7 : this.flushCheckpoints) {
               var7.flushCheckpoint(var7.flushCheckpoint() - var8);
            }
         }
      }
   }

   public ChannelFlushPromiseNotifier increaseWriteCounter(long var1) {
      if (var1 < (340431096L & 563102468L)) {
         throw new IllegalArgumentException("delta must be >= 0 but was " + var1);
      } else {
         this.writeCounter += var1;
         return this;
      }
   }

   public ChannelFlushPromiseNotifier notifyPromises(Throwable var1, Throwable var2) {
      this.notifyPromises0(var1);

      while (true) {
         ChannelFlushPromiseNotifier$FlushCheckpoint var3 = this.flushCheckpoints.poll();
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
         ChannelFlushPromiseNotifier$FlushCheckpoint var2 = this.flushCheckpoints.poll();
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
}
