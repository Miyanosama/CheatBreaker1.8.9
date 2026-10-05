package net.minecraft.client.resources.data;

import net.minecraft.util.IChatComponent;

public class PackMetadataSection implements IMetadataSection {
   public int packFormat;
   public IChatComponent packDescription;

   public PackMetadataSection(IChatComponent var1, int var2) {
      this.packDescription = var1;
      this.packFormat = var2;
   }

   public int getPackFormat() {
      return this.packFormat;
   }

   public IChatComponent getPackDescription() {
      return this.packDescription;
   }
}
