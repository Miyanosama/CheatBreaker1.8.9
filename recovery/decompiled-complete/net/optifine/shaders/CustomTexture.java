package net.optifine.shaders;

import io.netty.channel.sctp.SctpNotificationHandler;
import net.minecraft.client.renderer.entity.RenderBlaze;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureUtil;

public class CustomTexture implements ICustomTexture {
   public ITextureObject texture;
   public RenderBlaze field_0004;
   public String path;
   public int textureUnit = -1;
   public SctpNotificationHandler field_0000;

   @Override
   public int getTextureId() {
      return this.texture.getGlTextureId();
   }

   @Override
   public int getTextureUnit() {
      return this.textureUnit;
   }

   @Override
   public int getTarget() {
      return 3553;
   }

   public ITextureObject getTexture() {
      return this.texture;
   }

   @Override
   public void deleteTexture() {
      TextureUtil.deleteTexture(this.texture.getGlTextureId());
   }

   public String getPath() {
      return this.path;
   }

   public CustomTexture(int var1, String var2, ITextureObject var3) {
      this.path = null;
      this.texture = null;
      this.textureUnit = var1;
      this.path = var2;
      this.texture = var3;
   }

   @Override
   public String toString() {
      return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.getTextureId();
   }
}
