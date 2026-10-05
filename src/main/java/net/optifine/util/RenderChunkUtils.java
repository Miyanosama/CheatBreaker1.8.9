package net.optifine.util;

import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.MathHelper;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class RenderChunkUtils {
   public static int getCountBlocks(RenderChunk var0) {
      ExtendedBlockStorage[] var1 = var0.getChunk().getBlockStorageArray();
      if (var1 == null) {
         return 0;
      } else {
         int var2 = var0.getPosition().getY() >> 4;
         ExtendedBlockStorage var3 = var1[var2];
         return var3 == null ? 0 : var3.getBlockRefCount();
      }
   }

   public static double getRelativeBufferSize(int var0) {
      double var1 = var0 / 4096.0;
      var1 *= 0.995;
      double var3 = var1 * 2.0 - 1.0;
      var3 = MathHelper.clamp_double(var3, -1.0, 1.0);
      return MathHelper.sqrt_double(1.0 - var3 * var3);
   }

   public static double getRelativeBufferSize(RenderChunk var0) {
      int var1 = getCountBlocks(var0);
      return getRelativeBufferSize(var1);
   }
}
