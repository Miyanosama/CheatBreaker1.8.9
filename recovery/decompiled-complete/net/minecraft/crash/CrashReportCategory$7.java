package net.minecraft.crash;

import io.netty.channel.SucceededChannelFuture;
import java.util.concurrent.Callable;
import net.minecraft.util.BlockPos;

public class CrashReportCategory$7 implements Callable<String> {
   public SucceededChannelFuture field_0000;

   public String call() {
      return CrashReportCategory.getCoordinateInfo(this.field_175752_a);
   }

   public CrashReportCategory$7(BlockPos var1) {
      this.field_175752_a = var1;
      super();
   }
}
