package net.minecraft.world;

import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.CrashReportCategory$1;
import net.minecraft.util.BlockPos;
import org.apache.log4j.spi.NOPLogger;

public class World$1 implements Callable<String> {
   public CrashReportCategory$1 field_0003;
   public NOPLogger field_0002;

   public String call() {
      return CrashReportCategory.getCoordinateInfo(this.field_180253_a);
   }

   public World$1(World var1, BlockPos var2) {
      this.field_77485_a = var1;
      this.field_180253_a = var2;
      super();
   }
}
