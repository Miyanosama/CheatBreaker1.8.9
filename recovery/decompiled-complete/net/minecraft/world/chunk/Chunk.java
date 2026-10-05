package net.minecraft.world.chunk;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import io.netty.handler.codec.compression.JZlibEncoder$1;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$AxisDirection;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.ChunkProviderDebug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass1944;

public class Chunk {
   public boolean[] updateSkylightColumns;
   public boolean isGapLightingUpdated;
   public int b;
   public long inhabitedTime;
   public byte[] blockBiomeArray;
   public boolean hasEntities;
   public boolean isLightPopulated;
   public boolean field_150815_m;
   public ExtendedBlockStorage[] storageArrays = new ExtendedBlockStorage[16];
   public ClassInheritanceMultiMap<Entity>[] entityLists;
   public S24PacketBlockAction field_0005;
   public boolean isChunkLoaded;
   public int a;
   public boolean isModified;
   public UnidentifiedClass1944 field_0018;
   public long lastSaveTime;
   public int heightMapMinimum;
   public static Logger logger = LogManager.getLogger();
   public int[] precipitationHeightMap;
   public JZlibEncoder$1 field_0021;
   public World worldObj;
   public ConcurrentLinkedQueue<BlockPos> tileEntityPosQueue;
   public Map<BlockPos, TileEntity> chunkTileEntityMap;
   public boolean isTerrainPopulated;
   public int[] heightMap;
   public int queuedLightChecks;

   public Map<BlockPos, TileEntity> getTileEntityMap() {
      return this.chunkTileEntityMap;
   }

   public Block getBlock(int var1, int var2, int var3) {
      try {
         return this.getBlock0(var1 & 15, var2, var3 & 15);
      } catch (ReportedException var6) {
         CrashReportCategory var5 = var6.getCrashReport().makeCategory("Block being got");
         var5.addCrashSectionCallable("Location", new Chunk$1(this, var1, var2, var3));
         throw var6;
      }
   }

   public int[] getHeightMap() {
      return this.heightMap;
   }

   public IBlockState setBlockState(BlockPos var1, IBlockState var2) {
      int var3 = var1.getX() & 15;
      int var4 = var1.getY();
      int var5 = var1.getZ() & 15;
      int var6 = var5 << 4 | var3;
      if (var4 >= this.precipitationHeightMap[var6] - 1) {
         this.precipitationHeightMap[var6] = -999;
      }

      int var7 = this.heightMap[var6];
      IBlockState var8 = this.getBlockState(var1);
      if (var8 == var2) {
         return null;
      } else {
         Block var9 = var2.getBlock();
         Block var10 = var8.getBlock();
         ExtendedBlockStorage var11 = this.storageArrays[var4 >> 4];
         boolean var12 = false;
         if (var11 == null) {
            if (var9 == Blocks.air) {
               return null;
            }

            var11 = this.storageArrays[var4 >> 4] = new ExtendedBlockStorage(var4 >> 4 << 4, !this.worldObj.t.getHasNoSky());
            var12 = var4 >= var7;
         }

         var11.set(var3, var4 & 15, var5, var2);
         if (var10 != var9) {
            if (!this.worldObj.D) {
               var10.breakBlock(this.worldObj, var1, var8);
            } else if (var10 instanceof ITileEntityProvider) {
               this.worldObj.removeTileEntity(var1);
            }
         }

         if (var11.getBlockByExtId(var3, var4 & 15, var5) != var9) {
            return null;
         } else {
            if (var12) {
               this.generateSkylightMap();
            } else {
               int var13 = var9.getLightOpacity();
               int var14 = var10.getLightOpacity();
               if (var13 > 0) {
                  if (var4 >= var7) {
                     this.relightBlock(var3, var4 + 1, var5);
                  }
               } else if (var4 == var7 - 1) {
                  this.relightBlock(var3, var4, var5);
               }

               if (var13 != var14 && (var13 < var14 || this.getLightFor(EnumSkyBlock.SKY, var1) > 0 || this.getLightFor(EnumSkyBlock.BLOCK, var1) > 0)) {
                  this.propagateSkylightOcclusion(var3, var5);
               }
            }

            if (var10 instanceof ITileEntityProvider) {
               TileEntity var15 = this.getTileEntity(var1, Chunk$EnumCreateEntityType.CHECK);
               if (var15 != null) {
                  var15.updateContainingBlockInfo();
               }
            }

            if (!this.worldObj.D && var10 != var9) {
               var9.onBlockAdded(this.worldObj, var1, var2);
            }

            if (var9 instanceof ITileEntityProvider) {
               TileEntity var16 = this.getTileEntity(var1, Chunk$EnumCreateEntityType.CHECK);
               if (var16 == null) {
                  var16 = ((ITileEntityProvider)var9).createNewTileEntity(this.worldObj, var9.getMetaFromState(var2));
                  this.worldObj.setTileEntity(var1, var16);
               }

               if (var16 != null) {
                  var16.updateContainingBlockInfo();
               }
            }

            this.isModified = true;
            return var8;
         }
      }
   }

   public void removeTileEntity(BlockPos var1) {
      if (this.isChunkLoaded) {
         TileEntity var2 = this.chunkTileEntityMap.remove(var1);
         if (var2 != null) {
            var2.invalidate();
         }
      }
   }

   public boolean isTerrainPopulated() {
      return this.isTerrainPopulated;
   }

   public boolean func_150811_f(int var1, int var2) {
      int var3 = this.getTopFilledSegment();
      boolean var4 = false;
      boolean var5 = false;
      BlockPos$MutableBlockPos var6 = new BlockPos$MutableBlockPos((this.a << 4) + var1, 0, (this.b << 4) + var2);

      for (int var7 = var3 + 16 - 1; var7 > this.worldObj.F() || var7 > 0 && !var5; var7--) {
         var6.set(var6.getX(), var7, var6.getZ());
         int var8 = this.getBlockLightOpacity(var6);
         if (var8 == 255 && var6.getY() < this.worldObj.F()) {
            var5 = true;
         }

         if (!var4 && var8 > 0) {
            var4 = true;
         } else if (var4 && var8 == 0 && !this.worldObj.checkLight(var6)) {
            return false;
         }
      }

      for (int var9 = var6.getY(); var9 > 0; var9--) {
         var6.set(var6.getX(), var9, var6.getZ());
         if (this.getBlock(var6).getLightValue() > 0) {
            this.worldObj.checkLight(var6);
         }
      }

      return true;
   }

   public void populateChunk(IChunkProvider var1, IChunkProvider var2, int var3, int var4) {
      boolean var5 = var1.chunkExists(var3, var4 - 1);
      boolean var6 = var1.chunkExists(var3 + 1, var4);
      boolean var7 = var1.chunkExists(var3, var4 + 1);
      boolean var8 = var1.chunkExists(var3 - 1, var4);
      boolean var9 = var1.chunkExists(var3 - 1, var4 - 1);
      boolean var10 = var1.chunkExists(var3 + 1, var4 + 1);
      boolean var11 = var1.chunkExists(var3 - 1, var4 + 1);
      boolean var12 = var1.chunkExists(var3 + 1, var4 - 1);
      if (var6 && var7 && var10) {
         if (!this.isTerrainPopulated) {
            var1.populate(var2, var3, var4);
         } else {
            var1.populateChunk(var2, this, var3, var4);
         }
      }

      if (var8 && var7 && var11) {
         Chunk var13 = var1.provideChunk(var3 - 1, var4);
         if (!var13.isTerrainPopulated) {
            var1.populate(var2, var3 - 1, var4);
         } else {
            var1.populateChunk(var2, var13, var3 - 1, var4);
         }
      }

      if (var5 && var6 && var12) {
         Chunk var14 = var1.provideChunk(var3, var4 - 1);
         if (!var14.isTerrainPopulated) {
            var1.populate(var2, var3, var4 - 1);
         } else {
            var1.populateChunk(var2, var14, var3, var4 - 1);
         }
      }

      if (var9 && var5 && var8) {
         Chunk var15 = var1.provideChunk(var3 - 1, var4 - 1);
         if (!var15.isTerrainPopulated) {
            var1.populate(var2, var3 - 1, var4 - 1);
         } else {
            var1.populateChunk(var2, var15, var3 - 1, var4 - 1);
         }
      }
   }

   public void setChunkModified() {
      this.isModified = true;
   }

   public int getHeightValue(int var1, int var2) {
      return this.heightMap[var2 << 4 | var1];
   }

   public void checkSkylightNeighborHeight(int var1, int var2, int var3) {
      int var4 = this.worldObj.getHeight(new BlockPos(var1, 0, var2)).getY();
      if (var4 > var3) {
         this.updateSkylightNeighborHeight(var1, var2, var3, var4 + 1);
      } else if (var4 < var3) {
         this.updateSkylightNeighborHeight(var1, var2, var4, var3 + 1);
      }
   }

   public void generateHeightMap() {
      int var1 = this.getTopFilledSegment();
      this.heightMapMinimum = Integer.MAX_VALUE;

      for (int var2 = 0; var2 < 16; var2++) {
         for (int var3 = 0; var3 < 16; var3++) {
            this.precipitationHeightMap[var2 + (var3 << 4)] = -999;

            for (int var4 = var1 + 16; var4 > 0; var4--) {
               Block var5 = this.getBlock0(var2, var4 - 1, var3);
               if (var5.getLightOpacity() != 0) {
                  this.heightMap[var3 << 4 | var2] = var4;
                  if (var4 < this.heightMapMinimum) {
                     this.heightMapMinimum = var4;
                  }
                  break;
               }
            }
         }
      }

      this.isModified = true;
   }

   public void setInhabitedTime(long var1) {
      this.inhabitedTime = var1;
   }

   public byte[] getBiomeArray() {
      return this.blockBiomeArray;
   }

   public void onChunkUnload() {
      this.isChunkLoaded = false;

      for (TileEntity var2 : this.chunkTileEntityMap.values()) {
         this.worldObj.markTileEntityForRemoval(var2);
      }

      for (int var3 = 0; var3 < this.entityLists.length; var3++) {
         this.worldObj.unloadEntities(this.entityLists[var3]);
      }
   }

   public ChunkCoordIntPair getChunkCoordIntPair() {
      return new ChunkCoordIntPair(this.a, this.b);
   }

   public boolean isEmpty() {
      return false;
   }

   public void func_150809_p() {
      this.isTerrainPopulated = true;
      this.isLightPopulated = true;
      BlockPos var1 = new BlockPos(this.a << 4, 0, this.b << 4);
      if (!this.worldObj.t.getHasNoSky()) {
         if (this.worldObj.isAreaLoaded(var1.add(-1, 0, -1), var1.add(16, this.worldObj.F(), 16))) {
            label44:
            for (int var2 = 0; var2 < 16; var2++) {
               for (int var3 = 0; var3 < 16; var3++) {
                  if (!this.func_150811_f(var2, var3)) {
                     this.isLightPopulated = false;
                     break label44;
                  }
               }
            }

            if (this.isLightPopulated) {
               for (EnumFacing var6 : EnumFacing$Plane.HORIZONTAL) {
                  int var4 = var6.getAxisDirection() == EnumFacing$AxisDirection.POSITIVE ? 16 : 1;
                  this.worldObj.getChunkFromBlockCoords(var1.a(var6, var4)).func_180700_a(var6.getOpposite());
               }

               this.func_177441_y();
            }
         } else {
            this.isLightPopulated = false;
         }
      }
   }

   public boolean isLightPopulated() {
      return this.isLightPopulated;
   }

   public int getLowestHeight() {
      return this.heightMapMinimum;
   }

   public int getHeight(BlockPos var1) {
      return this.getHeightValue(var1.getX() & 15, var1.getZ() & 15);
   }

   public void relightBlock(int var1, int var2, int var3) {
      int var4 = this.heightMap[var3 << 4 | var1] & 0xFF;
      int var5 = var4;
      if (var2 > var4) {
         var5 = var2;
      }

      while (var5 > 0 && this.getBlockLightOpacity(var1, var5 - 1, var3) == 0) {
         var5--;
      }

      if (var5 != var4) {
         this.worldObj.markBlocksDirtyVertical(var1 + this.a * 16, var3 + this.b * 16, var5, var4);
         this.heightMap[var3 << 4 | var1] = var5;
         int var6 = this.a * 16 + var1;
         int var7 = this.b * 16 + var3;
         if (!this.worldObj.t.getHasNoSky()) {
            if (var5 < var4) {
               for (int var8 = var5; var8 < var4; var8++) {
                  ExtendedBlockStorage var9 = this.storageArrays[var8 >> 4];
                  if (var9 != null) {
                     var9.setExtSkylightValue(var1, var8 & 15, var3, 15);
                     this.worldObj.method_09975(new BlockPos((this.a << 4) + var1, var8, (this.b << 4) + var3));
                  }
               }
            } else {
               for (int var13 = var4; var13 < var5; var13++) {
                  ExtendedBlockStorage var16 = this.storageArrays[var13 >> 4];
                  if (var16 != null) {
                     var16.setExtSkylightValue(var1, var13 & 15, var3, 0);
                     this.worldObj.method_09975(new BlockPos((this.a << 4) + var1, var13, (this.b << 4) + var3));
                  }
               }
            }

            int var14 = 15;

            while (var5 > 0 && var14 > 0) {
               int var17 = this.getBlockLightOpacity(var1, --var5, var3);
               if (var17 == 0) {
                  var17 = 1;
               }

               var14 -= var17;
               if (var14 < 0) {
                  var14 = 0;
               }

               ExtendedBlockStorage var10 = this.storageArrays[var5 >> 4];
               if (var10 != null) {
                  var10.setExtSkylightValue(var1, var5 & 15, var3, var14);
               }
            }
         }

         int var15 = this.heightMap[var3 << 4 | var1];
         int var18 = var4;
         int var19 = var15;
         if (var15 < var4) {
            var18 = var15;
            var19 = var4;
         }

         if (var15 < this.heightMapMinimum) {
            this.heightMapMinimum = var15;
         }

         if (!this.worldObj.t.getHasNoSky()) {
            for (EnumFacing var12 : EnumFacing$Plane.HORIZONTAL) {
               this.updateSkylightNeighborHeight(var6 + var12.getFrontOffsetX(), var7 + var12.getFrontOffsetZ(), var18, var19);
            }

            this.updateSkylightNeighborHeight(var6, var7, var18, var19);
         }

         this.isModified = true;
      }
   }

   public int getTopFilledSegment() {
      for (int var1 = this.storageArrays.length - 1; var1 >= 0; var1--) {
         if (this.storageArrays[var1] != null) {
            return this.storageArrays[var1].getYLocation();
         }
      }

      return 0;
   }

   public void func_150804_b(boolean var1) {
      if (this.isGapLightingUpdated && !this.worldObj.t.getHasNoSky() && !var1) {
         this.recheckGaps(this.worldObj.D);
      }

      this.field_150815_m = true;
      if (!this.isLightPopulated && this.isTerrainPopulated) {
         this.func_150809_p();
      }

      while (!this.tileEntityPosQueue.isEmpty()) {
         BlockPos var2 = this.tileEntityPosQueue.poll();
         if (this.getTileEntity(var2, Chunk$EnumCreateEntityType.CHECK) == null && this.getBlock(var2).hasTileEntity()) {
            TileEntity var3 = this.createNewTileEntity(var2);
            this.worldObj.setTileEntity(var2, var3);
            this.worldObj.markBlockRangeForRenderUpdate(var2, var2);
         }
      }
   }

   public int getLightFor(EnumSkyBlock var1, BlockPos var2) {
      int var3 = var2.getX() & 15;
      int var4 = var2.getY();
      int var5 = var2.getZ() & 15;
      ExtendedBlockStorage var6 = this.storageArrays[var4 >> 4];
      return var6 == null
         ? (this.canSeeSky(var2) ? var1.defaultLightValue : 0)
         : (
            var1 == EnumSkyBlock.SKY
               ? (this.worldObj.t.getHasNoSky() ? 0 : var6.getExtSkylightValue(var3, var4 & 15, var5))
               : (var1 == EnumSkyBlock.BLOCK ? var6.getExtBlocklightValue(var3, var4 & 15, var5) : var1.defaultLightValue)
         );
   }

   public void generateSkylightMap() {
      int var1 = this.getTopFilledSegment();
      this.heightMapMinimum = Integer.MAX_VALUE;

      for (int var2 = 0; var2 < 16; var2++) {
         for (int var3 = 0; var3 < 16; var3++) {
            this.precipitationHeightMap[var2 + (var3 << 4)] = -999;

            for (int var4 = var1 + 16; var4 > 0; var4--) {
               if (this.getBlockLightOpacity(var2, var4 - 1, var3) != 0) {
                  this.heightMap[var3 << 4 | var2] = var4;
                  if (var4 < this.heightMapMinimum) {
                     this.heightMapMinimum = var4;
                  }
                  break;
               }
            }

            if (!this.worldObj.t.getHasNoSky()) {
               int var8 = 15;
               int var5 = var1 + 16 - 1;

               while (true) {
                  int var6 = this.getBlockLightOpacity(var2, var5, var3);
                  if (var6 == 0 && var8 != 15) {
                     var6 = 1;
                  }

                  var8 -= var6;
                  if (var8 > 0) {
                     ExtendedBlockStorage var7 = this.storageArrays[var5 >> 4];
                     if (var7 != null) {
                        var7.setExtSkylightValue(var2, var5 & 15, var3, var8);
                        this.worldObj.method_09975(new BlockPos((this.a << 4) + var2, var5, (this.b << 4) + var3));
                     }
                  }

                  if (--var5 <= 0 || var8 <= 0) {
                     break;
                  }
               }
            }
         }
      }

      this.isModified = true;
   }

   public void enqueueRelightChecks() {
      BlockPos var1 = new BlockPos(this.a << 4, 0, this.b << 4);

      for (int var2 = 0; var2 < 8; var2++) {
         if (this.queuedLightChecks >= 4096) {
            return;
         }

         int var3 = this.queuedLightChecks % 16;
         int var4 = this.queuedLightChecks / 16 % 16;
         int var5 = this.queuedLightChecks / 256;
         this.queuedLightChecks++;

         for (int var6 = 0; var6 < 16; var6++) {
            BlockPos var7 = var1.add(var4, (var3 << 4) + var6, var5);
            boolean var8 = var6 == 0 || var6 == 15 || var4 == 0 || var4 == 15 || var5 == 0 || var5 == 15;
            if (this.storageArrays[var3] == null && var8
               || this.storageArrays[var3] != null && this.storageArrays[var3].getBlockByExtId(var4, var6, var5).getMaterial() == Material.air) {
               for (EnumFacing var12 : EnumFacing.values()) {
                  BlockPos var13 = var7.a(var12);
                  if (this.worldObj.getBlockState(var13).getBlock().getLightValue() > 0) {
                     this.worldObj.checkLight(var13);
                  }
               }

               this.worldObj.checkLight(var7);
            }
         }
      }
   }

   public void addTileEntity(TileEntity var1) {
      this.addTileEntity(var1.v(), var1);
      if (this.isChunkLoaded) {
         this.worldObj.addTileEntity(var1);
      }
   }

   public Chunk(World var1, ChunkPrimer var2, int var3, int var4) {
      this(var1, var3, var4);
      short var5 = 256;
      boolean var6 = !var1.t.getHasNoSky();

      for (int var7 = 0; var7 < 16; var7++) {
         for (int var8 = 0; var8 < 16; var8++) {
            for (int var9 = 0; var9 < var5; var9++) {
               int var10 = var7 * var5 * 16 | var8 * var5 | var9;
               IBlockState var11 = var2.getBlockState(var10);
               if (var11.getBlock().getMaterial() != Material.air) {
                  int var12 = var9 >> 4;
                  if (this.storageArrays[var12] == null) {
                     this.storageArrays[var12] = new ExtendedBlockStorage(var12 << 4, var6);
                  }

                  this.storageArrays[var12].set(var7, var9 & 15, var8, var11);
               }
            }
         }
      }
   }

   public TileEntity getTileEntity(BlockPos var1, Chunk$EnumCreateEntityType var2) {
      TileEntity var3 = this.chunkTileEntityMap.get(var1);
      if (var3 == null) {
         if (var2 == Chunk$EnumCreateEntityType.IMMEDIATE) {
            var3 = this.createNewTileEntity(var1);
            this.worldObj.setTileEntity(var1, var3);
         } else if (var2 == Chunk$EnumCreateEntityType.QUEUED) {
            this.tileEntityPosQueue.add(var1);
         }
      } else if (var3.isInvalid()) {
         this.chunkTileEntityMap.remove(var1);
         return null;
      }

      return var3;
   }

   public void resetRelightChecks() {
      this.queuedLightChecks = 0;
   }

   public long getInhabitedTime() {
      return this.inhabitedTime;
   }

   public void removeEntity(Entity var1) {
      this.removeEntityAtIndex(var1, var1.chunkCoordY);
   }

   public void removeEntityAtIndex(Entity var1, int var2) {
      if (var2 < 0) {
         var2 = 0;
      }

      if (var2 >= this.entityLists.length) {
         var2 = this.entityLists.length - 1;
      }

      this.entityLists[var2].remove(var1);
   }

   public void getEntitiesWithinAABBForEntity(Entity var1, AxisAlignedBB var2, List<Entity> var3, Predicate<? super Entity> var4) {
      int var5 = MathHelper.floor_double((var2.b - 2.0) / 16.0);
      int var6 = MathHelper.floor_double((var2.e + 2.0) / 16.0);
      var5 = MathHelper.clamp_int(var5, 0, this.entityLists.length - 1);
      var6 = MathHelper.clamp_int(var6, 0, this.entityLists.length - 1);

      for (int var7 = var5; var7 <= var6; var7++) {
         if (!this.entityLists[var7].isEmpty()) {
            for (Entity var9 : this.entityLists[var7]) {
               if (var9.getEntityBoundingBox().intersectsWith(var2) && var9 != var1) {
                  if (var4 == null || var4.apply(var9)) {
                     var3.add(var9);
                  }

                  Entity[] var10 = var9.getParts();
                  if (var10 != null) {
                     for (int var11 = 0; var11 < var10.length; var11++) {
                        var9 = var10[var11];
                        if (var9 != var1 && var9.getEntityBoundingBox().intersectsWith(var2) && (var4 == null || var4.apply(var9))) {
                           var3.add(var9);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public TileEntity createNewTileEntity(BlockPos var1) {
      Block var2 = this.getBlock(var1);
      return !var2.hasTileEntity() ? null : ((ITileEntityProvider)var2).createNewTileEntity(this.worldObj, this.getBlockMetadata(var1));
   }

   public int getBlockMetadata(int var1, int var2, int var3) {
      if (var2 >> 4 >= this.storageArrays.length) {
         return 0;
      } else {
         ExtendedBlockStorage var4 = this.storageArrays[var2 >> 4];
         return var4 != null ? var4.getExtBlockMetadata(var1, var2 & 15, var3) : 0;
      }
   }

   public boolean isPopulated() {
      return this.field_150815_m && this.isTerrainPopulated && this.isLightPopulated;
   }

   public void setLightFor(EnumSkyBlock var1, BlockPos var2, int var3) {
      int var4 = var2.getX() & 15;
      int var5 = var2.getY();
      int var6 = var2.getZ() & 15;
      ExtendedBlockStorage var7 = this.storageArrays[var5 >> 4];
      if (var7 == null) {
         var7 = this.storageArrays[var5 >> 4] = new ExtendedBlockStorage(var5 >> 4 << 4, !this.worldObj.t.getHasNoSky());
         this.generateSkylightMap();
      }

      this.isModified = true;
      if (var1 == EnumSkyBlock.SKY) {
         if (!this.worldObj.t.getHasNoSky()) {
            var7.setExtSkylightValue(var4, var5 & 15, var6, var3);
         }
      } else if (var1 == EnumSkyBlock.BLOCK) {
         var7.setExtBlocklightValue(var4, var5 & 15, var6, var3);
      }
   }

   public void recheckGaps(boolean var1) {
      this.worldObj.B.startSection("recheckGaps");
      if (this.worldObj.isAreaLoaded(new BlockPos(this.a * 16 + 8, 0, this.b * 16 + 8), 16)) {
         for (int var2 = 0; var2 < 16; var2++) {
            for (int var3 = 0; var3 < 16; var3++) {
               if (this.updateSkylightColumns[var2 + var3 * 16]) {
                  this.updateSkylightColumns[var2 + var3 * 16] = false;
                  int var4 = this.getHeightValue(var2, var3);
                  int var5 = this.a * 16 + var2;
                  int var6 = this.b * 16 + var3;
                  int var7 = Integer.MAX_VALUE;

                  for (EnumFacing var9 : EnumFacing$Plane.HORIZONTAL) {
                     var7 = Math.min(var7, this.worldObj.getChunksLowestHorizon(var5 + var9.getFrontOffsetX(), var6 + var9.getFrontOffsetZ()));
                  }

                  this.checkSkylightNeighborHeight(var5, var6, var7);

                  for (EnumFacing var11 : EnumFacing$Plane.HORIZONTAL) {
                     this.checkSkylightNeighborHeight(var5 + var11.getFrontOffsetX(), var6 + var11.getFrontOffsetZ(), var4);
                  }

                  if (var1) {
                     this.worldObj.B.endSection();
                     return;
                  }
               }
            }
         }

         this.isGapLightingUpdated = false;
      }

      this.worldObj.B.endSection();
   }

   public ExtendedBlockStorage[] getBlockStorageArray() {
      return this.storageArrays;
   }

   public boolean canSeeSky(BlockPos var1) {
      int var2 = var1.getX() & 15;
      int var3 = var1.getY();
      int var4 = var1.getZ() & 15;
      return var3 >= this.heightMap[var4 << 4 | var2];
   }

   public void updateSkylightNeighborHeight(int var1, int var2, int var3, int var4) {
      if (var4 > var3 && this.worldObj.isAreaLoaded(new BlockPos(var1, 0, var2), 16)) {
         for (int var5 = var3; var5 < var4; var5++) {
            this.worldObj.checkLightFor(EnumSkyBlock.SKY, new BlockPos(var1, var5, var2));
         }

         this.isModified = true;
      }
   }

   public void setLightPopulated(boolean var1) {
      this.isLightPopulated = var1;
   }

   public void addEntity(Entity var1) {
      this.hasEntities = true;
      int var2 = MathHelper.floor_double(var1.s / 16.0);
      int var3 = MathHelper.floor_double(var1.u / 16.0);
      if (var2 != this.a || var3 != this.b) {
         logger.warn("Wrong location! (" + var2 + ", " + var3 + ") should be (" + this.a + ", " + this.b + "), " + var1, new Object[]{var1});
         var1.setDead();
      }

      int var4 = MathHelper.floor_double(var1.t / 16.0);
      if (var4 < 0) {
         var4 = 0;
      }

      if (var4 >= this.entityLists.length) {
         var4 = this.entityLists.length - 1;
      }

      var1.addedToChunk = true;
      var1.chunkCoordX = this.a;
      var1.chunkCoordY = var4;
      var1.chunkCoordZ = this.b;
      this.entityLists[var4].add(var1);
   }

   public void setChunkLoaded(boolean var1) {
      this.isChunkLoaded = var1;
   }

   public ClassInheritanceMultiMap<Entity>[] getEntityLists() {
      return this.entityLists;
   }

   public int getBlockLightOpacity(BlockPos var1) {
      return this.getBlock(var1).getLightOpacity();
   }

   public void addTileEntity(BlockPos var1, TileEntity var2) {
      var2.setWorldObj(this.worldObj);
      var2.setPos(var1);
      if (this.getBlock(var1) instanceof ITileEntityProvider) {
         if (this.chunkTileEntityMap.containsKey(var1)) {
            this.chunkTileEntityMap.get(var1).invalidate();
         }

         var2.validate();
         this.chunkTileEntityMap.put(var1, var2);
      }
   }

   public void setBiomeArray(byte[] var1) {
      if (this.blockBiomeArray.length != var1.length) {
         logger.warn("Could not set level chunk biomes, array length is " + var1.length + " instead of " + this.blockBiomeArray.length);
      } else {
         for (int var2 = 0; var2 < this.blockBiomeArray.length; var2++) {
            this.blockBiomeArray[var2] = var1[var2];
         }
      }
   }

   public void setLastSaveTime(long var1) {
      this.lastSaveTime = var1;
   }

   public void propagateSkylightOcclusion(int var1, int var2) {
      this.updateSkylightColumns[var1 + var2 * 16] = true;
      this.isGapLightingUpdated = true;
   }

   public void setTerrainPopulated(boolean var1) {
      this.isTerrainPopulated = var1;
   }

   public Random getRandomWithSeed(long var1) {
      return new Random(
         this.worldObj.J() + this.a * this.a * 4987142 + this.a * 5947611 + this.b * this.b * (-652480208652531801L & 1363388399L) + this.b * 389711 ^ var1
      );
   }

   public Chunk(World var1, int var2, int var3) {
      this.blockBiomeArray = new byte[256];
      this.precipitationHeightMap = new int[256];
      this.updateSkylightColumns = new boolean[256];
      this.chunkTileEntityMap = Maps.newHashMap();
      this.queuedLightChecks = 4096;
      this.tileEntityPosQueue = Queues.newConcurrentLinkedQueue();
      this.entityLists = new ClassInheritanceMultiMap[16];
      this.worldObj = var1;
      this.a = var2;
      this.b = var3;
      this.heightMap = new int[256];

      for (int var4 = 0; var4 < this.entityLists.length; var4++) {
         this.entityLists[var4] = new ClassInheritanceMultiMap<>(Entity.class);
      }

      Arrays.fill(this.precipitationHeightMap, -999);
      Arrays.fill(this.blockBiomeArray, (byte)-1);
   }

   public BiomeGenBase getBiome(BlockPos var1, WorldChunkManager var2) {
      int var3 = var1.getX() & 15;
      int var4 = var1.getZ() & 15;
      int var5 = this.blockBiomeArray[var4 << 4 | var3] & 255;
      if (var5 == 255) {
         BiomeGenBase var6 = var2.getBiomeGenerator(var1, BiomeGenBase.plains);
         var5 = var6.az;
         this.blockBiomeArray[var4 << 4 | var3] = (byte)(var5 & 0xFF);
      }

      BiomeGenBase var7 = BiomeGenBase.getBiome(var5);
      return var7 == null ? BiomeGenBase.plains : var7;
   }

   public boolean getAreLevelsEmpty(int var1, int var2) {
      if (var1 < 0) {
         var1 = 0;
      }

      if (var2 >= 256) {
         var2 = 255;
      }

      for (int var3 = var1; var3 <= var2; var3 += 16) {
         ExtendedBlockStorage var4 = this.storageArrays[var3 >> 4];
         if (var4 != null && !var4.isEmpty()) {
            return false;
         }
      }

      return true;
   }

   public void setHeightMap(int[] var1) {
      if (this.heightMap.length != var1.length) {
         logger.warn("Could not set level chunk heightmap, array length is " + var1.length + " instead of " + this.heightMap.length);
      } else {
         for (int var2 = 0; var2 < this.heightMap.length; var2++) {
            this.heightMap[var2] = var1[var2];
         }
      }
   }

   public <T extends Entity> void getEntitiesOfTypeWithinAAAB(Class<? extends T> var1, AxisAlignedBB var2, List<T> var3, Predicate<? super T> var4) {
      int var5 = MathHelper.floor_double((var2.b - 2.0) / 16.0);
      int var6 = MathHelper.floor_double((var2.e + 2.0) / 16.0);
      var5 = MathHelper.clamp_int(var5, 0, this.entityLists.length - 1);
      var6 = MathHelper.clamp_int(var6, 0, this.entityLists.length - 1);

      for (int var7 = var5; var7 <= var6; var7++) {
         for (Entity var9 : this.entityLists[var7].getByClass(var1)) {
            if (var9.getEntityBoundingBox().intersectsWith(var2) && (var4 == null || var4.apply(var9))) {
               var3.add(var9);
            }
         }
      }
   }

   public Block getBlock(BlockPos var1) {
      try {
         return this.getBlock0(var1.getX() & 15, var1.getY(), var1.getZ() & 15);
      } catch (ReportedException var4) {
         CrashReportCategory var3 = var4.getCrashReport().makeCategory("Block being got");
         var3.addCrashSectionCallable("Location", new Chunk$2(this, var1));
         throw var4;
      }
   }

   public void setModified(boolean var1) {
      this.isModified = var1;
   }

   public boolean needsSaving(boolean var1) {
      if (var1) {
         if (this.hasEntities && this.worldObj.K() != this.lastSaveTime || this.isModified) {
            return true;
         }
      } else if (this.hasEntities && this.worldObj.K() >= this.lastSaveTime + (420529144L & -3243544791276836263L)) {
         return true;
      }

      return this.isModified;
   }

   public void onChunkLoad() {
      this.isChunkLoaded = true;
      this.worldObj.addTileEntities(this.chunkTileEntityMap.values());

      for (int var1 = 0; var1 < this.entityLists.length; var1++) {
         for (Entity var3 : this.entityLists[var1]) {
            var3.onChunkLoad();
         }

         this.worldObj.loadEntities(this.entityLists[var1]);
      }
   }

   public BlockPos getPrecipitationHeight(BlockPos var1) {
      int var2 = var1.getX() & 15;
      int var3 = var1.getZ() & 15;
      int var4 = var2 | var3 << 4;
      BlockPos var5 = new BlockPos(var1.getX(), this.precipitationHeightMap[var4], var1.getZ());
      if (var5.getY() == -999) {
         int var6 = this.getTopFilledSegment() + 15;
         var5 = new BlockPos(var1.getX(), var6, var1.getZ());
         int var7 = -1;

         while (var5.getY() > 0 && var7 == -1) {
            Block var8 = this.getBlock(var5);
            Material var9 = var8.getMaterial();
            if (!var9.blocksMovement() && !var9.isLiquid()) {
               var5 = var5.down();
            } else {
               var7 = var5.getY() + 1;
            }
         }

         this.precipitationHeightMap[var4] = var7;
      }

      return new BlockPos(var1.getX(), this.precipitationHeightMap[var4], var1.getZ());
   }

   public boolean isAtLocation(int var1, int var2) {
      return var1 == this.a && var2 == this.b;
   }

   public World getWorld() {
      return this.worldObj;
   }

   public int getBlockMetadata(BlockPos var1) {
      return this.getBlockMetadata(var1.getX() & 15, var1.getY(), var1.getZ() & 15);
   }

   public void setStorageArrays(ExtendedBlockStorage[] var1) {
      if (this.storageArrays.length != var1.length) {
         logger.warn("Could not set level chunk sections, array length is " + var1.length + " instead of " + this.storageArrays.length);
      } else {
         for (int var2 = 0; var2 < this.storageArrays.length; var2++) {
            this.storageArrays[var2] = var1[var2];
         }
      }
   }

   public void fillChunk(byte[] var1, int var2, boolean var3) {
      int var4 = 0;
      boolean var5 = !this.worldObj.t.getHasNoSky();

      for (int var6 = 0; var6 < this.storageArrays.length; var6++) {
         if ((var2 & 1 << var6) != 0) {
            if (this.storageArrays[var6] == null) {
               this.storageArrays[var6] = new ExtendedBlockStorage(var6 << 4, var5);
            }

            char[] var7 = this.storageArrays[var6].getData();

            for (int var8 = 0; var8 < var7.length; var8++) {
               var7[var8] = (char)((var1[var4 + 1] & 255) << 8 | var1[var4] & 255);
               var4 += 2;
            }
         } else if (var3 && this.storageArrays[var6] != null) {
            this.storageArrays[var6] = null;
         }
      }

      for (int var9 = 0; var9 < this.storageArrays.length; var9++) {
         if ((var2 & 1 << var9) != 0 && this.storageArrays[var9] != null) {
            NibbleArray var14 = this.storageArrays[var9].getBlocklightArray();
            System.arraycopy(var1, var4, var14.getData(), 0, var14.getData().length);
            var4 += var14.getData().length;
         }
      }

      if (var5) {
         for (int var10 = 0; var10 < this.storageArrays.length; var10++) {
            if ((var2 & 1 << var10) != 0 && this.storageArrays[var10] != null) {
               NibbleArray var15 = this.storageArrays[var10].getSkylightArray();
               System.arraycopy(var1, var4, var15.getData(), 0, var15.getData().length);
               var4 += var15.getData().length;
            }
         }
      }

      if (var3) {
         System.arraycopy(var1, var4, this.blockBiomeArray, 0, this.blockBiomeArray.length);
         int var11 = var4 + this.blockBiomeArray.length;
      }

      for (int var12 = 0; var12 < this.storageArrays.length; var12++) {
         if (this.storageArrays[var12] != null && (var2 & 1 << var12) != 0) {
            this.storageArrays[var12].removeInvalidBlocks();
         }
      }

      this.isLightPopulated = true;
      this.isTerrainPopulated = true;
      this.generateHeightMap();

      for (TileEntity var16 : this.chunkTileEntityMap.values()) {
         var16.updateContainingBlockInfo();
      }
   }

   public int getLightSubtracted(BlockPos var1, int var2) {
      int var3 = var1.getX() & 15;
      int var4 = var1.getY();
      int var5 = var1.getZ() & 15;
      ExtendedBlockStorage var6 = this.storageArrays[var4 >> 4];
      if (var6 != null) {
         int var7 = this.worldObj.t.getHasNoSky() ? 0 : var6.getExtSkylightValue(var3, var4 & 15, var5);
         var7 -= var2;
         int var8 = var6.getExtBlocklightValue(var3, var4 & 15, var5);
         if (var8 > var7) {
            var7 = var8;
         }

         return var7;
      } else {
         return !this.worldObj.t.getHasNoSky() && var2 < EnumSkyBlock.SKY.defaultLightValue ? EnumSkyBlock.SKY.defaultLightValue - var2 : 0;
      }
   }

   public IBlockState getBlockState(BlockPos var1) {
      if (this.worldObj.x_() == WorldType.DEBUG_WORLD) {
         IBlockState var7 = null;
         if (var1.getY() == 60) {
            var7 = Blocks.barrier.getDefaultState();
         }

         if (var1.getY() == 70) {
            var7 = ChunkProviderDebug.func_177461_b(var1.getX(), var1.getZ());
         }

         return var7 == null ? Blocks.air.getDefaultState() : var7;
      } else {
         try {
            if (var1.getY() >= 0 && var1.getY() >> 4 < this.storageArrays.length) {
               ExtendedBlockStorage var2 = this.storageArrays[var1.getY() >> 4];
               if (var2 != null) {
                  int var8 = var1.getX() & 15;
                  int var9 = var1.getY() & 15;
                  int var5 = var1.getZ() & 15;
                  return var2.get(var8, var9, var5);
               }
            }

            return Blocks.air.getDefaultState();
         } catch (Throwable var6) {
            CrashReport var3 = CrashReport.makeCrashReport(var6, "Getting block state");
            CrashReportCategory var4 = var3.makeCategory("Block being got");
            var4.addCrashSectionCallable("Location", new Chunk$3(this, var1));
            throw new ReportedException(var3);
         }
      }
   }

   public Block getBlock0(int var1, int var2, int var3) {
      Block var4 = Blocks.air;
      if (var2 >= 0 && var2 >> 4 < this.storageArrays.length) {
         ExtendedBlockStorage var5 = this.storageArrays[var2 >> 4];
         if (var5 != null) {
            try {
               var4 = var5.getBlockByExtId(var1, var2 & 15, var3);
            } catch (Throwable var8) {
               CrashReport var7 = CrashReport.makeCrashReport(var8, "Getting block");
               throw new ReportedException(var7);
            }
         }
      }

      return var4;
   }

   public void func_180700_a(EnumFacing var1) {
      if (this.isTerrainPopulated) {
         if (var1 == EnumFacing.EAST) {
            for (int var2 = 0; var2 < 16; var2++) {
               this.func_150811_f(15, var2);
            }
         } else if (var1 == EnumFacing.WEST) {
            for (int var3 = 0; var3 < 16; var3++) {
               this.func_150811_f(0, var3);
            }
         } else if (var1 == EnumFacing.SOUTH) {
            for (int var4 = 0; var4 < 16; var4++) {
               this.func_150811_f(var4, 15);
            }
         } else if (var1 == EnumFacing.NORTH) {
            for (int var5 = 0; var5 < 16; var5++) {
               this.func_150811_f(var5, 0);
            }
         }
      }
   }

   public void func_177441_y() {
      for (int var1 = 0; var1 < this.updateSkylightColumns.length; var1++) {
         this.updateSkylightColumns[var1] = true;
      }

      this.recheckGaps(false);
   }

   public void setHasEntities(boolean var1) {
      this.hasEntities = var1;
   }

   public int getBlockLightOpacity(int var1, int var2, int var3) {
      return this.getBlock0(var1, var2, var3).getLightOpacity();
   }

   public boolean isLoaded() {
      return this.isChunkLoaded;
   }
}
