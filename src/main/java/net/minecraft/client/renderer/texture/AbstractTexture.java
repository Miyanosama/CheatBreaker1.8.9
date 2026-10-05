package net.minecraft.client.renderer.texture;

import net.minecraft.client.renderer.GlStateManager;
import net.optifine.shaders.MultiTexID;
import net.optifine.shaders.ShadersTex;
import org.lwjgl.opengl.GL11;

public abstract class AbstractTexture implements ITextureObject {
   public int glTextureId = -1;
   public boolean mipmap;
   public boolean blurLast;
   public boolean mipmapLast;
   public boolean blur;
   public MultiTexID multiTex;

   @Override
   public void restoreLastBlurMipmap() {
      this.setBlurMipmapDirect(this.blurLast, this.mipmapLast);
   }

   @Override
   public MultiTexID getMultiTexID() {
      return ShadersTex.getMultiTexID(this);
   }

   @Override
   public void setBlurMipmap(boolean var1, boolean var2) {
      this.blurLast = this.blur;
      this.mipmapLast = this.mipmap;
      this.setBlurMipmapDirect(var1, var2);
   }

   @Override
   public int getGlTextureId() {
      if (this.glTextureId == -1) {
         this.glTextureId = TextureUtil.glGenTextures();
      }

      return this.glTextureId;
   }

   public void setBlurMipmapDirect(boolean var1, boolean var2) {
      this.blur = var1;
      this.mipmap = var2;
      int var3 = -1;
      short var4 = -1;
      if (var1) {
         var3 = var2 ? 9987 : 9729;
         var4 = 9729;
      } else {
         var3 = var2 ? 9986 : 9728;
         var4 = 9728;
      }

      GlStateManager.bindTexture(this.getGlTextureId());
      GL11.glTexParameteri(3553, 10241, var3);
      GL11.glTexParameteri(3553, 10240, var4);
   }

   public void deleteGlTexture() {
      ShadersTex.deleteTextures(this, this.glTextureId);
      if (this.glTextureId != -1) {
         TextureUtil.deleteTexture(this.glTextureId);
         this.glTextureId = -1;
      }
   }
}
