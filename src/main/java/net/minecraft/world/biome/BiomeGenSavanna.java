package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna extends BiomeGenBase {
   public static WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      ag.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

      for (int var4 = 0; var4 < 7; var4++) {
         int var5 = var2.nextInt(16) + 8;
         int var6 = var2.nextInt(16) + 8;
         int var7 = var2.nextInt(var1.getHeight(var3.add(var5, 0, var6)).getY() + 32);
         ag.generate(var1, var2, var3.add(var5, var7, var6));
      }

      super.decorate(var1, var2, var3);
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      BiomeGenSavanna.Mutated var2 = new BiomeGenSavanna.Mutated(var1, this);
      var2.ap = (this.ap + 1.0F) * 0.5F;
      var2.an = this.an * 0.5F + 0.3F;
      var2.ao = this.ao * 0.5F + 1.2F;
      return var2;
   }

   public BiomeGenSavanna(int var1) {
      super(var1);
      this.au.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
      this.as.treesPerChunk = 1;
      this.as.flowersPerChunk = 4;
      this.as.grassPerChunk = 20;
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(5) > 0 ? field_150627_aC : this.aA);
   }

   public static class Mutated extends BiomeGenMutated {
      @Override
      public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
         this.ak = Blocks.grass.getDefaultState();
         this.al = Blocks.dirt.getDefaultState();
         if (var6 > 1.75) {
            this.ak = Blocks.stone.getDefaultState();
            this.al = Blocks.stone.getDefaultState();
         } else if (var6 > -0.5) {
            this.ak = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
         }

         this.b(var1, var2, var3, var4, var5, var6);
      }

      public Mutated(int var1, BiomeGenBase var2) {
         super(var1, var2);
         this.as.treesPerChunk = 2;
         this.as.flowersPerChunk = 2;
         this.as.grassPerChunk = 5;
      }

      @Override
      public void decorate(World var1, Random var2, BlockPos var3) {
         this.as.decorate(var1, var2, this, var3);
      }
   }
}
