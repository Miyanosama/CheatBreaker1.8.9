package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public class ModuleSettingsElement extends AbstractModulesGuiElement {
   public AbstractScrollableElement parent;
   public AbstractModule module;
   public int recoveredField939 = 0;
   public ResourceLocation rightIcon = new ResourceLocation("client/icons/right.png");
   public int recoveredField940;

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      double var4 = this.height - 10;
      double var6 = var4 / this.parent.recoveredField3009 * 100.0;
      double var8 = var4 / 100.0 * var6;
      double var10 = this.parent.recoveredField3012 / 100.0 * var6;
      boolean var12 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11 - var10) * this.scale
         && var2 < (this.y + 8 + var8 - var10) * this.scale;
      boolean var13 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11) * this.scale
         && var2 < (this.y + 6 + var4 - 3.0) * this.scale;
      if (var3 == 0 && var13 || var12) {
         this.parent.recoveredField3014 = true;
      }

      this.parent.method_03200(this.module);
   }

   public ModuleSettingsElement(AbstractScrollableElement var1, int var2, AbstractModule var3, float var4) {
      super(var4);
      this.parent = var1;
      this.recoveredField940 = var2;
      this.module = var3;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = this.isMouseInside(var1, var2);
      byte var5 = 75;
      Gui.a(
         this.x,
         this.y + this.height - 1,
         this.x + this.width,
         this.y + this.height,
         GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
      );
      if (this.parent.method_03198(this.module)) {
         if (var4) {
            float var6 = CBModulesGui.getSmoothFloat(790.0F);
            if (this.recoveredField939 + var6 < var5) {
               this.recoveredField939 = (int)(this.recoveredField939 + var6);
               if (this.recoveredField939 > var5) {
                  this.recoveredField939 = var5;
               }
            }
         } else if (this.recoveredField939 > 0) {
            float var7 = CBModulesGui.getSmoothFloat(790.0F);
            this.recoveredField939 = this.recoveredField939 - var7 < 0.0F ? 0 : (int)(this.recoveredField939 - var7);
         }

         if (this.recoveredField939 > 0) {
            float var8 = (float)this.recoveredField939 / var5 * 100.0F;
            Gui.a(this.x, (int)(this.y + (this.height - this.height * var8 / 100.0F)), this.x + this.width, this.y + this.height, this.recoveredField940);
         }
      }

      float var9 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var9, var9, var9, 0.35F);
      RenderUtil.drawIcon(this.rightIcon, 2.5F, this.x + 6, this.y + 6.0F);
      CheatBreaker.getInstance()
         .recoveredField1595
         .drawString(
            this.module.getName().toUpperCase(),
            this.x + 14.0F,
            this.y + 3.0F,
            this.parent.method_03198(this.module)
               ? (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1667 : GuiThemeColors.recoveredField1670)
               : (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674)
         );
   }
}
