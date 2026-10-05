package net.optifine.gui;

import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.settings.GameSettings;

public class GuiOptionSliderOF extends GuiOptionSlider implements IOptionControl {
   public GameSettings.Options option = null;

   @Override
   public GameSettings.Options getOption() {
      return this.option;
   }

   public GuiOptionSliderOF(int var1, int var2, int var3, GameSettings.Options var4) {
      super(var1, var2, var3, var4);
      this.option = var4;
   }
}
