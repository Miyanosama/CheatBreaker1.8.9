package net.minecraft.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenStoneBeach extends BiomeGenBase {
   public BiomeGenStoneBeach(int var1) {
      super(var1);
      this.au.clear();
      this.ak = Blocks.stone.getDefaultState();
      this.al = Blocks.stone.getDefaultState();
      this.as.treesPerChunk = -999;
      this.as.deadBushPerChunk = 0;
      this.as.reedsPerChunk = 0;
      this.as.cactiPerChunk = 0;
   }
}
