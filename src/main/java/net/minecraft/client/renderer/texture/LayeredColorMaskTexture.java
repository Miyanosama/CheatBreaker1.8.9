package net.minecraft.client.renderer.texture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LayeredColorMaskTexture extends AbstractTexture {
   public ResourceLocation textureLocation;
   public static Logger LOG = LogManager.getLogger();
   public List<EnumDyeColor> field_174950_i;
   public List<String> field_174949_h;

   public LayeredColorMaskTexture(ResourceLocation var1, List<String> var2, List<EnumDyeColor> var3) {
      this.textureLocation = var1;
      this.field_174949_h = var2;
      this.field_174950_i = var3;
   }

   @Override
   public void loadTexture(IResourceManager var1) throws java.io.IOException {
      this.deleteGlTexture();

      BufferedImage var2;
      try {
         BufferedImage var3 = TextureUtil.readBufferedImage(var1.getResource(this.textureLocation).getInputStream());
         int var4 = var3.getType();
         if (var4 == 0) {
            var4 = 6;
         }

         var2 = new BufferedImage(var3.getWidth(), var3.getHeight(), var4);
         Graphics var5 = var2.getGraphics();
         var5.drawImage(var3, 0, 0, (ImageObserver)null);

         for (int var6 = 0; var6 < 17 && var6 < this.field_174949_h.size() && var6 < this.field_174950_i.size(); var6++) {
            String var7 = this.field_174949_h.get(var6);
            MapColor var8 = this.field_174950_i.get(var6).getMapColor();
            if (var7 != null) {
               InputStream var9 = var1.getResource(new ResourceLocation(var7)).getInputStream();
               BufferedImage var10 = TextureUtil.readBufferedImage(var9);
               if (var10.getWidth() == var2.getWidth() && var10.getHeight() == var2.getHeight() && var10.getType() == 6) {
                  for (int var11 = 0; var11 < var10.getHeight(); var11++) {
                     for (int var12 = 0; var12 < var10.getWidth(); var12++) {
                        int var13 = var10.getRGB(var12, var11);
                        if ((var13 & 0xFF000000) != 0) {
                           int var14 = (var13 & 0xFF0000) << 8 & 0xFF000000;
                           int var15 = var3.getRGB(var12, var11);
                           int var16 = MathHelper.func_180188_d(var15, var8.colorValue) & 16777215;
                           var10.setRGB(var12, var11, var14 | var16);
                        }
                     }
                  }

                  var2.getGraphics().drawImage(var10, 0, 0, (ImageObserver)null);
               }
            }
         }
      } catch (IOException var17) {
         LOG.error("Couldn't load layered image", var17);
         return;
      }

      if (Config.isShaders()) {
         ShadersTex.loadSimpleTexture(this.getGlTextureId(), var2, false, false, var1, this.textureLocation, this.getMultiTexID());
      } else {
         TextureUtil.uploadTextureImage(this.getGlTextureId(), var2);
      }
   }
}
