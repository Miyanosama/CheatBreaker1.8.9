package net.minecraft.crash;

import java.util.concurrent.Callable;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.gui.MapItemRenderer$1;
import net.minecraft.util.BlockPos;
import org.newsclub.net.unix.NarSystem;
import recovered.unidentified.UnidentifiedClass4776;

public class CrashReportCategory$3 implements Callable<String> {
   public SoundEventAccessor field_0004;
   public NarSystem field_0001;
   public UnidentifiedClass4776 field_0003;
   public MapItemRenderer$1 field_0000;

   public String call() {
      return CrashReportCategory.getCoordinateInfo(this.field_175749_a);
   }

   public CrashReportCategory$3(BlockPos var1) {
      this.field_175749_a = var1;
      super();
   }
}
