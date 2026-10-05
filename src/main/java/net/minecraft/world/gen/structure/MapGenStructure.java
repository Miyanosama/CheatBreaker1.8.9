package net.minecraft.world.gen.structure;

import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

public abstract class MapGenStructure extends MapGenBase {
   public Map<Long, StructureStart> structureMap = Maps.newHashMap();
   public MapGenStructureData structureData;

   public void setStructureStart(int var1, int var2, StructureStart var3) {
      this.structureData.writeInstance(var3.writeStructureComponentsToNBT(var1, var2), var1, var2);
      this.structureData.markDirty();
   }

   public StructureStart func_175797_c(BlockPos var1) {
      for (StructureStart var3 : this.structureMap.values()) {
         if (var3.isSizeableStructure() && var3.getBoundingBox().isVecInside(var1)) {
            for (StructureComponent var5 : var3.getComponents()) {
               if (var5.getBoundingBox().isVecInside(var1)) {
                  return var3;
               }
            }
         }
      }

      return null;
   }

   public void initializeStructureData(World var1) {
      if (this.structureData == null) {
         this.structureData = (MapGenStructureData)var1.loadItemData(MapGenStructureData.class, this.getStructureName());
         if (this.structureData == null) {
            this.structureData = new MapGenStructureData(this.getStructureName());
            var1.setItemData(this.getStructureName(), this.structureData);
         } else {
            NBTTagCompound var2 = this.structureData.getTagCompound();

            for (String var4 : var2.getKeySet()) {
               NBTBase var5 = var2.getTag(var4);
               if (var5.getId() == 10) {
                  NBTTagCompound var6 = (NBTTagCompound)var5;
                  if (var6.hasKey("ChunkX") && var6.hasKey("ChunkZ")) {
                     int var7 = var6.getInteger("ChunkX");
                     int var8 = var6.getInteger("ChunkZ");
                     StructureStart var9 = MapGenStructureIO.getStructureStart(var6, var1);
                     if (var9 != null) {
                        this.structureMap.put(ChunkCoordIntPair.chunkXZ2Int(var7, var8), var9);
                     }
                  }
               }
            }
         }
      }
   }

   public boolean generateStructure(World var1, Random var2, ChunkCoordIntPair var3) {
      this.initializeStructureData(var1);
      int var4 = (var3.chunkXPos << 4) + 8;
      int var5 = (var3.chunkZPos << 4) + 8;
      boolean var6 = false;

      for (StructureStart var8 : this.structureMap.values()) {
         if (var8.isSizeableStructure() && var8.func_175788_a(var3) && var8.getBoundingBox().intersectsWith(var4, var5, var4 + 15, var5 + 15)) {
            var8.generateStructure(var1, var2, new StructureBoundingBox(var4, var5, var4 + 15, var5 + 15));
            var8.func_175787_b(var3);
            var6 = true;
            this.setStructureStart(var8.getChunkPosX(), var8.getChunkPosZ(), var8);
         }
      }

      return var6;
   }

   @Override
   public void recursiveGenerate(World var1, final int var2, final int var3, int var4, int var5, ChunkPrimer var6) {
      this.initializeStructureData(var1);
      if (!this.structureMap.containsKey(ChunkCoordIntPair.chunkXZ2Int(var2, var3))) {
         this.b.nextInt();

         try {
            if (this.canSpawnStructureAtCoords(var2, var3)) {
               StructureStart var7 = this.getStructureStart(var2, var3);
               this.structureMap.put(ChunkCoordIntPair.chunkXZ2Int(var2, var3), var7);
               this.setStructureStart(var2, var3, var7);
            }
         } catch (Throwable var10) {
            CrashReport var8 = CrashReport.makeCrashReport(var10, "Exception preparing structure feature");
            CrashReportCategory var9 = var8.makeCategory("Feature being prepared");
            var9.addCrashSectionCallable("Is feature chunk", new Callable<String>() {
               public String call() throws java.lang.Exception {
                  return MapGenStructure.this.canSpawnStructureAtCoords(var2, var3) ? "True" : "False";
               }
            });
            var9.addCrashSection("Chunk location", String.format("%d,%d", var2, var3));
            var9.addCrashSectionCallable("Chunk pos hash", new Callable<String>() {
               public String call() throws java.lang.Exception {
                  return String.valueOf(ChunkCoordIntPair.chunkXZ2Int(var2, var3));
               }
            });
            var9.addCrashSectionCallable("Structure type", new Callable<String>() {
               public String call() throws java.lang.Exception {
                  return MapGenStructure.this.getClass().getCanonicalName();
               }
            });
            throw new ReportedException(var8);
         }
      }
   }

   public List<BlockPos> D_() {
      return null;
   }

   public BlockPos getClosestStrongholdPos(World var1, BlockPos var2) {
      this.c = var1;
      this.initializeStructureData(var1);
      this.b.setSeed(var1.J());
      long var3 = this.b.nextLong();
      long var5 = this.b.nextLong();
      long var7 = (var2.getX() >> 4) * var3;
      long var9 = (var2.getZ() >> 4) * var5;
      this.b.setSeed(var7 ^ var9 ^ var1.J());
      this.recursiveGenerate(var1, var2.getX() >> 4, var2.getZ() >> 4, 0, 0, (ChunkPrimer)null);
      double var11 = Double.MAX_VALUE;
      BlockPos var13 = null;

      for (StructureStart var15 : this.structureMap.values()) {
         if (var15.isSizeableStructure()) {
            StructureComponent var16 = var15.getComponents().get(0);
            BlockPos var17 = var16.getBoundingBoxCenter();
            double var18 = var17.distanceSq(var2);
            if (var18 < var11) {
               var11 = var18;
               var13 = var17;
            }
         }
      }

      if (var13 != null) {
         return var13;
      } else {
         List var20 = this.D_();
         if (var20 != null) {
            BlockPos var21 = null;

            for (BlockPos var23 : (Iterable<BlockPos>)(Iterable<?>)(var20)) {
               double var24 = var23.distanceSq(var2);
               if (var24 < var11) {
                  var11 = var24;
                  var21 = var23;
               }
            }

            return var21;
         } else {
            return null;
         }
      }
   }

   public abstract String getStructureName();

   public boolean isPositionInStructure(World var1, BlockPos var2) {
      this.initializeStructureData(var1);

      for (StructureStart var4 : this.structureMap.values()) {
         if (var4.isSizeableStructure() && var4.getBoundingBox().isVecInside(var2)) {
            return true;
         }
      }

      return false;
   }

   public abstract StructureStart getStructureStart(int var1, int var2);

   public boolean func_175795_b(BlockPos var1) {
      this.initializeStructureData(this.c);
      return this.func_175797_c(var1) != null;
   }

   public abstract boolean canSpawnStructureAtCoords(int var1, int var2);
}
