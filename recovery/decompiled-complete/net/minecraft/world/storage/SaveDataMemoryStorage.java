package net.minecraft.world.storage;

import net.minecraft.world.WorldSavedData;

public class SaveDataMemoryStorage extends MapStorage {
   @Override
   public int getUniqueDataId(String var1) {
      return 0;
   }

   @Override
   public WorldSavedData loadData(Class<? extends WorldSavedData> var1, String var2) {
      return this.a.get(var2);
   }

   @Override
   public void setData(String var1, WorldSavedData var2) {
      this.a.put(var1, var2);
   }

   @Override
   public void saveAllData() {
   }

   public SaveDataMemoryStorage() {
      super((ISaveHandler)null);
   }
}
