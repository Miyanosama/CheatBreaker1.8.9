package io.netty.util.internal.chmv8;

import net.minecraft.world.pathfinder.WalkNodeProcessor;
import recovered.unidentified.UnidentifiedClass1349;

public class ForkJoinWorkerThread extends Thread {
   public ConcurrentHashMapV8$MapReduceEntriesToIntTask __junk1002368016142330957;
   public ForkJoinPool$WorkQueue workQueue;
   public WalkNodeProcessor __junk575374933466811471;
   public UnidentifiedClass1349 __junk111965476060414842;
   public ForkJoinPool pool;

   @Override
   public void run() {
      Throwable var1 = null;

      try {
         this.onStart();
         this.pool.runWorker(this.workQueue);
      } catch (Throwable var40) {
         var1 = var40;
      } finally {
         try {
            this.onTermination(var1);
         } catch (Throwable var41) {
            if (var1 == null) {
               var1 = var41;
            }
         } finally {
            this.pool.deregisterWorker(this, var1);
         }
      }
   }

   public int getPoolIndex() {
      return this.workQueue.poolIndex >>> 1;
   }

   public void onStart() {
   }

   public ForkJoinPool getPool() {
      return this.pool;
   }

   public void onTermination(Throwable var1) {
   }

   public ForkJoinWorkerThread(ForkJoinPool var1) {
      super("aForkJoinWorkerThread");
      this.pool = var1;
      this.workQueue = var1.registerWorker(this);
   }
}
