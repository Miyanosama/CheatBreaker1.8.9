package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.optifine.render.ChunkVisibility;

public class GuiSlider extends GuiButton {
   public String name;
   public EntityAIAvoidEntity field_0007;
   public float max;
   public boolean isMouseDown;
   public ChunkVisibility field_0001;
   public GuiPageButtonList$GuiResponder responder;
   public float min;
   public GuiSlider$FormatHelper formatHelper;
   public float sliderPosition = 1.0F;

   public float func_175217_d() {
      return this.sliderPosition;
   }

   public float func_175220_c() {
      return this.min + (this.max - this.min) * this.sliderPosition;
   }

   public void func_175219_a(float var1) {
      this.sliderPosition = var1;
      this.j = this.getDisplayString();
      this.responder.onTick(this.k, this.func_175220_c());
   }

   public void func_175218_a(float var1, boolean var2) {
      this.sliderPosition = (var1 - this.min) / (this.max - this.min);
      this.j = this.getDisplayString();
      if (var2) {
         this.responder.onTick(this.k, this.func_175220_c());
      }
   }

   @Override
   public int getHoverState(boolean var1) {
      return 0;
   }

   public String getDisplayString() {
      return this.formatHelper == null
         ? I18n.format(this.name) + ": " + this.func_175220_c()
         : this.formatHelper.getText(this.k, I18n.format(this.name), this.func_175220_c());
   }

   @Override
   public void mouseDragged(Minecraft var1, int var2, int var3) {
      if (this.m) {
         if (this.isMouseDown) {
            this.sliderPosition = (float)(var2 - (this.h + 4)) / (this.f - 8);
            if (this.sliderPosition < 0.0F) {
               this.sliderPosition = 0.0F;
            }

            if (this.sliderPosition > 1.0F) {
               this.sliderPosition = 1.0F;
            }

            this.j = this.getDisplayString();
            this.responder.onTick(this.k, this.func_175220_c());
         }

         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.h + (int)(this.sliderPosition * (this.f - 8)), this.i, 0, 66, 4, 20);
         this.drawTexturedModalRect(this.h + (int)(this.sliderPosition * (this.f - 8)) + 4, this.i, 196, 66, 4, 20);
      }
   }

   @Override
   public boolean mousePressed(Minecraft var1, int var2, int var3) {
      if (super.mousePressed(var1, var2, var3)) {
         this.sliderPosition = (float)(var2 - (this.h + 4)) / (this.f - 8);
         if (this.sliderPosition < 0.0F) {
            this.sliderPosition = 0.0F;
         }

         if (this.sliderPosition > 1.0F) {
            this.sliderPosition = 1.0F;
         }

         this.j = this.getDisplayString();
         this.responder.onTick(this.k, this.func_175220_c());
         this.isMouseDown = true;
         return true;
      } else {
         return false;
      }
   }

   public GuiSlider(
      GuiPageButtonList$GuiResponder var1, int var2, int var3, int var4, String var5, float var6, float var7, float var8, GuiSlider$FormatHelper var9
   ) {
      super(var2, var3, var4, 150, 20, "");
      this.name = var5;
      this.min = var6;
      this.max = var7;
      this.sliderPosition = (var8 - var6) / (var7 - var6);
      this.formatHelper = var9;
      this.responder = var1;
      this.j = this.getDisplayString();
   }

   @Override
   public void mouseReleased(int var1, int var2) {
      this.isMouseDown = false;
   }
}
