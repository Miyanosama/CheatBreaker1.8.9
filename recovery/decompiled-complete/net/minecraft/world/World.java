package net.minecraft.world;

import com.cheatbreaker.client.CheatBreaker;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.netty.handler.codec.http.websocketx.Utf8Validator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab$EnumBlockHalf;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStairs$EnumHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.Chunk$EnumCreateEntityType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.ConnectedTexturesCompact$Dir;

public abstract class World implements IBlockAccess {
   public WorldProvider t;
   public int ambientTickCountdown;
   public Scoreboard C;
   public float o;
   public Calendar field_0005;
   public boolean spawnPeacefulMobs;
   public IChunkProvider v;
   public int[] lightUpdateBlockList;
   public List<TileEntity> addedTileEntityList;
   public boolean D;
   public int field_0006 = 63;
   public MapStorage z;
   public List<EntityPlayer> j;
   public List<Entity> weatherEffects;
   public int updateLCG;
   public WorldInfo x;
   public boolean findingSpawnPoint;
   public List<Entity> g;
   public int field_0015;
   public int lastLightningBolt;
   public List<TileEntity> field_0019;
   public long cloudColour;
   public List<IWorldAccess> worldAccesses;
   public boolean spawnHostileMobs;
   public ISaveHandler w;
   public int skylightSubtracted;
   public float q;
   public Set<ChunkCoordIntPair> E;
   public boolean e;
   public Random s;
   public float r;
   public IntHashMap<Entity> l;
   public WorldBorder field_0009;
   public List<TileEntity> tickableTileEntities;
   public ConnectedTexturesCompact$Dir field_0026;
   public Utf8Validator field_0029;
   public float p;
   public List<TileEntity> h;
   public boolean processingLoadedTiles;
   public Profiler B;
   public List<Entity> f = Lists.newArrayList();
   public VillageCollection A;

   public List<NextTickListEntry> getPendingBlockUpdates(Chunk var1, boolean var2) {
      return null;
   }

   public boolean isBlockPowered(BlockPos var1) {
      return this.getRedstonePower(var1.down(), EnumFacing.DOWN) > 0
         ? true
         : (
            this.getRedstonePower(var1.up(), EnumFacing.UP) > 0
               ? true
               : (
                  this.getRedstonePower(var1.north(), EnumFacing.NORTH) > 0
                     ? true
                     : (
                        this.getRedstonePower(var1.south(), EnumFacing.SOUTH) > 0
                           ? true
                           : (this.getRedstonePower(var1.west(), EnumFacing.WEST) > 0 ? true : this.getRedstonePower(var1.east(), EnumFacing.EAST) > 0)
                     )
               )
         );
   }

   public void updateComparatorOutputLevel(BlockPos var1, Block var2) {
      for (EnumFacing var4 : EnumFacing$Plane.HORIZONTAL) {
         BlockPos var5 = var1.a(var4);
         if (this.e(var5)) {
            IBlockState var6 = this.getBlockState(var5);
            if (Blocks.unpowered_comparator.isAssociated(var6.getBlock())) {
               var6.getBlock().onNeighborBlockChange(this, var5, var6, var2);
            } else if (var6.getBlock().isNormalCube()) {
               var5 = var5.a(var4);
               var6 = this.getBlockState(var5);
               if (Blocks.unpowered_comparator.isAssociated(var6.getBlock())) {
                  var6.getBlock().onNeighborBlockChange(this, var5, var6, var2);
               }
            }
         }
      }
   }

   public WorldInfo P() {
      return this.x;
   }

   public void sendBlockBreakProgress(int var1, BlockPos var2, int var3) {
      for (int var4 = 0; var4 < this.worldAccesses.size(); var4++) {
         IWorldAccess var5 = this.worldAccesses.get(var4);
         var5.sendBlockBreakProgress(var1, var2, var3);
      }
   }

   public BlockPos getPrecipitationHeight(BlockPos var1) {
      return this.getChunkFromBlockCoords(var1).getPrecipitationHeight(var1);
   }

   public boolean isAreaLoaded(BlockPos var1, BlockPos var2) {
      return this.isAreaLoaded(var1, var2, true);
   }

   public MovingObjectPosition rayTraceBlocks(Vec3 var1, Vec3 var2) {
      return this.rayTraceBlocks(var1, var2, false, false, false);
   }

   public boolean canSeeSky(BlockPos var1) {
      return this.getChunkFromBlockCoords(var1).canSeeSky(var1);
   }

   public Random setRandomSeed(int var1, int var2, int var3) {
      long var4 = var1 * (343018173720L & 8930829717980577161L) + var2 * (132967201789L & 132939930581L) + this.P().getSeed() + var3;
      this.s.setSeed(var4);
      return this.s;
   }

   public boolean isWater(BlockPos var1) {
      return this.getBlockState(var1).getBlock().getMaterial() == Material.water;
   }

   public void unloadEntities(Collection<Entity> var1) {
      this.g.addAll(var1);
   }

   public String getProviderName() {
      return this.v.makeString();
   }

   public void B(BlockPos var1) {
      this.x.setSpawn(var1);
   }

   public <T extends Entity> List<T> getEntitiesWithinAABB(Class<? extends T> var1, AxisAlignedBB var2) {
      return this.getEntitiesWithinAABB(var1, var2, EntitySelectors.NOT_SPECTATING);
   }

   public WorldSavedData loadItemData(Class<? extends WorldSavedData> var1, String var2) {
      return this.z.loadData(var1, var2);
   }

   public List<NextTickListEntry> func_175712_a(StructureBoundingBox var1, boolean var2) {
      return null;
   }

   public float getStarBrightness(float var1) {
      float var2 = this.getCelestialAngle(var1);
      float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);
      var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
      return var3 * var3 * 0.5F;
   }

   public GameRules Q() {
      return this.x.getGameRulesInstance();
   }

   public void initialize(WorldSettings var1) {
      this.x.setServerInitialized(true);
   }

   public int calculateSkylightSubtracted(float var1) {
      float var2 = this.getCelestialAngle(var1);
      float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.5F);
      var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
      var3 = 1.0F - var3;
      var3 = (float)(var3 * (1.0 - this.j(var1) * 5.0F / 16.0));
      var3 = (float)(var3 * (1.0 - this.h(var1) * 5.0F / 16.0));
      var3 = 1.0F - var3;
      return (int)(var3 * 11.0F);
   }

   public boolean isBlockModifiable(EntityPlayer var1, BlockPos var2) {
      return true;
   }

   public long K() {
      return this.x.getWorldTotalTime();
   }

   public <T extends Entity> List<T> method_09943(Class<? extends T> var1, Predicate<? super T> var2) {
      ArrayList var3 = Lists.newArrayList();

      for (Entity var5 : this.f) {
         if (var1.isAssignableFrom(var5.getClass()) && var2.apply(var5)) {
            var3.add(var5);
         }
      }

      return var3;
   }

   public int getActualHeight() {
      return this.t.getHasNoSky() ? 128 : 256;
   }

   public void updateEntity(Entity var1) {
      this.updateEntityWithOptionalForce(var1, true);
   }

   public void notifyNeighborsOfStateChange(BlockPos var1, Block var2) {
      this.notifyBlockOfStateChange(var1.west(), var2);
      this.notifyBlockOfStateChange(var1.east(), var2);
      this.notifyBlockOfStateChange(var1.down(), var2);
      this.notifyBlockOfStateChange(var1.up(), var2);
      this.notifyBlockOfStateChange(var1.north(), var2);
      this.notifyBlockOfStateChange(var1.south(), var2);
   }

   public boolean isAnyPlayerWithinRangeAt(double var1, double var3, double var5, double var7) {
      for (int var9 = 0; var9 < this.j.size(); var9++) {
         EntityPlayer var10 = this.j.get(var9);
         if (EntitySelectors.NOT_SPECTATING.apply(var10)) {
            double var11 = var10.e(var1, var3, var5);
            if (var7 < 0.0 || var11 < var7 * var7) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean tickUpdates(boolean var1) {
      return false;
   }

   public boolean isBlockLoaded(BlockPos var1, boolean var2) {
      return !this.isValid(var1) ? false : this.a(var1.getX() >> 4, var1.getZ() >> 4, var2);
   }

   public boolean spawnEntityInWorld(Entity var1) {
      int var2 = MathHelper.floor_double(var1.s / 16.0);
      int var3 = MathHelper.floor_double(var1.u / 16.0);
      boolean var4 = var1.n;
      if (var1 instanceof EntityPlayer) {
         var4 = true;
      }

      if (!var4 && !this.a(var2, var3, true)) {
         return false;
      } else {
         if (var1 instanceof EntityPlayer) {
            EntityPlayer var5 = (EntityPlayer)var1;
            this.j.add(var5);
            this.updateAllPlayersSleepingFlag();
         }

         this.a(var2, var3).addEntity(var1);
         this.f.add(var1);
         this.onEntityAdded(var1);
         return true;
      }
   }

   public boolean isFindingSpawnPoint() {
      return this.findingSpawnPoint;
   }

   public void notifyNeighborsRespectDebug(BlockPos var1, Block var2) {
      if (this.x.getTerrainType() != WorldType.DEBUG_WORLD) {
         this.notifyNeighborsOfStateChange(var1, var2);
      }
   }

   public void C() {
      if (this.x.isRaining()) {
         this.p = 1.0F;
         if (this.x.isThundering()) {
            this.r = 1.0F;
         }
      }
   }

   public int getLightFromNeighborsFor(EnumSkyBlock var1, BlockPos var2) {
      if (this.t.getHasNoSky() && var1 == EnumSkyBlock.SKY) {
         return 0;
      } else {
         if (var2.getY() < 0) {
            var2 = new BlockPos(var2.getX(), 0, var2.getZ());
         }

         if (!this.isValid(var2)) {
            return var1.defaultLightValue;
         } else if (!this.e(var2)) {
            return var1.defaultLightValue;
         } else if (this.getBlockState(var2).getBlock().getUseNeighborBrightness()) {
            int var8 = this.getLightFor(var1, var2.up());
            int var4 = this.getLightFor(var1, var2.east());
            int var5 = this.getLightFor(var1, var2.west());
            int var6 = this.getLightFor(var1, var2.south());
            int var7 = this.getLightFor(var1, var2.north());
            if (var4 > var8) {
               var8 = var4;
            }

            if (var5 > var8) {
               var8 = var5;
            }

            if (var6 > var8) {
               var8 = var6;
            }

            if (var7 > var8) {
               var8 = var7;
            }

            return var8;
         } else {
            Chunk var3 = this.getChunkFromBlockCoords(var2);
            return var3.getLightFor(var1, var2);
         }
      }
   }

   public double getHorizon() {
      return this.x.getTerrainType() == WorldType.FLAT ? 0.0 : 63.0;
   }

   public boolean canBlockFreeze(BlockPos var1, boolean var2) {
      BiomeGenBase var3 = this.getBiomeGenForCoords(var1);
      float var4 = var3.getFloatTemperature(var1);
      if (var4 > 0.15F) {
         return false;
      } else {
         if (var1.getY() >= 0 && var1.getY() < 256 && this.getLightFor(EnumSkyBlock.BLOCK, var1) < 10) {
            IBlockState var5 = this.getBlockState(var1);
            Block var6 = var5.getBlock();
            if ((var6 == Blocks.water || var6 == Blocks.flowing_water) && var5.getValue(BlockLiquid.b) == 0) {
               if (!var2) {
                  return true;
               }

               boolean var7 = this.isWater(var1.west()) && this.isWater(var1.east()) && this.isWater(var1.north()) && this.isWater(var1.south());
               if (!var7) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean canBlockFreezeWater(BlockPos var1) {
      return this.canBlockFreeze(var1, false);
   }

   public abstract IChunkProvider createChunkProvider();

   public long J() {
      return this.x.getSeed();
   }

   public boolean isMaterialInBB(AxisAlignedBB var1, Material var2) {
      int var3 = MathHelper.floor_double(var1.a);
      int var4 = MathHelper.floor_double(var1.d + 1.0);
      int var5 = MathHelper.floor_double(var1.b);
      int var6 = MathHelper.floor_double(var1.e + 1.0);
      int var7 = MathHelper.floor_double(var1.c);
      int var8 = MathHelper.floor_double(var1.f + 1.0);
      BlockPos$MutableBlockPos var9 = new BlockPos$MutableBlockPos();

      for (int var10 = var3; var10 < var4; var10++) {
         for (int var11 = var5; var11 < var6; var11++) {
            for (int var12 = var7; var12 < var8; var12++) {
               if (this.getBlockState(var9.set(var10, var11, var12)).getBlock().getMaterial() == var2) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public static boolean doesBlockHaveSolidTopSurface(IBlockAccess var0, BlockPos var1) {
      IBlockState var2 = var0.getBlockState(var1);
      Block var3 = var2.getBlock();
      return var3.getMaterial().isOpaque() && var3.isFullCube()
         ? true
         : (
            var3 instanceof BlockStairs
               ? var2.getValue(BlockStairs.HALF) == BlockStairs$EnumHalf.TOP
               : (
                  var3 instanceof BlockSlab
                     ? var2.getValue(BlockSlab.a) == BlockSlab$EnumBlockHalf.TOP
                     : (var3 instanceof BlockHopper ? true : (var3 instanceof BlockSnow ? var2.getValue(BlockSnow.LAYERS) == 7 : false))
               )
         );
   }

   public void setActivePlayerChunksAndCheckLight() {
      this.E.clear();
      this.B.startSection("buildList");

      for (int var1 = 0; var1 < this.j.size(); var1++) {
         EntityPlayer var2 = this.j.get(var1);
         int var3 = MathHelper.floor_double(var2.s / 16.0);
         int var4 = MathHelper.floor_double(var2.u / 16.0);
         int var5 = this.getRenderDistanceChunks();

         for (int var6 = -var5; var6 <= var5; var6++) {
            for (int var7 = -var5; var7 <= var5; var7++) {
               this.E.add(new ChunkCoordIntPair(var6 + var3, var7 + var4));
            }
         }
      }

      this.B.endSection();
      if (this.ambientTickCountdown > 0) {
         this.ambientTickCountdown--;
      }

      this.B.startSection("playerCheckLight");
      if (!this.j.isEmpty()) {
         int var8 = this.s.nextInt(this.j.size());
         EntityPlayer var9 = this.j.get(var8);
         int var10 = MathHelper.floor_double(var9.s) + this.s.nextInt(11) - 5;
         int var11 = MathHelper.floor_double(var9.t) + this.s.nextInt(11) - 5;
         int var12 = MathHelper.floor_double(var9.u) + this.s.nextInt(11) - 5;
         this.checkLight(new BlockPos(var10, var11, var12));
      }

      this.B.endSection();
   }

   public boolean isAreaLoaded(int var1, int var2, int var3, int var4, int var5, int var6, boolean var7) {
      if (var5 >= 0 && var2 < 256) {
         var1 >>= 4;
         var3 >>= 4;
         var4 >>= 4;
         var6 >>= 4;

         for (int var8 = var1; var8 <= var4; var8++) {
            for (int var9 = var3; var9 <= var6; var9++) {
               if (!this.a(var8, var9, var7)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isAreaLoaded(BlockPos var1, int var2) {
      return this.isAreaLoaded(var1, var2, true);
   }

   @Override
   public boolean w_() {
      return false;
   }

   public WorldChunkManager getWorldChunkManager() {
      return this.t.getWorldChunkManager();
   }

   public boolean canBlockSeeSky(BlockPos var1) {
      if (var1.getY() >= this.F()) {
         return this.canSeeSky(var1);
      } else {
         BlockPos var2 = new BlockPos(var1.getX(), this.F(), var1.getZ());
         if (!this.canSeeSky(var2)) {
            return false;
         } else {
            for (BlockPos var4 = var2.down(); var4.getY() > var1.getY(); var4 = var4.down()) {
               Block var3 = this.getBlockState(var4).getBlock();
               if (var3.getLightOpacity() > 0 && !var3.getMaterial().isLiquid()) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public boolean canSnowAt(BlockPos var1, boolean var2) {
      BiomeGenBase var3 = this.getBiomeGenForCoords(var1);
      float var4 = var3.getFloatTemperature(var1);
      if (var4 > 0.15F) {
         return false;
      } else if (!var2) {
         return true;
      } else {
         if (var1.getY() >= 0 && var1.getY() < 256 && this.getLightFor(EnumSkyBlock.BLOCK, var1) < 10) {
            Block var5 = this.getBlockState(var1).getBlock();
            if (var5.getMaterial() == Material.air && Blocks.snow_layer.canPlaceBlockAt(this, var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public void updateAllPlayersSleepingFlag() {
   }

   public void removeEntity(Entity var1) {
      if (var1.l != null) {
         var1.l.mountEntity((Entity)null);
      }

      if (var1.m != null) {
         var1.mountEntity((Entity)null);
      }

      var1.setDead();
      if (var1 instanceof EntityPlayer) {
         this.j.remove(var1);
         this.updateAllPlayersSleepingFlag();
         this.onEntityRemoved(var1);
      }
   }

   public boolean isAreaLoaded(BlockPos var1, BlockPos var2, boolean var3) {
      return this.isAreaLoaded(var1.getX(), var1.getY(), var1.getZ(), var2.getX(), var2.getY(), var2.getZ(), var3);
   }

   public void onEntityRemoved(Entity var1) {
      for (int var2 = 0; var2 < this.worldAccesses.size(); var2++) {
         this.worldAccesses.get(var2).onEntityRemoved(var1);
      }
   }

   public boolean checkLightFor(EnumSkyBlock var1, BlockPos var2) {
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0087.getValue()
         && (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0027.getValue()) {
         return true;
      } else if (!this.isAreaLoaded(var2, 17, false)) {
         return false;
      } else {
         int var3 = 0;
         int var4 = 0;
         this.B.startSection("getBrightness");
         int var5 = this.getLightFor(var1, var2);
         int var6 = this.getRawLight(var2, var1);
         int var7 = var2.getX();
         int var8 = var2.getY();
         int var9 = var2.getZ();
         if (var6 > var5) {
            this.lightUpdateBlockList[var4++] = 133152;
         } else if (var6 < var5) {
            this.lightUpdateBlockList[var4++] = 133152 | var5 << 18;

            while (var3 < var4) {
               int var10 = this.lightUpdateBlockList[var3++];
               int var11 = (var10 & 63) - 32 + var7;
               int var12 = (var10 >> 6 & 63) - 32 + var8;
               int var13 = (var10 >> 12 & 63) - 32 + var9;
               int var14 = var10 >> 18 & 15;
               BlockPos var15 = new BlockPos(var11, var12, var13);
               int var16 = this.getLightFor(var1, var15);
               if (var16 == var14) {
                  this.setLightFor(var1, var15, 0);
                  if (var14 > 0) {
                     int var17 = MathHelper.abs_int(var11 - var7);
                     int var18 = MathHelper.abs_int(var12 - var8);
                     int var19 = MathHelper.abs_int(var13 - var9);
                     if (var17 + var18 + var19 < 17) {
                        BlockPos$MutableBlockPos var20 = new BlockPos$MutableBlockPos();

                        for (EnumFacing var24 : EnumFacing.values()) {
                           int var25 = var11 + var24.getFrontOffsetX();
                           int var26 = var12 + var24.getFrontOffsetY();
                           int var27 = var13 + var24.getFrontOffsetZ();
                           var20.set(var25, var26, var27);
                           int var28 = Math.max(1, this.getBlockState(var20).getBlock().getLightOpacity());
                           var16 = this.getLightFor(var1, var20);
                           if (var16 == var14 - var28 && var4 < this.lightUpdateBlockList.length) {
                              this.lightUpdateBlockList[var4++] = var25 - var7 + 32 | var26 - var8 + 32 << 6 | var27 - var9 + 32 << 12 | var14 - var28 << 18;
                           }
                        }
                     }
                  }
               }
            }

            var3 = 0;
         }

         this.B.endSection();
         this.B.startSection("checkedPosition < toCheckCount");

         while (var3 < var4) {
            int var29 = this.lightUpdateBlockList[var3++];
            int var30 = (var29 & 63) - 32 + var7;
            int var31 = (var29 >> 6 & 63) - 32 + var8;
            int var32 = (var29 >> 12 & 63) - 32 + var9;
            BlockPos var33 = new BlockPos(var30, var31, var32);
            int var34 = this.getLightFor(var1, var33);
            int var36 = this.getRawLight(var33, var1);
            if (var36 != var34) {
               this.setLightFor(var1, var33, var36);
               if (var36 > var34) {
                  int var37 = Math.abs(var30 - var7);
                  int var38 = Math.abs(var31 - var8);
                  int var39 = Math.abs(var32 - var9);
                  boolean var40 = var4 < this.lightUpdateBlockList.length - 6;
                  if (var37 + var38 + var39 < 17 && var40) {
                     if (this.getLightFor(var1, var33.west()) < var36) {
                        this.lightUpdateBlockList[var4++] = var30 - 1 - var7 + 32 + (var31 - var8 + 32 << 6) + (var32 - var9 + 32 << 12);
                     }

                     if (this.getLightFor(var1, var33.east()) < var36) {
                        this.lightUpdateBlockList[var4++] = var30 + 1 - var7 + 32 + (var31 - var8 + 32 << 6) + (var32 - var9 + 32 << 12);
                     }

                     if (this.getLightFor(var1, var33.down()) < var36) {
                        this.lightUpdateBlockList[var4++] = var30 - var7 + 32 + (var31 - 1 - var8 + 32 << 6) + (var32 - var9 + 32 << 12);
                     }

                     if (this.getLightFor(var1, var33.up()) < var36) {
                        this.lightUpdateBlockList[var4++] = var30 - var7 + 32 + (var31 + 1 - var8 + 32 << 6) + (var32 - var9 + 32 << 12);
                     }

                     if (this.getLightFor(var1, var33.north()) < var36) {
                        this.lightUpdateBlockList[var4++] = var30 - var7 + 32 + (var31 - var8 + 32 << 6) + (var32 - 1 - var9 + 32 << 12);
                     }

                     if (this.getLightFor(var1, var33.south()) < var36) {
                        this.lightUpdateBlockList[var4++] = var30 - var7 + 32 + (var31 - var8 + 32 << 6) + (var32 + 1 - var9 + 32 << 12);
                     }
                  }
               }
            }
         }

         this.B.endSection();
         return true;
      }
   }

   public void playAuxSFXAtEntity(EntityPlayer var1, int var2, BlockPos var3, int var4) {
      try {
         for (int var5 = 0; var5 < this.worldAccesses.size(); var5++) {
            this.worldAccesses.get(var5).playAuxSFX(var1, var2, var3, var4);
         }
      } catch (Throwable var8) {
         CrashReport var6 = CrashReport.makeCrashReport(var8, "Playing level event");
         CrashReportCategory var7 = var6.makeCategory("Level event being played");
         var7.addCrashSection("Block coordinates", CrashReportCategory.getCoordinateInfo(var3));
         var7.addCrashSection("Event source", var1);
         var7.addCrashSection("Event type", var2);
         var7.addCrashSection("Event data", var4);
         throw new ReportedException(var6);
      }
   }

   public List<Entity> getEntitiesWithinAABBExcludingEntity(Entity var1, AxisAlignedBB var2) {
      return this.a(var1, var2, EntitySelectors.NOT_SPECTATING);
   }

   public boolean setBlockState(BlockPos var1, IBlockState var2) {
      return this.a(var1, var2, 3);
   }

   @Override
   public BiomeGenBase getBiomeGenForCoords(BlockPos var1) {
      if (this.e(var1)) {
         Chunk var2 = this.getChunkFromBlockCoords(var1);

         try {
            return var2.getBiome(var1, this.t.getWorldChunkManager());
         } catch (Throwable var6) {
            CrashReport var4 = CrashReport.makeCrashReport(var6, "Getting biome");
            CrashReportCategory var5 = var4.makeCategory("Coordinates of biome request");
            var5.addCrashSectionCallable("Location", new World$1(this, var1));
            throw new ReportedException(var4);
         }
      } else {
         return this.t.getWorldChunkManager().getBiomeGenerator(var1, BiomeGenBase.plains);
      }
   }

   public BlockPos getTopSolidOrLiquidBlock(BlockPos var1) {
      Chunk var2 = this.getChunkFromBlockCoords(var1);
      BlockPos var3 = new BlockPos(var1.getX(), var2.getTopFilledSegment() + 16, var1.getZ());

      while (var3.getY() >= 0) {
         BlockPos var4 = var3.down();
         Material var5 = var2.getBlock(var4).getMaterial();
         if (var5.blocksMovement() && var5 != Material.leaves) {
            break;
         }

         var3 = var4;
      }

      return var3;
   }

   public void b(int var1, BlockPos var2, int var3) {
      this.playAuxSFXAtEntity((EntityPlayer)null, var1, var2, var3);
   }

   public long L() {
      return this.x.getWorldTime();
   }

   public List<AxisAlignedBB> a(Entity var1, AxisAlignedBB var2) {
      ArrayList var3 = Lists.newArrayList();
      int var4 = MathHelper.floor_double(var2.a);
      int var5 = MathHelper.floor_double(var2.d + 1.0);
      int var6 = MathHelper.floor_double(var2.b);
      int var7 = MathHelper.floor_double(var2.e + 1.0);
      int var8 = MathHelper.floor_double(var2.c);
      int var9 = MathHelper.floor_double(var2.f + 1.0);
      WorldBorder var10 = this.af();
      boolean var11 = var1.isOutsideBorder();
      boolean var12 = this.isInsideBorder(var10, var1);
      IBlockState var13 = Blocks.stone.getDefaultState();
      BlockPos$MutableBlockPos var14 = new BlockPos$MutableBlockPos();

      for (int var15 = var4; var15 < var5; var15++) {
         for (int var16 = var8; var16 < var9; var16++) {
            if (this.e(var14.set(var15, 64, var16))) {
               for (int var17 = var6 - 1; var17 < var7; var17++) {
                  var14.set(var15, var17, var16);
                  if (var11 && var12) {
                     var1.setOutsideBorder(false);
                  } else if (!var11 && !var12) {
                     var1.setOutsideBorder(true);
                  }

                  IBlockState var18 = var13;
                  if (var10.contains(var14) || !var12) {
                     var18 = this.getBlockState(var14);
                  }

                  var18.getBlock().addCollisionBoxesToList(this, var14, var18, var2, var3, var1);
               }
            }
         }
      }

      double var20 = 0.25;
      List var21 = this.getEntitiesWithinAABBExcludingEntity(var1, var2.expand(var20, var20, var20));

      for (int var22 = 0; var22 < var21.size(); var22++) {
         if (var1.l != var21 && var1.m != var21) {
            AxisAlignedBB var19 = ((Entity)var21.get(var22)).t_();
            if (var19 != null && var19.intersectsWith(var2)) {
               var3.add(var19);
            }

            var19 = var1.getCollisionBox((Entity)var21.get(var22));
            if (var19 != null && var19.intersectsWith(var2)) {
               var3.add(var19);
            }
         }
      }

      return var3;
   }

   public void notifyBlockOfStateChange(BlockPos var1, Block var2) {
      if (!this.D) {
         IBlockState var3 = this.getBlockState(var1);

         try {
            var3.getBlock().onNeighborBlockChange(this, var1, var3, var2);
         } catch (Throwable var7) {
            CrashReport var5 = CrashReport.makeCrashReport(var7, "Exception while updating neighbours");
            CrashReportCategory var6 = var5.makeCategory("Block being updated");
            var6.addCrashSectionCallable("Source block type", new World$2(this, var2));
            CrashReportCategory.addBlockInfo(var6, var1, var3);
            throw new ReportedException(var5);
         }
      }
   }

   public void makeFireworks(double var1, double var3, double var5, double var7, double var9, double var11, NBTTagCompound var13) {
   }

   @Override
   public int getCombinedLight(BlockPos var1, int var2) {
      int var3 = this.getLightFromNeighborsFor(EnumSkyBlock.SKY, var1);
      int var4 = this.getLightFromNeighborsFor(EnumSkyBlock.BLOCK, var1);
      if (var4 < var2) {
         var4 = var2;
      }

      return var3 << 20 | var4 << 4;
   }

   public void notifyNeighborsOfStateExcept(BlockPos var1, Block var2, EnumFacing var3) {
      if (var3 != EnumFacing.WEST) {
         this.notifyBlockOfStateChange(var1.west(), var2);
      }

      if (var3 != EnumFacing.EAST) {
         this.notifyBlockOfStateChange(var1.east(), var2);
      }

      if (var3 != EnumFacing.DOWN) {
         this.notifyBlockOfStateChange(var1.down(), var2);
      }

      if (var3 != EnumFacing.UP) {
         this.notifyBlockOfStateChange(var1.up(), var2);
      }

      if (var3 != EnumFacing.NORTH) {
         this.notifyBlockOfStateChange(var1.north(), var2);
      }

      if (var3 != EnumFacing.SOUTH) {
         this.notifyBlockOfStateChange(var1.south(), var2);
      }
   }

   public boolean checkNoEntityCollision(AxisAlignedBB var1) {
      return this.checkNoEntityCollision(var1, (Entity)null);
   }

   public void playBroadcastSound(int var1, BlockPos var2, int var3) {
      for (int var4 = 0; var4 < this.worldAccesses.size(); var4++) {
         this.worldAccesses.get(var4).broadcastSound(var1, var2, var3);
      }
   }

   public boolean addTileEntity(TileEntity var1) {
      boolean var2 = this.h.add(var1);
      if (var2 && var1 instanceof ITickable) {
         this.tickableTileEntities.add(var1);
      }

      return var2;
   }

   public void setLastLightningBolt(int var1) {
      this.lastLightningBolt = var1;
   }

   public boolean method_09969(BlockPos var1) {
      return this.canBlockFreeze(var1, true);
   }

   public void spawnParticle(EnumParticleTypes var1, double var2, double var4, double var6, double var8, double var10, double var12, int... var14) {
      this.spawnParticle(var1.getParticleID(), var1.getShouldIgnoreRange(), var2, var4, var6, var8, var10, var12, var14);
   }

   public void method_05035() {
   }

   public void updateBlocks() {
      this.setActivePlayerChunksAndCheckLight();
   }

   public void markBlockRangeForRenderUpdate(BlockPos var1, BlockPos var2) {
      this.markBlockRangeForRenderUpdate(var1.getX(), var1.getY(), var1.getZ(), var2.getX(), var2.getY(), var2.getZ());
   }

   public void addTileEntities(Collection<TileEntity> var1) {
      if (this.processingLoadedTiles) {
         this.addedTileEntityList.addAll(var1);
      } else {
         for (TileEntity var3 : var1) {
            this.h.add(var3);
            if (var3 instanceof ITickable) {
               this.tickableTileEntities.add(var3);
            }
         }
      }
   }

   public boolean canBlockBePlaced(Block var1, BlockPos var2, boolean var3, EnumFacing var4, Entity var5, ItemStack var6) {
      Block var7 = this.getBlockState(var2).getBlock();
      AxisAlignedBB var8 = var3 ? null : var1.getCollisionBoundingBox(this, var2, var1.getDefaultState());
      return var8 != null && !this.checkNoEntityCollision(var8, var5)
         ? false
         : (
            var7.getMaterial() == Material.circuits && var1 == Blocks.anvil
               ? true
               : var7.getMaterial().isReplaceable() && var1.canReplace(this, var2, var4, var6)
         );
   }

   public Scoreboard Z() {
      return this.C;
   }

   public EntityPlayer method_09945(String var1) {
      for (int var2 = 0; var2 < this.j.size(); var2++) {
         EntityPlayer var3 = this.j.get(var2);
         if (var3.isEntityAlive()
            && (var1.equals(var3.getGameProfile().getId().toString()) || var1.equals(var3.getGameProfile().getId().toString().replaceAll("-", "")))) {
            return var3;
         }
      }

      return null;
   }

   public World(ISaveHandler var1, WorldInfo var2, WorldProvider var3, Profiler var4, boolean var5) {
      this.g = Lists.newArrayList();
      this.h = Lists.newArrayList();
      this.tickableTileEntities = Lists.newArrayList();
      this.addedTileEntityList = Lists.newArrayList();
      this.field_0019 = Lists.newArrayList();
      this.j = Lists.newArrayList();
      this.weatherEffects = Lists.newArrayList();
      this.l = new IntHashMap<>();
      this.cloudColour = 33554431L & 318767103L;
      this.updateLCG = new Random().nextInt();
      this.field_0015 = 1013904223;
      this.s = new Random();
      this.worldAccesses = Lists.newArrayList();
      this.field_0005 = Calendar.getInstance();
      this.C = new Scoreboard();
      this.E = Sets.newHashSet();
      this.ambientTickCountdown = this.s.nextInt(12000);
      this.spawnHostileMobs = true;
      this.spawnPeacefulMobs = true;
      this.lightUpdateBlockList = new int[32768];
      this.w = var1;
      this.B = var4;
      this.x = var2;
      this.t = var3;
      this.D = var5;
      this.field_0009 = var3.getWorldBorder();
   }

   public void i(float var1) {
      this.q = var1;
      this.r = var1;
   }

   public void removeTileEntity(BlockPos var1) {
      TileEntity var2 = this.getTileEntity(var1);
      if (var2 != null && this.processingLoadedTiles) {
         var2.invalidate();
         this.addedTileEntityList.remove(var2);
      } else {
         if (var2 != null) {
            this.addedTileEntityList.remove(var2);
            this.h.remove(var2);
            this.tickableTileEntities.remove(var2);
         }

         this.getChunkFromBlockCoords(var1).removeTileEntity(var1);
      }
   }

   public boolean isBlockFullCube(BlockPos var1) {
      IBlockState var2 = this.getBlockState(var1);
      AxisAlignedBB var3 = var2.getBlock().getCollisionBoundingBox(this, var1, var2);
      return var3 != null && var3.getAverageEdgeLength() >= 1.0;
   }

   public void markBlocksDirtyVertical(int var1, int var2, int var3, int var4) {
      if (var3 > var4) {
         int var5 = var4;
         var4 = var3;
         var3 = var5;
      }

      if (!this.t.getHasNoSky()) {
         for (int var6 = var3; var6 <= var4; var6++) {
            this.checkLightFor(EnumSkyBlock.SKY, new BlockPos(var1, var6, var2));
         }
      }

      this.markBlockRangeForRenderUpdate(var1, var3, var2, var1, var4, var2);
   }

   public boolean isSidePowered(BlockPos var1, EnumFacing var2) {
      return this.getRedstonePower(var1, var2) > 0;
   }

   public boolean destroyBlock(BlockPos var1, boolean var2) {
      IBlockState var3 = this.getBlockState(var1);
      Block var4 = var3.getBlock();
      if (var4.getMaterial() == Material.air) {
         return false;
      } else {
         this.b(2001, var1, Block.getStateId(var3));
         if (var2) {
            var4.dropBlockAsItem(this, var1, var3, 0);
         }

         return this.a(var1, Blocks.air.getDefaultState(), 3);
      }
   }

   public void removeWorldAccess(IWorldAccess var1) {
      this.worldAccesses.remove(var1);
   }

   public int getLightFor(EnumSkyBlock var1, BlockPos var2) {
      if (var2.getY() < 0) {
         var2 = new BlockPos(var2.getX(), 0, var2.getZ());
      }

      if (!this.isValid(var2)) {
         return var1.defaultLightValue;
      } else if (!this.e(var2)) {
         return var1.defaultLightValue;
      } else {
         Chunk var3 = this.getChunkFromBlockCoords(var2);
         return var3.getLightFor(var1, var2);
      }
   }

   public abstract int getRenderDistanceChunks();

   public int getMoonPhase() {
      return this.t.getMoonPhase(this.x.getWorldTime());
   }

   public EntityPlayer getPlayerEntityByName(String var1) {
      for (int var2 = 0; var2 < this.j.size(); var2++) {
         EntityPlayer var3 = this.j.get(var2);
         if (var1.equals(var3.z_())) {
            return var3;
         }
      }

      return null;
   }

   public Vec3 getSkyColor(Entity var1, float var2) {
      float var3 = this.getCelestialAngle(var2);
      float var4 = MathHelper.cos(var3 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      var4 = MathHelper.clamp_float(var4, 0.0F, 1.0F);
      int var5 = MathHelper.floor_double(var1.s);
      int var6 = MathHelper.floor_double(var1.t);
      int var7 = MathHelper.floor_double(var1.u);
      BlockPos var8 = new BlockPos(var5, var6, var7);
      BiomeGenBase var9 = this.getBiomeGenForCoords(var8);
      float var10 = var9.getFloatTemperature(var8);
      int var11 = var9.getSkyColorByTemp(var10);
      float var12 = (var11 >> 16 & 0xFF) / 255.0F;
      float var13 = (var11 >> 8 & 0xFF) / 255.0F;
      float var14 = (var11 & 0xFF) / 255.0F;
      var12 *= var4;
      var13 *= var4;
      var14 *= var4;
      float var15 = this.j(var2);
      if (var15 > 0.0F) {
         float var16 = (var12 * 0.3F + var13 * 0.59F + var14 * 0.11F) * 0.6F;
         float var17 = 1.0F - var15 * 0.75F;
         var12 = var12 * var17 + var16 * (1.0F - var17);
         var13 = var13 * var17 + var16 * (1.0F - var17);
         var14 = var14 * var17 + var16 * (1.0F - var17);
      }

      float var23 = this.h(var2);
      if (var23 > 0.0F) {
         float var24 = (var12 * 0.3F + var13 * 0.59F + var14 * 0.11F) * 0.2F;
         float var18 = 1.0F - var23 * 0.75F;
         var12 = var12 * var18 + var24 * (1.0F - var18);
         var13 = var13 * var18 + var24 * (1.0F - var18);
         var14 = var14 * var18 + var24 * (1.0F - var18);
      }

      if (this.lastLightningBolt > 0) {
         float var25 = this.lastLightningBolt - var2;
         if (var25 > 1.0F) {
            var25 = 1.0F;
         }

         var25 *= 0.45F;
         var12 = var12 * (1.0F - var25) + 0.8F * var25;
         var13 = var13 * (1.0F - var25) + 0.8F * var25;
         var14 = var14 * (1.0F - var25) + 1.0F * var25;
      }

      return new Vec3(var12, var13, var14);
   }

   @Override
   public TileEntity getTileEntity(BlockPos var1) {
      if (!this.isValid(var1)) {
         return null;
      } else {
         TileEntity var2 = null;
         if (this.processingLoadedTiles) {
            for (int var3 = 0; var3 < this.addedTileEntityList.size(); var3++) {
               TileEntity var4 = this.addedTileEntityList.get(var3);
               if (!var4.isInvalid() && var4.v().equals(var1)) {
                  var2 = var4;
                  break;
               }
            }
         }

         if (var2 == null) {
            var2 = this.getChunkFromBlockCoords(var1).getTileEntity(var1, Chunk$EnumCreateEntityType.IMMEDIATE);
         }

         if (var2 == null) {
            for (int var5 = 0; var5 < this.addedTileEntityList.size(); var5++) {
               TileEntity var6 = this.addedTileEntityList.get(var5);
               if (!var6.isInvalid() && var6.v().equals(var1)) {
                  var2 = var6;
                  break;
               }
            }
         }

         return var2;
      }
   }

   public void setSkylightSubtracted(int var1) {
      this.skylightSubtracted = var1;
   }

   public void joinEntityInSurroundings(Entity var1) {
      int var2 = MathHelper.floor_double(var1.s / 16.0);
      int var3 = MathHelper.floor_double(var1.u / 16.0);
      byte var4 = 2;

      for (int var5 = var2 - var4; var5 <= var2 + var4; var5++) {
         for (int var6 = var3 - var4; var6 <= var3 + var4; var6++) {
            this.a(var5, var6);
         }
      }

      if (!this.f.contains(var1)) {
         this.f.add(var1);
      }
   }

   public void updateBlockTick(BlockPos var1, Block var2, int var3, int var4) {
   }

   public int getRedstonePower(BlockPos var1, EnumFacing var2) {
      IBlockState var3 = this.getBlockState(var1);
      Block var4 = var3.getBlock();
      return var4.isNormalCube() ? this.getStrongPower(var1) : var4.getWeakPower(this, var1, var3, var2);
   }

   public boolean checkNoEntityCollision(AxisAlignedBB var1, Entity var2) {
      List var3 = this.getEntitiesWithinAABBExcludingEntity((Entity)null, var1);

      for (int var4 = 0; var4 < var3.size(); var4++) {
         Entity var5 = (Entity)var3.get(var4);
         if (!var5.I && var5.k && var5 != var2 && (var2 == null || var2.m != var5 && var2.l != var5)) {
            return false;
         }
      }

      return true;
   }

   @Override
   public boolean isAirBlock(BlockPos var1) {
      return this.getBlockState(var1).getBlock().getMaterial() == Material.air;
   }

   public boolean isDaytime() {
      return this.skylightSubtracted < 4;
   }

   public void calculateInitialSkylight() {
      int var1 = this.calculateSkylightSubtracted(1.0F);
      if (var1 != this.skylightSubtracted) {
         this.skylightSubtracted = var1;
      }
   }

   public int getRawLight(BlockPos var1, EnumSkyBlock var2) {
      if (var2 == EnumSkyBlock.SKY && this.canSeeSky(var1)) {
         return 15;
      } else {
         Block var3 = this.getBlockState(var1).getBlock();
         int var4 = var2 == EnumSkyBlock.SKY ? 0 : var3.getLightValue();
         int var5 = var3.getLightOpacity();
         if (var5 >= 15 && var3.getLightValue() > 0) {
            var5 = 1;
         }

         if (var5 < 1) {
            var5 = 1;
         }

         if (var5 >= 15) {
            return 0;
         } else if (var4 >= 14) {
            return var4;
         } else {
            for (EnumFacing var9 : EnumFacing.values()) {
               BlockPos var10 = var1.a(var9);
               int var11 = this.getLightFor(var2, var10) - var5;
               if (var11 > var4) {
                  var4 = var11;
               }

               if (var4 >= 14) {
                  return var4;
               }
            }

            return var4;
         }
      }
   }

   public void updateWeather() {
      if (!this.t.getHasNoSky() && !this.D) {
         int var1 = this.x.getCleanWeatherTime();
         if (var1 > 0) {
            this.x.setCleanWeatherTime(--var1);
            this.x.setThunderTime(this.x.isThundering() ? 1 : 2);
            this.x.setRainTime(this.x.isRaining() ? 1 : 2);
         }

         int var2 = this.x.getThunderTime();
         if (var2 <= 0) {
            if (this.x.isThundering()) {
               this.x.setThunderTime(this.s.nextInt(12000) + 3600);
            } else {
               this.x.setThunderTime(this.s.nextInt(168000) + 12000);
            }
         } else {
            this.x.setThunderTime(--var2);
            if (var2 <= 0) {
               this.x.setThundering(!this.x.isThundering());
            }
         }

         this.q = this.r;
         if (this.x.isThundering()) {
            this.r = (float)(this.r + 0.01);
         } else {
            this.r = (float)(this.r - 0.01);
         }

         this.r = MathHelper.clamp_float(this.r, 0.0F, 1.0F);
         int var3 = this.x.getRainTime();
         if (var3 <= 0) {
            if (this.x.isRaining()) {
               this.x.setRainTime(this.s.nextInt(12000) + 12000);
            } else {
               this.x.setRainTime(this.s.nextInt(168000) + 12000);
            }
         } else {
            this.x.setRainTime(--var3);
            if (var3 <= 0) {
               this.x.setRaining(!this.x.isRaining());
            }
         }

         this.o = this.p;
         if (this.x.isRaining()) {
            this.p = (float)(this.p + 0.01);
         } else {
            this.p = (float)(this.p - 0.01);
         }

         this.p = MathHelper.clamp_float(this.p, 0.0F, 1.0F);
      }
   }

   public boolean setBlockToAir(BlockPos var1) {
      return this.a(var1, Blocks.air.getDefaultState(), 3);
   }

   public void playRecord(BlockPos var1, String var2) {
      for (int var3 = 0; var3 < this.worldAccesses.size(); var3++) {
         this.worldAccesses.get(var3).playRecord(var2, var1);
      }
   }

   public boolean isAreaLoaded(StructureBoundingBox var1) {
      return this.isAreaLoaded(var1, true);
   }

   public boolean a(BlockPos var1, IBlockState var2, int var3) {
      if (!this.isValid(var1)) {
         return false;
      } else if (!this.D && this.x.getTerrainType() == WorldType.DEBUG_WORLD) {
         return false;
      } else {
         Chunk var4 = this.getChunkFromBlockCoords(var1);
         Block var5 = var2.getBlock();
         IBlockState var6 = var4.setBlockState(var1, var2);
         if (var6 == null) {
            return false;
         } else {
            Block var7 = var6.getBlock();
            if (var5.getLightOpacity() != var7.getLightOpacity() || var5.getLightValue() != var7.getLightValue()) {
               this.B.startSection("checkLight");
               this.checkLight(var1);
               this.B.endSection();
            }

            if ((var3 & 2) != 0 && (!this.D || (var3 & 4) == 0) && var4.isPopulated()) {
               this.h(var1);
            }

            if (!this.D && (var3 & 1) != 0) {
               this.notifyNeighborsRespectDebug(var1, var6.getBlock());
               if (var5.hasComparatorInputOverride()) {
                  this.updateComparatorOutputLevel(var1, var5);
               }
            }

            return true;
         }
      }
   }

   public void tick() {
      this.updateWeather();
   }

   public EntityPlayer getClosestPlayerToEntity(Entity var1, double var2) {
      return this.getClosestPlayer(var1.s, var1.t, var1.u, var2);
   }

   public float j(float var1) {
      return this.o + (this.p - this.o) * var1;
   }

   public boolean a(int var1, int var2, boolean var3) {
      return this.v.chunkExists(var1, var2) && (var3 || !this.v.provideChunk(var1, var2).isEmpty());
   }

   public int getLightFromNeighbors(BlockPos var1) {
      return this.getLight(var1, true);
   }

   public Chunk getChunkFromBlockCoords(BlockPos var1) {
      return this.a(var1.getX() >> 4, var1.getZ() >> 4);
   }

   public void k(float var1) {
      this.o = var1;
      this.p = var1;
   }

   public List<AxisAlignedBB> getCollisionBoxes(AxisAlignedBB var1) {
      ArrayList var2 = Lists.newArrayList();
      int var3 = MathHelper.floor_double(var1.a);
      int var4 = MathHelper.floor_double(var1.d + 1.0);
      int var5 = MathHelper.floor_double(var1.b);
      int var6 = MathHelper.floor_double(var1.e + 1.0);
      int var7 = MathHelper.floor_double(var1.c);
      int var8 = MathHelper.floor_double(var1.f + 1.0);
      BlockPos$MutableBlockPos var9 = new BlockPos$MutableBlockPos();

      for (int var10 = var3; var10 < var4; var10++) {
         for (int var11 = var7; var11 < var8; var11++) {
            if (this.e(var9.set(var10, 64, var11))) {
               for (int var12 = var5 - 1; var12 < var6; var12++) {
                  var9.set(var10, var12, var11);
                  IBlockState var13;
                  if (var10 >= -30000000 && var10 < 30000000 && var11 >= -30000000 && var11 < 30000000) {
                     var13 = this.getBlockState(var9);
                  } else {
                     var13 = Blocks.bedrock.getDefaultState();
                  }

                  var13.getBlock().addCollisionBoxesToList(this, var9, var13, var1, var2, (Entity)null);
               }
            }
         }
      }

      return var2;
   }

   public boolean isRaining() {
      return this.j(1.0F) > 0.2;
   }

   public BlockPos getStrongholdPos(String var1, BlockPos var2) {
      return this.N().getStrongholdGen(this, var1, var2);
   }

   public boolean isFlammableWithin(AxisAlignedBB var1) {
      int var2 = MathHelper.floor_double(var1.a);
      int var3 = MathHelper.floor_double(var1.d + 1.0);
      int var4 = MathHelper.floor_double(var1.b);
      int var5 = MathHelper.floor_double(var1.e + 1.0);
      int var6 = MathHelper.floor_double(var1.c);
      int var7 = MathHelper.floor_double(var1.f + 1.0);
      if (this.isAreaLoaded(var2, var4, var6, var3, var5, var7, true)) {
         BlockPos$MutableBlockPos var8 = new BlockPos$MutableBlockPos();

         for (int var9 = var2; var9 < var3; var9++) {
            for (int var10 = var4; var10 < var5; var10++) {
               for (int var11 = var6; var11 < var7; var11++) {
                  Block var12 = this.getBlockState(var8.set(var9, var10, var11)).getBlock();
                  if (var12 == Blocks.fire || var12 == Blocks.flowing_lava || var12 == Blocks.lava) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public int getStrongPower(BlockPos var1) {
      int var2 = 0;
      var2 = Math.max(var2, this.getStrongPower(var1.down(), EnumFacing.DOWN));
      if (var2 >= 15) {
         return var2;
      } else {
         var2 = Math.max(var2, this.getStrongPower(var1.up(), EnumFacing.UP));
         if (var2 >= 15) {
            return var2;
         } else {
            var2 = Math.max(var2, this.getStrongPower(var1.north(), EnumFacing.NORTH));
            if (var2 >= 15) {
               return var2;
            } else {
               var2 = Math.max(var2, this.getStrongPower(var1.south(), EnumFacing.SOUTH));
               if (var2 >= 15) {
                  return var2;
               } else {
                  var2 = Math.max(var2, this.getStrongPower(var1.west(), EnumFacing.WEST));
                  if (var2 >= 15) {
                     return var2;
                  } else {
                     var2 = Math.max(var2, this.getStrongPower(var1.east(), EnumFacing.EAST));
                     return var2 >= 15 ? var2 : var2;
                  }
               }
            }
         }
      }
   }

   @Override
   public IBlockState getBlockState(BlockPos var1) {
      if (!this.isValid(var1)) {
         return Blocks.air.getDefaultState();
      } else {
         Chunk var2 = this.getChunkFromBlockCoords(var1);
         return var2.getBlockState(var1);
      }
   }

   public MovingObjectPosition rayTraceBlocks(Vec3 var1, Vec3 var2, boolean var3) {
      return this.rayTraceBlocks(var1, var2, var3, false, false);
   }

   public void markChunkDirty(BlockPos var1, TileEntity var2) {
      if (this.e(var1)) {
         this.getChunkFromBlockCoords(var1).setChunkModified();
      }
   }

   public float getBlockDensity(Vec3 var1, AxisAlignedBB var2) {
      double var3 = 1.0 / ((var2.d - var2.a) * 2.0 + 1.0);
      double var5 = 1.0 / ((var2.e - var2.b) * 2.0 + 1.0);
      double var7 = 1.0 / ((var2.f - var2.c) * 2.0 + 1.0);
      double var9 = (1.0 - Math.floor(1.0 / var3) * var3) / 2.0;
      double var11 = (1.0 - Math.floor(1.0 / var7) * var7) / 2.0;
      if (var3 >= 0.0 && var5 >= 0.0 && var7 >= 0.0) {
         int var13 = 0;
         int var14 = 0;

         for (float var15 = 0.0F; var15 <= 1.0F; var15 = (float)(var15 + var3)) {
            for (float var16 = 0.0F; var16 <= 1.0F; var16 = (float)(var16 + var5)) {
               for (float var17 = 0.0F; var17 <= 1.0F; var17 = (float)(var17 + var7)) {
                  double var18 = var2.a + (var2.d - var2.a) * var15;
                  double var20 = var2.b + (var2.e - var2.b) * var16;
                  double var22 = var2.c + (var2.f - var2.c) * var17;
                  if (this.rayTraceBlocks(new Vec3(var18 + var9, var20, var22 + var11), var1) == null) {
                     var13++;
                  }

                  var14++;
               }
            }
         }

         return (float)var13 / var14;
      } else {
         return 0.0F;
      }
   }

   public EntityPlayer getPlayerEntityByUUID(UUID var1) {
      for (int var2 = 0; var2 < this.j.size(); var2++) {
         EntityPlayer var3 = this.j.get(var2);
         if (var1.equals(var3.aK())) {
            return var3;
         }
      }

      return null;
   }

   public void addBlockEvent(BlockPos var1, Block var2, int var3, int var4) {
      var2.onBlockEventReceived(this, var1, this.getBlockState(var1), var3, var4);
   }

   public int getSkylightSubtracted() {
      return this.skylightSubtracted;
   }

   public IChunkProvider N() {
      return this.v;
   }

   public Entity getEntityByID(int var1) {
      return this.l.lookup(var1);
   }

   public void setTileEntity(BlockPos var1, TileEntity var2) {
      if (var2 != null && !var2.isInvalid()) {
         if (this.processingLoadedTiles) {
            var2.setPos(var1);
            Iterator var3 = this.addedTileEntityList.iterator();

            while (var3.hasNext()) {
               TileEntity var4 = (TileEntity)var3.next();
               if (var4.v().equals(var1)) {
                  var4.invalidate();
                  var3.remove();
               }
            }

            this.addedTileEntityList.add(var2);
         } else {
            this.addTileEntity(var2);
            this.getChunkFromBlockCoords(var1).addTileEntity(var1, var2);
         }
      }
   }

   public List<Entity> getLoadedEntityList() {
      return this.f;
   }

   public int getLastLightningBolt() {
      return this.lastLightningBolt;
   }

   public int getUniqueDataId(String var1) {
      return this.z.getUniqueDataId(var1);
   }

   public DifficultyInstance E(BlockPos var1) {
      long var2 = 9741581L & 1378435120L;
      float var4 = 0.0F;
      if (this.e(var1)) {
         var4 = this.getCurrentMoonPhaseFactor();
         var2 = this.getChunkFromBlockCoords(var1).getInhabitedTime();
      }

      return new DifficultyInstance(this.getDifficulty(), this.L(), var2, var4);
   }

   public void spawnParticle(int var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      for (int var16 = 0; var16 < this.worldAccesses.size(); var16++) {
         this.worldAccesses.get(var16).spawnParticle(var1, var2, var3, var5, var7, var9, var11, var13, var15);
      }
   }

   public void markTileEntityForRemoval(TileEntity var1) {
      this.field_0019.add(var1);
   }

   public void scheduleBlockUpdate(BlockPos var1, Block var2, int var3, int var4) {
   }

   public boolean isAreaLoaded(BlockPos var1, int var2, boolean var3) {
      return this.isAreaLoaded(var1.getX() - var2, var1.getY() - var2, var1.getZ() - var2, var1.getX() + var2, var1.getY() + var2, var1.getZ() + var2, var3);
   }

   public void playSoundToNearExcept(EntityPlayer var1, String var2, float var3, float var4) {
      for (int var5 = 0; var5 < this.worldAccesses.size(); var5++) {
         this.worldAccesses.get(var5).playSoundToNearExcept(var1, var2, var1.s, var1.t, var1.u, var3, var4);
      }
   }

   public boolean isBlockinHighHumidity(BlockPos var1) {
      BiomeGenBase var2 = this.getBiomeGenForCoords(var1);
      return var2.isHighHumidity();
   }

   public boolean isAreaLoaded(StructureBoundingBox var1, boolean var2) {
      return this.isAreaLoaded(var1.minX, var1.minY, var1.minZ, var1.maxX, var1.maxY, var1.maxZ, var2);
   }

   public MovingObjectPosition rayTraceBlocks(Vec3 var1, Vec3 var2, boolean var3, boolean var4, boolean var5) {
      if (Double.isNaN(var1.xCoord) || Double.isNaN(var1.yCoord) || Double.isNaN(var1.zCoord)) {
         return null;
      } else if (!Double.isNaN(var2.xCoord) && !Double.isNaN(var2.yCoord) && !Double.isNaN(var2.zCoord)) {
         int var6 = MathHelper.floor_double(var2.xCoord);
         int var7 = MathHelper.floor_double(var2.yCoord);
         int var8 = MathHelper.floor_double(var2.zCoord);
         int var9 = MathHelper.floor_double(var1.xCoord);
         int var10 = MathHelper.floor_double(var1.yCoord);
         int var11 = MathHelper.floor_double(var1.zCoord);
         BlockPos var12 = new BlockPos(var9, var10, var11);
         IBlockState var13 = this.getBlockState(var12);
         Block var14 = var13.getBlock();
         if ((!var4 || var14.getCollisionBoundingBox(this, var12, var13) != null) && var14.canCollideCheck(var13, var3)) {
            MovingObjectPosition var15 = var14.collisionRayTrace(this, var12, var1, var2);
            if (var15 != null) {
               return var15;
            }
         }

         MovingObjectPosition var43 = null;
         int var16 = 200;

         while (var16-- >= 0) {
            if (Double.isNaN(var1.xCoord) || Double.isNaN(var1.yCoord) || Double.isNaN(var1.zCoord)) {
               return null;
            }

            if (var9 == var6 && var10 == var7 && var11 == var8) {
               return var5 ? var43 : null;
            }

            boolean var17 = true;
            boolean var18 = true;
            boolean var19 = true;
            double var20 = 999.0;
            double var22 = 999.0;
            double var24 = 999.0;
            if (var6 > var9) {
               var20 = var9 + 1.0;
            } else if (var6 < var9) {
               var20 = var9 + 0.0;
            } else {
               var17 = false;
            }

            if (var7 > var10) {
               var22 = var10 + 1.0;
            } else if (var7 < var10) {
               var22 = var10 + 0.0;
            } else {
               var18 = false;
            }

            if (var8 > var11) {
               var24 = var11 + 1.0;
            } else if (var8 < var11) {
               var24 = var11 + 0.0;
            } else {
               var19 = false;
            }

            double var26 = 999.0;
            double var28 = 999.0;
            double var30 = 999.0;
            double var32 = var2.xCoord - var1.xCoord;
            double var34 = var2.yCoord - var1.yCoord;
            double var36 = var2.zCoord - var1.zCoord;
            if (var17) {
               var26 = (var20 - var1.xCoord) / var32;
            }

            if (var18) {
               var28 = (var22 - var1.yCoord) / var34;
            }

            if (var19) {
               var30 = (var24 - var1.zCoord) / var36;
            }

            if (var26 == -0.0) {
               var26 = -1.0E-4;
            }

            if (var28 == -0.0) {
               var28 = -1.0E-4;
            }

            if (var30 == -0.0) {
               var30 = -1.0E-4;
            }

            EnumFacing var38;
            if (var26 < var28 && var26 < var30) {
               var38 = var6 > var9 ? EnumFacing.WEST : EnumFacing.EAST;
               var1 = new Vec3(var20, var1.yCoord + var34 * var26, var1.zCoord + var36 * var26);
            } else if (var28 < var30) {
               var38 = var7 > var10 ? EnumFacing.DOWN : EnumFacing.UP;
               var1 = new Vec3(var1.xCoord + var32 * var28, var22, var1.zCoord + var36 * var28);
            } else {
               var38 = var8 > var11 ? EnumFacing.NORTH : EnumFacing.SOUTH;
               var1 = new Vec3(var1.xCoord + var32 * var30, var1.yCoord + var34 * var30, var24);
            }

            var9 = MathHelper.floor_double(var1.xCoord) - (var38 == EnumFacing.EAST ? 1 : 0);
            var10 = MathHelper.floor_double(var1.yCoord) - (var38 == EnumFacing.UP ? 1 : 0);
            var11 = MathHelper.floor_double(var1.zCoord) - (var38 == EnumFacing.SOUTH ? 1 : 0);
            var12 = new BlockPos(var9, var10, var11);
            IBlockState var39 = this.getBlockState(var12);
            Block var40 = var39.getBlock();
            if (!var4 || var40.getCollisionBoundingBox(this, var12, var39) != null) {
               if (var40.canCollideCheck(var39, var3)) {
                  MovingObjectPosition var41 = var40.collisionRayTrace(this, var12, var1, var2);
                  if (var41 != null) {
                     return var41;
                  }
               } else {
                  var43 = new MovingObjectPosition(MovingObjectPosition$MovingObjectType.MISS, var1, var38, var12);
               }
            }
         }

         return var5 ? var43 : null;
      } else {
         return null;
      }
   }

   public BlockPos M() {
      BlockPos var1 = new BlockPos(this.x.getSpawnX(), this.x.getSpawnY(), this.x.getSpawnZ());
      if (!this.af().contains(var1)) {
         var1 = this.getHeight(new BlockPos(this.af().getCenterX(), 0.0, this.af().getCenterZ()));
      }

      return var1;
   }

   public float getSunBrightness(float var1) {
      float var2 = this.getCelestialAngle(var1);
      float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.2F);
      var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
      var3 = 1.0F - var3;
      var3 = (float)(var3 * (1.0 - this.j(var1) * 5.0F / 16.0));
      var3 = (float)(var3 * (1.0 - this.h(var1) * 5.0F / 16.0));
      return var3 * 0.8F + 0.2F;
   }

   public void f(Entity var1) {
      var1.setDead();
      if (var1 instanceof EntityPlayer) {
         this.j.remove(var1);
         this.updateAllPlayersSleepingFlag();
      }

      int var2 = var1.chunkCoordX;
      int var3 = var1.chunkCoordZ;
      if (var1.addedToChunk && this.a(var2, var3, true)) {
         this.a(var2, var3).removeEntity(var1);
      }

      this.f.remove(var1);
      this.onEntityRemoved(var1);
   }

   public int getChunksLowestHorizon(int var1, int var2) {
      if (var1 >= -30000000 && var2 >= -30000000 && var1 < 30000000 && var2 < 30000000) {
         if (!this.a(var1 >> 4, var2 >> 4, true)) {
            return 0;
         } else {
            Chunk var3 = this.a(var1 >> 4, var2 >> 4);
            return var3.getLowestHeight();
         }
      } else {
         return this.F() + 1;
      }
   }

   public void addWorldAccess(IWorldAccess var1) {
      this.worldAccesses.add(var1);
   }

   public float h(float var1) {
      return (this.q + (this.r - this.q) * var1) * this.j(var1);
   }

   public void forceBlockUpdateTick(Block var1, BlockPos var2, Random var3) {
      this.e = true;
      var1.updateTick(this, var2, this.getBlockState(var2), var3);
      this.e = false;
   }

   public boolean isInsideBorder(WorldBorder var1, Entity var2) {
      double var3 = var1.minX();
      double var5 = var1.minZ();
      double var7 = var1.maxX();
      double var9 = var1.maxZ();
      if (var2.isOutsideBorder()) {
         var3++;
         var5++;
         var7--;
         var9--;
      } else {
         var3--;
         var5--;
         var7++;
         var9++;
      }

      return var2.s > var3 && var2.s < var7 && var2.u > var5 && var2.u < var9;
   }

   public boolean isBlockTickPending(BlockPos var1, Block var2) {
      return false;
   }

   public Vec3 getFogColor(float var1) {
      float var2 = this.getCelestialAngle(var1);
      return this.t.getFogColor(var2, var1);
   }

   public int getLight(BlockPos var1) {
      if (var1.getY() < 0) {
         return 0;
      } else {
         if (var1.getY() >= 256) {
            var1 = new BlockPos(var1.getX(), 255, var1.getZ());
         }

         return this.getChunkFromBlockCoords(var1).getLightSubtracted(var1, 0);
      }
   }

   public void scheduleUpdate(BlockPos var1, Block var2, int var3) {
   }

   public boolean e(BlockPos var1) {
      return this.isBlockLoaded(var1, true);
   }

   public boolean checkBlockCollision(AxisAlignedBB var1) {
      int var2 = MathHelper.floor_double(var1.a);
      int var3 = MathHelper.floor_double(var1.d);
      int var4 = MathHelper.floor_double(var1.b);
      int var5 = MathHelper.floor_double(var1.e);
      int var6 = MathHelper.floor_double(var1.c);
      int var7 = MathHelper.floor_double(var1.f);
      BlockPos$MutableBlockPos var8 = new BlockPos$MutableBlockPos();

      for (int var9 = var2; var9 <= var3; var9++) {
         for (int var10 = var4; var10 <= var5; var10++) {
            for (int var11 = var6; var11 <= var7; var11++) {
               Block var12 = this.getBlockState(var8.set(var9, var10, var11)).getBlock();
               if (var12.getMaterial() != Material.air) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public int getHeight() {
      return 256;
   }

   public WorldBorder af() {
      return this.field_0009;
   }

   public void setEntityState(Entity var1, byte var2) {
   }

   public void setSeaLevel(int var1) {
      this.field_0006 = var1;
   }

   public boolean checkLight(BlockPos var1) {
      boolean var2 = false;
      if (!this.t.getHasNoSky()) {
         var2 |= this.checkLightFor(EnumSkyBlock.SKY, var1);
      }

      return var2 | this.checkLightFor(EnumSkyBlock.BLOCK, var1);
   }

   public void markBlockRangeForRenderUpdate(int var1, int var2, int var3, int var4, int var5, int var6) {
      for (int var7 = 0; var7 < this.worldAccesses.size(); var7++) {
         this.worldAccesses.get(var7).markBlockRangeForRenderUpdate(var1, var2, var3, var4, var5, var6);
      }
   }

   public boolean isAnyLiquid(AxisAlignedBB var1) {
      int var2 = MathHelper.floor_double(var1.a);
      int var3 = MathHelper.floor_double(var1.d);
      int var4 = MathHelper.floor_double(var1.b);
      int var5 = MathHelper.floor_double(var1.e);
      int var6 = MathHelper.floor_double(var1.c);
      int var7 = MathHelper.floor_double(var1.f);
      BlockPos$MutableBlockPos var8 = new BlockPos$MutableBlockPos();

      for (int var9 = var2; var9 <= var3; var9++) {
         for (int var10 = var4; var10 <= var5; var10++) {
            for (int var11 = var6; var11 <= var7; var11++) {
               Block var12 = this.getBlockState(var8.set(var9, var10, var11)).getBlock();
               if (var12.getMaterial().isLiquid()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean isAABBInMaterial(AxisAlignedBB var1, Material var2) {
      int var3 = MathHelper.floor_double(var1.a);
      int var4 = MathHelper.floor_double(var1.d + 1.0);
      int var5 = MathHelper.floor_double(var1.b);
      int var6 = MathHelper.floor_double(var1.e + 1.0);
      int var7 = MathHelper.floor_double(var1.c);
      int var8 = MathHelper.floor_double(var1.f + 1.0);
      BlockPos$MutableBlockPos var9 = new BlockPos$MutableBlockPos();

      for (int var10 = var3; var10 < var4; var10++) {
         for (int var11 = var5; var11 < var6; var11++) {
            for (int var12 = var7; var12 < var8; var12++) {
               IBlockState var13 = this.getBlockState(var9.set(var10, var11, var12));
               Block var14 = var13.getBlock();
               if (var14.getMaterial() == var2) {
                  int var15 = var13.getValue(BlockLiquid.b);
                  double var16 = var11 + 1;
                  if (var15 < 8) {
                     var16 = var11 + 1 - var15 / 8.0;
                  }

                  if (var16 >= var1.b) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public void setAllowedSpawnTypes(boolean var1, boolean var2) {
      this.spawnHostileMobs = var1;
      this.spawnPeacefulMobs = var2;
   }

   public void I() {
      this.w.checkSessionLock();
   }

   public boolean isSpawnChunk(int var1, int var2) {
      BlockPos var3 = this.M();
      int var4 = var1 * 16 + 8 - var3.getX();
      int var5 = var2 * 16 + 8 - var3.getZ();
      short var6 = 128;
      return var4 >= -var6 && var4 <= var6 && var5 >= -var6 && var5 <= var6;
   }

   @Override
   public WorldType x_() {
      return this.x.getTerrainType();
   }

   public Explosion newExplosion(Entity var1, double var2, double var4, double var6, float var8, boolean var9, boolean var10) {
      Explosion var11 = new Explosion(this, var1, var2, var4, var6, var8, var9, var10);
      var11.doExplosionA();
      var11.doExplosionB(true);
      return var11;
   }

   public int countEntities(Class<?> var1) {
      int var2 = 0;

      for (Entity var4 : this.f) {
         if ((!(var4 instanceof EntityLiving) || !((EntityLiving)var4).isNoDespawnRequired()) && var1.isAssignableFrom(var4.getClass())) {
            var2++;
         }
      }

      return var2;
   }

   public String getDebugLoadedEntities() {
      return "All: " + this.f.size();
   }

   public void setItemData(String var1, WorldSavedData var2) {
      this.z.setData(var1, var2);
   }

   public BlockPos getHeight(BlockPos var1) {
      int var2;
      if (var1.getX() >= -30000000 && var1.getZ() >= -30000000 && var1.getX() < 30000000 && var1.getZ() < 30000000) {
         if (this.a(var1.getX() >> 4, var1.getZ() >> 4, true)) {
            var2 = this.a(var1.getX() >> 4, var1.getZ() >> 4).getHeightValue(var1.getX() & 15, var1.getZ() & 15);
         } else {
            var2 = 0;
         }
      } else {
         var2 = this.F() + 1;
      }

      return new BlockPos(var1.getX(), var2, var1.getZ());
   }

   public void playSound(double var1, double var3, double var5, String var7, float var8, float var9, boolean var10) {
   }

   public void method_09997(long var1) {
      this.x.setWorldTotalTime(var1);
   }

   public VillageCollection getVillageCollection() {
      return this.A;
   }

   public Calendar getCurrentDate() {
      if (this.K() % (17306488L & 1283770072L) == (606079010L & 403838596L)) {
         this.field_0005.setTimeInMillis(MinecraftServer.getCurrentTimeMillis());
      }

      return this.field_0005;
   }

   public ISaveHandler O() {
      return this.w;
   }

   public int getLight(BlockPos var1, boolean var2) {
      if (var1.getX() < -30000000 || var1.getZ() < -30000000 || var1.getX() >= 30000000 || var1.getZ() >= 30000000) {
         return 15;
      } else if (var2 && this.getBlockState(var1).getBlock().getUseNeighborBrightness()) {
         int var8 = this.getLight(var1.up(), false);
         int var4 = this.getLight(var1.east(), false);
         int var5 = this.getLight(var1.west(), false);
         int var6 = this.getLight(var1.south(), false);
         int var7 = this.getLight(var1.north(), false);
         if (var4 > var8) {
            var8 = var4;
         }

         if (var5 > var8) {
            var8 = var5;
         }

         if (var6 > var8) {
            var8 = var6;
         }

         if (var7 > var8) {
            var8 = var7;
         }

         return var8;
      } else if (var1.getY() < 0) {
         return 0;
      } else {
         if (var1.getY() >= 256) {
            var1 = new BlockPos(var1.getX(), 255, var1.getZ());
         }

         Chunk var3 = this.getChunkFromBlockCoords(var1);
         return var3.getLightSubtracted(var1, this.skylightSubtracted);
      }
   }

   public boolean isRainingAt(BlockPos var1) {
      if (!this.isRaining()) {
         return false;
      } else if (!this.canSeeSky(var1)) {
         return false;
      } else if (this.getPrecipitationHeight(var1).getY() > var1.getY()) {
         return false;
      } else {
         BiomeGenBase var2 = this.getBiomeGenForCoords(var1);
         return var2.getEnableSnow() ? false : (this.canSnowAt(var1, false) ? false : var2.canRain());
      }
   }

   public void updateEntities() {
      this.B.startSection("entities");
      this.B.startSection("global");

      for (int var1 = 0; var1 < this.weatherEffects.size(); var1++) {
         Entity var2 = this.weatherEffects.get(var1);

         try {
            var2.W++;
            var2.onUpdate();
         } catch (Throwable var9) {
            CrashReport var4 = CrashReport.makeCrashReport(var9, "Ticking entity");
            CrashReportCategory var5 = var4.makeCategory("Entity being ticked");
            if (var2 == null) {
               var5.addCrashSection("Entity", "~~NULL~~");
            } else {
               var2.addEntityCrashInfo(var5);
            }

            throw new ReportedException(var4);
         }

         if (var2.I) {
            this.weatherEffects.remove(var1--);
         }
      }

      this.B.endStartSection("remove");
      this.f.removeAll(this.g);

      for (int var10 = 0; var10 < this.g.size(); var10++) {
         Entity var14 = this.g.get(var10);
         int var3 = var14.chunkCoordX;
         int var21 = var14.chunkCoordZ;
         if (var14.addedToChunk && this.a(var3, var21, true)) {
            this.a(var3, var21).removeEntity(var14);
         }
      }

      for (int var11 = 0; var11 < this.g.size(); var11++) {
         this.onEntityRemoved(this.g.get(var11));
      }

      this.g.clear();
      this.B.endStartSection("regular");

      for (int var12 = 0; var12 < this.f.size(); var12++) {
         Entity var15 = this.f.get(var12);
         if (var15.m != null) {
            if (!var15.m.I && var15.m.l == var15) {
               continue;
            }

            var15.m.l = null;
            var15.m = null;
         }

         this.B.startSection("tick");
         if (!var15.I) {
            try {
               this.updateEntity(var15);
            } catch (Throwable var8) {
               CrashReport var22 = CrashReport.makeCrashReport(var8, "Ticking entity");
               CrashReportCategory var24 = var22.makeCategory("Entity being ticked");
               var15.addEntityCrashInfo(var24);
               throw new ReportedException(var22);
            }
         }

         this.B.endSection();
         this.B.startSection("remove");
         if (var15.I) {
            int var18 = var15.chunkCoordX;
            int var23 = var15.chunkCoordZ;
            if (var15.addedToChunk && this.a(var18, var23, true)) {
               this.a(var18, var23).removeEntity(var15);
            }

            this.f.remove(var12--);
            this.onEntityRemoved(var15);
         }

         this.B.endSection();
      }

      this.B.endStartSection("blockEntities");
      this.processingLoadedTiles = true;
      Iterator var13 = this.tickableTileEntities.iterator();

      while (var13.hasNext()) {
         TileEntity var16 = (TileEntity)var13.next();
         if (!var16.isInvalid() && var16.t()) {
            BlockPos var19 = var16.v();
            if (this.e(var19) && this.field_0009.contains(var19)) {
               try {
                  ((ITickable)var16).update();
               } catch (Throwable var7) {
                  CrashReport var25 = CrashReport.makeCrashReport(var7, "Ticking block entity");
                  CrashReportCategory var6 = var25.makeCategory("Block entity being ticked");
                  var16.addInfoToCrashReport(var6);
                  throw new ReportedException(var25);
               }
            }
         }

         if (var16.isInvalid()) {
            var13.remove();
            this.h.remove(var16);
            if (this.e(var16.v())) {
               this.getChunkFromBlockCoords(var16.v()).removeTileEntity(var16.v());
            }
         }
      }

      this.processingLoadedTiles = false;
      if (!this.field_0019.isEmpty()) {
         this.tickableTileEntities.removeAll(this.field_0019);
         this.h.removeAll(this.field_0019);
         this.field_0019.clear();
      }

      this.B.endStartSection("pendingBlockEntities");
      if (!this.addedTileEntityList.isEmpty()) {
         for (int var17 = 0; var17 < this.addedTileEntityList.size(); var17++) {
            TileEntity var20 = this.addedTileEntityList.get(var17);
            if (!var20.isInvalid()) {
               if (!this.h.contains(var20)) {
                  this.addTileEntity(var20);
               }

               if (this.e(var20.v())) {
                  this.getChunkFromBlockCoords(var20.v()).addTileEntity(var20.v(), var20);
               }

               this.h(var20.v());
            }
         }

         this.addedTileEntityList.clear();
      }

      this.B.endSection();
      this.B.endSection();
   }

   public float getCelestialAngleRadians(float var1) {
      float var2 = this.getCelestialAngle(var1);
      return var2 * (float) Math.PI * 2.0F;
   }

   public boolean isThundering() {
      return this.h(1.0F) > 0.9;
   }

   public Explosion createExplosion(Entity var1, double var2, double var4, double var6, float var8, boolean var9) {
      return this.newExplosion(var1, var2, var4, var6, var8, false, var9);
   }

   public boolean isBlockNormalCube(BlockPos var1, boolean var2) {
      if (!this.isValid(var1)) {
         return var2;
      } else {
         Chunk var3 = this.v.provideChunk(var1);
         if (var3.isEmpty()) {
            return var2;
         } else {
            Block var4 = this.getBlockState(var1).getBlock();
            return var4.getMaterial().isOpaque() && var4.isFullCube();
         }
      }
   }

   public void setInitialSpawnLocation() {
      this.B(new BlockPos(8, 64, 8));
   }

   public void method_09975(BlockPos var1) {
      for (int var2 = 0; var2 < this.worldAccesses.size(); var2++) {
         this.worldAccesses.get(var2).notifyLightSet(var1);
      }
   }

   public int F() {
      return this.field_0006;
   }

   public float getCurrentMoonPhaseFactor() {
      return WorldProvider.moonPhaseFactors[this.t.getMoonPhase(this.x.getWorldTime())];
   }

   public CrashReportCategory addWorldInfoToCrashReport(CrashReport var1) {
      CrashReportCategory var2 = var1.makeCategoryDepth("Affected level", 1);
      var2.addCrashSection("Level name", this.x == null ? "????" : this.x.getWorldName());
      var2.addCrashSectionCallable("All players", new World$3(this));
      var2.addCrashSectionCallable("Chunk stats", new World$4(this));

      try {
         this.x.addToCrashReport(var2);
      } catch (Throwable var4) {
         var2.addCrashSectionThrowable("Level Data Unobtainable", var4);
      }

      return var2;
   }

   public void setWorldTime(long var1) {
      this.x.setWorldTime(var1);
   }

   public void setLightFor(EnumSkyBlock var1, BlockPos var2, int var3) {
      if (this.isValid(var2) && this.e(var2)) {
         Chunk var4 = this.getChunkFromBlockCoords(var2);
         var4.setLightFor(var1, var2, var3);
         this.method_09975(var2);
      }
   }

   public int isBlockIndirectlyGettingPowered(BlockPos var1) {
      int var2 = 0;

      for (EnumFacing var6 : EnumFacing.values()) {
         int var7 = this.getRedstonePower(var1.a(var6), var6);
         if (var7 >= 15) {
            return 15;
         }

         if (var7 > var2) {
            var2 = var7;
         }
      }

      return var2;
   }

   public void updateEntityWithOptionalForce(Entity var1, boolean var2) {
      int var3 = MathHelper.floor_double(var1.s);
      int var4 = MathHelper.floor_double(var1.u);
      byte var5 = 32;
      if (!var2 || this.isAreaLoaded(var3 - var5, 0, var4 - var5, var3 + var5, 0, var4 + var5, true)) {
         var1.P = var1.s;
         var1.Q = var1.t;
         var1.R = var1.u;
         var1.A = var1.y;
         var1.B = var1.z;
         if (var2 && var1.addedToChunk) {
            var1.W++;
            if (var1.m != null) {
               var1.updateRidden();
            } else {
               var1.onUpdate();
            }
         }

         this.B.startSection("chunkCheck");
         if (Double.isNaN(var1.s) || Double.isInfinite(var1.s)) {
            var1.s = var1.P;
         }

         if (Double.isNaN(var1.t) || Double.isInfinite(var1.t)) {
            var1.t = var1.Q;
         }

         if (Double.isNaN(var1.u) || Double.isInfinite(var1.u)) {
            var1.u = var1.R;
         }

         if (Double.isNaN(var1.z) || Double.isInfinite(var1.z)) {
            var1.z = var1.B;
         }

         if (Double.isNaN(var1.y) || Double.isInfinite(var1.y)) {
            var1.y = var1.A;
         }

         int var6 = MathHelper.floor_double(var1.s / 16.0);
         int var7 = MathHelper.floor_double(var1.t / 16.0);
         int var8 = MathHelper.floor_double(var1.u / 16.0);
         if (!var1.addedToChunk || var1.chunkCoordX != var6 || var1.chunkCoordY != var7 || var1.chunkCoordZ != var8) {
            if (var1.addedToChunk && this.a(var1.chunkCoordX, var1.chunkCoordZ, true)) {
               this.a(var1.chunkCoordX, var1.chunkCoordZ).removeEntityAtIndex(var1, var1.chunkCoordY);
            }

            if (this.a(var6, var8, true)) {
               var1.addedToChunk = true;
               this.a(var6, var8).addEntity(var1);
            } else {
               var1.addedToChunk = false;
            }
         }

         this.B.endSection();
         if (var2 && var1.addedToChunk && var1.l != null) {
            if (!var1.l.I && var1.l.m == var1) {
               this.updateEntity(var1.l);
            } else {
               var1.l.m = null;
               var1.l = null;
            }
         }
      }
   }

   public <T extends Entity> List<T> getEntitiesWithinAABB(Class<? extends T> var1, AxisAlignedBB var2, Predicate<? super T> var3) {
      int var4 = MathHelper.floor_double((var2.a - 2.0) / 16.0);
      int var5 = MathHelper.floor_double((var2.d + 2.0) / 16.0);
      int var6 = MathHelper.floor_double((var2.c - 2.0) / 16.0);
      int var7 = MathHelper.floor_double((var2.f + 2.0) / 16.0);
      ArrayList var8 = Lists.newArrayList();

      for (int var9 = var4; var9 <= var5; var9++) {
         for (int var10 = var6; var10 <= var7; var10++) {
            if (this.a(var9, var10, true)) {
               this.a(var9, var10).getEntitiesOfTypeWithinAAAB(var1, var2, var8, var3);
            }
         }
      }

      return var8;
   }

   public Block getGroundAboveSeaLevel(BlockPos var1) {
      BlockPos var2 = new BlockPos(var1.getX(), this.F(), var1.getZ());

      while (!this.isAirBlock(var2.up())) {
         var2 = var2.up();
      }

      return this.getBlockState(var2).getBlock();
   }

   public void spawnParticle(EnumParticleTypes var1, boolean var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      this.spawnParticle(var1.getParticleID(), var1.getShouldIgnoreRange() | var2, var3, var5, var7, var9, var11, var13, var15);
   }

   public EnumDifficulty getDifficulty() {
      return this.P().getDifficulty();
   }

   public Chunk a(int var1, int var2) {
      return this.v.provideChunk(var1, var2);
   }

   public EntityPlayer getClosestPlayer(double var1, double var3, double var5, double var7) {
      double var9 = -1.0;
      EntityPlayer var11 = null;

      for (int var12 = 0; var12 < this.j.size(); var12++) {
         EntityPlayer var13 = this.j.get(var12);
         if (EntitySelectors.NOT_SPECTATING.apply(var13)) {
            double var14 = var13.e(var1, var3, var5);
            if ((var7 < 0.0 || var14 < var7 * var7) && (var9 == -1.0 || var14 < var9)) {
               var9 = var14;
               var11 = var13;
            }
         }
      }

      return var11;
   }

   public void playSoundEffect(double var1, double var3, double var5, String var7, float var8, float var9) {
      for (int var10 = 0; var10 < this.worldAccesses.size(); var10++) {
         this.worldAccesses.get(var10).playSound(var7, var1, var3, var5, var8, var9);
      }
   }

   public float getCelestialAngle(float var1) {
      return this.t.calculateCelestialAngle(this.x.getWorldTime(), var1);
   }

   public boolean extinguishFire(EntityPlayer var1, BlockPos var2, EnumFacing var3) {
      var2 = var2.a(var3);
      if (this.getBlockState(var2).getBlock() == Blocks.fire) {
         this.playAuxSFXAtEntity(var1, 1004, var2, 0);
         this.setBlockToAir(var2);
         return true;
      } else {
         return false;
      }
   }

   public void a(int var1, int var2, Chunk var3) {
      this.B.endStartSection("moodSound");
      if (this.ambientTickCountdown == 0 && !this.D) {
         this.updateLCG = this.updateLCG * 3 + 1013904223;
         int var4 = this.updateLCG >> 2;
         int var5 = var4 & 15;
         int var6 = var4 >> 8 & 15;
         int var7 = var4 >> 16 & 0xFF;
         BlockPos var8 = new BlockPos(var5, var7, var6);
         Block var9 = var3.getBlock(var8);
         var5 += var1;
         var6 += var2;
         if (var9.getMaterial() == Material.air && this.getLight(var8) <= this.s.nextInt(8) && this.getLightFor(EnumSkyBlock.SKY, var8) <= 0) {
            EntityPlayer var10 = this.getClosestPlayer(var5 + 0.5, var7 + 0.5, var6 + 0.5, 8.0);
            if (var10 != null && var10.e(var5 + 0.5, var7 + 0.5, var6 + 0.5) > 4.0) {
               this.playSoundEffect(var5 + 0.5, var7 + 0.5, var6 + 0.5, "ambient.cave.cave", 0.7F, 0.8F + this.s.nextFloat() * 0.2F);
               this.ambientTickCountdown = this.s.nextInt(12000) + 6000;
            }
         }
      }

      this.B.endStartSection("checkLight");
      var3.enqueueRelightChecks();
   }

   public <T extends Entity> List<T> method_10010(Class<? extends T> var1, Predicate<? super T> var2) {
      ArrayList var3 = Lists.newArrayList();

      for (Entity var5 : this.j) {
         if (var1.isAssignableFrom(var5.getClass()) && var2.apply(var5)) {
            var3.add(var5);
         }
      }

      return var3;
   }

   public World init() {
      return this;
   }

   public float o(BlockPos var1) {
      return this.t.getLightBrightnessTable()[this.getLightFromNeighbors(var1)];
   }

   @Override
   public int getStrongPower(BlockPos var1, EnumFacing var2) {
      IBlockState var3 = this.getBlockState(var1);
      return var3.getBlock().getStrongPower(this, var1, var3, var2);
   }

   public boolean addWeatherEffect(Entity var1) {
      this.weatherEffects.add(var1);
      return true;
   }

   public Vec3 getCloudColour(float var1) {
      float var2 = this.getCelestialAngle(var1);
      float var3 = MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.5F;
      var3 = MathHelper.clamp_float(var3, 0.0F, 1.0F);
      float var4 = (float)(this.cloudColour >> 16 & 7130827764810125567L & 404235775L) / 255.0F;
      float var5 = (float)(this.cloudColour >> 8 & 274858751L & -9223257128141045249L) / 255.0F;
      float var6 = (float)(this.cloudColour & 251679487L & 4587775L) / 255.0F;
      float var7 = this.j(var1);
      if (var7 > 0.0F) {
         float var8 = (var4 * 0.3F + var5 * 0.59F + var6 * 0.11F) * 0.6F;
         float var9 = 1.0F - var7 * 0.95F;
         var4 = var4 * var9 + var8 * (1.0F - var9);
         var5 = var5 * var9 + var8 * (1.0F - var9);
         var6 = var6 * var9 + var8 * (1.0F - var9);
      }

      var4 *= var3 * 0.9F + 0.1F;
      var5 *= var3 * 0.9F + 0.1F;
      var6 *= var3 * 0.85F + 0.15F;
      float var15 = this.h(var1);
      if (var15 > 0.0F) {
         float var16 = (var4 * 0.3F + var5 * 0.59F + var6 * 0.11F) * 0.2F;
         float var10 = 1.0F - var15 * 0.95F;
         var4 = var4 * var10 + var16 * (1.0F - var10);
         var5 = var5 * var10 + var16 * (1.0F - var10);
         var6 = var6 * var10 + var16 * (1.0F - var10);
      }

      return new Vec3(var4, var5, var6);
   }

   public void h(BlockPos var1) {
      for (int var2 = 0; var2 < this.worldAccesses.size(); var2++) {
         this.worldAccesses.get(var2).markBlockForUpdate(var1);
      }
   }

   public boolean handleMaterialAcceleration(AxisAlignedBB var1, Material var2, Entity var3) {
      int var4 = MathHelper.floor_double(var1.a);
      int var5 = MathHelper.floor_double(var1.d + 1.0);
      int var6 = MathHelper.floor_double(var1.b);
      int var7 = MathHelper.floor_double(var1.e + 1.0);
      int var8 = MathHelper.floor_double(var1.c);
      int var9 = MathHelper.floor_double(var1.f + 1.0);
      if (!this.isAreaLoaded(var4, var6, var8, var5, var7, var9, true)) {
         return false;
      } else {
         boolean var10 = false;
         Vec3 var11 = new Vec3(0.0, 0.0, 0.0);
         BlockPos$MutableBlockPos var12 = new BlockPos$MutableBlockPos();

         for (int var13 = var4; var13 < var5; var13++) {
            for (int var14 = var6; var14 < var7; var14++) {
               for (int var15 = var8; var15 < var9; var15++) {
                  var12.set(var13, var14, var15);
                  IBlockState var16 = this.getBlockState(var12);
                  Block var17 = var16.getBlock();
                  if (var17.getMaterial() == var2) {
                     double var18 = var14 + 1 - BlockLiquid.getLiquidHeightPercent(var16.getValue(BlockLiquid.b));
                     if (var7 >= var18) {
                        var10 = true;
                        var11 = var17.modifyAcceleration(this, var12, var3, var11);
                     }
                  }
               }
            }
         }

         if (var11.lengthVector() > 0.0 && var3.isPushedByWater()) {
            var11 = var11.normalize();
            double var21 = 0.014;
            var3.v = var3.v + var11.xCoord * var21;
            var3.w = var3.w + var11.yCoord * var21;
            var3.x = var3.x + var11.zCoord * var21;
         }

         return var10;
      }
   }

   public void onEntityAdded(Entity var1) {
      for (int var2 = 0; var2 < this.worldAccesses.size(); var2++) {
         this.worldAccesses.get(var2).onEntityAdded(var1);
      }
   }

   public void loadEntities(Collection<Entity> var1) {
      this.f.addAll(var1);

      for (Entity var3 : var1) {
         this.onEntityAdded(var3);
      }
   }

   public MapStorage T() {
      return this.z;
   }

   public void a(Entity var1, String var2, float var3, float var4) {
      for (int var5 = 0; var5 < this.worldAccesses.size(); var5++) {
         this.worldAccesses.get(var5).playSound(var2, var1.s, var1.t, var1.u, var3, var4);
      }
   }

   public <T extends Entity> T findNearestEntityWithinAABB(Class<? extends T> var1, AxisAlignedBB var2, T var3) {
      List var4 = this.getEntitiesWithinAABB(var1, var2);
      Entity var5 = null;
      double var6 = Double.MAX_VALUE;

      for (int var8 = 0; var8 < var4.size(); var8++) {
         Entity var9 = (Entity)var4.get(var8);
         if (var9 != var3 && EntitySelectors.NOT_SPECTATING.apply(var9)) {
            double var10 = var3.h(var9);
            if (var10 <= var6) {
               var5 = var9;
               var6 = var10;
            }
         }
      }

      return (T)var5;
   }

   public boolean isValid(BlockPos var1) {
      return var1.getX() >= -30000000 && var1.getZ() >= -30000000 && var1.getX() < 30000000 && var1.getZ() < 30000000 && var1.getY() >= 0 && var1.getY() < 256;
   }

   public List<Entity> a(Entity var1, AxisAlignedBB var2, Predicate<? super Entity> var3) {
      ArrayList var4 = Lists.newArrayList();
      int var5 = MathHelper.floor_double((var2.a - 2.0) / 16.0);
      int var6 = MathHelper.floor_double((var2.d + 2.0) / 16.0);
      int var7 = MathHelper.floor_double((var2.c - 2.0) / 16.0);
      int var8 = MathHelper.floor_double((var2.f + 2.0) / 16.0);

      for (int var9 = var5; var9 <= var6; var9++) {
         for (int var10 = var7; var10 <= var8; var10++) {
            if (this.a(var9, var10, true)) {
               this.a(var9, var10).getEntitiesWithinAABBForEntity(var1, var2, var4, var3);
            }
         }
      }

      return var4;
   }
}
