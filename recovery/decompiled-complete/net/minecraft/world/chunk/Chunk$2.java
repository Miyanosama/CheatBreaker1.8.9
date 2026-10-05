package net.minecraft.world.chunk;

import io.netty.channel.ChannelFlushPromiseNotifier;
import java.util.concurrent.Callable;
import junit.swingui.TestRunner$1;
import net.minecraft.client.gui.GuiFlatPresets$ListSlot;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;
import net.optifine.reflect.ReflectorMethod;
import net.optifine.render.GlAlphaState;

public class Chunk$2 implements Callable<String> {
   public GlAlphaState field_0003;
   public TestRunner$1 field_0002;
   public ChannelFlushPromiseNotifier field_0000;
   public GuiFlatPresets$ListSlot field_0001;
   public ReflectorMethod field_0006;

   public Chunk$2(Chunk var1, BlockPos var2) {
      this.field_177456_b = var1;
      this.field_177457_a = var2;
      super();
   }

   public String call() {
      return CrashReportCategory.getCoordinateInfo(this.field_177457_a);
   }
}
