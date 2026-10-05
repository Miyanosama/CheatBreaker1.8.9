package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public class ModulesGuiButtonElement extends AbstractModulesGuiElement {
   public boolean recoveredField2845;
   public AbstractScrollableElement recoveredField2846;
   public int recoveredField2847;
   public int recoveredField2848;
   public CBFontRenderer recoveredField2849;
   public boolean recoveredField2850;
   public boolean recoveredField2851;
   public boolean recoveredField2852;
   public boolean recoveredField2853 = true;
   public String displayString;
   public boolean recoveredField2854;

   public void method_07928(boolean var1) {
      this.recoveredField2853 = var1;
   }

   public ModulesGuiButtonElement(
      CBFontRenderer var1, AbstractScrollableElement var2, String var3, int var4, int var5, int var6, int var7, int var8, float var9, boolean var10
   ) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.recoveredField2850 = var10;
   }

   public void method_07931(boolean var1) {
      this.recoveredField2845 = var1;
   }

   public void method_07927(boolean var1) {
      this.recoveredField2854 = var1;
   }

   public ModulesGuiButtonElement(
      CBFontRenderer var1,
      AbstractScrollableElement var2,
      String var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      float var9,
      boolean var10,
      boolean var11
   ) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      this.recoveredField2850 = var10;
      this.recoveredField2851 = var11;
   }

   public ModulesGuiButtonElement(AbstractScrollableElement var1, String var2, int var3, int var4, int var5, int var6, int var7, float var8) {
      this(CheatBreaker.getInstance().recoveredField1549, var1, var2, var3, var4, var5, var6, var7, var8);
      this.recoveredField2850 = true;
   }

   public int method_07929() {
      return this.width;
   }

   public void method_07930(boolean var1) {
      this.recoveredField2852 = var1;
   }

   public ModulesGuiButtonElement(
      CBFontRenderer var1, AbstractScrollableElement var2, String var3, int var4, int var5, int var6, int var7, int var8, float var9
   ) {
      super(var9);
      this.recoveredField2848 = 0;
      this.displayString = var3;
      this.setDimensions(var4, var5, var6, var7);
      this.recoveredField2847 = var8;
      this.recoveredField2846 = var2;
      this.recoveredField2849 = var1;
      this.recoveredField2850 = true;
   }

   public ModulesGuiButtonElement(AbstractScrollableElement var1, String var2, int var3, int var4, int var5, int var6, int var7, float var8, boolean var9) {
      this(CheatBreaker.getInstance().recoveredField1549, var1, var2, var3, var4, var5, var6, var7, var8);
      this.recoveredField2850 = var9;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var5 = this.isMouseInside(var1, var2);
      byte var6 = 120;
      if (var5 && this.recoveredField2853) {
         if (this.recoveredField2850) {
            Gui.a(
               this.x - 2,
               this.y - 2,
               this.x + this.width + 2,
               this.y + this.height + 2,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1656 : GuiThemeColors.recoveredField1673
            );
         }

         float var11 = CBModulesGui.getSmoothFloat(790.0F);
         this.recoveredField2848 = this.recoveredField2848 + var11 < var6 ? (int)(this.recoveredField2848 + var11) : var6;
      } else if (this.recoveredField2848 > 0) {
         float var4 = CBModulesGui.getSmoothFloat(790.0F);
         this.recoveredField2848 = this.recoveredField2848 - var4 < 0.0F ? 0 : (int)(this.recoveredField2848 - var4);
      }

      if (this.recoveredField2851) {
         Gui.a(
            this.x,
            this.y,
            this.x + this.width,
            this.y + this.height,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1672 : GuiThemeColors.recoveredField1668
         );
      }

      if (this.recoveredField2850) {
         if (this.recoveredField2853) {
            Gui.a(
               this.x,
               this.y,
               this.x + this.width,
               this.y + this.height,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1648 : GuiThemeColors.recoveredField1678
            );
         } else {
            Gui.a(
               this.x,
               this.y,
               this.x + this.width,
               this.y + this.height,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1649 : GuiThemeColors.recoveredField1650
            );
         }
      }

      if (this.recoveredField2848 > 0) {
         float var12 = (float)this.recoveredField2848 / var6 * 100.0F;
         Gui.a(this.x, (int)(this.y + (this.height - this.height * var12 / 100.0F)), this.x + this.width, this.y + this.height, this.recoveredField2847);
      }

      if (this.displayString.contains(".png")) {
         float var7 = this.recoveredField2852 ? 3.5F : 8.0F;
         float var8 = this.recoveredField2845 ? this.x + 2.0F : (this.recoveredField2852 ? (float)(this.x + 1.6) : this.x + 6);
         float var9 = this.recoveredField2845 ? this.y + 2.0F : (this.recoveredField2852 ? (float)(this.y + 1.7) : this.y + 6);
         GL11.glPushMatrix();
         float var10 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
         GL11.glColor4f(var10, var10, var10, 0.45F);
         RenderUtil.drawIcon(new ResourceLocation("client/icons/" + this.displayString), var7, var8, var9);
         GL11.glPopMatrix();
      } else {
         float var13 = this.recoveredField2849 == CheatBreaker.getInstance().recoveredField1549 ? 2.0F : 0.5F;
         this.recoveredField2849
            .drawCenteredString(
               this.displayString.toUpperCase(),
               this.x + this.width / 2,
               this.y + this.height / 2 - this.recoveredField2849.getHeight() + var13,
               this.recoveredField2854
                  ? 11141120
                  : (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662)
            );
      }
   }
}
