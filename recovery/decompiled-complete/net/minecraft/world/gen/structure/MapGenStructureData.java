package net.minecraft.world.gen.structure;

import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;
import org.apache.log4j.spi.ThrowableInformation;

public class MapGenStructureData extends WorldSavedData {
   public NBTTagCompound tagCompound = new NBTTagCompound();
   public EntityPainting$EnumArt field_0002;
   public ThrowableInformation field_0000;

   public NBTTagCompound getTagCompound() {
      return this.tagCompound;
   }

   public void writeInstance(NBTTagCompound var1, int var2, int var3) {
      this.tagCompound.setTag(formatChunkCoords(var2, var3), var1);
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      var1.setTag("Features", this.tagCompound);
   }

   public MapGenStructureData(String var1) {
      super(var1);
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      this.tagCompound = var1.getCompoundTag("Features");
   }

   public static String formatChunkCoords(int var0, int var1) {
      return "[" + var0 + "," + var1 + "]";
   }
}
