package io.netty.util.concurrent;

import io.netty.channel.AbstractChannel$AbstractUnsafe$5;
import io.netty.channel.local.LocalChannel$2;
import net.minecraft.world.gen.structure.StructureVillagePieces$Path;

public class DefaultPromise$5 implements Runnable {
   public LocalChannel$2 __junk6694754139954795061;
   public StructureVillagePieces$Path __junk3646313503214147283;
   public AbstractChannel$AbstractUnsafe$5 __junk3122131474550099613;

   public DefaultPromise$5(DefaultPromise var1, ProgressiveFuture var2, GenericProgressiveFutureListener var3, long var4, long var6) {
      this.this$0 = var1;
      this.val$self = var2;
      this.val$l = var3;
      this.val$progress = var4;
      this.val$total = var6;
      super();
   }

   @Override
   public void run() {
      DefaultPromise.access$300(this.val$self, this.val$l, this.val$progress, this.val$total);
   }
}
