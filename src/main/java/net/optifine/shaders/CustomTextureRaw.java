package net.optifine.shaders;

import java.nio.ByteBuffer;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class CustomTextureRaw implements ICustomTexture {
   public TextureType type;
   public int textureUnit;
   public int textureId;

   @Override
   public int getTarget() {
      return this.type.getId();
   }

   @Override
   public int getTextureId() {
      return this.textureId;
   }

   public CustomTextureRaw(
      TextureType var1,
      InternalFormat var2,
      int var3,
      int var4,
      int var5,
      PixelFormat var6,
      PixelType var7,
      ByteBuffer var8,
      int var9,
      boolean var10,
      boolean var11
   ) {
      this.type = var1;
      this.textureUnit = var9;
      this.textureId = GL11.glGenTextures();
      GL11.glBindTexture(this.getTarget(), this.textureId);
      int var12 = var11 ? '脯' : 10497;
      int var13 = var10 ? 9729 : 9728;
      switch (var1) {
         case TEXTURE_1D:
            GL11.glTexImage1D(3552, 0, var2.getId(), var3, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(3552, 10242, var12);
            GL11.glTexParameteri(3552, 10240, var13);
            GL11.glTexParameteri(3552, 10241, var13);
            break;
         case TEXTURE_2D:
            GL11.glTexImage2D(3553, 0, var2.getId(), var3, var4, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(3553, 10242, var12);
            GL11.glTexParameteri(3553, 10243, var12);
            GL11.glTexParameteri(3553, 10240, var13);
            GL11.glTexParameteri(3553, 10241, var13);
            break;
         case TEXTURE_3D:
            GL12.glTexImage3D(32879, 0, var2.getId(), var3, var4, var5, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(32879, 10242, var12);
            GL11.glTexParameteri(32879, 10243, var12);
            GL11.glTexParameteri(32879, 32882, var12);
            GL11.glTexParameteri(32879, 10240, var13);
            GL11.glTexParameteri(32879, 10241, var13);
            break;
         case TEXTURE_RECTANGLE:
            GL11.glTexImage2D(34037, 0, var2.getId(), var3, var4, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(34037, 10242, var12);
            GL11.glTexParameteri(34037, 10243, var12);
            GL11.glTexParameteri(34037, 10240, var13);
            GL11.glTexParameteri(34037, 10241, var13);
      }

      GL11.glBindTexture(this.getTarget(), 0);
   }

   @Override
   public void deleteTexture() {
      if (this.textureId > 0) {
         GL11.glDeleteTextures(this.textureId);
         this.textureId = 0;
      }
   }

   @Override
   public int getTextureUnit() {
      return this.textureUnit;
   }
}
