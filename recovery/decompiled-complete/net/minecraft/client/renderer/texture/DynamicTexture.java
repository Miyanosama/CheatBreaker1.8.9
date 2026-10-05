package net.minecraft.client.renderer.texture;

import java.awt.image.BufferedImage;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.command.CommandBase;
import org.apache.log4j.helpers.Loader;

public class DynamicTexture extends AbstractTexture {
   public CommandBase field_0002;
   public int[] dynamicTextureData;
   public int width;
   public int height;
   public Loader field_0000;

   public void updateDynamicTexture() {
      TextureUtil.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
   }

   @Override
   public void loadTexture(IResourceManager var1) {
   }

   public DynamicTexture(int var1, int var2) {
      this.width = var1;
      this.height = var2;
      this.dynamicTextureData = new int[var1 * var2];
      TextureUtil.allocateTexture(this.getGlTextureId(), var1, var2);
   }

   public DynamicTexture(BufferedImage var1) {
      this(var1.getWidth(), var1.getHeight());
      var1.getRGB(0, 0, var1.getWidth(), var1.getHeight(), this.dynamicTextureData, 0, var1.getWidth());
      this.updateDynamicTexture();
   }

   public int[] getTextureData() {
      return this.dynamicTextureData;
   }
}
