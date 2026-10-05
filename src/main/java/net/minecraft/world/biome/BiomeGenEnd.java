package net.minecraft.world.biome;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;

public class BiomeGenEnd extends BiomeGenBase {
   @Override
   public int getSkyColorByTemp(float var1) {
      return 0;
   }

   public BiomeGenEnd(int var1) {
      super(var1);
      this.at.clear();
      this.au.clear();
      this.av.clear();
      this.aw.clear();
      this.at.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 4, 4));
      this.ak = Blocks.dirt.getDefaultState();
      this.al = Blocks.dirt.getDefaultState();
      this.as = new BiomeEndDecorator();
   }
}
