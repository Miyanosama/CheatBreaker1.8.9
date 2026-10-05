package net.optifine;

import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;

public interface IRandomEntity {
   int getId();

   String getName();

   int getHealth();

   int getMaxHealth();

   BiomeGenBase getSpawnBiome();

   BlockPos getSpawnPosition();
}
