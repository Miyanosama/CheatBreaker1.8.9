package net.minecraft.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenBeach extends BiomeGenBase {
   public BiomeGenBeach(int var1) {
      super(var1);
      this.au.clear();
      this.ak = Blocks.sand.getDefaultState();
      this.al = Blocks.sand.getDefaultState();
      this.as.treesPerChunk = -999;
      this.as.deadBushPerChunk = 0;
      this.as.reedsPerChunk = 0;
      this.as.cactiPerChunk = 0;
   }
}
