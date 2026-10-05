package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelShapes$2;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;

public class CrashReportCategory$6 implements Callable<String> {
   public BlockModelShapes$2 field_0002;
   public S3BPacketScoreboardObjective field_0000;

   public String call() {
      return this.field_175754_a.toString();
   }

   public CrashReportCategory$6(IBlockState var1) {
      this.field_175754_a = var1;
      super();
   }
}
