package com.cheatbreaker.client.ui.element.type.custom;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public class GlobalSettingsElement extends AbstractModulesGuiElement {
   public int recoveredField1604 = 0;
   public AbstractScrollableElement recoveredField1605;
   public ResourceLocation recoveredField1606 = new ResourceLocation("client/icons/right.png");
   public int recoveredField1607;

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   public GlobalSettingsElement(AbstractScrollableElement var1, int var2, float var3) {
      super(var3);
      this.recoveredField1605 = var1;
      this.recoveredField1607 = var2;
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
      float var6 = CBModulesGui.getSmoothFloat(790.0F);
      if (var4) {
         if (this.recoveredField1604 < var5) {
            this.recoveredField1604 = (int)(this.recoveredField1604 + var6);
            if (this.recoveredField1604 > var5) {
               this.recoveredField1604 = var5;
            }
         }
      } else if (this.recoveredField1604 > 0) {
         this.recoveredField1604 = this.recoveredField1604 - var6 < 0.0F ? 0 : (int)(this.recoveredField1604 - var6);
      }

      if (this.recoveredField1604 > 0) {
         float var7 = (float)this.recoveredField1604 / var5 * 100.0F;
         Gui.a(this.x, (int)(this.y + (this.height - this.height * var7 / 100.0F)), this.x + this.width, this.y + this.height, this.recoveredField1607);
      }

      float var8 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var8, var8, var8, 0.35F);
      RenderUtil.drawIcon(this.recoveredField1606, 2.5F, this.x + 6, this.y + 6.0F);
      CheatBreaker.getInstance()
         .recoveredField1595
         .drawString(
            "CheatBreaker Settings".toUpperCase(),
            this.x + 14.0F,
            this.y + 3.0F,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1667 : GuiThemeColors.recoveredField1670
         );
   }
}
