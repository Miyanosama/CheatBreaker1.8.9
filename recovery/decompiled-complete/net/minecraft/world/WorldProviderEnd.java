package net.minecraft.world;

import com.cheatbreaker.client.ui.fading.ColorFade;
import io.netty.bootstrap.Bootstrap;
import io.netty.handler.codec.http.HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1;
import io.netty.handler.codec.spdy.DefaultSpdyRstStreamFrame;
import io.netty.handler.ssl.SslContext$1;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$JunglePyramid;
import net.optifine.config.MatchBlock;
import net.optifine.util.MathUtilsTest;

public class WorldProviderEnd extends WorldProvider {
   public MatchBlock field_0004;
   public DefaultSpdyRstStreamFrame field_0002;
   public SslContext$1 field_0005;
   public MathUtilsTest field_0007;
   public Bootstrap field_0000;
   public HttpHeaderDateFormat$HttpHeaderDateFormatObsolete1 field_0001;
   public ComponentScatteredFeaturePieces$JunglePyramid field_0003;
   public ColorFade field_0006;

   @Override
   public float[] calcSunriseSunsetColors(float var1, float var2) {
      return null;
   }

   @Override
   public int getAverageGroundLevel() {
      return 50;
   }

   @Override
   public boolean canCoordinateBeSpawn(int var1, int var2) {
      return this.b.getGroundAboveSeaLevel(new BlockPos(var1, 0, var2)).getMaterial().blocksMovement();
   }

   @Override
   public boolean isSurfaceWorld() {
      return false;
   }

   @Override
   public Vec3 getFogColor(float var1, float var2) {
      int var3 = 10518688;
      float var4 = MathHelper.cos(var1 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      var4 = MathHelper.clamp_float(var4, 0.0F, 1.0F);
      float var5 = (var3 >> 16 & 0xFF) / 255.0F;
      float var6 = (var3 >> 8 & 0xFF) / 255.0F;
      float var7 = (var3 & 0xFF) / 255.0F;
      var5 *= var4 * 0.0F + 0.15F;
      var6 *= var4 * 0.0F + 0.15F;
      var7 *= var4 * 0.0F + 0.15F;
      return new Vec3(var5, var6, var7);
   }

   @Override
   public String getDimensionName() {
      return "The End";
   }

   @Override
   public boolean method_10680() {
      return false;
   }

   @Override
   public boolean doesXZShowFog(int var1, int var2) {
      return true;
   }

   @Override
   public String getInternalNameSuffix() {
      return "_end";
   }

   @Override
   public float getCloudHeight() {
      return 8.0F;
   }

   @Override
   public IChunkProvider createChunkGenerator() {
      return new ChunkProviderEnd(this.b, this.b.J());
   }

   @Override
   public boolean canRespawnHere() {
      return false;
   }

   @Override
   public void registerWorldChunkManager() {
      this.c = new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F);
      this.g = 1;
      this.e = true;
   }

   @Override
   public float calculateCelestialAngle(long var1, float var3) {
      return 0.0F;
   }

   @Override
   public BlockPos getSpawnCoordinate() {
      return new BlockPos(100, 50, 0);
   }
}
