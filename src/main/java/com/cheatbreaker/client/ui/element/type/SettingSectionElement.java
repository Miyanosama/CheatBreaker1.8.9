package com.cheatbreaker.client.ui.element.type;

import com.cheatbreaker.client.ui.util.GuiThemeColors;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import net.minecraft.client.gui.Gui;

public class SettingSectionElement extends AbstractModulesGuiElement {
   public SettingSectionElement(Setting var1, float var2) {
      super(var2);
      this.setting = var1;
      this.height = 12;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
   }

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      CheatBreaker.getInstance()
         .recoveredField1589
         .drawString(
            ((String)this.setting.getValue()).toUpperCase(),
            this.x + 2,
            this.y + 2,
            GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1676 : GuiThemeColors.recoveredField1662
         );
      Gui.a(
         this.x + 2,
         this.y + this.height - 1,
         this.x + this.width / 2 - 20,
         this.y + this.height,
         GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1657 : GuiThemeColors.recoveredField1681
      );
   }
}
