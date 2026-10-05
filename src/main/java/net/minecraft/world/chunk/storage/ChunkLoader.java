package net.minecraft.world.chunk.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.NibbleArray;

public class ChunkLoader {
   public static void convertToAnvilFormat(ChunkLoader.AnvilConverterData var0, NBTTagCompound var1, WorldChunkManager var2) {
      var1.setInteger("xPos", var0.x);
      var1.setInteger("zPos", var0.z);
      var1.setLong("LastUpdate", var0.lastUpdated);
      int[] var3 = new int[var0.heightmap.length];

      for (int var4 = 0; var4 < var0.heightmap.length; var4++) {
         var3[var4] = var0.heightmap[var4];
      }

      var1.setIntArray("HeightMap", var3);
      var1.setBoolean("TerrainPopulated", var0.terrainPopulated);
      NBTTagList var16 = new NBTTagList();

      for (int var5 = 0; var5 < 8; var5++) {
         boolean var6 = true;

         for (int var7 = 0; var7 < 16 && var6; var7++) {
            for (int var8 = 0; var8 < 16 && var6; var8++) {
               for (int var9 = 0; var9 < 16; var9++) {
                  int var10 = var7 << 11 | var9 << 7 | var8 + (var5 << 4);
                  byte var11 = var0.blocks[var10];
                  if (var11 != 0) {
                     var6 = false;
                     break;
                  }
               }
            }
         }

         if (!var6) {
            byte[] var19 = new byte[4096];
            NibbleArray var21 = new NibbleArray();
            NibbleArray var23 = new NibbleArray();
            NibbleArray var24 = new NibbleArray();

            for (int var25 = 0; var25 < 16; var25++) {
               for (int var12 = 0; var12 < 16; var12++) {
                  for (int var13 = 0; var13 < 16; var13++) {
                     int var14 = var25 << 11 | var13 << 7 | var12 + (var5 << 4);
                     byte var15 = var0.blocks[var14];
                     var19[var12 << 8 | var13 << 4 | var25] = (byte)(var15 & 255);
                     var21.set(var25, var12, var13, var0.data.get(var25, var12 + (var5 << 4), var13));
                     var23.set(var25, var12, var13, var0.skyLight.get(var25, var12 + (var5 << 4), var13));
                     var24.set(var25, var12, var13, var0.blockLight.get(var25, var12 + (var5 << 4), var13));
                  }
               }
            }

            NBTTagCompound var26 = new NBTTagCompound();
            var26.setByte("Y", (byte)(var5 & 0xFF));
            var26.setByteArray("Blocks", var19);
            var26.setByteArray("Data", var21.getData());
            var26.setByteArray("SkyLight", var23.getData());
            var26.setByteArray("BlockLight", var24.getData());
            var16.appendTag(var26);
         }
      }

      var1.setTag("Sections", var16);
      byte[] var17 = new byte[256];
      BlockPos.MutableBlockPos var18 = new BlockPos.MutableBlockPos();

      for (int var20 = 0; var20 < 16; var20++) {
         for (int var22 = 0; var22 < 16; var22++) {
            var18.set(var0.x << 4 | var20, 0, var0.z << 4 | var22);
            var17[var22 << 4 | var20] = (byte)(var2.getBiomeGenerator(var18, BiomeGenBase.field_180279_ad).az & 0xFF);
         }
      }

      var1.setByteArray("Biomes", var17);
      var1.setTag("Entities", var0.entities);
      var1.setTag("TileEntities", var0.tileEntities);
      if (var0.tileTicks != null) {
         var1.setTag("TileTicks", var0.tileTicks);
      }
   }

   public static ChunkLoader.AnvilConverterData load(NBTTagCompound var0) {
      int var1 = var0.getInteger("xPos");
      int var2 = var0.getInteger("zPos");
      ChunkLoader.AnvilConverterData var3 = new ChunkLoader.AnvilConverterData(var1, var2);
      var3.blocks = var0.getByteArray("Blocks");
      var3.data = new NibbleArrayReader(var0.getByteArray("Data"), 7);
      var3.skyLight = new NibbleArrayReader(var0.getByteArray("SkyLight"), 7);
      var3.blockLight = new NibbleArrayReader(var0.getByteArray("BlockLight"), 7);
      var3.heightmap = var0.getByteArray("HeightMap");
      var3.terrainPopulated = var0.getBoolean("TerrainPopulated");
      var3.entities = var0.getTagList("Entities", 10);
      var3.tileEntities = var0.getTagList("TileEntities", 10);
      var3.tileTicks = var0.getTagList("TileTicks", 10);

      try {
         var3.lastUpdated = var0.getLong("LastUpdate");
      } catch (ClassCastException var5) {
         var3.lastUpdated = var0.getInteger("LastUpdate");
      }

      return var3;
   }

   public static class AnvilConverterData {
      public NibbleArrayReader blockLight;
      public NibbleArrayReader skyLight;
      public boolean terrainPopulated;
      public byte[] blocks;
      public NBTTagList tileTicks;
      public int x;
      public NibbleArrayReader data;
      public int z;
      public NBTTagList entities;
      public NBTTagList tileEntities;
      public byte[] heightmap;
      public long lastUpdated;

      public AnvilConverterData(int var1, int var2) {
         this.x = var1;
         this.z = var2;
      }
   }
}
