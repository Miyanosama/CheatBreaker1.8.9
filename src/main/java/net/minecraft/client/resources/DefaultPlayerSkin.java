package net.minecraft.client.resources;

import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class DefaultPlayerSkin {
   public static ResourceLocation TEXTURE_STEVE = new ResourceLocation("textures/entity/steve.png");
   public static ResourceLocation TEXTURE_ALEX = new ResourceLocation("textures/entity/alex.png");

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
