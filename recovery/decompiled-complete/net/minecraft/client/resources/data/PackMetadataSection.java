package net.minecraft.client.resources.data;

import io.netty.handler.codec.http.multipart.HttpPostBodyUtil;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class PackMetadataSection implements IMetadataSection {
   public HttpPostBodyUtil field_0001;
   public int packFormat;
   public World field_0000;
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
