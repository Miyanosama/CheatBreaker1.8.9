package net.minecraft.server.management;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.src.Config;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.optifine.ChunkPosComparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerManager {
   public WorldServer theWorldServer;
   public Map<EntityPlayerMP, Set<ChunkCoordIntPair>> mapPlayerPendingEntries;
   public static Logger pmLogger = LogManager.getLogger();
   public long previousTotalWorldTime;
   public int[][] xzDirectionsConst;
   public List<PlayerManager.PlayerInstance> playerInstancesToUpdate;
   public LongHashMap<PlayerManager.PlayerInstance> playerInstances;
   public int playerViewRadius;
   public List<EntityPlayerMP> players = Lists.newArrayList();
   public List<PlayerManager.PlayerInstance> playerInstanceList;

   public Set<ChunkCoordIntPair> getPendingEntriesSafe(EntityPlayerMP var1) {
      Set var2 = this.mapPlayerPendingEntries.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         int var3 = Math.min(this.playerViewRadius, 8);
         int var4 = this.playerViewRadius * 2 + 1;
         int var5 = var3 * 2 + 1;
         int var6 = var4 * var4 - var5 * var5;
         var6 = Math.max(var6, 16);
         HashSet var7 = new HashSet(var6);
         this.mapPlayerPendingEntries.put(var1, var7);
         return var7;
      }
   }

   public void updatePlayerInstances() {
      Set var1 = this.mapPlayerPendingEntries.entrySet();
      Iterator var2 = var1.iterator();

      while (var2.hasNext()) {
         Entry var3 = (Entry)var2.next();
         Set var4 = (Set)var3.getValue();
         if (!var4.isEmpty()) {
            EntityPlayerMP var5 = (EntityPlayerMP)var3.getKey();
            if (var5.o != this.theWorldServer) {
               var2.remove();
            } else {
               int var6 = this.playerViewRadius / 3 + 1;
               if (!Config.isLazyChunkLoading()) {
                  var6 = this.playerViewRadius * 2 + 1;
               }

               for (ChunkCoordIntPair var8 : (Iterable<ChunkCoordIntPair>)(Iterable<?>)(this.getNearest(var4, var5, var6))) {
                  PlayerManager.PlayerInstance var9 = this.getPlayerInstance(var8.chunkXPos, var8.chunkZPos, true);
                  var9.addPlayer(var5);
                  var4.remove(var8);
               }
            }
         }
      }

      long var10 = this.theWorldServer.K();
      if (var10 - this.previousTotalWorldTime > 8000L) {
         this.previousTotalWorldTime = var10;

         for (int var11 = 0; var11 < this.playerInstanceList.size(); var11++) {
            PlayerManager.PlayerInstance var14 = this.playerInstanceList.get(var11);
            var14.onUpdate();
            var14.processChunk();
         }
      } else {
         for (int var12 = 0; var12 < this.playerInstancesToUpdate.size(); var12++) {
            PlayerManager.PlayerInstance var15 = this.playerInstancesToUpdate.get(var12);
            var15.onUpdate();
         }
      }

      this.playerInstancesToUpdate.clear();
      if (this.players.isEmpty()) {
         WorldProvider var13 = this.theWorldServer.t;
         if (!var13.canRespawnHere()) {
            this.theWorldServer.theChunkProviderServer.unloadAllChunks();
         }
      }
   }

   public static int getFurthestViewableBlock(int var0) {
      return var0 * 16 - 16;
   }

   public PriorityQueue<ChunkCoordIntPair> getNearest(Set<ChunkCoordIntPair> var1, EntityPlayerMP var2, int var3) {
      float var4 = var2.y + 90.0F;

      while (var4 <= -180.0F) {
         var4 += 360.0F;
      }

      while (var4 > 180.0F) {
         var4 -= 360.0F;
      }

      double var5 = var4 * (Math.PI / 180.0);
      double var7 = var2.z;
      double var9 = var7 * (Math.PI / 180.0);
      ChunkPosComparator var11 = new ChunkPosComparator(var2.chunkCoordX, var2.chunkCoordZ, var5, var9);
      Comparator var12 = Collections.reverseOrder(var11);
      PriorityQueue var13 = new PriorityQueue(var3, var12);

      for (ChunkCoordIntPair var15 : var1) {
         if (var13.size() < var3) {
            var13.add(var15);
         } else {
            ChunkCoordIntPair var16 = (ChunkCoordIntPair)var13.peek();
            if (var11.compare(var15, var16) < 0) {
               var13.remove();
               var13.add(var15);
            }
         }
      }

      return var13;
   }

   public void setPlayerViewRadius(int var1) {
      var1 = MathHelper.clamp_int(var1, 3, 64);
      if (var1 != this.playerViewRadius) {
         int var2 = var1 - this.playerViewRadius;

         for (EntityPlayerMP var4 : Lists.newArrayList(this.players)) {
            int var5 = (int)var4.s >> 4;
            int var6 = (int)var4.u >> 4;
            Set var7 = this.getPendingEntriesSafe(var4);
            if (var2 > 0) {
               for (int var12 = var5 - var1; var12 <= var5 + var1; var12++) {
                  for (int var13 = var6 - var1; var13 <= var6 + var1; var13++) {
                     if (Config.isLazyChunkLoading()) {
                        var7.add(new ChunkCoordIntPair(var12, var13));
                     } else {
                        PlayerManager.PlayerInstance var14 = this.getPlayerInstance(var12, var13, true);
                        if (!var14.playersWatchingChunk.contains(var4)) {
                           var14.addPlayer(var4);
                        }
                     }
                  }
               }
            } else {
               for (int var8 = var5 - this.playerViewRadius; var8 <= var5 + this.playerViewRadius; var8++) {
                  for (int var9 = var6 - this.playerViewRadius; var9 <= var6 + this.playerViewRadius; var9++) {
                     if (!this.overlaps(var8, var9, var5, var6, var1)) {
                        var7.remove(new ChunkCoordIntPair(var8, var9));
                        PlayerManager.PlayerInstance var10 = this.getPlayerInstance(var8, var9, true);
                        if (var10 != null) {
                           var10.removePlayer(var4);
                        }
                     }
                  }
               }
            }
         }

         this.playerViewRadius = var1;
      }
   }

   public PlayerManager(WorldServer var1) {
      this.playerInstances = new LongHashMap<>();
      this.playerInstancesToUpdate = Lists.newArrayList();
      this.playerInstanceList = Lists.newArrayList();
      this.xzDirectionsConst = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
      this.mapPlayerPendingEntries = new HashMap<>();
      this.theWorldServer = var1;
      this.setPlayerViewRadius(var1.getMinecraftServer().getConfigurationManager().getViewDistance());
   }

   public void filterChunkLoadQueue(EntityPlayerMP var1) {
      ArrayList var2 = Lists.newArrayList(var1.loadedChunks);
      int var3 = 0;
      int var4 = this.playerViewRadius;
      int var5 = (int)var1.s >> 4;
      int var6 = (int)var1.u >> 4;
      int var7 = 0;
      int var8 = 0;
      ChunkCoordIntPair var9 = this.getPlayerInstance(var5, var6, true).chunkCoords;
      var1.loadedChunks.clear();
      if (var2.contains(var9)) {
         var1.loadedChunks.add(var9);
      }

      for (int var10 = 1; var10 <= var4 * 2; var10++) {
         for (int var11 = 0; var11 < 2; var11++) {
            int[] var12 = this.xzDirectionsConst[var3++ % 4];

            for (int var13 = 0; var13 < var10; var13++) {
               var7 += var12[0];
               var8 += var12[1];
               var9 = this.getPlayerInstance(var5 + var7, var6 + var8, true).chunkCoords;
               if (var2.contains(var9)) {
                  var1.loadedChunks.add(var9);
               }
            }
         }
      }

      var3 %= 4;

      for (int var17 = 0; var17 < var4 * 2; var17++) {
         var7 += this.xzDirectionsConst[var3][0];
         var8 += this.xzDirectionsConst[var3][1];
         var9 = this.getPlayerInstance(var5 + var7, var6 + var8, true).chunkCoords;
         if (var2.contains(var9)) {
            var1.loadedChunks.add(var9);
         }
      }
   }

   public void removePlayer(EntityPlayerMP var1) {
      this.mapPlayerPendingEntries.remove(var1);
      int var2 = (int)var1.managedPosX >> 4;
      int var3 = (int)var1.managedPosZ >> 4;

      for (int var4 = var2 - this.playerViewRadius; var4 <= var2 + this.playerViewRadius; var4++) {
         for (int var5 = var3 - this.playerViewRadius; var5 <= var3 + this.playerViewRadius; var5++) {
            PlayerManager.PlayerInstance var6 = this.getPlayerInstance(var4, var5, false);
            if (var6 != null) {
               var6.removePlayer(var1);
            }
         }
      }

      this.players.remove(var1);
   }

   public PlayerManager.PlayerInstance getPlayerInstance(int var1, int var2, boolean var3) {
      long var4 = var1 + 2147483647L | var2 + 2147483647L << 32;
      PlayerManager.PlayerInstance var6 = this.playerInstances.getValueByKey(var4);
      if (var6 == null && var3) {
         var6 = new PlayerManager.PlayerInstance(var1, var2);
         this.playerInstances.add(var4, var6);
         this.playerInstanceList.add(var6);
      }

      return var6;
   }

   public void updateMountedMovingPlayer(EntityPlayerMP var1) {
      int var2 = (int)var1.s >> 4;
      int var3 = (int)var1.u >> 4;
      double var4 = var1.managedPosX - var1.s;
      double var6 = var1.managedPosZ - var1.u;
      double var8 = var4 * var4 + var6 * var6;
      if (var8 >= 64.0) {
         int var10 = (int)var1.managedPosX >> 4;
         int var11 = (int)var1.managedPosZ >> 4;
         int var12 = this.playerViewRadius;
         int var13 = var2 - var10;
         int var14 = var3 - var11;
         if (var13 != 0 || var14 != 0) {
            Set var15 = this.getPendingEntriesSafe(var1);

            for (int var16 = var2 - var12; var16 <= var2 + var12; var16++) {
               for (int var17 = var3 - var12; var17 <= var3 + var12; var17++) {
                  if (!this.overlaps(var16, var17, var10, var11, var12)) {
                     if (Config.isLazyChunkLoading()) {
                        var15.add(new ChunkCoordIntPair(var16, var17));
                     } else {
                        this.getPlayerInstance(var16, var17, true).addPlayer(var1);
                     }
                  }

                  if (!this.overlaps(var16 - var13, var17 - var14, var2, var3, var12)) {
                     var15.remove(new ChunkCoordIntPair(var16 - var13, var17 - var14));
                     PlayerManager.PlayerInstance var18 = this.getPlayerInstance(var16 - var13, var17 - var14, false);
                     if (var18 != null) {
                        var18.removePlayer(var1);
                     }
                  }
               }
            }

            this.filterChunkLoadQueue(var1);
            var1.managedPosX = var1.s;
            var1.managedPosZ = var1.u;
         }
      }
   }

   public void markBlockForUpdate(BlockPos var1) {
      int var2 = var1.getX() >> 4;
      int var3 = var1.getZ() >> 4;
      PlayerManager.PlayerInstance var4 = this.getPlayerInstance(var2, var3, false);
      if (var4 != null) {
         var4.flagChunkForUpdate(var1.getX() & 15, var1.getY(), var1.getZ() & 15);
      }
   }

   public boolean isPlayerWatchingChunk(EntityPlayerMP var1, int var2, int var3) {
      PlayerManager.PlayerInstance var4 = this.getPlayerInstance(var2, var3, false);
      return var4 != null && var4.playersWatchingChunk.contains(var1) && !var1.loadedChunks.contains(var4.chunkCoords);
   }

   public boolean overlaps(int var1, int var2, int var3, int var4, int var5) {
      int var6 = var1 - var3;
      int var7 = var2 - var4;
      return var6 >= -var5 && var6 <= var5 ? var7 >= -var5 && var7 <= var5 : false;
   }

   public WorldServer getWorldServer() {
      return this.theWorldServer;
   }

   public void addPlayer(EntityPlayerMP var1) {
      int var2 = (int)var1.s >> 4;
      int var3 = (int)var1.u >> 4;
      var1.managedPosX = var1.s;
      var1.managedPosZ = var1.u;
      int var4 = Math.min(this.playerViewRadius, 8);
      int var5 = var2 - var4;
      int var6 = var2 + var4;
      int var7 = var3 - var4;
      int var8 = var3 + var4;
      Set var9 = this.getPendingEntriesSafe(var1);

      for (int var10 = var2 - this.playerViewRadius; var10 <= var2 + this.playerViewRadius; var10++) {
         for (int var11 = var3 - this.playerViewRadius; var11 <= var3 + this.playerViewRadius; var11++) {
            if (var10 >= var5 && var10 <= var6 && var11 >= var7 && var11 <= var8) {
               this.getPlayerInstance(var10, var11, true).addPlayer(var1);
            } else {
               var9.add(new ChunkCoordIntPair(var10, var11));
            }
         }
      }

      this.players.add(var1);
      this.filterChunkLoadQueue(var1);
   }

   public boolean hasPlayerInstance(int var1, int var2) {
      long var3 = var1 + 2147483647L | var2 + 2147483647L << 32;
      return this.playerInstances.getValueByKey(var3) != null;
   }

   public class PlayerInstance {
      public short[] locationOfBlockChange;
      public int flagsYAreasToUpdate;
      public ChunkCoordIntPair chunkCoords;
      public long previousWorldTime;
      public int numBlocksToUpdate;
      public List<EntityPlayerMP> playersWatchingChunk = Lists.newArrayList();

      public void processChunk() {
         this.increaseInhabitedTime(PlayerManager.this.theWorldServer.a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos));
      }

      public void addPlayer(EntityPlayerMP var1) {
         if (this.playersWatchingChunk.contains(var1)) {
            PlayerManager.pmLogger.debug("Failed to add player. {} already is in chunk {}, {}", var1, this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
         } else {
            if (this.playersWatchingChunk.isEmpty()) {
               this.previousWorldTime = PlayerManager.this.theWorldServer.K();
            }

            this.playersWatchingChunk.add(var1);
            var1.loadedChunks.add(this.chunkCoords);
         }
      }

      public void increaseInhabitedTime(Chunk var1) {
         var1.setInhabitedTime(var1.getInhabitedTime() + PlayerManager.this.theWorldServer.K() - this.previousWorldTime);
         this.previousWorldTime = PlayerManager.this.theWorldServer.K();
      }

      public void removePlayer(EntityPlayerMP var1) {
         if (this.playersWatchingChunk.contains(var1)) {
            Chunk var2 = PlayerManager.this.theWorldServer.a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
            if (var2.isPopulated()) {
               var1.playerNetServerHandler.sendPacket(new S21PacketChunkData(var2, true, 0));
            }

            this.playersWatchingChunk.remove(var1);
            var1.loadedChunks.remove(this.chunkCoords);
            if (this.playersWatchingChunk.isEmpty()) {
               long var3 = this.chunkCoords.chunkXPos + 2147483647L | this.chunkCoords.chunkZPos + 2147483647L << 32;
               this.increaseInhabitedTime(var2);
               PlayerManager.this.playerInstances.remove(var3);
               PlayerManager.this.playerInstanceList.remove(this);
               if (this.numBlocksToUpdate > 0) {
                  PlayerManager.this.playerInstancesToUpdate.remove(this);
               }

               PlayerManager.this.getWorldServer().theChunkProviderServer.dropChunk(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
            }
         }
      }

      public void onUpdate() {
         if (this.numBlocksToUpdate != 0) {
            if (this.numBlocksToUpdate == 1) {
               int var8 = (this.locationOfBlockChange[0] >> 12 & 15) + this.chunkCoords.chunkXPos * 16;
               int var10 = this.locationOfBlockChange[0] & 255;
               int var12 = (this.locationOfBlockChange[0] >> 8 & 15) + this.chunkCoords.chunkZPos * 16;
               BlockPos var14 = new BlockPos(var8, var10, var12);
               this.sendToAllPlayersWatchingChunk(new S23PacketBlockChange(PlayerManager.this.theWorldServer, var14));
               if (PlayerManager.this.theWorldServer.getBlockState(var14).getBlock().hasTileEntity()) {
                  this.sendTileToAllPlayersWatchingChunk(PlayerManager.this.theWorldServer.getTileEntity(var14));
               }
            } else if (this.numBlocksToUpdate != 64) {
               this.sendToAllPlayersWatchingChunk(
                  new S22PacketMultiBlockChange(
                     this.numBlocksToUpdate,
                     this.locationOfBlockChange,
                     PlayerManager.this.theWorldServer.a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos)
                  )
               );

               for (int var7 = 0; var7 < this.numBlocksToUpdate; var7++) {
                  int var9 = (this.locationOfBlockChange[var7] >> 12 & 15) + this.chunkCoords.chunkXPos * 16;
                  int var11 = this.locationOfBlockChange[var7] & 255;
                  int var13 = (this.locationOfBlockChange[var7] >> 8 & 15) + this.chunkCoords.chunkZPos * 16;
                  BlockPos var15 = new BlockPos(var9, var11, var13);
                  if (PlayerManager.this.theWorldServer.getBlockState(var15).getBlock().hasTileEntity()) {
                     this.sendTileToAllPlayersWatchingChunk(PlayerManager.this.theWorldServer.getTileEntity(var15));
                  }
               }
            } else {
               int var1 = this.chunkCoords.chunkXPos * 16;
               int var2 = this.chunkCoords.chunkZPos * 16;
               this.sendToAllPlayersWatchingChunk(
                  new S21PacketChunkData(
                     PlayerManager.this.theWorldServer.a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos), false, this.flagsYAreasToUpdate
                  )
               );

               for (int var3 = 0; var3 < 16; var3++) {
                  if ((this.flagsYAreasToUpdate & 1 << var3) != 0) {
                     int var4 = var3 << 4;
                     List var5 = PlayerManager.this.theWorldServer.getTileEntitiesIn(var1, var4, var2, var1 + 16, var4 + 16, var2 + 16);

                     for (int var6 = 0; var6 < var5.size(); var6++) {
                        this.sendTileToAllPlayersWatchingChunk((TileEntity)var5.get(var6));
                     }
                  }
               }
            }

            this.numBlocksToUpdate = 0;
            this.flagsYAreasToUpdate = 0;
         }
      }

      public void sendTileToAllPlayersWatchingChunk(TileEntity var1) {
         if (var1 != null) {
            Packet var2 = var1.getDescriptionPacket();
            if (var2 != null) {
               this.sendToAllPlayersWatchingChunk(var2);
            }
         }
      }

      public void sendToAllPlayersWatchingChunk(Packet var1) {
         for (int var2 = 0; var2 < this.playersWatchingChunk.size(); var2++) {
            EntityPlayerMP var3 = this.playersWatchingChunk.get(var2);
            if (!var3.loadedChunks.contains(this.chunkCoords)) {
               var3.playerNetServerHandler.sendPacket(var1);
            }
         }
      }

      public PlayerInstance(int var2, int var3) {
         this.locationOfBlockChange = new short[64];
         this.chunkCoords = new ChunkCoordIntPair(var2, var3);
         PlayerManager.this.getWorldServer().theChunkProviderServer.loadChunk(var2, var3);
      }

      public void flagChunkForUpdate(int var1, int var2, int var3) {
         if (this.numBlocksToUpdate == 0) {
            PlayerManager.this.playerInstancesToUpdate.add(this);
         }

         this.flagsYAreasToUpdate |= 1 << (var2 >> 4);
         if (this.numBlocksToUpdate < 64) {
            short var4 = (short)(var1 << 12 | var3 << 8 | var2);

            for (int var5 = 0; var5 < this.numBlocksToUpdate; var5++) {
               if (this.locationOfBlockChange[var5] == var4) {
                  return;
               }
            }

            this.locationOfBlockChange[this.numBlocksToUpdate++] = var4;
         }
      }
   }
}
