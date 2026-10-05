package net.minecraft.world.biome;

import io.netty.handler.codec.socks.SocksAuthRequestDecoder$1;
import io.netty.handler.codec.socks.UnknownSocksResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.client.gui.GuiPlayerTabOverlay$1;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$14;
import org.apache.log4j.pattern.NameAbbreviator$PatternAbbreviator;

public class WorldChunkManagerHell extends WorldChunkManager {
   public float rainfall;
   public LogBrokerMonitor$14 field_0004;
   public BiomeGenBase biomeGenerator;
   public ChunkCoordIntPair field_0003;
   public S48PacketResourcePackSend field_0007;
   public GuiPlayerTabOverlay$1 field_0008;
   public SocksAuthRequestDecoder$1 field_0000;
   public NameAbbreviator$PatternAbbreviator field_0002;
   public UnknownSocksResponse field_0005;

   @Override
   public float[] getRainfall(float[] var1, int var2, int var3, int var4, int var5) {
      if (var1 == null || var1.length < var4 * var5) {
         var1 = new float[var4 * var5];
      }

      Arrays.fill(var1, 0, var4 * var5, this.rainfall);
      return var1;
   }

   @Override
   public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] var1, int var2, int var3, int var4, int var5) {
      if (var1 == null || var1.length < var4 * var5) {
         var1 = new BiomeGenBase[var4 * var5];
      }

      Arrays.fill(var1, 0, var4 * var5, this.biomeGenerator);
      return var1;
   }

   public WorldChunkManagerHell(BiomeGenBase var1, float var2) {
      this.biomeGenerator = var1;
      this.rainfall = var2;
   }

   @Override
   public boolean areBiomesViable(int var1, int var2, int var3, List<BiomeGenBase> var4) {
      return var4.contains(this.biomeGenerator);
   }

   @Override
   public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] var1, int var2, int var3, int var4, int var5, boolean var6) {
      return this.loadBlockGeneratorData(var1, var2, var3, var4, var5);
   }

   @Override
   public BiomeGenBase getBiomeGenerator(BlockPos var1) {
      return this.biomeGenerator;
   }

   @Override
   public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] var1, int var2, int var3, int var4, int var5) {
      if (var1 == null || var1.length < var4 * var5) {
         var1 = new BiomeGenBase[var4 * var5];
      }

      Arrays.fill(var1, 0, var4 * var5, this.biomeGenerator);
      return var1;
   }

   @Override
   public BlockPos findBiomePosition(int var1, int var2, int var3, List<BiomeGenBase> var4, Random var5) {
      return var4.contains(this.biomeGenerator) ? new BlockPos(var1 - var3 + var5.nextInt(var3 * 2 + 1), 0, var2 - var3 + var5.nextInt(var3 * 2 + 1)) : null;
   }
}
