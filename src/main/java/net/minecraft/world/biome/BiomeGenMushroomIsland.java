package net.minecraft.world.biome;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;

public class BiomeGenMushroomIsland extends BiomeGenBase {
   public BiomeGenMushroomIsland(int var1) {
      super(var1);
      this.as.treesPerChunk = -100;
      this.as.flowersPerChunk = -100;
      this.as.grassPerChunk = -100;
      this.as.mushroomsPerChunk = 1;
      this.as.bigMushroomsPerChunk = 1;
      this.ak = Blocks.mycelium.getDefaultState();
      this.at.clear();
      this.au.clear();
      this.av.clear();
      this.au.add(new BiomeGenBase.SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
   }
}
