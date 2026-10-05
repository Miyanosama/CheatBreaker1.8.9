package net.optifine.util;

import io.netty.util.internal.TypeParameterMatcher$ReflectiveMatcher;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.resources.IResourceManager;
import net.optifine.TextureAnimations;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.config.ShaderOption;

public class TextureUtils$2 implements ITickableTextureObject {
   public TypeParameterMatcher$ReflectiveMatcher field_0000;
   public ShaderOption field_0001;

   @Override
   public void setBlurMipmap(boolean var1, boolean var2) {
   }

   @Override
   public MultiTexID getMultiTexID() {
      return null;
   }

   @Override
   public void tick() {
      TextureAnimations.updateAnimations();
   }

   @Override
   public void restoreLastBlurMipmap() {
   }

   @Override
   public int getGlTextureId() {
      return 0;
   }

   @Override
   public void loadTexture(IResourceManager var1) {
   }
}
