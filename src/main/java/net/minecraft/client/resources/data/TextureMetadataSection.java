package net.minecraft.client.resources.data;

import java.util.Collections;
import java.util.List;

public class TextureMetadataSection implements IMetadataSection {
   public List<Integer> listMipmaps;
   public boolean textureBlur;
   public boolean textureClamp;

   public boolean getTextureClamp() {
      return this.textureClamp;
   }

   public boolean getTextureBlur() {
      return this.textureBlur;
   }

   public TextureMetadataSection(boolean var1, boolean var2, List<Integer> var3) {
      this.textureBlur = var1;
      this.textureClamp = var2;
      this.listMipmaps = var3;
   }

   public List<Integer> getListMipmaps() {
      return Collections.unmodifiableList(this.listMipmaps);
   }
}
