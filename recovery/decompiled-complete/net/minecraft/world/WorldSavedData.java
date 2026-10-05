package net.minecraft.world;

import javazoom.jl.player.AudioDeviceBase;
import net.minecraft.block.BlockSand;
import net.minecraft.client.resources.data.IMetadataSerializer$Registration;
import net.minecraft.entity.monster.EntitySlime$SlimeMoveHelper;
import net.minecraft.nbt.NBTTagCompound;

public abstract class WorldSavedData {
   public IMetadataSerializer$Registration field_0004;
   public AudioDeviceBase field_0001;
   public boolean dirty;
   public EntitySlime$SlimeMoveHelper field_0000;
   public String a;
   public BlockSand field_0003;

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
