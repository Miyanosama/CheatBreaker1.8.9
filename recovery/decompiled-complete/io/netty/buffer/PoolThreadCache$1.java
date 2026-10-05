package io.netty.buffer;

import io.netty.channel.SingleThreadEventLoop;
import junit.swingui.TestSuitePanel$1;
import net.minecraft.network.EnumConnectionState$1;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Library;

public class PoolThreadCache$1 implements Runnable {
   public EnumConnectionState$1 __junk2445097962468712199;
   public StructureStrongholdPieces$Library __junk1479603489662862832;
   public TestSuitePanel$1 __junk109182695586438305;
   public SingleThreadEventLoop __junk5904864208128255192;

   public PoolThreadCache$1(PoolThreadCache var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      PoolThreadCache.access$000(this.this$0);
   }
}
