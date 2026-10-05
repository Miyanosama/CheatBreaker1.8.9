package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiOtherSettingsOF extends GuiScreen implements GuiYesNoCallback {
   public TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());
   public String title;
   public GuiScreen prevScreen;
   public GameSettings settings;
   public static GameSettings.Options[] enumOptions = new GameSettings.Options[]{
      GameSettings.Options.LAGOMETER,
      GameSettings.Options.PROFILER,
      GameSettings.Options.SHOW_FPS,
      GameSettings.Options.ADVANCED_TOOLTIPS,
      GameSettings.Options.WEATHER,
      GameSettings.Options.TIME,
      GameSettings.Options.USE_FULLSCREEN,
      GameSettings.Options.FULLSCREEN_MODE,
      GameSettings.Options.ANAGLYPH,
      GameSettings.Options.AUTOSAVE_TICKS,
      GameSettings.Options.SCREENSHOT_SIZE,
      GameSettings.Options.SHOW_GL_ERRORS
   };

   @Override
   public void initGui() {
      this.title = I18n.format("of.options.otherTitle");
      this.n.clear();

      for (int var1 = 0; var1 < enumOptions.length; var1++) {
         GameSettings.Options var2 = enumOptions[var1];
         int var3 = this.l / 2 - 155 + var1 % 2 * 160;
         int var4 = this.m / 6 + 21 * (var1 / 2) - 12;
         if (!var2.getEnumFloat()) {
            this.n.add(new GuiOptionButtonOF(var2.returnEnumOrdinal(), var3, var4, var2, this.settings.getKeyBinding(var2)));
         } else {
            this.n.add(new GuiOptionSliderOF(var2.returnEnumOrdinal(), var3, var4, var2));
         }
      }

      this.n.add(new GuiButton(210, this.l / 2 - 100, this.m / 6 + 168 + 11 - 44, I18n.format("of.options.other.reset")));
      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 168 + 11, I18n.format("gui.done")));
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.title, this.l / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
      this.tooltipManager.drawTooltips(var1, var2, this.n);
   }

   public GuiOtherSettingsOF(GuiScreen var1, GameSettings var2) {
      this.prevScreen = var1;
      this.settings = var2;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k < 200 && var1 instanceof GuiOptionButton) {
            this.settings.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
            var1.j = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(var1.k));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.prevScreen);
         }

         if (var1.k == 210) {
            this.j.gameSettings.saveOptions();
            GuiYesNo var2 = new GuiYesNo(this, I18n.format("of.message.other.reset"), "", 9999);
            this.j.displayGuiScreen(var2);
         }
      }
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var1) {
         this.j.gameSettings.resetSettings();
      }

      this.j.displayGuiScreen(this);
   }
}
