package net.optifine.shaders;

import java.nio.ByteBuffer;
import net.minecraft.client.renderer.BlockModelShapes;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.network.NetHandlerPlayServer$2;
import net.minecraft.util.ChatAllowedCharacters;
import net.optifine.expr.TokenParser;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class CustomTextureRaw implements ICustomTexture {
   public TextureType type;
   public TileEntitySkullRenderer field_0007;
   public ItemFlintAndSteel field_0003;
   public TokenParser field_0006;
   public int textureUnit;
   public int textureId;
   public ChatAllowedCharacters field_0008;
   public BlockModelShapes field_0005;
   public NetHandlerPlayServer$2 field_0002;

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
      switch (CustomTextureRaw$1.$SwitchMap$net$optifine$texture$TextureType[var1.ordinal()]) {
         case 1:
            GL11.glTexImage1D(3552, 0, var2.getId(), var3, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(3552, 10242, var12);
            GL11.glTexParameteri(3552, 10240, var13);
            GL11.glTexParameteri(3552, 10241, var13);
            break;
         case 2:
            GL11.glTexImage2D(3553, 0, var2.getId(), var3, var4, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(3553, 10242, var12);
            GL11.glTexParameteri(3553, 10243, var12);
            GL11.glTexParameteri(3553, 10240, var13);
            GL11.glTexParameteri(3553, 10241, var13);
            break;
         case 3:
            GL12.glTexImage3D(32879, 0, var2.getId(), var3, var4, var5, 0, var6.getId(), var7.getId(), var8);
            GL11.glTexParameteri(32879, 10242, var12);
            GL11.glTexParameteri(32879, 10243, var12);
            GL11.glTexParameteri(32879, 32882, var12);
            GL11.glTexParameteri(32879, 10240, var13);
            GL11.glTexParameteri(32879, 10241, var13);
            break;
         case 4:
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
