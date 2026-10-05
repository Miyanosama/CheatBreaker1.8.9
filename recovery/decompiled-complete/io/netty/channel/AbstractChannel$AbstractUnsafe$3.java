package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.world.gen.NoiseGenerator;
import recovered.unidentified.UnidentifiedClass0433;

public class AbstractChannel$AbstractUnsafe$3 extends OneTimeTask {
   public UnidentifiedClass0433 __junk5613525612871860863;
   public NoiseGenerator __junk2355680988592509054;

   @Override
   public void run() {
      AbstractChannel.access$500(this.this$1.this$0).fireChannelInactive();
   }

   public AbstractChannel$AbstractUnsafe$3(AbstractChannel$AbstractUnsafe var1) {
      this.this$1 = var1;
      super();
   }
}
