package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LayeredTexture extends AbstractTexture {
   public static Logger logger = LogManager.getLogger();
   public ResourceLocation textureLocation;
   public List<String> layeredTextureNames;

   public LayeredTexture(String... var1) {
      this.layeredTextureNames = Lists.newArrayList(var1);
      if (var1.length > 0 && var1[0] != null) {
         this.textureLocation = new ResourceLocation(var1[0]);
      }
   }

   @Override
   public void loadTexture(IResourceManager var1) throws java.io.IOException {
      this.deleteGlTexture();
      BufferedImage var2 = null;

      try {
         for (String var4 : this.layeredTextureNames) {
            if (var4 != null) {
               InputStream var5 = var1.getResource(new ResourceLocation(var4)).getInputStream();
               BufferedImage var6 = TextureUtil.readBufferedImage(var5);
               if (var2 == null) {
                  var2 = new BufferedImage(var6.getWidth(), var6.getHeight(), 2);
               }

               var2.getGraphics().drawImage(var6, 0, 0, (ImageObserver)null);
            }
         }
      } catch (IOException var7) {
         logger.error("Couldn't load layered image", var7);
         return;
      }

      if (Config.isShaders()) {
         ShadersTex.loadSimpleTexture(this.getGlTextureId(), var2, false, false, var1, this.textureLocation, this.getMultiTexID());
      } else {
         TextureUtil.uploadTextureImage(this.getGlTextureId(), var2);
      }
   }
}
