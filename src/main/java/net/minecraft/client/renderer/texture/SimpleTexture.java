package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.EmissiveTextures;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleTexture extends AbstractTexture {
   public ResourceLocation f;
   public boolean isEmissive;
   public static Logger logger = LogManager.getLogger();
   public ResourceLocation locationEmissive;

   @Override
   public void loadTexture(IResourceManager var1) throws java.io.IOException {
      this.deleteGlTexture();
      InputStream var2 = null;

      try {
         IResource var3 = var1.getResource(this.f);
         var2 = var3.getInputStream();
         BufferedImage var4 = TextureUtil.readBufferedImage(var2);
         boolean var5 = false;
         boolean var6 = false;
         if (var3.hasMetadata()) {
            try {
               TextureMetadataSection var7 = var3.getMetadata("texture");
               if (var7 != null) {
                  var5 = var7.getTextureBlur();
                  var6 = var7.getTextureClamp();
               }
            } catch (RuntimeException var11) {
               logger.warn("Failed reading metadata of: " + this.f, var11);
            }
         }

         if (Config.isShaders()) {
            ShadersTex.loadSimpleTexture(this.getGlTextureId(), var4, var5, var6, var1, this.f, this.getMultiTexID());
         } else {
            TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), var4, var5, var6);
         }

         if (EmissiveTextures.isActive()) {
            EmissiveTextures.loadTexture(this.f, this);
         }
      } finally {
         if (var2 != null) {
            var2.close();
         }
      }
   }

   public SimpleTexture(ResourceLocation var1) {
      this.f = var1;
   }
}
