package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenSwamp extends BiomeGenBase {
   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return this.aC;
   }

   public BiomeGenSwamp(int var1) {
      super(var1);
      this.as.treesPerChunk = 2;
      this.as.flowersPerChunk = 1;
      this.as.deadBushPerChunk = 1;
      this.as.mushroomsPerChunk = 8;
      this.as.reedsPerChunk = 10;
      this.as.clayPerChunk = 1;
      this.as.waterlilyPerChunk = 4;
      this.as.sandPerChunk2 = 0;
      this.as.sandPerChunk = 0;
      this.as.grassPerChunk = 5;
      this.ar = 14745518;
      this.at.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
   }

   @Override
   public int getGrassColorAtPos(BlockPos var1) {
      double var2 = af.func_151601_a(var1.getX() * 0.0225, var1.getZ() * 0.0225);
      return var2 < -0.1 ? 5011004 : 6975545;
   }

   @Override
   public int getFoliageColorAtPos(BlockPos var1) {
      return 6975545;
   }

   @Override
   public BlockFlower.EnumFlowerType pickRandomFlower(Random var1, BlockPos var2) {
      return BlockFlower.EnumFlowerType.BLUE_ORCHID;
   }

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      double var8 = af.func_151601_a(var4 * 0.25, var5 * 0.25);
      if (var8 > 0.0) {
         int var10 = var4 & 15;
         int var11 = var5 & 15;

         for (int var12 = 255; var12 >= 0; var12--) {
            if (var3.getBlockState(var11, var12, var10).getBlock().getMaterial() != Material.air) {
               if (var12 == 62 && var3.getBlockState(var11, var12, var10).getBlock() != Blocks.water) {
                  var3.setBlockState(var11, var12, var10, Blocks.water.getDefaultState());
                  if (var8 < 0.12) {
                     var3.setBlockState(var11, var12 + 1, var10, Blocks.waterlily.getDefaultState());
                  }
               }
               break;
            }
         }
      }

      this.b(var1, var2, var3, var4, var5, var6);
   }
}
