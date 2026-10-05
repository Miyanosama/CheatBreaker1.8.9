package net.minecraft.world.chunk.storage;

import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.concurrent.SingleThreadEventExecutor$1;
import net.minecraft.command.NumberInvalidException;
import org.apache.log4j.rewrite.RewriteAppender;

public class NibbleArrayReader {
   public FastThreadLocalThread field_0003;
   public byte[] data;
   public NumberInvalidException field_0002;
   public int depthBitsPlusFour;
   public int depthBits;
   public SingleThreadEventExecutor$1 field_0001;
   public RewriteAppender field_0006;

   public NibbleArrayReader(byte[] var1, int var2) {
      this.data = var1;
      this.depthBits = var2;
      this.depthBitsPlusFour = var2 + 4;
   }

   public int get(int var1, int var2, int var3) {
      int var4 = var1 << this.depthBitsPlusFour | var3 << this.depthBits | var2;
      int var5 = var4 >> 1;
      int var6 = var4 & 1;
      return var6 == 0 ? this.data[var5] & 15 : this.data[var5] >> 4 & 15;
   }
}
