package net.optifine.shaders.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.optifine.shaders.config.ShaderOption;

public class GuiSliderShaderOption extends GuiButtonShaderOption {
   public boolean dragging;
   public float sliderValue = 1.0F;
   public ShaderOption shaderOption = null;

   @Override
   public void mouseDragged(Minecraft var1, int var2, int var3) {
      if (this.m) {
         if (this.dragging && !GuiScreen.isShiftKeyDown()) {
            this.sliderValue = (float)(var2 - (this.h + 4)) / (this.f - 8);
            this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
            this.shaderOption.setIndexNormalized(this.sliderValue);
            this.sliderValue = this.shaderOption.getIndexNormalized();
            this.j = GuiShaderOptions.getButtonText(this.shaderOption, this.f);
         }

         var1.getTextureManager().bindTexture(a);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.h + (int)(this.sliderValue * (this.f - 8)), this.i, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.h + (int)(this.sliderValue * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
      }
   }

   @Override
   public int getHoverState(boolean var1) {
      return 0;
   }

   @Override
   public void valueChanged() {
      this.sliderValue = this.shaderOption.getIndexNormalized();
   }

   @Override
   public boolean isSwitchable() {
      return false;
   }

   public GuiSliderShaderOption(int var1, int var2, int var3, int var4, int var5, ShaderOption var6, String var7) {
      super(var1, var2, var3, var4, var5, var6, var7);
      this.shaderOption = var6;
      this.sliderValue = var6.getIndexNormalized();
      this.j = GuiShaderOptions.getButtonText(var6, this.f);
   }

   @Override
   public void mouseReleased(int var1, int var2) {
      this.dragging = false;
   }

   @Override
   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if (super.mousePressed(var1, var2, var3)) {
         this.sliderValue = (float)(var2 - (this.h + 4)) / (this.f - 8);
         this.sliderValue = MathHelper.clamp_float(this.sliderValue, 0.0F, 1.0F);
         this.shaderOption.setIndexNormalized(this.sliderValue);
         this.j = GuiShaderOptions.getButtonText(this.shaderOption, this.f);
         this.dragging = true;
         return true;
      } else {
         return false;
      }
   }
}
