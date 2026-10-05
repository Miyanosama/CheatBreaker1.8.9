package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.http.websocketx.WebSocketHandshakeException;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.command.server.CommandAchievement;
import org.lwjgl.opengl.GL11;

public class CrosshairModule extends AbstractModule {
   public Setting field_0009;
   public Setting field_0011;
   public GuiLabel field_0003;
   public Setting field_0004;
   public Setting field_0016;
   public Setting field_0013;
   public Setting field_0018;
   public Setting field_0015;
   public WebSocketHandshakeException field_0001;
   public Setting field_0007;
   public Setting field_0010;
   public Setting field_0014;
   public Setting field_0002;
   public Setting field_0008;
   public Setting field_0006;
   public Setting field_0017;
   public CommandAchievement field_0005;
   public Setting field_0012;
   public Setting field_0000;

   public void method_28369(float var1, float var2, boolean var3) {
      GL11.glPushMatrix();
      float var4 = (Float)this.field_0044.getValue();
      if (var3) {
         if (!this.field_0001.getValue().equals("Global")) {
            String var5 = (String)this.field_0001.getValue();
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
      float var7 = this.field_0014.method_08905();
      float var8 = this.field_0006.method_08905();
      float var9 = this.field_0012.method_08905();
      float var10 = this.field_0013.method_08905();
      float var11 = this.field_0010.method_08905();
      int var12 = this.field_0016.method_08901();
      boolean var13 = this.field_0004.method_08908();
      GL11.glColor4f((var12 >> 16 & 0xFF) / 255.0F, (var12 >> 8 & 0xFF) / 255.0F, (var12 & 0xFF) / 255.0F, (var12 >> 24 & 0xFF) / 255.0F);
      this.minecraft.getTextureManager().bindTexture(Gui.icons);
      GL11.glEnable(3008);
      if ((Boolean)this.field_0002.getValue()) {
         if ((Boolean)this.field_0009.getValue()) {
            OpenGlHelper.glBlendFunc(775, 769, 1, 0);
         } else {
            OpenGlHelper.glBlendFunc(768, 769, 1, 0);
         }

         this.minecraft.ingameGUI.drawTexturedModalRect(var14 - 7.0F, var15 - 7.0F, 0, 0, 16, 16);
      } else {
         if ((Boolean)this.field_0009.getValue()) {
            OpenGlHelper.glBlendFunc(775, 769, 1, 0);
         }

         if ((Boolean)this.field_0008.getValue()) {
            RenderUtil.method_22054(var14 - var11, var15 - var11, var14 + var11, var15 + var11, 0.0, var12);
         }

         RenderUtil.method_22054(var14 - var8 - var7, var15 - var9 / 2.0F, var14 - var8, var15 + var9 / 2.0F, 0.0, var12);
         RenderUtil.method_22054(var14 + var8, var15 - var9 / 2.0F, var14 + var8 + var7, var15 + var9 / 2.0F, 0.0, var12);
         RenderUtil.method_22054(var14 - var9 / 2.0F, var15 - var8 - var7, var14 + var9 / 2.0F, var15 - var8, 0.0, var12);
         RenderUtil.method_22054(var14 - var9 / 2.0F, var15 + var8, var14 + var9 / 2.0F, var15 + var8 + var7, 0.0, var12);
         if (var13) {
            Gui.drawBoxWithOutLine(var14 - var8 - var7, var15 - var9 / 2.0F, var14 - var8, var15 + var9 / 2.0F, var10, this.field_0011.method_08901(), 0);
            Gui.drawBoxWithOutLine(var14 + var8, var15 - var9 / 2.0F, var14 + var8 + var7, var15 + var9 / 2.0F, var10, this.field_0011.method_08901(), 0);
            Gui.drawBoxWithOutLine(var14 - var9 / 2.0F, var15 - var8 - var7, var14 + var9 / 2.0F, var15 - var8, var10, this.field_0011.method_08901(), 0);
            Gui.drawBoxWithOutLine(var14 - var9 / 2.0F, var15 + var8, var14 + var9 / 2.0F, var15 + var8 + var7, var10, this.field_0011.method_08901(), 0);
            if ((Boolean)this.field_0008.getValue()) {
               Gui.drawBoxWithOutLine(var14 - var11, var15 - var11, var14 + var11, var15 + var11, var10, this.field_0011.method_08901(), 0);
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
      this.field_0018 = new Setting(this, "label").setValue("Crosshair Preview");
      this.field_0007 = new Setting(
            this, "Preview Background", "Change the preview background to see what the crosshair can look like in multiple environments."
         )
         .setValue("Birch")
         .acceptedValues("Birch", "Roofed", "Swamp", "Hills", "Desert", "Mesa", "Nether", "Sky");
      this.field_0017 = new Setting(this, "label").setValue("General Options");
      this.field_0002 = new Setting(this, "Use Pack Crosshair").setValue(false);
      this.field_0004 = new Setting(this, "Outline", "Add an outline around the crosshair.")
         .setValue(false)
         .method_08894(() -> !(Boolean)this.field_0002.getValue());
      this.field_0008 = new Setting(this, "Dot").setValue(false).method_08894(() -> !(Boolean)this.field_0002.getValue());
      this.field_0012 = new Setting(this, "Thickness", "Change the thickness of the crosshair.")
         .setValue(2.0F)
         .setMinMax(0.5F, 3.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.field_0002.getValue());
      this.field_0013 = new Setting(this, "Outline Thickness", "Change the outline thickness of the crosshair.")
         .setValue(0.5F)
         .setMinMax(0.5F, 3.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.field_0002.getValue() && (Boolean)this.field_0004.getValue());
      this.field_0014 = new Setting(this, "Size", "Change the size of the crosshair.")
         .setValue(4.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.field_0002.getValue());
      this.field_0006 = new Setting(this, "Gap", "Change how distant the cross is from the center.")
         .setValue(2.0F)
         .setMinMax(1.0F, 7.5F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.field_0002.getValue());
      this.field_0010 = new Setting(this, "Dot Size", "Change the size of the dot.")
         .setValue(1.0F)
         .setMinMax(0.5F, 3.0F)
         .method_08892("px")
         .method_08894(() -> !(Boolean)this.field_0002.getValue() && (Boolean)this.field_0008.getValue());
      this.field_0000 = new Setting(this, "label").setValue("Color Options");
      this.field_0009 = new Setting(this, "Vanilla Blending", "Make the crosshair's color invert depending on what you are looking at.").setValue(true);
      this.field_0016 = new Setting(this, "Crosshair Color", "Change the color of the crosshair.").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.field_0015 = new Setting(this, "Dot Color", "Change the color of the crosshair dot.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !(Boolean)this.field_0002.getValue() && (Boolean)this.field_0008.getValue());
      this.field_0011 = new Setting(this, "Outline Color", "Change the color of the crosshair outline.")
         .setValue(-1358954496)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> !(Boolean)this.field_0002.getValue() && (Boolean)this.field_0004.getValue());
      this.method_28821("Replace the Vanilla crosshair with your own custom crosshair.");
   }
}
