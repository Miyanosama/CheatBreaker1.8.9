package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEventData;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandDebug;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.ScoreboardSaveData;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ReportedException;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.village.VillageCollection;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGeneratorBonusChest;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldServer extends World implements IThreadListener {
   public Set<NextTickListEntry> pendingTickListEntriesHashSet = Sets.newHashSet();
   public EntityAIEatGrass field_0017;
   public AchievementList field_0009;
   public int updateEntityTick;
   public WorldServer$ServerBlockEventList[] blockEventQueue;
   public static List<WeightedRandomChestContent> bonusChestContent = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.stick, 0, 1, 3, 10),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.planks), 0, 1, 3, 10),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 1, 3, 10),
         new WeightedRandomChestContent(Items.stone_axe, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.wooden_axe, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.stone_pickaxe, 0, 1, 1, 3),
         new WeightedRandomChestContent(Items.wooden_pickaxe, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.apple, 0, 2, 3, 5),
         new WeightedRandomChestContent(Items.bread, 0, 2, 3, 3),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log2), 0, 1, 3, 10)
      }
   );
   public int blockEventCacheIndex;
   public boolean disableLevelSaving;
   public CommandDebug field_0005;
   public PlayerManager thePlayerManager;
   public SpawnerAnimals mobSpawner;
   public VillageSiege villageSiege;
   public MinecraftServer mcServer;
   public Map<UUID, Entity> entitiesByUuid;
   public boolean allPlayersSleeping;
   public Teleporter worldTeleporter;
   public List<NextTickListEntry> pendingTickListEntriesThisTick;
   public TreeSet<NextTickListEntry> pendingTickListEntriesTreeSet = new TreeSet<>();
   public EntityTracker theEntityTracker;
   public static Logger logger = LogManager.getLogger();
   public ChunkProviderServer theChunkProviderServer;

   public void resetRainAndThunder() {
      this.x.setRainTime(0);
      this.x.setRaining(false);
      this.x.setThunderTime(0);
      this.x.setThundering(false);
   }

   @Override
   public void updateEntities() {
      if (this.j.isEmpty()) {
         if (this.updateEntityTick++ >= 1200) {
            return;
         }
      } else {
         this.resetUpdateEntityTick();
      }

      super.updateEntities();
   }

   public boolean canSpawnNPCs() {
      return this.mcServer.method_06888();
   }

   public void createBonusChest() {
      WorldGeneratorBonusChest var1 = new WorldGeneratorBonusChest(bonusChestContent, 10);

      for (int var2 = 0; var2 < 10; var2++) {
         int var3 = this.x.getSpawnX() + this.s.nextInt(6) - this.s.nextInt(6);
         int var4 = this.x.getSpawnZ() + this.s.nextInt(6) - this.s.nextInt(6);
         BlockPos var5 = this.getTopSolidOrLiquidBlock(new BlockPos(var3, 0, var4)).up();
         if (var1.generate(this, this.s, var5)) {
            break;
         }
      }
   }

   @Override
   public void setEntityState(Entity var1, byte var2) {
      this.getEntityTracker().func_151248_b(var1, new S19PacketEntityStatus(var1, var2));
   }

   @Override
   public void initialize(WorldSettings var1) {
      if (!this.x.isInitialized()) {
         try {
            this.createSpawnPosition(var1);
            if (this.x.getTerrainType() == WorldType.DEBUG_WORLD) {
               this.setDebugWorldSettings();
            }

            super.initialize(var1);
         } catch (Throwable var6) {
            CrashReport var3 = CrashReport.makeCrashReport(var6, "Exception initializing level");

            try {
               this.addWorldInfoToCrashReport(var3);
            } catch (Throwable var5) {
            }

            throw new ReportedException(var3);
         }

         this.x.setServerInitialized(true);
      }
   }

   @Override
   public ListenableFuture<Object> addScheduledTask(Runnable var1) {
      return this.mcServer.addScheduledTask(var1);
   }

   public boolean areAllPlayersAsleep() {
      if (this.allPlayersSleeping && !this.D) {
         for (EntityPlayer var2 : this.j) {
            if (var2.isSpectator() || !var2.isPlayerFullyAsleep()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public BlockPos getSpawnCoordinate() {
      return this.t.getSpawnCoordinate();
   }

   public BiomeGenBase$SpawnListEntry getSpawnListEntryForTypeAt(EnumCreatureType var1, BlockPos var2) {
      List var3 = this.N().getPossibleCreatures(var1, var2);
      return var3 != null && !var3.isEmpty() ? WeightedRandom.getRandomItem(this.s, var3) : null;
   }

   @Override
   public void tick() {
      super.tick();
      if (this.P().isHardcoreModeEnabled() && this.getDifficulty() != EnumDifficulty.HARD) {
         this.P().setDifficulty(EnumDifficulty.HARD);
      }

      this.t.getWorldChunkManager().cleanupCache();
      if (this.areAllPlayersAsleep()) {
         if (this.Q().getBoolean("doDaylightCycle")) {
            long var1 = this.x.getWorldTime() + (5463022785880194497L & -5463022787887866396L);
            this.x.setWorldTime(var1 - var1 % (1150148577L & 573726154L));
         }

         this.wakeAllPlayers();
      }

      this.B.startSection("mobSpawner");
      if (this.Q().getBoolean("doMobSpawning") && this.x.getTerrainType() != WorldType.DEBUG_WORLD) {
         this.mobSpawner
            .findChunksForSpawning(
               this, this.spawnHostileMobs, this.spawnPeacefulMobs, this.x.getWorldTotalTime() % (134349205L & 1140868496L) == (1627422816L & 140407808L)
            );
      }

      this.B.endStartSection("chunkSource");
      this.v.unloadQueuedChunks();
      int var3 = this.calculateSkylightSubtracted(1.0F);
      if (var3 != this.getSkylightSubtracted()) {
         this.setSkylightSubtracted(var3);
      }

      this.x.setWorldTotalTime(this.x.getWorldTotalTime() + (16943185L & 1621627429L));
      if (this.Q().getBoolean("doDaylightCycle")) {
         this.x.setWorldTime(this.x.getWorldTime() + (-110731646222729199L & 844243459L));
      }

      this.B.endStartSection("tickPending");
      this.tickUpdates(false);
      this.B.endStartSection("tickBlocks");
      this.updateBlocks();
      this.B.endStartSection("chunkMap");
      this.thePlayerManager.updatePlayerInstances();
      this.B.endStartSection("village");
      this.A.tick();
      this.villageSiege.tick();
      this.B.endStartSection("portalForcer");
      this.worldTeleporter.removeStalePortalLocations(this.K());
      this.B.endSection();
      this.sendQueuedBlockEvents();
   }

   public EntityTracker getEntityTracker() {
      return this.theEntityTracker;
   }

   public void setDebugWorldSettings() {
      this.x.setMapFeaturesEnabled(false);
      this.x.setAllowCommands(true);
      this.x.setRaining(false);
      this.x.setThundering(false);
      this.x.setCleanWeatherTime(1000000000);
      this.x.setWorldTime(451602531750690808L & 4200310L);
      this.x.setGameType(WorldSettings$GameType.SPECTATOR);
      this.x.setHardcore(false);
      this.x.setDifficulty(EnumDifficulty.PEACEFUL);
      this.x.setDifficultyLocked(true);
      this.Q().setOrCreateGameRule("doDaylightCycle", "false");
   }

   @Override
   public void updateEntityWithOptionalForce(Entity var1, boolean var2) {
      if (!this.canSpawnAnimals() && (var1 instanceof EntityAnimal || var1 instanceof EntityWaterMob)) {
         var1.setDead();
      }

      if (!this.canSpawnNPCs() && var1 instanceof INpc) {
         var1.setDead();
      }

      super.updateEntityWithOptionalForce(var1, var2);
   }

   @Override
   public void onEntityAdded(Entity var1) {
      super.onEntityAdded(var1);
      this.l.addKey(var1.F(), var1);
      this.entitiesByUuid.put(var1.aK(), var1);
      Entity[] var2 = var1.getParts();
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            this.l.addKey(var2[var3].F(), var2[var3]);
         }
      }
   }

   public void createSpawnPosition(WorldSettings var1) {
      if (!this.t.canRespawnHere()) {
         this.x.setSpawn(BlockPos.ORIGIN.up(this.t.getAverageGroundLevel()));
      } else if (this.x.getTerrainType() == WorldType.DEBUG_WORLD) {
         this.x.setSpawn(BlockPos.ORIGIN.up());
      } else {
         this.findingSpawnPoint = true;
         WorldChunkManager var2 = this.t.getWorldChunkManager();
         List var3 = var2.getBiomesToSpawnIn();
         Random var4 = new Random(this.J());
         BlockPos var5 = var2.findBiomePosition(0, 0, 256, var3, var4);
         int var6 = 0;
         int var7 = this.t.getAverageGroundLevel();
         int var8 = 0;
         if (var5 != null) {
            var6 = var5.getX();
            var8 = var5.getZ();
         } else {
            logger.warn("Unable to find spawn biome");
         }

         int var9 = 0;

         while (!this.t.canCoordinateBeSpawn(var6, var8)) {
            var6 += var4.nextInt(64) - var4.nextInt(64);
            var8 += var4.nextInt(64) - var4.nextInt(64);
            if (++var9 == 1000) {
               break;
            }
         }

         this.x.setSpawn(new BlockPos(var6, var7, var8));
         this.findingSpawnPoint = false;
         if (var1.method_26031()) {
            this.createBonusChest();
         }
      }
   }

   @Override
   public void updateBlockTick(BlockPos var1, Block var2, int var3, int var4) {
      NextTickListEntry var5 = new NextTickListEntry(var1, var2);
      byte var6 = 0;
      if (this.e && var2.getMaterial() != Material.air) {
         if (var2.requiresUpdates()) {
            var6 = 8;
            if (this.isAreaLoaded(var5.position.add(-var6, -var6, -var6), var5.position.add((int)var6, (int)var6, (int)var6))) {
               IBlockState var7 = this.getBlockState(var5.position);
               if (var7.getBlock().getMaterial() != Material.air && var7.getBlock() == var5.getBlock()) {
                  var7.getBlock().updateTick(this, var5.position, var7, this.s);
               }
            }

            return;
         }

         var3 = 1;
      }

      if (this.isAreaLoaded(var1.add(-var6, -var6, -var6), var1.add((int)var6, (int)var6, (int)var6))) {
         if (var2.getMaterial() != Material.air) {
            var5.setScheduledTime(var3 + this.x.getWorldTotalTime());
            var5.setPriority(var4);
         }

         if (!this.pendingTickListEntriesHashSet.contains(var5)) {
            this.pendingTickListEntriesHashSet.add(var5);
            this.pendingTickListEntriesTreeSet.add(var5);
         }
      }
   }

   @Override
   public boolean tickUpdates(boolean var1) {
      if (this.x.getTerrainType() == WorldType.DEBUG_WORLD) {
         return false;
      } else {
         int var2 = this.pendingTickListEntriesTreeSet.size();
         if (var2 != this.pendingTickListEntriesHashSet.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
         } else {
            if (var2 > 1000) {
               var2 = 1000;
            }

            this.B.startSection("cleaning");

            for (int var3 = 0; var3 < var2; var3++) {
               NextTickListEntry var4 = this.pendingTickListEntriesTreeSet.first();
               if (!var1 && var4.scheduledTime > this.x.getWorldTotalTime()) {
                  break;
               }

               this.pendingTickListEntriesTreeSet.remove(var4);
               this.pendingTickListEntriesHashSet.remove(var4);
               this.pendingTickListEntriesThisTick.add(var4);
            }

            this.B.endSection();
            this.B.startSection("ticking");
            Iterator var11 = this.pendingTickListEntriesThisTick.iterator();

            while (var11.hasNext()) {
               NextTickListEntry var12 = (NextTickListEntry)var11.next();
               var11.remove();
               byte var5 = 0;
               if (this.isAreaLoaded(var12.position.add(-var5, -var5, -var5), var12.position.add((int)var5, (int)var5, (int)var5))) {
                  IBlockState var6 = this.getBlockState(var12.position);
                  if (var6.getBlock().getMaterial() != Material.air && Block.isEqualTo(var6.getBlock(), var12.getBlock())) {
                     try {
                        var6.getBlock().updateTick(this, var12.position, var6, this.s);
                     } catch (Throwable var10) {
                        CrashReport var8 = CrashReport.makeCrashReport(var10, "Exception while ticking a block");
                        CrashReportCategory var9 = var8.makeCategory("Block being ticked");
                        CrashReportCategory.addBlockInfo(var9, var12.position, var6);
                        throw new ReportedException(var8);
                     }
                  }
               } else {
                  this.scheduleUpdate(var12.position, var12.getBlock(), 0);
               }
            }

            this.B.endSection();
            this.pendingTickListEntriesThisTick.clear();
            return !this.pendingTickListEntriesTreeSet.isEmpty();
         }
      }
   }

   @Override
   public void updateWeather() {
      boolean var1 = this.isRaining();
      super.updateWeather();
      if (this.o != this.p) {
         this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension(new S2BPacketChangeGameState(7, this.p), this.t.getDimensionId());
      }

      if (this.q != this.r) {
         this.mcServer.getConfigurationManager().sendPacketToAllPlayersInDimension(new S2BPacketChangeGameState(8, this.r), this.t.getDimensionId());
      }

      if (var1 != this.isRaining()) {
         if (var1) {
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(2, 0.0F));
         } else {
            this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(1, 0.0F));
         }

         this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(7, this.p));
         this.mcServer.getConfigurationManager().sendPacketToAllPlayers(new S2BPacketChangeGameState(8, this.r));
      }
   }

   @Override
   public IChunkProvider createChunkProvider() {
      IChunkLoader var1 = this.w.getChunkLoader(this.t);
      this.theChunkProviderServer = new ChunkProviderServer(this, var1, this.t.createChunkGenerator());
      return this.theChunkProviderServer;
   }

   @Override
   public boolean addWeatherEffect(Entity var1) {
      if (super.addWeatherEffect(var1)) {
         this.mcServer.getConfigurationManager().sendToAllNear(var1.s, var1.t, var1.u, 512.0, this.t.getDimensionId(), new S2CPacketSpawnGlobalEntity(var1));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public World init() {
      this.z = new MapStorage(this.w);
      String var1 = VillageCollection.fileNameForProvider(this.t);
      VillageCollection var2 = (VillageCollection)this.z.loadData(VillageCollection.class, var1);
      if (var2 == null) {
         this.A = new VillageCollection(this);
         this.z.setData(var1, this.A);
      } else {
         this.A = var2;
         this.A.setWorldsForAll(this);
      }

      this.C = new ServerScoreboard(this.mcServer);
      ScoreboardSaveData var3 = (ScoreboardSaveData)this.z.loadData(ScoreboardSaveData.class, "scoreboard");
      if (var3 == null) {
         var3 = new ScoreboardSaveData();
         this.z.setData("scoreboard", var3);
      }

      var3.setScoreboard(this.C);
      ((ServerScoreboard)this.C).func_96547_a(var3);
      this.af().setCenter(this.x.getBorderCenterX(), this.x.getBorderCenterZ());
      this.af().setDamageAmount(this.x.getBorderDamagePerBlock());
      this.af().setDamageBuffer(this.x.getBorderSafeZone());
      this.af().setWarningDistance(this.x.getBorderWarningDistance());
      this.af().setWarningTime(this.x.getBorderWarningTime());
      if (this.x.getBorderLerpTime() > (1118790L & 1402994993L)) {
         this.af().setTransition(this.x.getBorderSize(), this.x.getBorderLerpTarget(), this.x.getBorderLerpTime());
      } else {
         this.af().setTransition(this.x.getBorderSize());
      }

      return this;
   }

   public Entity getEntityFromUuid(UUID var1) {
      return this.entitiesByUuid.get(var1);
   }

   @Override
   public boolean isCallingFromMinecraftThread() {
      return this.mcServer.isCallingFromMinecraftThread();
   }

   public void saveLevel() {
      this.I();
      this.x.setBorderSize(this.af().getDiameter());
      this.x.getBorderCenterX(this.af().getCenterX());
      this.x.getBorderCenterZ(this.af().getCenterZ());
      this.x.setBorderSafeZone(this.af().getDamageBuffer());
      this.x.setBorderDamagePerBlock(this.af().getDamageAmount());
      this.x.setBorderWarningDistance(this.af().getWarningDistance());
      this.x.setBorderWarningTime(this.af().getWarningTime());
      this.x.setBorderLerpTarget(this.af().getTargetSize());
      this.x.setBorderLerpTime(this.af().getTimeUntilTarget());
      this.w.saveWorldInfoWithPlayer(this.x, this.mcServer.getConfigurationManager().getHostPlayerData());
      this.z.saveAllData();
   }

   @Override
   public void updateBlocks() {
      super.updateBlocks();
      if (this.x.getTerrainType() == WorldType.DEBUG_WORLD) {
         for (ChunkCoordIntPair var2 : this.E) {
            this.a(var2.chunkXPos, var2.chunkZPos).func_150804_b(false);
         }
      } else {
         int var20 = 0;
         int var21 = 0;

         for (ChunkCoordIntPair var4 : this.E) {
            int var5 = var4.chunkXPos * 16;
            int var6 = var4.chunkZPos * 16;
            this.B.startSection("getChunk");
            Chunk var7 = this.a(var4.chunkXPos, var4.chunkZPos);
            this.a(var5, var6, var7);
            this.B.endStartSection("tickChunk");
            var7.func_150804_b(false);
            this.B.endStartSection("thunder");
            if (this.s.nextInt(100000) == 0 && this.isRaining() && this.isThundering()) {
               this.updateLCG = this.updateLCG * 3 + 1013904223;
               int var8 = this.updateLCG >> 2;
               BlockPos var9 = this.adjustPosToNearbyEntity(new BlockPos(var5 + (var8 & 15), 0, var6 + (var8 >> 8 & 15)));
               if (this.isRainingAt(var9)) {
                  this.addWeatherEffect(new EntityLightningBolt(this, var9.getX(), var9.getY(), var9.getZ()));
               }
            }

            this.B.endStartSection("iceandsnow");
            if (this.s.nextInt(16) == 0) {
               this.updateLCG = this.updateLCG * 3 + 1013904223;
               int var22 = this.updateLCG >> 2;
               BlockPos var24 = this.getPrecipitationHeight(new BlockPos(var5 + (var22 & 15), 0, var6 + (var22 >> 8 & 15)));
               BlockPos var10 = var24.down();
               if (this.method_09969(var10)) {
                  this.setBlockState(var10, Blocks.ice.getDefaultState());
               }

               if (this.isRaining() && this.canSnowAt(var24, true)) {
                  this.setBlockState(var24, Blocks.snow_layer.getDefaultState());
               }

               if (this.isRaining() && this.getBiomeGenForCoords(var10).canRain()) {
                  this.getBlockState(var10).getBlock().fillWithRain(this, var10);
               }
            }

            this.B.endStartSection("tickBlocks");
            int var23 = this.Q().getInt("randomTickSpeed");
            if (var23 > 0) {
               for (ExtendedBlockStorage var12 : var7.getBlockStorageArray()) {
                  if (var12 != null && var12.getNeedsRandomTick()) {
                     for (int var13 = 0; var13 < var23; var13++) {
                        this.updateLCG = this.updateLCG * 3 + 1013904223;
                        int var14 = this.updateLCG >> 2;
                        int var15 = var14 & 15;
                        int var16 = var14 >> 8 & 15;
                        int var17 = var14 >> 16 & 15;
                        var21++;
                        IBlockState var18 = var12.get(var15, var17, var16);
                        Block var19 = var18.getBlock();
                        if (var19.getTickRandomly()) {
                           var20++;
                           var19.randomTick(this, new BlockPos(var15 + var5, var17 + var12.getYLocation(), var16 + var6), var18, this.s);
                        }
                     }
                  }
               }
            }

            this.B.endSection();
         }
      }
   }

   public boolean canCreatureTypeSpawnHere(EnumCreatureType var1, BiomeGenBase$SpawnListEntry var2, BlockPos var3) {
      List var4 = this.N().getPossibleCreatures(var1, var3);
      return var4 != null && !var4.isEmpty() ? var4.contains(var2) : false;
   }

   @Override
   public void updateAllPlayersSleepingFlag() {
      this.allPlayersSleeping = false;
      if (!this.j.isEmpty()) {
         int var1 = 0;
         int var2 = 0;

         for (EntityPlayer var4 : this.j) {
            if (var4.isSpectator()) {
               var1++;
            } else if (var4.bJ()) {
               var2++;
            }
         }

         this.allPlayersSleeping = var2 > 0 && var2 >= this.j.size() - var1;
      }
   }

   @Override
   public boolean isBlockTickPending(BlockPos var1, Block var2) {
      NextTickListEntry var3 = new NextTickListEntry(var1, var2);
      return this.pendingTickListEntriesThisTick.contains(var3);
   }

   public void flush() {
      this.w.flush();
   }

   public void wakeAllPlayers() {
      this.allPlayersSleeping = false;

      for (EntityPlayer var2 : this.j) {
         if (var2.bJ()) {
            var2.wakeUpPlayer(false, false, true);
         }
      }

      this.resetRainAndThunder();
   }

   public WorldServer(MinecraftServer var1, ISaveHandler var2, WorldInfo var3, int var4, Profiler var5) {
      super(var2, var3, WorldProvider.getProviderForDimension(var4), var5, false);
      this.entitiesByUuid = Maps.newHashMap();
      this.mobSpawner = new SpawnerAnimals();
      this.villageSiege = new VillageSiege(this);
      this.blockEventQueue = new WorldServer$ServerBlockEventList[]{new WorldServer$ServerBlockEventList(null), new WorldServer$ServerBlockEventList(null)};
      this.pendingTickListEntriesThisTick = Lists.newArrayList();
      this.mcServer = var1;
      this.theEntityTracker = new EntityTracker(this);
      this.thePlayerManager = new PlayerManager(this);
      this.t.registerWorld(this);
      this.v = this.createChunkProvider();
      this.worldTeleporter = new Teleporter(this);
      this.calculateInitialSkylight();
      this.C();
      this.af().setSize(var1.getMaxWorldSize());
   }

   public boolean canSpawnAnimals() {
      return this.mcServer.getCanSpawnAnimals();
   }

   @Override
   public int getRenderDistanceChunks() {
      return this.mcServer.getConfigurationManager().getViewDistance();
   }

   public void spawnParticle(
      EnumParticleTypes var1, double var2, double var4, double var6, int var8, double var9, double var11, double var13, double var15, int... var17
   ) {
      this.spawnParticle(var1, false, var2, var4, var6, var8, var9, var11, var13, var15, var17);
   }

   public List<TileEntity> getTileEntitiesIn(int var1, int var2, int var3, int var4, int var5, int var6) {
      ArrayList var7 = Lists.newArrayList();

      for (int var8 = 0; var8 < this.h.size(); var8++) {
         TileEntity var9 = this.h.get(var8);
         BlockPos var10 = var9.v();
         if (var10.getX() >= var1 && var10.getY() >= var2 && var10.getZ() >= var3 && var10.getX() < var4 && var10.getY() < var5 && var10.getZ() < var6) {
            var7.add(var9);
         }
      }

      return var7;
   }

   public void resetUpdateEntityTick() {
      this.updateEntityTick = 0;
   }

   public MinecraftServer getMinecraftServer() {
      return this.mcServer;
   }

   public BlockPos adjustPosToNearbyEntity(BlockPos var1) {
      BlockPos var2 = this.getPrecipitationHeight(var1);
      AxisAlignedBB var3 = new AxisAlignedBB(var2, new BlockPos(var2.getX(), this.getHeight(), var2.getZ())).expand(3.0, 3.0, 3.0);
      List var4 = this.getEntitiesWithinAABB(EntityLivingBase.class, var3, new WorldServer$1(this));
      return !var4.isEmpty() ? ((EntityLivingBase)var4.get(this.s.nextInt(var4.size()))).getPosition() : var2;
   }

   public PlayerManager getPlayerManager() {
      return this.thePlayerManager;
   }

   public boolean fireBlockEvent(BlockEventData var1) {
      IBlockState var2 = this.getBlockState(var1.getPosition());
      return var2.getBlock() == var1.getBlock()
         ? var2.getBlock().onBlockEventReceived(this, var1.getPosition(), var2, var1.getEventID(), var1.getEventParameter())
         : false;
   }

   @Override
   public void onEntityRemoved(Entity var1) {
      super.onEntityRemoved(var1);
      this.l.removeObject(var1.F());
      this.entitiesByUuid.remove(var1.aK());
      Entity[] var2 = var1.getParts();
      if (var2 != null) {
         for (int var3 = 0; var3 < var2.length; var3++) {
            this.l.removeObject(var2[var3].F());
         }
      }
   }

   public void sendQueuedBlockEvents() {
      while (!this.blockEventQueue[this.blockEventCacheIndex].isEmpty()) {
         int var1 = this.blockEventCacheIndex;
         this.blockEventCacheIndex ^= 1;

         for (BlockEventData var3 : this.blockEventQueue[var1]) {
            if (this.fireBlockEvent(var3)) {
               this.mcServer
                  .getConfigurationManager()
                  .sendToAllNear(
                     var3.getPosition().getX(),
                     var3.getPosition().getY(),
                     var3.getPosition().getZ(),
                     64.0,
                     this.t.getDimensionId(),
                     new S24PacketBlockAction(var3.getPosition(), var3.getBlock(), var3.getEventID(), var3.getEventParameter())
                  );
            }
         }

         this.blockEventQueue[var1].clear();
      }
   }

   public void spawnParticle(
      EnumParticleTypes var1,
      boolean var2,
      double var3,
      double var5,
      double var7,
      int var9,
      double var10,
      double var12,
      double var14,
      double var16,
      int... var18
   ) {
      S2APacketParticles var19 = new S2APacketParticles(
         var1, var2, (float)var3, (float)var5, (float)var7, (float)var10, (float)var12, (float)var14, (float)var16, var9, var18
      );

      for (int var20 = 0; var20 < this.j.size(); var20++) {
         EntityPlayerMP var21 = (EntityPlayerMP)this.j.get(var20);
         BlockPos var22 = var21.getPosition();
         double var23 = var22.distanceSq(var3, var5, var7);
         if (var23 <= 256.0 || var2 && var23 <= 65536.0) {
            var21.playerNetServerHandler.sendPacket(var19);
         }
      }
   }

   @Override
   public void addBlockEvent(BlockPos var1, Block var2, int var3, int var4) {
      BlockEventData var5 = new BlockEventData(var1, var2, var3, var4);

      for (BlockEventData var7 : this.blockEventQueue[this.blockEventCacheIndex]) {
         if (var7.equals(var5)) {
            return;
         }
      }

      this.blockEventQueue[this.blockEventCacheIndex].add(var5);
   }

   @Override
   public Explosion newExplosion(Entity var1, double var2, double var4, double var6, float var8, boolean var9, boolean var10) {
      Explosion var11 = new Explosion(this, var1, var2, var4, var6, var8, var9, var10);
      var11.doExplosionA();
      var11.doExplosionB(false);
      if (!var10) {
         var11.clearAffectedBlockPositions();
      }

      for (EntityPlayer var13 : this.j) {
         if (var13.e(var2, var4, var6) < 4096.0) {
            ((EntityPlayerMP)var13)
               .playerNetServerHandler
               .sendPacket(new S27PacketExplosion(var2, var4, var6, var8, var11.getAffectedBlockPositions(), var11.getPlayerKnockbackMap().get(var13)));
         }
      }

      return var11;
   }

   @Override
   public void scheduleUpdate(BlockPos var1, Block var2, int var3) {
      this.updateBlockTick(var1, var2, var3, 0);
   }

   public void saveAllChunks(boolean var1, IProgressUpdate var2) {
      if (this.v.canSave()) {
         if (var2 != null) {
            var2.displaySavingString("Saving level");
         }

         this.saveLevel();
         if (var2 != null) {
            var2.displayLoadingString("Saving chunks");
         }

         this.v.saveChunks(var1, var2);

         for (Chunk var4 : Lists.newArrayList(this.theChunkProviderServer.func_152380_a())) {
            if (var4 != null && !this.thePlayerManager.hasPlayerInstance(var4.a, var4.b)) {
               this.theChunkProviderServer.dropChunk(var4.a, var4.b);
            }
         }
      }
   }

   @Override
   public List<NextTickListEntry> getPendingBlockUpdates(Chunk var1, boolean var2) {
      ChunkCoordIntPair var3 = var1.getChunkCoordIntPair();
      int var4 = (var3.chunkXPos << 4) - 2;
      int var5 = var4 + 16 + 2;
      int var6 = (var3.chunkZPos << 4) - 2;
      int var7 = var6 + 16 + 2;
      return this.func_175712_a(new StructureBoundingBox(var4, 0, var6, var5, 256, var7), var2);
   }

   @Override
   public List<NextTickListEntry> func_175712_a(StructureBoundingBox var1, boolean var2) {
      ArrayList var3 = null;

      for (int var4 = 0; var4 < 2; var4++) {
         Iterator var5;
         if (var4 == 0) {
            var5 = this.pendingTickListEntriesTreeSet.iterator();
         } else {
            var5 = this.pendingTickListEntriesThisTick.iterator();
         }

         while (var5.hasNext()) {
            NextTickListEntry var6 = (NextTickListEntry)var5.next();
            BlockPos var7 = var6.position;
            if (var7.getX() >= var1.minX && var7.getX() < var1.maxX && var7.getZ() >= var1.minZ && var7.getZ() < var1.maxZ) {
               if (var2) {
                  this.pendingTickListEntriesHashSet.remove(var6);
                  var5.remove();
               }

               if (var3 == null) {
                  var3 = Lists.newArrayList();
               }

               var3.add(var6);
            }
         }
      }

      return var3;
   }

   @Override
   public void setInitialSpawnLocation() {
      if (this.x.getSpawnY() <= 0) {
         this.x.setSpawnY(this.F() + 1);
      }

      int var1 = this.x.getSpawnX();
      int var2 = this.x.getSpawnZ();
      int var3 = 0;

      while (this.getGroundAboveSeaLevel(new BlockPos(var1, 0, var2)).getMaterial() == Material.air) {
         var1 += this.s.nextInt(8) - this.s.nextInt(8);
         var2 += this.s.nextInt(8) - this.s.nextInt(8);
         if (++var3 == 10000) {
            break;
         }
      }

      this.x.setSpawnX(var1);
      this.x.setSpawnZ(var2);
   }

   @Override
   public void scheduleBlockUpdate(BlockPos var1, Block var2, int var3, int var4) {
      NextTickListEntry var5 = new NextTickListEntry(var1, var2);
      var5.setPriority(var4);
      if (var2.getMaterial() != Material.air) {
         var5.setScheduledTime(var3 + this.x.getWorldTotalTime());
      }

      if (!this.pendingTickListEntriesHashSet.contains(var5)) {
         this.pendingTickListEntriesHashSet.add(var5);
         this.pendingTickListEntriesTreeSet.add(var5);
      }
   }

   public void method_03794() {
      if (this.v.canSave()) {
         this.v.saveExtraData();
      }
   }

   public Teleporter getDefaultTeleporter() {
      return this.worldTeleporter;
   }

   @Override
   public boolean isBlockModifiable(EntityPlayer var1, BlockPos var2) {
      return !this.mcServer.isBlockProtected(this, var2, var1) && this.af().contains(var2);
   }
}
