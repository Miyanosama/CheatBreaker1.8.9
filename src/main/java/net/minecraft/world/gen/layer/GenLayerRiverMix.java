package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRiverMix extends GenLayer {
   public GenLayer riverPatternGeneratorChain;
   public GenLayer biomePatternGeneratorChain;

   @Override
   public void initWorldGenSeed(long var1) {
      this.biomePatternGeneratorChain.initWorldGenSeed(var1);
      this.riverPatternGeneratorChain.initWorldGenSeed(var1);
      super.initWorldGenSeed(var1);
   }

   public GenLayerRiverMix(long var1, GenLayer var3, GenLayer var4) {
      super(var1);
      this.biomePatternGeneratorChain = var3;
      this.riverPatternGeneratorChain = var4;
   }

   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      int[] var5 = this.biomePatternGeneratorChain.getInts(var1, var2, var3, var4);
      int[] var6 = this.riverPatternGeneratorChain.getInts(var1, var2, var3, var4);
      int[] var7 = IntCache.getIntCache(var3 * var4);

      for (int var8 = 0; var8 < var3 * var4; var8++) {
         if (var5[var8] == BiomeGenBase.ocean.az || var5[var8] == BiomeGenBase.deepOcean.az) {
            var7[var8] = var5[var8];
         } else if (var6[var8] == BiomeGenBase.river.az) {
            if (var5[var8] == BiomeGenBase.icePlains.az) {
               var7[var8] = BiomeGenBase.frozenRiver.az;
            } else if (var5[var8] != BiomeGenBase.mushroomIsland.az && var5[var8] != BiomeGenBase.recoveredField745.az) {
               var7[var8] = var6[var8] & 0xFF;
            } else {
               var7[var8] = BiomeGenBase.recoveredField745.az;
            }
         } else {
            var7[var8] = var5[var8];
         }
      }

      return var7;
   }
}
