package net.minecraft.world;

import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;

public class WorldProviderHell extends WorldProvider {
   @Override
   public Vec3 getFogColor(float var1, float var2) {
      return new Vec3(0.2F, 0.03F, 0.03F);
   }

   @Override
   public IChunkProvider createChunkGenerator() {
      return new ChunkProviderHell(this.b, this.b.P().isMapFeaturesEnabled(), this.b.J());
   }

   @Override
   public void generateLightBrightnessTable() {
      float var1 = 0.1F;

      for (int var2 = 0; var2 <= 15; var2++) {
         float var3 = 1.0F - var2 / 15.0F;
         this.lightBrightnessTable[var2] = (1.0F - var3) / (var3 * 3.0F + 1.0F) * (1.0F - var1) + var1;
      }
   }

   @Override
   public boolean canRespawnHere() {
      return false;
   }

   @Override
   public void registerWorldChunkManager() {
      this.c = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
      this.d = true;
      this.e = true;
      this.g = -1;
   }

   @Override
   public String getInternalNameSuffix() {
      return "_nether";
   }

   @Override
   public boolean doesXZShowFog(int var1, int var2) {
      return true;
   }

   @Override
   public WorldBorder getWorldBorder() {
      return new WorldBorder() {
         @Override
         public double getCenterX() {
            return super.getCenterX() / 8.0;
         }

         @Override
         public double getCenterZ() {
            return super.getCenterZ() / 8.0;
         }
      };
   }

   @Override
   public String getDimensionName() {
      return "Nether";
   }

   @Override
   public boolean canCoordinateBeSpawn(int var1, int var2) {
      return false;
   }

   @Override
   public float calculateCelestialAngle(long var1, float var3) {
      return 0.5F;
   }

   @Override
   public boolean isSurfaceWorld() {
      return false;
   }
}
