package net.optifine.shaders.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiButtonDownloadShaders extends GuiButton {
   public GuiButtonDownloadShaders(int var1, int var2, int var3) {
      super(var1, var2, var3, 22, 20, "");
   }

   @Override
   public void drawButton(Minecraft var1, int var2, int var3) {
      if (this.m) {
         super.drawButton(var1, var2, var3);
         ResourceLocation var4 = new ResourceLocation("optifine/textures/icons.png");
         var1.getTextureManager().bindTexture(var4);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.h + 3, this.i + 2, 0, 0, 16, 16);
      }
   }
}
