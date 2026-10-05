package net.optifine.shaders;

import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.resources.IResourceManager;

public class DefaultTexture extends AbstractTexture {
   @Override
   public void loadTexture(IResourceManager var1) {
      int[] var2 = ShadersTex.createAIntImage(1, -1);
      ShadersTex.setupTexture(this.getMultiTexID(), var2, 1, 1, false, false);
   }

   public DefaultTexture() {
      this.loadTexture((IResourceManager)null);
   }
}
