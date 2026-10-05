package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.element.type.IconNumericSliderElement$EnumSwitch;

import com.cheatbreaker.client.ui.util.GuiThemeColors;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class IconNumericSliderElement extends AbstractModulesGuiElement {
   public ResourceLocation recoveredField1846;
   public ResourceLocation recoveredField1847;
   public boolean recoveredField1848;
   public float recoveredField1849;
   public boolean recoveredField1850;
   public ResourceLocation recoveredField1851;
   public float recoveredField1852 = -1.0F;
   public boolean recoveredField1853 = false;
   public ResourceLocation recoveredField1854;
   public ResourceLocation recoveredField1855;
   public ResourceLocation recoveredField1856 = new ResourceLocation("client/icons/sun-64.png");
   public ResourceLocation recoveredField1857 = new ResourceLocation("client/icons/moon-64.png");

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      short var6 = 148;
      if (this.recoveredField1853 && !Mouse.isButtonDown(0)) {
         this.recoveredField1853 = false;
      }

      float var7 = 8.0F;
      float var8 = 16.0F;
      float var9 = 17.25F;
      byte var10 = 20;
      if (!this.setting.method_08872().isEmpty()) {
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawCenteredString(
               this.setting.method_08872(),
               this.x + 172 + var6 / 2,
               this.y - 2,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
            );
         Gui.drawRect(
            this.x + 172 + var6 / 2 - 0.5F,
            this.y + 8,
            this.x + 172 + var6 / 2 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         this.recoveredField1848 = true;
      }

      float var11 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var11, var11, var11, 0.43F);
      if (!this.setting.method_08882().isEmpty() && !this.setting.method_08875().isEmpty()) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(new ResourceLocation("client/icons/" + this.setting.method_08882() + ".png"), this.x + 180 - 5.0F, this.y + 3, 10.0F, 10.0F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(
            new ResourceLocation("client/icons/" + this.setting.method_08875() + ".png"), this.x + 170 + var6 - 10.0F - 5.0F, this.y + 3, 10.0F, 10.0F
         );
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         this.recoveredField1850 = true;
      } else if (this.setting.method_08911().endsWith("Opacity")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1846, this.x + 180 - 4.0F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1854, this.x + 170 + var6 - 7.5F - 6.5F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         this.recoveredField1850 = true;
      } else if (this.setting.method_08911().endsWith("Volume")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1847, this.x + 180 - 3.25F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1855, this.x + 170 + var6 - 7.5F - 5.0F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         this.recoveredField1850 = true;
      } else if (this.setting.method_08911().equals("World Time")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1857, this.x + 180 - 3.25F, this.y + 3, 7.5F, 7.5F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1856, this.x + 170 + var6 - 10 - 5.0F, this.y + 2, 10.0F, 10.0F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         this.recoveredField1850 = true;
      }

      if (this.setting.method_08911().endsWith("Scale") || this.setting.method_08911().endsWith("Scale Multiplier")) {
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1851, this.x + 180 - 3.0F, this.y + 4, 6.0F, 6.0F);
         Gui.drawRect(
            this.x + 180 - 0.5F,
            this.y + 12,
            this.x + 180 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         GL11.glColor4f(var11, var11, var11, 0.43F);
         RenderUtil.method_22064(this.recoveredField1851, this.x + 170 + var6 - 10 - 4.0F, this.y + 2.5F, 8.0F, 8.0F);
         Gui.drawRect(
            this.x + 170 + var6 - 10 - 0.5F,
            this.y + 12,
            this.x + 170 + var6 - 10 + 0.5F,
            this.y + 14,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
         this.recoveredField1850 = true;
      }

      if (!this.recoveredField1850 && !this.recoveredField1848) {
         this.height = 12;
         var7 = 2.0F;
         var8 = 6.0F;
         var9 = 7.25F;
         var10 = 10;
      }

      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(
            this.setting.method_08911().toUpperCase(),
            this.x + 10,
            this.y + var7,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
         );
      if (this.setting.method_08876()) {
         String var12 = this.setting.getValue().toString() + this.setting.method_08870();
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawString(
               var12,
               (float)(this.x + 169) - CheatBreaker.getInstance().recoveredField1589.getStringWidth(var12),
               this.y + var7,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
            );
      }

      boolean var22 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + var6 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + var10 + this.yOffset) * this.scale;
      RenderUtil.method_22054(
         this.x + 174,
         this.y + var8,
         this.x + 170 + var6 - 4,
         this.y + var8 + 2.0F,
         1.0,
         var22
            ? (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654)
            : (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662)
      );
      double var13 = var6 - 18;
      if (this.setting.method_08904() != null && this.setting.method_08878() != null) {
         float var15 = Float.parseFloat("" + this.setting.method_08904());
         float var16 = Float.parseFloat("" + this.setting.method_08878());
         if (this.recoveredField1853) {
            this.recoveredField1849 = (float)Math.round((var15 + (var1 - (this.x + 180) * this.scale) * ((var16 - var15) / (var13 * this.scale))) * 100.0)
               / 100.0F;
            if (this.setting.getType().equals(Setting.Type.INTEGER) || Keyboard.isKeyDown(42)) {
               this.recoveredField1849 = Math.round(this.recoveredField1849);
            }

            if (this.recoveredField1849 < var15) {
               this.recoveredField1849 = var15;
            } else if (this.recoveredField1849 > var16) {
               this.recoveredField1849 = var16;
            }

            switch (IconNumericSliderElement$EnumSwitch.recoveredField1469[this.setting.getType().ordinal()]) {
               case 1:
                  this.setting.setValue(Integer.parseInt((int)this.recoveredField1849 + ""));
                  break;
               case 2:
                  this.setting.setValue(this.recoveredField1849);
                  break;
               case 3:
                  this.setting.setValue(Double.parseDouble(this.recoveredField1849 + ""));
            }

            Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
         }

         float var5;
         float var20;
         var5 = (var5 = Float.parseFloat(this.setting.getValue() + "")) < this.recoveredField1852
            ? this.recoveredField1852 - var5
            : (var20 = var5 - this.recoveredField1852);
         float var17 = ((var16 - var15) / 20.0F + var5 * 8.0F) / (Minecraft.debugFPS + 1);
         if (var17 < 1.0E-4) {
            var17 = 1.0E-4F;
         }

         float var4;
         if (this.recoveredField1852 < (var4 = Float.parseFloat(this.setting.getValue() + ""))) {
            this.recoveredField1852 = this.recoveredField1852 + var17 <= var4 ? (this.recoveredField1852 += var17) : var4;
         } else if (this.recoveredField1852 > var4) {
            this.recoveredField1852 = this.recoveredField1852 - var17 >= var4 ? (this.recoveredField1852 -= var17) : var4;
         }

         double var18 = 100.0F * ((this.recoveredField1852 - var15) / (var16 - var15));
         RenderUtil.method_22054(this.x + 174, this.y + var8, this.x + 180 + var13 * var18 / 100.0, this.y + var8 + 2.0F, 4.0, -12418828);
         GL11.glColor4f(0.25F, 0.45F, 1.0F, 1.0F);
         RenderUtil.method_22052(this.x + 181.25F + var13 * var18 / 100.0, this.y + var9, 4.5);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.method_22052(this.x + 181.25F + var13 * var18 / 100.0, this.y + var9, 2.7F);
         this.method_23220(this.setting, var1, var2);
      }
   }

   public float method_25117(float var1) {
      return Math.round(var1 * 2.0F) / 2.0F;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      short var4 = 170;
      byte var5 = 20;
      if (this.height == 12) {
         var5 = 10;
      }

      boolean var6 = var1 > (this.x + 170) * this.scale
         && var1 < (this.x + 170 + 148 - 2) * this.scale
         && var2 > (this.y + 4 + this.yOffset) * this.scale
         && var2 < (this.y + var5 + this.yOffset) * this.scale;
      if (var3 == 0 && var6) {
         this.recoveredField1853 = true;
      }
   }

   public IconNumericSliderElement(Setting var1, float var2) {
      super(var2);
      this.recoveredField1847 = new ResourceLocation("client/icons/volume-mute-64.png");
      this.recoveredField1855 = new ResourceLocation("client/icons/volume-up-64.png");
      this.recoveredField1854 = new ResourceLocation("client/icons/circle-64.png");
      this.recoveredField1846 = new ResourceLocation("client/icons/circle-hollow-64.png");
      this.recoveredField1851 = new ResourceLocation("client/icons/letter-t-64.png");
      this.recoveredField1850 = false;
      this.recoveredField1848 = false;
      this.setting = var1;
      this.height = 22;
      this.recoveredField1852 = Float.parseFloat("" + var1.getValue());
   }
}
