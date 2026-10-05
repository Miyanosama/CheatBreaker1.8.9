package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.LongHashMap;

public class BiomeCache {
   public LongHashMap<BiomeCache.Block> cacheMap = new LongHashMap<>();
   public List<BiomeCache.Block> cache = Lists.newArrayList();
   public long lastCleanupTime;
   public WorldChunkManager chunkManager;

   public BiomeGenBase[] getCachedBiomes(int var1, int var2) {
      return this.getBiomeCacheBlock(var1, var2).biomes;
   }

   public BiomeCache.Block getBiomeCacheBlock(int var1, int var2) {
      var1 >>= 4;
      var2 >>= 4;
      long var3 = var1 & 4294967295L | (var2 & 4294967295L) << 32;
      BiomeCache.Block var5 = this.cacheMap.getValueByKey(var3);
      if (var5 == null) {
         var5 = new BiomeCache.Block(var1, var2);
         this.cacheMap.add(var3, var5);
         this.cache.add(var5);
      }

      var5.lastAccessTime = MinecraftServer.getCurrentTimeMillis();
      return var5;
   }

   public void cleanupCache() {
      long var1 = MinecraftServer.getCurrentTimeMillis();
      long var3 = var1 - this.lastCleanupTime;
      if (var3 > 7500L || var3 < 0L) {
         this.lastCleanupTime = var1;

         for (int var5 = 0; var5 < this.cache.size(); var5++) {
            BiomeCache.Block var6 = this.cache.get(var5);
            long var7 = var1 - var6.lastAccessTime;
            if (var7 > 30000L || var7 < 0L) {
               this.cache.remove(var5--);
               long var9 = var6.xPosition & 4294967295L | (var6.zPosition & 4294967295L) << 32;
               this.cacheMap.remove(var9);
            }
         }
      }
   }

   public BiomeGenBase func_180284_a(int var1, int var2, BiomeGenBase var3) {
      BiomeGenBase var4 = this.getBiomeCacheBlock(var1, var2).getBiomeGenAt(var1, var2);
      return var4 == null ? var3 : var4;
   }

   public BiomeCache(WorldChunkManager var1) {
      this.chunkManager = var1;
   }

   public class Block {
      public int zPosition;
      public int xPosition;
      public long lastAccessTime;
      public BiomeGenBase[] biomes;
      public float[] rainfallValues = new float[256];

      public BiomeGenBase getBiomeGenAt(int var1, int var2) {
         return this.biomes[var1 & 15 | (var2 & 15) << 4];
      }

      public Block(int var2, int var3) {
         this.biomes = new BiomeGenBase[256];
         this.xPosition = var2;
         this.zPosition = var3;
         BiomeCache.this.chunkManager.getRainfall(this.rainfallValues, var2 << 4, var3 << 4, 16, 16);
         BiomeCache.this.chunkManager.getBiomeGenAt(this.biomes, var2 << 4, var3 << 4, 16, 16, false);
      }
   }
}
