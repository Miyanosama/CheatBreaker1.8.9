package io.netty.channel;

import io.netty.channel.sctp.nio.NioSctpChannel$2;
import io.netty.util.internal.OneTimeTask;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$1;
import recovered.unidentified.UnidentifiedClass0026;

public class AbstractChannelHandlerContext$2 extends OneTimeTask {
   public StructureOceanMonumentPieces$1 __junk2676411883724769158;
   public UnidentifiedClass0026 __junk3054903389856674592;
   public NioSctpChannel$2 __junk4438351867853700223;

   public AbstractChannelHandlerContext$2(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$next = var2;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$100(this.val$next);
   }
}
