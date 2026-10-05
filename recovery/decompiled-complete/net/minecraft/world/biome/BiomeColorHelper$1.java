package net.minecraft.world.biome;

import net.minecraft.command.EntityNotFoundException;
import net.minecraft.util.BlockPos;

public class BiomeColorHelper$1 implements BiomeColorHelper$ColorResolver {
   public EntityNotFoundException field_0000;

   @Override
   public int getColorAtPos(BiomeGenBase var1, BlockPos var2) {
      return var1.getGrassColorAtPos(var2);
   }
}
