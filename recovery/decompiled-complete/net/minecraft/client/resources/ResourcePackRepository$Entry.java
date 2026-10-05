package net.minecraft.client.resources;

import io.netty.channel.rxtx.RxtxChannel$RxtxUnsafe$1;
import io.netty.util.internal.TypeParameterMatcher;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.data.PackMetadataSection;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class ResourcePackRepository$Entry {
   public ResourceLocation locationTexturePackIcon;
   public BufferedImage texturePackIcon;
   public RxtxChannel$RxtxUnsafe$1 field_0005;
   public File resourcePackFile;
   public PackMetadataSection rePackMetadataSection;
   public IResourcePack reResourcePack;
   public TypeParameterMatcher field_0004;

   public String getResourcePackName() {
      return this.reResourcePack.getPackName();
   }

   public String getTexturePackDescription() {
      return this.rePackMetadataSection == null
         ? EnumChatFormatting.RED + "Invalid pack.mcmeta (or missing 'pack' section)"
         : this.rePackMetadataSection.getPackDescription().getFormattedText();
   }

   public IResourcePack getResourcePack() {
      return this.reResourcePack;
   }

   public ResourcePackRepository$Entry(ResourcePackRepository var1, File var2) {
      this.this$0 = var1;
      super();
      this.resourcePackFile = var2;
   }

   public void closeResourcePack() {
      if (this.reResourcePack instanceof Closeable) {
         IOUtils.closeQuietly((Closeable)this.reResourcePack);
      }
   }

   public int func_183027_f() {
      return this.rePackMetadataSection.getPackFormat();
   }

   @Override
   public int hashCode() {
      return this.toString().hashCode();
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1 ? true : (var1 instanceof ResourcePackRepository$Entry ? this.toString().equals(var1.toString()) : false);
   }

   @Override
   public String toString() {
      return String.format(
         "%s:%s:%d", this.resourcePackFile.getName(), this.resourcePackFile.isDirectory() ? "folder" : "zip", this.resourcePackFile.lastModified()
      );
   }

   public void bindTexturePackIcon(TextureManager var1) {
      if (this.locationTexturePackIcon == null) {
         this.locationTexturePackIcon = var1.getDynamicTextureLocation("texturepackicon", new DynamicTexture(this.texturePackIcon));
      }

      var1.bindTexture(this.locationTexturePackIcon);
   }

   public void updateResourcePack() {
      this.reResourcePack = (IResourcePack)(this.resourcePackFile.isDirectory()
         ? new FolderResourcePack(this.resourcePackFile)
         : new FileResourcePack(this.resourcePackFile));
      this.rePackMetadataSection = this.reResourcePack.getPackMetadata(this.this$0.rprMetadataSerializer, "pack");

      try {
         this.texturePackIcon = this.reResourcePack.getPackImage();
      } catch (IOException var2) {
      }

      if (this.texturePackIcon == null) {
         this.texturePackIcon = this.this$0.rprDefaultResourcePack.getPackImage();
      }

      this.closeResourcePack();
   }
}
