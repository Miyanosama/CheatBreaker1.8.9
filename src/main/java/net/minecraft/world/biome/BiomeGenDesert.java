package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeGenDesert extends BiomeGenBase {
   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      super.decorate(var1, var2, var3);
      if (var2.nextInt(1000) == 0) {
         int var4 = var2.nextInt(16) + 8;
         int var5 = var2.nextInt(16) + 8;
         BlockPos var6 = var1.getHeight(var3.add(var4, 0, var5)).up();
         new WorldGenDesertWells().generate(var1, var2, var6);
      }
   }

   public BiomeGenDesert(int var1) {
      super(var1);
      this.au.clear();
      this.ak = Blocks.sand.getDefaultState();
      this.al = Blocks.sand.getDefaultState();
      this.as.treesPerChunk = -999;
      this.as.deadBushPerChunk = 2;
      this.as.reedsPerChunk = 50;
      this.as.cactiPerChunk = 10;
      this.au.clear();
   }
}
