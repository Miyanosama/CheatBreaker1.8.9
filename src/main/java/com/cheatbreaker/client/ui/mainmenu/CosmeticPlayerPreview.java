package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.cosmetic.CosmeticType;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

/** A menu-only model: never creates a world or changes the live player's pose. */
final class CosmeticPlayerPreview {
   private final Entity pose = new Entity(null) {
      public void k_() { }
      public void readEntityFromNBT(NBTTagCompound tag) { }
      public void writeEntityToNBT(NBTTagCompound tag) { }
   };
   private ModelPlayer model;
   private ResourceLocation skin;
   private boolean requestedSkin;

   void draw(float x, float y, float size, float yaw) {
      Minecraft mc = Minecraft.getMinecraft();
      if (!requestedSkin) {
         requestedSkin = true;
         skin = DefaultPlayerSkin.getDefaultSkin(mc.getSession().getProfile().getId());
         model = new ModelPlayer(0F, "slim".equals(DefaultPlayerSkin.getSkinType(mc.getSession().getProfile().getId())));
         mc.getSkinManager().loadProfileTextures(mc.getSession().getProfile(), (type, location, texture) -> {
            if (type == MinecraftProfileTexture.Type.SKIN) {
               skin = location;
               model = new ModelPlayer(0F, "slim".equals(texture.getMetadata("model")));
            }
         }, false);
      }
      GlStateManager.pushMatrix();
      GlStateManager.translate(x, y, 100F);
      GlStateManager.scale(size, size, -size);
      GlStateManager.rotate(135F, 0F, 1F, 0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135F, 0F, 1F, 0F);
      GlStateManager.rotate(-10F, 1F, 0F, 0F);
      GlStateManager.rotate(yaw, 0F, 1F, 0F);
      GlStateManager.enableDepth();
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableColorMaterial();
      GlStateManager.color(1F, 1F, 1F, 1F);
      mc.getTextureManager().bindTexture(skin);
      model.render(pose, 0F, 0F, 0F, 0F, 0F, 0.0625F);
      ClientResourceManager cape = CheatBreaker.getInstance().method_19791().getLocalCosmetics().getEquipped(CosmeticType.CAPE);
      if (cape != null && CheatBreaker.getInstance().method_19791().getPreviewCache().bindModelCape(cape)) {
         GlStateManager.pushMatrix();
         GlStateManager.translate(0F, 0F, 0.125F);
         GlStateManager.rotate(6F, 1F, 0F, 0F);
         GlStateManager.rotate(180F, 0F, 1F, 0F);
         model.renderCape(0.0625F);
         GlStateManager.popMatrix();
      }
      ClientResourceManager wings = CheatBreaker.getInstance().method_19791().getLocalCosmetics().getEquipped(CosmeticType.WINGS);
      if (wings != null) model.recoveredField3328.renderPreview(0.0625F, wings.method_20846(), wings.method_20859());
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.disableColorMaterial();
      GlStateManager.disableDepth();
      GlStateManager.color(1F, 1F, 1F, 1F);
   }
}
