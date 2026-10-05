package net.minecraft.entity;

import net.minecraft.stats.Achievement;

public class DataWatcher$WatchableObject {
   public int dataValueId;
   public Object watchedObject;
   public Achievement field_0001;
   public int objectType;
   public boolean watched;

   public int getDataValueId() {
      return this.dataValueId;
   }

   public Object getObject() {
      return this.watchedObject;
   }

   public int getObjectType() {
      return this.objectType;
   }

   public DataWatcher$WatchableObject(int var1, int var2, Object var3) {
      this.dataValueId = var2;
      this.watchedObject = var3;
      this.objectType = var1;
      this.watched = true;
   }

   public boolean isWatched() {
      return this.watched;
   }

   public void setObject(Object var1) {
      this.watchedObject = var1;
   }

   public void setWatched(boolean var1) {
      this.watched = var1;
   }
}
