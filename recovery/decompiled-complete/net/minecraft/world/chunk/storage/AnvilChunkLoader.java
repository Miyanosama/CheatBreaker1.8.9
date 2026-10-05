package net.minecraft.world.chunk.storage;

import io.netty.handler.ssl.SslContext$1;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.NibbleArray;
import net.minecraft.world.storage.IThreadedFileIO;
import net.minecraft.world.storage.ThreadedFileIOBase;
import net.optifine.entity.model.ModelAdapterCow;
import net.optifine.entity.model.ModelAdapterSpider;
import org.apache.log4j.net.JMSSink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.java_websocket.server.WebSocketServer$WebSocketWorker;

public class AnvilChunkLoader implements IChunkLoader, IThreadedFileIO {
   public File chunkSaveLocation;
   public Map<ChunkCoordIntPair, NBTTagCompound> chunksToRemove = new ConcurrentHashMap<>();
   public ModelAdapterCow field_0004;
   public Set<ChunkCoordIntPair> pendingAnvilChunksCoordinates = Collections.newSetFromMap(new ConcurrentHashMap<>());
   public static Logger logger = LogManager.getLogger();
   public SslContext$1 field_0002;
   public RenderChunk field_0011;
   public ModelAdapterSpider field_0008;
   public boolean field_183014_e = false;
   public S27PacketExplosion field_0012;
   public Session field_0000;
   public WebSocketServer$WebSocketWorker field_0006;
   public JMSSink field_0007;

   public void writeChunkToNBT(Chunk var1, World var2, NBTTagCompound var3) {
      var3.setByte("V", (byte)1);
      var3.setInteger("xPos", var1.a);
      var3.setInteger("zPos", var1.b);
      var3.setLong("LastUpdate", var2.K());
      var3.setIntArray("HeightMap", var1.getHeightMap());
      var3.setBoolean("TerrainPopulated", var1.isTerrainPopulated());
      var3.setBoolean("LightPopulated", var1.isLightPopulated());
      var3.setLong("InhabitedTime", var1.getInhabitedTime());
      ExtendedBlockStorage[] var4 = var1.getBlockStorageArray();
      NBTTagList var5 = new NBTTagList();
      boolean var6 = !var2.t.getHasNoSky();

      for (ExtendedBlockStorage var10 : var4) {
         if (var10 != null) {
            NBTTagCompound var11 = new NBTTagCompound();
            var11.setByte("Y", (byte)(var10.getYLocation() >> 4 & 0xFF));
            byte[] var12 = new byte[var10.getData().length];
            NibbleArray var13 = new NibbleArray();
            NibbleArray var14 = null;

            for (int var15 = 0; var15 < var10.getData().length; var15++) {
               char var16 = var10.getData()[var15];
               int var17 = var15 & 15;
               int var18 = var15 >> 8 & 15;
               int var19 = var15 >> 4 & 15;
               if (var16 >> '\f' != 0) {
                  if (var14 == null) {
                     var14 = new NibbleArray();
                  }

                  var14.set(var17, var18, var19, var16 >> '\f');
               }

               var12[var15] = (byte)(var16 >> 4 & 0xFF);
               var13.set(var17, var18, var19, var16 & 15);
            }

            var11.setByteArray("Blocks", var12);
            var11.setByteArray("Data", var13.getData());
            if (var14 != null) {
               var11.setByteArray("Add", var14.getData());
            }

            var11.setByteArray("BlockLight", var10.getBlocklightArray().getData());
            if (var6) {
               var11.setByteArray("SkyLight", var10.getSkylightArray().getData());
            } else {
               var11.setByteArray("SkyLight", new byte[var10.getBlocklightArray().getData().length]);
            }

            var5.appendTag(var11);
         }
      }

      var3.setTag("Sections", var5);
      var3.setByteArray("Biomes", var1.getBiomeArray());
      var1.setHasEntities(false);
      NBTTagList var20 = new NBTTagList();

      for (int var21 = 0; var21 < var1.getEntityLists().length; var21++) {
         for (Entity var26 : var1.getEntityLists()[var21]) {
            NBTTagCompound var29 = new NBTTagCompound();
            if (var26.writeToNBTOptional(var29)) {
               var1.setHasEntities(true);
               var20.appendTag(var29);
            }
         }
      }

      var3.setTag("Entities", var20);
      NBTTagList var22 = new NBTTagList();

      for (TileEntity var27 : var1.getTileEntityMap().values()) {
         NBTTagCompound var30 = new NBTTagCompound();
         var27.writeToNBT(var30);
         var22.appendTag(var30);
      }

      var3.setTag("TileEntities", var22);
      List var25 = var2.getPendingBlockUpdates(var1, false);
      if (var25 != null) {
         long var28 = var2.K();
         NBTTagList var31 = new NBTTagList();

         for (NextTickListEntry var33 : var25) {
            NBTTagCompound var34 = new NBTTagCompound();
            ResourceLocation var35 = Block.blockRegistry.getNameForObject(var33.getBlock());
            var34.setString("i", var35 == null ? "" : var35.toString());
            var34.setInteger("x", var33.position.getX());
            var34.setInteger("y", var33.position.getY());
            var34.setInteger("z", var33.position.getZ());
            var34.setInteger("t", (int)(var33.scheduledTime - var28));
            var34.setInteger("p", var33.priority);
            var31.appendTag(var34);
         }

         var3.setTag("TileTicks", var31);
      }
   }

   @Override
   public void saveChunk(World var1, Chunk var2) {
      var1.I();

      try {
         NBTTagCompound var3 = new NBTTagCompound();
         NBTTagCompound var4 = new NBTTagCompound();
         var3.setTag("Level", var4);
         this.writeChunkToNBT(var2, var1, var4);
         this.addChunkToPending(var2.getChunkCoordIntPair(), var3);
      } catch (Exception var5) {
         logger.error("Failed to save chunk", var5);
      }
   }

   public void func_183013_b(ChunkCoordIntPair var1, NBTTagCompound var2) {
      DataOutputStream var3 = RegionFileCache.getChunkOutputStream(this.chunkSaveLocation, var1.chunkXPos, var1.chunkZPos);
      CompressedStreamTools.write(var2, var3);
      var3.close();
   }

   @Override
   public void saveExtraData() {
      try {
         this.field_183014_e = true;

         while (true) {
            if (this.writeNextIO()) {
            }
         }
      } finally {
         this.field_183014_e = false;
      }
   }

   public Chunk checkedReadChunkFromNBT(World var1, int var2, int var3, NBTTagCompound var4) {
      if (!var4.hasKey("Level", 10)) {
         logger.error("Chunk file at " + var2 + "," + var3 + " is missing level data, skipping");
         return null;
      } else {
         NBTTagCompound var5 = var4.getCompoundTag("Level");
         if (!var5.hasKey("Sections", 9)) {
            logger.error("Chunk file at " + var2 + "," + var3 + " is missing block data, skipping");
            return null;
         } else {
            Chunk var6 = this.readChunkFromNBT(var1, var5);
            if (!var6.isAtLocation(var2, var3)) {
               logger.error(
                  "Chunk file at "
                     + var2
                     + ","
                     + var3
                     + " is in the wrong location; relocating. (Expected "
                     + var2
                     + ", "
                     + var3
                     + ", got "
                     + var6.a
                     + ", "
                     + var6.b
                     + ")"
               );
               var5.setInteger("xPos", var2);
               var5.setInteger("zPos", var3);
               var6 = this.readChunkFromNBT(var1, var5);
            }

            return var6;
         }
      }
   }

   public Chunk readChunkFromNBT(World var1, NBTTagCompound var2) {
      int var3 = var2.getInteger("xPos");
      int var4 = var2.getInteger("zPos");
      Chunk var5 = new Chunk(var1, var3, var4);
      var5.setHeightMap(var2.getIntArray("HeightMap"));
      var5.setTerrainPopulated(var2.getBoolean("TerrainPopulated"));
      var5.setLightPopulated(var2.getBoolean("LightPopulated"));
      var5.setInhabitedTime(var2.getLong("InhabitedTime"));
      NBTTagList var6 = var2.getTagList("Sections", 10);
      byte var7 = 16;
      ExtendedBlockStorage[] var8 = new ExtendedBlockStorage[var7];
      boolean var9 = !var1.t.getHasNoSky();

      for (int var10 = 0; var10 < var6.tagCount(); var10++) {
         NBTTagCompound var11 = var6.getCompoundTagAt(var10);
         byte var12 = var11.getByte("Y");
         ExtendedBlockStorage var13 = new ExtendedBlockStorage(var12 << 4, var9);
         byte[] var14 = var11.getByteArray("Blocks");
         NibbleArray var15 = new NibbleArray(var11.getByteArray("Data"));
         NibbleArray var16 = var11.hasKey("Add", 7) ? new NibbleArray(var11.getByteArray("Add")) : null;
         char[] var17 = new char[var14.length];

         for (int var18 = 0; var18 < var17.length; var18++) {
            int var19 = var18 & 15;
            int var20 = var18 >> 8 & 15;
            int var21 = var18 >> 4 & 15;
            int var22 = var16 != null ? var16.get(var19, var20, var21) : 0;
            var17[var18] = (char)(var22 << 12 | (var14[var18] & 255) << 4 | var15.get(var19, var20, var21));
         }

         var13.setData(var17);
         var13.setBlocklightArray(new NibbleArray(var11.getByteArray("BlockLight")));
         if (var9) {
            var13.setSkylightArray(new NibbleArray(var11.getByteArray("SkyLight")));
         }

         var13.removeInvalidBlocks();
         var8[var12] = var13;
      }

      var5.setStorageArrays(var8);
      if (var2.hasKey("Biomes", 7)) {
         var5.setBiomeArray(var2.getByteArray("Biomes"));
      }

      NBTTagList var23 = var2.getTagList("Entities", 10);
      if (var23 != null) {
         for (int var24 = 0; var24 < var23.tagCount(); var24++) {
            NBTTagCompound var26 = var23.getCompoundTagAt(var24);
            Entity var29 = EntityList.createEntityFromNBT(var26, var1);
            var5.setHasEntities(true);
            if (var29 != null) {
               var5.addEntity(var29);
               Entity var32 = var29;

               for (NBTTagCompound var35 = var26; var35.hasKey("Riding", 10); var35 = var35.getCompoundTag("Riding")) {
                  Entity var37 = EntityList.createEntityFromNBT(var35.getCompoundTag("Riding"), var1);
                  if (var37 != null) {
                     var5.addEntity(var37);
                     var32.mountEntity(var37);
                  }

                  var32 = var37;
               }
            }
         }
      }

      NBTTagList var25 = var2.getTagList("TileEntities", 10);
      if (var25 != null) {
         for (int var27 = 0; var27 < var25.tagCount(); var27++) {
            NBTTagCompound var30 = var25.getCompoundTagAt(var27);
            TileEntity var33 = TileEntity.createAndLoadEntity(var30);
            if (var33 != null) {
               var5.addTileEntity(var33);
            }
         }
      }

      if (var2.hasKey("TileTicks", 9)) {
         NBTTagList var28 = var2.getTagList("TileTicks", 10);
         if (var28 != null) {
            for (int var31 = 0; var31 < var28.tagCount(); var31++) {
               NBTTagCompound var34 = var28.getCompoundTagAt(var31);
               Block var36;
               if (var34.hasKey("i", 8)) {
                  var36 = Block.getBlockFromName(var34.getString("i"));
               } else {
                  var36 = Block.getBlockById(var34.getInteger("i"));
               }

               var1.scheduleBlockUpdate(
                  new BlockPos(var34.getInteger("x"), var34.getInteger("y"), var34.getInteger("z")), var36, var34.getInteger("t"), var34.getInteger("p")
               );
            }
         }
      }

      return var5;
   }

   @Override
   public Chunk loadChunk(World var1, int var2, int var3) {
      ChunkCoordIntPair var4 = new ChunkCoordIntPair(var2, var3);
      NBTTagCompound var5 = this.chunksToRemove.get(var4);
      if (var5 == null) {
         DataInputStream var6 = RegionFileCache.getChunkInputStream(this.chunkSaveLocation, var2, var3);
         if (var6 == null) {
            return null;
         }

         var5 = CompressedStreamTools.read(var6);
      }

      return this.checkedReadChunkFromNBT(var1, var2, var3, var5);
   }

   @Override
   public void saveExtraChunkData(World var1, Chunk var2) {
   }

   public void addChunkToPending(ChunkCoordIntPair var1, NBTTagCompound var2) {
      if (!this.pendingAnvilChunksCoordinates.contains(var1)) {
         this.chunksToRemove.put(var1, var2);
      }

      ThreadedFileIOBase.getThreadedIOInstance().queueIO(this);
   }

   public AnvilChunkLoader(File var1) {
      this.chunkSaveLocation = var1;
   }

   @Override
   public void chunkTick() {
   }

   @Override
   public boolean writeNextIO() {
      if (this.chunksToRemove.isEmpty()) {
         if (this.field_183014_e) {
            logger.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", new Object[]{this.chunkSaveLocation.getName()});
         }

         return false;
      } else {
         ChunkCoordIntPair var1 = this.chunksToRemove.keySet().iterator().next();

         boolean var2;
         try {
            this.pendingAnvilChunksCoordinates.add(var1);
            NBTTagCompound var3 = this.chunksToRemove.remove(var1);
            if (var3 != null) {
               try {
                  this.func_183013_b(var1, var3);
               } catch (Exception var8) {
                  logger.error("Failed to save chunk", var8);
               }
            }

            var2 = true;
         } finally {
            this.pendingAnvilChunksCoordinates.remove(var1);
         }

         return var2;
      }
   }
}
