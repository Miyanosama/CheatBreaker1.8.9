package net.minecraft.world.biome;

import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S3EPacketTeams;
import org.apache.log4j.lf5.viewer.LogTableColumnFormatException;

public class BiomeGenMushroomIsland extends BiomeGenBase {
   public S3EPacketTeams field_0000;
   public LogTableColumnFormatException field_0002;
   public S36PacketSignEditorOpen field_0001;

   public BiomeGenMushroomIsland(int var1) {
      super(var1);
      this.as.treesPerChunk = -100;
      this.as.flowersPerChunk = -100;
      this.as.grassPerChunk = -100;
      this.as.mushroomsPerChunk = 1;
      this.as.bigMushroomsPerChunk = 1;
      this.ak = Blocks.mycelium.getDefaultState();
      this.at.clear();
      this.au.clear();
      this.av.clear();
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityMooshroom.class, 8, 4, 8));
   }
}
