package net.minecraft.client.resources;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.entity.Entity$3;
import net.minecraft.entity.monster.EntityGhast$GhastMoveHelper;
import net.minecraft.network.NettyEncryptingDecoder;
import net.minecraft.util.ResourceLocation;

public class SkinManager$2 implements IImageBuffer {
   public NettyEncryptingDecoder field_0004;
   public EntityGhast$GhastMoveHelper field_0007;
   public Entity$3 field_0000;

   @Override
   public BufferedImage parseUserSkin(BufferedImage var1) {
      if (this.field_152635_a != null) {
         var1 = this.field_152635_a.parseUserSkin(var1);
      }

      return var1;
   }

   public SkinManager$2(
      SkinManager var1, IImageBuffer var2, SkinManager$SkinAvailableCallback var3, Type var4, ResourceLocation var5, MinecraftProfileTexture var6
   ) {
      this.field_152639_e = var1;
      this.field_152635_a = var2;
      this.field_152636_b = var3;
      this.field_152637_c = var4;
      this.field_152638_d = var5;
      this.field_177249_e = var6;
      super();
   }

   @Override
   public void skinAvailable() {
      if (this.field_152635_a != null) {
         this.field_152635_a.skinAvailable();
      }

      if (this.field_152636_b != null) {
         this.field_152636_b.skinAvailable(this.field_152637_c, this.field_152638_d, this.field_177249_e);
      }
   }
}
