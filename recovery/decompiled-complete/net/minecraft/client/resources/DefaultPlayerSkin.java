package net.minecraft.client.resources;

import io.netty.handler.codec.http.websocketx.WebSocket13FrameEncoder;
import java.util.UUID;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.client.renderer.entity.RenderMinecart;
import net.minecraft.util.ResourceLocation;

public class DefaultPlayerSkin {
   public static ResourceLocation TEXTURE_ALEX = new ResourceLocation("textures/entity/alex.png");
   public RenderMinecart field_0004;
   public BlockNetherWart field_0001;
   public static ResourceLocation TEXTURE_STEVE = new ResourceLocation("textures/entity/steve.png");
   public WebSocket13FrameEncoder field_0000;

   public static ResourceLocation getDefaultSkin(UUID var0) {
      return isSlimSkin(var0) ? TEXTURE_ALEX : TEXTURE_STEVE;
   }

   public static boolean isSlimSkin(UUID var0) {
      return (var0.hashCode() & 1) == 1;
   }

   public static ResourceLocation getDefaultSkinLegacy() {
      return TEXTURE_STEVE;
   }

   public static String getSkinType(UUID var0) {
      return isSlimSkin(var0) ? "slim" : "default";
   }
}
