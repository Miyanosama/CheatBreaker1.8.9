package net.minecraft.crash;

import io.netty.channel.socket.oio.OioServerSocketChannel;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;

public class CrashReportCategory$1 implements Callable<String> {
   public OioServerSocketChannel field_0001;
   public BlockStone field_0002;

   public CrashReportCategory$1(int var1, Block var2) {
      this.field_85080_a = var1;
      this.field_147151_b = var2;
      super();
   }

   public String call() {
      try {
         return String.format(
            "ID #%d (%s // %s)", this.field_85080_a, this.field_147151_b.getUnlocalizedName(), this.field_147151_b.getClass().getCanonicalName()
         );
      } catch (Throwable var2) {
         return "ID #" + this.field_85080_a;
      }
   }
}
