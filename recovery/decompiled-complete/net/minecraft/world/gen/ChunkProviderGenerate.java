package net.minecraft.world.gen;

import java.util.List;
import java.util.Random;
import javax.vecmath.VecMathI18N;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.JsonSerializableSet;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import recovered.unidentified.UnidentifiedClass1617;

public class ChunkProviderGenerate implements IChunkProvider {
   public MapGenStronghold strongholdGenerator;
   public double[] field_0027;
   public NoiseGeneratorOctaves mobSpawnerNoise;
   public ItemStack field_0024;
   public Block oceanBlockTmpl = Blocks.water;
   public double[] field_0007;
   public double[] stoneNoise = new double[256];
   public NoiseGeneratorPerlin field_147430_m;
   public NoiseGeneratorOctaves field_147431_j;
   public double[] field_0030;
   public BiomeGenBase[] biomesForGeneration;
   public NoiseGeneratorOctaves noiseGen6;
   public MapGenScatteredFeature scatteredFeatureGenerator;
   public double[] field_147434_q;
   public float[] parabolicField;
   public WorldType field_177475_o;
   public MapGenVillage villageGenerator;
   public boolean mapFeaturesEnabled;
   public MapGenMineshaft mineshaftGenerator;
   public Random rand;
   public double[] field_0002;
   public NoiseGeneratorOctaves field_147429_l;
   public NoiseGeneratorOctaves noiseGen5;
   public VecMathI18N field_0000;
   public StructureOceanMonument oceanMonumentGenerator;
   public ChunkProviderSettings settings;
   public MapGenBase caveGenerator = new MapGenCaves();
   public JsonSerializableSet field_0012;
   public NoiseGeneratorOctaves field_147432_k;
   public World worldObj;
   public MapGenBase ravineGenerator;

   @Override
   public void recreateStructures(Chunk var1, int var2, int var3) {
      if (this.settings.useMineShafts && this.mapFeaturesEnabled) {
         this.mineshaftGenerator.generate(this, this.worldObj, var2, var3, (ChunkPrimer)null);
      }

      if (this.settings.useVillages && this.mapFeaturesEnabled) {
         this.villageGenerator.generate(this, this.worldObj, var2, var3, (ChunkPrimer)null);
      }

      if (this.settings.useStrongholds && this.mapFeaturesEnabled) {
         this.strongholdGenerator.generate(this, this.worldObj, var2, var3, (ChunkPrimer)null);
      }

      if (this.settings.useTemples && this.mapFeaturesEnabled) {
         this.scatteredFeatureGenerator.generate(this, this.worldObj, var2, var3, (ChunkPrimer)null);
      }

      if (this.settings.useMonuments && this.mapFeaturesEnabled) {
         this.oceanMonumentGenerator.generate(this, this.worldObj, var2, var3, (ChunkPrimer)null);
      }
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public List<BiomeGenBase$SpawnListEntry> getPossibleCreatures(EnumCreatureType var1, BlockPos var2) {
      BiomeGenBase var3 = this.worldObj.getBiomeGenForCoords(var2);
      if (this.mapFeaturesEnabled) {
         if (var1 == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.func_175798_a(var2)) {
            return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
         }

         if (var1 == EnumCreatureType.MONSTER && this.settings.useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, var2)) {
            return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
         }
      }

      return var3.getSpawnableList(var1);
   }

   @Override
   public boolean chunkExists(int var1, int var2) {
      return true;
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   @Override
   public boolean saveChunks(boolean var1, IProgressUpdate var2) {
      return true;
   }

   @Override
   public String makeString() {
      return "RandomLevelSource";
   }

   public void setBlocksInChunk(int var1, int var2, ChunkPrimer var3) {
      this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, var1 * 4 - 2, var2 * 4 - 2, 10, 10);
      this.func_147423_a(var1 * 4, 0, var2 * 4);

      for (int var4 = 0; var4 < 4; var4++) {
         int var5 = var4 * 5;
         int var6 = (var4 + 1) * 5;

         for (int var7 = 0; var7 < 4; var7++) {
            int var8 = (var5 + var7) * 33;
            int var9 = (var5 + var7 + 1) * 33;
            int var10 = (var6 + var7) * 33;
            int var11 = (var6 + var7 + 1) * 33;

            for (int var12 = 0; var12 < 32; var12++) {
               double var13 = 0.125;
               double var15 = this.field_147434_q[var8 + var12];
               double var17 = this.field_147434_q[var9 + var12];
               double var19 = this.field_147434_q[var10 + var12];
               double var21 = this.field_147434_q[var11 + var12];
               double var23 = (this.field_147434_q[var8 + var12 + 1] - var15) * var13;
               double var25 = (this.field_147434_q[var9 + var12 + 1] - var17) * var13;
               double var27 = (this.field_147434_q[var10 + var12 + 1] - var19) * var13;
               double var29 = (this.field_147434_q[var11 + var12 + 1] - var21) * var13;

               for (int var31 = 0; var31 < 8; var31++) {
                  double var32 = 0.25;
                  double var34 = var15;
                  double var36 = var17;
                  double var38 = (var19 - var15) * var32;
                  double var40 = (var21 - var17) * var32;

                  for (int var42 = 0; var42 < 4; var42++) {
                     double var43 = 0.25;
                     double var45 = (var36 - var34) * var43;
                     double var47 = var34 - var45;

                     for (int var49 = 0; var49 < 4; var49++) {
                        if ((var47 += var45) > 0.0) {
                           var3.setBlockState(var4 * 4 + var42, var12 * 8 + var31, var7 * 4 + var49, Blocks.stone.getDefaultState());
                        } else if (var12 * 8 + var31 < this.settings.seaLevel) {
                           var3.setBlockState(var4 * 4 + var42, var12 * 8 + var31, var7 * 4 + var49, this.oceanBlockTmpl.getDefaultState());
                        }
                     }

                     var34 += var38;
                     var36 += var40;
                  }

                  var15 += var23;
                  var17 += var25;
                  var19 += var27;
                  var21 += var29;
               }
            }
         }
      }
   }

   @Override
   public boolean populateChunk(IChunkProvider var1, Chunk var2, int var3, int var4) {
      boolean var5 = false;
      if (this.settings.useMonuments && this.mapFeaturesEnabled && var2.getInhabitedTime() < (-1895788791117328688L & 538193685L)) {
         var5 |= this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, new ChunkCoordIntPair(var3, var4));
      }

      return var5;
   }

   @Override
   public void saveExtraData() {
   }

   @Override
   public BlockPos getStrongholdGen(World var1, String var2, BlockPos var3) {
      return "Stronghold".equals(var2) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(var1, var3) : null;
   }

   @Override
   public Chunk provideChunk(BlockPos var1) {
      return this.provideChunk(var1.getX() >> 4, var1.getZ() >> 4);
   }

   @Override
   public Chunk provideChunk(int var1, int var2) {
      this.rand.setSeed(var1 * (341915333993L & 8276740765224926492L) + var2 * (-1693243508831880235L & 1693243641629204437L));
      ChunkPrimer var3 = new ChunkPrimer();
      this.setBlocksInChunk(var1, var2, var3);
      this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, var1 * 16, var2 * 16, 16, 16);
      this.replaceBlocksForBiome(var1, var2, var3, this.biomesForGeneration);
      if (this.settings.useCaves) {
         this.caveGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      if (this.settings.useRavines) {
         this.ravineGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      if (this.settings.useMineShafts && this.mapFeaturesEnabled) {
         this.mineshaftGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      if (this.settings.useVillages && this.mapFeaturesEnabled) {
         this.villageGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      if (this.settings.useStrongholds && this.mapFeaturesEnabled) {
         this.strongholdGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      if (this.settings.useTemples && this.mapFeaturesEnabled) {
         this.scatteredFeatureGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      if (this.settings.useMonuments && this.mapFeaturesEnabled) {
         this.oceanMonumentGenerator.generate(this, this.worldObj, var1, var2, var3);
      }

      Chunk var4 = new Chunk(this.worldObj, var3, var1, var2);
      byte[] var5 = var4.getBiomeArray();

      for (int var6 = 0; var6 < var5.length; var6++) {
         var5[var6] = (byte)this.biomesForGeneration[var6].az;
      }

      var4.generateSkylightMap();
      return var4;
   }

   public ChunkProviderGenerate(World var1, long var2, boolean var4, String var5) {
      this.strongholdGenerator = new MapGenStronghold();
      this.villageGenerator = new MapGenVillage();
      this.mineshaftGenerator = new MapGenMineshaft();
      this.scatteredFeatureGenerator = new MapGenScatteredFeature();
      this.ravineGenerator = new MapGenRavine();
      this.oceanMonumentGenerator = new StructureOceanMonument();
      this.worldObj = var1;
      this.mapFeaturesEnabled = var4;
      this.field_177475_o = var1.P().getTerrainType();
      this.rand = new Random(var2);
      this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
      this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
      this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
      this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
      this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
      this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
      this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
      this.field_147434_q = new double[825];
      this.parabolicField = new float[25];

      for (int var6 = -2; var6 <= 2; var6++) {
         for (int var7 = -2; var7 <= 2; var7++) {
            float var8 = 10.0F / MathHelper.sqrt_float(var6 * var6 + var7 * var7 + 0.2F);
            this.parabolicField[var6 + 2 + (var7 + 2) * 5] = var8;
         }
      }

      if (var5 != null) {
         this.settings = ChunkProviderSettings$Factory.jsonToFactory(var5).func_177864_b();
         this.oceanBlockTmpl = this.settings.useLavaOceans ? Blocks.lava : Blocks.water;
         var1.setSeaLevel(this.settings.seaLevel);
      }
   }

   @Override
   public boolean canSave() {
      return true;
   }

   public void replaceBlocksForBiome(int var1, int var2, ChunkPrimer var3, BiomeGenBase[] var4) {
      double var5 = 0.03125;
      this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, var1 * 16, var2 * 16, 16, 16, var5 * 2.0, var5 * 2.0, 1.0);

      for (int var7 = 0; var7 < 16; var7++) {
         for (int var8 = 0; var8 < 16; var8++) {
            BiomeGenBase var9 = var4[var8 + var7 * 16];
            var9.genTerrainBlocks(this.worldObj, this.rand, var3, var1 * 16 + var7, var2 * 16 + var8, this.stoneNoise[var8 + var7 * 16]);
         }
      }
   }

   public void func_147423_a(int var1, int var2, int var3) {
      this.field_0002 = this.noiseGen6
         .generateNoiseOctaves(
            this.field_0002, var1, var3, 5, 5, this.settings.depthNoiseScaleX, this.settings.depthNoiseScaleZ, this.settings.depthNoiseScaleExponent
         );
      float var4 = this.settings.coordinateScale;
      float var5 = this.settings.heightScale;
      this.field_0030 = this.field_147429_l
         .generateNoiseOctaves(
            this.field_0030,
            var1,
            var2,
            var3,
            5,
            33,
            5,
            var4 / this.settings.mainNoiseScaleX,
            var5 / this.settings.mainNoiseScaleY,
            var4 / this.settings.mainNoiseScaleZ
         );
      this.field_0027 = this.field_147431_j.generateNoiseOctaves(this.field_0027, var1, var2, var3, 5, 33, 5, var4, var5, var4);
      this.field_0007 = this.field_147432_k.generateNoiseOctaves(this.field_0007, var1, var2, var3, 5, 33, 5, var4, var5, var4);
      boolean var37 = false;
      boolean var36 = false;
      int var6 = 0;
      int var7 = 0;

      for (int var8 = 0; var8 < 5; var8++) {
         for (int var9 = 0; var9 < 5; var9++) {
            float var10 = 0.0F;
            float var11 = 0.0F;
            float var12 = 0.0F;
            byte var13 = 2;
            BiomeGenBase var14 = this.biomesForGeneration[var8 + 2 + (var9 + 2) * 10];

            for (int var15 = -var13; var15 <= var13; var15++) {
               for (int var16 = -var13; var16 <= var13; var16++) {
                  BiomeGenBase var17 = this.biomesForGeneration[var8 + var15 + 2 + (var9 + var16 + 2) * 10];
                  float var18 = this.settings.biomeDepthOffSet + var17.an * this.settings.biomeDepthWeight;
                  float var19 = this.settings.biomeScaleOffset + var17.ao * this.settings.biomeScaleWeight;
                  if (this.field_177475_o == WorldType.AMPLIFIED && var18 > 0.0F) {
                     var18 = 1.0F + var18 * 2.0F;
                     var19 = 1.0F + var19 * 4.0F;
                  }

                  float var20 = this.parabolicField[var15 + 2 + (var16 + 2) * 5] / (var18 + 2.0F);
                  if (var17.an > var14.an) {
                     var20 /= 2.0F;
                  }

                  var10 += var19 * var20;
                  var11 += var18 * var20;
                  var12 += var20;
               }
            }

            var10 /= var12;
            var11 /= var12;
            var10 = var10 * 0.9F + 0.1F;
            var11 = (var11 * 4.0F - 1.0F) / 8.0F;
            double var42 = this.field_0002[var7] / 8000.0;
            if (var42 < 0.0) {
               var42 = -var42 * 0.3;
            }

            var42 = var42 * 3.0 - 2.0;
            if (var42 < 0.0) {
               var42 /= 2.0;
               if (var42 < -1.0) {
                  var42 = -1.0;
               }

               var42 /= 1.4;
               var42 /= 2.0;
            } else {
               if (var42 > 1.0) {
                  var42 = 1.0;
               }

               var42 /= 8.0;
            }

            var7++;
            double var47 = var11;
            double var50 = var10;
            var47 += var42 * 0.2;
            var47 = var47 * this.settings.baseSize / 8.0;
            double var21 = this.settings.baseSize + var47 * 4.0;

            for (int var23 = 0; var23 < 33; var23++) {
               double var24 = (var23 - var21) * this.settings.stretchY * 128.0 / 256.0 / var50;
               if (var24 < 0.0) {
                  var24 *= 4.0;
               }

               double var26 = this.field_0027[var6] / this.settings.lowerLimitScale;
               double var28 = this.field_0007[var6] / this.settings.upperLimitScale;
               double var30 = (this.field_0030[var6] / 10.0 + 1.0) / 2.0;
               double var32 = MathHelper.denormalizeClamp(var26, var28, var30) - var24;
               if (var23 > 29) {
                  double var34 = (var23 - 29) / 3.0F;
                  var32 = var32 * (1.0 - var34) + -10.0 * var34;
               }

               this.field_147434_q[var6] = var32;
               var6++;
            }
         }
      }
   }

   @Override
   public void populate(IChunkProvider var1, int var2, int var3) {
      BlockFalling.fallInstantly = true;
      int var4 = var2 * 16;
      int var5 = var3 * 16;
      BlockPos var6 = new BlockPos(var4, 0, var5);
      BiomeGenBase var7 = this.worldObj.getBiomeGenForCoords(var6.add(16, 0, 16));
      this.rand.setSeed(this.worldObj.J());
      long var8 = this.rand.nextLong() / (268447810L & 189662214L) * (-4857192672600775129L & 251400458L) + (1589771L & 360841477L);
      long var10 = this.rand.nextLong() / (-5803358192211025918L & 5803358191656961162L) * (1161824274L & -5593250765878479318L)
         + (1117946989L & 4000091525732909203L);
      this.rand.setSeed(var2 * var8 + var3 * var10 ^ this.worldObj.J());
      boolean var12 = false;
      ChunkCoordIntPair var13 = new ChunkCoordIntPair(var2, var3);
      if (this.settings.useMineShafts && this.mapFeaturesEnabled) {
         this.mineshaftGenerator.generateStructure(this.worldObj, this.rand, var13);
      }

      if (this.settings.useVillages && this.mapFeaturesEnabled) {
         var12 = this.villageGenerator.generateStructure(this.worldObj, this.rand, var13);
      }

      if (this.settings.useStrongholds && this.mapFeaturesEnabled) {
         this.strongholdGenerator.generateStructure(this.worldObj, this.rand, var13);
      }

      if (this.settings.useTemples && this.mapFeaturesEnabled) {
         this.scatteredFeatureGenerator.generateStructure(this.worldObj, this.rand, var13);
      }

      if (this.settings.useMonuments && this.mapFeaturesEnabled) {
         this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, var13);
      }

      if (var7 != BiomeGenBase.desert
         && var7 != BiomeGenBase.desertHills
         && this.settings.useWaterLakes
         && !var12
         && this.rand.nextInt(this.settings.waterLakeChance) == 0) {
         int var14 = this.rand.nextInt(16) + 8;
         int var15 = this.rand.nextInt(256);
         int var16 = this.rand.nextInt(16) + 8;
         new UnidentifiedClass1617(Blocks.water).generate(this.worldObj, this.rand, var6.add(var14, var15, var16));
      }

      if (!var12 && this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes) {
         int var19 = this.rand.nextInt(16) + 8;
         int var22 = this.rand.nextInt(this.rand.nextInt(248) + 8);
         int var25 = this.rand.nextInt(16) + 8;
         if (var22 < this.worldObj.F() || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0) {
            new UnidentifiedClass1617(Blocks.lava).generate(this.worldObj, this.rand, var6.add(var19, var22, var25));
         }
      }

      if (this.settings.useDungeons) {
         for (int var20 = 0; var20 < this.settings.dungeonChance; var20++) {
            int var23 = this.rand.nextInt(16) + 8;
            int var26 = this.rand.nextInt(256);
            int var17 = this.rand.nextInt(16) + 8;
            new WorldGenDungeons().generate(this.worldObj, this.rand, var6.add(var23, var26, var17));
         }
      }

      var7.decorate(this.worldObj, this.rand, new BlockPos(var4, 0, var5));
      SpawnerAnimals.performWorldGenSpawning(this.worldObj, var7, var4 + 8, var5 + 8, 16, 16, this.rand);
      var6 = var6.add(8, 0, 8);

      for (int var21 = 0; var21 < 16; var21++) {
         for (int var24 = 0; var24 < 16; var24++) {
            BlockPos var27 = this.worldObj.getPrecipitationHeight(var6.add(var21, 0, var24));
            BlockPos var28 = var27.down();
            if (this.worldObj.canBlockFreezeWater(var28)) {
               this.worldObj.a(var28, Blocks.ice.getDefaultState(), 2);
            }

            if (this.worldObj.canSnowAt(var27, true)) {
               this.worldObj.a(var27, Blocks.snow_layer.getDefaultState(), 2);
            }
         }
      }

      BlockFalling.fallInstantly = false;
   }
}
