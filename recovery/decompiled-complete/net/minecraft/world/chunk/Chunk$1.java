package net.minecraft.world.chunk;

import io.netty.util.Recycler$Stack;
import java.util.concurrent.Callable;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.BlockPos;

public class Chunk$1 implements Callable<String> {
   public Recycler$Stack field_0003;
   public CompiledChunk field_0004;
   public EntityNoteFX field_0000;

   public Chunk$1(Chunk var1, int var2, int var3, int var4) {
      this.field_150821_d = var1;
      this.field_150824_a = var2;
      this.field_150822_b = var3;
      this.field_150823_c = var4;
      super();
   }

   public String call() {
      return CrashReportCategory.getCoordinateInfo(
         new BlockPos(this.field_150821_d.a * 16 + this.field_150824_a, this.field_150822_b, this.field_150821_d.b * 16 + this.field_150823_c)
      );
   }
}
