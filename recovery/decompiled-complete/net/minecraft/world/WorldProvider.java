package net.minecraft.world;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderDebug;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.optifine.expr.TokenType;

public abstract class WorldProvider {
   public int g;
   public float[] colorsSunriseSunset;
   public TokenType field_0004;
   public WorldType terrainType;
   public World b;
   public boolean e;
   public String generatorSettings;
   public WorldChunkManager c;
   public static float[] moonPhaseFactors = new float[]{1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
   public WorldGenTallGrass field_0011;
   public float[] lightBrightnessTable = new float[16];
   public boolean d;

   public float[] calcSunriseSunsetColors(float var1, float var2) {
      float var3 = 0.4F;
      float var4 = MathHelper.cos(var1 * (float) Math.PI * 2.0F) - 0.0F;
      float var5 = -0.0F;
      if (var4 >= var5 - var3 && var4 <= var5 + var3) {
         float var6 = (var4 - var5) / var3 * 0.5F + 0.5F;
         float var7 = 1.0F - (1.0F - MathHelper.sin(var6 * (float) Math.PI)) * 0.99F;
         var7 *= var7;
         this.colorsSunriseSunset[0] = var6 * 0.3F + 0.7F;
         this.colorsSunriseSunset[1] = var6 * var6 * 0.7F + 0.2F;
         this.colorsSunriseSunset[2] = var6 * var6 * 0.0F + 0.2F;
         this.colorsSunriseSunset[3] = var7;
         return this.colorsSunriseSunset;
      } else {
         return null;
      }
   }

   public double getVoidFogYFactor() {
      return this.terrainType == WorldType.FLAT ? 1.0 : 0.03125;
   }

   public int getMoonPhase(long var1) {
      return (int)(
            var1 / (5321701526237110254L & -5321701527402717744L) % (-6249811407843013208L & 6249811406540505097L)
               + (5690219773175532744L & -5690219773725437668L)
         )
         % 8;
   }

   public IChunkProvider createChunkGenerator() {
      return (IChunkProvider)(this.terrainType == WorldType.FLAT
         ? new ChunkProviderFlat(this.b, this.b.J(), this.b.P().isMapFeaturesEnabled(), this.generatorSettings)
         : (
            this.terrainType == WorldType.DEBUG_WORLD
               ? new ChunkProviderDebug(this.b)
               : (
                  this.terrainType == WorldType.CUSTOMIZED
                     ? new ChunkProviderGenerate(this.b, this.b.J(), this.b.P().isMapFeaturesEnabled(), this.generatorSettings)
                     : new ChunkProviderGenerate(this.b, this.b.J(), this.b.P().isMapFeaturesEnabled(), this.generatorSettings)
               )
         ));
   }

   public int getAverageGroundLevel() {
      return this.terrainType == WorldType.FLAT ? 4 : this.b.F() + 1;
   }

   public int getDimensionId() {
      return this.g;
   }

   public abstract String getInternalNameSuffix();

   public boolean doesWaterVaporize() {
      return this.d;
   }

   public float[] getLightBrightnessTable() {
      return this.lightBrightnessTable;
   }

   public Vec3 getFogColor(float var1, float var2) {
      float var3 = MathHelper.cos(var1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
      float var4 = 0.7529412F;
      float var5 = 0.84705883F;
      float var6 = 1.0F;
      var4 *= var3 * 0.94F + 0.06F;
      var5 *= var3 * 0.94F + 0.06F;
      var6 *= var3 * 0.91F + 0.09F;
      return new Vec3(var4, var5, var6);
   }

   public WorldProvider() {
      this.colorsSunriseSunset = new float[4];
   }

   public abstract String getDimensionName();

   public boolean getHasNoSky() {
      return this.e;
   }

   public void registerWorld(World var1) {
      this.b = var1;
      this.terrainType = var1.P().getTerrainType();
      this.generatorSettings = var1.P().getGeneratorOptions();
      this.registerWorldChunkManager();
      this.generateLightBrightnessTable();
   }

   public void generateLightBrightnessTable() {
      float var1 = 0.0F;

      for (int var2 = 0; var2 <= 15; var2++) {
         float var3 = 1.0F - var2 / 15.0F;
         this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
      }
   }

   public boolean canCoordinateBeSpawn(int var1, int var2) {
      return this.b.getGroundAboveSeaLevel(new BlockPos(var1, 0, var2)) == Blocks.grass;
   }

   public boolean doesXZShowFog(int var1, int var2) {
      return false;
   }

   public boolean canRespawnHere() {
      return true;
   }

   public static WorldProvider getProviderForDimension(int var0) {
      return (WorldProvider)(var0 == -1 ? new WorldProviderHell() : (var0 == 0 ? new WorldProviderSurface() : (var0 == 1 ? new WorldProviderEnd() : null)));
   }

   public float calculateCelestialAngle(long var1, float var3) {
      int var4 = (int)(var1 % (24521L & -2000860740845249088L));
      float var5 = (var4 + var3) / 24000.0F - 0.25F;
      if (var5 < 0.0F) {
         var5++;
      }

      if (var5 > 1.0F) {
         var5--;
      }

      var5 = 1.0F - (float)((Math.cos(var5 * Math.PI) + 1.0) / 2.0);
      return var5 + (var5 - var5) / 3.0F;
   }

   public WorldBorder getWorldBorder() {
      return new WorldBorder();
   }

   public boolean method_10680() {
      return true;
   }

   public float getCloudHeight() {
      return 128.0F;
   }

   public WorldChunkManager getWorldChunkManager() {
      return this.c;
   }

   public void registerWorldChunkManager() {
      WorldType var1 = this.b.P().getTerrainType();
      if (var1 == WorldType.FLAT) {
         FlatGeneratorInfo var2 = FlatGeneratorInfo.createFlatGeneratorFromString(this.b.P().getGeneratorOptions());
         this.c = new WorldChunkManagerHell(BiomeGenBase.getBiomeFromBiomeList(var2.getBiome(), BiomeGenBase.field_180279_ad), 0.5F);
      } else if (var1 == WorldType.DEBUG_WORLD) {
         this.c = new WorldChunkManagerHell(BiomeGenBase.plains, 0.0F);
      } else {
         this.c = new WorldChunkManager(this.b);
      }
   }

   public BlockPos getSpawnCoordinate() {
      return null;
   }

   public boolean isSurfaceWorld() {
      return true;
   }
}
