package net.minecraft.client.network;

import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import io.netty.handler.codec.spdy.SpdyHttpEncoder;
import net.minecraft.client.renderer.block.model.ItemModelGenerator$1;

// $VF: synthetic class
public class NetworkPlayerInfo$2 {
   public SpdyHttpEncoder field_0002;
   public ItemModelGenerator$1 field_0000;

   static {
      try {
         field_178875_a[Type.SKIN.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_178875_a[Type.CAPE.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
