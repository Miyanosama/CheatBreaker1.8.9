package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderEnd implements IChunkProvider {
   public NoiseGeneratorOctaves noiseGen5;
   public NoiseGeneratorOctaves noiseGen4;
   public double[] noiseData2;
   public NoiseGeneratorOctaves noiseGen1;
   public BiomeGenBase[] biomesForGeneration;
   public double[] noiseData4;
   public double[] densities;
   public World endWorld;
   public NoiseGeneratorOctaves noiseGen2;
   public NoiseGeneratorOctaves noiseGen3;
   public double[] noiseData3;
   public double[] noiseData1;
   public Random endRNG;
   public double[] noiseData5;

   public void func_180519_a(ChunkPrimer var1) {
      for (int var2 = 0; var2 < 16; var2++) {
         for (int var3 = 0; var3 < 16; var3++) {
            int var4 = 1;
            int var5 = -1;
            IBlockState var6 = Blocks.end_stone.getDefaultState();
            IBlockState var7 = Blocks.end_stone.getDefaultState();

            for (int var8 = 127; var8 >= 0; var8--) {
               IBlockState var9 = var1.getBlockState(var2, var8, var3);
               if (var9.getBlock().getMaterial() == Material.air) {
                  var5 = -1;
               } else if (var9.getBlock() == Blocks.stone) {
                  if (var5 == -1) {
                     if (var4 <= 0) {
                        var6 = Blocks.air.getDefaultState();
                        var7 = Blocks.end_stone.getDefaultState();
                     }

                     var5 = var4;
                     if (var8 >= 0) {
                        var1.setBlockState(var2, var8, var3, var6);
                     } else {
                        var1.setBlockState(var2, var8, var3, var7);
                     }
                  } else if (var5 > 0) {
                     var5--;
                     var1.setBlockState(var2, var8, var3, var7);
                  }
               }
            }
         }
      }
   }

   @Override
   public boolean chunkExists(int var1, int var2) {
      return true;
   }

   @Override
   public boolean saveChunks(boolean var1, IProgressUpdate var2) {
      return true;
   }

   @Override
   public Chunk provideChunk(int var1, int var2) {
      this.endRNG.setSeed(var1 * 341873128712L + var2 * 132897987541L);
      ChunkPrimer var3 = new ChunkPrimer();
      this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, var1 * 16, var2 * 16, 16, 16);
      this.func_180520_a(var1, var2, var3);
      this.func_180519_a(var3);
      Chunk var4 = new Chunk(this.endWorld, var3, var1, var2);
      byte[] var5 = var4.getBiomeArray();

      for (int var6 = 0; var6 < var5.length; var6++) {
         var5[var6] = (byte)this.biomesForGeneration[var6].az;
      }

      var4.generateSkylightMap();
      return var4;
   }

   @Override
   public void populate(IChunkProvider var1, int var2, int var3) {
      BlockFalling.fallInstantly = true;
      BlockPos var4 = new BlockPos(var2 * 16, 0, var3 * 16);
      this.endWorld.getBiomeGenForCoords(var4.add(16, 0, 16)).decorate(this.endWorld, this.endWorld.s, var4);
      BlockFalling.fallInstantly = false;
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   @Override
   public BlockPos getStrongholdGen(World var1, String var2, BlockPos var3) {
      return null;
   }

   public double[] initializeNoiseField(double[] var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      if (var1 == null) {
         var1 = new double[var5 * var6 * var7];
      }

      double var8 = 684.412;
      double var10 = 684.412;
      this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, var2, var4, var5, var7, 1.121, 1.121, 0.5);
      this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, var2, var4, var5, var7, 200.0, 200.0, 0.5);
      var8 *= 2.0;
      this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, var2, var3, var4, var5, var6, var7, var8 / 80.0, var10 / 160.0, var8 / 80.0);
      this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, var2, var3, var4, var5, var6, var7, var8, var10, var8);
      this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, var2, var3, var4, var5, var6, var7, var8, var10, var8);
      int var12 = 0;

      for (int var13 = 0; var13 < var5; var13++) {
         for (int var14 = 0; var14 < var7; var14++) {
            float var15 = (var13 + var2) / 1.0F;
            float var16 = (var14 + var4) / 1.0F;
            float var17 = 100.0F - MathHelper.sqrt_float(var15 * var15 + var16 * var16) * 8.0F;
            if (var17 > 80.0F) {
               var17 = 80.0F;
            }

            if (var17 < -100.0F) {
               var17 = -100.0F;
            }

            for (int var18 = 0; var18 < var6; var18++) {
               double var19 = 0.0;
               double var21 = this.noiseData2[var12] / 512.0;
               double var23 = this.noiseData3[var12] / 512.0;
               double var25 = (this.noiseData1[var12] / 10.0 + 1.0) / 2.0;
               if (var25 < 0.0) {
                  var19 = var21;
               } else if (var25 > 1.0) {
                  var19 = var23;
               } else {
                  var19 = var21 + (var23 - var21) * var25;
               }

               var19 -= 8.0;
               var19 += var17;
               byte var27 = 2;
               if (var18 > var6 / 2 - var27) {
                  double var28 = (var18 - (var6 / 2 - var27)) / 64.0F;
                  var28 = MathHelper.clamp_double(var28, 0.0, 1.0);
                  var19 = var19 * (1.0 - var28) + -3000.0 * var28;
               }

               var27 = 8;
               if (var18 < var27) {
                  double var36 = (var27 - var18) / (var27 - 1.0F);
                  var19 = var19 * (1.0 - var36) + -30.0 * var36;
               }

               var1[var12] = var19;
               var12++;
            }
         }
      }

      return var1;
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public boolean populateChunk(IChunkProvider var1, Chunk var2, int var3, int var4) {
      return false;
   }

   @Override
   public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType var1, BlockPos var2) {
      return this.endWorld.getBiomeGenForCoords(var2).getSpawnableList(var1);
   }

   @Override
   public Chunk provideChunk(BlockPos var1) {
      return this.provideChunk(var1.getX() >> 4, var1.getZ() >> 4);
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public String makeString() {
      return "RandomLevelSource";
   }

   public void func_180520_a(int var1, int var2, ChunkPrimer var3) {
      byte var4 = 2;
      int var5 = var4 + 1;
      byte var6 = 33;
      int var7 = var4 + 1;
      this.densities = this.initializeNoiseField(this.densities, var1 * var4, 0, var2 * var4, var5, var6, var7);

      for (int var8 = 0; var8 < var4; var8++) {
         for (int var9 = 0; var9 < var4; var9++) {
            for (int var10 = 0; var10 < 32; var10++) {
               double var11 = 0.25;
               double var13 = this.densities[((var8 + 0) * var7 + var9 + 0) * var6 + var10 + 0];
               double var15 = this.densities[((var8 + 0) * var7 + var9 + 1) * var6 + var10 + 0];
               double var17 = this.densities[((var8 + 1) * var7 + var9 + 0) * var6 + var10 + 0];
               double var19 = this.densities[((var8 + 1) * var7 + var9 + 1) * var6 + var10 + 0];
               double var21 = (this.densities[((var8 + 0) * var7 + var9 + 0) * var6 + var10 + 1] - var13) * var11;
               double var23 = (this.densities[((var8 + 0) * var7 + var9 + 1) * var6 + var10 + 1] - var15) * var11;
               double var25 = (this.densities[((var8 + 1) * var7 + var9 + 0) * var6 + var10 + 1] - var17) * var11;
               double var27 = (this.densities[((var8 + 1) * var7 + var9 + 1) * var6 + var10 + 1] - var19) * var11;

               for (int var29 = 0; var29 < 4; var29++) {
                  double var30 = 0.125;
                  double var32 = var13;
                  double var34 = var15;
                  double var36 = (var17 - var13) * var30;
                  double var38 = (var19 - var15) * var30;

                  for (int var40 = 0; var40 < 8; var40++) {
                     double var41 = 0.125;
                     double var43 = var32;
                     double var45 = (var34 - var32) * var41;

                     for (int var47 = 0; var47 < 8; var47++) {
                        IBlockState var48 = null;
                        if (var43 > 0.0) {
                           var48 = Blocks.end_stone.getDefaultState();
                        }

                        int var49 = var40 + var8 * 8;
                        int var50 = var29 + var10 * 4;
                        int var51 = var47 + var9 * 8;
                        var3.setBlockState(var49, var50, var51, var48);
                        var43 += var45;
                     }

                     var32 += var36;
                     var34 += var38;
                  }

                  var13 += var21;
                  var15 += var23;
                  var17 += var25;
                  var19 += var27;
               }
            }
         }
      }
   }

   @Override
   public void recreateStructures(Chunk var1, int var2, int var3) {
   }

   public ChunkProviderEnd(World var1, long var2) {
      this.endWorld = var1;
      this.endRNG = new Random(var2);
      this.noiseGen1 = new NoiseGeneratorOctaves(this.endRNG, 16);
      this.noiseGen2 = new NoiseGeneratorOctaves(this.endRNG, 16);
      this.noiseGen3 = new NoiseGeneratorOctaves(this.endRNG, 8);
      this.noiseGen4 = new NoiseGeneratorOctaves(this.endRNG, 10);
      this.noiseGen5 = new NoiseGeneratorOctaves(this.endRNG, 16);
   }

   @Override
   public void saveExtraData() {
   }
}
