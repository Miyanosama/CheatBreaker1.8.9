package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.item.crafting.RecipeFireworks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RightTurn;
import net.minecraft.world.storage.SaveDataMemoryStorage;

public class BiomeCache {
   public LongHashMap<BiomeCache$Block> cacheMap = new LongHashMap<>();
   public List<BiomeCache$Block> cache = Lists.newArrayList();
   public long lastCleanupTime;
   public WorldChunkManager chunkManager;
   public RecipeFireworks field_0000;
   public SaveDataMemoryStorage field_0001;
   public StructureStrongholdPieces$RightTurn field_0006;

   public BiomeGenBase[] getCachedBiomes(int var1, int var2) {
      return this.getBiomeCacheBlock(var1, var2).biomes;
   }

   public BiomeCache$Block getBiomeCacheBlock(int var1, int var2) {
      var1 >>= 4;
      var2 >>= 4;
      long var3 = var1 & 4294967295L & 4294967295L | (var2 & 4294967295L & 4294967295L) << 32;
      BiomeCache$Block var5 = this.cacheMap.getValueByKey(var3);
      if (var5 == null) {
         var5 = new BiomeCache$Block(this, var1, var2);
         this.cacheMap.add(var3, var5);
         this.cache.add(var5);
      }

      var5.lastAccessTime = MinecraftServer.getCurrentTimeMillis();
      return var5;
   }

   public void cleanupCache() {
      long var1 = MinecraftServer.getCurrentTimeMillis();
      long var3 = var1 - this.lastCleanupTime;
      if (var3 > (4098807524239613260L & 401372L) || var3 < (113729576L & 152604930L)) {
         this.lastCleanupTime = var1;

         for (int var5 = 0; var5 < this.cache.size(); var5++) {
            BiomeCache$Block var6 = this.cache.get(var5);
            long var7 = var1 - var6.lastAccessTime;
            if (var7 > (1413568011645777268L & -1413568013679231688L) || var7 < (-9123864301201378048L & 9123864299684055561L)) {
               this.cache.remove(var5--);
               long var9 = var6.xPosition & 2786282818357952511L & -2786282814062985217L | (var6.zPosition & -5871982804945862657L & 4294967295L) << 32;
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
}
