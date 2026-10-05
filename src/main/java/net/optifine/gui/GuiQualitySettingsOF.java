package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiQualitySettingsOF extends GuiScreen {
   public String title;
   public GameSettings settings;
   public TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());
   public GuiScreen prevScreen;
   public static GameSettings.Options[] enumOptions = new GameSettings.Options[]{
      GameSettings.Options.MIPMAP_LEVELS,
      GameSettings.Options.MIPMAP_TYPE,
      GameSettings.Options.AF_LEVEL,
      GameSettings.Options.AA_LEVEL,
      GameSettings.Options.CLEAR_WATER,
      GameSettings.Options.RANDOM_ENTITIES,
      GameSettings.Options.BETTER_GRASS,
      GameSettings.Options.BETTER_SNOW,
      GameSettings.Options.CUSTOM_FONTS,
      GameSettings.Options.CUSTOM_COLORS,
      GameSettings.Options.CONNECTED_TEXTURES,
      GameSettings.Options.NATURAL_TEXTURES,
      GameSettings.Options.CUSTOM_SKY,
      GameSettings.Options.CUSTOM_ITEMS,
      GameSettings.Options.CUSTOM_ENTITY_MODELS,
      GameSettings.Options.CUSTOM_GUIS,
      GameSettings.Options.EMISSIVE_TEXTURES
   };

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.title, this.l / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
      this.tooltipManager.drawTooltips(var1, var2, this.n);
   }

   public GuiQualitySettingsOF(GuiScreen var1, GameSettings var2) {
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

         if (var1.k != GameSettings.Options.AA_LEVEL.ordinal()) {
            ScaledResolution var2 = new ScaledResolution(this.j);
            this.setWorldAndResolution(this.j, var2.getScaledWidth(), var2.getScaledHeight());
         }
      }
   }

   @Override
   public void initGui() {
      this.title = I18n.format("of.options.qualityTitle");
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

      this.n.add(new GuiButton(200, this.l / 2 - 100, this.m / 6 + 168 + 11, I18n.format("gui.done")));
   }
}
