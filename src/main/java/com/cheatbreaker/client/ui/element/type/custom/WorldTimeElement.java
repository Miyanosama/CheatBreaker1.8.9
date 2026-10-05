package com.cheatbreaker.client.ui.element.type.custom;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.element.type.custom.WorldTimeElement$EnumSwitch;

public class WorldTimeElement extends AbstractModulesGuiElement {
   public ResourceLocation moonIcon;
   public float recoveredField3368;
   public boolean recoveredField3369;
   public Setting setting;
   public ResourceLocation sunIcon;
   public float time = -1.0F;

   public WorldTimeElement(Setting var1, float var2) {
      super(var2);
      this.recoveredField3369 = false;
      this.sunIcon = new ResourceLocation("client/icons/sun-64.png");
      this.moonIcon = new ResourceLocation("client/icons/moon-64.png");
      this.setting = var1;
      this.height = 22;
      this.time = Float.parseFloat("" + var1.getValue());
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      short var6 = 148;
      CheatBreaker.getInstance().recoveredField1589.drawString(this.setting.method_08911().toUpperCase(), this.x + 10, this.y + 8, -1895825408);
      if (this.recoveredField3369 && !Mouse.isButtonDown(0)) {
         this.recoveredField3369 = false;
      }

      float var10002 = this.x + 172 + var6 / 2.0F;
      float var10003 = this.y - 2;
      CheatBreaker.getInstance().recoveredField1589.drawCenteredString("SERVER", var10002, var10003, -1895825408);
      Gui.drawRect(this.x + 172 + var6 / 2 - 0.5F, this.y + 8, this.x + 172 + var6 / 2 + 0.5F, this.y + 14, 1862270976);
      RenderUtil.method_22064(this.moonIcon, this.x + 180 - 3.25F, this.y + 3, 7.5F, 7.5F);
      Gui.drawRect(this.x + 180 - 0.5F, this.y + 12, this.x + 180 + 0.5F, this.y + 14, 1862270976);
      RenderUtil.method_22064(this.sunIcon, this.x + 170 + var6 - 10 - 5.0F, this.y + 2, 10.0F, 10.0F);
      Gui.drawRect(this.x + 170 + var6 - 10 - 0.5F, this.y + 12, this.x + 170 + var6 - 10 + 0.5F, this.y + 14, 1862270976);
      boolean var7 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + var6 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 20 + this.yOffset) * this.scale;
      RenderUtil.method_22054(this.x + 174, this.y + 16, this.x + 170 + var6 - 4, this.y + 18, 1.0, var7 ? -1895825408 : 1862270976);
      double var8 = var6 - 18;
      if (this.setting.method_08904() != null && this.setting.method_08878() != null) {
         float var10 = Float.parseFloat("" + this.setting.method_08904());
         float var11 = Float.parseFloat("" + this.setting.method_08878());
         if (this.recoveredField3369) {
            this.recoveredField3368 = (float)Math.round((var10 + (var1 - (this.x + 180) * this.scale) * ((var11 - var10) / (var8 * this.scale))) * 100.0)
               / 100.0F;
            if (this.recoveredField3368 < -13490.0F && this.recoveredField3368 > -15490.0F) {
               this.recoveredField3368 = -14490.0F;
            }

            if (this.setting.getType().equals(Setting.Type.INTEGER)) {
               this.recoveredField3368 = Math.round(this.recoveredField3368);
            }

            if (this.recoveredField3368 < var10) {
               this.recoveredField3368 = var10;
            } else if (this.recoveredField3368 > var11) {
               this.recoveredField3368 = var11;
            }

            switch (WorldTimeElement$EnumSwitch.recoveredField623[this.setting.getType().ordinal()]) {
               case 1:
                  this.setting.setValue(Integer.parseInt((int)this.recoveredField3368 + ""));
                  break;
               case 2:
                  this.setting.setValue(this.recoveredField3368);
                  break;
               case 3:
                  this.setting.setValue(Double.parseDouble(this.recoveredField3368 + ""));
            }
         }

         float var5;
         float var15;
         var5 = (var5 = Float.parseFloat(this.setting.getValue() + "")) < this.time ? this.time - var5 : (var15 = var5 - this.time);
         float var12 = ((var11 - var10) / 20.0F + var5 * 8.0F) / (Minecraft.debugFPS + 1);
         if (var12 < 1.0E-4) {
            var12 = 1.0E-4F;
         }

         float var4;
         if (this.time < (var4 = Float.parseFloat(this.setting.getValue() + ""))) {
            this.time = this.time + var12 <= var4 ? (this.time += var12) : var4;
         } else if (this.time > var4) {
            this.time = this.time - var12 >= var4 ? (this.time -= var12) : var4;
         }

         double var13 = 100.0F * ((this.time - var10) / (var11 - var10));
         RenderUtil.method_22054(this.x + 174, this.y + 16, this.x + 180 + var8 * var13 / 100.0, this.y + 18, 4.0, -12418828);
         GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
         RenderUtil.method_22052(this.x + 181.25F + var8 * var13 / 100.0, this.y + 17.25F, 4.5);
         if (this.time == -14490.0F) {
            GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
         } else {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         }

         RenderUtil.method_22052(this.x + 181.25F + var8 * var13 / 100.0, this.y + 17.25F, 2.7F);
      }
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      short var4 = 170;
      boolean var5 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + var4 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + 20 + this.yOffset) * this.scale;
      if (var3 == 0 && var5) {
         this.recoveredField3369 = true;
      }
   }
}
