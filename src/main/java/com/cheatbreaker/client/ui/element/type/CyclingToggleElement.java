package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.util.GuiThemeColors;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CyclingToggleElement extends AbstractModulesGuiElement {
   private final String displayName;
   public String recoveredField1971;
   public int recoveredField1972;
   public ResourceLocation recoveredField1973;
   public ResourceLocation recoveredField1974 = new ResourceLocation("client/icons/left.png");
   public float recoveredField1975;

   public CyclingToggleElement(Setting var1, float var2) {
      this(var1, var2, var1.method_08911());
   }

   public CyclingToggleElement(Setting var1, float var2, String displayName) {
      super(var2);
      this.displayName = displayName;
      this.recoveredField1973 = new ResourceLocation("client/icons/right.png");
      this.recoveredField1972 = 0;
      this.recoveredField1975 = 0.0F;
      this.setting = var1;
      this.height = 12;
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var4 = var1 > (this.x + this.width - 48.0F) * this.scale
         && var1 < (this.x + this.width - 10.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 92.0F) * this.scale
         && var1 < (this.x + this.width - 48.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(
            this.displayName.toUpperCase(),
            this.x + 10,
            this.y + 2,
            !var5 && !var4
               ? (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654)
               : (GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1665 : GuiThemeColors.recoveredField1663)
         );
      if (this.recoveredField1972 == 0) {
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawCenteredString(
               this.setting.method_08908() ? this.setting.method_08871() : this.setting.method_08919(),
               this.x + this.width - 48,
               this.y + 2,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
            );
      } else {
         boolean var6 = this.recoveredField1972 == 1;
         float var10002 = this.x + this.width - 48.0F - (var6 ? -this.recoveredField1975 : this.recoveredField1975);
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawCenteredString(
               this.recoveredField1971,
               var10002,
               this.y + 2,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
            );
         if (var6) {
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawCenteredString(
                  this.setting.method_08908() ? this.setting.method_08871() : this.setting.method_08919(),
                  this.x + this.width - 98 + this.recoveredField1975,
                  this.y + 2,
                  GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
               );
         } else {
            CheatBreaker.getInstance()
               .recoveredField1589
               .drawCenteredString(
                  this.setting.method_08908() ? this.setting.method_08871() : this.setting.method_08919(),
                  this.x + this.width + 2 - this.recoveredField1975,
                  this.y + 2,
                  GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1679 : GuiThemeColors.recoveredField1654
               );
         }

         if (this.recoveredField1975 >= 50.0F) {
            this.recoveredField1972 = 0;
            this.recoveredField1975 = 0.0F;
         } else {
            float var7 = CBModulesGui.getSmoothFloat(50.0F + this.recoveredField1975 * 15.0F);
            this.recoveredField1975 = Math.min(this.recoveredField1975 + var7, 50.0F);
         }

         Gui.a(
            this.x + this.width - 130,
            this.y + 2,
            this.x + this.width - 72,
            this.y + 12,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1678
         );
         Gui.a(
            this.x + this.width - 22,
            this.y + 2,
            this.x + this.width + 4,
            this.y + 12,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1664 : GuiThemeColors.recoveredField1678
         );
      }

      float var8 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var8, var8, var8, var5 ? 0.8F : 0.45F);
      RenderUtil.drawIcon(this.recoveredField1974, 4.0F, this.x + this.width - 82.0F, this.y + 3.0F);
      GL11.glColor4f(var8, var8, var8, var4 ? 0.8F : 0.45F);
      RenderUtil.drawIcon(this.recoveredField1973, 4.0F, this.x + this.width - 22.0F, this.y + 3.0F);
      this.method_23220(this.setting, var1, var2);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + this.width - 92.0F) * this.scale
         && var1 < (this.x + this.width - 48.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 48.0F) * this.scale
         && var1 < (this.x + this.width - 10.0F) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + 10.0F + this.yOffset) * this.scale;
      if ((var4 || var5) && this.recoveredField1972 == 0) {
         this.recoveredField1972 = var4 ? 1 : 2;
         this.recoveredField1975 = 0.0F;
         this.recoveredField1971 = (Boolean)this.setting.getValue() ? this.setting.method_08871() : this.setting.method_08919();
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.setting.setValue(!(Boolean)this.setting.getValue());
         if (this.setting == CheatBreaker.getInstance().getGlobalSettings().recoveredField564
            && !(Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField564.getValue()) {
            CheatBreaker.getInstance().getModuleManager().teammatesModule.method_01340(false);
         }
      }
   }
}
