package net.minecraft.client.renderer.texture;

import net.minecraft.client.resources.IResourceManager;
import net.optifine.shaders.MultiTexID;

public interface ITextureObject {
   void loadTexture(IResourceManager var1) throws java.io.IOException ;

   void restoreLastBlurMipmap();

   MultiTexID getMultiTexID();

   int getGlTextureId();

   void setBlurMipmap(boolean var1, boolean var2);
}
