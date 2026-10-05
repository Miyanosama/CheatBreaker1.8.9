package net.minecraft.server.management;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;

public class PlayerManager$PlayerInstance {
   public short[] locationOfBlockChange;
   public int flagsYAreasToUpdate;
   public ChunkCoordIntPair chunkCoords;
   public long previousWorldTime;
   public int numBlocksToUpdate;
   public List<EntityPlayerMP> playersWatchingChunk;

   public void processChunk() {
      this.increaseInhabitedTime(PlayerManager.access$100(this.this$0).a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos));
   }

   public void addPlayer(EntityPlayerMP var1) {
      if (this.playersWatchingChunk.contains(var1)) {
         PlayerManager.access$000()
            .debug("Failed to add player. {} already is in chunk {}, {}", new Object[]{var1, this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos});
      } else {
         if (this.playersWatchingChunk.isEmpty()) {
            this.previousWorldTime = PlayerManager.access$100(this.this$0).K();
         }

         this.playersWatchingChunk.add(var1);
         var1.loadedChunks.add(this.chunkCoords);
      }
   }

   public void increaseInhabitedTime(Chunk var1) {
      var1.setInhabitedTime(var1.getInhabitedTime() + PlayerManager.access$100(this.this$0).K() - this.previousWorldTime);
      this.previousWorldTime = PlayerManager.access$100(this.this$0).K();
   }

   public void removePlayer(EntityPlayerMP var1) {
      if (this.playersWatchingChunk.contains(var1)) {
         Chunk var2 = PlayerManager.access$100(this.this$0).a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
         if (var2.isPopulated()) {
            var1.playerNetServerHandler.sendPacket(new S21PacketChunkData(var2, true, 0));
         }

         this.playersWatchingChunk.remove(var1);
         var1.loadedChunks.remove(this.chunkCoords);
         if (this.playersWatchingChunk.isEmpty()) {
            long var3 = this.chunkCoords.chunkXPos + (2147483647L & -2018573067206262785L) | this.chunkCoords.chunkZPos + (2147483647L & 2147483647L) << 32;
            this.increaseInhabitedTime(var2);
            PlayerManager.access$200(this.this$0).remove(var3);
            PlayerManager.access$300(this.this$0).remove(this);
            if (this.numBlocksToUpdate > 0) {
               PlayerManager.access$400(this.this$0).remove(this);
            }

            this.this$0.getWorldServer().theChunkProviderServer.dropChunk(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos);
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
            this.sendToAllPlayersWatchingChunk(new S23PacketBlockChange(PlayerManager.access$100(this.this$0), var14));
            if (PlayerManager.access$100(this.this$0).getBlockState(var14).getBlock().hasTileEntity()) {
               this.sendTileToAllPlayersWatchingChunk(PlayerManager.access$100(this.this$0).getTileEntity(var14));
            }
         } else if (this.numBlocksToUpdate != 64) {
            this.sendToAllPlayersWatchingChunk(
               new S22PacketMultiBlockChange(
                  this.numBlocksToUpdate,
                  this.locationOfBlockChange,
                  PlayerManager.access$100(this.this$0).a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos)
               )
            );

            for (int var7 = 0; var7 < this.numBlocksToUpdate; var7++) {
               int var9 = (this.locationOfBlockChange[var7] >> 12 & 15) + this.chunkCoords.chunkXPos * 16;
               int var11 = this.locationOfBlockChange[var7] & 255;
               int var13 = (this.locationOfBlockChange[var7] >> 8 & 15) + this.chunkCoords.chunkZPos * 16;
               BlockPos var15 = new BlockPos(var9, var11, var13);
               if (PlayerManager.access$100(this.this$0).getBlockState(var15).getBlock().hasTileEntity()) {
                  this.sendTileToAllPlayersWatchingChunk(PlayerManager.access$100(this.this$0).getTileEntity(var15));
               }
            }
         } else {
            int var1 = this.chunkCoords.chunkXPos * 16;
            int var2 = this.chunkCoords.chunkZPos * 16;
            this.sendToAllPlayersWatchingChunk(
               new S21PacketChunkData(
                  PlayerManager.access$100(this.this$0).a(this.chunkCoords.chunkXPos, this.chunkCoords.chunkZPos), false, this.flagsYAreasToUpdate
               )
            );

            for (int var3 = 0; var3 < 16; var3++) {
               if ((this.flagsYAreasToUpdate & 1 << var3) != 0) {
                  int var4 = var3 << 4;
                  List var5 = PlayerManager.access$100(this.this$0).getTileEntitiesIn(var1, var4, var2, var1 + 16, var4 + 16, var2 + 16);

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

   public PlayerManager$PlayerInstance(PlayerManager var1, int var2, int var3) {
      this.this$0 = var1;
      super();
      this.playersWatchingChunk = Lists.newArrayList();
      this.locationOfBlockChange = new short[64];
      this.chunkCoords = new ChunkCoordIntPair(var2, var3);
      var1.getWorldServer().theChunkProviderServer.loadChunk(var2, var3);
   }

   public void flagChunkForUpdate(int var1, int var2, int var3) {
      if (this.numBlocksToUpdate == 0) {
         PlayerManager.access$400(this.this$0).add(this);
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
