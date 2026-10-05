package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.block.BlockSlime;

public class AbstractChannel$AbstractUnsafe$5 extends OneTimeTask {
   public BlockSlime __junk5468996027683273707;

   public AbstractChannel$AbstractUnsafe$5(AbstractChannel$AbstractUnsafe var1) {
      this.this$1 = var1;
      super();
   }

   @Override
   public void run() {
      AbstractChannel.access$500(this.this$1.this$0).fireChannelInactive();
   }
}
