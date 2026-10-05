package net.minecraft.world.storage;

import java.io.File;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public class SaveHandlerMP implements ISaveHandler {
   @Override
   public IPlayerFileData getPlayerNBTManager() {
      return null;
   }

   @Override
   public File getMapFileFromName(String var1) {
      return null;
   }

   @Override
   public void checkSessionLock() throws net.minecraft.world.MinecraftException {
   }

   @Override
   public void saveWorldInfo(WorldInfo var1) {
   }

   @Override
   public File getWorldDirectory() {
      return null;
   }

   @Override
   public IChunkLoader getChunkLoader(WorldProvider var1) {
      return null;
   }

   @Override
   public void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2) {
   }

   @Override
   public String getWorldDirectoryName() {
      return "none";
   }

   @Override
   public void flush() {
   }

   @Override
   public WorldInfo loadWorldInfo() {
      return null;
   }
}
