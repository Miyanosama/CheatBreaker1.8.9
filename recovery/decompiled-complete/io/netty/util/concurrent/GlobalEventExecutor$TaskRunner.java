package io.netty.util.concurrent;

import recovered.unidentified.UnidentifiedClass1318;

public class GlobalEventExecutor$TaskRunner implements Runnable {
   public UnidentifiedClass1318 __junk8041740583785070873;

   public GlobalEventExecutor$TaskRunner(GlobalEventExecutor var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      while (true) {
         Runnable var1 = this.this$0.takeTask();
         if (var1 != null) {
            try {
               var1.run();
            } catch (Throwable var3) {
               GlobalEventExecutor.access$100().warn("Unexpected exception from the global event executor: ", var3);
            }

            if (var1 != this.this$0.purgeTask) {
               continue;
            }
         }

         if (this.this$0.taskQueue.isEmpty() && this.this$0.delayedTaskQueue.size() == 1) {
            boolean var2 = GlobalEventExecutor.access$200(this.this$0).compareAndSet(true, false);
            if (!$assertionsDisabled && !var2) {
               throw new AssertionError();
            }

            if (this.this$0.taskQueue.isEmpty() && this.this$0.delayedTaskQueue.size() == 1
               || !GlobalEventExecutor.access$200(this.this$0).compareAndSet(false, true)) {
               return;
            }
         }
      }
   }
}
