package net.minecraft.world.chunk.storage;

import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.client.stream.IngestServerTester$1;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.realms.RealmsServerStatusPinger;

public class ChunkLoader$AnvilConverterData {
   public NibbleArrayReader blockLight;
   public NibbleArrayReader skyLight;
   public boolean terrainPopulated;
   public byte[] blocks;
   public NBTTagList tileTicks;
   public int x;
   public NibbleArrayReader data;
   public IngestServerTester$1 field_0009;
   public int z;
   public NBTTagList entities;
   public NBTTagList tileEntities;
   public byte[] heightmap;
   public RealmsServerStatusPinger field_0008;
   public long lastUpdated;
   public ServerListEntryLanScan field_0010;

   public ChunkLoader$AnvilConverterData(int var1, int var2) {
      this.x = var1;
      this.z = var2;
   }
}
