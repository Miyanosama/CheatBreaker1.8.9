package net.minecraft.world.gen;

import com.cheatbreaker.client.nethandler.server.PacketOverrideNametags;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButtonRealmsProxy;
import net.minecraft.client.resources.FolderResourcePack;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import recovered.unidentified.UnidentifiedClass1617;
import recovered.unidentified.UnidentifiedClass4396;

public class ChunkProviderFlat implements IChunkProvider {
   public FolderResourcePack field_0005;
   public boolean field_0010;
   public UnidentifiedClass4396 field_0004;
   public GuiButtonRealmsProxy field_0009;
   public World worldObj;
   public List<MapGenStructure> structureGenerators;
   public boolean field_0011;
   public UnidentifiedClass1617 field_0008;
   public PacketOverrideNametags field_0003;
   public UnidentifiedClass1617 field_0012;
   public Random random;
   public IBlockState[] field_0006 = new IBlockState[256];
   public FlatGeneratorInfo field_0007;

   @Override
   public boolean saveChunks(boolean var1, IProgressUpdate var2) {
      return true;
   }

   @Override
   public String makeString() {
      return "FlatLevelSource";
   }

   @Override
   public boolean canSave() {
      return true;
   }

   @Override
   public void recreateStructures(Chunk var1, int var2, int var3) {
      for (MapGenStructure var5 : this.structureGenerators) {
         var5.generate(this, this.worldObj, var2, var3, (ChunkPrimer)null);
      }
   }

   @Override
   public int getLoadedChunkCount() {
      return 0;
   }

   @Override
   public boolean unloadQueuedChunks() {
      return false;
   }

   @Override
   public boolean populateChunk(IChunkProvider var1, Chunk var2, int var3, int var4) {
      return false;
   }

   @Override
   public List<BiomeGenBase$SpawnListEntry> getPossibleCreatures(EnumCreatureType var1, BlockPos var2) {
      BiomeGenBase var3 = this.worldObj.getBiomeGenForCoords(var2);
      return var3.getSpawnableList(var1);
   }

   public ChunkProviderFlat(World var1, long var2, boolean var4, String var5) {
      this.structureGenerators = Lists.newArrayList();
      this.worldObj = var1;
      this.random = new Random(var2);
      this.field_0007 = FlatGeneratorInfo.createFlatGeneratorFromString(var5);
      if (var4) {
         Map var6 = this.field_0007.getWorldFeatures();
         if (var6.containsKey("village")) {
            Map var7 = (Map)var6.get("village");
            if (!var7.containsKey("size")) {
               var7.put("size", "1");
            }

            this.structureGenerators.add(new MapGenVillage(var7));
         }

         if (var6.containsKey("biome_1")) {
            this.structureGenerators.add(new MapGenScatteredFeature((Map<String, String>)var6.get("biome_1")));
         }

         if (var6.containsKey("mineshaft")) {
            this.structureGenerators.add(new MapGenMineshaft((Map<String, String>)var6.get("mineshaft")));
         }

         if (var6.containsKey("stronghold")) {
            this.structureGenerators.add(new MapGenStronghold((Map<String, String>)var6.get("stronghold")));
         }

         if (var6.containsKey("oceanmonument")) {
            this.structureGenerators.add(new StructureOceanMonument((Map<String, String>)var6.get("oceanmonument")));
         }
      }

      if (this.field_0007.getWorldFeatures().containsKey("lake")) {
         this.field_0008 = new UnidentifiedClass1617(Blocks.water);
      }

      if (this.field_0007.getWorldFeatures().containsKey("lava_lake")) {
         this.field_0012 = new UnidentifiedClass1617(Blocks.lava);
      }

      this.field_0010 = this.field_0007.getWorldFeatures().containsKey("dungeon");
      int var13 = 0;
      int var14 = 0;
      boolean var8 = true;

      for (FlatLayerInfo var10 : this.field_0007.getFlatLayers()) {
         for (int var11 = var10.getMinY(); var11 < var10.getMinY() + var10.getLayerCount(); var11++) {
            IBlockState var12 = var10.getLayerMaterial();
            if (var12.getBlock() != Blocks.air) {
               var8 = false;
               this.field_0006[var11] = var12;
            }
         }

         if (var10.getLayerMaterial().getBlock() == Blocks.air) {
            var14 += var10.getLayerCount();
         } else {
            var13 += var10.getLayerCount() + var14;
            var14 = 0;
         }
      }

      var1.setSeaLevel(var13);
      this.field_0011 = var8 ? false : this.field_0007.getWorldFeatures().containsKey("decoration");
   }

   @Override
   public boolean chunkExists(int var1, int var2) {
      return true;
   }

   @Override
   public Chunk provideChunk(int var1, int var2) {
      ChunkPrimer var3 = new ChunkPrimer();

      for (int var4 = 0; var4 < this.field_0006.length; var4++) {
         IBlockState var5 = this.field_0006[var4];
         if (var5 != null) {
            for (int var6 = 0; var6 < 16; var6++) {
               for (int var7 = 0; var7 < 16; var7++) {
                  var3.setBlockState(var6, var4, var7, var5);
               }
            }
         }
      }

      for (MapGenBase var10 : this.structureGenerators) {
         var10.generate(this, this.worldObj, var1, var2, var3);
      }

      Chunk var9 = new Chunk(this.worldObj, var3, var1, var2);
      BiomeGenBase[] var11 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, var1 * 16, var2 * 16, 16, 16);
      byte[] var12 = var9.getBiomeArray();

      for (int var13 = 0; var13 < var12.length; var13++) {
         var12[var13] = (byte)var11[var13].az;
      }

      var9.generateSkylightMap();
      return var9;
   }

   @Override
   public BlockPos getStrongholdGen(World var1, String var2, BlockPos var3) {
      if ("Stronghold".equals(var2)) {
         for (MapGenStructure var5 : this.structureGenerators) {
            if (var5 instanceof MapGenStronghold) {
               return var5.getClosestStrongholdPos(var1, var3);
            }
         }
      }

      return null;
   }

   @Override
   public Chunk provideChunk(BlockPos var1) {
      return this.provideChunk(var1.getX() >> 4, var1.getZ() >> 4);
   }

   @Override
   public void saveExtraData() {
   }

   @Override
   public void populate(IChunkProvider var1, int var2, int var3) {
      int var4 = var2 * 16;
      int var5 = var3 * 16;
      BlockPos var6 = new BlockPos(var4, 0, var5);
      BiomeGenBase var7 = this.worldObj.getBiomeGenForCoords(new BlockPos(var4 + 16, 0, var5 + 16));
      boolean var8 = false;
      this.random.setSeed(this.worldObj.J());
      long var9 = this.random.nextLong() / (1225433218L & -710865815691326421L) * (1633226842L & 3375759819102544167L) + (1343750705L & -4996734900275985407L);
      long var11 = this.random.nextLong() / (-3738136808158395366L & 201785542L) * (5153739486262215714L & -5153739487074543418L) + (373345305L & 1210196897L);
      this.random.setSeed(var2 * var9 + var3 * var11 ^ this.worldObj.J());
      ChunkCoordIntPair var13 = new ChunkCoordIntPair(var2, var3);

      for (MapGenStructure var15 : this.structureGenerators) {
         boolean var16 = var15.generateStructure(this.worldObj, this.random, var13);
         if (var15 instanceof MapGenVillage) {
            var8 |= var16;
         }
      }

      if (this.field_0008 != null && !var8 && this.random.nextInt(4) == 0) {
         this.field_0008.generate(this.worldObj, this.random, var6.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
      }

      if (this.field_0012 != null && !var8 && this.random.nextInt(8) == 0) {
         BlockPos var17 = var6.add(this.random.nextInt(16) + 8, this.random.nextInt(this.random.nextInt(248) + 8), this.random.nextInt(16) + 8);
         if (var17.getY() < this.worldObj.F() || this.random.nextInt(10) == 0) {
            this.field_0012.generate(this.worldObj, this.random, var17);
         }
      }

      if (this.field_0010) {
         for (int var18 = 0; var18 < 8; var18++) {
            new WorldGenDungeons()
               .generate(this.worldObj, this.random, var6.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
         }
      }

      if (this.field_0011) {
         var7.decorate(this.worldObj, this.random, var6);
      }
   }
}
