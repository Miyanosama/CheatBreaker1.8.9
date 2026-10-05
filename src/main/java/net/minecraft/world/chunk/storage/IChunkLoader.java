package net.minecraft.world.chunk.storage;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IChunkLoader {
   void saveExtraData();

   void saveChunk(World var1, Chunk var2) throws java.io.IOException, net.minecraft.world.MinecraftException ;

   Chunk loadChunk(World var1, int var2, int var3) throws java.io.IOException ;

   void saveExtraChunkData(World var1, Chunk var2) throws java.io.IOException ;

   void chunkTick();
}
