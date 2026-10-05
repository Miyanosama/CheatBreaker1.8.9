package net.optifine;

import java.util.Comparator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.optifine.shaders.ItemAliases;

public class ChunkPosComparator implements Comparator<ChunkCoordIntPair> {
   public int chunkPosZ;
   public double pitchNorm;
   public int chunkPosX;
   public ItemAliases field_0003;
   public double yawRad;

   public ChunkPosComparator(int var1, int var2, double var3, double var5) {
      this.chunkPosX = var1;
      this.chunkPosZ = var2;
      this.yawRad = var3;
      this.pitchNorm = 1.0 - MathHelper.clamp_double(Math.abs(var5) / (Math.PI / 2), 0.0, 1.0);
   }

   public int getDistSq(ChunkCoordIntPair var1) {
      int var2 = var1.chunkXPos - this.chunkPosX;
      int var3 = var1.chunkZPos - this.chunkPosZ;
      int var4 = var2 * var2 + var3 * var3;
      double var5 = MathHelper.atan2(var3, var2);
      double var7 = Math.abs(var5 - this.yawRad);
      if (var7 > Math.PI) {
         var7 = (Math.PI * 2) - var7;
      }

      return (int)(var4 * 1000.0 * this.pitchNorm * var7 * var7);
   }

   public int compare(ChunkCoordIntPair var1, ChunkCoordIntPair var2) {
      int var3 = this.getDistSq(var1);
      int var4 = this.getDistSq(var2);
      return var3 - var4;
   }
}
