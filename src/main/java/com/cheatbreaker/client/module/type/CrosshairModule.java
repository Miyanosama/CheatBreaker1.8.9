package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class CrosshairModule extends AbstractModule {
   public Setting recoveredField2705;
   public Setting recoveredField2706;
   public Setting recoveredField2707;
   public Setting recoveredField2708;
   public Setting recoveredField2709;
   public Setting recoveredField2710;
   public Setting recoveredField2711;
   public Setting recoveredField2712;
   public Setting recoveredField2713;
   public Setting recoveredField2714;
   public Setting recoveredField2715;
   public Setting recoveredField2716;
   public Setting recoveredField2717;
   public Setting recoveredField2718;
   public Setting recoveredField2719;
   public Setting recoveredField2720;

   public void method_28369(float var1, float var2, boolean var3) {
      GL11.glPushMatrix();
      float var4 = (Float)this.recoveredField3895.getValue();
      if (var3) {
         if (!(Boolean)this.recoveredField3908.getValue().equals("Global")) {
            String var5 = (String)this.recoveredField3908.getValue();
            switch (var5) {
               case "Small":
                  var4 = var4 * 0.5F / CheatBreaker.method_19763();
                  break;
               case "Normal":
                  var4 /= CheatBreaker.method_19763();
                  break;
               case "Large":
                  var4 = var4 * 1.5F / CheatBreaker.method_19763();
                  break;
               case "Auto":
                  var4 = var4 * 2.0F / CheatBreaker.method_19763();
            }
         } else {
            var4 *= this.method_28770();
         }
      }

      float var14 = var1 / var4;
      float var15 = var2 / var4;
      GL11.glScalef(var4, var4, var4);
      float var7 = this.recoveredField2714.method_08905();
      float var8 = this.recoveredField2717.method_08905();
      float var9 = this.recoveredField2719.method_08905();
      float var10 = this.recoveredField2709.method_08905();
      float var11 = this.recoveredField2713.method_08905();
      int var12 = this.recoveredField2708.method_08901();
      boolean var13 = this.recoveredField2707.method_08908();
      GL11.glColor4f((var12 >> 16 & 0xFF) / 255.0F, (var12 >> 8 & 0xFF) / 255.0F, (var12 & 0xFF) / 255.0F, (var12 >> 24 & 0xFF) / 255.0F);
      this.minecraft.getTextureManager().bindTexture(Gui.icons);
      GL11.glEnable(3008);
      if ((Boolean)this.recoveredField2715.getValue()) {
         if ((Boolean)this.recoveredField2705.getValue()) {
            OpenGlHelper.glBlendFunc(775, 769, 1, 0);
         } else {
            OpenGlHelper.glBlendFunc(768, 769, 1, 0);
         }

         this.minecraft.ingameGUI.drawTexturedModalRect(var14 - 7.0F, var15 - 7.0F, 0, 0, 16, 16);
      } else {
         if ((Boolean)this.recoveredField2705.getValue()) {
            OpenGlHelper.glBlendFunc(775, 769, 1, 0);
         }

         if ((Boolean)this.recoveredField2716.getValue()) {
            RenderUtil.method_22054(var14 - var11, var15 - var11, var14 + var11, var15 + var11, 0.0, var12);
         }

         RenderUtil.method_22054(var14 - var8 - var7, var15 - var9 / 2.0F, var14 - var8, var15 + var9 / 2.0F, 0.0, var12);
         RenderUtil.method_22054(var14 + var8, var15 - var9 / 2.0F, var14 + var8 + var7, var15 + var9 / 2.0F, 0.0, var12);
         RenderUtil.method_22054(var14 - var9 / 2.0F, var15 - var8 - var7, var14 + var9 / 2.0F, var15 - var8, 0.0, var12);
         RenderUtil.method_22054(var14 - var9 / 2.0F, var15 + var8, var14 + var9 / 2.0F, var15 + var8 + var7, 0.0, var12);
         if (var13) {
            Gui.drawBoxWithOutLine(
               var14 - var8 - var7, var15 - var9 / 2.0F, var14 - var8, var15 + var9 / 2.0F, var10, this.recoveredField2706.method_08901(), 0
            );
            Gui.drawBoxWithOutLine(
               var14 + var8, var15 - var9 / 2.0F, var14 + var8 + var7, var15 + var9 / 2.0F, var10, this.recoveredField2706.method_08901(), 0
            );
            Gui.drawBoxWithOutLine(
               var14 - var9 / 2.0F, var15 - var8 - var7, var14 + var9 / 2.0F, var15 - var8, var10, this.recoveredField2706.method_08901(), 0
            );
            Gui.drawBoxWithOutLine(
               var14 - var9 / 2.0F, var15 + var8, var14 + var9 / 2.0F, var15 + var8 + var7, var10, this.recoveredField2706.method_08901(), 0
            );
            if ((Boolean)this.recoveredField2716.getValue()) {
               Gui.drawBoxWithOutLine(var14 - var11, var15 - var11, var14 + var11, var15 + var11, var10, this.recoveredField2706.method_08901(), 0);
            }
         }
      }

      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glPopMatrix();
   }

   public CrosshairModule() {
      super("Crosshair", "Normal");
      this.setDefaultState(false);
      this.recoveredField2710 = new Setting(this, "label").setValue("Crosshair Preview");
      this.recoveredField2712 = new Setting(
            this, "Preview Background", "Change the preview background to see what the crosshair can look like in multiple environments."
         )
         .setValue("Birch")
         .acceptedValues("Birch", "Roofed", "Swamp", "Hills", "Desert", "Mesa", "Nether", "Sky");
      this.recoveredField2718 = new Setting(this, "label").setValue("General Options");
      this.recoveredField2715 = new Setting(this, "Use Pack Crosshair").setValue(false);
      this.recoveredField2707 = new Setting(this, "Outline", "Add an outline around the crosshair.")
         .setValue(false)
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue());
      this.recoveredField2716 = new Setting(this, "Dot").setValue(false).method_08894(() -> !(Boolean)this.recoveredField2715.getValue());
      this.recoveredField2719 = new Setting(this, "Thickness", "Change the thickness of the crosshair.")
         .setValue(2.0F)
         .setMinMax(0.5F, 3.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue());
      this.recoveredField2709 = new Setting(this, "Outline Thickness", "Change the outline thickness of the crosshair.")
         .setValue(0.5F)
         .setMinMax(0.5F, 3.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue() && (Boolean)this.recoveredField2707.getValue());
      this.recoveredField2714 = new Setting(this, "Size", "Change the size of the crosshair.")
         .setValue(4.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue());
      this.recoveredField2717 = new Setting(this, "Gap", "Change how distant the cross is from the center.")
         .setValue(2.0F)
         .setMinMax(1.0F, 7.5F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue());
      this.recoveredField2713 = new Setting(this, "Dot Size", "Change the size of the dot.")
         .setValue(1.0F)
         .setMinMax(0.5F, 3.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue() && (Boolean)this.recoveredField2716.getValue());
      this.recoveredField2720 = new Setting(this, "label").setValue("Color Options");
      this.recoveredField2705 = new Setting(this, "Vanilla Blending", "Make the crosshair's color invert depending on what you are looking at.").setValue(true);
      this.recoveredField2708 = new Setting(this, "Crosshair Color", "Change the color of the crosshair.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.recoveredField2711 = new Setting(this, "Dot Color", "Change the color of the crosshair dot.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue() && (Boolean)this.recoveredField2716.getValue());
      this.recoveredField2706 = new Setting(this, "Outline Color", "Change the color of the crosshair outline.")
         .setValue(-1358954496)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !(Boolean)this.recoveredField2715.getValue() && (Boolean)this.recoveredField2707.getValue());
      this.method_28821("Replace the Vanilla crosshair with your own custom crosshair.");
   }
}
