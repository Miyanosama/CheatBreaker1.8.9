package net.minecraft.world;

import net.minecraft.nbt.NBTTagCompound;

public abstract class WorldSavedData {
   public boolean dirty;
   public String a;

   public void markDirty() {
      this.setDirty(true);
   }

   public void setDirty(boolean var1) {
      this.dirty = var1;
   }

   public abstract void writeToNBT(NBTTagCompound var1);

   public boolean isDirty() {
      return this.dirty;
   }

   public WorldSavedData(String var1) {
      this.a = var1;
   }

   public abstract void readFromNBT(NBTTagCompound var1);
}
