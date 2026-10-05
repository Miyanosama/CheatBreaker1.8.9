package io.netty.buffer;

import net.minecraft.block.BlockPane;
import net.optifine.util.MathUtilsTest$1;

public class PoolThreadCache$SubPageMemoryRegionCache<T> extends PoolThreadCache$MemoryRegionCache<T> {
   public MathUtilsTest$1 __junk1927019610289352658;
   public BlockPane __junk282620743487103641;

   public PoolThreadCache$SubPageMemoryRegionCache(int var1) {
      super(var1);
   }

   @Override
   public void initBuf(PoolChunk<T> var1, long var2, PooledByteBuf<T> var4, int var5) {
      var1.initBufWithSubpage(var4, var2, var5);
   }
}
