package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;

public interface IChunkProvider {
   void populate(IChunkProvider var1, int var2, int var3);

   boolean unloadQueuedChunks();

   Chunk provideChunk(int var1, int var2);

   boolean populateChunk(IChunkProvider var1, Chunk var2, int var3, int var4);

   boolean canSave();

   String makeString();

   Chunk provideChunk(BlockPos var1);

   BlockPos getStrongholdGen(World var1, String var2, BlockPos var3);

   List<BiomeGenBase$SpawnListEntry> getPossibleCreatures(EnumCreatureType var1, BlockPos var2);

   void recreateStructures(Chunk var1, int var2, int var3);

   void saveExtraData();

   boolean chunkExists(int var1, int var2);

   int getLoadedChunkCount();

   boolean saveChunks(boolean var1, IProgressUpdate var2);
}
