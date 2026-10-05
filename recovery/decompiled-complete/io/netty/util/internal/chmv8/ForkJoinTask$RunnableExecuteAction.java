package io.netty.util.internal.chmv8;

import net.minecraft.network.play.server.S21PacketChunkData;

public class ForkJoinTask$RunnableExecuteAction extends ForkJoinTask<Void> {
   public Runnable runnable;
   public static long serialVersionUID;
   public S21PacketChunkData __junk8539140334140600022;

   public void setRawResult(Void var1) {
   }

   public Void getRawResult() {
      return null;
   }

   public ForkJoinTask$RunnableExecuteAction(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.runnable = var1;
      }
   }

   @Override
   public void internalPropagateException(Throwable var1) {
      rethrow(var1);
   }

   @Override
   public boolean exec() {
      this.runnable.run();
      return true;
   }
}
