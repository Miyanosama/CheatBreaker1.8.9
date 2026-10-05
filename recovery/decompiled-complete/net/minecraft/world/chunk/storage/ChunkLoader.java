package net.minecraft.world.chunk.storage;

import io.netty.handler.codec.http.multipart.MixedAttribute;
import net.minecraft.block.BlockPressurePlate$1;
import net.minecraft.block.BlockPrismarine$EnumType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemShears;
import net.minecraft.item.crafting.RecipesArmor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.NibbleArray;
import recovered.unidentified.UnidentifiedClass0144;

public class ChunkLoader {
   public UnidentifiedClass0144 field_0004;
   public Tessellator field_0007;
   public ItemEnderPearl field_0003;
   public BlockPressurePlate$1 field_0006;
   public EntityGiantZombie field_0000;
   public RecipesArmor field_0001;
   public ItemShears field_0008;
   public MixedAttribute field_0005;
   public BlockPrismarine$EnumType field_0002;

   public static void convertToAnvilFormat(ChunkLoader$AnvilConverterData var0, NBTTagCompound var1, WorldChunkManager var2) {
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
      BlockPos$MutableBlockPos var18 = new BlockPos$MutableBlockPos();

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

   public static ChunkLoader$AnvilConverterData load(NBTTagCompound var0) {
      int var1 = var0.getInteger("xPos");
      int var2 = var0.getInteger("zPos");
      ChunkLoader$AnvilConverterData var3 = new ChunkLoader$AnvilConverterData(var1, var2);
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
}
