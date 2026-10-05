package net.minecraft.util;

import io.netty.util.concurrent.BlockingOperationException;
import java.util.Iterator;

public class BlockPos$1 implements Iterable<BlockPos> {
   public BlockingOperationException field_0000;

   public BlockPos$1(BlockPos var1, BlockPos var2) {
      this.field_179307_a = var1;
      this.field_179306_b = var2;
      super();
   }

   @Override
   public Iterator<BlockPos> iterator() {
      return new BlockPos$1$1(this);
   }
}
