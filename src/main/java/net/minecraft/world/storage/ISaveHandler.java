package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public interface ISaveHandler {
   IPlayerFileData getPlayerNBTManager();

   File getMapFileFromName(String var1);

   void checkSessionLock() throws net.minecraft.world.MinecraftException ;

   void flush();

   String getWorldDirectoryName();

   IChunkLoader getChunkLoader(WorldProvider var1);

   WorldInfo loadWorldInfo();

   void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2);

   File getWorldDirectory();

   void saveWorldInfo(WorldInfo var1);
}
