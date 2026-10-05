package io.netty.channel;

import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import net.minecraft.block.state.BlockWorldState$1;
import net.optifine.entity.model.ModelAdapterSign;
import recovered.unidentified.UnidentifiedClass0672;

public class PendingWriteQueue$PendingWrite$1 extends Recycler<PendingWriteQueue$PendingWrite> {
   public ModelAdapterSign __junk8476117276425938354;
   public UnidentifiedClass0672 __junk5514877788038989937;
   public BlockWorldState$1 __junk9098808666918388074;

   public PendingWriteQueue$PendingWrite newObject(Recycler$Handle var1) {
      return new PendingWriteQueue$PendingWrite(var1, null);
   }
}
