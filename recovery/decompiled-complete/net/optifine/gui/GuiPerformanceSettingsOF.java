package net.optifine.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.WorldRenderer$2;
import net.minecraft.client.renderer.entity.RenderSpider;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings$Options;
import net.minecraft.item.crafting.RecipesFood;
import net.optifine.config.EntityClassLocator;
import net.optifine.http.HttpUtils;
import org.slf4j.helpers.NOPLoggerFactory;

public class GuiPerformanceSettingsOF extends GuiScreen {
   public static GameSettings$Options[] enumOptions = new GameSettings$Options[]{
      GameSettings$Options.SMOOTH_FPS,
      GameSettings$Options.SMOOTH_WORLD,
      GameSettings$Options.FAST_RENDER,
      GameSettings$Options.FAST_MATH,
      GameSettings$Options.CHUNK_UPDATES,
      GameSettings$Options.CHUNK_UPDATES_DYNAMIC,
      GameSettings$Options.RENDER_REGIONS,
      GameSettings$Options.LAZY_CHUNK_LOADING,
      GameSettings$Options.SMART_ANIMATIONS
   };
   public GameSettings settings;
   public RecipesFood field_0004;
   public NOPLoggerFactory field_0007;
   public EntityClassLocator field_0001;
   public RenderSpider field_0002;
   public GuiScreen prevScreen;
   public HttpUtils field_0006;
   public WorldRenderer$2 field_0003;
   public String title;
   public TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.title, this.l / 2, 15, 16777215);
      super.drawScreen(var1, var2, var3);
      this.tooltipManager.drawTooltips(var1, var2, this.n);
   }

   public GuiPerformanceSettingsOF(GuiScreen var1, GameSettings var2) {
      this.prevScreen = var1;
      this.settings = var2;
   }

   @Override
   public void initGui() {
      this.title = I18n.format("of.options.performanceTitle");
      this.n.clear();

      for (int var1 = 0; var1 < enumOptions.length; var1++) {
         GameSettings$Options var2 = enumOptions[var1];
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

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k < 200 && var1 instanceof GuiOptionButton) {
            this.settings.setOptionValue(((GuiOptionButton)var1).returnEnumOptions(), 1);
            var1.j = this.settings.getKeyBinding(GameSettings$Options.getEnumOptions(var1.k));
         }

         if (var1.k == 200) {
            this.j.gameSettings.saveOptions();
            this.j.displayGuiScreen(this.prevScreen);
         }
      }
   }
}
